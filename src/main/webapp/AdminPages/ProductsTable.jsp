<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

    <jsp:include page="/Head.jsp">
        <jsp:param name="page_title" value="Classes.Cart.CartModel - Pezzotammazon"/>
    </jsp:include>

    <jsp:include page="/Navbar.jsp">
        <jsp:param name="access_type" value="${access_type}"/>
    </jsp:include>

    <%
        response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
        if(session.getAttribute("user") == null)
            response.sendRedirect("/LogIn.jsp");
    %>
</head>
<body>

<div class="container">
    <div class="box mt-4">
        <c:forEach items="${data}" var="product">
            <div class="block">
        <div class="columns is-align-items-center">
            <div class="column is-1">
            <p class="has-text-grey-light"><span class="is-size-5">#</span>
                <fmt:formatNumber value="${product.getID()}" type="number"/>
            </p>
        </div>

            <div class="column">
                <img src="data:image/png;base64,${product.getImage()}" width="100" height="100">
            </div>

            <div class="column"><p class="has-text-primary">
                <c:out value="${product.getName()}"/>
            </p></div>
            <div class="column"><i></i>
                <c:out value="${product.getCategory().getCategoryDescription()}" />
            </div>
            <div class="column"><i class="fa-solid fa-boxes-stacked"></i>
                <fmt:formatNumber value="${product.getAmount()}" groupingUsed="false" type="number" />
            </div>
            <div class="column"><p class="has-text-danger has-text-weight-bold">
                <fmt:formatNumber value="${product.getCost()}" type="currency" maxFractionDigits="2" currencyCode="EUR" />
            </p></div>
            <div class="column">
                <a class="button is-small" href="${pageContext.request.contextPath}/FetchProduct.do?id=${product.getID()}">
    <span class="icon is-small">
      <i class="fa-solid fa-pen-to-square"></i>
    </span>
                    <span>Edit</span>
                </a>
            </div>

            <hr>
        </div>
            </c:forEach>
        </div>
    </div>
</div>


</body>
</html>
