<%-- 
    Document   : index
    Created on : Mar 27, 2015, 2:04:55 PM
    Author     : bjr5336
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="GET" action="roster-action">
            <legend>Sample Form</legend>
            <input type="hidden" name="requestType" value="browseRoster"/>
            <input type="radio" name="inputType" value="firstName">
                <label>First Name</label>
            <input type="radio" name="inputType" value="firstName">
                <label>First Name</label>
            <input type="radio" name="inputType" value="firstName">
                <label>First Name</label>
            <input type="radio" name="inputType" value="firstName">
                <label>Team Number</label>
        </form>
    </body>
</html>
