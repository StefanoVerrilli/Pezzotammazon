<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="Classes.ShoppingItem.ShoppingItemModel" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

    <%
      response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
      if(session.getAttribute("user") == null) {
          response.sendRedirect("/LogIn.jsp");
      }
  %>
<jsp:include page="/Head.jsp">
    <jsp:param name="page_title" value="Your cart - Pezzotammazon"/>
</jsp:include>

<jsp:include page="/Navbar.jsp"/>

<body>

<script>
    function Quantity(id,quantity){
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
                $('#cart').load("/UserPages/Cart.jsp #cart");
                $('#total').load("/UserPages/Cart.jsp #total");
            }
        });
        return false;
    }
    function Delete(id){
        let formdata = new FormData();
        formdata.append("id",id);
        $.ajax({
            url: 'DeleteOrder.do',
            type: 'POST',
            data: formdata,
            processData: false,
            contentType: false,
            success : function (){
                $('#cart').load("/UserPages/Cart.jsp #cart");
                $('#pay').load("/UserPages/Cart.jsp #pay");
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
                            <img src="data:image/png;base64,${item.getProduct().getImage()}">
                        </figure>
                    </div>

                    <div class="column">
                        <p class="is-size-3"> <c:out value="${item.getProduct().getName()}"/>  </p>

                        <div class="columns">

                            <div class="column">

                            <div class="control has-icons-left">
                                <div class="select">
                                    <select onchange="Quantity(${item.getProduct().getID()},this.value)">

                                        <c:choose>
                                        <c:when test="${item.getProduct().getAmount() < 10}">
                                            <c:set var="max_amount" value="${item.getProduct().getAmount()}"></c:set>
                                        </c:when>
                                        <c:otherwise>
                                            <c:set var="max_amount" value="10"></c:set>
                                        </c:otherwise>
                                        </c:choose>

                                         <c:forEach begin="1" end="${max_amount}" step="1" varStatus="quantity">
                                             <option <c:if test="${quantity.count == item.getQuantity()}">selected</c:if>><c:out value="${quantity.count}"/></option>
                                         </c:forEach>
                                    </select>
                                </div>
                                <span class="icon is-left">
                                    <i class="fas fa-layer-group"></i>
                                </span>
                            </div>
                            </div>


                        <div class="column">
                            <p class="is-size-6 has-text-weight-bold has-text-info"><fmt:formatNumber value="${item.getProduct().getCost()}" type="currency" maxFractionDigits="2" currencyCode="EUR"  /></p>
                        </div>

                        <div>

                            <div class="column has-text-centered">
                            <button onclick="Delete(${item.getProduct().getID()})" class="button is-danger is-outlined is-small">
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


    <span id="pay">
    <div class="is-pulled-right has-text-centered box mx-5" style="width:30vw; position:sticky; top:10vh">
        <p class="has-text-centered is-size-2 has-text-info"><span id="total"><fmt:formatNumber value="${total}" type="currency" maxFractionDigits="2" currencyCode="EUR" /></span></p>
        <a href="PaymentPageLoad.do" class="mt-3 block button is-primary is-medium is-responsive">Pay</a>
    </div>
    </span>
    </c:otherwise>
    </c:choose>
</div>
</div>







</body>
</html>
