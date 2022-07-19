package LOGIC.ProfessorMenu;

import GUI.Main;
import LOGIC.Person.*;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DeputyEditCourses implements Initializable {

    static Logger log = LogManager.getLogger(DeputyEditCourses.class);

    // fields for moving lists
    private ArrayList<Student> studentsMove;
    private ArrayList<Course> prerequisitesMove;
    private ArrayList<Course> corequistitesMove;

    @FXML
    private Button backButton;
    @FXML
    private Button mainMenuButton;
    @FXML
    private Button addButton;
    @FXML
    private Button removeButton;
    @FXML
    private Button editButton;
    @FXML
    private Button applyEditButton;
    @FXML
    private TextField nameField;
    @FXML
    private TextField idField;
    @FXML
    private TextField creditField;
    @FXML
    private TextField professorNumberField;
    @FXML
    private TextField examDateField;
    @FXML
    private ChoiceBox<DaysOfWeek> daysOfWeekChoiceBox;
    @FXML
    private ChoiceBox<CollegeName> collegeNameChoiceBox;
    @FXML
    private ChoiceBox<StudentType> studentTypeChoiceBox;
    @FXML
    private Label wrongInformation;

    public void back(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("ProfessorMenu\\DeputyCourses.fxml");
    }

    public void mainMenu(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("DeputyMain.fxml");
    }

    public void add(ActionEvent event) throws IOException {

        // get user
        Gson tempGson = new Gson();
        BufferedReader userReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\LoggedInUser.json"));
        Professor user = tempGson.fromJson(userReader.readLine(), Professor.class);
        userReader.close();

        // check everything
        if (nameField.getText().isEmpty() || idField.getText().isEmpty() || creditField.getText().isEmpty()
                || professorNumberField.getText().isEmpty() || examDateField.getText().isEmpty()
                || daysOfWeekChoiceBox.getValue() == null || collegeNameChoiceBox.getValue() == null
                || studentTypeChoiceBox.getValue() == null || user.getCollegeName() != collegeNameChoiceBox.getValue()) {
            nameField.setText("");
            idField.setText("");
            creditField.setText("");
            professorNumberField.setText("");
            examDateField.setText("");
            daysOfWeekChoiceBox.setValue(null);
            collegeNameChoiceBox.setValue(null);
            studentTypeChoiceBox.setValue(null);
            wrongInformation.setText("Invalid inputs !");
        }
        else {
            int help = 0;
            BufferedWriter tempWriter = new BufferedWriter(new FileWriter("src\\LOGIC\\Files\\Temp.json"));
            if (checkValidity(Integer.parseInt(idField.getText()), Integer.parseInt(creditField.getText()),
                    professorNumberField.getText(), examDateField.getText())) {

                Gson gson = new Gson();

                // check course id
                BufferedReader courseReaderHelp = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Courses.json"));
                String line;
                while ((line = courseReaderHelp.readLine()) != null) {
                    Course tempCourse = gson.fromJson(line, Course.class);
                    if (tempCourse.getId() == Integer.parseInt(idField.getText())) {
                        nameField.setText("");
                        idField.setText("");
                        creditField.setText("");
                        professorNumberField.setText("");
                        examDateField.setText("");
                        daysOfWeekChoiceBox.setValue(null);
                        collegeNameChoiceBox.setValue(null);
                        studentTypeChoiceBox.setValue(null);
                        wrongInformation.setText("Invalid inputs !");
                        return;
                    }
                }

                BufferedReader courseReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Courses.json"));
                while ((line = courseReader.readLine()) != null) {
                    Course tempCourse = gson.fromJson(line, Course.class);
                    String json = gson.toJson(tempCourse);
                    if (help == 0) {
                        tempWriter.write(json);
                        help = 1;
                    } else {
                        tempWriter.write("\n" + json);
                    }
                }
                courseReader.close();

                // find related professor
                Professor professor = null;
                BufferedReader professorReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Professors.json"));
                while ((line = professorReader.readLine()) != null) {
                    Professor tempProfessor = gson.fromJson(line, Professor.class);
                    if (tempProfessor.getProfessorNumber().equals(professorNumberField.getText())) {
                        professor = tempProfessor;
                    }
                }
                professorReader.close();
                BufferedReader deputyReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Deputies.json"));
                while ((line = deputyReader.readLine()) != null) {
                    Professor tempDeputy = gson.fromJson(line, Professor.class);
                    if (tempDeputy.getProfessorNumber().equals(professorNumberField.getText())) {
                        professor = tempDeputy;
                    }
                }
                deputyReader.close();
                BufferedReader presidentReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Presidents.json"));
                while ((line = presidentReader.readLine()) != null) {
                    Professor tempPresident = gson.fromJson(line, Professor.class);
                    if (tempPresident.getProfessorNumber().equals(professorNumberField.getText())) {
                        professor = tempPresident;
                    }
                }
                presidentReader.close();

                // add new course
                assert professor != null;
                Course newCourse = new Course(nameField.getText(), Integer.parseInt(idField.getText()),
                        Integer.parseInt(creditField.getText()), professor, daysOfWeekChoiceBox.getValue(),
                        examDateField.getText(), collegeNameChoiceBox.getValue(), studentTypeChoiceBox.getValue());
                String course = gson.toJson(newCourse);
                tempWriter.write("\n" + course);
                tempWriter.close();

                // copy all courses from temp file to courses file
                int anotherHelp = 0;
                BufferedWriter newCourseWriter = new BufferedWriter(new FileWriter("src\\LOGIC\\Files\\Courses.json"));
                BufferedReader newCourseReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Temp.json"));
                while ((line = newCourseReader.readLine()) != null) {
                    Course tempCourse = gson.fromJson(line, Course.class);
                    String json = gson.toJson(tempCourse);
                    if (anotherHelp == 0) {
                        newCourseWriter.write(json);
                        anotherHelp = 1;
                    } else {
                        newCourseWriter.write("\n" + json);
                    }
                }
                nameField.setText("");
                idField.setText("");
                creditField.setText("");
                professorNumberField.setText("");
                examDateField.setText("");
                daysOfWeekChoiceBox.setValue(null);
                collegeNameChoiceBox.setValue(null);
                studentTypeChoiceBox.setValue(null);
                newCourseReader.close();
                newCourseWriter.close();
                wrongInformation.setText("");
                log.info("New course added");
            }
            else {
                nameField.setText("");
                idField.setText("");
                creditField.setText("");
                professorNumberField.setText("");
                examDateField.setText("");
                daysOfWeekChoiceBox.setValue(null);
                collegeNameChoiceBox.setValue(null);
                studentTypeChoiceBox.setValue(null);
                wrongInformation.setText("Invalid inputs !");
            }
        }
    }

    public void remove(ActionEvent event) throws IOException {

        // get user
        Gson tempGson = new Gson();
        BufferedReader userReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\LoggedInUser.json"));
        Professor user = tempGson.fromJson(userReader.readLine(), Professor.class);
        userReader.close();

        // remove course
        if (!nameField.getText().isEmpty() || idField.getText().isEmpty() || !creditField.getText().isEmpty()
                || !professorNumberField.getText().isEmpty() || !examDateField.getText().isEmpty()
                || daysOfWeekChoiceBox.getValue() != null || collegeNameChoiceBox.getValue() != null
                || studentTypeChoiceBox.getValue() != null) {
            nameField.setText("");
            idField.setText("");
            creditField.setText("");
            professorNumberField.setText("");
            examDateField.setText("");
            daysOfWeekChoiceBox.setValue(null);
            collegeNameChoiceBox.setValue(null);
            studentTypeChoiceBox.setValue(null);
            wrongInformation.setText("Invalid inputs !");
        }
        else {
            Gson gson = new Gson();
            int help = 0;
            boolean isInCourses = false;
            BufferedWriter tempWriter = new BufferedWriter(new FileWriter("src\\LOGIC\\Files\\Temp.json"));
            BufferedReader courseReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Courses.json"));
            String line;
            while ((line = courseReader.readLine()) != null) {
                Course tempCourse = gson.fromJson(line, Course.class);
                String json = gson.toJson(tempCourse);
                if (user.getCollegeName() != tempCourse.getCollegeName() && tempCourse.getId() == Integer.parseInt(idField.getText())) {
                    nameField.setText("");
                    idField.setText("");
                    creditField.setText("");
                    professorNumberField.setText("");
                    examDateField.setText("");
                    daysOfWeekChoiceBox.setValue(null);
                    collegeNameChoiceBox.setValue(null);
                    studentTypeChoiceBox.setValue(null);
                    wrongInformation.setText("Invalid inputs !");
                    tempWriter.close();
                    courseReader.close();
                    return;
                }
                if (tempCourse.getId() == Integer.parseInt(idField.getText())) {
                    isInCourses = true;
                }
                if (tempCourse.getId() != Integer.parseInt(idField.getText())) {
                    if (help == 0) {
                        tempWriter.write(json);
                        help = 1;
                    } else {
                        tempWriter.write("\n" + json);
                    }
                }
            }
            tempWriter.close();
            courseReader.close();

            // copy all courses from temp file to courses file
            int anotherHelp = 0;
            BufferedWriter newCourseWriter = new BufferedWriter(new FileWriter("src\\LOGIC\\Files\\Courses.json"));
            BufferedReader newCourseReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Temp.json"));
            while ((line = newCourseReader.readLine()) != null) {
                Course tempCourse = gson.fromJson(line, Course.class);
                String json = gson.toJson(tempCourse);
                if (anotherHelp == 0) {
                    newCourseWriter.write(json);
                    anotherHelp = 1;
                } else {
                    newCourseWriter.write("\n" + json);
                }
            }
            newCourseReader.close();
            newCourseWriter.close();
            nameField.setText("");
            idField.setText("");
            creditField.setText("");
            professorNumberField.setText("");
            examDateField.setText("");
            daysOfWeekChoiceBox.setValue(null);
            collegeNameChoiceBox.setValue(null);
            studentTypeChoiceBox.setValue(null);
            wrongInformation.setText("");
            log.info("Course deleted");
            if (!isInCourses) {
                nameField.setText("");
                idField.setText("");
                creditField.setText("");
                professorNumberField.setText("");
                examDateField.setText("");
                daysOfWeekChoiceBox.setValue(null);
                collegeNameChoiceBox.setValue(null);
                studentTypeChoiceBox.setValue(null);
                wrongInformation.setText("Invalid inputs !");
            }
        }
    }

    public void edit(ActionEvent event) throws IOException {

        // get user
        Gson tempGson = new Gson();
        BufferedReader userReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\LoggedInUser.json"));
        Professor user = tempGson.fromJson(userReader.readLine(), Professor.class);
        userReader.close();

        // remove course
        if (!nameField.getText().isEmpty() || idField.getText().isEmpty() || !creditField.getText().isEmpty()
                || !professorNumberField.getText().isEmpty() || !examDateField.getText().isEmpty()
                || daysOfWeekChoiceBox.getValue() != null || collegeNameChoiceBox.getValue() != null
                || studentTypeChoiceBox.getValue() != null) {
            nameField.setText("");
            idField.setText("");
            creditField.setText("");
            professorNumberField.setText("");
            examDateField.setText("");
            daysOfWeekChoiceBox.setValue(null);
            collegeNameChoiceBox.setValue(null);
            studentTypeChoiceBox.setValue(null);
            wrongInformation.setText("Invalid inputs !");
        }
        else {
            Gson gson = new Gson();
            BufferedReader courseReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Courses.json"));
            String line;
            while ((line = courseReader.readLine()) != null) {
                Course tempCourse = gson.fromJson(line, Course.class);
                if (tempCourse.getId() == Integer.parseInt(idField.getText()) && tempCourse.getCollegeName() == user.getCollegeName()) {
                    nameField.setText(tempCourse.getName());
                    idField.setEditable(false);
                    creditField.setText(String.valueOf(tempCourse.getCredit()));
                    professorNumberField.setText(tempCourse.getProfessorNumber());
                    examDateField.setText(tempCourse.getExamDate());
                    daysOfWeekChoiceBox.setValue(tempCourse.getClassDay());
                    collegeNameChoiceBox.setValue(tempCourse.getCollegeName());
                    studentTypeChoiceBox.setValue(tempCourse.getStudentType());
                    addButton.setDisable(true);
                    removeButton.setDisable(true);
                    editButton.setDisable(true);
                    applyEditButton.setDisable(false);
                    this.studentsMove = tempCourse.getStudents();
                    this.prerequisitesMove = tempCourse.getPrerequisites();
                    this.corequistitesMove = tempCourse.getCorequistites();
                    wrongInformation.setText("");
                    return;
                }
            }
            nameField.setText("");
            idField.setText("");
            creditField.setText("");
            professorNumberField.setText("");
            examDateField.setText("");
            daysOfWeekChoiceBox.setValue(null);
            collegeNameChoiceBox.setValue(null);
            studentTypeChoiceBox.setValue(null);
            wrongInformation.setText("Invalid inputs !");
        }
    }

    public void applyEdit(ActionEvent event) throws IOException {
        idField.setEditable(true);
        addButton.setDisable(false);
        removeButton.setDisable(false);
        editButton.setDisable(false);
        applyEditButton.setDisable(true);

        // get user
        Gson tempGson = new Gson();
        BufferedReader userReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\LoggedInUser.json"));
        Professor user = tempGson.fromJson(userReader.readLine(), Professor.class);
        userReader.close();

        if (nameField.getText().isEmpty() || idField.getText().isEmpty() || creditField.getText().isEmpty()
                || professorNumberField.getText().isEmpty() || examDateField.getText().isEmpty()
                || daysOfWeekChoiceBox.getValue() == null || collegeNameChoiceBox.getValue() == null
                || studentTypeChoiceBox.getValue() == null || user.getCollegeName() != collegeNameChoiceBox.getValue()) {
            nameField.setText("");
            idField.setText("");
            creditField.setText("");
            professorNumberField.setText("");
            examDateField.setText("");
            daysOfWeekChoiceBox.setValue(null);
            collegeNameChoiceBox.setValue(null);
            studentTypeChoiceBox.setValue(null);
            wrongInformation.setText("Invalid inputs !");
        }
        else {
            if (checkValidity(Integer.parseInt(idField.getText()), Integer.parseInt(creditField.getText()),
                    professorNumberField.getText(), examDateField.getText())) {
                Gson gson = new Gson();

                // remove ex course
                int help = 0;
                BufferedWriter tempWriter = new BufferedWriter(new FileWriter("src\\LOGIC\\Files\\Temp.json"));
                BufferedReader courseReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Courses.json"));
                String line;
                while ((line = courseReader.readLine()) != null) {
                    Course tempCourse = gson.fromJson(line, Course.class);
                    String json = gson.toJson(tempCourse);
                    if (tempCourse.getId() != Integer.parseInt(idField.getText())) {
                        if (help == 0) {
                            tempWriter.write(json);
                            help = 1;
                        } else {
                            tempWriter.write("\n" + json);
                        }
                    }
                }
                tempWriter.close();
                courseReader.close();

                // find related professor
                Professor professor = null;
                BufferedReader professorReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Professors.json"));
                while ((line = professorReader.readLine()) != null) {
                    Professor tempProfessor = gson.fromJson(line, Professor.class);
                    if (tempProfessor.getProfessorNumber().equals(professorNumberField.getText())) {
                        professor = tempProfessor;
                    }
                }
                professorReader.close();
                BufferedReader deputyReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Deputies.json"));
                while ((line = deputyReader.readLine()) != null) {
                    Professor tempDeputy = gson.fromJson(line, Professor.class);
                    if (tempDeputy.getProfessorNumber().equals(professorNumberField.getText())) {
                        professor = tempDeputy;
                    }
                }
                deputyReader.close();
                BufferedReader presidentReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Presidents.json"));
                while ((line = presidentReader.readLine()) != null) {
                    Professor tempPresident = gson.fromJson(line, Professor.class);
                    if (tempPresident.getProfessorNumber().equals(professorNumberField.getText())) {
                        professor = tempPresident;
                    }
                }
                presidentReader.close();

                // make new course
                assert professor != null;
                Course newCourse = new Course(nameField.getText(), Integer.parseInt(idField.getText()),
                        Integer.parseInt(creditField.getText()), professor, daysOfWeekChoiceBox.getValue(),
                        examDateField.getText(), collegeNameChoiceBox.getValue(), studentTypeChoiceBox.getValue());
                newCourse.setStudents(this.studentsMove);
                newCourse.setPrerequisites(this.prerequisitesMove);
                newCourse.setCorequistites(this.corequistitesMove);

                // copy all courses from temp file to courses file
                int anotherHelp = 0;
                BufferedWriter newCourseWriter = new BufferedWriter(new FileWriter("src\\LOGIC\\Files\\Courses.json"));
                BufferedReader newCourseReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Temp.json"));
                while ((line = newCourseReader.readLine()) != null) {
                    Course tempCourse = gson.fromJson(line, Course.class);
                    String json = gson.toJson(tempCourse);
                    if (anotherHelp == 0) {
                        newCourseWriter.write(json);
                        anotherHelp = 1;
                    } else {
                        newCourseWriter.write("\n" + json);
                    }
                }
                String course = gson.toJson(newCourse);
                newCourseWriter.write("\n" + course);
                newCourseReader.close();
                newCourseWriter.close();
                nameField.setText("");
                idField.setText("");
                creditField.setText("");
                professorNumberField.setText("");
                examDateField.setText("");
                daysOfWeekChoiceBox.setValue(null);
                collegeNameChoiceBox.setValue(null);
                studentTypeChoiceBox.setValue(null);
                wrongInformation.setText("");
                log.info("Course information edited");
            }
            else {
                nameField.setText("");
                idField.setText("");
                creditField.setText("");
                professorNumberField.setText("");
                examDateField.setText("");
                daysOfWeekChoiceBox.setValue(null);
                collegeNameChoiceBox.setValue(null);
                studentTypeChoiceBox.setValue(null);
                wrongInformation.setText("Invalid inputs !");
            }
        }

    }

    private boolean checkValidity(int id, int credit, String professorNumber, String examDate) throws IOException {
        if (credit > 4 || credit < 1 || id < 1000 || id > 6000) {
            return false;
        }
        else if (examDate.length() != 10) {
            return false;
        }
        else if (examDate.charAt(0) != '2' || examDate.charAt(1) != '0' || examDate.charAt(2) != '2'
                || examDate.charAt(3) != '2' || examDate.charAt(4) != '-' || examDate.charAt(7) != '-'
                || ((examDate.charAt(5) - 48) * 10 + (examDate.charAt(6) - 48) > 12)
                || ((examDate.charAt(5) - 48) * 10 + (examDate.charAt(6) - 48) < 1)
                || ((examDate.charAt(8) - 48) * 10 + (examDate.charAt(9) - 48) > 30)
                || ((examDate.charAt(8) - 48) * 10 + (examDate.charAt(9) - 48) < 1)) {
            return false;
        }
        else {
            Gson gson = new Gson();
            String line;

            // check professor number
            BufferedReader professorReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Professors.json"));
            while ((line = professorReader.readLine()) != null) {
                Professor tempProfessor = gson.fromJson(line, Professor.class);
                if (tempProfessor.getProfessorNumber().equals(professorNumber)) {
                    return true;
                }
            }
            BufferedReader deputyReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Deputies.json"));
            while ((line = deputyReader.readLine()) != null) {
                Professor tempDeputy = gson.fromJson(line, Professor.class);
                if (tempDeputy.getProfessorNumber().equals(professorNumber)) {
                    return true;
                }
            }
            BufferedReader presidentReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Presidents.json"));
            while ((line = presidentReader.readLine()) != null) {
                Professor tempPresident = gson.fromJson(line, Professor.class);
                if (tempPresident.getProfessorNumber().equals(professorNumber)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        applyEditButton.setDisable(true);

        // set choice boxes
        daysOfWeekChoiceBox.getItems().addAll(null, DaysOfWeek.saturday, DaysOfWeek.sunday, DaysOfWeek.monday,
                DaysOfWeek.tuesday, DaysOfWeek.wednesday, DaysOfWeek.thursday, DaysOfWeek.friday);
        collegeNameChoiceBox.getItems().addAll(null, CollegeName.computer, CollegeName.electrical,
                CollegeName.mechanical, CollegeName.civil, CollegeName.chemistry);
        studentTypeChoiceBox.getItems().addAll(null, StudentType.bachelor,
                StudentType.master, StudentType.doctor);
    }
}
