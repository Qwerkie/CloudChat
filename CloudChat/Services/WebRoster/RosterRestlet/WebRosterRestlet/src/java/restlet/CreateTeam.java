package restlet;

import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.data.Status;
import org.restlet.data.MediaType;
import org.restlet.data.Form;

public class CreateTeam extends ServerResource {
    public CreateTeam() { }

    @Post
    public Representation create(Representation data) {
		Status reqStatus = null;
		String reqMessage = null;

		// Extract the data from the POST body.
		Form form = new Form(data);
		String firstName1 = form.getFirstValue("firstName1");
		String lastName1 = form.getFirstValue("lastName1");
		String studentId1 = form.getFirstValue("studentId1");
		String firstName2 = form.getFirstValue("firstName2");
		String lastName2 = form.getFirstValue("lastName2");
		String studentId2 = form.getFirstValue("studentId2");
		String firstName3 = form.getFirstValue("firstName3");
		String lastName3 = form.getFirstValue("lastName3");
		String studentId3 = form.getFirstValue("studentId3");
		Integer teamNumber = Integer.parseInt(form.getFirstValue("teamNumber").trim());

		if (firstName1 == null || lastName1 == null || studentId1 == null || teamNumber == null ||
			firstName2 == null || lastName2 == null || studentId2 == null ||
			firstName3 == null || lastName3 == null || studentId3 == null) {
		    reqMessage = "Not enough information was sent to the server.\n";
		    reqStatus = Status.CLIENT_ERROR_BAD_REQUEST;
		}
		else {
			RosterEntry newEntry1 = new RosterEntry();
			newEntry1.setFirstName(firstName1.toUpperCase());
			newEntry1.setLastName(lastName1.toUpperCase());
			newEntry1.setStudentId(studentId1.toUpperCase());
			newEntry1.setTeamNumber(teamNumber);
			RosterEntry newEntry2 = new RosterEntry();
			newEntry2.setFirstName(firstName2.toUpperCase());
			newEntry2.setLastName(lastName2.toUpperCase());
			newEntry2.setStudentId(studentId2.toUpperCase());
			newEntry2.setTeamNumber(teamNumber);
			RosterEntry newEntry3 = new RosterEntry();
			newEntry3.setFirstName(firstName3.toUpperCase());
			newEntry3.setLastName(lastName3.toUpperCase());
			newEntry3.setStudentId(studentId3.toUpperCase());
			newEntry3.setTeamNumber(teamNumber);
		    Roster.addEntry(newEntry1);
		    Roster.addEntry(newEntry2);
		    Roster.addEntry(newEntry3);
		    reqMessage = "The new RosterEntry objects have been added to the Roster.";
		    reqStatus = Status.SUCCESS_OK;
		}

		setStatus(reqStatus);
		return new StringRepresentation(reqMessage, MediaType.TEXT_PLAIN);
    }
}


