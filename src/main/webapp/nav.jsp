<%-- 
    Document   : nav.jsp
    Created on : 2017.04.16., 19:13:44
    Author     : Ildi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    //allow access only if session exists
    String username = null;
    Integer secLvl = null;
    if (session != null) {
        username = (String) session.getAttribute("username");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- LIBS -->
        <script src="./lib/jquery/jquery.min.js"></script>
        <script src="./lib/jquery/jquery-ui.min.js"></script>
        <script src="./lib/bootstrap/js/bootstrap.min.js"></script>
        <link href="https://maxcdn.bootstrapcdn.com/bootswatch/3.3.7/yeti/bootstrap.min.css" rel="stylesheet">
        <!-- /LIBS -->

        <!-- SITE -->        
        <link rel="stylesheet" href="./css/project.css">
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
                        <li><a href="./index">Főoldal</a></li>
                        <li><a href="./info">Kölcsönzésről</a></li>
                        <li><a href="./books">Könyvek listázása</a></li>
                         <% if (username != null) { %>                        
                        <li><a href="./borrows">Kölcsönzések kezelése</a></li>
                        <% if (username.equals("admin")) { %>                        
                        <li><a href="./newbook">Új könyv felvétele</a></li>  
                        <% } %>                       
                        <% } %>
                      </ul>                         
                        <ul class="nav navbar-nav navbar-right">
                            <% if (username == null) { %>
                            <li><a href="./login">Bejelentkezés</a></li>
                            <li><a href="./regist">Regisztráció</a></li>
                            <% } else { %>
                            <li><form action="LogoutServlet"><button type="submit" class="btn btn-primary">Kijelentkezés</button></form></li>
                            <% } %>
                        </ul>
                </div>
            </div>
        </nav>
    </body>
</html>
