package GUI;

import LOGIC.Person.*;
import com.google.gson.Gson;

import java.io.*;

public class Resources {

    public Resources() throws IOException {

        Gson gson = new Gson();

        student1.getCourses().add(course1);
        student1.getCourses().add(course3);

        president2.getCourses().add(course10);
        president2.getCourses().add(course11);

        professor3.getCourses().add(course5);
        professor1.getCourses().add(course6);

        student13.getCourses().add(course6);
        student13.getCourses().add(course8);

        String json = gson.toJson(student1);
        BufferedWriter writer1 = new BufferedWriter(new FileWriter("src\\LOGIC\\Files\\Students.json"));
        writer1.write(json);

        json = gson.toJson(student2);
        writer1.write("\n" + json);

        json = gson.toJson(student3);
        writer1.write("\n" + json);

        json = gson.toJson(student4);
        writer1.write("\n" + json);

        json = gson.toJson(student5);
        writer1.write("\n" + json);

        json = gson.toJson(student0);
        writer1.write("\n" + json);

        json = gson.toJson(student13);
        writer1.write("\n" + json);
        writer1.close();

        json = gson.toJson(professor1);
        BufferedWriter writer2 = new BufferedWriter(new FileWriter("src\\LOGIC\\Files\\Professors.json"));
        writer2.write(json);

        json = gson.toJson(professor2);
        writer2.write("\n" + json);

        json = gson.toJson(professor3);
        writer2.write("\n" + json);
        writer2.close();

        json = gson.toJson(deputy1);
        BufferedWriter writer3 = new BufferedWriter(new FileWriter("src\\LOGIC\\Files\\Deputies.json"));
        writer3.write(json);

        json = gson.toJson(deputy2);
        writer3.write("\n" + json);
        writer3.close();

        json = gson.toJson(president1);
        BufferedWriter writer4 = new BufferedWriter(new FileWriter("src\\LOGIC\\Files\\Presidents.json"));
        writer4.write(json);

        json = gson.toJson(president2);
        writer4.write("\n" + json);
        writer4.close();

        course1.getPrerequisites().add(course2);
        course7.getPrerequisites().add(course5);

        json = gson.toJson(course1);
        BufferedWriter writer5 = new BufferedWriter(new FileWriter("src\\LOGIC\\Files\\Courses.json"));
        writer5.write(json);

        json = gson.toJson(course2);
        writer5.write("\n" + json);

        json = gson.toJson(course3);
        writer5.write("\n" + json);

        json = gson.toJson(course4);
        writer5.write("\n" + json);

        json = gson.toJson(course5);
        writer5.write("\n" + json);

        json = gson.toJson(course6);
        writer5.write("\n" + json);

        json = gson.toJson(course7);
        writer5.write("\n" + json);

        json = gson.toJson(course8);
        writer5.write("\n" + json);

        json = gson.toJson(course10);
        writer5.write("\n" + json);

        json = gson.toJson(course11);
        writer5.write("\n" + json);
        writer5.close();
    }

    private Deputy deputy1 = new Deputy("Ali Hakaki", "1124800924", "77106639",
            "09124119452", "hakaki@gmail.com", "deputy", CollegeName.computer, 12,
            "GUI\\Images\\deputyProfile.png", ProfessorDegree.associateProfessor);

    private Deputy deputy2 = new Deputy("Mohsen Tajik", "1124800123", "00106639",
            "09914119452", "tajik@gmail.com", "deputy", CollegeName.electrical, 13,
            "GUI\\Images\\deputyProfile.png", ProfessorDegree.fullProfessor);

    private Professor professor1 = new Professor("Ali Nourani", "0024800925", "88106639",
            "09125232900", "nourani@gmail.com", "professor", CollegeName.mechanical, 23,
            "GUI\\Images\\professorProfile.png", ProfessorDegree.assistantProfessor);

    private Professor professor2 = new Professor("Mojtaba Tefagh", "9924800925", "55106639",
            "09195232900", "tefagh@gmail.com", "professor", CollegeName.computer, 6,
            "GUI\\Images\\professorProfile.png", ProfessorDegree.associateProfessor);

    private Professor professor3 = new Professor("Mojtaba Rezai", "9924800926", "55116639",
            "09195232911", "M.R.@gmail.com", "professor", CollegeName.chemistry, 8,
            "GUI\\Images\\professorProfile.png", ProfessorDegree.fullProfessor);

