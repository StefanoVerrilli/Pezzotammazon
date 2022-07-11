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
    <c:choose>
    <c:when test="${user != null}">
        <a class="navbar-item" href="ProductsTable.do">
    </c:when>
    <c:otherwise>
        <a class="navbar-item" href="LogIn.jsp">
    </c:otherwise>
    </c:choose>
            <img src="/static/logo.png" width="150" height="80" style="max-height: initial">
        </a>

        <a role="button" class="navbar-burger" aria-label="menu" aria-expanded="false" data-target="navbarBasicExample">
            <span aria-hidden="true"></span>
            <span aria-hidden="true"></span>
            <span aria-hidden="true"></span>
        </a>
    </div>
    <div id="navbarBasicExample" class="navbar-menu">
<c:choose>
<c:when test="${user != null}">
        <div class="navbar-start">
            <c:forEach items="${myNavbar.getElements()}" var="link" >
            <a class="navbar-item" href="<c:out value="${link.getValue()}"/>">
                    ${link.getKey()}
            </a>
            </c:forEach>
        </div>

    <div class="navbar-end">
        <div class="navbar-item has-dropdown is-hoverable">
            <a class="navbar-link">
                    ${user.getUsername()}
            </a>

            <div class="navbar-dropdown">
                <a class="navbar-item" href="/LogOut.do">
                    Logout
                </a>
            </div>
    </div>
</c:when>
    <c:otherwise>
        <div class="navbar-end">
            <div class="navbar-item">
                <div class="buttons">
                    <a class="button is-primary" href="/Register.jsp">
                        <strong>Sign up</strong>
                    </a>
                    <a class="button is-light" href="/LogIn.jsp">
                        Log in
                    </a>
                </div>
            </div>
        </div>
    </c:otherwise>
</c:choose>
    </div>

</nav>


<script>
    $(document).ready(function() {

        // Check for click events on the navbar burger icon
        $(".navbar-burger").click(function() {

            // Toggle the "is-active" class on both the "navbar-burger" and the "navbar-menu"
            $(".navbar-burger").toggleClass("is-active");
            $(".navbar-menu").toggleClass("is-active");

        });
    });
</script>

