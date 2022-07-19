package LOGIC.StudentEducation.Requests;

import GUI.Main;
import LOGIC.Person.Student;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentWithdrawalFromEducationRequest implements Initializable {

    @FXML
    private Label resultLabel;

    public void back(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // get user
        Gson gson = new Gson();
        BufferedReader userReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\LoggedInUser.json"));
        Student user = gson.fromJson(userReader.readLine(), Student.class);
        userReader.close();

        // change scene
        switch (user.getStudentType()) {
            case bachelor:
                main.changeScene("StudentEducation\\Requests\\StudentBachelorRequests.fxml");
                break;
            case master:
                main.changeScene("StudentEducation\\Requests\\StudentMasterRequests.fxml");
                break;
            case doctor:
                main.changeScene("StudentEducation\\Requests\\StudentDoctorRequests.fxml");
                break;
        }
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
            resultLabel.setText("accepted");
        }
        else {
            resultLabel.setText("rejected");
        }
    }
}
