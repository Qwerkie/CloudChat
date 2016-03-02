<%-- 
    Document   : deleteTeam
    Created on : Mar 4, 2015, 10:29:38 AM
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
                parent.adjustIframeHeight(document.getElementById("deleteTeamContent").scrollHeight);
                parent.adjustIframeWidth(document.getElementById("deleteTeamContent").scrollWidth);
            }
            
            function sendRequest() {
                var request;	
                var url;
                
                //Grab parameter user is searching for
                request = document.getElementById('teamNumberDel').value;
                
		//Construct user query string
		uQuery="teamNumber="+request;
                //Append to url to request
                url="http://localhost:8282/restlet/removeTeam";
                
                //Create xmlhttprequest
		var xmlHttp;
		if (window.XMLHttpRequest) {xmlHttp = new XMLHttpRequest(); }
		else { xmlHttp = new ActiveXObject("Microsoft.XMLHTTP"); }
                
                //Specify method and url
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
                        if(htmlContent === "successful") {
                            menuFrame.src = "success.jsp";
                        }
                        else if(htmlContent === "teamNotFound") {
                            menuFrame.src = "failure.jsp";
                        }
                    }
                };                
                //Send request
                xmlHttp.send(uQuery);
            } 
        </script>
    </head>
    <body onload="adjustIframe()">
        <div id="deleteTeamContent">
            <form id="deleteTeamMenu">
                <fieldset>
                    <legend>Delete Team</legend>
                    <label>Team Number: </label>
                    <input type="text" id="teamNumberDel" name="teamNumberDel"/>
                    <input type="button" onclick="sendRequest()" value="Delete"/>
                </fieldset>
            </form>
        </div>        
    </body>
</html>
