package com.ej.example.action.member;

import com.ej.example.action.ActionForward;
import com.ej.example.action.IAction;
import com.ej.example.dao.DAOAdaptor;
import com.ej.example.dao.member.MemberDAO;
import com.ej.example.domain.MemberDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

public class ListMemberAction extends DAOAdaptor implements IAction {

    public String processCommand(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        MemberDAO memberDAO = new MemberDAO();
        List<MemberDTO> dtoList = memberDAO.selectList(1, 10);
        System.out.println(dtoList.size());
        request.setAttribute("list", dtoList);
        request.setAttribute("name", "eunjeong");

        return "/member/member_list.jsp";
    }

    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, UnsupportedEncodingException {

        ActionForward forward = new ActionForward();
        return null;
    }
}
