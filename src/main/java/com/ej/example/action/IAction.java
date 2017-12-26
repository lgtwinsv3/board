package com.ej.example.action;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

public interface IAction {

    ActionForward action(HttpServletRequest request) throws SQLException, UnsupportedEncodingException;
}
