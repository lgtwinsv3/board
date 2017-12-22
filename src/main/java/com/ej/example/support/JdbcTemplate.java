package com.ej.example.support;

import com.ej.example.datasource.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class JdbcTemplate extends ConnectionManager {

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public int executeUpdate(String query) throws SQLException {
        int rtn = 0;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            setParameters(pstmt);
            rtn = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }

            if (conn != null) {
                conn.close();
            }
        }
        return rtn;
    }

    public int executeQueryWithGeneratedKey(String query) throws SQLException {
        int rtn = 0;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            setParameters(pstmt);

            int affectRows = pstmt.executeUpdate();
            if (affectRows > 0) {
                rs = pstmt.getGeneratedKeys();
                while (rs.next()) {
                    rtn = (int) rs.getLong(1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }

            if (conn != null) {
                conn.close();
            }
        }
        return rtn;
    }

    protected abstract void setParameters(PreparedStatement pstmt) throws SQLException;
}
