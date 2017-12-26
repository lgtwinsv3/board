package com.ej.example.action.board;

import com.ej.example.action.ActionForward;
import com.ej.example.action.IAction;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class CreateBoardFormAction implements IAction {

    public ActionForward action(HttpServletRequest request) throws SQLException {
        ActionForward actionForward = new ActionForward();
        actionForward.setRedirect(true);
        actionForward.setPath("/board/board_post_form.jsp");
        return actionForward;
    }

}
