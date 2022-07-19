package LOGIC.ProfessorTranscript;

import GUI.Main;
import LOGIC.Person.*;
import com.google.gson.Gson;
import javafx.event.ActionEvent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ProfessorTemporaryScores {

    public void back(ActionEvent event) throws IOException {

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
            case "president":
                main.changeScene("ProfessorTranscript\\ProfessorTranscript.fxml");
                break;
            case "deputy":
                main.changeScene("ProfessorTranscript\\DeputyTranscript.fxml");
                break;
        }
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
