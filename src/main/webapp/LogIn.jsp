<%@ page import="Classes.Pattern.*" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<%//response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
    Actions.putAction("GET/LogIn.do", new LogIn());
    Actions.putAction("POST/LogIn.do",new Register());
    Actions.putAction("GET/ProductsTable.do",new ProductsPageLogic());
    Actions.putAction("GET/Edit.do",new Edit());
    Actions.putAction("GET/delete.do",new delete());
    Actions.putAction("POST/EditAction.do",new EditAction());
    Actions.putAction("POST/Insert.do",new Insert());
%>
<h1>Log In</h1>
<br/>
<form action="LogIn.do" method="get">
<input type="email" name="mail" required>
<input type="password" name="password" required>
<input type="submit">
</form>
<a href="register.jsp">Register</a>
</body>
</html>