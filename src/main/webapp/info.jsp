<%-- 
    Document   : info
    Created on : 2017.03.04., 19:38:22
    Author     : Ildi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Kölcsönzésről</title>
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
                    Néhány használatos információ az oldal használatával kapcsolatban:
                    <ul>
                        <li>A könyvek listáját bárki megtekintheti, azonban ha kölcsönözni szeretnél akkor regisztráció szükséges</li>
                        <li>Bejelentkezést követően megtalálod a listát az általad kölcsönzött könyvekről, valamint itt van lehetőséged értékelni is</li>
                    </ul>
                </div>
            </div>
        </div>
    </body>
</html>

