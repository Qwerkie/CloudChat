/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managementService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.jws.WebService;

/**
 *
 * @author Charlie
 */
@WebService(endpointInterface = "managementService.UserManagerService")
public class UserManagerServiceImpl implements UserManagerService{
    private final String DAO_URL = "http://127.0.0.1:8080/CloudChatDAO/CloudDAO";
    @Override
    public boolean logInUser(String userID, String password) {
        try {
            URL url = new URL(DAO_URL);
            
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("charset", "utf-8");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.connect();
            String query = String.format("action=%s&userID=%s&password=%s",URLEncoder.encode("LogIn", "UTF-8"),
                    URLEncoder.encode(userID, "UTF-8"), URLEncoder.encode(password, "UTF-8"));
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(query);  //send data to request
            System.out.println("Sent: " + query);
            writer.flush();
            InputStreamReader isr = new InputStreamReader(connection.getInputStream());
            BufferedReader reader = new BufferedReader(isr);
            String line;
            System.out.println("This is the result:");
            while((line = reader.readLine()) != null){
                System.out.println(line);
            }
            writer.close();
            reader.close();
            connection.disconnect();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(UserManagerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }

    @Override
    public boolean logOutUser(String userID) {
        try {
            URL url = new URL(DAO_URL);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("charset", "utf-8");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.connect();
            String query = String.format("action=%s&userID=%s",URLEncoder.encode("LogOut", "UTF-8"),
                    URLEncoder.encode(userID, "UTF-8"));
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(query);  //send data to request
            System.out.println("Sent: " + query);
            writer.flush();
            InputStreamReader isr = new InputStreamReader(connection.getInputStream());
            BufferedReader reader = new BufferedReader(isr);
            String line;
            System.out.println("This is the result:");
            while((line = reader.readLine()) != null){
                System.out.println(line);
            }
            writer.close();
            reader.close();
            connection.disconnect();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(UserManagerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean registerUser(String userID, String password) {
        try{
            URL url = new URL(DAO_URL);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("charset", "utf-8");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.connect();
            String query = String.format("action=%s&userID=%s&password=%s",URLEncoder.encode("AddUser", "UTF-8"),
                    URLEncoder.encode(userID, "UTF-8"), URLEncoder.encode(password, "UTF-8"));
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(query);  //send data to request
            System.out.println("Sent: " + query);
            writer.flush();
            InputStreamReader isr = new InputStreamReader(connection.getInputStream());
            BufferedReader reader = new BufferedReader(isr);
            String line;
            System.out.println("This is the result:");
            while((line = reader.readLine()) != null){
                System.out.println(line);
            }
            writer.close();
            reader.close();
            connection.disconnect();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(UserManagerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    @Override
    public String getAllLoggedUsers(){
        try{
            URL url = new URL(DAO_URL);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("charset", "utf-8");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.connect();
            String query = String.format("action=%s",URLEncoder.encode("GetCurrentUsers", "UTF-8"));
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(query);  //send data to request
            System.out.println("Sent: " + query);
            writer.flush();
            InputStreamReader isr = new InputStreamReader(connection.getInputStream());
            BufferedReader reader = new BufferedReader(isr);
            String line;
            System.out.println("This is the result:");
            String result = "";
            while((line = reader.readLine()) != null){
                System.out.println(line);
                result += line;
            }
            writer.close();
            reader.close();
            connection.disconnect();
            return result;
        } catch (IOException ex) {
            Logger.getLogger(UserManagerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
