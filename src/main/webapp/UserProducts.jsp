<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <jsp:include page="Head.jsp">
        <jsp:param name="page_title" value="Products - Pezzotammazon"/>
    </jsp:include>

    <jsp:include page="Navbar.jsp">
        <jsp:param name="access_type" value="${access_type}"/>
    </jsp:include>
<body>
<%
    if(session.getAttribute("user") == null) {
        response.sendRedirect("/LogIn.jsp");
    }
    //request.getSession().getAttribute
%>


<div class="block m-5">
    <!-- TITOLO SEZIONE -->
    <h2 class="title">Section title</h2>

    <div class="block is-flex is-flex-direction-row is-justify-content-flex-start is-flex-wrap-wrap">
<c:forEach var="product" items="${data}">
        <!-- PRODOTTO SINGOLO -->
        <a href="Edit.do?id=${product.getID()}" class="mx-5 my-3">
            <div class="box is-inline-block">
                <figure class="image has-ratio 16by9" style="max-width:300px">
                    <img src="data:image/png;base64,${product.getImage()}">
                </figure>
                <p class="title is-6 mt-3"><c:out value="${product.getName()}"/> </p>
            </div>
        </a>
</c:forEach>
    </div>
</div>


</div>



</body>
</html>
