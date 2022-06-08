<%@ page import="Classes.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="Classes.ProductOperations" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Products</title>
    <%
        List<Product> data = ProductOperations.GetProducts();
    %>
</head>
<body>
<jsp:include page="Header.jsp" />
<table>
    <tr>
    <th>ID</th>
    <th>Name</th>
    <th>Amount</th>
    <th>Cost</th>
    <th>Category</th>
    <th>Description</th>
        <th>Modify</th>
    </tr>
    <tbody>
    <%
        for(int i=0;i<data.size();i++){
            out.print("<tr>");
            out.print("<td>"+data.get(i).getID() + "</td>");
            out.print("<td>"+data.get(i).getName() + "</td>");
            out.print("<td>"+data.get(i).getAmount() + "</td>");
            out.print("<td>"+data.get(i).getCost() + "</td>");
            out.print("<td>"+data.get(i).getCategory() + "</td>");
            out.print("<td>"+data.get(i).getDesc() + "</td>");
            out.print("<td>"+"<a href=\"EditProduct.jsp?id=" + data.get(i).getID() +"\">Edit</a>" +"</td>");
        }
    %>
    </tbody>
</table>
</body>
</html>
