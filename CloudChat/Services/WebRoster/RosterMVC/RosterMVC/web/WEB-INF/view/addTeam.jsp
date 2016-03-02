<%@include file="navigation.jsp" %>
<!DOCTYPE HTML>
<html lang = "en">
  <head>
    <meta charset = "UTF-8" />
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
  </head> 
  <body>
    <div>
    <h2>Team Addition</h2>
            <label>Team Number:</label>
            <form action="roster" method="POST">
                <input type="text" name="teamNumber" placeholder="Team Number to be added"/>
                <input type="submit" name="button" value="Add Team">
            </form>
    </div>
  </body>
</html>