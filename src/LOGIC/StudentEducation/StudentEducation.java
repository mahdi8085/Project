package LOGIC.StudentEducation;

import GUI.Main;
import LOGIC.Person.Student;
import LOGIC.Person.StudentType;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class StudentEducation {

    @FXML
    private Button weeklyScheduleButton;
    @FXML
    private Button examScheduleButton;
    @FXML
    private Button requestsButton;
    @FXML
    private Button mainMenuButton;

    public void studentWeeklySchedule(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("StudentEducation\\StudentWeeklySchedule.fxml");
    }

    public void studentExamSchedule(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("StudentEducation\\StudentExamSchedule.fxml");
    }

    public void studentRequests(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // get user
        Gson gson = new Gson();
        BufferedReader userReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\LoggedInUser.json"));
        Student user = gson.fromJson(userReader.readLine(), Student.class);
        userReader.close();

        // change scene
        if (user.getStudentType() == StudentType.bachelor) {
            main.changeScene("StudentEducation\\Requests\\StudentBachelorRequests.fxml");
        }
        else if (user.getStudentType() == StudentType.master) {
            main.changeScene("StudentEducation\\Requests\\StudentMasterRequests.fxml");
        }
        else if (user.getStudentType() == StudentType.doctor) {
            main.changeScene("StudentEducation\\Requests\\StudentDoctorRequests.fxml");
        }
    }

    public void mainMenu(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("StudentMain.fxml");
    }
}
