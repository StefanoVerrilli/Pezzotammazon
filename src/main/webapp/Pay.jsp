<%--
  Created by IntelliJ IDEA.
  User: stefanoverrilli
  Date: 18/06/22
  Time: 15:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <jsp:include page="Head.jsp">
    <jsp:param name="page_title" value="Cart - Pezzotammazon"/>
  </jsp:include>

  <jsp:include page="Navbar.jsp">
    <jsp:param name="access_type" value="${access_type}"/>
  </jsp:include>
</head>
<body>
<form method="post" action="">
  <input type="radio" value="Card" id="Card" name="CreditCard">
  <label for="Card">Card</label><br>
  <input type="radio" id="Bacomat" value="Bancomat" name="Bancomat">
  <label for="Bacomat">Bancomat</label>
  <input type="submit" name="Proceed">
</form>
</body>
</html>
