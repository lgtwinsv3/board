package com.ej.example.servlet;

import com.ej.example.command.CommandFactory;
import com.ej.example.command.IAction;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class BoardServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("[POST]");
        doGet(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            System.out.println("[GET]");

            String uri = request.getRequestURI();
            String contextPath = request.getServletPath();
            String command = request.getParameter("command");

            System.out.println("uri :  " + uri);
            System.out.println("contextPath : " + contextPath);
            System.out.println("command : " + command);
            System.out.println("==================================");

            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=utf-8");

            CommandFactory factory = CommandFactory.getInstance();
            IAction action = factory.doAction(command);
            String nextPage = action.processCommand(request, response);
            RequestDispatcher view = getServletContext().getRequestDispatcher(nextPage);
            view.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
