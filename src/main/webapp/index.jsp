<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--<%@ include file="/WEB-INF/jsp/include/taglib.jsp" %>--%>
<html>
<head>
    <title>Title</title>
    <script src="/webjars/jquery/1.11.1/jquery.min.js"></script>
    <link href="/webjars/bootstrap/3.3.7/css/bootstrap.css" rel="stylesheet">
    <script src="/webjars/bootstrap/3.3.7/js/bootstrap.js"></script>
    <%--<link href="/webjars/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">--%>
</head>
<body>
<div class="wrapper">
    <div class="container">
        <div class="panel panel-default">
            <div class="panel-body">
                <table class="table">
                    <caption>목록</caption>
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Username</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <th scope="row">1</th>
                        <td>Mark</td>
                        <td>Otto</td>
                        <td>@mdo</td>
                    </tr>
                    <tr>
                        <th scope="row">2</th>
                        <td>Jacob</td>
                        <td>Thornton</td>
                        <td>@fat</td>
                    </tr>
                    <tr>
                        <th scope="row">3</th>
                        <td>Larry</td>
                        <td>the Bird</td>
                        <td>@twitter</td>
                    </tr>
                    </tbody>
                </table>

            </div>
        </div>
    </div>
</div>
</body>
</html>
