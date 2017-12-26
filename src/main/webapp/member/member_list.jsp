<%@ page import="com.ej.example.domain.MemberDTO" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ include file="/WEB-INF/jsp/include/taglib.jsp" %>--%>
<html>
<head>
    <title>회원 목록</title>
    <script src="/webjars/jquery/1.11.1/jquery.min.js"></script>
    <link href="/webjars/bootstrap/3.3.7/css/bootstrap.css" rel="stylesheet">
    <script src="/webjars/bootstrap/3.3.7/js/bootstrap.js"></script>
    <%--<link href="/webjars/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">--%>
    <script>
        function read(seq) {
            document.read.seq.value = seq;
            document.read.submit();

        }

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
        <h2>회원 목록</h2>
        <div class="panel panel-default">
            <div class="panel-body">
                <a href="javascript: doAction(0, 'post_form')"><input type="button" value=" 글쓰기" class="btn btn-primary" style="float: right"/></a>
                <table class="table">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>아이디</th>
                        <th>이름</th>
                        <th>가입일</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        List<MemberDTO> dtoList = (List<MemberDTO>) request.getAttribute("model");
                        System.out.println(request.getContextPath());
                        if (dtoList.size() == 0) {
                    %>
                    <tr>
                        <td>no data</td>
                    </tr>
                    <%
                    } else {
                        for (MemberDTO dto : dtoList) {
                    %>
                    <tr>
                        <th><%=dto.getSeq()%>
                        </th>
                        <td><a href="javascript:doAction('<%=dto.getSeq()%>', 'read')"><%=dto.getUserId()%>
                        </a></td>
                        <td><%=dto.getName()%>
                        </td>
                        <td><%=dto.getCreateDate()%>
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

<form name="actionForm" method="get" action="./member">
    <input type="hidden" name="command" value="read">
    <input type="hidden" name="seq"/>
    <%--<input type="hidden" name="keyField" value="<%=keyField%>"/>--%>
    <%--<input type="hidden" name="keyWord" value="<%=keyWord%>"/>--%>
</form>
</body>
</html>