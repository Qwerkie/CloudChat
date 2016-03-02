package restlet;

import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.data.Status;
import org.restlet.data.MediaType;
import org.restlet.data.Form;

public class UpdateRoster extends ServerResource {
    public UpdateRoster() { }

    @Put
    public Representation update(Representation data) {
		Status reqStatus = null;
		String reqMessage = null;

		// Extract the data from the POST body.
		Form form = new Form(data);
		String oldId = form.getFirstValue("oldId");
		String newFn = form.getFirstValue("newFn");
		String newLn = form.getFirstValue("newLn");
		String newId = form.getFirstValue("newId");
		Integer newTn = Integer.parseInt(form.getFirstValue("newTn").trim());

		if (oldId == null || newFn == null || newLn == null || newId == null || newTn == null) {
		    reqMessage = "Not enough information sent to the server.\n";
		    reqStatus = Status.CLIENT_ERROR_BAD_REQUEST;
		}
		else {
		    RosterEntry changingEntry = Roster.findStudent(oldId);
		    if (changingEntry == null) {
				reqMessage = "No student found with id: "+oldId;
				reqStatus = Status.CLIENT_ERROR_BAD_REQUEST;
		    }
		    else {
				changingEntry.setFirstName(newFn);
				changingEntry.setLastName(newLn);
				changingEntry.setStudentId(newId);
				changingEntry.setTeamNumber(newTn);
				reqMessage = "Student has been updated.";
				reqStatus = Status.SUCCESS_OK;
		    }
		}

		setStatus(reqStatus);
		return new StringRepresentation(reqMessage, MediaType.TEXT_PLAIN);
    }
}


