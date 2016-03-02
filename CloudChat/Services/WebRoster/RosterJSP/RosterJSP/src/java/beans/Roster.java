/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import javax.servlet.jsp.JspWriter;

/**
 *
 * @author ctd5100
 */
public class Roster implements Serializable{
    private ArrayList<Student> rosterList;
    private Scanner scanner;
    public Roster() throws FileNotFoundException{
        scanner = new Scanner(this.getClass().getClassLoader().getResourceAsStream("/Roster.txt"));
        rosterList = new ArrayList();
        populateRosterList();
    }
    
    public ArrayList<Student> getRosterList(){
        return rosterList;
    }
    private void populateRosterList(){
        while(scanner.hasNextLine()){
            String lastName = scanner.nextLine().trim();
            String firstName = scanner.nextLine().trim();
            String psuID = scanner.nextLine().trim();
            String teamNumber = scanner.nextLine().trim();
            rosterList.add(new Student(lastName, firstName, psuID, teamNumber));
            scanner.nextLine();
            
        }
    }
    
}
