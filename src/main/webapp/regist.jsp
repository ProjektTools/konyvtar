<%-- 
    Document   : regist
    Created on : 2017.03.04., 19:22:14
    Author     : Ildi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Regisztráció</title>
        <!-- LIBS -->
        <script src="lib/jquery/jquery.min.js"></script>
        <script src="lib/jquery/jquery-ui.min.js"></script>
        <script src="lib/bootstrap/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="lib/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="lib/bootstrap/css/bootstrap-theme.min.css">
        <!-- /LIBS -->

        <!-- SITE -->
        <script src="js/project.js"></script>
        <link rel="stylesheet" href="css/project.css">
        <!-- /SITE -->
    </head>
    <body>
        <h1> KönyvSziget</h1>
        <div class="panel panel-default">
            <div class="row" style="margin-bottom: 10px;">
                <div class="col-md-7">
                </div>
                <div class="col-md-5" style="padding-right: 50px; padding-top: 20px;">
                    <a href="regist.jsp"><input type="submit" value="Regisztráció" style="float: right; margin: 2px;"class="btn btn-warning"></a>
                    <a href="login.jsp"><input type="submit" value="Bejelentkezés" style="float: right; margin: 2px"class="btn btn-warning"></a>
                </div>
            </div>
            <div class="panel-body">
                <div class="col-md-12">
                    <div id="main_menu_bar">
                        <ul>
                            <li><a href="index.jsp">Főoldal</a></li>
                            <li><a href="info.jsp">Kölcsönzésről</a></li>
                            <li><a href="books.jsp">Könyvek listázása</a></li>
                        </ul>
                    </div>
                    <form action="LoginServlet" method="POST" id="login_form" class="user_form">
                        <div class="col-md-12">
                            <label>Felhasználónév</label>
                            <input class="form-control textinput" type="text" id="username_input" name="username_input" placeholder="Felhasználónév">
                            <label>Jelszó</label>
                            <input class="form-control textinput" type="password" id="password_input" name="password_input" placeholder="Jelszó">
                            <input style="width: 100%;" type="submit" class="btn btn-lg tsm_button" value="Regisztráció">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>

