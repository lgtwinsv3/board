package com.ej.example.command;

import com.ej.example.action.ActionForward;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

public interface ICommandFactory {
    ActionForward getForwardInstance(String command, HttpServletRequest request) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException, UnsupportedEncodingException;
}
