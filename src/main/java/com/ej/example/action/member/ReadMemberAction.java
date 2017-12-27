package com.ej.example.action.member;

import com.ej.example.action.ActionForward;
import com.ej.example.action.IAction;
import com.ej.example.dao.member.MemberDAO;
import com.ej.example.domain.MemberDTO;
import com.ej.example.domain.Paging;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class ReadMemberAction implements IAction {

    public ActionForward action(HttpServletRequest request) throws SQLException {
        ActionForward actionForward = new ActionForward();
        MemberDAO dao = new MemberDAO();

        Paging<MemberDTO> paging = new Paging<MemberDTO>();
        if (!"".equalsIgnoreCase(request.getParameter("page"))) {
            paging.setPage(Integer.parseInt(request.getParameter("page")));
        }
        if (!"".equals(request.getParameter("size"))) {
            paging.setRowCount(Integer.parseInt(request.getParameter("size")));
        }

        MemberDTO dto = new MemberDTO();
        if (request.getParameter("seq") != null) {
            dto = dao.selectOne(Integer.parseInt(request.getParameter("seq")));
        }

        paging.setBody(dto);
        actionForward.setRedirect(false);
        actionForward.setModel(paging);
        actionForward.setPath("/member/member_view.jsp");

        return actionForward;
    }

}
