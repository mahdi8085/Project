package LOGIC.ProfessorEducation;

import GUI.Main;
import LOGIC.Person.Professor;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ProfessorEducation {

    @FXML
    private Button weeklyScheduleButton;
    @FXML
    private Button examScheduleButton;
    @FXML
    private Button requestsButton;
    @FXML
    private Button mainMenuButton;

    public void professorWeeklySchedule(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("ProfessorEducation\\ProfessorWeeklySchedule.fxml");
    }

    public void professorExamSchedule(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("ProfessorEducation\\ProfessorExamSchedule.fxml");
    }

    public void professorRequests(ActionEvent event) {
    }

    public void mainMenu(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // get user
        Gson gson = new Gson();
        BufferedReader userReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\LoggedInUser.json"));
        Professor user = gson.fromJson(userReader.readLine(), Professor.class);
        userReader.close();

        // change scene
        switch (user.getRole()) {
            case "professor":
                main.changeScene("ProfessorMain.fxml");
                break;
            case "deputy":
                main.changeScene("DeputyMain.fxml");
                break;
            case "president":
                main.changeScene("PresidentMain.fxml");
                break;
        }
    }
}
