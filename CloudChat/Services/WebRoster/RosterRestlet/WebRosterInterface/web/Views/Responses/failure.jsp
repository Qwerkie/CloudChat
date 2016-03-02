<%-- 
    Document   : failure
    Created on : Mar 3, 2015, 2:52:21 PM
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
                parent.adjustIframeHeight(document.getElementById("failureContent").scrollHeight);
                parent.adjustIframeWidth(document.getElementById("failureContent").scrollWidth);
            }
        </script>
    </head>
    <body onload='adjustIframe()'>
        <div id='failureContent'>
            <h1 style='color:red'>Unsuccessful Request</h1>
        </div>
    </body>
</html>
