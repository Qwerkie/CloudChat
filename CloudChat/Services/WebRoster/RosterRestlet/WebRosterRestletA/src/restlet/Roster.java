package restlet;

import java.util.concurrent.CopyOnWriteArrayList;

public class Roster {
	
	// ArrayList for holding all of the RosterEntry objects
	private static CopyOnWriteArrayList<RosterEntry> rosterEntries;

	// Fill the ArrayList roster with RosterEntry objects
	static {
		final String[] stringRosterEntries = {
            "BAKER,AUSTIN,ARB5671,3",
            "BALOS,AMANDA,AZB199,1",
            "BALTA,TRENT,TMB5577,12",
            "CAMPBELL,MATTHEW,MRC5372,10",
            "DUONG,CHARLIE,CTD5100,9",
            "GOGA,DANIEL,DRG5190,11",
            "HANKEWYCZ,ANDREW,AZH5442,7",
            "HANSEN,ROBERT,RAH5360,8",
            "IVANCO,JUSTIN,JJI5019,10",
            "JACOBS,TYLER,TAJ5130,7",
            "JONES,EDWARD,EAJ5073,6",
            "KALMAR,PETER,PJK5220,3",
            "KARSH,JAMES,JRK5377,12",
            "KELLEHER,AUSTIN,ALK5492,7",
            "KRUZAN,ANDREW,ASK5264,4",
            "KUBACKI,DAVID,DMK5048,5",
            "LIN,GARY,GQL5141,6",
            "LOUCHART,GREGORY,GDL5051,2",
            "OROSZ,ZACHARY,ZJO5002,6",
            "PANETTA,MATTHEW,MDP5280,9",
            "POLJAK,DYLAN,DJP5319,4",
            "RAUSCH,AUSTIN,AHR5067,12",
            "REESE,ARIN,AWR5319,11",
            "REOTT,ZACHARY,ZDR5023,6",
            "RISTAU,BRIAN,BJR5336,2",
            "RISTAU,STEVEN,SPR5122,2",
            "ROSSWOG,CHRISTOPHER,CMR5556,10",
            "SHULTZ,JAMES,JTS5507,8",
            "SITTERLEY,SHAUN,SMS6179,4",
            "STANKIEWICZ,MARK,MIS5708,9",
            "STEEN,JOSHUA,JDS5782,9",
            "TAROSKY,KIMBERLY,KRT5110,1",
            "TAYLOR,DYLAN,DMT5235,3",
            "VALLO,ALEXANDER,ANV5067,2",
            "WILCZEK,CONNOR,CVW5203,5"
        };
        rosterEntries = new CopyOnWriteArrayList<RosterEntry>();
        for (int i=0; i<stringRosterEntries.length; i++) {
            String currentLine = stringRosterEntries[i];
            String[] objectInfo = currentLine.split(",");
            RosterEntry newEntry = new RosterEntry();
            newEntry.setLastName(objectInfo[0]);
            newEntry.setFirstName(objectInfo[1]);
            newEntry.setStudentId(objectInfo[2]);
            newEntry.setTeamNumber(Integer.parseInt(objectInfo[3]));
            rosterEntries.add(newEntry);
        }
    }

    // Return the list of RosterEntry objects as plain text
    public static String toPlain() {
        String rosterString = "";
        for (RosterEntry rosterEntry : rosterEntries) {
            rosterString += rosterEntry.getStudentId()+" -- ";
            rosterString += rosterEntry.getLastName()+", ";
            rosterString += rosterEntry.getFirstName()+" -- Team #";
            rosterString += rosterEntry.getTeamNumber()+"\n";
        }
        return rosterString;
    }

    public static String toTable() {
        String rosterTable = "<table>";
        for (RosterEntry rosterEntry : rosterEntries) {
            rosterTable += "\t<tr class='body'><td>"+rosterEntry.getLastName()+"</td>\n";
            rosterTable += "\t\t<td>"+rosterEntry.getFirstName()+"</td>\n";
            rosterTable += "\t\t<td>"+rosterEntry.getStudentId()+"</td>\n";
            rosterTable += "\t\t<td>"+rosterEntry.getTeamNumber()+"</td></tr>\n";
        }
        return rosterTable+"</table>\n";
    }

	// Return the entire ArrayList of RosterEntry objects 
	public static CopyOnWriteArrayList<RosterEntry> getEntries() {
		return rosterEntries;
	}
        
    // Assist plural GET request (browse)
    public static CopyOnWriteArrayList<RosterEntry> findEntry(String fieldType, String fieldValue) {
        // ArrayList object to hold all matching RosterEntry objects
        CopyOnWriteArrayList<RosterEntry> returnEntries = new CopyOnWriteArrayList<RosterEntry>();
        if (fieldType.toUpperCase().equals("FIRSTNAME")) {
        	for (RosterEntry rosterEntry : rosterEntries) {
                if (rosterEntry.getFirstName().toUpperCase().equals(fieldValue.toUpperCase())) {
                    returnEntries.add(rosterEntry);
                } 
            }
        } else if (fieldType.toUpperCase().equals("LASTNAME")) {
            for (RosterEntry rosterEntry : rosterEntries) {
                if (rosterEntry.getLastName().toUpperCase().equals(fieldValue.toUpperCase())) {
                    returnEntries.add(rosterEntry);
                }
        	}
        } else if (fieldType.toUpperCase().equals("ENTRYID")) {
                returnEntries.add(findStudent(fieldValue));
        } else if (fieldType.toUpperCase().equals("TEAMNUMBER")) {
            CopyOnWriteArrayList<RosterEntry> tmpEntries = findTeam(fieldValue);
            for (RosterEntry tmpEntry : tmpEntries) {
                returnEntries.add(tmpEntry);
            }
        } else {
            RosterEntry newEntry = new RosterEntry();
            newEntry.setLastName(fieldType.toUpperCase());
            newEntry.setFirstName("is not an");
            newEntry.setStudentId("option...");
            newEntry.setTeamNumber(1234);
            returnEntries.add(newEntry);
        }
        return returnEntries;
    }

	// Assist singular GET request
	public static RosterEntry findStudent(String value) {
		// Object to hold
		RosterEntry returnEntry = null;
		for (RosterEntry rosterEntry : rosterEntries) {
            if (rosterEntry.getStudentId().toUpperCase().equals(value.toUpperCase())) {
                returnEntry = rosterEntry;
            }
		}
		return returnEntry;
	}
        
        // Assist plural GET request
	public static CopyOnWriteArrayList<RosterEntry> findTeam(String value) {
        // ArrayList object to hold all matching RosterEntry objects
        CopyOnWriteArrayList<RosterEntry> returnEntry = new CopyOnWriteArrayList<RosterEntry>();
        for (RosterEntry rosterEntry : rosterEntries) {
            if (rosterEntry.getTeamNumber() == Integer.parseInt(value)) {
                returnEntry.add(rosterEntry);
            }
        }
        return returnEntry;
	}

	// Assist singular/plural POST request
	public static void addEntry(RosterEntry rosterEntry) {
		rosterEntries.add(rosterEntry);
	}

	// Assist singular/plural DELETE request
	public static void deleteEntry(RosterEntry rosterEntry) {
                rosterEntries.remove(rosterEntry);
	}
}