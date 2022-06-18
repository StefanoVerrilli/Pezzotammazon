<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="Head.jsp">
    <jsp:param name="page_title" value="Products - Pezzotammazon"/>
</jsp:include>

<jsp:include page="Navbar.jsp">
   <jsp:param name="access_type" value="${access_type}"/>
</jsp:include>
<body>
<div class="mx-auto container is-fullhd columns is-flex is-flex-direction-row is-flex-wrap-wrap is-justify-content-space-between is-align-content-center is-align-items-center">
    <div class="column is-flex-grow-3 is-flex-shrink-1 container is-max-desktop" style="min-width:300px">
        <div class="block">
            <figure class="image is-fullwidth mx-auto">
                <img src="data:image/png;base64,${Product.getImage()}">
            </figure>
        </div>
    </div>
    <div class="column is-flex-grow-4 is-flex-shink-2 is-align-self-flex-start" style="min-width:500px">
        <div class="block">
            <p class="title">${Product.getName()}</p>
                ${Product.getDesc()}
            </div>
    </div>
    <div style="min-width:250px" class="column is-flex-grow-2 is-flex-shink-4">
        <div class="block">
            <p class="title is-2"><fmt:formatNumber value="${Product.getCost()}" type="currency" maxFractionDigits="2" currencyCode="EUR"/></p>
            <a href="TestInsertOrder.do?id=${Product.getID()}" class="button is-large is-fullwidth is-primary">Add to cart</a>
        </div>
    </div>
</div>
</body>
</html>
