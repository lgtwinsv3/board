<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ page import="com.ej.example.domain.MemberDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html>
<head>
    <title>조회</title>
    <script src="/webjars/jquery/1.11.1/jquery.min.js"></script>
    <link href="/webjars/bootstrap/3.3.7/css/bootstrap.css" rel="stylesheet">
    <script src="/webjars/bootstrap/3.3.7/js/bootstrap.js"></script>
    <script>
        function goListPage() {
            document.viewFrm.submit();
        }

        function doAction(seq, command) {
            document.actionForm.seq.value = seq;
            document.actionForm.command.value = command;
            document.actionForm.submit();

        }
    </script>
</head>
<body>
<%
    MemberDTO dto = (MemberDTO) request.getAttribute("model");
%>
<div class="wrapper">
    <div class="container" style="margin-top: 100px">
        <h2>회원 정보 조회</h2>
        <div class="panel panel-default">
            <div class="panel-body">
                <form action="./member" method="get" id="viewFrm" name="viewFrm" class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="userId">사용자 ID</label>
                        <div class="col-sm-10">
                            <input type="text" id="userId" name="userId" value="<%=dto.getUserId()%>" class="form-control" placeholder="User ID" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="name">이름</label>
                        <div class="col-sm-10">
                            <input type="text" id="name" name="name" value="<%=dto.getName()%>" class="form-control" placeholder="이름" disabled>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-2" for="email">E-mail</label>
                        <div class="col-sm-10">
                            <input type="text" id="email" name="email"
                                   <%--value='<fmt:formatDate value="${user.createDate}" pattern="yyyy-MM-dd"/>' --%>value="<%= dto.getEmail()%>"
                                   class="form-control"
                                   placeholder="메일" disabled>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-2" for="createDate">가입일</label>
                        <div class="col-sm-10">
                            <input type="text" id="createDate" class="form-control" placeholder="생성일" disabled value='<%= dto.getCreateDate()%>'>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-2"></div>
                        <div class="col-sm-10">
                            <input type="button" value="목록" onclick="goListPage();" class="btn btn-default"/>
                            <input type="button" value="추가" onclick="doAction(0, 'post_form');" class="btn btn-default"/>
                            <input type="button" value="수정" onclick="doAction(<%=dto.getSeq()%>, 'update_form');" id="editBtn" class="btn btn-mini"/>
                            <input type="button" value="삭제" onclick="doAction(<%=dto.getSeq()%>, 'delete');" class="btn btn-mini btn-danger"/>
                        </div>
                    </div>
                    <%--<input type="hidden" name="page" value="${paging.page}">--%>
                    <%--<input type="hidden" name="size" value="${paging.rowCount}">--%>
                    <%-- <input type="hidden" name="searchCondition" value="${paging.searchCondition}">
                     <input type="hidden" name="searchKeyword" value="${paging.searchKeyword}">
                     <input type="hidden" name="searchCategory" value="${paging.searchCategory}">
                     <input type="hidden" name="searchEnabled" value="${paging.searchEnabled}">--%>
                </form>

            </div>
        </div>
    </div>
</div>
<form name="actionForm" method="get" action="./member">
    <input type="hidden" name="command">
    <input type="hidden" name="seq"/>
    <%--<input type="hidden" name="keyField" value="<%=keyField%>"/>--%>
    <%--<input type="hidden" name="keyWord" value="<%=keyWord%>"/>--%>
</form>


</body>
</html>
