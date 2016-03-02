<%-- 
    Document   : browse
    Created on : Feb 27, 2015, 2:08:47 PM
    Author     : spr5122
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        
        <script>
            //Change the size of the iframe in the parent document
            function adjustIframe() {
                parent.adjustIframeHeight(document.getElementById("browseContent").scrollHeight);
                parent.adjustIframeWidth(document.getElementById("browseContent").scrollWidth);
            }
            
            function sendRequest() {
		// Check button for selected radio button                
		var buttons = document.getElementsByName("paramtype");
		for(i=0;i<buttons.length;i++) {
			if(buttons[i].checked) {
				var requestType =buttons[i].getAttribute('id');
			}
		}
                
                //Grab parameter user is searching for
                var requestValue = document.getElementById('param').value;
                
		//Construct user query string
		var uQuery="queryType="+requestType+"&queryValue="+requestValue;
                var url="http://localhost:8282/restlet/browseRoster";
                
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
                        var tableBody = parent.document.getElementById("rosterTableBody");
                        tableBody.innerHTML = htmlContent;
                    }
		};           
                
                //Send request
                console.log("sending query {"+uQuery+"} to "+url);
                xmlHttp.send(uQuery);
            } 
        </script>        
    </head>
    <body onload = "adjustIframe()">
        <div id="browseContent">
            <form id="browseMenu">
                <fieldset>
                    <legend>Browse Table</legend>
                    <input type='radio' name='paramtype' id='firstName' value='firstName'>
                        <label>First Name</label></input><br>
                    <input type='radio' name='paramtype' id='lastName' value='lastName'>
                        <label>Last Name</label></input><br>
                    <input type='radio' name='paramtype' id='entryId' value='entryId'>
                        <label>Student ID</label></input><br>
                    <input type='radio' name='paramtype' id='teamNumber' value='teamNumber'>
                        <label>Team Number</label></input><br>
                    <input type='text' name='param' id='param'></input>
                    <input type='button' onclick='sendRequest()' value='Search'/>
                </fieldset>
            </form>
        </div>
    </body>
</html>
