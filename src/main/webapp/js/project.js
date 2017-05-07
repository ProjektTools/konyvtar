/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    console.log("itten");
    getCategories();


    $('#searchbutton').click(function () {
        var title = document.getElementById("searchtitle").value;
        var author = document.getElementById("searchauthor").value;
        var category_id = document.getElementById("searchcategory").value;
        if (title == "" && author == "" && category_id == 0) {            
            $('#talalatok').empty();
            getCategories();
        } else {
            search(title, author, category_id);
        }
    });
});

function search(title, author, category_id) {
    $.ajax({
        url: "AjaxServlet",
        type: "GET",
        dataType: 'json',
        data: {
            cmd: "search",
            title: title,
            author: author,
            category_id: category_id
        },
        async: true,
        success: function (data, textStatus, jqXHR) {
            console.log(data);
            $('#talalatok').empty();
            $('#kategorialista').empty();
            var beszurni = "";
            beszurni += "<h2>A keresési feltételeknek megfelelő találatok (" + data.sbooks.length + " db):</h2>";
            beszurni += "<div class=\"list-group\">";
            for (var j = 0; j < data.sbooks.length; j++) {
                beszurni += "<a href=\"./books/book.jsp?id=" + data.sbooks[j].id + "\" class=\"list-group-item\"> <h5 class=\"list-group-item-heading\">" + data.sbooks[j].title + "</h5> <p class=\"list-group-item-text\">" + data.sbooks[j].author + " (" + data.sbooks[j].year + ")</p> </a>";
            }
            beszurni += "</div>";
            $("#talalatok").append(beszurni);
            beszurni = "";
        },
        error: function (data, jqXHR, textStatus, errorThrown) {
            console.log(data);
        }
    });
}

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
            var beszurni = "";
            $('#kategorialista').empty();
            $('#searchcategory').empty();
            document.getElementById('searchcategory').options.add(new Option("-----", "0"));
            for (var i = 0; i < data.categories.length; i++) {
                beszurni += "<div class=\"col-lg-4\"><div class=\"panel panel-basic\"><div class=\"panel-heading\"><h3 class=\"panel-title\"><b>" + data.categories[i].name + "</b></h3></div><div class=\"panel-body\"><div class=\"list-group\">";
                var talalt = 0;
                for (var j = 0; j < data.books.length; j++) {
                    if (data.books[j].category_id === data.categories[i].id) {
                        talalt = talalt + 1;
                        if (talalt < 3) {
                            beszurni += "<a href=\"./books/book.jsp?id=" + data.books[j].id + "\" class=\"list-group-item\"><h5 class=\"list-group-item-heading\">" + data.books[j].title + "</h5> <p class=\"list-group-item-text\">" + data.books[j].author + " (" + data.books[j].year + ")</p></a>";
                        }
                    }
                }
                beszurni += "</a></div></div></div></div>";
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