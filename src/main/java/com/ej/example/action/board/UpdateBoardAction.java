package com.ej.example.action.board;

import com.ej.example.action.ActionForward;
import com.ej.example.action.IAction;
import com.ej.example.dao.board.BoardDAO;
import com.ej.example.domain.BoardDTO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class UpdateBoardAction implements IAction {

    public ActionForward action(HttpServletRequest request) throws SQLException {
        ActionForward actionForward = new ActionForward();
        BoardDAO boardDao = new BoardDAO();

        int seq = Integer.parseInt(request.getParameter("seq"));
        BoardDTO dto = boardDao.selectOne(seq);

        String writer = request.getParameter("writer");
        String subject = request.getParameter("subject");
        String password = request.getParameter("password");
        String content = request.getParameter("content");

        if (!password.equals(dto.getPassword())) {
            actionForward.setRedirect(false);
            actionForward.setModel(dto);
            actionForward.setPath("/board/board_view.jsp");
            return actionForward;
        }

        dto.setWriter("".equals(writer) ? dto.getWriter() : writer);
        dto.setSubject("".equals(subject) ? dto.getSubject() : subject);
        dto.setPassword(password);
        dto.setContent("".equals(content) ? dto.getContent() : content);

        boardDao.update(dto);

        actionForward.setRedirect(false);
        actionForward.setModel(dto);
        actionForward.setPath("/board/board_view.jsp");

        return actionForward;
    }

}
