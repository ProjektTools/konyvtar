<%-- 
    Document   : category
    Created on : 2017.05.13., 18:20:37
    Author     : Ildi
--%>

<%@page import="com.mycompany.project_tools.helpers.DatabaseHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        

        <title>Kategóriához tartozó könyvek</title>
        
        <%@include file="nav.jsp" %>
        <script src="./js/findcategory.js"></script>
    </head>
    <body>
        <div id="katlista"></div>
        <%@include file="footer.jsp" %>
    </body>
</html>
