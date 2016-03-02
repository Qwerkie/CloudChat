package restlet;

import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.data.Status;
import org.restlet.data.MediaType;
import org.restlet.data.Form;

public class DeleteStudent extends ServerResource {
    public DeleteStudent() { }

    @Post
    public Representation delete(Representation data) {
		Status reqStatus = null;
		String reqMessage = null;

		// Extract the data from the DELETE body.
		Form form = new Form(data);
		String studentId = form.getFirstValue("studentId");
		
		if (studentId == null) {
		    reqMessage = "Not enough information was sent to the server.\n";
		    reqStatus = Status.CLIENT_ERROR_BAD_REQUEST;
		}
		else {
			RosterEntry removedEntry = Roster.findStudent(studentId);
			if (removedEntry == null) {
				reqMessage = "The RosterEntry object was not found.";
				reqStatus = Status.CLIENT_ERROR_BAD_REQUEST;
			} else {
				Roster.deleteEntry(removedEntry);
				reqMessage = "The RosterEntry object has been removed from the Roster.";
		    	reqStatus = Status.SUCCESS_OK;
			}
		}

		setStatus(reqStatus);
		return new StringRepresentation(reqMessage, MediaType.TEXT_PLAIN);
    }
}


