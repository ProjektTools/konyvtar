<%-- 
    Document   : info
    Created on : 2017.03.04., 19:38:22
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
        <title>Kölcsönzésről</title>
    </head>
    <body>
        <% if (username == null) { %>
        <%@include file="nav.jsp" %>
        <% } else { %>
        <% if (username.equals("admin")) { %>
        <%@include file="nav_admin.jsp" %>
        <% } else { %>
        <%@include file="nav_user.jsp" %>
        <% } %>
        <% }%>
        <h2>Néhány használatos információ az oldal használatával kapcsolatban:</h2>
        <ul>
            <li>A könyvek listáját bárki megtekintheti, azonban ha kölcsönözni szeretnél akkor regisztráció szükséges</li>
            <li>Bejelentkezést követően megtalálod a listát az általad kölcsönzött könyvekről, valamint itt van lehetőséged értékelni is</li>
        </ul>

        <h2>A kölcsönzés menete </h2>
        <ul>
            <li>Regisztrálsz az oldalra</li>
            <li>Bejelentkezel</li>
            <li>A könyvek listájában megkeresed a számodra legizgalmasabb könyvet</li>
            <li>A Kölcsönzés gombra kattintva kikölcsönzöd a könyvet</li>
            <li>A kölcsönzések kezelése oldalon vissza tudni hozni ha már elolvastad, valamint tudod értékelni hogy megkönnyítsd a választást mások számára</li>
        </ul>

        <%@include file="footer.jsp" %>
    </body>
</html>

