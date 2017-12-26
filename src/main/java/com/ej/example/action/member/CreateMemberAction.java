package com.ej.example.action.member;

import com.ej.example.action.ActionForward;
import com.ej.example.dao.member.MemberDAO;
import com.ej.example.domain.MemberDTO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class CreateMemberAction {

    public ActionForward action(HttpServletRequest request) throws SQLException {
        MemberDAO dao = new MemberDAO();
        ActionForward actionForward = new ActionForward();

        MemberDTO dto = new MemberDTO();
        dto.setEmail(request.getParameter("email"));
        dto.setName(request.getParameter("name"));
        dto.setPassword(request.getParameter("password"));
        dto.setUserId(request.getParameter("userId"));

        int seq = dao.insert(dto);

        if (seq < 0) {
            throw new SQLException();
        }

        List<MemberDTO> dtoList = dao.selectList(1, 10);

        actionForward.setRedirect(false);
        actionForward.setModel(dtoList);
        actionForward.setPath("/member/member_list.jsp");

        return actionForward;
    }

}
