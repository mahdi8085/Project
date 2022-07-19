package LOGIC.ProfessorMenu;

import GUI.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class ProfessorRegister {

    @FXML
    private Button coursesButton;
    @FXML
    private Button professorsButton;
    @FXML
    private Button mainMenuButton;

    public void professorCourses(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("ProfessorMenu\\ProfessorCourses.fxml");
    }

    public void professorProfessors(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("ProfessorMenu\\ProfessorProfessors.fxml");
    }

    public void mainMenu(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("ProfessorMain.fxml");
    }
}
