/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    function getQueryParam(param) {
        var result = window.location.search.match(
                new RegExp("(\\?|&)" + param + "(\\[\\])?=([^&]*)")
                );
        return result ? result[3] : false;
    }
    var id = getQueryParam("id");
    getBookDatas(id);
    var name = document.getElementById("username"), username;
    username = name.getAttribute("value");
    console.log(username);
    $('#olvastam').click(function () {
        insertToRead(username, id);
    });
});

function insertToRead(username, id){
    $.ajax({
        url: "../AjaxServlet",
        type: "GET",
        dataType: 'json',
        data: {
            cmd: "insertToRead",
            id: id,
            username: username
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

function getBookDatas(id) {
    $.ajax({
        url: "../AjaxServlet",
        type: "GET",
        dataType: 'json',
        data: {
            cmd: "getBookDetails",
            id: id
        },
        async: true,
        success: function (data, textStatus, jqXHR) {
            var sor = "";
            sor += "<h2>" + data.books[0].title + "</h2>";
            sor += "<ul>";
            sor += "<li>" + data.books[0].author + "</li>";
            sor += "<li>" + data.books[0].publisher + "</li>";
            sor += "<li>" + data.books[0].year + "</li>";
            sor += "</ul>";
            sor += "<h3>Könyv tartalma</h3><p>" + data.books[0].description;
            $("#tartalom").append(sor);
        },
        error: function (data, jqXHR, textStatus, errorThrown) {
            console.log(data);
        }
    });
}