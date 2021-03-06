<%-- 
    Document   : addStudent
    Created on : Mar 3, 2015, 12:02:55 PM
    Author     : Steven
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
                parent.adjustIframeHeight(document.getElementById("addStudentContent").scrollHeight);
                parent.adjustIframeWidth(document.getElementById("addStudentContent").scrollWidth);
            }
            
            function sendRequest() {
                //Url to request
                var url="http://localhost:8282/restlet/createStudent";
                
                //Grab user input
                var fname = document.getElementById("firstName").value;
                var lname = document.getElementById("lastName").value;
                var stuid = document.getElementById("entryId").value;
                var teamNumber = document.getElementById("teamNumber").value;
                
                //If any of the entries are empty...
                if((fname ==='')||(lname === '')||(stuid === '')||(teamNumber === '')) {
                    console.log("Blank entry");
                    
                    //Load unsuccessful submission page
                    var menuFrame = parent.document.getElementById("menuFrame");
                    menuFrame.src = "./Responses/failure.jsp";
                }
                else {

                    //Construct data string
                    var queryData = 'firstName='+fname+"&lastName="+lname+'&studentId='+stuid+"&teamNumber="+teamNumber;

                    //Create xmlhttprequest
                    var xmlHttp;
                    if (window.XMLHttpRequest) {xmlHttp = new XMLHttpRequest(); }
                    else { xmlHttp = new ActiveXObject("Microsoft.XMLHTTP"); }

                    //Specify method and url
                    xmlHttp.open("POST", url);

                    //Specify request body type - text/html
                    xmlHttp.setRequestHeader("Content-Type", "x-www-form-urlencoded");

                    //When response arrives...
                    xmlHttp.onreadystatechange = function() {
                        //If successful...
                        if(xmlHttp.readyState === 4 && xmlHttp.status === 200) {
                            //Handle response
                            var menuFrame = parent.document.getElementById("menuFrame");
                            menuFrame.src = "./Responses/success.jsp";
                        }
                    };
                    console.log("Sending QUERY DATA");
                    xmlHttp.send(queryData);
                }
                    
            }
        </script>
    </head>
    <body onload="adjustIframe()">
        <div id="addStudentContent">
            <form id="addStudentMenu">
                <fieldset>
                    <legend>Add Student</legend>
                    <label>First Name: </label>
                    <input type="text" id="firstName" name="firstName"/>
                    <label>Last Name: </label>
                    <input type="text" id="lastName" name="lastName"/>
                    <label>Student ID: </label>
                    <input type="text" id="entryId" name="entryId"/>
                    <lable>Team Number: </label>
                    <input type="text" id="teamNumber" name="teamNumber"/>
                    <input type='button' onclick='sendRequest()' value='Add'/>
                </fieldset>
            </form>
        </div>
    </body>
</html>
