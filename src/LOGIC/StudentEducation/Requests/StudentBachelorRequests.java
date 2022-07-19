package LOGIC.StudentEducation.Requests;

import GUI.Main;
import javafx.event.ActionEvent;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;

public class StudentBachelorRequests {

    static Logger log = LogManager.getLogger(StudentBachelorRequests.class);
    
    public void bachelorRecommendation(ActionEvent event) throws IOException {

        log.info("User requested recommendation");

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("StudentEducation\\Requests\\StudentRecommendationRequest.fxml");
    }

    public void bachelorEducationalCertificate(ActionEvent event) throws IOException {

        log.info("User requested educational certificate");

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("StudentEducation\\Requests\\StudentEducationalCertificateRequest.fxml");
    }

    public void bachelorMinor(ActionEvent event) throws IOException {

        log.info("User requested minor");

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("StudentEducation\\Requests\\StudentMinorRequest.fxml");
    }

    public void bachelorWithdrawalFromEducation(ActionEvent event) throws IOException {

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
