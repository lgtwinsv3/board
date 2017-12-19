package com.ej.example.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

public interface IAction {
    public String processCommand(HttpServletRequest request, HttpServletResponse response) throws SQLException, UnsupportedEncodingException;
}
