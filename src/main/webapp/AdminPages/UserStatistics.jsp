<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="https://d3js.org/d3.v7.min.js"></script>
<script defer src="static/js/d3-tree.js"></script>
<jsp:include page="/Head.jsp">
    <jsp:param name="page_title" value="User macro-groups - Pezzotammazon"/>
</jsp:include>
<jsp:include page="/Navbar.jsp"/>
<%
    response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
    if (session.getAttribute("user") == null)
        response.sendRedirect("/LogIn.jsp");
%>
<body>


<div id="data-visualization"></div>

<style>
    svg {
        border: solid 1px #ccc;
    }
    .link {
        fill: none;
        stroke: #ccc;
        stroke-width: 2px;
    }
</style>

<script type="text/javascript">
    let data_input = ${analytics_data};
</script>


</body>
</html>
