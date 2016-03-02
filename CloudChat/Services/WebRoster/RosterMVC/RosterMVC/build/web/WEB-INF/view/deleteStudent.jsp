<%@include file="navigation.jsp" %>
<!DOCTYPE HTML>
<html lang = "en">
  <head>
    <meta charset = "UTF-8" />
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
  </head> 
  <body>
    <div>
    <h2>Student Deletion</h2>
            <label>PSU ID:</label>
            <form action="roster" method="POST">
                <input type="text" name="psuID" placeholder="PSU ID of student"/>
                <input type="submit" name="button" value="Delete Student"/>
            </form>
    </div>
  </body>
</html>