package edu.upc.dsa.CRUD.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueryHelper {
    public QueryHelper() {    }


    public static String createQueryINSERT(Object entity) throws NoSuchFieldException, InvocationTargetException, IllegalAccessException {
        StringBuffer buffer = new StringBuffer("INSERT INTO ");
        buffer.append(entity.getClass().getSimpleName()).append(" (");
        String[] fields = ObjectHelper.getFields(entity);
        String[] var3 = fields;
        int var4 = fields.length;

        int var5;
        for(var5 = 0; var5 < var4; ++var5) {
            String field = var3[var5];
            buffer.append(field).append(", ");
        }

        buffer.setLength(buffer.length() - 2);
        buffer.append(") VALUES (");
        var3 = fields;
        var4 = fields.length;

        for(var5 = 0; var5 < var4; ++var5) {
            String var10000 = var3[var5];
            buffer.append("?, ");
        }

        buffer.setLength(buffer.length() - 2);
        buffer.append(")");
        return buffer.toString();
    }

    public static String createQuerySELECT(Object entity) throws NoSuchFieldException, InvocationTargetException, IllegalAccessException {
        String field = (String) Arrays.stream(ObjectHelper.getFields(entity)).filter((x) -> {
            return x.matches("(?i).*id.*");
        }).findFirst().orElse((String) null);
        StringBuffer buffer = new StringBuffer();
        buffer.append("SELECT * FROM ").append(entity.getClass().getSimpleName());
        buffer.append(" WHERE ").append(field);
        buffer.append(" = ?");
        return buffer.toString();
    }

    public static String createQuerySELECT3(Object entity, String attribute) throws NoSuchFieldException, InvocationTargetException, IllegalAccessException {
        String field = (String) Arrays.stream(ObjectHelper.getFields(entity)).filter((x) -> {
            return x.matches("(?i).*"+ attribute +".*");
        }).findFirst().orElse((String) null);
        StringBuffer buffer = new StringBuffer();
        buffer.append("SELECT * FROM ").append(entity.getClass().getSimpleName());
        buffer.append(" WHERE ").append(field);
        buffer.append(" = ?");
        return buffer.toString();
    }

    public static String createQuerySELECT2(Object entity, List<String> attributes) throws NoSuchFieldException, InvocationTargetException, IllegalAccessException {
        List<String> fields= new ArrayList<String>();

        for (int i = 0; i< attributes.size(); i++){
            String attri = attributes.get(i);
            String field = (String) Arrays.stream(ObjectHelper.getFields(entity)).filter((x) -> {
                return x.matches("(?i).*"+ attri +".*");
            }).findFirst().orElse((String) null);
            fields.add(field);
        }

        StringBuffer buffer = new StringBuffer();
        buffer.append("SELECT * FROM ").append(entity.getClass().getSimpleName());
        buffer.append(" WHERE ").append(fields.get(0));
        buffer.append(" = ?");

        for (int i = 1; i< fields.size(); i++) {
            buffer.append(" AND ");
            buffer.append(fields.get(i));
            buffer.append(" = ?");
        }
        return buffer.toString();
    }

    public static String createQueryUPDATE(Object entity) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("UPDATE ").append(entity.getClass().getSimpleName());
        buffer.append(" SET ");
        String[] fields = ObjectHelper.getFields(entity);
        String[] var3 = fields;
        int var4 = fields.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            String field = var3[var5];
            buffer.append(field).append(" = ?, ");
        }

        buffer.setLength(buffer.length() - 2);
        buffer.append(" WHERE ").append(ObjectHelper.getIdAttributeName(entity.getClass())).append(" = ?");
        return buffer.toString();
    }

    public static String createQueryDELETE(Object entity) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("DELETE FROM ").append(entity.getClass().getSimpleName());
        buffer.append(" WHERE ").append(ObjectHelper.getIdAttributeName(entity.getClass())).append(" = ?");
        return buffer.toString();
    }

    public static String createQueryDeleteRecords(Class theClass) {
        StringBuffer query = new StringBuffer();
        query.append("DELETE FROM ").append(theClass.getSimpleName());
        return query.toString();
    }

    public static String createQuerySelectAll(Class theClass) {
        StringBuffer query = new StringBuffer();
        query.append("SELECT * FROM ").append(theClass.getSimpleName());
        return query.toString();
    }

}
