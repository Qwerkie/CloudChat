<%-- 
    Document   : updateStudent
    Created on : Mar 2, 2015, 4:38:25 PM
    Author     : ctd5100
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="navigation.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Update Student</h1>
        <p>Enter in the student's PSU ID and the corresponding data to be changed</p>
        <form action="roster" method="POST">
        <div>
                <label>PSU ID:&emsp;&emsp;&emsp;&nbsp;</label>
                <input type="text" name="psuID" placeholder="Student's PSU ID"/>
        </div>
        <div>
                <label class="bottom">First Name:&emsp;&emsp;</label>
                <input type="text" name="firstName" placeholder="Student's first name"/>
        </div>
        <div>
                <label>Last Name:&emsp;&emsp;</label>
                <input type="text" name="lastName" placeholder="Student's last name"/>
        </div>
        <div>
                <label>Team Number:&nbsp;&nbsp;&nbsp;</label>
                <input type="text" name="teamNumber" placeholder="Student's team number"/>
        </div>
        <div>
                <input type="submit" name="button" value="Update">
        </div>
    </form>
    </body>
</html>
