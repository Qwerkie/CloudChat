<%-- 
    Document   : delete
    Created on : Apr 25, 2015, 7:39:21 PM
    Author     : jts5507
--%>

<!DOCTYPE HTML>
<html lang = "en">
    <head>
        <meta charset = "UTF-8" />
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <link rel="stylesheet" href="http://127.0.0.1:8080/CloudChatServer/css/serverStyle.css">
        <script src="http://127.0.0.1:8080/CloudChatServer/scripts/sharedScript.js"></script>
        <script>
            var messageID = $("#messageID").val();
            function passData() {
                $.ajax({
                    method: "POST",
                    url: "http://127.0.0.1:8888/CloudChat/navigate",
                    data: {"type": "Server", "action": "RemoveMessage", "messageID": messageID},
                    success: function (data) {
                        var result = JSON.parse(data);
                        if (result.retVal == true) {
                            console.log(result);
                            sessionStorage.setItem("user", userID);
                            document.body.innerHTML = "";
                            document.write(data);
                        }
                        else {
                            $("#errorMessage").html("Message has not been deleted.");
                        }
                    },
                    error: function (err) {
                        console.log(err);
                        $("#errorMessage").html("Message has not been deleted.");
                    }
                });
                return false;
            }
        </script>
    </head> 
    <body>
        <div id="pageContent">
            <h4>Please enter the timestamp of the message you would like to delete</h4><br>
            <h4>Timestamp</h4>
            <div>
                <input type="text" id="messageID"></textarea>
            </div><br>
            <div><button>Delete</button></div>
            <br>
            <div><button onclick="moveToPage('ChatHome');">Back</button></div>
            <br>
        </div>
    </body>
</html>
