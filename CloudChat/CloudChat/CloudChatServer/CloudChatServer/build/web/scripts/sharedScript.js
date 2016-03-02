/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function moveToPage(page) {
    $.ajax({
        method: "POST",
        url: "http://127.0.0.1:8888/CloudChat/navigate",
        data: {type: "GetPage", page: page},
        success: function (data) {
            $("#pageContent").slideUp("slow");
            console.log(data);
            setTimeout(function () {
                document.body.innerHTML = "";
                document.write(data);
                $("#pageContent").hide();
                $("#pageContent").slideDown("slow");
            }, 700);
        }
    });
}