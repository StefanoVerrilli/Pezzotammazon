<%@ page import="org.omg.PortableInterceptor.SUCCESSFUL" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        System.out.println("ciao");
        System.out.println(Integer.getInteger(request.getParameter("id")));
        if(request.getParameter("id").equals("")){
            System.out.println("e si");
        }
    %>
</head>
<body>
<a href="delete.do?id=${id}">Delete Product</a>
</body>
</html>
