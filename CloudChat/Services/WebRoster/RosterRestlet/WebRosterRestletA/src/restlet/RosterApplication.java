package restlet;

// Import the necessary libraries for running the Restlet
import java.util.concurrent.CopyOnWriteArrayList;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.routing.Router;
import org.restlet.data.Status;
import org.restlet.data.MediaType;

// Application for the Roster
public class RosterApplication extends Application {
    
    // Function to create the restlet
    @Override
    public synchronized Restlet createInboundRoot() {

		Router applicationRouter = new Router(getContext());
        // Show the Roster
        applicationRouter.attach("/", DisplayRoster.class);
        // Update Button
        applicationRouter.attach("/updateRoster", UpdateRoster.class);
        // Browse Button
        applicationRouter.attach("/browseRoster", BrowseRoster.class);
        // Add Student Button
        applicationRouter.attach("/createStudent", CreateStudent.class);
        // Add Team Button
        applicationRouter.attach("/createTeam", CreateTeam.class);
        // Delete Student Button
        applicationRouter.attach("/removeStudent", DeleteStudent.class);
        // Delete Team Button
        applicationRouter.attach("/removeTeam", DeleteTeam.class);
        
        // Return the router as the Restlet
        return applicationRouter;
    }

    private String badRequest(String msg) {
    	Status error = new Status(Status.CLIENT_ERROR_BAD_REQUEST, msg);
    	return error.toString();
    }
    
}
