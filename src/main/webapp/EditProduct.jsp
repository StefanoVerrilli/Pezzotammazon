<%@ page import="Classes.Product" %>
<%@ page import="Classes.Database" %>
<%@ page import="Classes.ProductOperations" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EditProduct</title>
    <%
        Product oldProduct = ProductOperations.GetSpecificProduct(Integer.parseInt(request.getParameter("id")));
        System.out.println(oldProduct.getName());
    %>
</head>
<body>
<form action="EditProductAction" method="get" id="productForm">
    <input type="text" id="productName" name="productName" value="<%=oldProduct.getName() %>" required>
    <input type="text" id="productDesc" name="productDesc" value="<%=oldProduct.getDesc() %>">
    <input type="number" id="productCost" value="<%=oldProduct.getCost() %>" name="productCost" step="0.01" required>
    <input type="number" id="productAmount" value="<%=oldProduct.getAmount() %>" name="productAmount" required>
    <select selected="<%= oldProduct.getCategory()%>" id="productCategory" name="productCategory">
        <option value="videogiochi">videogiochi</option>
        <option value="giochi">giochi</option>
        <option value="casa">casa</option>
    </select>
    <input type="submit">
    <input type="hidden" name="productID" value="<%=request.getParameter("id")%>">
</form>
</body>
</html>
