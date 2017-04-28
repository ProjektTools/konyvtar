<%-- 
    Document   : newbook
    Created on : 2017.04.17., 14:14:17
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
        <title>Új könyv</title>
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


        <%@include file="footer.jsp" %>
    </body>
</html>

