<%-- 
    Document   : chat
    Created on : Apr 22, 2015, 4:58:11 PM
    Author     : jts5507
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <link rel="stylesheet" href="http://127.0.0.1:8080/CloudChatServer/css/serverStyle.css">
        <script type="text/javascript" src="http://127.0.0.1:8080/CloudChatServer/scripts/googiespell/AJS.js"></script>
        <script type="text/javascript" src="http://127.0.0.1:8080/CloudChatServer/scripts/googiespell/googiespell.js"></script>
        <script type="text/javascript" src="http://127.0.0.1:8080/CloudChatServer/scripts/googiespell/cookiesupport.js"></script>
        <link href="http://127.0.0.1:8080/CloudChatServer/scripts/googiespell/googiespell.css" rel="stylesheet" type="text/css" />
        <script src="http://127.0.0.1:8080/CloudChatServer/scripts/sharedScript.js"></script>
        <script>

            function pullMessages() {
                var selection = $("#selection").val();
                console.log("pulling messages");
                $.ajax({
                    method: "POST",
                    url: "http://127.0.0.1:8888/CloudChat/navigate",
                    data: {type: "Server", action: "getCategory", category: selection},
                    success: function (data) {
                        console.log("success");
                        
                        var result=JSON.parse(data);
                        var allMessages="";
                        for(var i=0; i<result.length;i++)
                        {
                            allMessages+=result[i].messageID + "  \t"+result[i].userID +": "+ result[i].message+"\n";
                        }
                       $("#box").val(allMessages);
                       $("#box").scrollTop($("#box")[0].scrollHeight);
                    },
                    error: function (err) {
                        console.log(err);
                    }
                });
            }
            function pullAllMessages() {
        
                console.log("pulling messages");
                $.ajax({
                    method: "POST",
                    url: "http://127.0.0.1:8888/CloudChat/navigate",
                    data: {type: "Server", action: "getAll"},
                    success: function (data) {
                        console.log("success");
                        
                        var result=JSON.parse(data);
                        var allMessages="";
                        for(var i=0; i<result.length;i++)
                        {
                            allMessages+=result[i].messageID + "  \t"+result[i].userID +": "+ result[i].message+"\n";
                        }
                       $("#box").val(allMessages);
                       $("#box").scrollTop($("#box")[0].scrollHeight);
                    },
                    error: function (err) {
                        console.log(err);
                    }
                });
            }
            
            function sendMessage() {
                var selection = $("#selection").val();
                var message = $("#message").val();
                var userID = sessionStorage.user;
                
                console.log("sending message");
                console.log(userID);
                $.ajax({
                    method: "POST",
                    url: "http://127.0.0.1:8888/CloudChat/navigate",
                    data: {type: "Server", action: "PostMessage", category: selection, message: message, userID: userID},
                    success: function (data) {
                        console.log(data);
                        $("#message").val("");
                        pullMessages();
                    },
                    error: function (err) {
                        console.log(err);
                    }
                });
            }
        </script>
    </head> 
    <body>
        <div id="pageContent">
            <div id = "messageContent">
                <h2>Cloud Chat Message Board</h2>
                <br>
                <div>
                    <select id="selection" value="category" onchanged="pullMessages();" >
                        <option selected disabled>Choose a message category</option>
                        <option value="school">School</option>
                        <option value="sports">Sports</option>
                        <option value="help">Help</option>
                        <option value="misc">Miscellaneous</option>
                    </select>
                </div>
                <br>
                <div>
                    <textarea id="box" readonly style="resize: none" cols=90 rows= 14></textarea>
                </div>
                <div>
                    <textarea id="message" value="message" style="resize: none" cols=90 rows= 4 placeholder="Write your message here"></textarea>
                </div>
                <script>
                    var googie1 = new GoogieSpell("http://127.0.0.1:8080/CloudChatServer/scripts/googiespell/", "http://127.0.0.1:8888/googiespell?lang=");
                    googie1.decorateTextarea("message");
                    
                </script>
                <br>


                <div><button onclick="sendMessage();">Send Message</button></div>
                <br>
                <div>
                    <button onclick="pullMessages();">View Category</button>
                    <button onclick="pullAllMessages();">View All Messages</button>
                    <button onclick="moveToPage('Edit');">Edit Message</button>
                    <button onclick="moveToPage('Delete')">Delete Message</button>
                </div>
            </div>
            <div id="currentUsers" align="left">
                <h2>Users</h2>
                <ul>
                    <li>Hi James </li>
                </ul>
            </div>
        </div>
    </body>
</html>