<%@ page import="Classes.Models.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="Head.jsp">
    <jsp:param name="page_title" value="Pezzotammazon"/>
</jsp:include>

<body>
<%
    response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
  User user = null;
  if(session.getAttribute("user") == null) {
      response.sendRedirect("/LogIn.jsp");
  }else{
      user = (User) request.getSession().getAttribute("user");
      System.out.println(user.getAccessType());
  }
%>
<jsp:include page="Navbar.jsp">
    <jsp:param name="access_type" value="${user.getAccessType()}"/>
</jsp:include>
<h1>HI ${user.getUsername()}</h1>
</body>
</html>
