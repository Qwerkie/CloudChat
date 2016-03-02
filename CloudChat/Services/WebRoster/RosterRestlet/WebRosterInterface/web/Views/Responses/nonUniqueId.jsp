<%-- 
    Document   : nonUniqueId
    Created on : Mar 3, 2015, 11:37:53 PM
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
                parent.adjustIframeHeight(document.getElementById("nonUniqueIdContent").scrollHeight);
                parent.adjustIframeWidth(document.getElementById("nonUniqueIdContent").scrollWidth);
            }
        </script>
    </head>
    <body onload="adjustIframe()">
        <div id="nonUniqueIdContent">
            <<h1 style='color:red'>Non-Unique Student ID</h1>
        </div>
    </body>
</html>
