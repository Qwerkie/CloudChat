/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Charlie
 */
public class User { 
    private String userID;
    private String password;
    public User(String userID, String password){
        this.userID = userID;
        this.password = password;
    }
    public String getuserID(){
        return userID;
    }
    /*
    public String getPassword(){
        return password;
    }
    */
}
