<%-- 
    Document   : nav.jsp
    Created on : 2017.04.16., 19:13:44
    Author     : Ildi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


        <!-- LIBS -->
        <script src="lib/jquery/jquery.min.js"></script>
        <script src="lib/jquery/jquery-ui.min.js"></script>
        <script src="lib/bootstrap/js/bootstrap.min.js"></script>
        <link href="https://maxcdn.bootstrapcdn.com/bootswatch/3.3.7/yeti/bootstrap.min.css" rel="stylesheet">
        <!-- /LIBS -->

        <!-- SITE -->
        <script src="js/project.js"></script>
        <link rel="stylesheet" href="css/project.css">
        <!-- /SITE -->
    </head>
    <body>
        <p>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand"><b>KönyvSziget</b></a>
                </div>

                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-2">
                    <ul class="nav navbar-nav">
                        <li><a href="index.jsp">Főoldal</a></li>
                        <li><a href="info.jsp">Kölcsönzésről</a></li>
                        <li><a href="books.jsp">Könyvek listázása</a></li>
                        <li><a href="borrows.jsp">Kölcsönzések kezelése</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="login.jsp">Kijelentkezés</a></li>
                    </ul>
                </div>
            </div>
        </nav>
    </body>
</html>
