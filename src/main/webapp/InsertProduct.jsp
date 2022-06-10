<%@ page import="Classes.ProductOperations" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<html>
<head>
    <title>InsertProduct</title>
    <script>
        function submitForm(){
            let form = document.getElementById('productForm');
            console.log("ciao");
            $.ajax({
                url: 'AddProduct' ,
                type: 'GET' ,
                data: {
                    'productName' : document.getElementById("productName").value,
                    'productDesc' : document.getElementById("productDesc").value,
                    'productCost' : document.getElementById("productCost").value,
                    'productAmount' : document.getElementById("productAmount").value,
                    'productCategory' : document.getElementById("productCategory").value
                },
                success : function (){
                    //Mettici quello che vuoi qua france
                }
            });
            form.reset();
            return false;
        }
    </script>
</head>
<%
    response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
    String user = null;
    int access_type;
    if(session.getAttribute("user") == null)
        response.sendRedirect("/LogIn.jsp");
    else{
        user = (String) session.getAttribute("user");
        access_type = (int) session.getAttribute("access_type");
    }
%>
<body>
<jsp:include page="Header.jsp">
    <jsp:param name="access_type" value="${access_type}"/>
</jsp:include>
<form id="productForm">
    <input type="text" id="productName" name="productName" placeholder="Product Name" required>
    <input type="text" id="productDesc" name="productDesc"placeholder="Short Description">
    <input type="number" id="productCost" placeholder="Cost" name="productCost" step="0.01" required>
    <input type="number" id="productAmount" placeholder="Amount" name="productAmount" required>
    <select id="productCategory" name="productCategory">
        <option value="videogiochi">videogiochi</option>
        <option value="giochi">giochi</option>
        <option value="casa">casa</option>
    </select>
    <input type="button" value="submit" onclick="submitForm()">
</form>
</body>
</html>
