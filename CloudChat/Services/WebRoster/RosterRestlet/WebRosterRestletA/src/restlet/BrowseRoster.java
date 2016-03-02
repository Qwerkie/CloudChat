package restlet;

import java.util.concurrent.CopyOnWriteArrayList;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.data.Status;
import org.restlet.data.MediaType;
import org.restlet.data.Form;

public class BrowseRoster extends ServerResource {

    // Constructor for UpdateRoster instance
    public BrowseRoster() {
    }

    @Post
    public Representation browseRoster(Representation data) {
        Status reqStatus = null;
        String reqMessage = null;

        // Extract the data from the POST body
        Form form = new Form(data);
        String queryType = form.getFirstValue("queryType");
        String queryValue = form.getFirstValue("queryValue");
        CopyOnWriteArrayList<RosterEntry> entries = Roster.findEntry(queryType, queryValue);
        setStatus(Status.SUCCESS_OK);
        return new StringRepresentation(Utilities.toTable(entries), MediaType.TEXT_PLAIN);
	}

}