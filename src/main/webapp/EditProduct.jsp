<%@ page import="Classes.Product" %>
<%@ page import="Classes.ProductOperations" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<html>
<head>
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
            url: 'EditProductAction' ,
            type: 'POST' ,
            data: formData,
            processData : false,
            contentType : false,
            success: function () {
                    window.location = "ProductPageLogic"
            },
        });
            form.reset();
            return false;
        }
    </script>
    <title>EditProduct</title>
    <%
        response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
        String user = null;
        int access_type;
        int id;
        Product oldProduct = new Product();
        if(session.getAttribute("user") == null) {
            response.sendRedirect("/LogIn.jsp");
        }else{
            access_type = (int) session.getAttribute("access_type");
            user = (String) session.getAttribute("user");
            id = (int) session.getAttribute("id");
            oldProduct = (Product) session.getAttribute("oldProduct");
        }

    %>
</head>
<body>
<jsp:include page="Header.jsp">
    <jsp:param name="access_type" value="${access_type}"/>
</jsp:include>
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
    <input type="file" id="productImage" name="productImage">
    <input type="button" value="submit" onclick="submitForm()">
    <input type="hidden" id="productID" name="productID" value="<%=oldProduct.getID()%>">
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
<jsp:include page="DeleteProduct.jsp">
    <jsp:param name="id" value="${id}"/>
</jsp:include>
</body>
</html>
