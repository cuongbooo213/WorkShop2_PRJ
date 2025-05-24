<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Tìm Kiếm Nhân Viên</title>
</head>
<body>
    <h1>Tìm Kiếm Nhân Viên</h1>

    <c:if test="${not empty sessionScope.user}">
        <a href="logout">Đăng Xuất</a> | <a href="index.jsp">Trang Chủ</a>
    </c:if>

    <form action="search" method="get">
        Tìm kiếm theo tên: <input type="text" name="keyword" value="${param.keyword}">
        <input type="submit" value="Tìm Kiếm">
    </form>

    <c:if test="${not empty employeeList}">
        <table border="1">
            <thead>
                <tr>
                    <th>Họ</th>
                    <th>Tên</th>
                    <th>Email</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${employeeList}" var="emp">
                    <tr>
                        <td>${emp.firstName}</td>
                        <td>${emp.lastName}</td>
                        <td>${emp.email}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</body>
</html>