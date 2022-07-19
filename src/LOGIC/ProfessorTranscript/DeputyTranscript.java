package LOGIC.ProfessorTranscript;

import GUI.Main;
import javafx.event.ActionEvent;

import java.io.IOException;

public class DeputyTranscript {

    public void mainMenu(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("DeputyMain.fxml");
    }

    public void professorTemporaryScores(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("ProfessorTranscript\\ProfessorTemporaryScores.fxml");
    }

    public void deputyEducationalStatus(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("ProfessorTranscript\\DeputyEducationalStatus.fxml");
    }
}
