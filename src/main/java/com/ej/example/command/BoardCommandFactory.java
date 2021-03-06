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

public class BoardCommandFactory implements ICommandFactory {

    public ActionForward getForwardInstance(String command, HttpServletRequest request) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException, UnsupportedEncodingException {

        if (command != null) {
            IAction actionCls;
            Properties properties = JavaUtil.readProperties("board.action");
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
}
