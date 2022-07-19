package LOGIC.StudentTranscript;

import GUI.Main;
import javafx.event.ActionEvent;

import java.io.IOException;

public class StudentTranscript {

    public void mainMenu(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("StudentMain.fxml");
    }

    public void studentTemporaryScores(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("StudentTranscript\\StudentTemporaryScores.fxml");
    }

    public void studentEducationalStatus(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("StudentTranscript\\StudentEducationalStatus.fxml");
    }
}
