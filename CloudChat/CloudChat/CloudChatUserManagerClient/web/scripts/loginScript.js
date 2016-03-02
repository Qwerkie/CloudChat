/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function logIn() {
    console.log($("#password").val());
    if ($("#userID").val().trim() !== "" && $("#password").val().trim() !== "" &&
            $("#password").val().trim().length > 6) {
        var userID = $("#userID").val().trim();
        var password = $("#password").val().trim();
        $.ajax({
            method: "POST",
            url: "http://127.0.0.1:8080/CloudChatUserManagerClient/CloudChatUserManagerServlet",
            data: {"action": "LogIn", "userID": userID, "password": password},
            success: function (data) {
                console.log(data);
                var result = JSON.parse(data);
                if (result.retVal == true) {
                    console.log(result);
                    sessionStorage.setItem("user", userID);
                    document.write("<h4>You are successfully logged in!</h4>");
                    // remove login and register buttons
                    var sframe = window.parent.document.getElementById("sframe");
                    $("#loginButton", sframe.contentDocument).fadeOut("slow");
                    $("#registerButton", sframe.contentDocument).fadeOut("slow");
                    setTimeout( function(){
                        fadeInChatOptions(sframe);
                    }, 600);

                }
                else {
                    $("#errorMessage").html("UserID or Password is not valid");
                }
            },
            error: function (err) {
                console.log(err);
                $("#errorMessage").html("UserID or Password is not valid");
            }
        });
    }
    else if ($("#userID").val().trim() === "") {
        $("#errorMessage").html("UserID cannot be blank");
    }
    else if ($("#password").val().trim().length <= 6) {
        $("#errorMessage").html("Password length must be greater than 6 characters");
    }
    return false;
}

