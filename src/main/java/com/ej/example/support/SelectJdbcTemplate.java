package com.ej.example.support;

import com.ej.example.datasource.ConnectionManager;
import com.ej.example.domain.DTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class SelectJdbcTemplate<T extends DTO> extends ConnectionManager {

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public List<T> findAll(T dto, String query) {
        List<T> dtoList = new ArrayList<T>();

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            setParameters(pstmt);
            rs = pstmt.executeQuery();

            dtoList = setDTOList(rs);
//            setDTO(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return dtoList;

    }


    public DTO findBySeq(DTO dto, String query) throws SQLException {

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            setParameters(pstmt);
            rs = pstmt.executeQuery();

            setDTO(rs);
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
        return dto;

    }


    protected abstract void setParameters(PreparedStatement pstmt) throws SQLException;

    protected abstract T setDTO(ResultSet rs) throws SQLException;

    protected abstract List<T> setDTOList(ResultSet rs) throws SQLException;
}
