<%-- 
    Document   : success
    Created on : Mar 3, 2015, 2:46:40 PM
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
                parent.adjustIframeHeight(document.getElementById("successContent").scrollHeight);
                parent.adjustIframeWidth(document.getElementById("successContent").scrollWidth);
            }
        </script>
    </head>
    <body onload='adjustIframe()'>
        <div id='successContent'>
            <h1 style='color:green'>Successful Request</h1>
        </div>
    </body>
</html>
