<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <jsp:include page="/Head.jsp">
        <jsp:param name="page_title" value="Insert a new product - Pezzotammazon"/>
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
<div class="container">
<form id="productForm" enctype="multipart/form-data" method="post" action="Insert.do" onsubmit="event.preventDefault();submitForm()">
    <div class="field">
        <label class="label" for="productName">Product name</label>
        <div class="control">
            <input class="input" type="text" id="productName" name="productName" placeholder="Product Name" required>
        </div>
    </div>
    <div class="field">
        <label class="label" for="productDesc">Product short description</label>
        <div class="control">
            <input class="input" type="text" id="productDesc" name="productDesc"placeholder="Short Description">
        </div>
    </div>
    <div class="field">
        <label class="label" for="productCost">Product price</label>
        <p class="control">
            <input class="input" type="number" id="productCost" placeholder="Cost" name="productCost" step="0.01" required>
        </p>
    </div>
    <div class="field">
        <label class="label" for="productAmount">Product stock</label>
        <div class="control">
            <input class="input" type="number" id="productAmount" placeholder="Amount" name="productAmount" required>
        </div>
    </div>

    <div class="field">
        <label class="label" for="productCategory">Product category</label>
        <div class="select is-medium">
            <select id="productCategory" name="productCategory">
                <c:forEach var="category" items="${categories}">
                    <option value="${category.getCategoryID()}"><c:out value="${category.getCategoryDescription()}"></c:out></option>
                </c:forEach>
            </select>
        </div>
    </div>

    <div class="file is-large is-boxed has-name">
        <label class="file-label">
            <input class="file-input" type="file" id="productImage" name="productImage" accept=".jpg, .jpeg, .png">
            <span class="file-cta">
      <span class="file-icon">
        <i class="fas fa-upload"></i>
      </span>
      <span class="file-label">
        Choose product image
      </span>
    </span>
            <div class="has-background-light" style="text-align:center">
      <img class="my-4" src="https://bulma.io/images/placeholders/128x128.png" id="preview" style="max-width:400px">
    </div>
        </label>
    </div>

    <input class="button is-primary is-outlined is-large is-fullwidth mt-5" type="submit" value="Add product" />

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

</div>
</body>
</html>
