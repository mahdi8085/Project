package LOGIC;

import GUI.Main;
import LOGIC.Person.Student;
import com.google.gson.Gson;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class StudentMain implements Initializable {

    static Logger log = LogManager.getLogger(StudentMain.class);

    @FXML
    private Button registerButton;
    @FXML
    private Button educationalServicesButton;
    @FXML
    private Button transcriptButton;
    @FXML
    private Button profileButton;
    @FXML
    private Button exitButton;
    @FXML
    private ImageView imageView;
    @FXML
    private Label time;
    @FXML
    private Label lastEntry;
    @FXML
    private Label name;
    @FXML
    private Label email;
    @FXML
    private Label educationalStatus;
    @FXML
    private Label supervisor;
    @FXML
    private Label registerPermit;
    @FXML
    private Label registerTime;

    public void studentRegister(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("StudentMenu\\StudentRegister.fxml");
    }

    public void studentEducationalServices(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("StudentEducation\\StudentEducation.fxml");
    }

    public void studentTranscript(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("StudentTranscript\\StudentTranscript.fxml");
    }

    public void studentProfile(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("StudentProfile\\StudentProfile.fxml");
    }

    public void exit(ActionEvent event) throws IOException {

        log.info("User logged out");

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("SignUp.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Main main = null;
        try {
            main = new Main();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // set timer
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                time.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            }
        };
        timer.start();

        // read logged-in user and fill information
        Gson gson = new Gson();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\LoggedInUser.json"));
            Student student = gson.fromJson(reader.readLine(), Student.class);
            name.setText(student.getName());
            email.setText(student.getEmail());
            educationalStatus.setText(student.getEducationalStatus().toString());
            supervisor.setText(student.getSupervisor().getName());
            registerPermit.setText("Valid");
            registerTime.setText("2022-05-30");
            imageView.setImage(new Image(student.getImage()));
            lastEntry.setText(student.getLastEntry());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
