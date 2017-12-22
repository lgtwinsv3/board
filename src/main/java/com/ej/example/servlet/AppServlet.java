package com.ej.example.servlet;

import com.ej.example.action.OldIAction;
import com.ej.example.command.CommandFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

public class AppServlet extends HttpServlet {
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
            System.out.println("queryString :  " + request.getQueryString());
            System.out.println("contextPath : " + contextPath);
            System.out.println("command : " + command);
            System.out.println("==================================");


// 리플렉션, Class, Field, Method


            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=utf-8");

            CommandFactory factory = CommandFactory.getInstance();
            OldIAction action = factory.doAction(command);
            String nextPage = action.processCommand(request, response);

            OldIAction servlet;
            if (uri.endsWith("board")) {
                // board doGet
//                 new com.ej.example.servlet.BoardServlet();
                servlet = (OldIAction) Class.forName("com.ej.example.servlet.BoardServlet").newInstance();
//                RequestDispatcher view = getServletContext().getRequestDispatcher(servlet.processCommand(request, response));
//                view.forward(request, response);

            } else if (uri.endsWith("member")) {
                servlet = (OldIAction) Class.forName("com.ej.example.servlet.MemberServlet").newInstance();
//                RequestDispatcher view = getServletContext().getRequestDispatcher(servlet.processCommand(request, response));
//                view.forward(request, response);

            } else {
                servlet = new OldIAction() {
                    public String processCommand(HttpServletRequest request, HttpServletResponse response) throws SQLException, UnsupportedEncodingException {
                        return null;
                    }
                };
            }
            servlet.processCommand(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
