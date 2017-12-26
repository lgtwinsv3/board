package com.ej.example.action.board;

import com.ej.example.action.ActionForward;
import com.ej.example.action.IAction;
import com.ej.example.dao.board.BoardDAO;
import com.ej.example.domain.BoardDTO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class ReadBoardAction implements IAction {

    public ActionForward action(HttpServletRequest request) throws SQLException {
        ActionForward actionForward = new ActionForward();
        BoardDAO boardDao = new BoardDAO();

        BoardDTO dto = new BoardDTO();
        if (request.getParameter("seq") != null) {
            dto = boardDao.selectOne(Integer.parseInt(request.getParameter("seq")));
        }

        actionForward.setRedirect(false);
        actionForward.setModel(dto);
        actionForward.setPath("/board/board_view.jsp");

        return actionForward;
    }

}
