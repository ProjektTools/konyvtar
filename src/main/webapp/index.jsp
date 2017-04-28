<%-- 
    Document   : index
    Created on : 2017.03.04., 15:15:55
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
        <title>Kezdőlap</title>
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
        <% } %>

        <img src="images/home.png" style="width:400px; float:right">
        <h1>Köszöntünk kis világunkban kedves idegen!</h1>
        <p>Ez a webalkalmazás a Projekt Eszközök nevű tárgy beadandó feladataként készült el.
        <p>Látogatóként böngészhetsz a könyvek között akár szerzőnként, kategóriánként szűkítve a találatok listáját, ha pedig regisztrálsz akkor lehetőséged
            van kölcsönözni és utólag értékelni a könyveket. Ezek alapján létrejövő rangsor a könyvek között:
            <p><b>A legolvasottabb könyv: </b>
                #KÖNYVCÍME
            <p><b>A legjobb értékeléssel rendelkező könyv: </b>
                #KÖNYVCÍME
            <p><b>A legrosszabb értékeléssel rendelkező könyv: </b>
                #KÖNYVCÍME
            
        <p><b>A csapat tagjai:</b>
        <ul>
            <li>Gy. Adrienn</li>
            <li>K. Ildikó</li>
            <li>M. Ildikó</li>
            <li>R. Marcell</li>
        </ul>
        <%@include file="footer.jsp" %>
    </body>
</html>
