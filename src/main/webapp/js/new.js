$(document).ready(function () {
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
            var beszurni = "";
            $('#searchcategory').empty();
            document.getElementById('searchcategory').options.add(new Option("-----", "0"));
            for (var i = 0; i < data.categories.length; i++) {
                document.getElementById('searchcategory').options.add(new Option(data.categories[i].name, data.categories[i].id));
            }
        },
        error: function (data, jqXHR, textStatus, errorThrown) {
            console.log(data);
        }
    })
}


