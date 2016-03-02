/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function register() {
    var userID = $("#userID").val().trim();
    var password = $("#password").val().trim();
    if (userID !== "" && password === $("#verifyPassword").val().trim()
            && password.length > 6) {
        $.ajax({
            method: "POST",
            url: "http://127.0.0.1:8080/CloudChatUserManagerClient/CloudChatUserManagerServlet",
            data: {"action": "AddUser", "userID": userID, "password": password},
            success: function (data) {
                var result = JSON.parse(data);
                if (result.retVal === true) {
                    console.log(result);
                    sessionStorage.setItem("user", userID);
                    document.write("<h4>You are successfully registered and logged in!</h4>");
                    // remove login and register buttons
                    var sframe = window.parent.document.getElementById("sframe");
                    $("#loginButton", sframe.contentDocument).fadeOut("slow");
                    $("#registerButton", sframe.contentDocument).fadeOut("slow");
                    setTimeout( function(){fadeInChatOptions(sframe);}, 600);
                }

            },
            error: function (err) {
                console.log(err);
            }
        });
    }
    else if ($("#userID").val().trim() === "") {
        $("#errorMessage").html("UserID cannot be blank");
    }
    else if ($("#password").val().trim() !== $("#verifyPassword").val().trim()) {
        $("#errorMessage").html("Passwords do not match");
    }
    else if ($("#password").val().trim().length <= 6) {
        $("#errorMessage").html("Password length must be greater than 6 characters");
    }
    return false;
}