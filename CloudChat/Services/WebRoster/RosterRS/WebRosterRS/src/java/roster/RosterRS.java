package roster;

import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Context;
import javax.servlet.ServletContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.rpc.util.Debug;
import java.net.URI;
import java.util.Scanner;
import javax.ws.rs.core.UriBuilder;
import roster.Student;
import roster.StudentList;

@Path("/")
public class RosterRS {
    @Context 
    private ServletContext sctx;          // dependency injection
    private static StudentList slist; // set in populate()

    public RosterRS() { }

    @GET
    @Path("/xml")
    @Produces({MediaType.APPLICATION_XML}) 
    public Response getXml() {
	checkContext();
	return Response.ok(slist, "application/xml").build();
    }

    @GET
    @Path("/xml/{id: \\d+}")
    @Produces({MediaType.APPLICATION_XML}) // could use "application/xml" instead
    public Response getXml(@PathParam("psuID") String id) {
	checkContext();
	return toRequestedType(id, "application/xml");
    }
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/json")
    public Response getJson() {
	checkContext();
	return Response.ok(toJson(slist), "application/json").build();
    }
    @GET    
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/json/{id: \\d+}")
    public Response getJson(@PathParam("psuId") String id) {
	checkContext();
	return toRequestedType(id, "application/json");
    }
    @GET
    @Path("/plain")
    @Produces({MediaType.TEXT_PLAIN}) 
    public String getPlain() {
	checkContext();
	return slist.toString();
    }

    @POST
    @Produces({MediaType.TEXT_PLAIN})
    @Path("/create")
    public Response create(@FormParam("firstName") String firstName, 
			   @FormParam("lastName") String lastName,
                           @FormParam("psuID") String psuID,
                           @FormParam("teamNumber") String teamNumber) {
	checkContext();
	String msg = null;
	// Require all properties to create.
	if (firstName == null || lastName == null || psuID == null || teamNumber == null) {
	    msg = "A Property is missing.\n";
	    return Response.status(Response.Status.BAD_REQUEST).
		                                   entity(msg).
		                                   type(MediaType.TEXT_PLAIN).
		                                   build();
	}	    
	// Otherwise, create the Prediction and add it to the collection.
	String id = addStudent(lastName, firstName, psuID, teamNumber);
	msg = "Student " + id + " created: (lastName = " + lastName + " firstname = " + firstName
                + " teamNumber = " + teamNumber+ ").\n";
	return Response.ok(msg, "text/plain").build();
    }
    @POST
    @Path("/navigate")
    public Response navigate(@FormParam("button") String button){
        try{
            URI uri;
            switch(button){
                case "Update":
                    uri = new URI("http://localhost:8080/WebRosterRS/updateStudent.jsp");
                    break;
                case "Delete Student":
                    uri = new URI("http://localhost:8080/WebRosterRS/deleteStudent.jsp");
                    break;
                case "Add Student":
                    uri = new URI("http://localhost:8080/WebRosterRS/addStudent.jsp");
                    break;
                default:
                    uri = new URI("http://localhost:8080/WebRosterRS/roster.jsp");
                    break;
            }
            return Response.temporaryRedirect(uri).build();
        }
        catch(Exception ex){
            return Response.serverError().build();
        }
       
        
    }
    @POST
    @Produces({MediaType.TEXT_PLAIN})
    @Path("/update")
    public Response update(@FormParam("psuID") String psuID,
			   @FormParam("lastName") String lastName, 
			   @FormParam("firstName") String firstName,
                           @FormParam("teamNumber") String teamNumber,
                           @FormParam("button") String button) {
	checkContext();

	// Check that sufficient data are present to do an edit.
	String msg = null;
	if (lastName == null && firstName == null && teamNumber == null) 
	    msg = "Everything was null";

	Student p = slist.find(psuID);
	if (p == null)
	    msg = "There is no student with ID " + psuID + "\n";

	if (msg != null)
	    return Response.status(Response.Status.BAD_REQUEST).
		                                   entity(msg).
		                                   type(MediaType.TEXT_PLAIN).
		                                   build();
	// Update.
	if (lastName != null) p.updateLastName(lastName);
	if (firstName != null) p.updateFirstName(firstName);
        if (teamNumber != null) p.updateTeamNumber(teamNumber);
	msg = "Student " + psuID + " has been updated.\n";
	return navigate("hafwefhu");//Response.ok(msg, "text/plain").build();
    }

    @POST
    @Produces({MediaType.TEXT_PLAIN})
    @Path("/delete/{id: \\d+}")
    public Response delete(@PathParam("psuID") String id) {
	checkContext();
	String msg = null;
	Student p = slist.find(id);
	if (p == null) {
	    msg = "There is no student with ID " + id + ". Cannot delete.\n";
	    return Response.status(Response.Status.BAD_REQUEST).
		                                   entity(msg).
		                                   type(MediaType.TEXT_PLAIN).
		                                   build();
	}
	slist.getStudents().remove(p);
	msg = "Student " + id + " deleted.\n";

	return Response.ok(msg, "text/plain").build();
    }

    //** utilities
    private void checkContext() {
	if (slist == null) populate();
    }

    private void populate() {
        Scanner scanner = new Scanner(this.getClass().getClassLoader().getResourceAsStream("/Roster.txt"));
	slist = new StudentList();
        while(scanner.hasNextLine()){
            String lastName = scanner.nextLine().trim();
            String firstName = scanner.nextLine().trim();
            String psuID = scanner.nextLine().trim();
            String teamNumber = scanner.nextLine().trim();
            slist.add(lastName, firstName, psuID, teamNumber);
            scanner.nextLine();
            
        }
    }

    // Add a new prediction to the list.
    private String addStudent(String lastName, String firstName, String psuID, String teamNumber) {
	slist.add(lastName, firstName, psuID, teamNumber);
	return  psuID;
    }

    // Prediction --> JSON document
    private String toJson(Student student) {
	String json = "If you see this, there's a problem.";
	try {
	    json = new ObjectMapper().writeValueAsString(student);
	}
	catch(Exception e) { }
	return json;
    }

    // PredictionsList --> JSON document
    private String toJson(StudentList slist) {
	String json = "If you see this, there's a problem.";
	try {
	    json = new ObjectMapper().writeValueAsString(slist);
	}
	catch(Exception e) { }
	return json;
    }

    // Generate an HTTP error response or typed OK response.
    private Response toRequestedType(String id, String type) { // not sure what type is
	Student stud = slist.find(id);
	if (stud == null) {
	    String msg = id + " is a bad ID.\n";
	    return Response.status(Response.Status.BAD_REQUEST).
		                                   entity(msg).
		                                   type(MediaType.TEXT_PLAIN).
		                                   build();
	}
	else if (type.contains("json"))
	    return Response.ok(toJson(stud), type).build();
	else
	    return Response.ok(stud, type).build(); // toXml is automatic
    }
}



