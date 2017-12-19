package com.ej.example.datasource;

import org.apache.commons.dbcp.BasicDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public abstract class ConnectionManager<T> {

    private static BasicDataSource dataSource;

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

        dataSource = ds;
        return dataSource;

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


    // query, param 으로 List<dto>
   /* private DTO findDTO(String query, DTO dto) {

        conn = getConnection();
        pstmt = conn.prepareStatement(query);
        rs = pstmt.executeQuery();


        return t;
    }
*/

}
