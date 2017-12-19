package com.ej.example.dao;

import com.ej.example.domain.BoardDTO;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

public class BoardDAO2 extends BoardConnectionManager {


    public List<BoardDTO> selectList(int page, int rowCount) throws SQLException {
        String query = "SELECT * FROM BOARD ORDER BY CREATE_DATE DESC";
        return executeQuery(query);
    }

    public BoardDTO selectOne(final int seq) throws SQLException {
        String query = "SELECT * FROM BOARD WHERE SEQ = ?";
        List<BoardDTO> result = executeQuery(query, seq);
        return result != null && result.size() > 0 ? result.get(0) : null;
    }


    public int insert(final BoardDTO dto) throws SQLException {
        String query = "INSERT INTO board (writer,password,subject,content) VALUES (?, ?, ?, ?)";
        return executeUpdate(query, dto.getWriter(), dto.getPassword(), dto.getSubject(), dto.getContent());
    }


    public int update(final BoardDTO dto) throws SQLException {
        String query = "UPDATE board SET writer = ?, subject = ?, content = ? , update_date = ? WHERE seq = ? ";
        return executeUpdate(query, dto.getWriter(), dto.getSubject(), dto.getContent(), new SimpleDateFormat("yyyy-MM-dd HH:hh:mm").format(new java.util.Date()), dto.getSeq());
    }

    public int updateReadCount(final BoardDTO dto) throws SQLException {
        String query = "UPDATE board SET read_count = ? WHERE SEQ = ?";
        return executeUpdate(query, dto.getReadCount() + 1, dto.getSeq());
    }

    public int delete(final int seq) throws SQLException {
        String query = "DELETE FROM board WHERE seq = ?";
        return executeUpdate(query, seq);
    }

}
