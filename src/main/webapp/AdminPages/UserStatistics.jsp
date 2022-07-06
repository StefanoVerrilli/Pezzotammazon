<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="https://d3js.org/d3.v7.min.js"></script>
<script defer src="static/js/d3-tree.js"></script>
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
<body>


<div class="data-visualization"></div>


<script type="text/javascript">
    let data = ${analytics_data}
</script>


</body>
</html>
