<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Danh Sách Nhân Viên</title>
</head>
<body>
    <h1>Danh Sách Nhân Viên</h1>

    <c:if test="${not empty sessionScope.user}">
        <a href="logout">Đăng Xuất</a> | <a href="index.jsp">Trang Chủ</a> | <a href="search.jsp">Tìm Kiếm Nhân Viên</a>
    </c:if>

    <form method="get">
        Lọc theo Phòng Ban:
        <select name="department">
            <option value="">Tất cả</option>
            <c:forEach items="${departmentList}" var="dept">
                <option value="${dept}" ${param.department eq dept ? 'selected' : ''}>${dept}</option>
            </c:forEach>
        </select>
        <input type="submit" value="Lọc">
    </form>

    <c:if test="${not empty employeeList}">
        <table border="1">
            <thead>
                <tr>
                    <th>Họ</th>
                    <th>Tên</th>
                    <th>Email</th>
                    <th>Số Điện Thoại</th>
                    <th>Ngày Tuyển Dụng</th>
                    <th>Chức Vụ</th>
                    <th>Phòng Ban</th>
                    <th>Hành Động</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${employeeList}" var="p">
                    <tr>
                        <td>${p.firstName}</td>
                        <td>${p.lastName}</td>
                        <td>${p.email}</td>
                        <td>${p.phoneNumber}</td>
                        <td>${p.hireDate}</td>
                        <td>${p.job.job_title}</td>
                        <td>${p.department.department_name}</td>
                        <td><a href="employees?showDependents=${p.employeeId}">Xem Người Phụ Thuộc</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <c:if test="${empty employeeList}">
        <p>Không tìm thấy nhân viên nào.</p>
    </c:if>

    <c:if test="${not empty dependents}">
        <h2>Người Phụ Thuộc</h2>
        <table border="1">
            <thead>
                <tr>
                    <th>Họ</th>
                    <th>Tên</th>
                    <th>Mối Quan Hệ</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${dependents}" var="dep">
                    <tr>
                        <td>${dep.firstName}</td>
                        <td>${dep.lastName}</td>
                        <td>${dep.relationship}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</body>
</html>