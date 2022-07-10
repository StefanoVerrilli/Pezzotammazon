<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <jsp:include page="/Head.jsp">
    <jsp:param name="page_title" value="Suggest user ${selected_user.getUsername()} a product - Pezzotammazon"/>
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

      function suggestProduct(user_id, suggested_product_id) {
          let formdata = new FormData();
          formdata.append("userId", user_id);
          formdata.append("productId", suggested_product_id);
          $.ajax({
              url: 'AddSuggestion.do',
              type: 'POST',
              data: formdata,
              processData: false,
              contentType: false,
              success: function () {
                  $('#suggested-product-carousel').load("UserSuggestion.jsp #suggested-product-carousel");
              }
          });
          return false;
      }
  </script>

  <div class="container">
  <h2 class="is-size-2">Suggested products for <c:out value="${selected_user.getUsername()}"></c:out></h2>

        <div id="suggested-product-carousel" class="container is-fullwidth">
            <div class="is-flex is-flex-direction-row is-flex-wrap-nowrap py-4" style="overflow-x: auto; overflow-y: hidden">
    <c:forEach var="suggestion" items="${suggestions}">
        <div class="card mx-4" style="min-width: 300px">
            <div class="card-content is-clipped" style=" height:300px">
                <div class="card-image is-clipped" style="height:150px">
                    <figure class="image has-ratio 16by9" style="max-width:300px">
                        <img src="data:image/png;base64,${suggestion.getImage()}"
                             alt="Image depicting ${suggestion.getName()}">
                    </figure>
                </div>
                <div class="content">
                    <p class="title is-5 mt-3"><c:out value="${suggestion.getName()}"/></p>
                    <p class="title is-4 mt-3">
                        <fmt:formatNumber value="${suggestion.getCost()}" type="currency" maxFractionDigits="2"
                                          currencyCode="EUR"/>
                    </p>
                </div>
            </div>
            <footer class="card-footer">
                <a href="#" class="card-footer-item" onclick="suggestProduct(${selected_user.getId()}, ${suggestion.getID()})">Suggest item</a>
            </footer>
        </div>
    </c:forEach>
            </div>
        </div>

    <canvas id="top-purchases-visualization" width="300" height="100"></canvas>

  <h2 class="is-size-2"><c:out value="${selected_user.getUsername()}"></c:out>'s top purchases by category:</h2>


    <c:forEach var="category" items="${user_purchases_by_category.entrySet()}" varStatus="index">
      <div class="columns">
        <div class="column is-flex-grow-0">
            <span class="is-size-3 has-text-grey-light">#<c:out value="${index.index + 1}"></c:out></span>
        </div>
        <div class="column">
        <p><span class="is-size-4"><c:out value="${category.getKey()}"></c:out></span></p>
        <p class="is-size-5">(<c:out value="${category.getValue()}"></c:out> purchases)</p>
        </div>
      </div>
    </c:forEach>
  </div>

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
