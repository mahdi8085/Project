package LOGIC.StudentEducation.Requests;

import GUI.Main;
import javafx.event.ActionEvent;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;

public class StudentMasterRequests {

    static Logger log = LogManager.getLogger(StudentMasterRequests.class);

    public void masterRecommendation(ActionEvent event) throws IOException {

        log.info("User requested recommendation");

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("StudentEducation\\Requests\\StudentRecommendationRequest.fxml");
    }

    public void masterEducationalCertificate(ActionEvent event) throws IOException {

        log.info("User requested educational certificate");

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("StudentEducation\\Requests\\StudentEducationalCertificateRequest.fxml");
    }

    public void masterDormitory(ActionEvent event) throws IOException {

        log.info("User requested educational certificate");

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("StudentEducation\\Requests\\StudentDormitoryRequest.fxml");
    }

    public void masterWithdrawalFromEducation(ActionEvent event) throws IOException {

        log.info("User requested withdrawal from education");

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("StudentEducation\\Requests\\StudentWithdrawalFromEducationRequest.fxml");
    }

    public void back(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("StudentEducation\\StudentEducation.fxml");
    }

    public void mainMenu(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("StudentMain.fxml");
    }
}
