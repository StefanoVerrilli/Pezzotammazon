<%@ page import="Classes.Models.Product" %>
<%@ page import="Classes.Models.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<html>
<head>
    <jsp:include page="/Head.jsp">
        <jsp:param name="page_title" value="Classes.Models.Cart - Pezzotammazon"/>
    </jsp:include>

    <jsp:include page="/Navbar.jsp">
        <jsp:param name="access_type" value="${access_type}"/>
    </jsp:include>

    <script>
            var result;
            function submitForm(){
            let form = document.getElementById('productForm');
            var formData = new FormData();
            formData.append("productName",document.getElementById("productName").value)
                formData.append("productDesc",document.getElementById("productDesc").value)
            formData.append("productCost",document.getElementById("productCost").value)
            formData.append("productAmount",document.getElementById("productAmount").value)
            formData.append("productCategory",document.getElementById("productCategory").value)
            formData.append("productID",document.getElementById("productID").value)
                if(productImage.files[0] != null)
                    formData.append("productImage",productImage.files[0])
            $.ajax({
            url: 'EditAction.do' ,
            type: 'POST' ,
            data: formData,
            processData : false,
            contentType : false,
            success: function () {
                    window.location = "ProductsTable.do"
            },
        });
            form.reset();
            return false;
        }
    </script>
    <title>EditProduct</title>
    <%
        response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
        User user = null;
        int access_type;
        int id;
        Product oldProduct = new Product();
        if(session.getAttribute("user") == null) {
            response.sendRedirect("/LogIn.jsp");
        }else{
            user = (User) session.getAttribute("user");
            access_type = user.getAccessType();
            oldProduct = (Product) session.getAttribute("Product");
            id = oldProduct.getID();
        }
    %>
</head>
<body>
<form id="productForm" enctype="multipart/form-data">
    <input type="text" id="productName" name="productName" value="<%=oldProduct.getName() %>" required>
    <input type="text" id="productDesc" name="productDesc" value="<%=oldProduct.getDesc() %>">
    <input type="number" id="productCost" value="<%=oldProduct.getCost() %>" name="productCost" step="0.01" required>
    <input type="number" id="productAmount" value="<%=oldProduct.getAmount() %>" name="productAmount" required>
    <select selected="<%= oldProduct.getCategory()%>" id="productCategory" name="productCategory">
        <option value="videogiochi">videogiochi</option>
        <option value="giochi">giochi</option>
        <option value="casa">casa</option>
    </select>
    <input type="hidden" id="productID" name="productID" value="<%=oldProduct.getID()%>">
    <input type="file" id="productImage" name="productImage">
    <input type="button" value="submit" onclick="submitForm()">
</form>
<script>
    const inputElement = document.getElementById('productImage');
    inputElement.addEventListener("change",function (){
        const [file] = this.files;
        if(file){
            preview.src = URL.createObjectURL(file)
        }
        result = this.files;
    })
</script>
<img src="data:image/jpeg;base64,<%=oldProduct.getImage()%>" id="preview">

<a href="delete.do?id=<%=oldProduct.getID()%>">Delete</a>

</body>
</html>