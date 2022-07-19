package LOGIC.ProfessorProfile;

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
import java.util.ResourceBundle;

public class DeputyNewClient implements Initializable {

    static Logger log = LogManager.getLogger(DeputyNewClient.class);

    @FXML
    private Button addProfessorButton;
    @FXML
    private Button addStudentButton;
    @FXML
    private Label wrongInformation;
    @FXML
    private TextField professorNameField;
    @FXML
    private TextField professorNumberField;
    @FXML
    private TextField professorNationalCodeField;
    @FXML
    private TextField professorEmailField;
    @FXML
    private TextField professorPhoneNumberField;
    @FXML
    private TextField professorRoomNumberField;
    @FXML
    private TextField professorImageUrlField;
    @FXML
    private ChoiceBox<CollegeName> professorCollegeNameChoiceBox;
    @FXML
    private ChoiceBox<ProfessorDegree> professorDegreeChoiceBox;
    @FXML
    private TextField studentNameField;
    @FXML
    private TextField studentNumberField;
    @FXML
    private TextField studentNationalCodeField;
    @FXML
    private TextField studentEmailField;
    @FXML
    private TextField studentPhoneNumberField;
    @FXML
    private TextField studentGPAField;
    @FXML
    private TextField studentSupervisorProfessorNumberField;
    @FXML
    private TextField studentEnteringYearField;
    @FXML
    private TextField studentImageUrlField;
    @FXML
    private ChoiceBox<CollegeName> studentCollegeNameChoiceBox;
    @FXML
    private ChoiceBox<StudentType> studentTypeChoiceBox;

    public void mainMenu(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("DeputyMain.fxml");
    }

    public void addProfessor(ActionEvent event) throws IOException {

        // check everything
        if (professorNameField.getText().isEmpty() || professorNumberField.getText().isEmpty() || professorNationalCodeField.getText().isEmpty()
                || professorEmailField.getText().isEmpty() || professorPhoneNumberField.getText().isEmpty()
                || professorRoomNumberField.getText().isEmpty() || professorImageUrlField.getText().isEmpty()
                || professorCollegeNameChoiceBox.getValue() == null || professorDegreeChoiceBox.getValue() == null) {
            wrongInformation.setText("Invalid inputs !");
            professorNameField.setText("");
            professorNumberField.setText("");
            professorNationalCodeField.setText("");
            professorEmailField.setText("");
            professorPhoneNumberField.setText("");
            professorRoomNumberField.setText("");
            professorImageUrlField.setText("");
            professorCollegeNameChoiceBox.setValue(null);
            professorDegreeChoiceBox.setValue(null);
        }
        else {
            int help = 0;
            BufferedWriter tempWriter = new BufferedWriter(new FileWriter("src\\LOGIC\\Files\\Temp.json"));
            if (professorNumberField.getText().length() == 8 && professorNationalCodeField.getText().length() == 10
                    && professorPhoneNumberField.getText().length() == 11 && Integer.parseInt(professorRoomNumberField.getText()) <= 100
                    && Integer.parseInt(professorRoomNumberField.getText()) >= 1) {

                Gson gson = new Gson();

                // check professor number
                BufferedReader professorReaderHelp = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Professors.json"));
                String line;
                while ((line = professorReaderHelp.readLine()) != null) {
                    Professor tempProfessor = gson.fromJson(line, Professor.class);
                    if (tempProfessor.getProfessorNumber().equals(professorNumberField.getText())) {
                        wrongInformation.setText("Invalid inputs !");
                        professorNameField.setText("");
                        professorNumberField.setText("");
                        professorNationalCodeField.setText("");
                        professorEmailField.setText("");
                        professorPhoneNumberField.setText("");
                        professorRoomNumberField.setText("");
                        professorImageUrlField.setText("");
                        professorCollegeNameChoiceBox.setValue(null);
                        professorDegreeChoiceBox.setValue(null);
                        return;
                    }
                }
                professorReaderHelp.close();

                BufferedReader professorReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Professors.json"));
                while ((line = professorReader.readLine()) != null) {
                    Professor tempProfessor = gson.fromJson(line, Professor.class);
                    String json = gson.toJson(tempProfessor);
                    if (help == 0) {
                        tempWriter.write(json);
                        help = 1;
                    } else {
                        tempWriter.write("\n" + json);
                    }
                }
                professorReader.close();

                // add new professor
                Professor newProfessor = new Professor(professorNameField.getText(), professorNationalCodeField.getText(),
                        professorNumberField.getText(), professorPhoneNumberField.getText(), professorEmailField.getText(), "professor",
                        professorCollegeNameChoiceBox.getValue(), Integer.parseInt(professorRoomNumberField.getText()),
                        professorImageUrlField.getText(), professorDegreeChoiceBox.getValue());
                String professor = gson.toJson(newProfessor);
                tempWriter.write("\n" + professor);
                tempWriter.close();

                // copy all professors from temp file to professors file
                int anotherHelp = 0;
                BufferedWriter newProfessorWriter = new BufferedWriter(new FileWriter("src\\LOGIC\\Files\\Professors.json"));
                BufferedReader newProfessorReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Temp.json"));
                while ((line = newProfessorReader.readLine()) != null) {
                    Professor tempProfessor = gson.fromJson(line, Professor.class);
                    String json = gson.toJson(tempProfessor);
                    if (anotherHelp == 0) {
                        newProfessorWriter.write(json);
                        anotherHelp = 1;
                    } else {
                        newProfessorWriter.write("\n" + json);
                    }
                }
                newProfessorReader.close();
                newProfessorWriter.close();
                professorNameField.setText("");
                professorNumberField.setText("");
                professorNationalCodeField.setText("");
                professorEmailField.setText("");
                professorPhoneNumberField.setText("");
                professorRoomNumberField.setText("");
                professorImageUrlField.setText("");
                professorCollegeNameChoiceBox.setValue(null);
                professorDegreeChoiceBox.setValue(null);
                wrongInformation.setText("");
                log.info("Deputy added new professor");
            }
            else {
                professorNameField.setText("");
                professorNumberField.setText("");
                professorNationalCodeField.setText("");
                professorEmailField.setText("");
                professorPhoneNumberField.setText("");
                professorRoomNumberField.setText("");
                professorImageUrlField.setText("");
                professorCollegeNameChoiceBox.setValue(null);
                professorDegreeChoiceBox.setValue(null);
                wrongInformation.setText("Invalid inputs !");
            }
        }
    }

