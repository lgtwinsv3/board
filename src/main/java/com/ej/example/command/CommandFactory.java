package com.ej.example.command;

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

    public IAction doAction(String command) {
        if (command != null) {

            if (command.equalsIgnoreCase(CREATE_COMMAND)) {
                return new CreateAction();

            } else if (command.equalsIgnoreCase(READ_COMMAND)) {
                return new ReadAction();

            } else if (command.equalsIgnoreCase(UPDATE_COMMAND)) {
                return new UpdateAction();

            } else if (command.equalsIgnoreCase(UPDATE_FORM_COMMAND)) {
                return new UpdateFormAction();

            } else if (command.equalsIgnoreCase(DELETE_COMMAND)) {
                return new DeleteAction();
            }
            return new ListAction();
        }


        return new ListAction();

    }
}
