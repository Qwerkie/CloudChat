<%-- 
    Document   : roster
    Created on : Feb 21, 2015, 5:20:03 PM
    Author     : ctd5100 and james and robbie
--%>

<%@page import="javax.servlet.jsp.tagext.BodyContent"%>
<%@page import="roster.Student"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="navigation.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="http://tablesorter.com/__jquery.tablesorter.min.js"></script>
        <script>


            $(document).ready(function () {

                
                $.get("http://localhost:8080/WebRosterRS/Roster/xml", function (data) {
                    var firstNames = $(data).find('firstName');
                    console.log(firstNames);
                    var lastNames = $(data).find('lastName');
                    var psuIDs = $(data).find('psuID');
                    var teamNumbers = $(data).find('teamNumber');
                    // var firstName = xmlData.getElementsByTagName("firstName");
                    //var lastName = xmlData.getElementsByTagName("lastName");
                    // var psuID = xmlData.getElementsByTagName("psuID");
                    // var teamNumber = xmlData.getElementsByTagName("teamNumber");

                    // styling 
                    
                    document.write("<fieldset style='margin-top: 15px'>");
                    document.write("<legend>Navigation</legend>");
                    document.write("<form action='/WebRosterRS/Roster/navigate' method='POST'>");
                    document.write("<input type='submit' name='button' value='Browse'</input>");
                    document.write("<input type='submit' name='button' value='Update'></input>");
                    document.write("<input type='submit' name='button' value='Add Student'></input>");
                    document.write("<input type='submit' name='button' value='Delete Student'></input>");
                    document.write("</form>");
                    document.write("</fieldset>");
                    
                    document.write("<style>");
                    document.write("table{margin-left:15px}");
                    document.write("table, th, td{border: 2px solid black;border-collapse: collapse;}");
                    document.write("th{background-color: blue;color: white;}");
                    document.write("th, td{padding:5px;}");
                    document.write("td{font-size:1.1em}")
                    document.write("</style>");
                    document.write("<h1 style='margin-top:20px'>Roster</h1>");
                    //generate content here
                    document.write("<table id='table'><thead><tr><th>" + psuIDs[0].innerHTML
                            + "</th><th> " + lastNames[0].innerHTML + "</th><th>"
                            + firstNames[0].innerHTML + "</th><th>"
                            + teamNumbers[0].innerHTML + "</th></tr></thead><tbody>");

                    for (var i = 1; i < psuIDs.length; i++) {
                        document.write("<tr>");
                        document.write("<td>" + psuIDs[i].innerHTML + "</td>");
                        document.write("<td>" + lastNames[i].innerHTML + "</td>");
                        document.write("<td>" + firstNames[i].innerHTML + "</td>");
                        document.write("<td>" + teamNumbers[i].innerHTML + "</td>");
                        document.write("</tr>");
                    }

                    document.write("</tbody></table>");
                    $("#table").tablesorter();
                });
            }
            );



        </script>    
    </head>
</html>
