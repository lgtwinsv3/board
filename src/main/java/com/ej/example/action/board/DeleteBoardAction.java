package com.ej.example.action.board;

import com.ej.example.action.OldIAction;
import com.ej.example.dao.board.OldBoardDAO;
import com.ej.example.domain.BoardDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class DeleteBoardAction implements OldIAction {

    public String processCommand(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        OldBoardDAO boardDao = new OldBoardDAO();
        int seq = Integer.parseInt(request.getParameter("seq"));
        int result = boardDao.delete(seq);

        if (result > 0) {
            List<BoardDTO> dtoList = boardDao.selectList(1, 10);
            request.setAttribute("list", dtoList);
            System.out.println("result[deleteAction] : " + result);
            return "/board/board_list.jsp";
        }

        BoardDTO dto = boardDao.selectOne(seq);
        request.setAttribute("board", dto);
        return "/board/board_view.jsp";
    }

}
