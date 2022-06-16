<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="Classes.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Classes.User" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>

<head>
    <title>Products</title>
    <%
        response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
        User user = null;
        List<Product> data = new ArrayList<>();
        int access_type;
        if(session.getAttribute("user") == null) {
            response.sendRedirect("/LogIn.jsp");
        }else{
            access_type = (int) session.getAttribute("access_type");
            user = (User) session.getAttribute("user");
            data = (List<Product>) session.getAttribute("data");
        }
    %>
</head>
<body>
<jsp:include page="Header.jsp">
    <jsp:param name="access_type" value="${access_type}"/>
</jsp:include>
<table>
    <tr>
    <th>ID</th>
    <th>Name</th>
    <th>Amount</th>
    <th>Cost</th>
    <th>Category</th>
    <th>Description</th>
        <th>Image</th>
        <th>Modify</th>
    </tr>
    <c:forEach items="${data}" var="product">
        <tr>
            <td><fmt:formatNumber value="${product.getID()}" type="number"/> </td>
            <td><c:out value="${product.getName()}"/> </td>
            <td><fmt:formatNumber value="${product.getAmount()}" groupingUsed="false" type="number" /> </td>
            <td><fmt:formatNumber value="${product.getCost()}" type="currency" maxFractionDigits="2" currencyCode="EUR" /></td>
            <td><c:out value="${product.getCategory()}" /> </td>
            <td><c:out value="${product.getDesc()}" /> </td>
            <td><img src="data:image/png;base64,${product.getImage()}" width="100" height="100"></td>
            <td><a href="${pageContext.request.contextPath}/Edit.do?id=${product.getID()}">modifica</a> </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
