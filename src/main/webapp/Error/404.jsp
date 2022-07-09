<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/Head.jsp">
    <jsp:param name="page_title" value="Error 404 - Pezzotammazon"/>
</jsp:include>

<jsp:include page="/Navbar.jsp">
    <jsp:param name="access_type" value="${access_type}"/>
</jsp:include>

<div class="container has-text-centered is-size-2">
    <i class="fa-solid fa-question"></i>
    <p class="is-size-1">Oh no! Page not found.</p>
    <p class="is-size-4">We couldn't find your page.</p>
</div>

</body>

