package restlet;

import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.data.Status;
import org.restlet.data.MediaType;
import org.restlet.data.Form;

public class CreateStudent extends ServerResource {
    public CreateStudent() { }

    @Post
    public Representation create(Representation data) {
		Status reqStatus = null;
		String reqMessage = null;

		// Extract the data from the POST body.
		Form form = new Form(data);
		String firstName = form.getFirstValue("firstName");
		String lastName = form.getFirstValue("lastName");
		String studentId = form.getFirstValue("studentId");
		Integer teamNumber = Integer.parseInt(form.getFirstValue("teamNumber").trim());

		if (firstName == null || lastName == null || studentId == null || teamNumber == null) {
		    reqMessage = "Not enough information was sent to the server.\n";
		    reqStatus = Status.CLIENT_ERROR_BAD_REQUEST;
		}
		else {
			RosterEntry newEntry = new RosterEntry();
			newEntry.setFirstName(firstName.toUpperCase());
			newEntry.setLastName(lastName.toUpperCase());
			newEntry.setStudentId(studentId.toUpperCase());
			newEntry.setTeamNumber(teamNumber);
		    Roster.addEntry(newEntry);
		    reqMessage = "The new RosterEntry object has been added to the Roster.";
		    reqStatus = Status.SUCCESS_OK;
		}

		setStatus(reqStatus);
		return new StringRepresentation(reqMessage, MediaType.TEXT_PLAIN);
    }
}


