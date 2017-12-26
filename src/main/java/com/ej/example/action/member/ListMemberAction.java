package com.ej.example.action.member;

import com.ej.example.action.ActionForward;
import com.ej.example.action.IAction;
import com.ej.example.dao.member.MemberDAO;
import com.ej.example.domain.MemberDTO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class ListMemberAction implements IAction {

    public ActionForward action(HttpServletRequest request) throws SQLException {
        MemberDAO memberDAO = new MemberDAO();
        List<MemberDTO> dtoList = memberDAO.selectList(1, 10);
        System.out.println(dtoList.size());

        ActionForward actionForward = new ActionForward();
        actionForward.setRedirect(false);
        actionForward.setPath("/member/member_list.jsp");
        actionForward.setModel(dtoList);
        return actionForward;
    }
}
