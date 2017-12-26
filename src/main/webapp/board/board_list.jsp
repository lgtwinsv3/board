<%@ page import="com.ej.example.domain.BoardDTO" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ include file="/WEB-INF/jsp/include/taglib.jsp" %>--%>
<html>
<head>
    <title>글 목록</title>
    <script src="/webjars/jquery/1.11.1/jquery.min.js"></script>
    <link href="/webjars/bootstrap/3.3.7/css/bootstrap.css" rel="stylesheet">
    <script src="/webjars/bootstrap/3.3.7/js/bootstrap.js"></script>
    <%--<link href="/webjars/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">--%>
    <script>
        function doAction(seq, command) {
            document.actionForm.seq.value = seq;
            document.actionForm.command.value = command;
            document.actionForm.submit();

        }

    </script>
</head>
<body>
<div class="wrapper">
    <div class="container" style="margin-top: 100px">
        <h2>게시물 목록</h2>
        <div class="panel panel-default">
            <div class="panel-body">
                <a href="javascript: doAction(0, 'post_form')"><input type="button" value=" 글쓰기" class="btn btn-primary" style="float: right"/></a>
                <table class="table">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>제목</th>
                        <th>작성자</th>
                        <th>게시일</th>
                        <th>조회수</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        List<BoardDTO> dtoList = (List<BoardDTO>) request.getAttribute("model");
                        System.out.println(request.getContextPath());
                        if (dtoList.size() == 0) {
                    %>
                    <tr>
                        <td>no data</td>
                        <td><%= (String) request.getAttribute("name")%>
                        </td>
                    </tr>
                    <%
                    } else {
                        for (BoardDTO dto : dtoList) {
                    %>
                    <tr>
                        <th><%=dto.getSeq()%>
                        </th>
                        <td><a href="javascript:doAction('<%=dto.getSeq()%>', 'read')"><%=dto.getSubject()%>
                        </a></td>
                        <td><%=dto.getWriter()%>
                        </td>
                        <td><%=dto.getCreateDate()%>
                        </td>
                        <td><%=dto.getReadCount()%>
                        </td>
                    </tr>
                    <%
                            }
                        }
                    %>

                    </tbody>
                </table>

            </div>
        </div>
    </div>
</div>

<form name="actionForm" method="get" action="./board">
    <input type="hidden" name="command" value="read">
    <input type="hidden" name="seq"/>
    <%--<input type="hidden" name="keyField" value="<%=keyField%>"/>--%>
    <%--<input type="hidden" name="keyWord" value="<%=keyWord%>"/>--%>
</form>
</body>
</html>