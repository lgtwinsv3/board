package com.ej.example.command;

import com.ej.example.dao.BoardDAO;
import com.ej.example.domain.BoardDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class DeleteAction implements IAction {

    public String processCommand(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        BoardDAO boardDao = new BoardDAO();
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
