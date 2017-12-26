package com.ej.example.action.board;

import com.ej.example.action.ActionForward;
import com.ej.example.action.IAction;
import com.ej.example.dao.board.BoardDAO;
import com.ej.example.domain.BoardDTO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class CreateBoardAction implements IAction {

    public ActionForward action(HttpServletRequest request) throws SQLException {
        BoardDAO boardDao = new BoardDAO();
        ActionForward actionForward = new ActionForward();

        BoardDTO dto = new BoardDTO();
        dto.setSubject(request.getParameter("subject"));
        dto.setWriter(request.getParameter("writer"));
        dto.setPassword(request.getParameter("password"));
        dto.setContent(request.getParameter("content"));

        int seq = boardDao.insert(dto);

        if (seq < 0) {
            throw new SQLException();
        }

        List<BoardDTO> dtoList = boardDao.selectList(1, 10);

        actionForward.setRedirect(false);
        actionForward.setModel(dtoList);
        actionForward.setPath("/board/board_list.jsp");

        return actionForward;
    }

}
