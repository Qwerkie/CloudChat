<%@include file="navigation.jsp" %>
<!DOCTYPE HTML>
<html lang = "en">
  <head>
    <meta charset = "UTF-8" />
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
  </head> 
  <body>
    <h2>Student Addition</h2>
    <form action="/WebRosterRS/Roster/create" method="POST">
        <div>
                <label class="bottom">First Name:&emsp;&emsp;</label>
                <input type="text" name="firstName" placeholder="Student's first name"/>
        </div>
        <div>
                <label>Last Name:&emsp;&emsp;</label>
                <input type="text" name="lastName" placeholder="Student's last name"/>
        </div>
        <div>
                <label>PSU ID:&emsp;&emsp;&emsp;&nbsp;</label>
                <input type="text" name="psuID" placeholder="Student's PSU ID"/>
        </div>
        <div>
                <label>Team Number:&nbsp;&nbsp;&nbsp;</label>
                <input type="text" name="teamNumber" placeholder="Student's team number"/>
        </div>
        <div>
                <input type="Submit" name="button" value="Add Student">
        </div>
    </form>
  </body>
</html>