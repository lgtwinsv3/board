package com.ej.example.action.board;

import com.ej.example.action.ActionForward;
import com.ej.example.action.IAction;
import com.ej.example.dao.board.BoardDAO;
import com.ej.example.domain.BoardDTO;
import com.ej.example.domain.Paging;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class CreateBoardAction implements IAction {

    public ActionForward action(HttpServletRequest request) throws SQLException {
        BoardDAO boardDao = new BoardDAO();
        ActionForward actionForward = new ActionForward();

        Paging<BoardDTO> paging = new Paging<BoardDTO>();
        if (!"".equalsIgnoreCase(request.getParameter("page"))) {
            paging.setPage(Integer.parseInt(request.getParameter("page")));
        }
        if (!"".equals(request.getParameter("size"))) {
            paging.setRowCount(Integer.parseInt(request.getParameter("size")));
        }
        paging.calcPaging(boardDao.selectCount());

        BoardDTO dto = new BoardDTO();
        dto.setSubject(request.getParameter("subject"));
        dto.setWriter(request.getParameter("writer"));
        dto.setPassword(request.getParameter("password"));
        dto.setContent(request.getParameter("content"));

        int seq = boardDao.insert(dto);

        if (seq < 0) {
            throw new SQLException();
        }

        List<BoardDTO> dtoList = boardDao.selectList(paging.getSkipRowCount(), paging.getRowCount());
        paging.setBody(dtoList);
        actionForward.setRedirect(false);
        actionForward.setModel(paging);
        actionForward.setPath("/board/board_list.jsp");

        return actionForward;
    }

}
