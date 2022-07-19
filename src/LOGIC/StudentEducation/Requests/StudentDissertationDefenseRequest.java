package LOGIC.StudentEducation.Requests;

import GUI.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentDissertationDefenseRequest implements Initializable {

    @FXML
    private Label resultLabel;

    public void back(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("StudentEducation\\Requests\\StudentDoctorRequests.fxml");
    }

    public void mainMenu(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("StudentMain.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // generate a random result
        int random = (int) Math.floor(Math.random() * (2 - 1 + 1) + 1);
        if (random % 2 == 0) {
            resultLabel.setText("2022-05-21");
        }
        else {
            resultLabel.setText("2022-05-25");
        }
    }
}
