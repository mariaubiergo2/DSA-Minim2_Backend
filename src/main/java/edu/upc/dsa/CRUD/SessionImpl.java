package edu.upc.dsa.CRUD;

import edu.upc.dsa.CRUD.util.ObjectHelper;
import edu.upc.dsa.CRUD.util.QueryHelper;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SessionImpl implements Session {
    private final Connection conn;

    public SessionImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void save(Object entity) throws SQLException {
        try{
            String insertQuery = QueryHelper.createQueryINSERT(entity);

            PreparedStatement statement = conn.prepareStatement(insertQuery);
            int i = 1;

            for(String field: ObjectHelper.getFields(entity)) {
                statement.setObject(i++, ObjectHelper.getter(entity, field));
            }
            statement.executeQuery();
        } catch (NoSuchFieldException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void close() throws SQLException {
        this.conn.close();
    }

    @Override
    public Object get(Class theClass, String id) {
        try {
            Object entity = theClass.newInstance();
            ObjectHelper.setter(entity, ObjectHelper.getIdAttributeName(theClass), id);
            String selectQuery = QueryHelper.createQuerySELECT(entity);
            PreparedStatement statement = this.conn.prepareStatement(selectQuery);
            statement.setObject(1, id);
            entity = ObjectHelper.createObjects(statement.executeQuery(), theClass).get(0);

            assert entity != null;

            return entity;
        } catch (SQLException | InstantiationException | IllegalAccessException | NoSuchFieldException | InvocationTargetException | ClassNotFoundException var6) {
            throw new RuntimeException(var6);
        }
    }

    @Override
    public Object get(Class theClass, String attribute, String value) throws SQLException {
        try {
            Object entity = theClass.newInstance();
            ObjectHelper.setter(entity, ObjectHelper.getAttributeName(theClass, attribute), value);
            String selectQuery = QueryHelper.createQuerySELECT3(entity, attribute);
            PreparedStatement statement = this.conn.prepareStatement(selectQuery);
            statement.setObject(1, value);
            List<Object> llista = ObjectHelper.createObjects(statement.executeQuery(), theClass);

            if (llista.size()!=0){
                entity=llista.get(0);
                //assert entity != null;
                return entity;
            }
            throw new SQLException();

        } catch (InstantiationException | IllegalAccessException | NoSuchFieldException | InvocationTargetException | ClassNotFoundException var6) {
            throw new RuntimeException(var6);
        }
    }


    @Override
    public void update(Object object) throws SQLException {
        try{
            String updateQuery = QueryHelper.createQueryUPDATE(object);
            PreparedStatement statement = conn.prepareStatement(updateQuery);
            int i = 1;

            for(String field: ObjectHelper.getFields(object)) {
                statement.setObject(i++, ObjectHelper.getter(object, field));
            }
            statement.setObject(i, ObjectHelper.getter(object, ObjectHelper.getAttributeName(object.getClass(), "id")));

            statement.executeQuery();
        } catch (NoSuchFieldException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Object object) {
        try {
            String updateQuery = QueryHelper.createQueryDELETE(object);
            PreparedStatement statement = this.conn.prepareStatement(updateQuery);
            statement.setObject(1, ObjectHelper.getter(object, ObjectHelper.getIdAttributeName(object.getClass())));
            statement.executeQuery();
        } catch (NoSuchFieldException | InvocationTargetException | IllegalAccessException | SQLException var4) {
            throw new RuntimeException(var4);
        }
    }


    public List<Object> findAll(Class theClass) {
        String selectQuery = QueryHelper.createQuerySelectAll(theClass);
        List<Object> objects = null;

        try {
            PreparedStatement statement = this.conn.prepareStatement(selectQuery);
            objects = ObjectHelper.createObjects(statement.executeQuery(), theClass);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchFieldException | InvocationTargetException | SQLException var6) {
            var6.printStackTrace();
        }

        return objects;
    }

    @Override
    public List<Object> findAll(Class theClass, HashMap<String, String> params) throws SQLException {
        List<Object> objects = null;

        try {
            List<String> keys = new ArrayList<>(params.keySet());
            List<String> values = new ArrayList<>(params.values());

            Object entity = theClass.newInstance();

            for (int i = 0; i<params.size(); i++){
                ObjectHelper.setter(entity, ObjectHelper.getAttributeName(theClass, keys.get(i)), values.get(i));
            }
            String selectQuery = QueryHelper.createQuerySELECT2(entity, keys);
            PreparedStatement statement = this.conn.prepareStatement(selectQuery);

            for (int i = 0; i< params.size(); i++){
                statement.setObject(i+1, values.get(i));
            }


            objects = ObjectHelper.createObjects(statement.executeQuery(), theClass);


            return objects;

        } catch (SQLException | NoSuchFieldException | ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }


    public List<Object> query(String query, Class theClass, HashMap params) {
        return null;
    }


    public void deleteRecords(Class theClass) {
        String selectQuery = QueryHelper.createQueryDeleteRecords(theClass);

        try {
            PreparedStatement statement = this.conn.prepareStatement(selectQuery);
            statement.executeQuery();
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

    }

}
