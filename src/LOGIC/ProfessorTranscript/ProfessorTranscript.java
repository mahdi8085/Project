package LOGIC.ProfessorTranscript;

import GUI.Main;
import LOGIC.Person.Professor;
import com.google.gson.Gson;
import javafx.event.ActionEvent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ProfessorTranscript {

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
            case "president":
                main.changeScene("PresidentMain.fxml");
                break;
        }
    }

    public void professorTemporaryScores(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("ProfessorTranscript\\ProfessorTemporaryScores.fxml");
    }
}
