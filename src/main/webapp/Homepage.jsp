<%@ page import="Classes.User" %><%--
  Created by IntelliJ IDEA.
  User: stefanoverrilli
  Date: 06/06/22
  Time: 08:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MainPage</title>
</head>
<body>
<%
    response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
  User user = null;
  String email = null;
  int access_type;
  if(session.getAttribute("user") == null) {
      response.sendRedirect("/LogIn.jsp");
  }else{
      user = (User) session.getAttribute("user");
      session.setAttribute("access_type",user.getAccessType());
  }
%>
<jsp:include page="Header.jsp">
    <jsp:param name="access_type" value="${access_type}"/>
</jsp:include>
<h1>HI ${user.getUsername()}</h1>
</body>
</html>
