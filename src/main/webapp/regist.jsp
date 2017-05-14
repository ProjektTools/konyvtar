<%-- 
    Document   : regist
    Created on : 2017.03.04., 19:22:14
    Author     : Ildi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Regisztráció</title>
    </head>
    <body>
        <%@include file="nav.jsp" %>
        <form method="POST" action="RegistServlet" id="regist_form" class="user_form" class="form-horizontal">
            <fieldset>
                <legend>Regisztráció</legend>
                <p> Az űrlap kitöltése során ne használjon ékezetes karaktert!</p>
                <div class="form-group">
                    <label for="username_input" class="col-lg-2 control-label nagyobb">Felhasználónév</label>
                    <div class="col-lg-10">
                        <input type="text" class="form-control terkozos" id="username_input" name="username_input" placeholder="Felhasználónév">
                    </div>
                </div>
                <p>
                <div class="form-group">
                    <label for="email_input" class="col-lg-2 control-label nagyobb">Email</label>
                    <div class="col-lg-10">
                        <input type="text" class="form-control terkozos" id="email_input" name="email_input" placeholder="Email">
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
                        <button type="submit" class="btn btn-primary">Regisztráció</button>
                    </div>
                </div>
            </fieldset>
        </form>
        <%@include file="footer.jsp" %>
    </body>
</html>

