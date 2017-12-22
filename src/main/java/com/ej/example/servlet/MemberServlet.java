package com.ej.example.servlet;

import com.ej.example.action.OldIAction;
import com.ej.example.command.CommandFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

public class MemberServlet implements OldIAction {

    HttpServletRequest request;
    HttpServletResponse response;

    public MemberServlet() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("[POST]");
        doGet(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            System.out.println("[GET]");

            String uri = request.getRequestURI();
            String url = request.getRequestURL().toString();
            String contextPath = request.getServletPath();
            String command = request.getParameter("command");

            System.out.println("uri :  " + uri);
            System.out.println("url :  " + url);
            System.out.println("contextPath : " + contextPath);
            System.out.println("command : " + command);
            System.out.println("==================================");

            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=utf-8");

            CommandFactory factory = CommandFactory.getInstance();
            OldIAction action = factory.doAction(command);
            String nextPage = action.processCommand(request, response);
            RequestDispatcher view = request.getRequestDispatcher(nextPage);
            view.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String processCommand(HttpServletRequest request, HttpServletResponse response) throws SQLException, UnsupportedEncodingException {
        return null;
    }
}
