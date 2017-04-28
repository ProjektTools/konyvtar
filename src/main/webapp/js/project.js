/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
    getCategories();
});

function getCategories(){
    $.ajax({
        url: "AjaxServlet",
        type:"GET",
        dataType: 'json',
        data:{
            cmd:"getCategories"
        },
        async: true,
        success: function(data, textStatus, jqXHR){
            document.getElementById('searchcategory').options.add(new Option("-----", "0"));
            for(var i=0;i<data.categories.length; i++){
                $("#kategorialista").append("<div class=\"col-lg-4\"><div class=\"panel panel-basic\"><div class=\"panel-heading\"><h3 class=\"panel-title\"><b>"+data.categories[i].name+"</b></h3><div class=\"panel-body\"><div class=\"list-group\"><a href=\"#\" class=\"list-group-item\"><h4 class=\"list-group-item-heading\">Könyv címe</h4> <p class=\"list-group-item-text\">Szerző (megjelenés éve)</p></a></div></div></div></div>");
                document.getElementById('searchcategory').options.add(new Option(data.categories[i].name,data.categories[i].id));
            }
        },
        error: function(data, jqXHR, textStatus, errorThrown){
            console.log(data);
        }
    })
}