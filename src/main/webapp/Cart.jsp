<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="Classes.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Classes.User" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
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

<body>

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
                $('#cart').load("Cart.jsp #cart");
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
                $('#cart').load("Cart.jsp #cart");
                $('#total').load("Cart.jsp #total");
            }
        })
    }
</script>

<c:set var="total" value="${0}"/>
<div class="container is-fluid" style="margin-top: 20vh">
        <div  class="is-flex is-align-content-space-around is-inline-block mx-5 box" style="width: 50vw">
        <div class="column is-align-self-center">
        <div id="cart">
            <c:choose>
            <c:when test="${empty ShoppingList}">
                <p class="has-text-centered">Your cart is empty.</p>
            </c:when>
            <c:otherwise>
        <c:forEach items="${ShoppingList}" var="item">
            <c:set var="total" value="${total + item.getSubTotal()}"/>
            <div class="py-5" style="border-bottom: 1px solid gray; border-top: 1px solid gray">
                <div class="columns">
                    <div class="is-pulled-right ">
                        <figure class="image is-128x128">
                            <img src="https://bulma.io/images/placeholders/128x128.png">
                        </figure>
                    </div>

                    <div class="column">
                        <p class="is-size-3"> <c:out value="${item.getProduct().getName()}"/>  </p><div class="columns">

                        <div class="column"> <input type="number" id="quantity" name="quantity" min="1" max="100" value="${item.getQuantity()}" onchange="Quantity(${item.getID()},this.value)" >
                        <div class="is-hidden">   <input type="number" id="subtotal" value="${item.getSubTotal()}" disabled readonly></div>
                        <div class="column has-text-centered">
                            <button onclick="Delete(${item.getID()})" class="button is-danger is-outlined is-small">
                                    <span class="icon is-small">
                                       <i class="fas fa-times"></i>
                                    </span>
                                <span>Remove</span>
                            </button>
                        </div>
                    </div>

                    </div>

                </div>
                    <div></div></div></div>
                 </c:forEach>
        </div>
        </div>


    </div>



    <div class="is-pulled-right has-text-centered box mx-5" style="width:30vw; position:sticky; top:10vh">
        <p class="has-text-centered is-size-2 has-text-info"><span id="total">${total}</span> €</p>
        <a href="Pay.jsp" class="mt-3 block button is-primary is-medium is-responsive">Pay</a>
    </div>
    </c:otherwise>
    </c:choose>
</div>
</div>







</body>
</html>
