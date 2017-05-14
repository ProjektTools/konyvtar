<%-- 
    Document   : borrows
    Created on : 2017.04.17., 14:13:45
    Author     : Ildi
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Kölcsönzések</title>
        
        <%@include file="nav.jsp" %>
        <script src="./js/borrow.js"></script>
    </head>
    <body>
        <div id="username" value="${username}"></div>
    <legend>Kölcsönzéseim</legend>
    <p> Egy kölcsönzést 3 x 1 hónap időtartamra lehet meghosszabbírani </p>
    <div id="borrowspace"></div>
    
    <%@include file="footer.jsp" %>


</body>
</html>

