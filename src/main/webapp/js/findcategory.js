/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    console.log(id);
    getCategoryDatas(id);
});

function getCategoryDatas(id) {
    $.ajax({
        url: "AjaxServlet",
        type: "GET",
        dataType: 'json',
        data: {
            cmd: "getCategoryDetails",
            category_id: id
        },
        async: true,
        success: function (data, textStatus, jqXHR) {
            $('#katlista').empty();
            var beszurni = "";
            beszurni += "<h2>A(z) "+data.sbooks[0].category_name +" kategóriába tartozó könyvek (" + data.sbooks.length + " db):</h2>";
            beszurni += "<div class=\"list-group\">";
            for (var j = 0; j < data.sbooks.length; j++) {
                beszurni += "<a href=\"./books/book.jsp?id=" + data.sbooks[j].id + "\" class=\"list-group-item\"> <h5 class=\"list-group-item-heading\">" + data.sbooks[j].title + "</h5> <p class=\"list-group-item-text\">" + data.sbooks[j].author + " (" + data.sbooks[j].year + ")</p> </a>";
            }
            beszurni += "</div>";
            $("#katlista").append(beszurni);
            beszurni = "";
        },
        error: function (data, jqXHR, textStatus, errorThrown) {
            console.log(data);
        }
    });
}

