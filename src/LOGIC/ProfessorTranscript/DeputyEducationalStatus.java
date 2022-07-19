package LOGIC.ProfessorTranscript;

import GUI.Main;
import javafx.event.ActionEvent;

import java.io.IOException;

public class DeputyEducationalStatus {

    public void back(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("ProfessorTranscript\\DeputyTranscript.fxml");
    }

    public void mainMenu(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("DeputyMain.fxml");
    }
}
