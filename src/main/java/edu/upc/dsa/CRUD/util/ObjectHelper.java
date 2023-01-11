package edu.upc.dsa.CRUD.util;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ObjectHelper {
    public ObjectHelper() {
    }

    public static String[] getFields(Object entity) {
        Class theClass = entity.getClass();
        Field[] fields = theClass.getDeclaredFields();
        String[] fieldsStringNames = new String[fields.length];
        int i = 0;

        for(int var7 = 0; var7 < fields.length; ++var7) {
            Field field = fields[var7];
            fieldsStringNames[var7] = field.getName();
        }

        return fieldsStringNames;
    }

    public static void setter(Object object, String property, Object value) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException {
        Method method = (Method)Arrays.stream(object.getClass().getMethods()).filter((x) -> {
            return x.getName().matches("(?i).*set" + property + ".*");
        }).findFirst().orElse((Method) null);

        assert method != null;

        method.invoke(object, value);
    }


    public static Object getter(Object object, String property) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException {
        Method method = (Method)Arrays.stream(object.getClass().getMethods()).filter((x) -> {
            return x.getName().matches("(?i).*get" + property + ".*");
        }).findFirst().orElse((Method) null);

        assert method != null;

        return method.invoke(object, (Object[])null);
    }

    public static String getIdAttributeName(Class theClass) {
        Field field = (Field)Arrays.stream(theClass.getDeclaredFields()).filter((x) -> {
            return x.getName().matches("(?i).*id.*");
        }).findFirst().orElse((Field) null);

        assert field != null;

        return field.getName();
    }


    public static String getAttributeName(Class theClass, String attribute) {
        Field field = (Field)Arrays.stream(theClass.getDeclaredFields()).filter((x) -> {
            return x.getName().matches("(?i).*"+ attribute +".*");
        }).findFirst().orElse((Field) null);

        assert field != null;

        return field.getName();
    }


    public static List<Object> createObjects(ResultSet resultSet, Class theClass) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, InvocationTargetException {
        List<Object> objects = new ArrayList();
        String[] fields = getFields(theClass.newInstance());

        while(resultSet.next()) {
            Object object = theClass.newInstance();
            String[] var5 = fields;
            int var6 = fields.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                String field = var5[var7];
                setter(object, field, resultSet.getObject(field));
            }

            objects.add(object);
        }

        return objects;
    }



    public static boolean assertEqual(Object object1, Object object2) throws NoSuchFieldException, InvocationTargetException, IllegalAccessException {
        String[] getFields1 = getFields(object1);
        String[] getFields2 = getFields(object2);
        if (!Arrays.equals(getFields1, getFields2)) {
            return false;
        } else {
            String[] var4 = getFields1;
            int var5 = getFields1.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                String field = var4[var6];
                if (!getter(object1, field).equals(getter(object2, field))) {
                    return false;
                }
            }

            return true;
        }
    }
}
