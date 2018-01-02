package com.ej.example.command;

import com.ej.example.action.ActionForward;
import com.ej.example.action.IAction;
import com.ej.example.action.board.ListBoardAction;
import com.ej.example.util.JavaUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class MemberCommandFactory implements ICommandFactory {

    public MemberCommandFactory() {
    }

    public ActionForward getForwardInstance(String command, HttpServletRequest request) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException, UnsupportedEncodingException {

        if (command != null) {
            IAction actionCls;
            Properties properties = JavaUtil.readProperties("member.action");
            Set<Map.Entry<Object, Object>> entries = properties.entrySet();

            for (Map.Entry<Object, Object> entry : entries) {
                if (command.equalsIgnoreCase(entry.getKey().toString())) {

                    System.out.println(entry.getKey() + " : " + entry.getValue());
                    actionCls = (IAction) Class.forName(entry.getValue().toString()).newInstance();
                    return actionCls.action(request);
                }
            }

        }
        return new ListBoardAction().action(request);


    }

//    private static final String LIST_COMMAND = "LIST";
//    private static final String CREATE_COMMAND = "POST";
//    private static final String CREATE_FORM_COMMAND = "POST_FORM";
//    private static final String READ_COMMAND = "READ";
//    private static final String UPDATE_COMMAND = "UPDATE";
//    private static final String UPDATE_FORM_COMMAND = "UPDATE_FORM";
//    private static final String DELETE_COMMAND = "DELETE";
//
//    public ActionForward getForwardInstance(String command, HttpServletRequest request) throws SQLException {
//
//        if (command == null || command.equals(LIST_COMMAND)) {
//            return new ListMemberAction().action(request);
//
//        } else if (command.equalsIgnoreCase(CREATE_COMMAND)) {
//            return new CreateMemberAction().action(request);
//
//        } else if (command.equalsIgnoreCase(CREATE_FORM_COMMAND)) {
//            return new CreateMemberFormAction().action(request);
//
//        } else if (command.equalsIgnoreCase(READ_COMMAND)) {
//            return new ReadMemberAction().action(request);
//
//        } else if (command.equalsIgnoreCase(UPDATE_COMMAND)) {
//            return new UpdateMemberAction().action(request);
//
//        } else if (command.equalsIgnoreCase(UPDATE_FORM_COMMAND)) {
//            return new UpdateMemberFormAction().action(request);
//
//        } else if (command.equalsIgnoreCase(DELETE_COMMAND)) {
//            return new DeleteMemberAction().action(request);
//
//        } else return new ListMemberAction().action(request);
//    }


}