    private Student student2 = new Student("Aria Tahami", "0024700929", "99108888",
            "09381393766", "mahdi80@gmail.com", 17.03, CollegeName.computer,
            professor2, 1400, "GUI\\Images\\studentProfile.png", StudentType.bachelor, EducationalStatus.studying);

    private Student student1 = new Student("Mahdi Shahrabi", "0024700924", "99106639",
            "09371393766", "mahdi8085@gmail.com", 19.57, CollegeName.mechanical,
            professor1, 1399, "GUI\\Images\\studentProfile.png", StudentType.master, EducationalStatus.studying);

    private Student student0 = new Student("Mahdi Mohamadi", "0024700945", "99106649",
            "09371393766", "mahdim@gmail.com", 19.07, CollegeName.mechanical,
            professor1, 1399, "GUI\\Images\\studentProfile.png", StudentType.bachelor, EducationalStatus.studying);

    private Student student3 = new Student("Mobina Ghasemi", "0024700900", "99106600",
            "09371393767", "ghasemi@gmail.com", 18.7, CollegeName.chemistry,
            professor3, 1399, "GUI\\Images\\studentProfile.png", StudentType.master, EducationalStatus.studying);

    private Student student4 = new Student("Mahdi Mirzai", "0024700000", "99106611",
            "09381393766", "mirza.m@gmail.com", 12.9, CollegeName.civil,
            deputy1, 1399, "GUI\\Images\\studentProfile.png", StudentType.doctor, EducationalStatus.studying);

    private Student student5 = new Student("Reza Khosrozade", "0924700924", "99106622",
            "09351393766", "khosro@gmail.com", 19.5, CollegeName.mechanical,
            professor2, 1399, "GUI\\Images\\studentProfile.png", StudentType.doctor, EducationalStatus.studying);

    private Student student13 = new Student("Reza Ghomri", "4924700924", "76106622",
            "09351393567", "rezagh@gmail.com", 15.9, CollegeName.electrical,
            deputy2, 1398, "GUI\\Images\\studentProfile.png", StudentType.bachelor, EducationalStatus.studying);

    private President president1 = new President("Navid Arjmand", "0029800925", "66106639",
            "09395232900", "arjmand@gmail.com", "president", CollegeName.electrical, 55,
            "GUI\\Images\\presidentProfile.png", ProfessorDegree.fullProfessor);

    private President president2 = new President("Ali Dehghan", "0899800925", "66106939",
            "09395792900", "DAdehghan@gmail.com", "president", CollegeName.civil, 55,
            "GUI\\Images\\presidentProfile.png", ProfessorDegree.fullProfessor);

    private Course course1 = new Course("Basic Programming", 1001, 3, professor2,
            DaysOfWeek.saturday, "2022-05-21", CollegeName.computer, StudentType.bachelor);

    private Course course2 = new Course("Advanced Programming", 1002, 4, professor2,
            DaysOfWeek.monday, "2022-05-22", CollegeName.computer, StudentType.bachelor);

    private Course course3 = new Course("Machine Design", 2001, 3, professor2,
            DaysOfWeek.tuesday, "2022-05-30", CollegeName.mechanical, StudentType.bachelor);

    private Course course6 = new Course("Strength Material", 2002, 2, professor1,
            DaysOfWeek.wednesday, "2022-05-28", CollegeName.mechanical, StudentType.bachelor);

    private Course course4 = new Course("Logic Circuit", 3001, 2, professor1,
            DaysOfWeek.monday, "2022-06-03", CollegeName.electrical, StudentType.master);

    private Course course8 = new Course("Mathematics", 3002, 1, deputy2,
            DaysOfWeek.monday, "2022-06-09", CollegeName.electrical, StudentType.master);

    private Course course5 = new Course("Materials", 4001, 3, professor3,
            DaysOfWeek.wednesday, "2022-06-06", CollegeName.chemistry, StudentType.bachelor);

    private Course course7 = new Course("Materials II", 4001, 2, professor3,
            DaysOfWeek.thursday, "2022-06-08", CollegeName.chemistry, StudentType.doctor);

    private Course course10 = new Course("Economy", 5001, 1, president2,
            DaysOfWeek.saturday, "2022-06-11", CollegeName.civil, StudentType.doctor);

    private Course course11 = new Course("Solid Mechanics", 5002, 3, president2,
            DaysOfWeek.thursday, "2022-06-12", CollegeName.civil, StudentType.bachelor);
}
