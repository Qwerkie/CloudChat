package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Startup;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jts5507
 */
@Startup
@WebServlet("/chat")
public class ChatController extends HttpServlet {

    private final String DAO_URL = "http://127.0.0.1:8080/CloudChatDAO/CloudDAO";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String value = request.getParameter("button");
        String address;
        System.out.println("GET");
        if (value.equals("Edit Message")) {
            address = "/edit.jsp";
        } else if (value.equals("Delete Message")) {
            address = "/delete.jsp";
        } else {
            address = "/chat.jsp";
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String value = request.getParameter("action");
        String address;
        System.out.println("POST");
        URL url = new URL(DAO_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("charset", "utf-8");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.connect();
        if (value.equals("PostMessage")) {
            try {
                String userID = request.getParameter("userID");
                String message = request.getParameter("message");
                String category = request.getParameter("category");
                String query = String.format("action=%s&userID=%s&message=%s&category=%s", "PostMessage", userID, message, category);
                OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
                writer.write(query);  //send data to request
                System.out.println("Sent: " + query);
                writer.flush();
                InputStreamReader isr = new InputStreamReader(connection.getInputStream());
                BufferedReader reader = new BufferedReader(isr);
                String line;
                System.out.println("This is the result:");
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
                writer.close();
                reader.close();
                connection.disconnect();
            } catch (IOException ex) {
            }
        } else if (value.equals("Edit")) {
            try {
                String messageID = request.getParameter("messageID");
                String message = request.getParameter("message");
                String query = String.format("action=%s&messageID=%s&message=%s", "EditMessage", messageID, message);
                OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
                writer.write(query);  //send data to request
                System.out.println("Sent: " + query);
                writer.flush();
                InputStreamReader isr = new InputStreamReader(connection.getInputStream());
                BufferedReader reader = new BufferedReader(isr);
                String line;
                System.out.println("This is the result:");
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
                writer.close();
                reader.close();
                connection.disconnect();
            } catch (IOException ex) {
            }
        } else if (value.equals("Delete")) {
            try {
                String messageID = request.getParameter("messageID");
                String query = String.format("action=%s&messageID=%s", "RemoveMessage", messageID);
                OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
                writer.write(query);  //send data to request
                System.out.println("Sent: " + query);
                writer.flush();
                InputStreamReader isr = new InputStreamReader(connection.getInputStream());
                BufferedReader reader = new BufferedReader(isr);
                String line;
                System.out.println("This is the result:");
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
                writer.close();
                reader.close();
                connection.disconnect();
            } catch (IOException ex) {
            }
        } else if (value.equals("getAll")) {
            try {
                String category = request.getParameter("category");
                String query = String.format("action=%s", "GetAllMessages");
                OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
                writer.write(query);  //send data to request
                System.out.println("Sent: " + query);
                writer.flush();
                InputStreamReader isr = new InputStreamReader(connection.getInputStream());
                BufferedReader reader = new BufferedReader(isr);
                String line;
                String res = "";
                System.out.println("This is the result:");
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                    res += line;
                }
                writer.close();
                reader.close();
                response.getWriter().write(res);
                connection.disconnect();
            } catch (IOException ex) {
            }
        } else if (value.equals("getCategory")) {
            try {
                String category = request.getParameter("category");
                String query = String.format("action=%s&category=%s", "GetMessagesInCategory", category);
                OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
                writer.write(query);  //send data to request
                System.out.println("Sent: " + query);
                writer.flush();
                InputStreamReader isr = new InputStreamReader(connection.getInputStream());
                BufferedReader reader = new BufferedReader(isr);
                String line;
                String res = "";
                System.out.println("This is the result:");
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                    res += line;
                }
                writer.close();
                reader.close();
                response.getWriter().write(res);
                connection.disconnect();
            } catch (IOException ex) {
            }
        }
        //RequestDispatcher dispatcher = request.getRequestDispatcher("/chat.jsp");
        //dispatcher.forward(request, response);
    }
}
