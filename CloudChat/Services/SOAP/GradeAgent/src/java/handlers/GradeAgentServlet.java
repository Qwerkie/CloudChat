package handlers;

import client.IGradeImplService;
import client.IGradeService;
import java.io.IOException;
import javax.ejb.Startup;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jts5507
 */
@Startup
@WebServlet("/gradeAgentServlet")
public class GradeAgentServlet extends HttpServlet{
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("POST");
        
        String grade = request.getParameter("grade");
        String choice = request.getParameter("choice");
        String unit = request.getParameter("unit");
        String retVal;
        
        IGradeImplService service = new IGradeImplService();
        service.setHandlerResolver(new HashHandler("Robbie", "QuestionRobbie"));
        IGradeService port = service.getIGradeImplPort();
        
        switch(choice){
            case "hundred-scale":
                retVal=port.toHundredScale(grade,unit);
                break;
            case "seven-scale":
                retVal=port.toSevenScale(grade,unit);
                break;
            case "letter-scale":
                retVal=port.toLetterScale(grade,unit);
                break;
            default:
                retVal="";
                break;
        }
        
        request.setAttribute("retVal",retVal);
        request.setAttribute("choice",choice);
                
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/GradeAgent.jsp");
        dispatcher.forward(request, response);
    }
}
