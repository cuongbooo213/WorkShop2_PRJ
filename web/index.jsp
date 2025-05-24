<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dal.DepartmentsDao, java.util.List, model.Departments" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Danh Sách Phòng Ban</title>
</head>
<body>
    <h1>Danh Sách Phòng Ban</h1>

    <% 
        if (session.getAttribute("departmentList") == null) {
            try {
                DepartmentsDao dao = DepartmentsDao.getInstance();
                List<Departments> departmentList = dao.getAllDepartments();
                session.setAttribute("departmentList", departmentList);
            } catch (Exception e) {
                out.println("<p style='color:red'>Lỗi khi tải danh sách phòng ban: " + e.getMessage() + "</p>");
            }
        }
    %>

    <c:if test="${not empty sessionScope.user}">
        <a href="logout">Đăng Xuất</a>
    </c:if>

    <c:if test="${not empty sessionScope.departmentList}">
        <table border="1">
            <thead>
                <tr>
                    <!--<th>ID Phòng Ban</th>-->
                    <th>Tên Phòng Ban</th>
                    <th>Địa Chỉ</th>
                    <!--<th>Thành Phố</th>-->
                    <!--<th>Mã Bưu Điện</th>-->
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${sessionScope.departmentList}" var="p">
                    <tr>
                        <!--<td>${p.department_id}</td>-->
                        <td>${p.department_name}</td>
                        <td>${p.location_id.street_address}</td>
                        <!--<td>${p.location_id.city}</td>-->
                        <!--<td>${p.location_id.postal_code}</td>-->
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <c:if test="${empty sessionScope.departmentList}">
        <p>Không tìm thấy phòng ban.</p>
    </c:if>

    <a href="login.jsp">Đăng Nhập với tư cách Admin</a>
</body>
</html>