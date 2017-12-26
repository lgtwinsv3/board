package com.ej.example.action.board;

import com.ej.example.dao.board.OldBoardDAO;
import com.ej.example.domain.BoardDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class OldListBoardAction {

    public String processCommand(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        OldBoardDAO boardDao = new OldBoardDAO();
        List<BoardDTO> dtoList = boardDao.selectList(1, 10);
        System.out.println(dtoList.size());
        request.setAttribute("list", dtoList);
        request.setAttribute("name", "eunjeong");

        return "/board/board_list.jsp";
    }
}
