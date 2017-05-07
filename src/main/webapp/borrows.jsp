<%-- 
    Document   : borrows
    Created on : 2017.04.17., 14:13:45
    Author     : Ildi
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Kölcsönzések</title>
    </head>
    <body>
        <%@include file="nav.jsp" %>
    <legend>Kölcsönzéseim</legend>

    <table class="table-striped table-hover">
        <thead>
            <tr>
                <th>#</th>
                <th>Könyv címe</th>
                <th>Határidő</th>
                <th>Műveletek</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>1</td>
                <td>Mazsola és Tádé</td>
                <td>2017.05.17.</td>
                <td>
                    <% if (username.equals("admin")) {%>
                    <a href="#" class="btn btn-default btn-sm">Kölcsönzés jóváhagyása</a>
                    <a href="#" class="btn btn-default btn-sm">Kölcsönzés törlése</a>    
                    <a href="#" class="btn btn-default btn-sm">Hosszabbítás jóváhagyása</a>  
                    <% } else {%>
                    <a href="#" class="btn btn-default btn-sm">Visszahoztam</a>
                    <a href="#" class="btn btn-default btn-sm">Értékelés</a>    
                    <a href="#" class="btn btn-default btn-sm">Hosszabbítanám</a>  
                    <% }%>
                </td>
            </tr>
            <tr>
                <td>2</td>
                <td>Semmi</td>
                <td>2017.05.01.</td>
                <td>
                    <% if (username.equals("admin")) {%>
                    <a href="#" class="btn btn-default btn-sm">Kölcsönzés jóváhagyása</a>
                    <a href="#" class="btn btn-default btn-sm">Kölcsönzés törlése</a>    
                    <a href="#" class="btn btn-default btn-sm">Hosszabbítás jóváhagyása</a>  
                    <% } else {%>
                    <a href="#" class="btn btn-default btn-sm">Visszahoztam</a>
                    <a href="#" class="btn btn-default btn-sm">Értékelés</a>    
                    <a href="#" class="btn btn-default btn-sm">Hosszabbítanám</a>  
                    <% }%>
                </td>
            </tr>
            <tr>
                <td>3</td>
                <td>Tíz kicsi néger</td>
                <td>2017.04.30.</td>
                <td>
                    <% if (username.equals("admin")) {%>
                    <a href="#" class="btn btn-default btn-sm">Kölcsönzés jóváhagyása</a>
                    <a href="#" class="btn btn-default btn-sm">Kölcsönzés törlése</a>    
                    <a href="#" class="btn btn-default btn-sm">Hosszabbítás jóváhagyása</a>  
                    <% } else {%>
                    <a href="#" class="btn btn-default btn-sm">Visszahoztam</a>
                    <a href="#" class="btn btn-default btn-sm">Értékelés</a>    
                    <a href="#" class="btn btn-default btn-sm">Hosszabbítanám</a>  
                    <% }%> 
                </td>
            </tr>
        </tbody>
    </table>
    <%@include file="footer.jsp" %>


</body>
</html>

