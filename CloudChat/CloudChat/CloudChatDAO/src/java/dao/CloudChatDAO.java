/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import jdbc.DBUtility;

/**
 *
 * @author Charlie
 */
public class CloudChatDAO {

    private Connection dbConnection;
    private PreparedStatement statement;

    public CloudChatDAO() {
        dbConnection = DBUtility.getConnection();
    }

    /**
     * Adds a user to the database
     *
     * @param userID
     * @param password
     * @return returns true if userID was able to be added to the database,
     * returns false if unable to
     */
    public boolean addUser(String userID, String password) {
        String addQuery = "INSERT INTO Credentials (UserID, Password, LoggedOn) VALUES (?,?,?)";
        try {
            statement = dbConnection.prepareStatement(addQuery);
            statement.setString(1, userID);
            statement.setString(2, password);
            statement.setBoolean(3, true);  // automagically log in user after registration?
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    /**
     * Removes a user from the database
     *
     * @param userID
     * @return returns true if user was able to be removed, false if else
     */
    public boolean removeUser(String userID) {
        String removeQuery = "DELETE FROM Credentials WHERE UserID = ?";
        try {
            statement = dbConnection.prepareStatement(removeQuery);
            statement.setString(1, userID);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    /**
     * Updates a user's password
     *
     * @param userID
     * @param newPassword
     * @return returns true if the password was able to be updated
     */
    public boolean updatePassword(String userID, String newPassword) {
        String updateQuery = "UPDATE Credentials SET Password = ? WHERE UserID = ?";
        try {
            statement = dbConnection.prepareStatement(updateQuery);
            statement.setString(1, newPassword);
            statement.setString(2, userID);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    /**
     * Checks to see if the user's credentials exist
     *
     * @param userID
     * @param password
     * @return true if the credentials are valid, false if not
     */
    public boolean checkUserCredentials(String userID, String password) {
        String checkQuery = "Select * FROM Credentials WHERE UserID = ? AND Password = ?";
        try {
            statement = dbConnection.prepareStatement(checkQuery, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.setString(1, userID);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if (rs.first()) { // if the result set is not empty
                logInUser(userID);
                return true;        // the data exists
            } else {
                return false;       // data does not exist
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    /**
     * Attempts to log the user in
     *
     * @param userID
     * @return returns true if the user was able to log in
     */
    private void logInUser(String userID) {
        String updateQuery = "UPDATE Credentials SET LoggedOn = ? WHERE UserID = ?";
        try {
            statement = dbConnection.prepareStatement(updateQuery);
            statement.setBoolean(1, true);
            statement.setString(2, userID);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    public void logOutUser(String userID){
        String updateQuery = "UPDATE Credentials SET LoggedOn = ? WHERE UserID = ?";
        try {
            statement = dbConnection.prepareStatement(updateQuery);
            statement.setBoolean(1, false);
            statement.setString(2, userID);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    public ResultSet getAllCurrentUsers() {
        String query = "SELECT UserID FROM Credentials WHERE LoggedOn = TRUE";
        try {
            statement = dbConnection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = statement.executeQuery();
            if (rs.isBeforeFirst()) {
                return rs;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    /**
     * Stores the message to the database
     *
     * @param messageID
     * @param userID
     * @param message
     * @param category
     */
    public boolean storeMessage(Timestamp messageID, String userID, String message, String category) {
        String insertQuery = "INSERT INTO Messages(MessageID, UserID, Message, Category) VALUES (?,?,?,?)";
        try {
            statement = dbConnection.prepareStatement(insertQuery);
            statement.setTimestamp(1, messageID);
            statement.setString(2, userID);
            statement.setString(3, message);
            statement.setString(4, category);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    /**
     * Retrieves messages from the database based on category
     *
     * @param category
     * @return returns result if data exists, returns null if it does not
     */
    public ResultSet getMessages(String category) {
        String query = "Select * FROM Messages Where Category = ? ORDER BY MessageID";
        try {
            statement = dbConnection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.setString(1, category);
            ResultSet rs = statement.executeQuery();
            if (rs.isBeforeFirst()) {
                return rs;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    /**
     * Retrieves all messages
     *
     * @return resultSet
     */
    public ResultSet getAllMessages() {
        String query = "Select * FROM Messages";
        try {
            statement = dbConnection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = statement.executeQuery();
            if (rs.isBeforeFirst()) {
                return rs;
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
    public boolean updateMessage(Timestamp messageID, String message){
        String query = "Update Messages SET Message = ? WHERE MessageID = ?";
        try{
            statement = dbConnection.prepareStatement(query);
            statement.setString(1, message);
            statement.setTimestamp(2, messageID);
            statement.executeUpdate();
            return true;
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
            return false;
        }
    }
    public boolean removeMessage(Timestamp messageID){
        String query = "DELETE FROM Messages WHERE MessageID = ?";
        try{
            statement = dbConnection.prepareStatement(query);
            statement.setTimestamp(1, messageID);
            statement.executeUpdate();
            return true;
        }
        catch(SQLException e){
            return false;
        }
    }
}
