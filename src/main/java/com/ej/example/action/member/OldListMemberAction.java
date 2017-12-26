package com.ej.example.action.member;

import com.ej.example.action.ActionForward;
import com.ej.example.dao.member.MemberDAO;
import com.ej.example.domain.MemberDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

public class OldListMemberAction {

    @Deprecated
    public String processCommand(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        MemberDAO memberDAO = new MemberDAO();
        List<MemberDTO> dtoList = memberDAO.selectList(1, 10);
        System.out.println(dtoList.size());
        request.setAttribute("list", dtoList);
        return "/member/member_list.jsp";
    }

    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, UnsupportedEncodingException {

        MemberDAO memberDAO = new MemberDAO();
        List<MemberDTO> dtoList = memberDAO.selectList(1, 10);
        System.out.println(dtoList.size());
        request.setAttribute("list", dtoList);

        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath("/member/member_list.jsp");
        return forward;
    }
}
