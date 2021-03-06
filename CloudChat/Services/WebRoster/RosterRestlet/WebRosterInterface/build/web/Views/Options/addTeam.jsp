<%-- 
    Document   : addTeam
    Created on : Mar 2, 2015, 1:07:31 PM
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
                parent.adjustIframeHeight(document.getElementById("addTeamContent").scrollHeight);
                parent.adjustIframeWidth(document.getElementById("addTeamContent").scrollWidth);
                
                document.getElementById("addTeamMenu").target = "menuFrame";
            }    
            
            function sendRequest() {
                //Url to request
                var url="http://localhost:8282/restlet/createTeam";
                
                //Grab user input
                var fname1 = document.getElementById("firstName1").value;
                var lname1 = document.getElementById("lastName1").value;
                var stuid1 = document.getElementById("entryId1").value;
                var fname2 = document.getElementById("firstName2").value;
                var lname2 = document.getElementById("lastName2").value;
                var stuid2 = document.getElementById("entryId2").value;
                var fname3 = document.getElementById("firstName3").value;
                var lname3 = document.getElementById("lastName3").value;
                var stuid3 = document.getElementById("entryId3").value;
                var teamn = document.getElementById("teamNumber").value;
                
                //If any of the student ids are the same...
                if((stuid1 === stuid2) || (stuid1 === stuid3) || (stuid2 === stuid3)) {
                    var menuFrame = parent.document.getElementById("menuFrame");
                    menuFrame.src = "./Responses/nonUniqueId.jsp";
                }
                else {
                    //If any of the entries are empty...
                    if(((fname1 ==='')||(lname1 === '')||(stuid1 === '')||
                            (fname2 === '')||(lname2 === '')||(stuid2 === '')
                            ||(fname3 === '')||(lname3 === '')||(stuid3 ===''))||(teamn==='')) {

                        //Load unsuccessful submission page
                        var menuFrame = parent.document.getElementById("menuFrame");
                        menuFrame.src = "./Responses/failure.jsp";
                    }
                    //All entries were filled out correctly
                    else {

                        //Construct data string
                        var queryData = 'firstName1='+fname1+"&lastName1="+lname1+'&studentId1='+stuid1+ 
                                '&firstName2='+fname2+"&lastName2="+lname2+'&studentId2='+stuid2+
                                '&firstName3='+fname3+"&lastName3="+lname3+'&studentId3='+stuid3+'&teamNumber='+teamn;

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
                        xmlHttp.send(queryData);
                    }
                }
            }
        </script>
    </head>
    <body onload="adjustIframe()">
        <div id="addTeamContent">
            <form id="addTeamMenu">
                <fieldset>
                    <fieldset>
                        <legend>Team Member #1</legend>
                        <label>First Name: </label>
                        <input type="text" id="firstName1" name="firstName1">
                        <label>Last Name: </label>
                        <input type="text" id="lastName1" name="lastName1">
                        <label>Student ID: </label>
                        <input type="text" id="entryId1" name="entryId1">
                    </fieldset>
                    
                    <fieldset>
                        <legend>Team Member #2</legend>
                        <label>First Name: </label>
                        <input type="text" id="firstName2" name="firstName2">
                        <label>Last Name: </label>
                        <input type="text" id="lastName2" name="lastName2">
                        <label>Student ID: </label>
                        <input type="text" id="entryId2" name="entryId2">
                    </fieldset>
                    
                    <fieldset>
                        <legend>Team Member #3</legend>
                        <label>First Name: </label>
                        <input type="text" id="firstName3" name="firstName3">
                        <label>Last Name: </label>
                        <input type="text" id="lastName3" name="lastName3">
                        <label>Student ID: </label>
                        <input type="text" id="entryId3" name="entryId3">
                    </fieldset>
                    <fieldset>
                        <legend>Team Number</legend>
                        <label>Team Number: </label>
                        <input type="text" id="teamNumber" name="teamNumber">
                    </fieldset>
                    <legend>Add Team</legend>
                    <input type='button' onclick='sendRequest()' value='Create'/>
                </fieldset>
            </form>
        </div>
        <div id="emptyFrame"/>
    </body>
</html>
