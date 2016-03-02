/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;

/**
 *
 * @author ctd5100
 */
public class Student implements Serializable{
    private String lastName;
    private String firstName;
    private String psuID;
    private String teamNumber;
    public Student(String lastName, String firstName, String psuID, String teamNumber){
        this.lastName = lastName;
        this.firstName = firstName;
        this.psuID = psuID;
        this.teamNumber = teamNumber;
    }
    public String getLastName(){
        return lastName;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getPsuID(){
        return psuID;
    }
    public String getTeamNumber(){
        return teamNumber;
    }
}
