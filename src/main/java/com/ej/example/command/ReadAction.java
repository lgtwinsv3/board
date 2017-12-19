package com.ej.example.command;

import com.ej.example.dao.BoardDAO;
import com.ej.example.domain.BoardDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ReadAction implements IAction {

    public String processCommand(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        BoardDAO boardDao = new BoardDAO();
        int seq = Integer.parseInt(request.getParameter("seq"));
        BoardDTO dto = boardDao.selectOne(seq);
        boardDao.updateReadCount(dto);
        request.setAttribute("board", dto);

        return "/board/board_view.jsp";
    }

}
