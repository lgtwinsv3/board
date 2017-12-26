package com.ej.example.action.member;

import com.ej.example.action.ActionForward;
import com.ej.example.action.IAction;
import com.ej.example.dao.member.MemberDAO;
import com.ej.example.domain.MemberDTO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class UpdateMemberAction implements IAction {

    public ActionForward action(HttpServletRequest request) throws SQLException {
        ActionForward actionForward = new ActionForward();
        MemberDAO dao = new MemberDAO();

        int seq = Integer.parseInt(request.getParameter("seq"));
        MemberDTO dto = dao.selectOne(seq);

        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        dto.setPassword(password);
        dto.setName("".equals(name) ? dto.getName() : name);
        dto.setEmail("".equals(email) ? dto.getEmail() : email);

        dao.update(dto);

        actionForward.setRedirect(false);
        actionForward.setModel(dto);
        actionForward.setPath("/member/member_view.jsp");

        return actionForward;
    }

}
