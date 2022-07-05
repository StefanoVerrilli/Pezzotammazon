<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <jsp:include page="/Head.jsp">
    <jsp:param name="page_title" value="Classes.Cart.CartModel - Pezzotammazon"/>
  </jsp:include>
  <script src="https://cdn.jsdelivr.net/npm/chart.js@3.8.0/dist/chart.min.js"></script>
  <jsp:include page="/Navbar.jsp">
    <jsp:param name="access_type" value="${user.getAccessType()}"/>
  </jsp:include>
    <%
        response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
        if (session.getAttribute("user") == null)
            response.sendRedirect("/LogIn.jsp");
    %>
  <body>

  <script>
      let dataArray = [];
      let categoriesArray = [];
  </script>

    Suggested products
    <c:forEach var="suggestion" items="${suggestions}">
        <li>${suggestion.getName()}</li>
    </c:forEach>


    <canvas id="top-purchases-visualization" width="300" height="100"></canvas>

    Top purchased by category for this user:

    <c:forEach var="category" items="${user_purchases_by_category.entrySet()}">
        <c:out value="${category.getKey()}"></c:out> (<c:out value="${category.getValue()}"></c:out> purchases)
    </c:forEach>

    <c:forEach var="category" items="${user_purchases_by_category.entrySet()}">
        <script>
            categoriesArray.push("<c:out value="${category.getKey()}"></c:out>")
            dataArray.push(<c:out value="${category.getValue()}"></c:out>)
        </script>
    </c:forEach>

    <script>
        const ctx = document.getElementById('top-purchases-visualization').getContext('2d');
        const topPurchases = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: categoriesArray,
                datasets: [{
                    label: 'Purchases',
                    data: dataArray,
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(255, 206, 86, 0.2)',
                        'rgba(75, 192, 192, 0.2)',
                        'rgba(153, 102, 255, 0.2)',
                        'rgba(255, 159, 64, 0.2)'
                    ],
                    borderColor: [
                        'rgba(255, 99, 132, 1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 206, 86, 1)',
                        'rgba(75, 192, 192, 1)',
                        'rgba(153, 102, 255, 1)',
                        'rgba(255, 159, 64, 1)'
                    ],
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    </script>

</body>
