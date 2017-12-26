package com.ej.example.action.board;

import com.ej.example.dao.board.OldBoardDAO;
import com.ej.example.domain.BoardDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class CreateBoardAction {

    public String processCommand(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        OldBoardDAO boardDao = new OldBoardDAO();

        BoardDTO dto = new BoardDTO();
        dto.setSubject(request.getParameter("subject"));
        dto.setWriter(request.getParameter("writer"));
        dto.setPassword(request.getParameter("password"));
        dto.setContent(request.getParameter("content"));

        int seq = boardDao.insert(dto);

        if (seq < 0) {
            throw new SQLException();
        }

        List<BoardDTO> dtoList = boardDao.selectList(1, 10);
        request.setAttribute("list", dtoList);

        return "/board/board_list.jsp";
    }

}
