/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Startup;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;

/**
 *
 * @author Charlie
 */
@Startup
@WebServlet("/navigatorServlet")
public class NavigatorServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getParameterMap().toString());
        String page = request.getParameter("page");
        String type = request.getParameter("type");
        
        System.out.println(page);
        System.out.println(type);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String address = "http://127.0.0.1:8080";
        HttpPost httpPost;
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        if (type.equalsIgnoreCase("GetPage")) {
            switch (page) {

                case "LogIn":
                    address += "/CloudChatUserManagerClient/loginPage.html";
                    break;
                case "LogOut":
                    address += "/CloudChatUserManagerClient/logoutConfirm.html";
                    break;
                case "LogInSuccess":
                    address += "/CloudChatUserManagerClient/loginSuccessPage.html";
                    break;
                case "Register":
                    System.out.println("Got here");
                    address += "/CloudChatUserManagerClient/registrationPage.html";
                    break;
                case "Edit":
                    address += "/CloudChatServer/edit.jsp";
                    break;
                case "Delete":
                    address += "/CloudChatServer/delete.jsp";
                    break;
                case "ChatHome":
                    address += "/CloudChatServer/chat.jsp";
                    break;
                default:
                    address = "";
                    break;
            }
        }
        else if (type.equalsIgnoreCase("UserManager")){
            address += "/CloudChatUserManagerClient/CloudChatUserManagerServlet";
            nvps.add(new BasicNameValuePair("action", request.getParameter("action")));
            nvps.add(new BasicNameValuePair("userID", request.getParameter("userID")));
            nvps.add(new BasicNameValuePair("password",request.getParameter("password")));
        }
        else if (type.equalsIgnoreCase("Server")){
            System.out.println("Got here");
            address += "/CloudChatServer/chat";
            nvps.add(new BasicNameValuePair("userID", request.getParameter("userID")));
            nvps.add(new BasicNameValuePair("action", request.getParameter("action")));
            nvps.add(new BasicNameValuePair("messageID", request.getParameter("messageID")));
            nvps.add(new BasicNameValuePair("message", request.getParameter("message")));
            nvps.add(new BasicNameValuePair("category",request.getParameter("category")));
        }
        httpPost = new HttpPost(address);
        httpPost.setEntity(new UrlEncodedFormEntity(nvps));
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        try {
            System.out.println(httpResponse.getStatusLine());
            HttpEntity entity = httpResponse.getEntity();
            BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
            String line;
            String res = "";
            while ((line = reader.readLine()) != null) {
                res += line;
            }
            EntityUtils.consume(entity);
            response.getWriter().write(res);
        } finally {
            httpResponse.close();
        }
    }

}