    public void addStudent(ActionEvent event) throws IOException {

        // check everything
        if (studentNameField.getText().isEmpty() || studentNumberField.getText().isEmpty() || studentNationalCodeField.getText().isEmpty()
                || studentEmailField.getText().isEmpty() || studentPhoneNumberField.getText().isEmpty()
                || studentGPAField.getText().isEmpty() || studentSupervisorProfessorNumberField.getText().isEmpty()
                || studentEnteringYearField.getText().isEmpty() || studentImageUrlField.getText().isEmpty()
                || studentCollegeNameChoiceBox.getValue() == null || studentTypeChoiceBox.getValue() == null) {
            wrongInformation.setText("Invalid inputs !");
            studentNameField.setText("");
            studentNumberField.setText("");
            studentNationalCodeField.setText("");
            studentEmailField.setText("");
            studentPhoneNumberField.setText("");
            studentGPAField.setText("");
            studentSupervisorProfessorNumberField.setText("");
            studentEnteringYearField.setText("");
            studentImageUrlField.setText("");
            studentCollegeNameChoiceBox.setValue(null);
            studentTypeChoiceBox.setValue(null);
        }
        else {
            int help = 0;
            BufferedWriter tempWriter = new BufferedWriter(new FileWriter("src\\LOGIC\\Files\\Temp.json"));
            if (studentNumberField.getText().length() == 8 && studentNationalCodeField.getText().length() == 10
                    && studentPhoneNumberField.getText().length() == 11) {

                Gson gson = new Gson();

                // check student number
                BufferedReader studentReaderHelp = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Students.json"));
                String line;
                while ((line = studentReaderHelp.readLine()) != null) {
                    Student tempStudent = gson.fromJson(line, Student.class);
                    if (tempStudent.getStudentNumber().equals(studentNumberField.getText())) {
                        wrongInformation.setText("Invalid inputs !");
                        studentNameField.setText("");
                        studentNumberField.setText("");
                        studentNationalCodeField.setText("");
                        studentEmailField.setText("");
                        studentPhoneNumberField.setText("");
                        studentGPAField.setText("");
                        studentSupervisorProfessorNumberField.setText("");
                        studentEnteringYearField.setText("");
                        studentImageUrlField.setText("");
                        studentCollegeNameChoiceBox.setValue(null);
                        studentTypeChoiceBox.setValue(null);
                        return;
                    }
                }
                studentReaderHelp.close();

                // find related supervisor
                Professor professor = null;
                BufferedReader professorReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Professors.json"));
                while ((line = professorReader.readLine()) != null) {
                    Professor tempProfessor = gson.fromJson(line, Professor.class);
                    if (tempProfessor.getProfessorNumber().equals(studentSupervisorProfessorNumberField.getText())) {
                        professor = tempProfessor;
                    }
                }
                professorReader.close();
                BufferedReader deputyReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Deputies.json"));
                while ((line = deputyReader.readLine()) != null) {
                    Professor tempDeputy = gson.fromJson(line, Professor.class);
                    if (tempDeputy.getProfessorNumber().equals(studentSupervisorProfessorNumberField.getText())) {
                        professor = tempDeputy;
                    }
                }
                deputyReader.close();
                BufferedReader presidentReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Presidents.json"));
                while ((line = presidentReader.readLine()) != null) {
                    Professor tempPresident = gson.fromJson(line, Professor.class);
                    if (tempPresident.getProfessorNumber().equals(studentSupervisorProfessorNumberField.getText())) {
                        professor = tempPresident;
                    }
                }
                presidentReader.close();
                if (professor == null) {
                    wrongInformation.setText("Invalid inputs !");
                    studentNameField.setText("");
                    studentNumberField.setText("");
                    studentNationalCodeField.setText("");
                    studentEmailField.setText("");
                    studentPhoneNumberField.setText("");
                    studentGPAField.setText("");
                    studentSupervisorProfessorNumberField.setText("");
                    studentEnteringYearField.setText("");
                    studentImageUrlField.setText("");
                    studentCollegeNameChoiceBox.setValue(null);
                    studentTypeChoiceBox.setValue(null);
                    return;
                }

                BufferedReader studentReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Students.json"));
                while ((line = studentReader.readLine()) != null) {
                    Student tempStudent = gson.fromJson(line, Student.class);
                    String json = gson.toJson(tempStudent);
                    if (help == 0) {
                        tempWriter.write(json);
                        help = 1;
                    } else {
                        tempWriter.write("\n" + json);
                    }
                }
                studentReader.close();

                // add new student
                Student newStudent = new Student(studentNameField.getText(), studentNationalCodeField.getText(),
                        studentNumberField.getText(), studentPhoneNumberField.getText(), studentEmailField.getText(),
                        Double.parseDouble(studentGPAField.getText()), studentCollegeNameChoiceBox.getValue(), professor,
                        Integer.parseInt(studentEnteringYearField.getText()), studentImageUrlField.getText(),
                        studentTypeChoiceBox.getValue(), EducationalStatus.studying);
                String student = gson.toJson(newStudent);
                tempWriter.write("\n" + student);
                tempWriter.close();

                // copy all students from temp file to students file
                int anotherHelp = 0;
                BufferedWriter newStudentWriter = new BufferedWriter(new FileWriter("src\\LOGIC\\Files\\Students.json"));
                BufferedReader newStudentReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Temp.json"));
                while ((line = newStudentReader.readLine()) != null) {
                    Student tempStudent = gson.fromJson(line, Student.class);
                    String json = gson.toJson(tempStudent);
                    if (anotherHelp == 0) {
                        newStudentWriter.write(json);
                        anotherHelp = 1;
                    } else {
                        newStudentWriter.write("\n" + json);
                    }
                }
                newStudentReader.close();
                newStudentWriter.close();
                studentNameField.setText("");
                studentNumberField.setText("");
                studentNationalCodeField.setText("");
                studentEmailField.setText("");
                studentPhoneNumberField.setText("");
                studentGPAField.setText("");
                studentSupervisorProfessorNumberField.setText("");
                studentEnteringYearField.setText("");
                studentImageUrlField.setText("");
                studentCollegeNameChoiceBox.setValue(null);
                studentTypeChoiceBox.setValue(null);
                wrongInformation.setText("");
                log.info("Deputy added new student");
            }
            else {
                studentNameField.setText("");
                studentNumberField.setText("");
                studentNationalCodeField.setText("");
                studentEmailField.setText("");
                studentPhoneNumberField.setText("");
                studentGPAField.setText("");
                studentSupervisorProfessorNumberField.setText("");
                studentEnteringYearField.setText("");
                studentImageUrlField.setText("");
                studentCollegeNameChoiceBox.setValue(null);
                studentTypeChoiceBox.setValue(null);
                wrongInformation.setText("Invalid inputs !");
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // set choice boxes
        professorCollegeNameChoiceBox.getItems().addAll(null, CollegeName.computer, CollegeName.electrical,
                CollegeName.mechanical, CollegeName.civil, CollegeName.chemistry);
        professorDegreeChoiceBox.getItems().addAll(null, ProfessorDegree.assistantProfessor,
                ProfessorDegree.associateProfessor, ProfessorDegree.fullProfessor);
        studentCollegeNameChoiceBox.getItems().addAll(null, CollegeName.computer, CollegeName.electrical,
                CollegeName.mechanical, CollegeName.civil, CollegeName.chemistry);
        studentTypeChoiceBox.getItems().addAll(null, StudentType.bachelor, StudentType.master,
                StudentType.doctor);
    }
}