package com.ej.example.action.board;

import com.ej.example.dao.board.OldBoardDAO;
import com.ej.example.domain.BoardDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class UpdateBoardFormAction {

    public String processCommand(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        OldBoardDAO boardDao = new OldBoardDAO();

        int seq = Integer.parseInt(request.getParameter("seq"));
        BoardDTO dto = boardDao.selectOne(seq);

        request.setAttribute("board", dto);

        return "/board/board_update_form.jsp";
    }

}
