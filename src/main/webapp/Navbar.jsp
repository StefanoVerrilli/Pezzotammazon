<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <%
        if(session.getAttribute("user") == null) {
            response.sendRedirect("/LogIn.jsp");
        }
        %>

<nav class="navbar" role="navigation" aria-label="main navigation">
    <div class="navbar-brand">
        <a class="navbar-item" href="/">
            <img src="/static/logo.png" width="150" height="80" style="max-height: initial">
        </a>

        <a role="button" class="navbar-burger" aria-label="menu" aria-expanded="false" data-target="navbarBasicExample">
            <span aria-hidden="true"></span>
            <span aria-hidden="true"></span>
            <span aria-hidden="true"></span>
        </a>
    </div>
<c:if test="${user != null}">
    <div id="navbarBasicExample" class="navbar-menu">
        <div class="navbar-start">
            <c:forEach items="${myNavbar.getElements()}" var="link" >
            <a class="navbar-item" href="<c:out value="${link.getValue()}"/>">
                    ${link.getKey()}
            </a>
            </c:forEach>
        </div>
    </div>
</c:if>
</nav>

