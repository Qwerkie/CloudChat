<%-- 
    Document   : roster
    Created on : Feb 21, 2015, 5:20:03 PM
    Author     : ctd5100
--%>

<%@page import="javax.servlet.jsp.tagext.BodyContent"%>
<%@page import="beans.Student"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="http://tablesorter.com/__jquery.tablesorter.min.js"></script>
        <script>

            
            $(document).ready(function(){
               $("#table").tablesorter();
                }
             );
            
        </script>
        <title>Roster</title>
        <%
            out.print("<style>");
            out.print("table{margin-left:15px}");
            out.print("table, th, td{border: 2px solid black;border-collapse: collapse;}");
            out.print("th{background-color: blue;color: white;}");
            out.print("th, td{padding:5px;}");
            out.print("td{font-size:1.1em}");
            out.print("</style>");
        %>
    </head>
    <body>
        <jsp:useBean id="roster" class="beans.Roster"/>
         <%  
            
           
                out.print("<h1>Class Roster</h1>");
                ArrayList<Student> rosterList = roster.getRosterList();
                out.print("<table id='table'><thead><tr><th>" + rosterList.get(0).getLastName()
                    + "</th><th> " + rosterList.get(0).getFirstName() +"</th><th>"
                        + rosterList.get(0).getPsuID()+"</th><th>"
                        + rosterList.get(0).getTeamNumber() + "</th></tr></thead><tbody>");
               
                for(int i = 1; i < rosterList.size(); i++){
                    out.print("<tr>");
                    out.print("<td>" + rosterList.get(i).getLastName() + "</td>");
                    out.print("<td>" + rosterList.get(i).getFirstName() + "</td>");
                    out.print("<td>" + rosterList.get(i).getPsuID()+ "</td>");
                    out.print("<td>" + rosterList.get(i).getTeamNumber()+ "</td>");
                    out.print("</tr>");
                }
                out.write("</tbody></table>");
            
            
            
         %>
         
        
    </body>
</html>
