package com.ej.example.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

public interface IAction {
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, UnsupportedEncodingException;




}
