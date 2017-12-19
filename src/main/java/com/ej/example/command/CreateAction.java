package com.ej.example.command;

import com.ej.example.dao.BoardDAO;
import com.ej.example.domain.BoardDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class CreateAction implements IAction {

    public String processCommand(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        BoardDAO boardDao = new BoardDAO();

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
        request.setAttribute("list", dtoList);

        return "/board/board_list.jsp";
    }

}
