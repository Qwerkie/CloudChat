/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author ctd5100
 */
public class Student implements Serializable{
    private String lastName;
    private String firstName;
    private final String studentID;
    private String teamNumber;
    public Student(String lastName, String firstName, String psuID, String teamNumber){
        this.lastName = lastName;
        this.firstName = firstName;
        this.studentID = psuID;
        this.teamNumber = teamNumber;
    }
    public String getLastName(){
        return lastName;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getPsuID(){
        return studentID;
    }
    public String getTeamNumber(){
        return teamNumber;
    }
    public void updateLastName(String lastName){
        this.lastName=lastName;
    }
    public void updateFirstName(String firstName){
        this.firstName=firstName;
    }
    public void updateTeamNumber(String teamNumber){
        this.teamNumber=teamNumber;
    }
}
