<%@ page import="java.util.ArrayList" %>
<%@ page import="Classes.Pair" %>
<%@ page import="Classes.BuilderNavBar.Director" %>
<%@ page import="java.util.List" %>
<%@ page import="Classes.BuilderNavBar.AdminNavBuilder" %>
<%@ page import="Classes.BuilderNavBar.Navbar" %><%--
  Created by IntelliJ IDEA.
  User: stefanoverrilli
  Date: 07/06/22
  Time: 13:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Header</title>
    <%
        Navbar myNavbar = new Navbar();
        if(session.getAttribute("user") == null) {
            response.sendRedirect("/LogIn.jsp");
        }else{
        Director director = new Director();
        AdminNavBuilder adminNavBar = new AdminNavBuilder();
        int Access;
        Access = Integer.parseInt(request.getParameter("access_type"));
        if(Access == 0) {
            director.constructUserNavBar(adminNavBar);
            myNavbar = adminNavBar.getProduct();
        }else{
            director.constructAdminNavBar(adminNavBar);
            myNavbar = adminNavBar.getProduct();
        }
        }
        %>
</head>
<body>
<table>
    <tr>
    <%
        for(int i=0;i<myNavbar.getElements().size();i++){
            out.print("<td>"+"<a href="+"\"" + myNavbar.getElements().get(i).getValue() + "\">" + myNavbar.getElements().get(i).getKey() +"</a>"+"</td>");
        }
    %>
    </tr>
</table>
</body>
</html>
