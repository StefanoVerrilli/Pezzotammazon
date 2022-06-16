<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <jsp:include page="Head.jsp">
        <jsp:param name="page_title" value="Products - Pezzotammazon"/>
    </jsp:include>

    <jsp:include page="Navbar.jsp">
        <jsp:param name="access_type" value="${access_type}"/>
    </jsp:include>
<body>



<div class="block m-5">
    <!-- TITOLO SEZIONE -->
    <h2 class="title">Section title</h2>

    <div class="block is-flex is-flex-direction-row is-justify-content-flex-start is-flex-wrap-wrap">

        <!-- PRODOTTO SINGOLO -->
        <a href="/" class="mx-5 my-3">
            <div class="box is-inline-block">
                <figure class="image has-ratio 16by9" style="max-width:300px">
                    <img src="https://bulma.io/images/placeholders/640x480.png">
                </figure>
                <p class="title is-6 mt-3">Prodotto</p>
            </div>
        </a>

    </div>

</div>


</div>



</body>
</html>
