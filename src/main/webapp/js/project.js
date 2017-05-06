/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    console.log("itten");
    getCategories();
});


function getCategories() {
    $.ajax({
        url: "AjaxServlet",
        type: "GET",
        dataType: 'json',
        data: {
            cmd: "getCategories",
            cmd2: "getBooks"
        },
        async: true,
        success: function (data, textStatus, jqXHR) {
            document.getElementById('searchcategory').options.add(new Option("-----", "0"));
            var beszurni = "";
            for (var i = 0; i < data.categories.length; i++) {
                beszurni += "<div class=\"col-lg-4\"><div class=\"panel panel-basic\"><div class=\"panel-heading\"><h3 class=\"panel-title\"><b>" + data.categories[i].name + "</b></h3></div><div class=\"panel-body\"><div class=\"list-group\">";
                var talalt = 0;
                for (var j = 0; j < data.books.length; j++) {
                    if (data.books[j].category_id === data.categories[i].id) {
                        talalt = talalt + 1;
                        if (talalt < 3) {
                            beszurni += "<a href=\"./books/book.jsp?id="+data.books[j].id+"\" class=\"list-group-item\"><h5 class=\"list-group-item-heading\">" + data.books[j].title + "</h5> <p class=\"list-group-item-text\">"+data.books[j].author+" ("+data.books[j].year+")</p></a>";
                        }
                    }
                }
                beszurni += "</a></div></div></div></div>";
                console.log(beszurni);
                console.log("....")
                $("#kategorialista").append(beszurni);
                beszurni = "";
                document.getElementById('searchcategory').options.add(new Option(data.categories[i].name, data.categories[i].id));
            }
        },
        error: function (data, jqXHR, textStatus, errorThrown) {
            console.log(data);
        }
    })
}