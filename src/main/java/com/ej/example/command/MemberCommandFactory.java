package com.ej.example.command;

import com.ej.example.action.ActionForward;
import com.ej.example.action.member.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class MemberCommandFactory implements ICommandFactory {

    public MemberCommandFactory() {
    }

    private static final String LIST_COMMAND = "LIST";
    private static final String CREATE_COMMAND = "POST";
    private static final String CREATE_FORM_COMMAND = "POST_FORM";
    private static final String READ_COMMAND = "READ";
    private static final String UPDATE_COMMAND = "UPDATE";
    private static final String UPDATE_FORM_COMMAND = "UPDATE_FORM";
    private static final String DELETE_COMMAND = "DELETE";

    public ActionForward getForwardInstance(String command, HttpServletRequest request) throws SQLException {

        if (command == null || command.equals(LIST_COMMAND)) {
            return new ListMemberAction().action(request);

        } else if (command.equalsIgnoreCase(CREATE_COMMAND)) {
            return new CreateMemberAction().action(request);

        } else if (command.equalsIgnoreCase(CREATE_FORM_COMMAND)) {
            return new CreateMemberFormAction().action(request);

        } else if (command.equalsIgnoreCase(READ_COMMAND)) {
            return new ReadMemberAction().action(request);

        } else if (command.equalsIgnoreCase(UPDATE_COMMAND)) {
            return new UpdateMemberAction().action(request);

        } else if (command.equalsIgnoreCase(UPDATE_FORM_COMMAND)) {
            return new UpdateMemberFormAction().action(request);

        } else if (command.equalsIgnoreCase(DELETE_COMMAND)) {
            return new DeleteMemberAction().action(request);

        } else return new ListMemberAction().action(request);
    }
}
