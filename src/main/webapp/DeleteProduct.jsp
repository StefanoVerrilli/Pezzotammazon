<%@ page import="Classes.Pair" %><%--
  Created by IntelliJ IDEA.
  User: stefanoverrilli
  Date: 09/06/22
  Time: 08:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        request.getParameter("id");
    %>
</head>
<body>
<a href="ProductDeletion?id=${id}&oldProduct=${oldProduct}">Delete Product</a>
</body>
</html>
