<%-- 
    Document   : navigation
    Created on : Feb 28, 2015, 11:13:45 PM
    Author     : Charlie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <fieldset style="margin-top: 15px">
            <legend>Navigation</legend>
            <form action="roster" method="GET">
                <input type="submit" name="button" value="Browse"></input>
                <input type="submit" name="button" value="Update"></input>
                <input type="submit" name="button" value="Add Student"></input>
                <input type="submit" name="button" value="Delete Student"></input>
                <input type="submit" name="button" value="Add Team"></input>
                <input type="submit" name="button" value="Delete Team"></input>
            </form>
        </fieldset>
    </body>
</html>
