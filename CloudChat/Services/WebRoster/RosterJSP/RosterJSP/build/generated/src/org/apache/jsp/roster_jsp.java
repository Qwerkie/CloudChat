package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.BodyContent;
import beans.Student;
import java.util.ArrayList;

public final class roster_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <script src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js\"></script>\n");
      out.write("        <script src=\"http://tablesorter.com/__jquery.tablesorter.min.js\"></script>\n");
      out.write("        <script>\n");
      out.write("\n");
      out.write("            \n");
      out.write("            $(document).ready(function(){\n");
      out.write("               $(\"#table\").tablesorter();\n");
      out.write("                }\n");
      out.write("             );\n");
      out.write("            \n");
      out.write("        </script>\n");
      out.write("        <title>Roster</title>\n");
      out.write("        ");

            out.print("<style>");
            out.print("table{margin-left:15px}");
            out.print("table, th, td{border: 2px solid black;border-collapse: collapse;}");
            out.print("th{background-color: blue;color: white;}");
            out.print("th, td{padding:5px;}");
            out.print("td{font-size:1.1em}");
            out.print("</style>");
        
      out.write("\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");
      beans.Roster roster = null;
      synchronized (_jspx_page_context) {
        roster = (beans.Roster) _jspx_page_context.getAttribute("roster", PageContext.PAGE_SCOPE);
        if (roster == null){
          roster = new beans.Roster();
          _jspx_page_context.setAttribute("roster", roster, PageContext.PAGE_SCOPE);
        }
      }
      out.write("\n");
      out.write("         ");
  
            
           
                out.print("<h1>Class Roster</h1>");
                ArrayList<Student> rosterList = roster.getRosterList();
                out.print("<table id='table'><thead><tr><th>" + rosterList.get(0).getLastName()
                    + "</th><th> " + rosterList.get(0).getFirstName() +"</th><th>"
                        + rosterList.get(0).getPsuID()+"</th><th>"
                        + rosterList.get(0).getTeamNumber() + "</th></tr></thead><tbody>");
               
                for(int i = 1; i < rosterList.size(); i++){
                    out.print("<tr>");
                    out.print("<td>" + rosterList.get(i).getLastName() + "</td>");
                    out.print("<td>" + rosterList.get(i).getFirstName() + "</td>");
                    out.print("<td>" + rosterList.get(i).getPsuID()+ "</td>");
                    out.print("<td>" + rosterList.get(i).getTeamNumber()+ "</td>");
                    out.print("</tr>");
                }
                out.write("</tbody></table>");
            
            
            
         
      out.write("\n");
      out.write("         \n");
      out.write("        \n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
