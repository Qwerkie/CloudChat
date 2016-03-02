/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roster;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ctd5100
 */
@XmlRootElement(name = "student")
public class Student implements Serializable{
    private String lastName;
    private String firstName;
    private String psuID;
    private String teamNumber;
    public Student(){

    }
    @XmlElement
    public String getLastName(){
        return lastName;
    }
    @XmlElement
    public String getFirstName(){
        return firstName;
    }
    @XmlElement
    public String getPsuID(){
        return psuID;
    }
    @XmlElement
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
    public void updatePsuID(String psuID){
        this.psuID=psuID;
    }
}
