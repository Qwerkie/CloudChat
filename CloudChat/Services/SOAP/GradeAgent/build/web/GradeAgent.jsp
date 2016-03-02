<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <title>Grade Agent</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script>
        
        function validateForm(){
            $("#returnValSpan").empty();
            var radioVal = $("input:radio[name=unit]:checked").val(); // gets value of the checked radiobutton
            var inputVal = $("#inputValue").val();
            if(radioVal == null || inputVal == null){
                alert("Error, you must specify the scale to convert from and the scale to convert to.");
                return false;
            }
            else{
                if(radioVal == "hundred-scale"){
                    if(inputVal >= 0 && inputVal <= 100){
                        return true;
                    }
                }
                else if (radioVal == "seven-scale"){
                    if(inputVal >= 4 && inputVal <= 7){
                        return true;
                    }
                }
                else{
                    switch(inputVal){
                        case "A":
                            return true;
                        case "A-":
                            return true;
                        case "B+":
                            return true;
                        case "B":
                            return true;
                        case "B-":
                            return true;
                        case "C+":
                            return true;
                        case "C":
                            return true;
                        case "D":
                            return true;
                        case "F":
                            return true;
                        
                    }
                }
            }
            alert("Error, incorrect input.");
            return false;
        }
        
    </script>
</head>
  <body>
      <h2><font face="courier" size="10">Grade Agent</font></h2>
      <font face="courier" size="4">
      <form action="gradeAgentServlet" method="POST" onsubmit="return validateForm();">
        <div>
                <label>Grade:&emsp;</label>
                <input id="inputValue" type="text" size="11" name="grade" placeholder="Grade to convert"/>
        </div>
        <div>
                <br><label>Scale to Convert From</label><br>
                <input type="radio" name="unit" value="hundred-scale">Hundred Scale
                <input type="radio" name="unit" value="seven-scale">Seven Scale
                <input type="radio" name="unit" value="letter-scale">Letter Scale
        </div>
        <div>
                <br><label>Scale to Convert To</label><br>
                <input type="radio" name="choice" value="hundred-scale">Hundred Scale
                <input type="radio" name="choice" value="seven-scale">Seven Scale
                <input type="radio" name="choice" value="letter-scale">Letter Scale
        </div>
        <div>
                <br><input type="submit" name="button" value="Submit">
        </div>
    </form>
    
    <%
        
        String choice=(String)request.getAttribute("choice");
    %> 
        <c:if test="${choice=='seven-scale'}">The grade in seven scale is:</c:if>
        <c:if test="${choice=='hundred-scale'}">The grade in hundred scale is:</c:if>
        <c:if test="${choice=='letter-scale'}">The grade in letter scale is:</c:if>
        
    <%
        String retVal=(String)request.getAttribute("retVal");

        if(retVal!=null && !retVal.equals("ERROR")){
            out.print(" "+retVal);   
        }
        else if(retVal != null && retVal.equals("ERROR")){
            out.print("ERRRORRRRR");
        }
    %>
    </font>    
  </body>
</html>
