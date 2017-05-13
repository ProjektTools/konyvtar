<%-- 
    Document   : books
    Created on : 2017.03.04., 19:52:03
    Author     : Ildi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Könyvek listázása</title>       
        
        <%@include file="nav.jsp" %>
        <script src="./js/booklist.js"></script>
    </head>
    <body>
    <legend>Keresés</legend>
    <div class="col-lg-3">
        <input type="text" class="form-control terkozos" id="searchtitle" placeholder="Cím">
    </div>
    <div class="col-lg-3">
        <input type="text" class="form-control terkozos" id="searchauthor" placeholder="Szerző">
    </div>
    <div class="col-lg-3">
        <select class="form-control terkozos" id="searchcategory">

        </select>
    </div>
    <div align="center"><button type="submit" id="searchbutton" class="btn btn-primary">Keresés</button></div>

    <hr>
    <div id="talalatok">
        
    </div>
    <div class="row">
        <div id="kategorialista">

        </div>
    </div>
</div>
<%@include file="footer.jsp" %>
</body>
</html>
