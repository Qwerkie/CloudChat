/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Charlie
 */
public class DBUtility {
    private static Connection connection = null;
    
    public static Connection getConnection(){
        if(connection != null){
            return connection;
        }
        else{
            try{
                String host = "jdbc:derby://localhost:1527/CloudChatDB";
                String userID = "app";
                String password = "app";
                connection = DriverManager.getConnection(host, userID, password);
            }
            catch(SQLException e){
                System.out.println(e);
            }
            return connection;
        }
    }
}
