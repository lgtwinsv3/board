package com.ej.example.action.member;

import com.ej.example.action.ActionForward;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class CreateMemberFormAction {

    public ActionForward action(HttpServletRequest request) throws SQLException {
        ActionForward actionForward = new ActionForward();
        actionForward.setRedirect(true);
        actionForward.setPath("/member/member_post_form.jsp");
        return actionForward;
    }

}
