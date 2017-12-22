package com.ej.example.command;

import com.ej.example.action.OldIAction;
import com.ej.example.action.board.*;

public class CommandFactory {

    public CommandFactory() {
    }

    private static final String LIST_COMMAND = "LIST";
    private static final String CREATE_COMMAND = "POST";
    private static final String READ_COMMAND = "READ";
    private static final String UPDATE_COMMAND = "UPDATE";
    private static final String UPDATE_FORM_COMMAND = "UPDATE_FORM";
    private static final String DELETE_COMMAND = "DELETE";

    private static CommandFactory instance = new CommandFactory();

    public static CommandFactory getInstance() {
        return instance;
    }

    public OldIAction doAction(String command) {
        if (command != null) {

            if (command.equalsIgnoreCase(CREATE_COMMAND)) {
                return new CreateBoardAction();

            } else if (command.equalsIgnoreCase(READ_COMMAND)) {
                return new ReadBoardAction();

            } else if (command.equalsIgnoreCase(UPDATE_COMMAND)) {
                return new UpdateBoardAction();

            } else if (command.equalsIgnoreCase(UPDATE_FORM_COMMAND)) {
                return new UpdateBoardFormAction();

            } else if (command.equalsIgnoreCase(DELETE_COMMAND)) {
                return new DeleteBoardAction();
            }
            return new ListBoardAction();
        }


        return new ListBoardAction();

    }
}
