package LOGIC;

import GUI.Main;
import LOGIC.Person.Student;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;

public class StudentChangePassword {

    static Logger log = LogManager.getLogger(StudentChangePassword.class);
    
    @FXML
    private TextField currentPasswordField;
    @FXML
    private TextField newPasswordField;
    @FXML
    private Button changePasswordButton;
    @FXML
    private Label wrongInformation;

    public void changePassword(ActionEvent event) throws IOException {

        // get user
        Gson tempGson = new Gson();
        BufferedReader userReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\LoggedInUser.json"));
        Student user = tempGson.fromJson(userReader.readLine(), Student.class);
        userReader.close();

        if (currentPasswordField.getText().isEmpty() || newPasswordField.getText().isEmpty()
                || currentPasswordField.getText().length() != 8 || newPasswordField.getText().length() != 8
                || currentPasswordField.getText().equals(newPasswordField.getText())
                || !user.getStudentNumber().equals(currentPasswordField.getText())
                || user.getStudentNumber().equals(newPasswordField.getText())) {
            currentPasswordField.setText("");
            newPasswordField.setText("");
            wrongInformation.setText("Invalid inputs !");
        }
        else {

            Gson gson = new Gson();

            // check new password
            BufferedReader reader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Students.json"));
            String line;
            while ((line = reader.readLine()) != null) {
                Student tempStudent = gson.fromJson(line, Student.class);
                if (tempStudent.getStudentNumber().equals(newPasswordField.getText())) {
                    currentPasswordField.setText("");
                    newPasswordField.setText("");
                    wrongInformation.setText("Wrong information !");
                    return;
                }
            }
            reader.close();

            // new student
            Student newStudent = new Student(user.getName(), user.getNationalCode(), newPasswordField.getText(),
                    user.getPhoneNumber(), user.getEmail(), user.getGPA(), user.getCollegeName(), user.getSupervisor(),
                    user.getEnteringYear(), user.getImage(), user.getStudentType(), user.getEducationalStatus());
            newStudent.setLastEntry(null);
            newStudent.setCourses(user.getCourses());

            // remove ex student
            int help = 0;
            BufferedWriter tempWriter = new BufferedWriter(new FileWriter("src\\LOGIC\\Files\\Temp.json"));
            BufferedReader studentReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Students.json"));
            while ((line = studentReader.readLine()) != null) {
                Student tempStudent = gson.fromJson(line, Student.class);
                String json = gson.toJson(tempStudent);
                if (!tempStudent.getStudentNumber().equals(currentPasswordField.getText())) {
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
            log.info("User password changed");
            Main main = new Main();
            main.changeScene("SignUp.fxml");
        }
    }
}
