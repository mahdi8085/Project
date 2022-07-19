package LOGIC.StudentProfile;

import GUI.Main;
import LOGIC.Person.Student;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentProfile implements Initializable {

    static Logger log = LogManager.getLogger(StudentProfile.class);

    @FXML
    private TextField nameField;
    @FXML
    private TextField nationalCodeField;
    @FXML
    private TextField studentNumberField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField GPAField;
    @FXML
    private TextField collegeNameField;
    @FXML
    private TextField supervisorNameField;
    @FXML
    private TextField enteringYearField;
    @FXML
    private TextField studentTypeField;
    @FXML
    private TextField educationalStatusField;
    @FXML
    private TextField newEmailField;
    @FXML
    private TextField newPhoneNumberField;
    @FXML
    private Button editEmailButton;
    @FXML
    private Button editNumberButton;
    @FXML
    private ImageView imageView;
    @FXML
    private Label wrongInformation;

    public void mainMenu(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("StudentMain.fxml");
    }

    public void editEmail(ActionEvent event) throws IOException {
        if (newEmailField.getText().isEmpty()) {
            wrongInformation.setText("Fill the blank !");
            newPhoneNumberField.setText("");
        }
        else {

            // get user
            Gson tempGson = new Gson();
            BufferedReader userReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\LoggedInUser.json"));
            Student user = tempGson.fromJson(userReader.readLine(), Student.class);
            userReader.close();

            // new student
            Student newStudent = new Student(user.getName(), user.getNationalCode(), user.getStudentNumber(),
                    user.getPhoneNumber(), newEmailField.getText(), user.getGPA(), user.getCollegeName(), user.getSupervisor(),
                    user.getEnteringYear(), user.getImage(), user.getStudentType(), user.getEducationalStatus());
            newStudent.setLastEntry(user.getLastEntry());
            newStudent.setCourses(user.getCourses());

            Gson gson = new Gson();

            // change logged in user
            BufferedWriter logInWriter = new BufferedWriter(new FileWriter("src\\LOGIC\\Files\\LoggedInUser.json"));
            String newLogInStudent = gson.toJson(newStudent);
            logInWriter.write(newLogInStudent);
            logInWriter.close();

            // remove ex student
            int help = 0;
            BufferedWriter tempWriter = new BufferedWriter(new FileWriter("src\\LOGIC\\Files\\Temp.json"));
            BufferedReader studentReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Students.json"));
            String line;
            while ((line = studentReader.readLine()) != null) {
                Student tempStudent = gson.fromJson(line, Student.class);
                String json = gson.toJson(tempStudent);
                if (!tempStudent.getStudentNumber().equals(studentNumberField.getText())) {
                    if (help == 0) {
                        tempWriter.write(json);
                        help = 1;
                    } else {
                        tempWriter.write("\n" + json);
                    }
                }
            }
            tempWriter.close();
            studentReader.close();

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
            String student = gson.toJson(newStudent);
            newStudentWriter.write("\n" + student);
            newStudentReader.close();
            newStudentWriter.close();
            wrongInformation.setText("");
            emailField.setText(newEmailField.getText());
            newEmailField.setText("");
            log.info("User edited email");
        }
    }

    public void editNumber(ActionEvent event) throws IOException {
        if (newPhoneNumberField.getText().isEmpty()) {
            wrongInformation.setText("Fill the blank !");
        }
        else if (newPhoneNumberField.getText().length() != 11) {
            wrongInformation.setText("Not valid !");
        }
        else {

            // get user
            Gson tempGson = new Gson();
            BufferedReader userReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\LoggedInUser.json"));
            Student user = tempGson.fromJson(userReader.readLine(), Student.class);
            userReader.close();

            // new student
            Student newStudent = new Student(user.getName(), user.getNationalCode(), user.getStudentNumber(),
                    newPhoneNumberField.getText(), user.getEmail(), user.getGPA(), user.getCollegeName(), user.getSupervisor(),
                    user.getEnteringYear(), user.getImage(), user.getStudentType(), user.getEducationalStatus());
            newStudent.setLastEntry(user.getLastEntry());
            newStudent.setCourses(user.getCourses());

            Gson gson = new Gson();

            // change logged in user
            BufferedWriter logInWriter = new BufferedWriter(new FileWriter("src\\LOGIC\\Files\\LoggedInUser.json"));
            String newLogInStudent = gson.toJson(newStudent);
            logInWriter.write(newLogInStudent);
            logInWriter.close();

            // remove ex student
            int help = 0;
            BufferedWriter tempWriter = new BufferedWriter(new FileWriter("src\\LOGIC\\Files\\Temp.json"));
            BufferedReader studentReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Students.json"));
            String line;
            while ((line = studentReader.readLine()) != null) {
                Student tempStudent = gson.fromJson(line, Student.class);
                String json = gson.toJson(tempStudent);
                if (!tempStudent.getStudentNumber().equals(studentNumberField.getText())) {
                    if (help == 0) {
                        tempWriter.write(json);
                        help = 1;
                    } else {
                        tempWriter.write("\n" + json);
                    }
                }
            }
            tempWriter.close();
            studentReader.close();

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
            String student = gson.toJson(newStudent);
            if (anotherHelp == 0) {
                newStudentWriter.write(student);
            }
            else {
                newStudentWriter.write("\n" + student);
            }
            newStudentReader.close();
            newStudentWriter.close();
            wrongInformation.setText("");
            phoneNumberField.setText(newPhoneNumberField.getText());
            newPhoneNumberField.setText("");
            log.info("User edited phone number");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // get user
        Gson tempGson = new Gson();
        BufferedReader userReader = null;
        Student user = null;
        try {
            userReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\LoggedInUser.json"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            assert userReader != null;
            user = tempGson.fromJson(userReader.readLine(), Student.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            userReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // set fields uneditable
        nameField.setEditable(false);
        nationalCodeField.setEditable(false);
        studentNumberField.setEditable(false);
        phoneNumberField.setEditable(false);
        emailField.setEditable(false);
        GPAField.setEditable(false);
        collegeNameField.setEditable(false);
        supervisorNameField.setEditable(false);
        enteringYearField.setEditable(false);
        studentTypeField.setEditable(false);
        educationalStatusField.setEditable(false);

        // set user information
        assert user != null;
        nameField.setText(user.getName());
        nationalCodeField.setText(user.getNationalCode());
        studentNumberField.setText(user.getStudentNumber());
        phoneNumberField.setText(user.getPhoneNumber());
        emailField.setText(user.getEmail());
        GPAField.setText(String.valueOf(user.getGPA()));
        collegeNameField.setText(user.getCollegeName().toString());
        supervisorNameField.setText(user.getSupervisor().getName());
        enteringYearField.setText(String.valueOf(user.getEnteringYear()));
        studentTypeField.setText(user.getStudentType().toString());
        educationalStatusField.setText(user.getEducationalStatus().toString());
        imageView.setImage(new Image(user.getImage()));
    }
}
