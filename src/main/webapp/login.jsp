<%-- 
    Document   : login
    Created on : 2017.03.04., 19:22:06
    Author     : Ildi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bejelentkezés</title>
    </head>
    <body>
        <%@include file="nav.jsp" %>
        <form action="LoginServlet" method="POST" id="login_form" class="user_form" class="form-horizontal">
            <fieldset>
                <legend>Bejelentkezés</legend>
                <div class="form-group">
                    <label for="username_input" class="col-lg-2 control-label nagyobb">Felhasználónév</label>
                    <div class="col-lg-10">
                        <input type="text" class="form-control terkozos" id="username_input"  name="username_input" placeholder="Felhasználónév">
                    </div>
                </div>
                <p>
                <div class="form-group">
                    <label for="password_input" class="col-lg-2 control-label nagyobb">Jelszó</label>
                    <div class="col-lg-10">
                        <input type="password" class="form-control terkozos" id="password_input" name="password_input" placeholder="Jelszó">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-2">
                        <button type="reset" class="btn btn-default">Mégse</button>
                        <button type="submit" class="btn btn-primary">Bejelentkezés</button>
                    </div>
                </div>
            </fieldset>
        </form>

        <%
            String status = request.getParameter("status");
            if (status != null) {
                if (status.equals("wrongCredentials")) {%>

        <div class="col-sm-12 alert alert-danger" style="text-align: center; margin-top: 10px; margin-bottom: 10px">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Sikertelen bejelentkezés!</strong> Rossz felhasználónév vagy jelszó :(
        </div>

        <% } else if (status.equals("sessionExpired")) {%>
        <div class="col-sm-12 alert alert-warning" style="text-align: center; margin-top: 10px; margin-bottom: 10px">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>A rendelkezésre álló idő lejárt!</strong>
        </div>
        <%}
            }%>
        <%@include file="footer.jsp" %>

    </body>
</html>
