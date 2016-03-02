<%-- 
    Document   : deleteStudent
    Created on : Mar 4, 2015, 11:06:03 AM
    Author     : spr5122
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        <script>
            //Change the size of the iframe in the parent document
            function adjustIframe() {
                parent.adjustIframeHeight(document.getElementById("deleteStudentContent").scrollHeight);
                parent.adjustIframeWidth(document.getElementById("deleteStudentContent").scrollWidth);
            }
            
            function sendRequest() {	
                //Grab parameter user is searching for
                var request = document.getElementById('studentDel').value;
		//Construct user query string
		var uQuery="studentId="+request;
                //Append to url to request
                var url="http://localhost:8282/restlet/removeStudent";
                
                //Create xmlhttprequest
                console.log("building xml http request");
		var xmlHttp;
		if (window.XMLHttpRequest) {xmlHttp = new XMLHttpRequest(); }
		else { xmlHttp = new ActiveXObject("Microsoft.XMLHTTP"); }
                
                //Specify method and url
                console.log("opening url with DELETE request");
		xmlHttp.open("POST", url);
                
                //Specify request body type - JSON
		xmlHttp.setRequestHeader("Content-Type", "x-www-form-urlencoded");
                
                //When response arrives...
                xmlHttp.onreadystatechange = function() {
                    //If successful...
                    if(xmlHttp.readyState === 4 && xmlHttp.status === 200) {
                        //Handle response
                        var htmlContent = xmlHttp.responseText;
                        var menuFrame = parent.document.getElementById("menuFrame");
                        console.log("Response has arrived: "+htmlContent);
                        menuFrame.src = "./Responses/success.jsp";
                    }
                };             
                
                //Send request
                console.log("sending request...");
                xmlHttp.send(uQuery);
            } 
        </script>        
    </head>
    <body onload="adjustIframe()">
        <div id="deleteStudentContent">
            <form id="deleteStudentMenu">
                <fieldset>
                    <legend>Delete Student</legend>
                    <label>Student ID: </label>
                    <input type="text" id="studentDel" name="studentDel"/>
                    <input type="button" onclick="sendRequest()" value="Delete"/>
                </fieldset>
            </form>
        </div>        
    </body>
</html>
