package com.ej.example.command;

import com.ej.example.dao.BoardDAO;
import com.ej.example.domain.BoardDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class ListAction implements IAction {

    public String processCommand(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        BoardDAO boardDao = new BoardDAO();
        List<BoardDTO> dtoList = boardDao.selectList(1, 10);
        System.out.println(dtoList.size());
        request.setAttribute("list", dtoList);
        request.setAttribute("name", "eunjeong");

        return "/board/board_list.jsp";
    }
}
