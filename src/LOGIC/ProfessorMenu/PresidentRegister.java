package LOGIC.ProfessorMenu;

import GUI.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class PresidentRegister {

    @FXML
    private Button coursesButton;
    @FXML
    private Button professorsButton;
    @FXML
    private Button mainMenuButton;

    public void presidentCourses(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("ProfessorMenu\\PresidentCourses.fxml");
    }

    public void presidentProfessors(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("ProfessorMenu\\PresidentProfessors.fxml");
    }

    public void mainMenu(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("PresidentMain.fxml");
    }
}