package com.ej.example.servlet;

import com.ej.example.action.ActionForward;
import com.ej.example.command.ICommandFactory;
import com.ej.example.command.MemberCommandFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberServlet {

    HttpServletRequest request;
    HttpServletResponse response;

    public MemberServlet() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalAccessException, InstantiationException {

        System.out.println("[POST]");
        doGet(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InstantiationException, IllegalAccessException {
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
            System.out.println("==========================11========");

            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=utf-8");

            ICommandFactory commandFactory = new MemberCommandFactory();
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
