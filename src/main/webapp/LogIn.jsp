<%@ page import="Classes.ServletsRegulation.Context" %>
<%@ page import="Classes.ServletsRegulation.DefaultState" %>
<%@ page import="Classes.ServletsRegulation.State" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%
        response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
        if(request.getSession().getAttribute("user") != null){
            request.getRequestDispatcher("ProductsTable.do").forward(request,response);
        }else{
            State DefaultState = new DefaultState();
            Context context = new Context(DefaultState);
            context.LoadLinks();
        }
    %>
<body>

<jsp:include page="Head.jsp">
    <jsp:param name="page_title" value="Login to your Pezzotammazon account"/>
</jsp:include>

<jsp:include page="Navbar.jsp" />

<div class="container content">
    <h1 class="has-text-centered title is-1">Log In</h1>
    <div class="columns is-mobile is-centered">
        <div class="column is-half">
            <form method="POST" action="LogIn.do" class="box">
                <div class="column is-vcentered">
                    <label class="label" for="email">Email</label>
                    <div class="control has-icons-left has-icons-right">
                        <input class="input" id="email" name="mail" type="email" placeholder="username@example.org" required autocomplete="email">
                        <span class="icon is-left">
                            <i class="fas fa-envelope"></i>
                        </span>
                    </div>

                    <label for="password" class="label mt-3">Password</label>
                    <div class="control has-icons-left has-icons-right">
                        <input class="input" id="password" name="password" type="password" placeholder="password" required autocomplete="current-password">
                        <span class="icon is-left">
                            <i class="fas fa-lock"></i>
                        </span>
                    </div>

                    <input type="submit" class="button is-primary is-half mt-3 block" value="Login">
                </div>
                Don't have an account yet? <a href="Register.jsp">Sign up now</a>.
            </form>
        </div>
    </div>
</div>

</body>
</html>