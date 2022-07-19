package LOGIC.StudentMenu;

import GUI.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class StudentRegister {

    @FXML
    private Button coursesButton;
    @FXML
    private Button professorsButton;
    @FXML
    private Button mainMenuButton;

    public void studentCourses(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("StudentMenu\\StudentCourses.fxml");
    }

    public void studentProfessors(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("StudentMenu\\StudentProfessors.fxml");
    }

    public void mainMenu(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("StudentMain.fxml");
    }
}
