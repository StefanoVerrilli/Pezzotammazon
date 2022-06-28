<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <jsp:include page="/Head.jsp">
    <jsp:param name="page_title" value="Classes.Cart.CartModel - Pezzotammazon"/>
  </jsp:include>

  <jsp:include page="/Navbar.jsp">
    <jsp:param name="access_type" value="${user.getAccessType()}"/>
  </jsp:include>
    <%
        response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
        if (session.getAttribute("user") == null)
            response.sendRedirect("/LogIn.jsp");
    %>
</head>
<body>
<ul>
    <c:forEach var="suggestion" items="${suggestions}">
        <li>${suggestion.getName()}</li>
    </c:forEach>
</ul>
</body>
</html>
