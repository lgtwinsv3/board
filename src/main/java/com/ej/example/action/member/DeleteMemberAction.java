package com.ej.example.action.member;

import com.ej.example.action.ActionForward;
import com.ej.example.action.IAction;
import com.ej.example.dao.member.MemberDAO;
import com.ej.example.domain.MemberDTO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class DeleteMemberAction implements IAction {

    public ActionForward action(HttpServletRequest request) throws SQLException {
        ActionForward actionForward = new ActionForward();
        MemberDAO dao = new MemberDAO();
        int seq = Integer.parseInt(request.getParameter("seq"));
        int result = dao.delete(seq);
        actionForward.setRedirect(false);

        if (result > 0) {
            List<MemberDTO> dtoList = dao.selectList(1, 10);

            actionForward.setModel(dtoList);
            actionForward.setPath("/member/member_list.jsp");
            return actionForward;
        }

        MemberDTO dto = dao.selectOne(seq);
        actionForward.setModel(dto);
        actionForward.setPath("/member/member_view.jsp");

        return actionForward;
    }

}
