<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="Classes.BuilderNavBar.Director" %>
<%@ page import="Classes.BuilderNavBar.AdminNavBuilder" %>
<%@ page import="Classes.BuilderNavBar.Navbar" %>
<%@ page import="Classes.Pair" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        for(Pair<String,String> navElement: myNavbar.getElements()){
            System.out.println(navElement.getKey());
            System.out.println(navElement.getValue());
        }
        }
        %>
<nav class="navbar" role="navigation" aria-label="main navigation">
    <div class="navbar-brand">
        <a class="navbar-item" href="/">
            <img src="static/logo.png" width="150" height="80" style="max-height: initial">
        </a>

        <a role="button" class="navbar-burger" aria-label="menu" aria-expanded="false" data-target="navbarBasicExample">
            <span aria-hidden="true"></span>
            <span aria-hidden="true"></span>
            <span aria-hidden="true"></span>
        </a>
    </div>

    <div id="navbarBasicExample" class="navbar-menu">
        <div class="navbar-start">
            <c:forEach items="${myNavbar.getElements()}" var="link" >
            <a class="navbar-item" href="<c:out value="${link.getValue()}"/>">
                    ${link.getKey()}
            </a>
            </c:forEach>
        </div>

    </div>
</nav>

