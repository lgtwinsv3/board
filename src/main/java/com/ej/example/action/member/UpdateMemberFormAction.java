package com.ej.example.action.member;

import com.ej.example.action.ActionForward;
import com.ej.example.action.IAction;
import com.ej.example.dao.member.MemberDAO;
import com.ej.example.domain.MemberDTO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class UpdateMemberFormAction implements IAction {

    public ActionForward action(HttpServletRequest request) throws SQLException {
        ActionForward actionForward = new ActionForward();
        MemberDAO dao = new MemberDAO();

        MemberDTO dto = new MemberDTO();
        if (request.getParameter("seq") != null) {
            dto = dao.selectOne(Integer.parseInt(request.getParameter("seq")));
        }
        actionForward.setRedirect(false);
        actionForward.setModel(dto);
        actionForward.setPath("/member/member_update_form.jsp");

        return actionForward;
    }

}
