<%@ page import="Classes.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.Blob" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.Base64" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Products</title>
    <%
        response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
        String user = null;
        List<Product> data = new ArrayList<>();
        int access_type;
        if(session.getAttribute("user") == null) {
            response.sendRedirect("/LogIn.jsp");
        }else{
            access_type = (int) session.getAttribute("access_type");
            user = (String) session.getAttribute("user");
            data = (List<Product>) request.getSession().getAttribute("data");
            System.out.println(data.get(0).getImage());
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
            out.print("<td>"+"<img src=\"data:image/png;base64,"+data.get(i).getImage()+"\" width=100 height=100></img>"+"</td>");
            out.print("<td>"+"<a href=\"EditProduct.jsp?id=" + data.get(i).getID() +"\">Edit</a>" +"</td>");
        }
    %>
    </tbody>
</table>
<a href="Undo">undo</a>
</body>
</html>
