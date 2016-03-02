package restlet;

public class RosterEntry {
    private String firstName;
    private String lastName;
    private String studentId;
    private Integer teamNumber;

    public RosterEntry() { }
    
    // Accessors and Mutators
    public String getFirstName() {
        return this.firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    public String getStudentId() {
        return this.studentId;
    }
    public Integer getTeamNumber() {
        return this.teamNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    public void setTeamNumber(Integer teamNumber) {
        this.teamNumber = teamNumber;
    }
}