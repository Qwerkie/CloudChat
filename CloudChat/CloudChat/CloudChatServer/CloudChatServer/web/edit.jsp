<!DOCTYPE HTML>
<html lang = "en">
    <head>
        <meta charset = "UTF-8" />
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <link rel="stylesheet" href="http://127.0.0.1:8080/CloudChatServer/css/serverStyle.css">
        <script src="http://127.0.0.1:8080/CloudChatServer/scripts/sharedScript.js"></script>
        <script>
            var message = $("#message").val();
            var messageID = $("#messageID").val();
            function passData() {
                $.ajax({
                    method: "POST",
                    url: "http://127.0.0.1:8888/CloudChat/navigate",
                    data: {"type": "Server", "action": "EditMessage", "message": message, "messageID": messageID},
                    success: function (data) {
                        var result = JSON.parse(data);
                        if (result.retVal == true) {
                            console.log(result);
                            document.innerHTML = "";
                            document.write(data);
                        }
                        else {
                            $("#errorMessage").html("Message has not been edited.");
                        }
                    },
                    error: function (err) {
                        console.log(err);
                        $("#errorMessage").html("Message has not been edited.");
                    }
                });
                return false;
            }
        </script>
    </head> 
    <body>
        <div id="pageContent">
            <h4>Please enter the timestamp of the message you would like to edit,<br> as well as the new message</h4>
            <div><span>Timestamp</span></div>
            <div>
                <input id="messageID" type="text"/>
            </div><br>
            <div><span>New message</span></div>
            <div>
                <input id="message" type="text"/>
            </div><br>
            <div>
                <button onclick="">Edit</button>
            </div>
            <br>
            <div>
                <button onclick="moveToPage('ChatHome');">Back</button>
            </div>
            <br>
        </div>
    </body>
</html>