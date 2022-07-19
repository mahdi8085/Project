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

public class StudentEducationalCertificateRequest implements Initializable {

    @FXML
    private Label nameLabel;
    @FXML
    private Label studentNumberLabel;
    @FXML
    private Label courseLabel;

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

        // complete request text
        assert user != null;
        nameLabel.setText(user.getName());
        studentNumberLabel.setText(user.getStudentNumber());
        courseLabel.setText(user.getCollegeName().toString());
    }
}
