<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <link href="./Styles/pageStyle.css" rel="stylesheet" type="text/css"/>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    
    <script>
        // Initialize all sorting order parameters
        var tableBody;
        var ord1=-1, ord2=1, ord3=1, ord4=1;
        window.onload = function () {
            tableBody = document.getElementById("rosterTableBody");
            
            //Change the target of get requests in the navigation bar to MenuDiv
            document.getElementById("navigationForm").target = "menuFrame";
        };
        
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
        
        function tableSort(body, col, ord) {
            // Get all the table rows
            var tableRows = body.rows;
            var tableArray = new Array();
            // Iterate through all the table rows
            for (i=0; i<tableRows.length; i++) {
                // Get all the table cells
                var tableCells = tableRows[i].cells;
                tableArray[i] = new Array();
                // Iterate through the table cells
                for (j=0; j<tableCells.length; j++) {
                    // Store HTML values to an array
                    tableArray[i][j] = tableCells[j].innerHTML;
                }
            }
            // Sort through the array according to column, ascending/descending
            tableArray.sort( function (a, b) {
                if (col === 3) {
                    return (parseInt(a[col]) === parseInt(b[col])) ? 0 : ((parseInt(a[col]) > parseInt(b[col])) ? ord : -1*ord);
                } else {
                    return (a[col].toLowerCase() === b[col].toLowerCase()) ? 0 : ((a[col].toLowerCase() > b[col].toLowerCase()) ? ord : -1*ord);
                }
            });
            // Format the table with appropiate HTML tags
            for (i=0; i<tableRows.length; i++) {
                tableArray[i] = "<td>"+tableArray[i].join("</td><td>")+"</td>";
            }
            // Return the table with appropriate class tags and HTML tags
            body.innerHTML = "<tr class='body'>"+tableArray.join("</tr><tr class='body'>")+"</tr>";
        }
        
        function adjustIframeHeight(height) {
            document.getElementById("menuFrame").style.height = (parseInt(height) + parseInt("10")) + "px";
        }
        function adjustIframeWidth(width) {
            document.getElementById("menuFrame").style.width = (parseInt(width) + parseInt("10")) + "px";
        }
    </script>
    
</head>
<body>
    
    <div id="navigationDiv">
        <form id="navigationForm" method="get">
            <fieldset id="navigationBar">
                <legend> Navigation </legend>
                <input type="submit" id="browseButton" formaction="Options/browse.jsp" value="Browse"/>
                <input type="submit" id="updateButton" formaction="Options/update.jsp" value="Update"/>
                <input type="submit" id="addTeamButton" formaction="Options/addTeam.jsp" value="Add Team"/>
                <input type="submit" id="addStudentButton" formaction="Options/addStudent.jsp" value="Add Student"/>
                <input type="submit" id="deleteTeamButton" formaction="Options/deleteTeam.jsp" value="Delete Team"/>
                <input type="submit" id="deleteStudentButton" formaction="Options/deleteStudent.jsp" value="Delete Student"/>
            </fieldset>
        </form>
    </div>
    
    <div id="menuDiv" onload="init()">
        <iframe id="menuFrame" name="menuFrame" height="0px" width="0px" frameborder="0" scrolling="no"></iframe>
    </div>      
    
    <div id="tableDiv">
        <table id="rosterTable" class="tableStyle">
            <!-- Table Header -->
            <thead id="rosterTableHead">
                <tr id="rosterTableHeadRow" class="header">
                    <th id="lnHeader" class="header" onclick="tableSort(tableBody, 0, ord1); ord1*=-1; ord2=1; ord3=1; ord4=1;">Last Name</th>
                    <th id="fnHeader" class="header" onclick="tableSort(tableBody, 1, ord2); ord1=1; ord2*=-1; ord3=1; ord4=1;">First Name</th>
                    <th id="idHeader" class="header" onclick="tableSort(tableBody, 2, ord3); ord1=1; ord2=1; ord3*=-1; ord4=1;">Student ID</th>
                    <th id="tnHeader" class="header" onclick="tableSort(tableBody, 3, ord4); ord1=1; ord2=1; ord3=1; ord4*=-1;">Team Number</th>
                </tr>
            </thead>
            <!-- Table Body -->
            <tbody id="rosterTableBody" class="tableBody">
            </tbody>
        </table>
    </div>          
    
</body>
</html>
