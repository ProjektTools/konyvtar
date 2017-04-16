<%-- 
    Document   : main
    Created on : 2017.03.04., 19:21:53
    Author     : Ildi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    //allow access only if session exists
    String username = null;
    Integer secLvl = null;
    if (session == null) {
        response.sendRedirect("index.jsp?status=sessionExpired");
    } else if (session.getAttribute("username") == null) {
        response.sendRedirect("index.jsp?status=sessionExpired");
    } else {
        username = (String) session.getAttribute("username");
    }
    String activePageTitle = "Főoldal";
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista</title>
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

        Hello <b><%=username%></b>, sikeresen bejelentkeztél!<br>
        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut vestibulum, tellus a suscipit vulputate, est lectus cursus erat, vel molestie tortor felis nec risus. Aliquam urna tortor, suscipit eget justo quis, egestas hendrerit mauris. Mauris a lacus sed nibh mollis mattis sit amet non massa. Curabitur congue lorem vel lorem accumsan pharetra. Ut dictum sodales ipsum, ac efficitur tortor luctus sed. Aenean condimentum risus vitae turpis luctus, a rutrum lectus euismod. Integer feugiat risus eu est scelerisque placerat. Etiam interdum, eros sit amet fermentum sodales, arcu ipsum sagittis tortor, quis pellentesque ex dolor sit amet nibh. Sed porta ligula nec pharetra rutrum. Sed aliquam quis nisl tempus rhoncus. Ut cursus porta erat, et fringilla augue rutrum nec. Vivamus vitae facilisis diam. Nullam nec lobortis enim. Nullam at nisi ut diam eleifend vestibulum malesuada eu nisi. Vestibulum varius lectus nisl, ac efficitur nisl tempor in.
        <%@include file="footer.jsp" %>
    </div>
</div>
</div>
</body>
</html>
