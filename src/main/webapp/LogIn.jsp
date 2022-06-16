<%@ page import="Classes.Pattern.*" %>
<%@ page import="Classes.Pair" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<jsp:include page="Head.jsp">
    <jsp:param name="page_title" value="Login to Pezzotammazon"/>
</jsp:include>

<jsp:include page="Navbar.jsp">
    <jsp:param name="access_type" value="${access_type}"/>
</jsp:include>

    <%//response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
        Actions.putAction("POST/LogIn.do", new Pair<>(new LogIn(),0));
        Actions.putAction("GET/ProductsTable.do",new Pair<>(new ProductsPageLogic(),1));
        Actions.putAction("GET/Edit.do",new Pair<>(new Edit(),1));
        Actions.putAction("GET/delete.do",new Pair<>(new delete(),1));
        Actions.putAction("POST/EditAction.do",new Pair<>(new EditAction(),1));
        Actions.putAction("POST/Insert.do",new Pair<>(new Insert(),1));
        Actions.putAction("POST/Register.do",new Pair<>(new Register(),0));
        Actions.putAction("GET/TestInsertOrder.do",new Pair<>(new TestInsertOrder(),0));
        Actions.putAction("POST/ChangeCost.do",new Pair<>(new ChangeCost(),0));
        Actions.putAction("POST/DeleteOrder.do",new Pair<>(new DeleteOrder(),0));
    %>
<body>


<div class="container content">
    <h1 class="has-text-centered title is-1">Log In</h1>
    <div class="columns is-mobile is-centered">
        <div class="column is-half">
            <form method="POST" action="LogIn.do" class="box">
                <div class="column is-vcentered">
                    <label class="label" for="email">Email</label>
                    <div class="control">
                        <input class="input" id="email" name="mail" type="email" placeholder="username@example.org" required="">
                    </div>

                    <label for="password" class="label mt-3">Password</label>
                    <div class="control">
                        <input class="input" id="password" name="password" type="password" placeholder="password" required="">
                    </div>

                    <input type="submit" class="button is-primary is-half mt-3 block" value="Login">
                </div>
                Don't have an account yet? <a href="register.jsp">Sign up now</a>.
            </form>
        </div>
    </div>
</div>

</body>
</html>