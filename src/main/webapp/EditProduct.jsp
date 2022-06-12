<%@ page import="Classes.Product" %>
<%@ page import="Classes.Database" %>
<%@ page import="Classes.ProductOperations" %>
<%@ page import="Classes.Memento.ProductList" %>
<%@ page import="java.util.List" %>
<%@ page import="Classes.Memento.Memento" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
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
            id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("id",id);
            oldProduct = ProductOperations.GetSpecificProduct(Integer.parseInt(request.getParameter("id")));
        }

    %>
</head>
<body>
<jsp:include page="Header.jsp">
    <jsp:param name="access_type" value="${access_type}"/>
</jsp:include>
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
    <input type="file" id="productImage" name="productImage">
    <input type="submit">
    <input type="hidden" name="productID" value="<%=request.getParameter("id")%>">
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
