package edu.upc.dsa.CRUD;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface Session {
    void save(Object entity) throws SQLException;
    void close() throws SQLException;
    Object get(Class theClass, String id);
    Object get(Class theClass, String attribute, String value) throws SQLException;
    void update(Object object) throws SQLException;
    void delete(Object object);
    List<Object> findAll(Class theClass);
    List<Object> findAll(Class theClass, HashMap<String, String> params) throws SQLException;
    List<Object> query(String query, Class theClass, HashMap params);
    void deleteRecords(Class theClass);
}
