package restlet;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.data.Status;
import org.restlet.data.MediaType;

public class DisplayRoster extends ServerResource {
    public DisplayRoster() { }

    @Get
    public Representation toPlain() {
    	String rosterEntries = Roster.toTable();
		setStatus(Status.SUCCESS_OK);
		return new StringRepresentation(rosterEntries, MediaType.TEXT_PLAIN);
    }
}


