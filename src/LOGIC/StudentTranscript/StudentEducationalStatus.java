package LOGIC.StudentTranscript;

import GUI.Main;
import javafx.event.ActionEvent;

import java.io.IOException;

public class StudentEducationalStatus {

    public void back(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("StudentTranscript\\StudentTranscript.fxml");
    }

    public void mainMenu(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("StudentMain.fxml");
    }
}
