<%--
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
  String user = null;
  if(session.getAttribute("user") == null)
      response.sendRedirect("/LogIn.jsp");
  else{
      user = (String) session.getAttribute("user");
  }
%>
<h1>HI ${user}</h1>
<a href="${pageContext.request.contextPath}/logout">logout</a>
<a href="ProductVisualization">Prodotti</a>
<a href="${pageContext.request.contextPath}/InsertProduct.jsp">Insert Product</a>
</body>
</html>
