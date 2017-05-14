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

function deletefunction(id) {
    deleteBorrow(id);
}

function renewfunction(id) {
    $.ajax({
        url: "AjaxServlet",
        type: "GET",
        dataType: 'json',
        data: {
            cmd: "renewuser",
            kolcs_id: id
        },
        async: true,
        success: function (data, textStatus, jqXHR) {
            console.log(data);            
            location.reload();
        },
        error: function (data, jqXHR, textStatus, errorThrown) {
            console.log(data);
        }
    });
}

function renewfunction_admin(id) {
    console.log(id);
    $.ajax({
        url: "AjaxServlet",
        type: "GET",
        dataType: 'json',
        data: {
            cmd: "renewadmin",
            kolcs_id: id
        },
        async: true,
        success: function (data, textStatus, jqXHR) {
            console.log(data);
            location.reload();
        },
        error: function (data, jqXHR, textStatus, errorThrown) {
            console.log(data);
        }
    });
}

function returnfunction(id) {
    console.log(id);
    $.ajax({
        url: "AjaxServlet",
        type: "GET",
        dataType: 'json',
        data: {
            cmd: "returnbook",
            kolcs_id: id
        },
        async: true,
        success: function (data, textStatus, jqXHR) {
            console.log(data);            
            location.reload();
        },
        error: function (data, jqXHR, textStatus, errorThrown) {
            console.log(data);
        }
    });
}

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
            var sor = "";
            sor += "<table class=\"table-striped table-hover\"><thead><tr><th>#</th><th>Kölcsönző neve</th><th>Könyv címe</th><th>Határidő</th><th>Műveletek</th><th>Státusz</th></tr></thead><tbody>";
            for (var i = 0; i < data.borrows.length; i++) {
                sor += "<tr><td>" + i + "</td><td>" + data.borrows[i].user + "</td><td>" + data.borrows[i].title + "</td><td>" + data.borrows[i].expiredate + "</td>";
                sor += "<td>";
                if (username === "admin") {
                    sor += "<button onclick=\"deletefunction(" + data.borrows[i].kolcs_id + ")\" class=\"btn btn-default btn-sm\">Kölcsönzés törlése</button> ";
                    if (data.borrows[i].status.trim() !== "hosszabbit")
                    {
                        sor += "<button class=\"btn btn-default  btn-sm disabled\">Hosszabbítás jóváhagyása</button>";
                    } else {
                        sor += "<button onclick=\"renewfunction_admin(" + data.borrows[i].kolcs_id + ")\" class=\"btn btn-default btn-sm\">Hosszabbítás jóváhagyása</button>";
                    }
                } else {
                    if (data.borrows[i].status.trim() === "visszahozott") {
                        sor += "<button class=\"btn btn-default btn-sm disabled\">Visszahoztam</button> ";
                    } else {
                        sor += "<button onclick=\"returnfunction(" + data.borrows[i].kolcs_id + ")\" class=\"btn btn-default btn-sm\">Visszahoztam</button> ";
                    }
                    if (data.borrows[i].status.trim() === "hosszabbit" || data.borrows[i].status.trim() ==="visszahozott")
                    {
                        sor += "<button class=\"btn btn-default  btn-sm disabled\">Hosszabbítanám</button>";
                    } else {
                        sor += "<button onclick=\"renewfunction(" + data.borrows[i].kolcs_id +")\" class=\"btn btn-default btn-sm \">Hosszabbítanám</button>";
                    }
                }
                sor += "<td>" + data.borrows[i].status + "</td>";
            }
            sor += "</td></tr></tbody></table>";
            $("#borrowspace").append(sor);
        },
        error: function (data, jqXHR, textStatus, errorThrown) {
            console.log(data);
        }
    });
}

function deleteBorrow(id) {
    $.ajax({
        url: "AjaxServlet",
        type: "GET",
        dataType: 'json',
        data: {
            cmd: "deleteBorrow",
            id: id
        },
        async: true,
        success: function (data, textStatus, jqXHR) {
            console.log(data);
            location.reload();
        },
        error: function (data, jqXHR, textStatus, errorThrown) {
            console.log(data);
        }
    });
}
