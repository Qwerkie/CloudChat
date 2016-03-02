/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientServlet;

import com.cdyne.ws.Check;
import com.cdyne.ws.DocumentSummary;
import com.cdyne.ws.Words;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author charlie
 */
@WebServlet(name = "SpellCheckServlet", urlPatterns = {"/SpellCheckServlet"})
public class SpellCheckServlet extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/wsf.cdyne.com/SpellChecker/check.asmx.wsdl")
    private Check service;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try { // Call Web Service Operation(async. callback)
            com.cdyne.ws.Check service = new com.cdyne.ws.Check();
            com.cdyne.ws.CheckSoap port = service.getCheckSoap();
            // TODO initialize WS operation arguments here
            java.lang.String bodyText = "";
            javax.xml.ws.AsyncHandler<com.cdyne.ws.CheckTextBodyV2Response> asyncHandler = new javax.xml.ws.AsyncHandler<com.cdyne.ws.CheckTextBodyV2Response>() {
                public void handleResponse(javax.xml.ws.Response<com.cdyne.ws.CheckTextBodyV2Response> response) {
                    try {
                        // TODO process asynchronous response here
                        System.out.println("Result = " + response.get());
                    } catch (Exception ex) {
                        // TODO handle exception
                    }
                }
            };
            java.util.concurrent.Future<? extends java.lang.Object> result = port.checkTextBodyV2Async(bodyText, asyncHandler);
            while (!result.isDone()) {
                // do something
                Thread.sleep(100);
            }
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            String json = "{ \"numOfMistakes\": ";
            //Get the TextArea from the web page
            String TextArea1 = request.getParameter("inputTextArea");

            //Initialize WS operation arguments
            java.lang.String bodyText = TextArea1;

            //Process result
            com.cdyne.ws.DocumentSummary doc = checkTextBodyV2(bodyText);
            String allcontent = doc.getBody();
            JsonArray array = Json.createArrayBuilder().build();
            //From the retrieved document summary,
            //identify the number of wrongly spelled words:
            int no_of_mistakes = doc.getMisspelledWordCount();
            json += "\"" + no_of_mistakes + "\", \"wrongWords\": [ ";
            //From the retrieved document summary,
            //identify the array of wrongly spelled words:
            List allwrongwords = doc.getMisspelledWord();
            for (int i = 0; i < allwrongwords.size(); i++) {
                String onewrongword = ((Words) allwrongwords.get(i)).getWord();
                json += "{\"word\" : \"" + onewrongword + "\",";
                json += "\"suggestions\": [ ";
                List allsuggestions = ((Words) allwrongwords.get(i)).getSuggestions();
                for (int k = 0; k < allsuggestions.size(); k++) {
                    String onesuggestion = (String) allsuggestions.get(k);
                    json += "\t{\"suggestion\" : \"" + onesuggestion + "\" },";
                }
                json = json.substring(0, json.length() - 1) + "] },";
            }
            json = json.substring(0, json.length() - 1) + "] }";
            /*
            out.println("<html>");
            out.println("<head>");

            out.println("<body>");
            //Display the report's name as a title in the browser's titlebar:
            out.println("<title>Spell Checker Report</title>");
            out.println("</head>");
            out.println("<body>");

            //Display the report's name as a header within the body of the report:
            out.println("<h2><font color='red'>Spell Checker Report</font></h2>");

            //Display all the content (correct as well as incorrectly spelled) between quotation marks:
            out.println("<hr><b>Your text:</b> \"" + allcontent + "\"" + "<p>");

            //For every array of wrong words (one array per wrong word),
            //identify the wrong word, the number of suggestions, and
            //the array of suggestions. Then display the wrong word and the number of suggestions and
            //then, for the array of suggestions belonging to the current wrong word, display each
            //suggestion:
            for (int i = 0; i < allwrongwords.size(); i++) {
                String onewrongword = ((Words) allwrongwords.get(i)).getWord();
                int onewordsuggestioncount = ((Words) allwrongwords.get(i)).getSuggestionCount();
                //List allsuggestions = ((Words) allwrongwords.get(i)).getSuggestions();
                out.println("<hr><p><b>Wrong word:</b><font color='red'> " + onewrongword + "</font>");
                out.println("<p><b>" + onewordsuggestioncount + " suggestions:</b><br>");
                for (int k = 0; k < allsuggestions.size(); k++) {
                    String onesuggestion = (String) allsuggestions.get(k);
                    out.println(onesuggestion);
                }
            }

            //Display a line after each array of wrong words:
            out.println("<hr>");

            //Summarize by providing the number of errors and display them:
            out.println("<font color='red'><b>Summary:</b> " + no_of_mistakes + " mistakes (");
            for (int i = 0; i < allwrongwords.size(); i++) {
                String onewrongword = ((Words) allwrongwords.get(i)).getWord();
                out.println(onewrongword);
            }

            out.println(").");
            out.println("</font>");
            out.println("</body>");
            out.println("</html>");
            */
            response.setContentType("application/json");
            out.print(json);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private DocumentSummary checkTextBodyV2(java.lang.String bodyText) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        com.cdyne.ws.CheckSoap port = service.getCheckSoap();
        return port.checkTextBodyV2(bodyText);
    }

}
