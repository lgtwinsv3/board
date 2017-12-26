package com.ej.example.servlet;

import com.ej.example.action.ActionForward;
import com.ej.example.command.BoardCommandFactory;
import com.ej.example.command.ICommandFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class BoardServlet implements IServlet {

//    HttpServletRequest request;
//    HttpServletResponse response;

    public BoardServlet() {
    }

//    BoardServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        this.request = request;
//        this.response = response;
//    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        System.out.println("[POST]");
        doProc(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        doProc(request, response);

    }

    public void doProc(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        try {
            System.out.println("[GET]");

            String uri = request.getRequestURI();
            String url = request.getRequestURL().toString();
            String contextPath = request.getServletPath();
            String command = request.getParameter("command");


            System.out.println("uri :  " + uri);


            System.out.println("url :  " + url);
            System.out.println("queryString :  " + request.getQueryString());
            System.out.println("contextPath : " + contextPath);
            System.out.println("command : " + command);
            System.out.println("===========================ss=======");

            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=utf-8");

//            IAction action = new BoardAction();
//            action.execute(command, request, response);

            ICommandFactory commandFactory = new BoardCommandFactory();
            ActionForward forward = commandFactory.getForwardInstance(command, request);
            if (forward.isRedirect()) {
                response.sendRedirect(forward.getPath());
            } else {
                RequestDispatcher view = request.getRequestDispatcher(forward.getPath());
                request.setAttribute("model", forward.getModel());
                view.forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
