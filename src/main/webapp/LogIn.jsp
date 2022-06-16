<%@ page import="Classes.Pattern.*" %>
<%@ page import="Classes.Pair" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%
        response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
        Actions.putAction("POST/LogIn.do", new LogIn());
        Actions.putAction("GET/ProductsTable.do",new ProductsPageLogic());
        Actions.putAction("GET/Edit.do",new Edit());
        Actions.putAction("GET/delete.do",new delete());
        Actions.putAction("POST/EditAction.do",new EditAction());
        Actions.putAction("POST/Insert.do",new Insert());
        Actions.putAction("POST/Register.do",new Register());
        Actions.putAction("GET/TestInsertOrder.do",new TestInsertOrder());
        Actions.putAction("POST/ChangeCost.do",new ChangeCost());
        Actions.putAction("POST/DeleteOrder.do",new DeleteOrder());
        if(request.getSession().getAttribute("user") != null){
            response.sendRedirect("Homepage.jsp");
        }
    %>
<body>

<jsp:include page="Head.jsp">
    <jsp:param name="page_title" value="Login to Pezzotammazon"/>
</jsp:include>

<jsp:include page="Navbar.jsp" />

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