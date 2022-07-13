<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/Head.jsp">
  <jsp:param name="page_title" value="Server error - Pezzotammazon"/>
</jsp:include>

<jsp:include page="/Navbar.jsp"/>

<div class="container has-text-centered is-size-2">
  <i class="fa-solid fa-x animate__animated animate__shakeX"></i>
  <p class="is-size-1">Oh no! An error occurred on our side.</p>
  <p class="is-size-4">
  <c:choose>
  <c:when test="${error != null}">
  Here is some detail: ${error}.
  </c:when>
  <c:otherwise>
    We have no detail about the issue.
  </c:otherwise>
  </c:choose>
  </p>
</div>

</body>
