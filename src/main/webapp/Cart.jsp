<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="Classes.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Classes.User" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<html>
<head>
    <title>Cart</title>

<jsp:include page="Head.jsp">
    <jsp:param name="page_title" value="Cart - Pezzotammazon"/>
</jsp:include>

<jsp:include page="Navbar.jsp">
    <jsp:param name="access_type" value="${access_type}"/>
</jsp:include>

    <%
      response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
      if(session.getAttribute("user") == null) {
          response.sendRedirect("/LogIn.jsp");
      }else{
          List<Order> ShoppingList = (List<Order>) request.getSession().getAttribute("ShoppingList");
      }
        DecimalFormat df = new DecimalFormat("#.##");

  %>
    <script>
        function Quantity(id,quantity){
            console.log(quantity);
            let formdata = new FormData();
            formdata.append("orderId",id);
            formdata.append("orderQuantity",quantity);
            $.ajax({
                url: 'ChangeCost.do' ,
                type: 'POST' ,
                data: formdata,
                processData : false,
                contentType : false,
                success : function (){
                    $('#table').load("Cart.jsp #table");
                    $('#total').load("Cart.jsp #total");
                }
            });
            return false;
        }
        function Delete(id){
            let formdata = new FormData();
            console.log(id)
            formdata.append("id",id);
            $.ajax({
                url: 'DeleteOrder.do',
                type: 'POST',
                data: formdata,
                processData: false,
                contentType: false,
                success : function (){
                    $('#table').load("Cart.jsp #table");
                    $('#total').load("Cart.jsp #total");
                }
            })
        }
    </script>
</head>
<body>
<c:set var="total" value="${0}"/>
<table id="table">
    <tr>
        <th>Name</th>
        <th>Quantity</th>
        <th>SubTotal</th>
        <th>Delete</th>
    <c:forEach items="${ShoppingList}" var="item">
    <c:set var="total" value="${total + item.getSubTotal()}"/>
    <tr>
        <td> <c:out value="${item.getProduct().getName()}"/> </td>
        <td> <input type="number" id="quantity" name="quantity" min="1" max="100" value="${item.getQuantity()}" onchange="Quantity(${item.getID()},this.value)" ></td>
        <td>  <input type="number" id="subtotal" value="${item.getSubTotal()}" disabled readonly></td>
        <td> <button onclick="Delete(${item.getID()})">Remove</button> </td>
</tr>
    </c:forEach>
</table>
<div id="total">
    ${total}
</div>
<a href="Pay.jsp">Pay</a>
</body>
</html>
