package com.ej.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

            System.out.println("uri :  " + uri);
            System.out.println("url :  " + url);
            System.out.println("queryString :  " + request.getQueryString());
            System.out.println("contextPath : " + contextPath);
            System.out.println("==================================");

            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=utf-8");

            IServlet servlet;
            if (uri.endsWith("board")) {
                servlet = (IServlet) Class.forName("com.ej.example.servlet.BoardServlet").newInstance();


            } else if (uri.endsWith("member")) {
                servlet = (IServlet) Class.forName("com.ej.example.servlet.MemberServlet").newInstance();

            } else {
                servlet = new IServlet() {
                    public void doProc(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
                        response.sendRedirect("/index.jsp");

                    }
                };
            }

            servlet.doProc(request, response);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
