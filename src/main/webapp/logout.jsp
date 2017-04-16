<%-- 
    Document   : logout
    Created on : 2017.04.16., 18:46:53
    Author     : Ildi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <div class="row" style="margin-bottom: 10px;">
            <div class="col-md-10"></div>
            <div class="col-md-2" style="padding-right: 25px;">
                <form action="LogoutServlet">
                    <input type="submit" value="Kijelentkezés" style="float: right;"class="btn btn-danger btn-xs">
                </form>
            </div>
        </div>
    </body>
</html>
