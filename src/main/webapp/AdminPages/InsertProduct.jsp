<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="Classes.User.UserModel" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<html>
<head>
    <jsp:include page="/Head.jsp">
        <jsp:param name="page_title" value="Classes.Cart.CartModel - Pezzotammazon"/>
    </jsp:include>

    <jsp:include page="/Navbar.jsp">
        <jsp:param name="access_type" value="${user.getAccessType()}"/>
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
            formData.append("productImage",productImage.files[0])
            $.ajax({
                url: 'Insert.do' ,
                type: 'POST' ,
                data: formData,
                processData : false,
                contentType : false,
                success : function (){
                    alert("success");
                }
            });
            form.reset();
            document.getElementById("preview").src = "";
            return false;
        }
    </script>
</head>
<%
    response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
    if(session.getAttribute("user") == null)
        response.sendRedirect("/LogIn.jsp");
%>
<body>
<form id="productForm" enctype="multipart/form-data">
    <input type="text" id="productName" name="productName" placeholder="Product Name" required>
    <input type="text" id="productDesc" name="productDesc"placeholder="Short Description">
    <input type="number" id="productCost" placeholder="Cost" name="productCost" step="0.01" required>
    <input type="number" id="productAmount" placeholder="Amount" name="productAmount" required>
    <select id="productCategory" name="productCategory">
        <c:forEach var="category" items="${categories}">
            <option value="${category.getCategoryID()}"><c:out value="${category.getCategoryDescription()}"></c:out></option>
        </c:forEach>
    </select>
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
<img src="#" id="preview">
</body>
</html>
