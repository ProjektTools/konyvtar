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
    </head>
    <body>
        <%@include file="nav.jsp" %>
    <legend>Keresés</legend>
            <div class="col-lg-3">
                <input type="text" class="form-control terkozos" id="searchtitle" placeholder="Cím">
            </div>
            <div class="col-lg-3">
                <input type="text" class="form-control terkozos" id="searchauthor" placeholder="Szerző">
            </div>
            <div class="col-lg-3">
                <select class="form-control terkozos" id="searchcatehory">
                    <option>-</option>
                    <option>Gasztronómia</option>
                    <option>Hobbi, szabadidő</option>
                    <option>Irodalom</option>
                    <option>Mese</option>
                </select>
            </div>
        <div align="center"><button type="submit" class="btn btn-primary">Keresés</button></div>
    
    <hr>
    <div class="row">
        <div class="col-lg-4">
            <div class="panel panel-basic">
                <div class="panel-heading">
                    <h3 class="panel-title"><b>Gasztronómia</b></h3>
                </div>
                <div class="panel-body">
                    <div class="list-group">
                        <a href="#" class="list-group-item">
                            <h4 class="list-group-item-heading">Könyv címe</h4>
                            <p class="list-group-item-text">tartalomtartalomtartalom.</p>
                        </a>
                        <a href="#" class="list-group-item">
                            <h4 class="list-group-item-heading">Könyv címe</h4>
                            <p class="list-group-item-text">tartalomtartalomtartalom.</p>
                        </a>
                        <a href="#" class="list-group-item">
                            <h4 class="list-group-item-heading">Könyv címe</h4>
                            <p class="list-group-item-text">tartalomtartalomtartalom.</p>
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-4">
            <div class="panel panel-basic">
                <div class="panel-heading">
                    <h3 class="panel-title"><b>Hobbi, szabadidő</b></h3>
                </div>
                <div class="panel-body">
                    <div class="list-group">
                        <a href="#" class="list-group-item">
                            <h4 class="list-group-item-heading">Könyv címe</h4>
                            <p class="list-group-item-text">tartalomtartalomtartalom.</p>
                        </a>
                        <a href="#" class="list-group-item">
                            <h4 class="list-group-item-heading">Könyv címe</h4>
                            <p class="list-group-item-text">tartalomtartalomtartalom.</p>
                        </a>
                        <a href="#" class="list-group-item">
                            <h4 class="list-group-item-heading">Könyv címe</h4>
                            <p class="list-group-item-text">tartalomtartalomtartalom.</p>
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-4">
            <div class="panel panel-basic">
                <div class="panel-heading">
                    <h3 class="panel-title"><b>Irodalom</b></h3>
                </div>
                <div class="panel-body">
                    <div class="list-group">
                        <a href="#" class="list-group-item">
                            <h4 class="list-group-item-heading">Könyv címe</h4>
                            <p class="list-group-item-text">tartalomtartalomtartalom.</p>
                        </a>
                        <a href="#" class="list-group-item">
                            <h4 class="list-group-item-heading">Könyv címe</h4>
                            <p class="list-group-item-text">tartalomtartalomtartalom.</p>
                        </a>
                        <a href="#" class="list-group-item">
                            <h4 class="list-group-item-heading">Könyv címe</h4>
                            <p class="list-group-item-text">tartalomtartalomtartalom.</p>
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-4">
            <div class="panel panel-basic">
                <div class="panel-heading">
                    <h3 class="panel-title"><b>Mese</b></h3>
                </div>
                <div class="panel-body">
                    <div class="list-group">
                        <a href="#" class="list-group-item">
                            <h4 class="list-group-item-heading">Könyv címe</h4>
                            <p class="list-group-item-text">tartalomtartalomtartalom.</p>
                        </a>
                        <a href="#" class="list-group-item">
                            <h4 class="list-group-item-heading">Könyv címe</h4>
                            <p class="list-group-item-text">tartalomtartalomtartalom.</p>
                        </a>
                        <a href="#" class="list-group-item">
                            <h4 class="list-group-item-heading">Könyv címe</h4>
                            <p class="list-group-item-text">tartalomtartalomtartalom.</p>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@include file="footer.jsp" %>
</body>
</html>
