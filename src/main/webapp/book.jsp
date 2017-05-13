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
        <% if (!DatabaseHelper.kolcsonzi(username, request.getParameter("id"))) {%>
        <button id="kolcsonzom" class="btn btn-info">Kölcsönözném</button>
        <% } else {%>
        <a href="#" class="btn btn-info disabled">Jelenleg kölcsönzöd ezt a könyvet.</a>
        <% } %>
        <% if (!DatabaseHelper.olvasta(username, request.getParameter("id"))) {%>
        <button id="olvastam" class="btn btn-success">Olvastam</button>
        <% } else {%>
        <a href="#" class="btn btn-success disabled"> Már olvasottnak jelölted ezt a könyvet.</a>
        <form class="form-horizontal" method=POST>
            <div class="form-group">
                <label class="col-lg-2 control-label nagyobb">Pontszám</label>
                <div class="col-lg-10">
                    <div class="radio">
                        <div><label><input id="point" name="point" value="1"  type="radio">★</label></div>
                        <div><label><input id="point" name="point" value="2"  type="radio">★★</label></div>
                        <div><label><input id="point" name="point" value="3"  type="radio">★★★</label></div>
                        <div><label><input id="point" name="point" value="4"  type="radio">★★★★</label></div>
                        <div><label><input id="point" name="point" value="5"  type="radio">★★★★★</label></div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-lg-10 col-lg-offset-2">
                    <button type="reset" class="btn btn-default">Mégse</button>
                    <button type="submit" class="btn btn-primary">Értékelés</button>
                </div>
            </div>
        </form>
        <% } %>
        <% }%>
        <%@include file="footer.jsp" %>
    </body>
</html>

