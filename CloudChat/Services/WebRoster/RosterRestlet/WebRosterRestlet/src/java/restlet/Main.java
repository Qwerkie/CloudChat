package restlet;

import org.restlet.Component;
import org.restlet.data.Protocol;

public class Main {
	
	public static void main (String[] args) throws Exception {

		// Create a new Component
		Component component = new Component();
		// Add a new HTTP server listening on port 8282
		component.getServers().add(Protocol.HTTP, 8282);
		// Attach the Roster application
		component.getDefaultHost().attach("/roster", new RosterApplication());
		// Start the Web Server
		component.start();

	}
}