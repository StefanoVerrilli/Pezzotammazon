<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>InsertProduct</title>
    <script>
        function submitForm(){
            let form = document.getElementById('productForm');
            form.submit();
            form.reset();
            return false;
        }
    </script>
</head>
<%
    response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
    String user = null;
    if(session.getAttribute("user") == null)
        response.sendRedirect("/LogIn.jsp");
    else{
        user = (String) session.getAttribute("user");
    }
%>
<body>
<form action="AddProduct" method="get" id="productForm">
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
