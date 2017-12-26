package com.ej.example.command;

import com.ej.example.action.ActionForward;
import com.ej.example.action.board.ListBoardAction;
import com.ej.example.dao.board.OldBoardDAO;
import com.ej.example.domain.BoardDTO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class BoardCommandFactory implements ICommandFactory {

    private static final String LIST_COMMAND = "LIST";
    private static final String CREATE_COMMAND = "POST";
    private static final String CREATE_FORM_COMMAND = "POST_FORM";
    private static final String READ_COMMAND = "READ";
    private static final String UPDATE_COMMAND = "UPDATE";
    private static final String UPDATE_FORM_COMMAND = "UPDATE_FORM";
    private static final String DELETE_COMMAND = "DELETE";

    public ActionForward getForwardInstance(String command, HttpServletRequest request) throws SQLException {
        ActionForward actionForward = new ActionForward();
        OldBoardDAO boardDao = new OldBoardDAO();

        if (command == null || command.equals(LIST_COMMAND)) {
//            List<BoardDTO> dtoList = boardDao.selectList(1, 10);
//            actionForward.setRedirect(false);
//            actionForward.setPath("/board/board_list.jsp");
//            actionForward.setModel(dtoList);

            return new ListBoardAction().getForwardInstance(request);

        }
        if (command.equalsIgnoreCase(CREATE_COMMAND)) {

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

        } else if (command.equalsIgnoreCase(CREATE_FORM_COMMAND)) {
            actionForward.setRedirect(true);
            actionForward.setPath("/board/board_post_form.jsp");

        } else if (command.equalsIgnoreCase(READ_COMMAND)) {

            BoardDTO dto = new BoardDTO();
            if (request.getParameter("seq") != null) {
                dto = boardDao.selectOne(Integer.parseInt(request.getParameter("seq")));
            }

            actionForward.setRedirect(false);
            actionForward.setModel(dto);
            actionForward.setPath("/board/board_view.jsp");


        } else if (command.equalsIgnoreCase(UPDATE_COMMAND)) {
            int seq = Integer.parseInt(request.getParameter("seq"));
            BoardDTO dto = boardDao.selectOne(seq);

            String writer = request.getParameter("writer");
            String subject = request.getParameter("subject");
            String password = request.getParameter("password");
            String content = request.getParameter("content");

            if (!password.equals(dto.getPassword())) {

                request.setAttribute("board", dto);
                actionForward.setRedirect(false);
                actionForward.setModel(dto);
                actionForward.setPath("/board/board_view.jsp");
                return actionForward;
            }

            dto.setWriter("".equals(writer) ? dto.getWriter() : writer);
            dto.setSubject("".equals(subject) ? dto.getSubject() : subject);
            dto.setPassword(password);
            dto.setContent("".equals(content) ? dto.getContent() : content);

            boardDao.update(dto);

            actionForward.setRedirect(false);
            actionForward.setModel(dto);
            actionForward.setPath("/board/board_view.jsp");


        } else if (command.equalsIgnoreCase(UPDATE_FORM_COMMAND)) {
            BoardDTO dto = new BoardDTO();
            System.out.println("seq : " + request.getParameter("seq"));
            if (request.getParameter("seq") != null) {
                dto = boardDao.selectOne(Integer.parseInt(request.getParameter("seq")));
                System.out.println("subject : " + dto.getSubject());
            }
            actionForward.setRedirect(false);
            actionForward.setModel(dto);
            actionForward.setPath("/board/board_update_form.jsp");

        } else if (command.equalsIgnoreCase(DELETE_COMMAND)) {
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
        }
        return actionForward;

    }
}
