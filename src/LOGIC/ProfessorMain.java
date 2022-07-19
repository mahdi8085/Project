package LOGIC;

import GUI.Main;
import LOGIC.Person.Professor;
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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ProfessorMain implements Initializable {

    static Logger log = LogManager.getLogger(ProfessorMain.class);

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

    public void professorRegister(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("ProfessorMenu\\ProfessorRegister.fxml");
    }

    public void professorEducationalServices(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("ProfessorEducation\\ProfessorEducation.fxml");
    }

    public void professorTranscript(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("ProfessorTranscript\\ProfessorTranscript.fxml");
    }

    public void professorProfile(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("ProfessorProfile\\ProfessorProfile.fxml");
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
            Professor professor = gson.fromJson(reader.readLine(), Professor.class);
            name.setText(professor.getName());
            email.setText(professor.getEmail());
            imageView.setImage(new Image(professor.getImage()));
            lastEntry.setText(professor.getLastEntry());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
