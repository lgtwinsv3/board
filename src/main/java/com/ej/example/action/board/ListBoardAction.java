package com.ej.example.action.board;

import com.ej.example.action.ActionForward;
import com.ej.example.dao.board.BoardDAO;
import com.ej.example.domain.BoardDTO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class ListBoardAction {

    public ActionForward action(HttpServletRequest request) throws SQLException {
        BoardDAO boardDao = new BoardDAO();
        List<BoardDTO> dtoList = boardDao.selectList(1, 10);
        System.out.println(dtoList.size());

        ActionForward actionForward = new ActionForward();
        actionForward.setRedirect(false);
        actionForward.setPath("/board/board_list.jsp");
        actionForward.setModel(dtoList);
        return actionForward;
    }
}
