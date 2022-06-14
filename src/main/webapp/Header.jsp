<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="Classes.BuilderNavBar.Director" %>
<%@ page import="Classes.BuilderNavBar.AdminNavBuilder" %>
<%@ page import="Classes.BuilderNavBar.Navbar" %>
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
        request.getSession().setAttribute("myNavbar",myNavbar);
        }
        %>
</head>
<body>
<table>
    <tr>
    <c:forEach items="${myNavbar.getElements()}" var="link" >
        <td><a href="<c:out value="${product.getValue()}"/>">${link.getKey()}</a> </td>
    </c:forEach>
    </tr>
</table>
</body>
</html>
