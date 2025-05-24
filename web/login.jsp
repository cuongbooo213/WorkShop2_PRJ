<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Admin Login</title>
</head>
<body>
    <h2>Admin Login</h2>
    <form action="login" method="post">
        Username: <input type="text" name="user"><br>
        Password: <input type="password" name="pwd"><br>
        <input type="submit" value="Login">
    </form>
    <h2><%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %></h2>
</body>
</html>