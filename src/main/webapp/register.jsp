<%--
  Created by IntelliJ IDEA.
  User: stefanoverrilli
  Date: 05/06/22
  Time: 09:33
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register Now</title>
</head>
<body>
<h1>Register Now</h1>
<form action="LogIn.do" method="post">
    <input type="text" name="username" required>
    <input type="email" name="mail" required>
    <input type="password" name="password" required>
    <input type="submit">
</form>
<c:if test="${error !=null}">
    <p><c:out value="${error}"></c:out></p>
</c:if>
<a href="/LogIn.jsp">go back</a>
</body>
</html>
