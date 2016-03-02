/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import clientSOAP.UserManagerService;
import clientSOAP.UserManagerServiceImplService;
import java.io.IOException;
import java.io.StringReader;
import javax.ejb.Startup;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static javax.ws.rs.client.Entity.json;

/**
 *
 * @author Charlie
 */
@Startup
@WebServlet("/CloudChatUserManagerServlet")
public class UserManagerServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Got Here");
        String action = request.getParameter("action");
        String userID = request.getParameter("userID");
        String password = request.getParameter("password");
        boolean retVal = false;
        String result = null;
        UserManagerServiceImplService service = new UserManagerServiceImplService();
        UserManagerService port = service.getUserManagerServiceImplPort();
        switch (action) {
            case "AddUser":
                retVal = port.registerUser(userID, password);
                break;
            case "LogIn":
                retVal = port.logInUser(userID, password);
                break;
            case "LogOut":
                retVal = port.logOutUser(userID);
                break;
            case "GetCurrentUsers":
                result = port.getAllLoggedUsers();
                if(result != null)
                    retVal = true;
                else
                    retVal = false;
                break;
            default:
                retVal = false;
                break;
        }
        JsonObjectBuilder builder = Json.createObjectBuilder().add("retVal", retVal);
        
        if(result != null){
            JsonReader jsonReader = Json.createReader(new StringReader(result));
            JsonArray jsonArray = jsonReader.readArray();
            builder.add("result", jsonArray);
        }
        
        response.getWriter().print(builder.build().toString());
    }
}