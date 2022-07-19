package LOGIC.Person;

import java.util.ArrayList;

public class Professor {

    // properties
    private String name;
    private String nationalCode;
    private String professorNumber;
    private String phoneNumber;
    private String email;
    private String role;
    private CollegeName collegeName;
    private int roomNumber;
    private String image;
    private ProfessorDegree professorDegree;
    private ArrayList<Course> courses;
    private String lastEntry;

    // getters
    public String getName() { return name; }
    public String getNationalCode() { return nationalCode; }
    public String getProfessorNumber() { return professorNumber; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
    public CollegeName getCollegeName() { return collegeName; }
    public int getRoomNumber() { return roomNumber; }
    public String getImage() { return image; }
    public ProfessorDegree getProfessorDegree() { return professorDegree; }
    public ArrayList<Course> getCourses() { return courses; }
    public String getLastEntry() { return lastEntry; }

    // setters
    public void setName(String name) { this.name = name; }
    public void setNationalCode(String nationalCode) { this.nationalCode = nationalCode; }
    public void setProfessorNumber(String professorNumber) { this.professorNumber = professorNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setEmail(String email) { this.email = email; }
    public void setRole(String role) { this.role = role; }
    public void setCollegeName(CollegeName collegeName) { this.collegeName = collegeName; }
    public void setRoomNumber(int roomNumber) { this.roomNumber = roomNumber; }
    public void setImage(String image) { this.image = image; }
    public void setProfessorDegree(ProfessorDegree professorDegree) { this.professorDegree = professorDegree; }
    public void setCourses(ArrayList<Course> courses) { this.courses = courses; }
    public void setLastEntry(String lastEntry) { this.lastEntry = lastEntry; }

    // constructor
    public Professor(String name, String nationalCode, String professorNumber, String phoneNumber, String email, String role,
                     CollegeName collegeName, int roomNumber, String image, ProfessorDegree professorDegree) {
        this.name = name;
        this.nationalCode = nationalCode;
        this.professorNumber = professorNumber;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.role = role;
        this.collegeName = collegeName;
        this.roomNumber = roomNumber;
        this.image = image;
        this.professorDegree = professorDegree;
        this.courses = new ArrayList<>();
        this.lastEntry = null;
    }
}
