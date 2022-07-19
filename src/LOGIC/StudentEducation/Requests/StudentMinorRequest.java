package LOGIC.StudentEducation.Requests;

import GUI.Main;
import LOGIC.Person.Student;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentMinorRequest implements Initializable {

    @FXML
    private Label resultLabel;

    public void back(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("StudentEducation\\Requests\\StudentBachelorRequests.fxml");
    }

    public void mainMenu(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("StudentMain.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // get user
        Gson gson = new Gson();
        BufferedReader userReader = null;
        try {
            userReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\LoggedInUser.json"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Student user = null;
        try {
            assert userReader != null;
            user = gson.fromJson(userReader.readLine(), Student.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            userReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // print result
        assert user != null;
        if (user.getGPA() >= 17) {
            resultLabel.setText("accepted");
        } else {
            resultLabel.setText("rejected because of your GPA");
        }
    }
}
