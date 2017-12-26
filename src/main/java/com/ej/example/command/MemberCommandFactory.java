package com.ej.example.command;

import com.ej.example.action.ActionForward;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class MemberCommandFactory implements ICommandFactory {

    public MemberCommandFactory() {
    }

    private static final String LIST_COMMAND = "LIST";
    private static final String CREATE_COMMAND = "POST";
    private static final String READ_COMMAND = "READ";
    private static final String UPDATE_COMMAND = "UPDATE";
    private static final String UPDATE_FORM_COMMAND = "UPDATE_FORM";
    private static final String DELETE_COMMAND = "DELETE";

    private static MemberCommandFactory instance = new MemberCommandFactory();

    public static MemberCommandFactory getInstance() {
        return instance;
    }

    public ActionForward getForwardInstance(String command, HttpServletRequest request) throws SQLException {
        return null;
    }
}
