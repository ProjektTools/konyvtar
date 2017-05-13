<%-- 
    Document   : newbook
    Created on : 2017.04.17., 14:14:17
    Author     : Ildi
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html" charset=UTF-8">
        <title>Új könyv</title>
    </head>
    <body>
        <%@include file="nav.jsp" %>
        <form action="NewBookServlet" method="POST" id="login_form" class="user_form" class="form-horizontal" accept-charset="UTF-8">
            <fieldset>
                <legend>Új könyv felvétele</legend>
                <div class="form-group">
                    <label for="title_input" class="col-lg-2 control-label nagyobb">Cím</label>
                    <div class="col-lg-10">
                        <input type="text" class="form-control terkozos" id="title_input" name="title_input" placeholder="Könyv címe">
                    </div>
                </div>
                <p>
                <div class="form-group">
                    <label for="author_input" class="col-lg-2 control-label nagyobb">Szerző</label>
                    <div class="col-lg-10">
                        <input type="text" class="form-control terkozos" id="author_input" name="author_input" placeholder="Könyv szerzője">
                    </div>
                </div>
                <p><div class="form-group">
                    <label for="publisher_input" class="col-lg-2 control-label nagyobb">Kiadó</label>
                    <div class="col-lg-10">
                        <input type="text" class="form-control terkozos" id="publisher_input" name="publisher_input" placeholder="Könyv kiadója">
                    </div>
                </div>
                <p>
                <div class="form-group">
                    <label for="description_input" class="col-lg-2 control-label nagyobb">Tartalom</label>
                    <div class="col-lg-10">
                        <textarea name="description_input" cols="40" rows="3" placeholder="Könyv tartalma" class="form-control terkozos"></textarea>
                    </div>
                </div>
                <p>
                <div class="form-group">
                    <label for="category_input" class="col-lg-2 control-label nagyobb">Kategória</label>
                    <div class="col-lg-10">
                            <select class="form-control terkozos" id="searchcategory" name="category_input"></select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="year_input" class="col-lg-2 control-label nagyobb">Megjelenés éve</label>
                    <div class="col-lg-10">
                        <input type="number" id="year_input" name="year_input" min="1950" max="2017" class="terkozos">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-2">
                        <button type="reset" class="btn btn-default">Mégse</button>
                        <button type="submit" class="btn btn-primary">Hozzáad</button>
                    </div>
                </div>
            </fieldset>
        </form>
        <%@include file="footer.jsp" %>
    </body>
</html>

