<%@ page import="Classes.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: stefanoverrilli
  Date: 10/06/22
  Time: 21:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="Head.jsp">
    <jsp:param name="page_title" value="Cart - Pezzotammazon"/>
</jsp:include>

<jsp:include page="Navbar.jsp">
    <jsp:param name="access_type" value="${access_type}"/>
</jsp:include>

  <%
      int access_type = 0;
      String user = null;
      List<Order> shoppingList = new ArrayList<>();
      response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
      if(session.getAttribute("user") == null) {
          response.sendRedirect("/LogIn.jsp");
      }else{
          access_type = (int) session.getAttribute("access_type");
          user = (String) session.getAttribute("user");
          shoppingList = (List<Order>) session.getAttribute("shoppingList");
      }
  %>
<body>
<jsp:include page="Navbar.jsp">
    <jsp:param name="access_type" value="${access_type}"/>
</jsp:include>
<table>
    <tr>
        <th>Name</th>
        <th>Quantity</th>
        <th>Cost</th>
    </tr>
    <tbody>
    <%
        for(int i=0;i<shoppingList.size();i++){
            out.print("<tr>");
            out.print("<td>"+shoppingList.get(i).getName() + "</td>");
            out.print("<td>"+shoppingList.get(i).getQuantity() + "</td>");
            out.print("<td>"+shoppingList.get(i).getCost() + "</td>");
            out.print("<tr>");
        }
    %>
    </tbody>
</table>

</body>
</html>
