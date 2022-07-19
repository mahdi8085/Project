package LOGIC.Person;

import java.util.ArrayList;

public class Course {

    // properties
    private String name;
    private int id;
    private int credit;
    private String professorName;
    private String professorNumber;
    private DaysOfWeek classDay;
    private String examDate;
    private CollegeName collegeName;
    private StudentType studentType;
    private ArrayList<Student> students;
    private ArrayList<Course> prerequisites;
    private ArrayList<Course> corequistites;

    // getters
    public String getName() { return name; }
    public int getId() { return id; }
    public int getCredit() { return credit; }
    public String getProfessorName() { return professorName; }
    public String getProfessorNumber() { return professorNumber; }
    public DaysOfWeek getClassDay() { return classDay; }
    public String getExamDate() { return examDate; }
    public CollegeName getCollegeName() { return collegeName; }
    public StudentType getStudentType() { return studentType; }
    public ArrayList<Student> getStudents() { return students; }
    public ArrayList<Course> getPrerequisites() { return prerequisites; }
    public ArrayList<Course> getCorequistites() { return corequistites; }

    // setters
    public void setName(String name) { this.name = name; }
    public void setId(int id) { this.id = id; }
    public void setCredit(int credit) { this.credit = credit; }
    public void setProfessorName(String professorName) { this.professorName = professorName; }
    public void setProfessorNumber(String professorNumber) { this.professorNumber = professorNumber; }
    public void setClassDay(DaysOfWeek classDay) { this.classDay = classDay; }
    public void setExamDate(String examDate) { this.examDate = examDate; }
    public void setCollegeName(CollegeName collegeName) { this.collegeName = collegeName; }
    public void setStudentType(StudentType studentType) { this.studentType = studentType; }
    public void setStudents(ArrayList<Student> students) { this.students = students; }
    public void setPrerequisites(ArrayList<Course> prerequisites) { this.prerequisites = prerequisites; }
    public void setCorequistites(ArrayList<Course> corequistites) { this.corequistites = corequistites; }

    // constructor
    public Course(String name, int id, int credit, Professor professor, DaysOfWeek classDay, String examDate, CollegeName collegeName, StudentType studentType) {
        this.name = name;
        this.id = id;
        this.credit = credit;
        this.professorName = professor.getName();
        this.professorNumber = professor.getProfessorNumber();
        this.classDay = classDay;
        this.examDate = examDate;
        this.collegeName = collegeName;
        this.studentType = studentType;
        this.prerequisites = new ArrayList<>();
        this.corequistites = new ArrayList<>();
        this.students = new ArrayList<>();
    }
}
