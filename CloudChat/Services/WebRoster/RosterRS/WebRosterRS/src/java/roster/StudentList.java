package roster;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import javax.xml.bind.annotation.XmlElement; 
import javax.xml.bind.annotation.XmlElementWrapper; 
import javax.xml.bind.annotation.XmlRootElement;
import roster.Student;

@XmlRootElement(name = "studentList")
public class StudentList {
    private List<Student> students;
    private AtomicInteger studId;

    public StudentList() { 
	students = new CopyOnWriteArrayList<Student>();
        studId=new AtomicInteger();
    }

    @XmlElement 
    public List<Student> getStudents() { 
	return this.students;
    } 
    public void setStudents(List<Student> studs) { 
	this.students = studs;
    }

    @Override
    public String toString() {
	String s = "";
	for (Student p : students) s += p.toString();
	return s;
    }

    public Student find(String id) {
	Student stud = null;
	// Search the list -- for now, the list is short enough that
	// a linear search is ok but binary search would be better if the
	// list got to be an order-of-magnitude larger in size.
	for (Student p : students) {
	    if (p.getPsuID().equals(id)) {
		stud = p;
		break;
	    }
	}	
	return stud;
    }
    public int add(String lastName, String firstName, String psuID, String teamNumber) {
	int id = studId.incrementAndGet();
        Student p = new Student();
        p.updatePsuID(psuID);
        p.updateLastName(lastName);
        p.updateFirstName(firstName);
        p.updateTeamNumber(teamNumber);
	students.add(p);
        return id;
    }
}