<%-- 
    Document   : book
    Created on : 2017.05.06., 13:10:33
    Author     : Ildi
--%>

<%@page import="com.mycompany.project_tools.helpers.DatabaseHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        

        <title>Könyv címe</title>
    </head>
    <body>
        <%@include file="navbook.jsp" %>
        <div id="tartalom"></div>
        <div id="username" value="${username}"></div>
        <% if (username != null) {%>
        <a href="#" class="btn btn-info">Kölcsönözném</a>
        <% if (!DatabaseHelper.olvasta(username, request.getParameter("id"))) {%>
        <button id="olvastam" class="btn btn-success">Olvastam</button>
        <% } else {%>
        <a href="#" class="btn btn-success disabled"> Már olvasottnak jelölted ezt a könyvet.</a>
        <% } %>
        <% } %>
        <%@include file="footer.jsp" %>
    </body>
</html>

