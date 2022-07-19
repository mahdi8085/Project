package LOGIC.StudentEducation.Requests;

import GUI.Main;
import javafx.event.ActionEvent;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;

public class StudentDoctorRequests {

    static Logger log = LogManager.getLogger(StudentDoctorRequests.class);
    
    public void doctorEducationalCertificate(ActionEvent event) throws IOException {

        log.info("User requested educational certificate");

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("StudentEducation\\Requests\\StudentEducationalCertificateRequest.fxml");
    }

    public void doctorWithdrawalFromEducation(ActionEvent event) throws IOException {

        log.info("User requested withdrawal from education");

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("StudentEducation\\Requests\\StudentWithdrawalFromEducationRequest.fxml");
    }

    public void doctorDissertationDefense(ActionEvent event) throws IOException {

        log.info("User requested dissertation defense");

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("StudentEducation\\Requests\\StudentDissertationDefenseRequest.fxml");
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
