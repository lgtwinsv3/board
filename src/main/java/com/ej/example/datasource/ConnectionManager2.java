package com.ej.example.datasource;

import org.apache.commons.dbcp.BasicDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public abstract class ConnectionManager2<T> {

    private Connection conn;
    private PreparedStatement pstmt;
    protected ResultSet rs;


    public BasicDataSource getDataSource() {
        Properties properties = readProperties();
        String url = properties.getProperty("url");
        String driverClassName = properties.getProperty("driverClassName");
        String userName = properties.getProperty("userName");
        String password = properties.getProperty("password");

        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(url);
        ds.setDriverClassName(driverClassName);
        ds.setUsername(userName);
        ds.setPassword(password);

        return ds;
    }

    protected Properties readProperties() {
        Properties properties = new Properties();
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }

    protected int executeUpdate(String query, Object... params) {
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);

            for (int i = 1; i < params.length + 1; i++) {
                pstmt.setObject(i, params[i - 1]);
            }

            int affectRows = pstmt.executeUpdate();
            int seq = 0;
            if (affectRows > 0) {
                rs = pstmt.getGeneratedKeys();
                while (rs.next()) {
                    seq = (int) rs.getLong(1);
                }
                if (seq == 0) {
                    return affectRows;
                }
            }
            return seq;


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }


    protected List executeQuery(String query, Class<T> clz, Object... params) {
        List<T> dtoList = new ArrayList<T>();
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            for (int i = 1; i < params.length + 1; i++) {
                pstmt.setObject(i, params[i - 1]);
            }
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    dtoList.add(createDTO(clz));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {

                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {

                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return dtoList;
    }

    protected abstract T createDTO(Class<T> clz) throws SQLException;

    protected abstract List<T> executeQuery(String query, Object... params);


}
