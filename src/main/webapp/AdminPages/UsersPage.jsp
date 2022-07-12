<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%
        response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
        if (session.getAttribute("user") == null)
            response.sendRedirect("/LogIn.jsp");
    %>
<jsp:include page="/Head.jsp">
    <jsp:param name="page_title" value="Users - Pezzotammazon"/>
</jsp:include>

<jsp:include page="/Navbar.jsp"/>
</head>
<body>
    <div class="container">
        <c:forEach items="${UsersList}" var="user">
        <a href="orderElaboration.do?id=${user.getId()}" class="box my-4 has-text-centered"> ${user.getUsername()} <span class="has-text-grey-light">(${user.getEmail()})</span></a>
        </c:forEach>
    </div>
</body>
</html>
