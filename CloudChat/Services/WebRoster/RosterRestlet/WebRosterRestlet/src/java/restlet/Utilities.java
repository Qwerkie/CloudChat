package restlet;

import java.util.concurrent.CopyOnWriteArrayList;

public class Utilities {

	public static String toTable(CopyOnWriteArrayList<RosterEntry> rosterEntries) {
		String tableString = "";
		for (RosterEntry rosterEntry : rosterEntries) {
			tableString += "<tr class='body'><td>"+rosterEntry.getLastName()+"</td>";
			tableString += "<td>"+rosterEntry.getFirstName()+"</td>";
			tableString += "<td>"+rosterEntry.getStudentId()+"</td>";
			tableString += "<td>"+rosterEntry.getTeamNumber()+"</td></tr>\n";
		}
		return tableString;
	}
}