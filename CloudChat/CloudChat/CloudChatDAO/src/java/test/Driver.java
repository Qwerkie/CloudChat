/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import dao.CloudChatDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;

/**
 *
 * @author Charlie
 */
public class Driver {

    public static void main(String[] args) {
        try {
            CloudChatDAO cloudDao = new CloudChatDAO();
            cloudDao.addUser("Charlie", "Duong");
            cloudDao.checkUserCredentials("Charlie", "Duong");
            cloudDao.checkUserCredentials("Charlie", "fdesfesf");
            cloudDao.updatePassword("Charlie", "charlie");
            cloudDao.checkUserCredentials("Charlie", "charlie");
            Calendar cal = GregorianCalendar.getInstance();
            Timestamp ts = new Timestamp(cal.getTimeInMillis());
            cloudDao.storeMessage(ts, "Charlie", "Herrroooo!!!!", "General");
            ResultSet rs1 = cloudDao.getMessages("Sports");
            ResultSet rs2 = cloudDao.getMessages("General");
            ResultSet rs3 = cloudDao.getAllMessages();
            // rs1 test
            /*
            if(rs1 != null && rs1.isBeforeFirst()){
                while(rs1.next()){
                    System.out.println(rs1.getString("Message"));
                }
            }
            else{
                System.out.println("It's null");
            }
                    */
            JsonArray ja = parseMessageSet(rs2);
            System.out.println(ja);
            // rs2 test
            if(rs2 != null && rs2.isBeforeFirst()){
                while(rs2.next()){
                    System.out.println(rs2.getString("Message"));
                }
            }
            // rs3 test
            if(rs3 != null && rs3.isBeforeFirst()){
                while(rs3.next()){
                    System.out.println(rs3.getString("Message"));
                }
            }
            cloudDao.removeUser("Charlie");
        } catch (SQLException ex) {
            Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private static JsonArray parseMessageSet(ResultSet rs) {
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
}
