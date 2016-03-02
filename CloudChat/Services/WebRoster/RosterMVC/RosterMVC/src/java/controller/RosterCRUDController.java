/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import model.Roster;
import model.Student;

/**
 *
 * @author Charlie
 */
@Startup
@WebServlet("/roster")
public class RosterCRUDController extends HttpServlet{
    private Roster roster;
    @Override
    public void init(){
        
        try {
            roster = new Roster();
            getServletContext().setAttribute("roster", roster);
            System.out.println("init");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RosterCRUDController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } 
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String value = request.getParameter("button");
        String address;
        System.out.println("GET");
        if(value.equals("Browse")){
            address = "/WEB-INF/view/roster.jsp";
        }
        else if(value.equals("Add Student")){
            address = "/WEB-INF/view/addStudent.jsp";
        }
        else if(value.equals("Add Team")){
            address = "/WEB-INF/view/addTeam.jsp";
        }
        else if(value.equals("Delete Student")){
            address = "/WEB-INF/view/deleteStudent.jsp";
        }
        else if(value.equals("Delete Team")){
            address = "/WEB-INF/view/deleteTeam.jsp";
        }
        else if(value.equals("Update")){
            address = "/WEB-INF/view/updateStudent.jsp";
        }
        else{
            address = "/WEB-INF/view/navigation.jsp";
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("POST");
        String value = request.getParameter("button");
        String address;
        if(value.equals("Add Student")){
            String teamNumber = request.getParameter("teamNumber");
            if(roster.getTeamList().contains(teamNumber)){
                String lastName = request.getParameter("lastName");
                String firstName = request.getParameter("firstName");
                String psuID = request.getParameter("psuID");
                firstName=firstName.toUpperCase();
                lastName=lastName.toUpperCase();
                psuID=psuID.toUpperCase();
                Student newStudent = new Student(lastName, firstName, psuID, teamNumber);
                roster.getRosterList().add(newStudent);
            }
        }
        else if(value.equals("Add Team")){
            String team = request.getParameter("teamNumber");
            if(!roster.getTeamList().contains(team))
                roster.getTeamList().add(team);
        }
        else if(value.equals("Delete Student")){
            String psuID = request.getParameter("psuID");
            for(Student s : roster.getRosterList()){
                if(s.getPsuID().equalsIgnoreCase(psuID)){
                    roster.getRosterList().remove(s);
                    break;
                }
            }
        }
        else if(value.equals("Delete Team")){
            String team = request.getParameter("teamNumber");
            if(roster.getTeamList().contains(team))
               roster.getTeamList().remove(team);
            ArrayList<Student> students = new ArrayList();
            for(Student s : roster.getRosterList()){
                if(s.getTeamNumber().equals(team)){
                    students.add(s);
                }
            }
            for(Student s : students)
                roster.getRosterList().remove(s);
        }
        else if(value.equals("Update")){
            String psuID = request.getParameter("psuID");
            System.out.println("The one we want: " +psuID);
            Student student=null;
            for(int i=0;i<roster.getRosterList().size();i++){
                if(roster.getRosterList().get(i).getPsuID().equalsIgnoreCase(psuID)){
                    student=roster.getRosterList().get(i);
                    break;
                }
            }
            System.out.println("The one we got: " + student.getPsuID());
            if(student!=null)
            {
                String lastName = request.getParameter("lastName");
                String firstName = request.getParameter("firstName");
                String teamNumber = request.getParameter("teamNumber");
                firstName=firstName.toUpperCase();
                lastName=lastName.toUpperCase();
                if(roster.getTeamList().contains(teamNumber))
                {
                    student.updateLastName(lastName);
                    student.updateFirstName(firstName);
                    student.updateTeamNumber(teamNumber);
                }
            }
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/roster.jsp");
        dispatcher.forward(request, response);
    }
}
