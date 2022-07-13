<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/Head.jsp">
  <jsp:param name="page_title" value="Server error - Pezzotammazon"/>
</jsp:include>

<jsp:include page="/Navbar.jsp"/>

<div class="container has-text-centered is-size-2">
  <i class="fa-solid fa-x"></i>
  <p class="is-size-1">Oh no! An error Occurred</p>
  <p class="is-size-4">Here some details: ${error}</p>
</div>

</body>
