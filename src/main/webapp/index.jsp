<%-- 
    Document   : index
    Created on : 2017.03.04., 15:15:55
    Author     : Ildi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Kezdőlap</title>
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
        <div class="panel panel-default">
            <div class="row" style="margin-bottom: 10px;">
                <div class="col-md-7">
                    <h1> KönyvSziget </h1>
                </div>
                <div class="col-md-5" style="padding-right: 50px; padding-top: 20px;">
                    <form action="RegistServlet">
                        <input type="submit" value="Regisztráció" style="float: right; margin: 2px;"class="btn btn-warning">
                    </form>

                    <form action="LoginServlet">
                        <input type="submit" value="Bejelentkezés" style="float: right; margin: 2px"class="btn btn-warning">
                    </form>
                </div>
            </div>
            <div class="panel-body">
                <div class="col-md-12">
                    <div id="main_menu_bar">
                        <ul>
                            <li><a href="index.jsp">Főoldal</a></li>
                            <li><a href=#>Kölcsönzésről</a></li>
                            <li><a href=#>Könyvek listázása</a></li>
                        </ul>
                    </div>
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut vestibulum, tellus a suscipit vulputate, est lectus cursus erat, vel molestie tortor felis nec risus. Aliquam urna tortor, suscipit eget justo quis, egestas hendrerit mauris. Mauris a lacus sed nibh mollis mattis sit amet non massa. Curabitur congue lorem vel lorem accumsan pharetra. Ut dictum sodales ipsum, ac efficitur tortor luctus sed. Aenean condimentum risus vitae turpis luctus, a rutrum lectus euismod. Integer feugiat risus eu est scelerisque placerat. Etiam interdum, eros sit amet fermentum sodales, arcu ipsum sagittis tortor, quis pellentesque ex dolor sit amet nibh. Sed porta ligula nec pharetra rutrum. Sed aliquam quis nisl tempus rhoncus. Ut cursus porta erat, et fringilla augue rutrum nec. Vivamus vitae facilisis diam. Nullam nec lobortis enim. Nullam at nisi ut diam eleifend vestibulum malesuada eu nisi. Vestibulum varius lectus nisl, ac efficitur nisl tempor in.
                </div>
            </div>
        </div>
    </body>
</html>
