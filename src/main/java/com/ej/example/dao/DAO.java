package com.ej.example.dao;

import com.ej.example.datasource.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DAO extends ConnectionManager implements DAOSupport {

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public DAO(String sql) throws SQLException {
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rs.close();
            pstmt.close();
            conn.close();
        }
    }
}
