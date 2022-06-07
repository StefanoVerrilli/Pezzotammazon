<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<%response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");%>
<h1>Log In</h1>
<br/>
<form action="${pageContext.request.contextPath}/Check" method="get">
<input type="email" name="mail" required>
<input type="password" name="password" required>
<input type="submit">
</form>
<a href="${pageContext.request.contextPath}/register.jsp">Register</a>
</body>
</html>