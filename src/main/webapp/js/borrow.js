/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    console.log("borrow");
     var name = document.getElementById("username"), username;
    username = name.getAttribute("value");
    console.log(username);
    getBorrows(username);
});

function getBorrows(username) {
    $.ajax({
        url: "AjaxServlet",
        type: "GET",
        dataType: 'json',
        data: {
            cmd: "getBorrows",
            username: username
        },
        async: true,
        success: function (data, textStatus, jqXHR) {
            var sor ="";
            sor+="<table class=\"table-striped table-hover\"><thead><tr><th>#</th><th>Kölcsönző neve</th><th>Könyv címe</th><th>Határidő</th><th>Műveletek</th></tr></thead><tbody>";
            for(var i=0; i<data.borrows.length;i++){
                sor+="<tr><td>"+i+"</td><td>"+data.borrows[i].user+"</td><td>"+data.borrows[i].title+"</td><td>"+data.borrows[i].expiredate+"</td>";
                sor+="<td>";
                if (username === "admin"){
                sor+="<a href=\"#\" class=\"btn btn-default btn-sm\">Kölcsönzés törlése</a> ";
                sor+="<a href=\"#\" class=\"btn btn-default btn-sm\">Hosszabbítás jóváhagyása</a>";
                } else {
                sor+="<a href=\"#\" class=\"btn btn-default btn-sm\">Visszahoztam</a>";
                sor+="<a href=\"#\" class=\"btn btn-default btn-sm\">Hosszabbítanám</a>";
                }
            }
            sor+="</td></tr></tbody></table>";
             $("#borrowspace").append(sor);
        },
        error: function (data, jqXHR, textStatus, errorThrown) {
            console.log(data);
        }
    });
}
