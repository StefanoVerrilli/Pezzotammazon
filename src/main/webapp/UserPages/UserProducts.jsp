<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<body>
<%
    if(session.getAttribute("user") == null) {
        response.sendRedirect("/LogIn.jsp");
    }
%>

<jsp:include page="/Head.jsp">
    <jsp:param name="page_title" value="Products - Pezzotammazon"/>
</jsp:include>

<jsp:include page="/Navbar.jsp"/>

<c:if test="${not empty product_suggestions}">
<div class="block m-5">
    <!-- TITOLO SEZIONE -->
    <h2 class="title has-text-info">Suggested for you</h2>

    <div class="block is-flex is-flex-direction-row is-justify-content-center is-flex-wrap-wrap has-background-info-light">
        <div id="suggested-product-carousel" class="container is-fullwidth">
            <div class="is-flex is-flex-direction-row is-flex-wrap-nowrap py-4" style="overflow-x: auto; overflow-y: hidden; box-shadow: 0 4px 2px -2px gray;">
        <c:forEach var="product" items="${product_suggestions}">
            <!-- PRODOTTO SINGOLO -->
            <a href="FetchProduct.do?id=${product.getProduct().getID()}" class="mx-5 my-3 box is-inline-block">
                <div class="block is-clipped" style="height: 200px; width:300px">
                    <figure class="image has-ratio 16by9" style="max-width:300px">
                        <img src="data:image/png;base64,${product.getProduct().getImage()}" alt="Image depicting ${product.getProduct().getName()}">
                    </figure>
                </div>
                <p class="title is-5 mt-3" style="width:300px"><c:out value="${product.getProduct().getName()}"/> </p>
                <p class="title is-4 mt-3">
                    <c:choose>
                        <c:when test="${product.getProduct().decimalIsZero(product.getProduct().getCost())}">
                            <fmt:formatNumber value="${product.getProduct().getCost()}" minFractionDigits="0" maxFractionDigits="0"/>
                        </c:when>
                        <c:otherwise>
                            <fmt:formatNumber value="${product.getProduct().getCost()}" minFractionDigits="2" maxFractionDigits="2"/>
                        </c:otherwise>
                    </c:choose>
                    €
                </p>
            </a>
        </c:forEach>
            </div>
        </div>
    </div>
</div>
</c:if>



<div class="block m-5">
    <!-- TITOLO SEZIONE -->
    <h2 class="title">Products</h2>

    <div class="block is-flex is-flex-direction-row is-justify-content-center is-flex-wrap-wrap">
<c:forEach var="product" items="${products_table}">
        <!-- PRODOTTO SINGOLO -->
        <a href="FetchProduct.do?id=${product.getID()}" class="mx-5 my-3 box is-inline-block">
            <div class="block is-clipped" style="height: 200px; width:300px">
                <figure class="image has-ratio 16by9" style="max-width:300px">
                    <img src="data:image/png;base64,${product.getImage()}" alt="Image depicting ${product.getName()}">
                </figure>
            </div>
            <p class="title is-5 mt-3" style="width:300px"><c:out value="${product.getName()}"/> </p>
            <p class="title is-4 mt-3">
                <c:choose>
                    <c:when test="${product.decimalIsZero(product.getCost())}">
                        <fmt:formatNumber value="${product.getCost()}" minFractionDigits="0" maxFractionDigits="0"/>
                    </c:when>
                    <c:otherwise>
                        <fmt:formatNumber value="${product.getCost()}" minFractionDigits="2" maxFractionDigits="2"/>
                    </c:otherwise>
                </c:choose>
                 €
            </p>
        </a>
</c:forEach>
    </div>
</div>


</div>



</body>
</html>
