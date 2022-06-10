<%--
  Created by IntelliJ IDEA.
  User: stefanoverrilli
  Date: 05/06/22
  Time: 09:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register Now</title>
</head>
<body>
<h1>Register Now</h1>
<form action="${pageContext.request.contextPath}/RegisterUser" method="get">
    <input type="text" name="username" required>
    <input type="email" name="mail" required>
    <input type="password" name="password" required>
    <input type="submit">
</form>
<a href="/LogIn.jsp">go back</a>
</body>
</html>
