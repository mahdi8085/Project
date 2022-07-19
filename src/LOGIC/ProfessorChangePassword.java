package LOGIC;

import GUI.Main;
import LOGIC.Person.Professor;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;

public class ProfessorChangePassword {

    static Logger log = LogManager.getLogger(ProfessorChangePassword.class);

    @FXML
    private TextField currentPasswordField;
    @FXML
    private TextField newPasswordField;
    @FXML
    private Button changePasswordButton;
    @FXML
    private Label wrongInformation;

    public void changePassword(ActionEvent event) throws IOException {

        // get user
        Gson tempGson = new Gson();
        BufferedReader userReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\LoggedInUser.json"));
        Professor user = tempGson.fromJson(userReader.readLine(), Professor.class);
        userReader.close();

        if (currentPasswordField.getText().isEmpty() || newPasswordField.getText().isEmpty()
                || currentPasswordField.getText().length() != 8 || newPasswordField.getText().length() != 8
                || currentPasswordField.getText().equals(newPasswordField.getText())
                || !user.getProfessorNumber().equals(currentPasswordField.getText())
                || user.getProfessorNumber().equals(newPasswordField.getText())) {
            currentPasswordField.setText("");
            newPasswordField.setText("");
            wrongInformation.setText("Invalid inputs !");
        }
        else {

            Gson gson = new Gson();

            // check new password
            BufferedReader professorReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Professors.json"));
            String line;
            while ((line = professorReader.readLine()) != null) {
                Professor tempProfessor = gson.fromJson(line, Professor.class);
                if (tempProfessor.getProfessorNumber().equals(newPasswordField.getText())) {
                    currentPasswordField.setText("");
                    newPasswordField.setText("");
                    wrongInformation.setText("Wrong information !");
                    return;
                }
            }
            professorReader.close();
            BufferedReader deputyReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Deputies.json"));
            while ((line = deputyReader.readLine()) != null) {
                Professor tempDeputy = gson.fromJson(line, Professor.class);
                if (tempDeputy.getProfessorNumber().equals(newPasswordField.getText())) {
                    currentPasswordField.setText("");
                    newPasswordField.setText("");
                    wrongInformation.setText("Wrong information !");
                    return;
                }
            }
            deputyReader.close();
            BufferedReader presidentReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Presidents.json"));
            while ((line = presidentReader.readLine()) != null) {
                Professor tempPresident = gson.fromJson(line, Professor.class);
                if (tempPresident.getProfessorNumber().equals(newPasswordField.getText())) {
                    currentPasswordField.setText("");
                    newPasswordField.setText("");
                    wrongInformation.setText("Wrong information !");
                    return;
                }
            }
            presidentReader.close();

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
            Professor newProfessor = new Professor(user.getName(), user.getNationalCode(), newPasswordField.getText(),
                    user.getPhoneNumber(), user.getEmail(), user.getRole(), user.getCollegeName(), user.getRoomNumber(),
                    user.getImage(), user.getProfessorDegree());
            newProfessor.setLastEntry(null);
            newProfessor.setCourses(user.getCourses());

            // remove ex professor
            int help = 0;
            BufferedWriter tempWriter = new BufferedWriter(new FileWriter("src\\LOGIC\\Files\\Temp.json"));
            assert jsonFile != null;
            BufferedReader newProfessorReader = new BufferedReader(new FileReader(jsonFile));
            while ((line = newProfessorReader.readLine()) != null) {
                Professor tempProfessor = gson.fromJson(line, Professor.class);
                String json = gson.toJson(tempProfessor);
                if (!tempProfessor.getProfessorNumber().equals(currentPasswordField.getText())) {
                    if (help == 0) {
                        tempWriter.write(json);
                        help = 1;
                    } else {
                        tempWriter.write("\n" + json);
                    }
                }
            }
            tempWriter.close();
            newProfessorReader.close();

            // copy all professors from temp file to professors file
            int anotherHelp = 0;
            BufferedWriter newProfessorWriterLast = new BufferedWriter(new FileWriter(jsonFile));
            BufferedReader newProfessorReaderLast = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Temp.json"));
            while ((line = newProfessorReaderLast.readLine()) != null) {
                Professor tempProfessor = gson.fromJson(line, Professor.class);
                String json = gson.toJson(tempProfessor);
                if (anotherHelp == 0) {
                    newProfessorWriterLast.write(json);
                    anotherHelp = 1;
                } else {
                    newProfessorWriterLast.write("\n" + json);
                }
            }
            String professor = gson.toJson(newProfessor);
            if (anotherHelp == 0) {
                newProfessorWriterLast.write(professor);
            }
            else {
                newProfessorWriterLast.write("\n" + professor);
            }
            newProfessorReaderLast.close();
            newProfessorWriterLast.close();
            wrongInformation.setText("");
            log.info("User password changed");
            Main main = new Main();
            main.changeScene("SignUp.fxml");
        }
    }
}
