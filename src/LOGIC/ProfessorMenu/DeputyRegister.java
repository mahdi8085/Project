package LOGIC.ProfessorMenu;

import GUI.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class DeputyRegister {

    @FXML
    private Button coursesButton;
    @FXML
    private Button professorsButton;
    @FXML
    private Button mainMenuButton;

    public void deputyCourses(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("ProfessorMenu\\DeputyCourses.fxml");
    }

    public void deputyProfessors(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("ProfessorMenu\\DeputyProfessors.fxml");
    }

    public void mainMenu(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("DeputyMain.fxml");
    }
}
