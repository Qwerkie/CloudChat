<%-- 
    Document   : invalidTeam
    Created on : Mar 3, 2015, 11:15:53 PM
    Author     : Steven
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        <script>
            function adjustIframe() {
                parent.adjustIframeHeight(document.getElementById("invalidTeamContent").scrollHeight);
                parent.adjustIframeWidth(document.getElementById("invalidTeamContent").scrollWidth);
            }
        </script>
    </head>
    <body onload="adjustIframe()">
        <div id="invalidTeamContent">
            <h1 style='color:red'>Invalid Team Number</h1>
        </div>
    </body>
</html>
