package com.ej.example.service;

import com.ej.example.dao.board.BoardDAO;
import com.ej.example.domain.BoardDTO;
import com.ej.example.domain.Paging;

import java.sql.SQLException;
import java.util.List;

public class BoardService implements IService<BoardDTO> {

    private BoardDAO dao;

    public BoardService() {
        this.dao = new BoardDAO();
    }

    public Paging<BoardDTO> getList(int page, int rowCount) throws SQLException {

        int count = dao.selectCount();

        Paging<BoardDTO> paging = new Paging<BoardDTO>();
        paging.calcPaging(count);

        List<BoardDTO> list = dao.selectList(paging.getSkipRowCount(), paging.getRowCount());

        paging.setBody(list);

        return paging;
    }

    public BoardDTO getOne(int seq) throws SQLException {
        BoardDTO boardDTO = dao.selectOne(seq);
        if (boardDTO != null) {
            dao.updateReadCount(boardDTO);
        }
        return boardDTO;
    }

    public int regist(BoardDTO dto) throws SQLException {
        return dao.insert(dto);
    }

    public int modify(BoardDTO dto) throws SQLException {
        return dao.update(dto);
    }

    public int remove(int seq) throws SQLException {
        return dao.delete(seq);
    }

}
