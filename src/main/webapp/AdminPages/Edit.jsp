<%@ page import="Classes.Product.ProductModel" %>
<%@ page import="Classes.User.UserModel" %>
<%@ page import="Classes.Product.ProductCategory.ProductCategoriesOperations" %>
<%@ page import="Classes.Product.ProductCategory.ProductCategoryModel" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<html>
<head>
    <jsp:include page="/Head.jsp">
        <jsp:param name="page_title" value="Edit ${Product.getName()} - Pezzotammazon"/>
    </jsp:include>

    <jsp:include page="/Navbar.jsp"/>

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
            return false;
        }
    </script>
    <%
        response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");

        if(session.getAttribute("user") == null) {
            response.sendRedirect("/LogIn.jsp");
        }
    %>
<body>
<div class="container my-3">
    <form id="productForm" enctype="multipart/form-data" action="EditAction.do" method="POST" onsubmit="event.preventDefault();submitForm()">
        <input type="hidden" id="productID" name="productID" value="${Product.getID()}">
        <div class="field">
            <label class="label" for="productName">Product name</label>
            <div class="control">
                <input class="input" type="text" id="productName" name="productName" value="${Product.getName()}" required>
            </div>
        </div>
        <div class="field">
            <label class="label" for="productDesc">Product short description</label>
            <div class="control">
                <input class="input" type="text" id="productDesc" name="productDesc" value="${Product.getDesc()}">
            </div>
        </div>
        <div class="field">
            <label class="label" for="productCost">Product price</label>
            <p class="control">
                <input class="input" type="number" id="productCost" value="${Product.getCost()}" name="productCost" step="0.01" required>
            </p>
        </div>
        <div class="field">
            <label class="label" for="productAmount">Product stock</label>
            <div class="control">
                <input class="input" type="number" id="productAmount" value="${Product.getAmount()}" name="productAmount" required>
            </div>
        </div>

        <div class="field">
            <label class="label" for="productCategory">Product category</label>
            <div class="select is-medium">
                <select id="productCategory" name="productCategory">
                    <c:forEach var="category" items="${categories}">
                        <option <c:if test="${category.getCategoryID() == Product.getCategory().getCategoryID()}">selected</c:if> value="${category.getCategoryID()}"><c:out value="${category.getCategoryDescription()}"></c:out></option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="file is-large is-boxed has-name">
            <label class="file-label">
                <input class="file-input" type="file" id="productImage" name="productImage" accept=".jpg, .jpeg, .png">
                <span>
      <span class="file-icon">
      </span>
      <span class="button is-success is-outlined">
        Edit product image
      </span>
    </span>
                <div class="has-background-light" style="text-align:center">
                    <img class="my-4" src="data:image/jpeg;base64,${Product.getImage()}" id="preview" style="max-width:400px">
                </div>
            </label>
        </div>

        <input class="button is-primary is-large is-fullwidth mt-5" type="submit" value="Save changes" />

    </form>

    <a href="delete.do?id=${Product.getID()}"class="button is-danger is-outlined is-fullwidth">
     <span class="icon is-small">
      <i class="fa-solid fa-trash-can"></i>
    </span>
        <span>Delete product</span>
    </a>
</div>
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


</body>
</html>
