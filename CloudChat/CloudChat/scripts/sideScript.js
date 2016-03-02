/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// variables for balking calls
var registerPressed, loginPressed, startChatPressed, logoutPressed;
var cframe;
window.onload = function () {
    cframe = window.parent.document.getElementById("cframe");
    registerPressed = false;
    loginPressed = false;
    startChatPressed = false;
    logoutPressed = false;
    $("#cloudChatBlock").hide();
    $("#cloudChatBlock").fadeIn("slow");
    $("#startChat").hide();
    $("#userIDTag").hide();
    console.log(sessionStorage.user);
    if (sessionStorage.user !== "null" && sessionStorage.user !== undefined) { // sessionStorage likes to store things as strings for some reason...
        $("#userIDTag").html("UserID: " + sessionStorage.user);
        $("#userIDTag").fadeIn("slow");
        $("#loginButton").hide();
        $("#registerButton").hide();
        $("#logOutButton").show();
        $("#startChat").show();
        console.log("user logged on already");
    }
    else {
        console.log("user not logged in yet");
        $("#logoutButton").hide();
    }
}
// ajax all day
function loadRegisterPage() {
    if(registerPressed == true)
        return;
    registerPressed = true;
    $.post("/CloudChat/navigate", {type : "GetPage", page : "Register"} ,function( data ){
        $("#pageContent", cframe.contentDocument).slideUp("slow");
        setTimeout(function(){
            cframe.contentDocument.body.innerHTML = "";
            cframe.contentDocument.write(data);
            $("#pageContent", cframe.contentDocument).hide();
            $("#pageContent", cframe.contentDocument).slideDown("slow");
            registerPressed = false;
        }, 700);

    });
}
function loadLoginPage() {
    if (loginPressed == true)
        return;
    loginPressed = true;
    console.log("Got here");
    $.post("/CloudChat/navigate", {type : "GetPage", page : "LogIn"} ,function( data ){
        $("#pageContent", cframe.contentDocument).slideUp("slow");
        setTimeout(function() {
            cframe.contentDocument.body.innerHTML = "";
            cframe.contentDocument.write(data);
            $("#pageContent", cframe.contentDocument).hide();
            $("#pageContent", cframe.contentDocument).slideDown("slow");
            loginPressed = false;
        }, 700);

    });
}
function loadChatPage() {
    if(startChatPressed == true)
        return;
    startChatPressed = true;
    $.post("/CloudChat/navigate", {type : "GetPage", page : "ChatHome"} ,function( data ){
        $("#pageContent", cframe.contentDocument).slideUp("slow");
        setTimeout(function() {
            cframe.contentDocument.body.innerHTML = "";
            cframe.contentDocument.write(data);
            $("#pageContent", cframe.contentDocument).hide();
            $("#pageContent", cframe.contentDocument).slideDown("slow");
            startChatPressed = false;
        }, 1000);

    });
}

function logout() {
    if(logoutPressed == true)
        return;
    logoutPressed = true;
    if (sessionStorage.user != null) {
        $.post("/CloudChat/navigate", {type : "UserManager", action : "LogOut"} ,function( data ){
            $.post("/CloudChat/navigate", {type : "GetPage", page : "LogOut"}, function( data2 ){
                var result = JSON.parse(data);
                if (result.retVal == true) {
                    $("#pageContent", cframe.contentDocument).slideUp("slow");
                    sessionStorage.user = null;
                    fadeOutChatOptions();
                    setTimeout(function() {
                        cframe.contentDocument.body.innerHTML = "";
                        cframe.contentDocument.write(data2);
                        $("#pageContent", cframe.contentDocument).hide();
                        $("#pageContent", cframe.contentDocument).slideDown("slow");
                        fadeInLoginOptions();
                        logoutPressed = false;
                    }, 700);
                }
            });

        });
    }
}


function fadeOutChatOptions() {
    $("#startChat").fadeOut("fast");
    $("#logoutButton").fadeOut("fast");
    $("#userIDTag").fadeOut("fast");
}
function fadeInLoginOptions() {
    $("#loginButton").fadeIn("slow");
    $("#registerButton").fadeIn("slow");
}