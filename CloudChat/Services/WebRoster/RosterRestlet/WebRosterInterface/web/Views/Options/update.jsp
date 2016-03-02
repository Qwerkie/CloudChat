<%-- 
    Document   : update
    Created on : Mar 1, 2015, 2:16:30 AM
    Author     : Steven
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        <script>
            function init() {
                console.log("Entered update.jsp");
                parent.adjustIframeHeight(document.getElementById("update").scrollHeight);
                parent.adjustIframeWidth(document.getElementById("update").scrollWidth);
                
                //Url to request
                var url="http://localhost:8282/restlet/";
                
                //Create xmlhttprequest
		var xmlHttp;
		if (window.XMLHttpRequest) {xmlHttp = new XMLHttpRequest(); }
		else { xmlHttp = new ActiveXObject("Microsoft.XMLHTTP"); }
                
                //Specify method and url
		xmlHttp.open("GET", url);
                
                //Specify request body type - text/html
		xmlHttp.setRequestHeader("Content-Type", "text/html");
                
                //When response arrives...
                xmlHttp.onreadystatechange = function() {
                    //If successful...
                    if(xmlHttp.readyState === 4 && xmlHttp.status === 200) {
			//Handle response
                        var htmlContent = xmlHttp.responseText;
                        var tableBody = parent.document.getElementById("rosterTableBody");
                        tableBody.innerHTML = htmlContent;
                    }
		}                
                
                //Send request
                xmlHttp.send();
            }
        </script>
    </head>
    <body id="updateContent" onload='init()'>
        <div id="update"/>
    </body
</html>
