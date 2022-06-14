<%@ page import="Classes.Pattern.*" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>${page_title}</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
</head>
<body>

<%//response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
    Actions.putAction("POST/LogIn.do", new LogIn());
    Actions.putAction("GET/ProductsTable.do",new ProductsPageLogic());
    Actions.putAction("GET/Edit.do",new Edit());
    Actions.putAction("GET/delete.do",new delete());
    Actions.putAction("POST/EditAction.do",new EditAction());
    Actions.putAction("POST/Insert.do",new Insert());
%>


<!-- NAVBAR -->
<nav class="navbar" role="navigation" aria-label="main navigation">
    <div class="navbar-brand">
        <a class="navbar-item" href="/">
            <img src="static/logo.png" width="150" height="80" style="max-height: initial">
        </a>

        <a role="button" class="navbar-burger" aria-label="menu" aria-expanded="false" data-target="navbarBasicExample">
            <span aria-hidden="true"></span>
            <span aria-hidden="true"></span>
            <span aria-hidden="true"></span>
        </a>
    </div>

    <div id="navbarBasicExample" class="navbar-menu">
        <div class="navbar-start">
            <a class="navbar-item">
                Home
            </a>

            <a class="navbar-item">
                Documentation
            </a>
        </div>

        <div class="navbar-end">
            <div class="navbar-item">
                <div class="buttons">
                    <a class="button is-primary">
                        <strong>Sign up</strong>
                    </a>
                    <a class="button is-light">
                        Log in
                    </a>
                </div>
            </div>
        </div>
    </div>
</nav>




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
                Don't have an account yet? <a href="register.jsp">Register</a>.
            </form>
        </div>
    </div>
</div>

</body>
</html>