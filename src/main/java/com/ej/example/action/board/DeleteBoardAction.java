package com.ej.example.action.board;

import com.ej.example.action.ActionForward;
import com.ej.example.action.IAction;
import com.ej.example.dao.board.BoardDAO;
import com.ej.example.domain.BoardDTO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class DeleteBoardAction implements IAction {

    public ActionForward action(HttpServletRequest request) throws SQLException {
        ActionForward actionForward = new ActionForward();
        BoardDAO boardDao = new BoardDAO();
        int seq = Integer.parseInt(request.getParameter("seq"));
        int result = boardDao.delete(seq);
        actionForward.setRedirect(false);

        if (result > 0) {
            List<BoardDTO> dtoList = boardDao.selectList(1, 10);

            actionForward.setModel(dtoList);
            actionForward.setPath("/board/board_list.jsp");
            return actionForward;
        }

        BoardDTO dto = boardDao.selectOne(seq);
        actionForward.setModel(dto);
        actionForward.setPath("/board/board_view.jsp");

        return actionForward;
    }

}
