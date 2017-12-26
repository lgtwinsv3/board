package com.ej.example.action.member;

import com.ej.example.action.ActionForward;
import com.ej.example.action.IAction;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class CreateMemberFormAction implements IAction {

    public ActionForward action(HttpServletRequest request) throws SQLException {
        ActionForward actionForward = new ActionForward();
        actionForward.setRedirect(true);
        actionForward.setPath("/member/member_post_form.jsp");
        return actionForward;
    }

}
