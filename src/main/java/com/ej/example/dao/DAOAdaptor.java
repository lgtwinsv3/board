package com.ej.example.dao;

public abstract class DAOAdaptor {
    protected DAO dao(Class<? extends DAO> c) {
        DAO dao = null;
        try {
            dao = c.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return dao;
    }
}
