package com.ej.example.dao;

import com.ej.example.domain.BoardDTO;
import com.ej.example.domain.DTO;
import com.ej.example.support.JdbcTemplate;
import com.ej.example.support.SelectJdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

public class BoardDAO3 implements DAOSupport<BoardDTO> {

    private String query;

    public List<BoardDTO> selectList(int page, int rowCount) throws SQLException {

        query = "SELECT * FROM BOARD ORDER BY CREATE_DATE DESC";

        SelectJdbcTemplate jdbcTemplate = new SelectJdbcTemplate<BoardDTO>() {
            protected Object createDTO(Class clz) throws SQLException {
                return null;
            }

            protected List executeQuery(String query, Object... params) {
                return null;
            }

            protected void setParameters(PreparedStatement pstmt) throws SQLException {

            }

            protected BoardDTO setDTO(ResultSet rs) throws SQLException {
                return null;
            }

            protected List<BoardDTO> setDTOList(ResultSet rs) throws SQLException {
                List<BoardDTO> dtoList = new LinkedList<BoardDTO>();
                if (rs != null) {
                    while (rs.next()) {
                        final BoardDTO dto = new BoardDTO();
                        dto.setSeq(rs.getInt("seq"));
                        dto.setWriter(rs.getString("writer"));
                        dto.setPassword(rs.getString("password"));
                        dto.setSubject(rs.getString("subject"));
                        dto.setContent(rs.getString("content"));
                        dto.setReadCount(rs.getInt("read_count"));
                        dto.setCreateDate(rs.getDate("create_date"));
                        dto.setUpdateDate(rs.getDate("update_date"));

                        dtoList.add(dto);
                    }
                }

                return dtoList;
            }
        };

        return jdbcTemplate.findAll(new BoardDTO(), query);
    }

    public BoardDTO selectOne(final int seq) throws SQLException {
        final BoardDTO dto = new BoardDTO();
        query = "SELECT * FROM BOARD WHERE SEQ = ?";

        SelectJdbcTemplate jdbcTemplate = new SelectJdbcTemplate() {

            protected Object createDTO(Class clz) throws SQLException {
                return null;
            }

            protected List executeQuery(String query, Object... params) {
                return null;
            }

            @Override
            protected void setParameters(PreparedStatement pstmt) throws SQLException {
                pstmt.setInt(1, seq);
            }

            @Override
            public DTO setDTO(ResultSet rs) throws SQLException {
                if (rs != null) {
                    while (rs.next()) {

                        dto.setSeq(rs.getInt("seq"));
                        dto.setWriter(rs.getString("writer"));
                        dto.setPassword(rs.getString("password"));
                        dto.setSubject(rs.getString("subject"));
                        dto.setContent(rs.getString("content"));
                        dto.setReadCount(rs.getInt("read_count"));
                        dto.setCreateDate(rs.getDate("create_date"));
                        dto.setUpdateDate(rs.getDate("update_date"));

                    }
                }
                return dto;
            }

            protected List<? extends DTO> setDTOList(ResultSet rs) throws SQLException {
                return null;
            }

        };

        return (BoardDTO) jdbcTemplate.findBySeq(dto, query);
    }


    public int insert(final BoardDTO dto) throws SQLException {

        query = "INSERT INTO board (writer,password,subject,content) VALUES (?, ?, ?, ?)";

        JdbcTemplate template = new JdbcTemplate() {

            protected Object createDTO(Class clz) throws SQLException {
                return null;
            }

            protected List executeQuery(String query, Object... params) {
                return null;
            }

            @Override
            protected void setParameters(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, dto.getWriter());
                pstmt.setString(2, dto.getPassword());
                pstmt.setString(3, dto.getSubject());
                pstmt.setString(4, dto.getContent());

            }

        };
        return template.executeQueryWithGeneratedKey(query);
    }


    public int update(final BoardDTO dto) throws SQLException {

        query = "UPDATE board SET writer = ?, subject = ?, content = ? , update_date = ? WHERE seq = ? ";

        JdbcTemplate template = new JdbcTemplate() {

            protected Object createDTO(Class clz) throws SQLException {
                return null;
            }

            protected List executeQuery(String query, Object... params) {
                return null;
            }

            @Override
            protected void setParameters(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, dto.getWriter());
                pstmt.setString(2, dto.getSubject());
                pstmt.setString(3, dto.getContent());
                pstmt.setString(4, new SimpleDateFormat("yyyy-MM-dd HH:hh:mm").format(new java.util.Date()));
                pstmt.setInt(5, dto.getSeq());
            }
        };
        return template.executeUpdate(query);
    }

    public int updateReadCount(final BoardDTO dto) throws SQLException {

        query = "UPDATE board SET read_count = ? WHERE SEQ = ?";

        JdbcTemplate jdbcTemplate = new JdbcTemplate() {

            protected Object createDTO(Class clz) throws SQLException {
                return null;
            }

            protected List executeQuery(String query, Object... params) {
                return null;
            }

            @Override
            protected void setParameters(PreparedStatement pstmt) throws SQLException {
                pstmt.setInt(1, dto.getReadCount() + 1);
                pstmt.setInt(2, dto.getSeq());
            }
        };
        return jdbcTemplate.executeUpdate(query);

    }

    public int delete(final int seq) throws SQLException {
        query = "DELETE FROM board WHERE seq = ?";

        JdbcTemplate jdbcTemplate = new JdbcTemplate() {

            protected Object createDTO(Class clz) throws SQLException {
                return null;
            }

            protected List executeQuery(String query, Object... params) {
                return null;
            }

            @Override
            protected void setParameters(PreparedStatement pstmt) throws SQLException {
                pstmt.setInt(1, seq);

            }
        };

        return jdbcTemplate.executeUpdate(query);
    }
}
