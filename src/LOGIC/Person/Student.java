package LOGIC.Person;

import java.util.ArrayList;

public class Student {

    // properties
    private String name;
    private String nationalCode;
    private String studentNumber;
    private String phoneNumber;
    private String email;
    private double GPA;
    private CollegeName collegeName;
    private Professor supervisor;
    private int enteringYear;
    private String image;
    private StudentType studentType;
    private EducationalStatus educationalStatus;
    private ArrayList<Course> courses;
    private String lastEntry;

    // getters
    public String getName() { return name; }
    public String getNationalCode() { return nationalCode; }
    public String getStudentNumber() { return studentNumber; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getEmail() { return email; }
    public double getGPA() { return GPA; }
    public CollegeName getCollegeName() { return collegeName; }
    public Professor getSupervisor() { return supervisor; }
    public int getEnteringYear() { return enteringYear; }
    public String getImage() { return image; }
    public StudentType getStudentType() { return studentType; }
    public EducationalStatus getEducationalStatus() { return educationalStatus; }
    public ArrayList<Course> getCourses() { return courses; }
    public String getLastEntry() { return lastEntry; }

    // setters
    public void setName(String name) { this.name = name; }
    public void setNationalCode(String nationalCode) { this.nationalCode = nationalCode; }
    public void setStudentNumber(String studentNumber) { this.studentNumber = studentNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setEmail(String email) { this.email = email; }
    public void setGPA(double GPA) { this.GPA = GPA; }
    public void setCollegeName(CollegeName collegeName) { this.collegeName = collegeName; }
    public void setSupervisor(Professor supervisor) { this.supervisor = supervisor; }
    public void setEnteringYear(int enteringYear) { this.enteringYear = enteringYear; }
    public void setImage(String image) { this.image = image; }
    public void setStudentType(StudentType studentType) { this.studentType = studentType; }
    public void setEducationalStatus(EducationalStatus educationalStatus) { this.educationalStatus = educationalStatus; }
    public void setCourses(ArrayList<Course> courses) { this.courses = courses; }
    public void setLastEntry(String lastEntry) { this.lastEntry = lastEntry; }

    // constructor
    public Student(String name, String nationalCode, String studentNumber, String phoneNumber,
                   String email, double GPA, CollegeName collegeName, Professor supervisor,
                   int enteringYear, String image, StudentType studentType, EducationalStatus educationalStatus) {
        this.name = name;
        this.nationalCode = nationalCode;
        this.studentNumber = studentNumber;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.GPA = GPA;
        this.collegeName = collegeName;
        this.supervisor = supervisor;
        this.enteringYear = enteringYear;
        this.image = image;
        this.studentType = studentType;
        this.educationalStatus = educationalStatus;
        this.courses = new ArrayList<>();
        this.lastEntry = null;
    }
}
