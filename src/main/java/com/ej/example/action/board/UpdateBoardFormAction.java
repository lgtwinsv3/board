package com.ej.example.action.board;

import com.ej.example.action.ActionForward;
import com.ej.example.dao.board.BoardDAO;
import com.ej.example.domain.BoardDTO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class UpdateBoardFormAction {

    public ActionForward action(HttpServletRequest request) throws SQLException {
        ActionForward actionForward = new ActionForward();
        BoardDAO boardDao = new BoardDAO();

        BoardDTO dto = new BoardDTO();
        System.out.println("seq : " + request.getParameter("seq"));
        if (request.getParameter("seq") != null) {
            dto = boardDao.selectOne(Integer.parseInt(request.getParameter("seq")));
            System.out.println("subject : " + dto.getSubject());
        }
        actionForward.setRedirect(false);
        actionForward.setModel(dto);
        actionForward.setPath("/board/board_update_form.jsp");

        return actionForward;
    }

}
