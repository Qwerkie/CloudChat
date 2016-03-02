/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CloudChatDAO;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.ejb.Startup;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Charlie
 */
@Startup
@WebServlet("/CloudDAO")
public class CloudChatDAOController extends HttpServlet {
    
    private CloudChatDAO dao;
    
    @Override
    public void init() {
        System.out.println("DAO initialized");
        dao = new CloudChatDAO();   // try to create dao to use, if it cannot be created, you can't connect to the DB

    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String value = request.getParameter("action");
        System.out.println(value);
        if (value.equalsIgnoreCase("AddUser")) {
            String userID = request.getParameter("userID");
            String password = request.getParameter("password");
            if (isValid(userID) && isValid(password)) {
                if (!dao.addUser(userID, password)) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                }
            }
        } else if (value.equalsIgnoreCase("RemoveUser")) {
            String userID = request.getParameter("userID");
            if (isValid(userID)) {
                if (!dao.removeUser(value)) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                }
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else if (value.equalsIgnoreCase("LogIn")) {
            String userID = request.getParameter("userID");
            String password = request.getParameter("password");
            if (isValid(userID) && isValid(password)) {
                if (!dao.checkUserCredentials(userID, password)) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                }
            }
        } else if (value.equalsIgnoreCase("LogOut")) {            
            String userID = request.getParameter("userID");
            if (isValid(userID)) {
                dao.logOutUser(userID);
            }
        } else if (value.equalsIgnoreCase("GetCurrentUsers")) {
            System.out.println("Got here");
            ResultSet rs = dao.getAllCurrentUsers();
            if (rs != null) {
                JsonArray array = parseUserSet(rs);
                System.out.println("Set : " +array.toString());
                response.getWriter().print(array.toString());
            }
            else{
                System.out.println("Nope");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else if (value.equalsIgnoreCase("PostMessage")) {
            String userID = request.getParameter("userID");
            String message = request.getParameter("message");
            String category = request.getParameter("category");
            if (isValid(userID) && isValid(message) && isValid(category)) {
                Calendar cal = GregorianCalendar.getInstance();
                Timestamp ts = new Timestamp(cal.getTimeInMillis());
                if (!dao.storeMessage(ts, userID, message, category)) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                }
                else{
                    response.getWriter().write("{ retval : true }");
                }
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else if (value.equalsIgnoreCase("EditMessage")) {
            Timestamp ts = Timestamp.valueOf(request.getParameter("messageID"));
            String message = request.getParameter("message");
            if (!dao.updateMessage(ts, message)) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else if (value.equalsIgnoreCase("RemoveMessage")) {
            Timestamp ts = Timestamp.valueOf(request.getParameter("messageID"));
            if (!dao.removeMessage(ts)) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else if (value.equalsIgnoreCase("GetAllMessages")) {
            ResultSet rs;
            if ((rs = dao.getAllMessages()) != null) {
                JsonArray array = parseMessageSet(rs);
                response.getWriter().print(array.toString());
            }
        } else if (value.equalsIgnoreCase("GetMessagesInCategory")) {
            String category = request.getParameter("category");
            ResultSet rs;
            if ((rs = dao.getMessages(category)) != null) {
                JsonArray array = parseMessageSet(rs);
                response.getWriter().print(array.toString());
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
        else{
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
    
    private boolean isValid(String input) {
        if (input.contains("=") || input.contains(")") || input.contains(";")
                || input.contains("DROP TABLE") || input.contains("--")) {
            return false;
        } else {
            return true;
        }
    }
    
    private JsonArray parseMessageSet(ResultSet rs) {
        JsonArrayBuilder jsBuilder = Json.createArrayBuilder();
        try {
            while (rs.next()) {
                String messageID = rs.getTimestamp("MessageID").toString();
                String userID = rs.getString("UserID");
                String message = rs.getString("Message");
                String category = rs.getString("Category");
                jsBuilder.add(Json.createObjectBuilder()
                        .add("messageID", messageID)
                        .add("userID", userID)
                        .add("message", message)
                        .add("category", category));
            }
            return jsBuilder.build();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
    
    private JsonArray parseUserSet(ResultSet rs) {
        JsonArrayBuilder jsBuilder = Json.createArrayBuilder();
        try {
            while (rs.next()) {
                String userID = rs.getString("UserID");
                jsBuilder.add(Json.createObjectBuilder().add("UserID",userID));
            }
            return jsBuilder.build();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
