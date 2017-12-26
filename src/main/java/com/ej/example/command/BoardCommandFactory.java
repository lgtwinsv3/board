package com.ej.example.command;

import com.ej.example.action.ActionForward;
import com.ej.example.action.board.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class BoardCommandFactory implements ICommandFactory {

    private static final String LIST_COMMAND = "LIST";
    private static final String CREATE_COMMAND = "POST";
    private static final String CREATE_FORM_COMMAND = "POST_FORM";
    private static final String READ_COMMAND = "READ";
    private static final String UPDATE_COMMAND = "UPDATE";
    private static final String UPDATE_FORM_COMMAND = "UPDATE_FORM";
    private static final String DELETE_COMMAND = "DELETE";

    public ActionForward getForwardInstance(String command, HttpServletRequest request) throws SQLException {

        if (command == null || command.equals(LIST_COMMAND)) {
            return new ListBoardAction().action(request);

        } else if (command.equalsIgnoreCase(CREATE_COMMAND)) {
            return new CreateBoardAction().action(request);

        } else if (command.equalsIgnoreCase(CREATE_FORM_COMMAND)) {
            return new CreateBoardFormAction().action(request);

        } else if (command.equalsIgnoreCase(READ_COMMAND)) {
            return new ReadBoardAction().action(request);

        } else if (command.equalsIgnoreCase(UPDATE_COMMAND)) {
            return new UpdateBoardAction().action(request);

        } else if (command.equalsIgnoreCase(UPDATE_FORM_COMMAND)) {
            return new UpdateBoardFormAction().action(request);

        } else if (command.equalsIgnoreCase(DELETE_COMMAND)) {
            return new DeleteBoardAction().action(request);

        } else return new ListBoardAction().action(request);
    }
}
