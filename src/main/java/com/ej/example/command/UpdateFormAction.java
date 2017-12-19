package com.ej.example.command;

import com.ej.example.dao.BoardDAO;
import com.ej.example.domain.BoardDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class UpdateFormAction implements IAction {

    public String processCommand(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        BoardDAO boardDao = new BoardDAO();

        int seq = Integer.parseInt(request.getParameter("seq"));
        BoardDTO dto = boardDao.selectOne(seq);

        request.setAttribute("board", dto);

        return "/board/board_update_form.jsp";
    }

}
