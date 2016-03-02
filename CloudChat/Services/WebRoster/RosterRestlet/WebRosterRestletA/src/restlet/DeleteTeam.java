package restlet;

import java.util.concurrent.CopyOnWriteArrayList;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.data.Status;
import org.restlet.data.MediaType;
import org.restlet.data.Form;

public class DeleteTeam extends ServerResource {
    public DeleteTeam() { }

    @Post
    public Representation delete(Representation data) {
		Status reqStatus = null;
		String reqMessage = null;

		// Extract the data from the DELETE body.
		Form form = new Form(data);
		String teamNumber = form.getFirstValue("teamNumber");
		
		if (teamNumber == null) {
		    reqMessage = "Not enough information was sent to the server.\n";
		    reqStatus = Status.CLIENT_ERROR_BAD_REQUEST;
		}
		else {
			CopyOnWriteArrayList<RosterEntry> entries = Roster.findTeam(teamNumber);
			if (entries.size() < 1) {
				reqMessage = "The RosterEntry object was not found.";
				reqStatus = Status.CLIENT_ERROR_BAD_REQUEST;
			} else {
				for (RosterEntry rosterEntry : entries) {
					Roster.deleteEntry(rosterEntry);
				}
				reqMessage = "The RosterEntry objects have been removed from the Roster.";
		    	reqStatus = Status.SUCCESS_OK;
			}
		}

		setStatus(reqStatus);
		return new StringRepresentation(reqMessage, MediaType.TEXT_PLAIN);
    }
}


