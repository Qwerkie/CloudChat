<%-- 
    Document   : empty
    Created on : Feb 28, 2015, 3:48:22 PM
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
                parent.adjustIframeHeight(document.getElementById("empty").scrollHeight);
                parent.adjustIframeWidth(document.getElementById("empty").scrollWidth);
            }
        </script>
    </head>
    <body onload = "adjustIframe()">
        <div id="empty"/>
    </body>
</html>
