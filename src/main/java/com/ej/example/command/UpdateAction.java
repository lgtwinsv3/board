package com.ej.example.command;

import com.ej.example.dao.BoardDAO;
import com.ej.example.domain.BoardDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

public class UpdateAction implements IAction {

    public String processCommand(HttpServletRequest request, HttpServletResponse response) throws SQLException, UnsupportedEncodingException {
        BoardDAO boardDao = new BoardDAO();

        int seq = Integer.parseInt(request.getParameter("seq"));
        BoardDTO dto = boardDao.selectOne(seq);

        String writer = request.getParameter("writer");
        String subject = request.getParameter("subject");
        String password = request.getParameter("password");
        String content = request.getParameter("content");

        if (!password.equals(dto.getPassword())) {
            try {

                request.setAttribute("board", dto);
                return "/board/board_view.jsp";

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        dto.setWriter(writer.equals("") ? dto.getWriter() : writer);
        dto.setSubject(subject.equals("") ? dto.getSubject() : subject);
        dto.setPassword(password);
        dto.setContent(content.equals("") ? dto.getContent() : content);

        boardDao.update(dto);
        request.setCharacterEncoding("UTF-8");
        request.setAttribute("board", dto);

        return "/board/board_view.jsp";
    }

}
