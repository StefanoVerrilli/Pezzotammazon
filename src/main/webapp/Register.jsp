<%--
  Created by IntelliJ IDEA.
  User: stefanoverrilli
  Date: 05/06/22
  Time: 09:33
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="Head.jsp">
    <jsp:param name="page_title" value="New account - Pezzotammazon"/>
</jsp:include>

<jsp:include page="Navbar.jsp">
    <jsp:param name="access_type" value="${access_type}"/>
</jsp:include>

<body>

<div class="container content">
    <h1 class="has-text-centered title is-1">Sign up</h1>
    <div class="columns is-mobile is-centered">
        <div class="column is-half">
            <form method="POST" action="Register.do" class="box">
                <div class="column is-vcentered">

                    <label class="label" for="username">Username</label>
                    <div class="control">
                        <input class="input" id="username" name="username" type="text" spellcheck="false" placeholder="username" required>
                    </div>

                    <label class="label mt-3" for="email">Email</label>
                    <div class="control">
                        <input class="input" id="email" name="mail" type="email" placeholder="username@example.org" required autocomplete="email">
                    </div>

                    <label for="password" class="label mt-3">Password</label>
                    <div class="control">
                        <input class="input" id="password" name="password" type="password" placeholder="password" required autocomplete="new-password">
                    </div>

                    <input type="submit" class="button is-primary is-half mt-3 block" value="Sign up">
                </div>

                <c:if test="${error !=null}">
                    <p class="help is-danger"><c:out value="${error}"></c:out></p>
                </c:if>

                Already have an account? <a href="/LogIn.jsp">Log in</a>.
            </form>
        </div>
    </div>
</div>

</body>
</html>