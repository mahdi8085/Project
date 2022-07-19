package LOGIC.ProfessorProfile;

import GUI.Main;
import LOGIC.Person.Professor;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfessorProfile implements Initializable {

    static Logger log = LogManager.getLogger(ProfessorProfile.class);

    @FXML
    private TextField nameField;
    @FXML
    private TextField nationalCodeField;
    @FXML
    private TextField professorNumberField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField collegeNameField;
    @FXML
    private TextField roomNumberField;
    @FXML
    private TextField professorDegreeField;
    @FXML
    private TextField newEmailField;
    @FXML
    private TextField newPhoneNumberField;
    @FXML
    private Button editEmailButton;
    @FXML
    private Button editNumberButton;
    @FXML
    private ImageView imageView;
    @FXML
    private Label wrongInformation;

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

    public void editEmail(ActionEvent event) throws IOException {
        if (newEmailField.getText().isEmpty()) {
            wrongInformation.setText("Fill the blank !");
        }
        else {

            // get user
            Gson tempGson = new Gson();
            BufferedReader userReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\LoggedInUser.json"));
            Professor user = tempGson.fromJson(userReader.readLine(), Professor.class);
            userReader.close();

            // set strings due to type of user
            String jsonFile = null;
            switch (user.getRole()) {
                case "professor":
                    jsonFile = "src\\LOGIC\\Files\\Professors.json";
                    break;
                case "deputy":
                    jsonFile = "src\\LOGIC\\Files\\Deputies.json";
                    break;
                case "president":
                    jsonFile = "src\\LOGIC\\Files\\Presidents.json";
                    break;
            }

            // new professor
            Professor newProfessor = new Professor(user.getName(), user.getNationalCode(), user.getProfessorNumber(),
                    user.getPhoneNumber(), newEmailField.getText(), user.getRole(), user.getCollegeName(), user.getRoomNumber(),
                    user.getImage(), user.getProfessorDegree());
            newProfessor.setLastEntry(user.getLastEntry());
            newProfessor.setCourses(user.getCourses());

            Gson gson = new Gson();

            // change logged in user
            BufferedWriter logInWriter = new BufferedWriter(new FileWriter("src\\LOGIC\\Files\\LoggedInUser.json"));
            String newLogInProfessor = gson.toJson(newProfessor);
            logInWriter.write(newLogInProfessor);
            logInWriter.close();

            // remove ex professor
            int help = 0;
            BufferedWriter tempWriter = new BufferedWriter(new FileWriter("src\\LOGIC\\Files\\Temp.json"));
            assert jsonFile != null;
            BufferedReader professorReader = new BufferedReader(new FileReader(jsonFile));
            String line;
            while ((line = professorReader.readLine()) != null) {
                Professor tempProfessor = gson.fromJson(line, Professor.class);
                String json = gson.toJson(tempProfessor);
                if (!tempProfessor.getProfessorNumber().equals(professorNumberField.getText())) {
                    if (help == 0) {
                        tempWriter.write(json);
                        help = 1;
                    } else {
                        tempWriter.write("\n" + json);
                    }
                }
            }
            tempWriter.close();
            professorReader.close();

            // copy all professors from temp file to professors file
            int anotherHelp = 0;
            BufferedWriter newProfessorWriter = new BufferedWriter(new FileWriter(jsonFile));
            BufferedReader newProfessorReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Temp.json"));
            while ((line = newProfessorReader.readLine()) != null) {
                Professor tempProfessor = gson.fromJson(line, Professor.class);
                String json = gson.toJson(tempProfessor);
                if (anotherHelp == 0) {
                    newProfessorWriter.write(json);
                    anotherHelp = 1;
                } else {
                    newProfessorWriter.write("\n" + json);
                }
            }
            String professor = gson.toJson(newProfessor);
            if (anotherHelp == 0) {
                newProfessorWriter.write(professor);
            }
            else {
                newProfessorWriter.write("\n" + professor);
            }
            newProfessorReader.close();
            newProfessorWriter.close();
            wrongInformation.setText("");
            emailField.setText(newEmailField.getText());
            newEmailField.setText("");
            log.info("User edited email");
        }
    }

    public void editNumber(ActionEvent event) throws IOException {
        if (newPhoneNumberField.getText().isEmpty()) {
            wrongInformation.setText("Fill the blank !");
        }
        else if (newPhoneNumberField.getText().length() != 11) {
            wrongInformation.setText("Not valid !");
            newPhoneNumberField.setText("");
        }
        else {

            // get user
            Gson tempGson = new Gson();
            BufferedReader userReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\LoggedInUser.json"));
            Professor user = tempGson.fromJson(userReader.readLine(), Professor.class);
            userReader.close();

            // set strings due to type of user
            String jsonFile = null;
            switch (user.getRole()) {
                case "professor":
                    jsonFile = "src\\LOGIC\\Files\\Professors.json";
                    break;
                case "deputy":
                    jsonFile = "src\\LOGIC\\Files\\Deputies.json";
                    break;
                case "president":
                    jsonFile = "src\\LOGIC\\Files\\Presidents.json";
                    break;
            }

            // new professor
            Professor newProfessor = new Professor(user.getName(), user.getNationalCode(), user.getProfessorNumber(),
                    newPhoneNumberField.getText(), user.getEmail(), user.getRole(), user.getCollegeName(), user.getRoomNumber(),
                    user.getImage(), user.getProfessorDegree());
            newProfessor.setLastEntry(user.getLastEntry());
            newProfessor.setCourses(user.getCourses());

            Gson gson = new Gson();

            // change logged in user
            BufferedWriter logInWriter = new BufferedWriter(new FileWriter("src\\LOGIC\\Files\\LoggedInUser.json"));
            String newLogInProfessor = gson.toJson(newProfessor);
            logInWriter.write(newLogInProfessor);
            logInWriter.close();

            // remove ex professor
            int help = 0;
            BufferedWriter tempWriter = new BufferedWriter(new FileWriter("src\\LOGIC\\Files\\Temp.json"));
            assert jsonFile != null;
            BufferedReader professorReader = new BufferedReader(new FileReader(jsonFile));
            String line;
            while ((line = professorReader.readLine()) != null) {
                Professor tempProfessor = gson.fromJson(line, Professor.class);
                String json = gson.toJson(tempProfessor);
                if (!tempProfessor.getProfessorNumber().equals(professorNumberField.getText())) {
                    if (help == 0) {
                        tempWriter.write(json);
                        help = 1;
                    } else {
                        tempWriter.write("\n" + json);
                    }
                }
            }
            tempWriter.close();
            professorReader.close();

            // copy all professors from temp file to professors file
            int anotherHelp = 0;
            BufferedWriter newProfessorWriter = new BufferedWriter(new FileWriter(jsonFile));
            BufferedReader newProfessorReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Temp.json"));
            while ((line = newProfessorReader.readLine()) != null) {
                Professor tempProfessor = gson.fromJson(line, Professor.class);
                String json = gson.toJson(tempProfessor);
                if (anotherHelp == 0) {
                    newProfessorWriter.write(json);
                    anotherHelp = 1;
                } else {
                    newProfessorWriter.write("\n" + json);
                }
            }
            String professor = gson.toJson(newProfessor);
            if (anotherHelp == 0) {
                newProfessorWriter.write("\n" + professor);
            }
            else {
                newProfessorWriter.write("\n" + professor);
            }
            newProfessorReader.close();
            newProfessorWriter.close();
            wrongInformation.setText("");
            phoneNumberField.setText(newPhoneNumberField.getText());
            newPhoneNumberField.setText("");
            log.info("User edited phone number");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // get user
        Gson tempGson = new Gson();
        BufferedReader userReader = null;
        Professor user = null;
        try {
            userReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\LoggedInUser.json"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            assert userReader != null;
            user = tempGson.fromJson(userReader.readLine(), Professor.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            userReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // set fields uneditable
        nameField.setEditable(false);
        nationalCodeField.setEditable(false);
        professorNumberField.setEditable(false);
        phoneNumberField.setEditable(false);
        emailField.setEditable(false);
        collegeNameField.setEditable(false);
        roomNumberField.setEditable(false);
        professorDegreeField.setEditable(false);

        // set user information
        assert user != null;
        nameField.setText(user.getName());
        nationalCodeField.setText(user.getNationalCode());
        professorNumberField.setText(user.getProfessorNumber());
        phoneNumberField.setText(user.getPhoneNumber());
        emailField.setText(user.getEmail());
        collegeNameField.setText(user.getCollegeName().toString());
        roomNumberField.setText(String.valueOf(user.getRoomNumber()));
        professorDegreeField.setText(user.getProfessorDegree().toString());
        imageView.setImage(new Image(user.getImage()));
    }
}
