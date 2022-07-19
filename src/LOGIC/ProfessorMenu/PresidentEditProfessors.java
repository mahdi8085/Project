package LOGIC.ProfessorMenu;

import GUI.Main;
import LOGIC.Person.*;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PresidentEditProfessors implements Initializable {

    static Logger log = LogManager.getLogger(PresidentEditProfessors.class);

    // fields for moving
    private ArrayList<Course> coursesMove;

    @FXML
    private Button backButton;
    @FXML
    private Button mainMenuButton;
    @FXML
    private Button addButton;
    @FXML
    private Button removeButton;
    @FXML
    private Button editButton;
    @FXML
    private Button applyEditButton;
    @FXML
    private TextField nameField;
    @FXML
    private TextField professorNumberField;
    @FXML
    private TextField nationalCodeField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private TextField roomNumberField;
    @FXML
    private ChoiceBox<CollegeName> collegeNameChoiceBox;
    @FXML
    private ChoiceBox<ProfessorDegree> professorDegreeChoiceBox;
    @FXML
    private Label wrongInformation;

    public void back(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("ProfessorMenu\\PresidentProfessors.fxml");
    }

    public void mainMenu(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("PresidentMain.fxml");
    }

    public void add(ActionEvent event) throws IOException {

        // get user
        Gson tempGson = new Gson();
        BufferedReader userReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\LoggedInUser.json"));
        Professor user = tempGson.fromJson(userReader.readLine(), Professor.class);
        userReader.close();

        // check everything
        if (nameField.getText().isEmpty() || professorNumberField.getText().isEmpty() || nationalCodeField.getText().isEmpty()
                || emailField.getText().isEmpty() || phoneNumberField.getText().isEmpty()
                || roomNumberField.getText().isEmpty() || collegeNameChoiceBox.getValue() == null
                || professorDegreeChoiceBox.getValue() == null || user.getCollegeName() != collegeNameChoiceBox.getValue()) {
            wrongInformation.setText("Invalid inputs !");
            nameField.setText("");
            professorNumberField.setText("");
            nationalCodeField.setText("");
            emailField.setText("");
            phoneNumberField.setText("");
            roomNumberField.setText("");
            collegeNameChoiceBox.setValue(null);
            professorDegreeChoiceBox.setValue(null);
        }
        else {
            int help = 0;
            BufferedWriter tempWriter = new BufferedWriter(new FileWriter("src\\LOGIC\\Files\\Temp.json"));
            if (checkValidity(professorNumberField.getText(), nationalCodeField.getText(),
                    phoneNumberField.getText(), Integer.parseInt(roomNumberField.getText()))) {

                Gson gson = new Gson();

                // check professor number
                BufferedReader professorReaderHelp = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Professors.json"));
                String line;
                while ((line = professorReaderHelp.readLine()) != null) {
                    Professor tempProfessor = gson.fromJson(line, Professor.class);
                    if (tempProfessor.getProfessorNumber().equals(professorNumberField.getText())) {
                        wrongInformation.setText("Invalid inputs !");
                        nameField.setText("");
                        professorNumberField.setText("");
                        nationalCodeField.setText("");
                        emailField.setText("");
                        phoneNumberField.setText("");
                        roomNumberField.setText("");
                        collegeNameChoiceBox.setValue(null);
                        professorDegreeChoiceBox.setValue(null);
                        return;
                    }
                }
                professorReaderHelp.close();

                BufferedReader professorReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Professors.json"));
                while ((line = professorReader.readLine()) != null) {
                    Professor tempProfessor = gson.fromJson(line, Professor.class);
                    String json = gson.toJson(tempProfessor);
                    if (help == 0) {
                        tempWriter.write(json);
                        help = 1;
                    } else {
                        tempWriter.write("\n" + json);
                    }
                }
                professorReader.close();

                // add new professor
                Professor newProfessor = new Professor(nameField.getText(), nationalCodeField.getText(),
                        professorNumberField.getText(), phoneNumberField.getText(), emailField.getText(), "professor",
                        collegeNameChoiceBox.getValue(), Integer.parseInt(roomNumberField.getText()),
                        "GUI\\Images\\professorProfile.png", professorDegreeChoiceBox.getValue());
                String professor = gson.toJson(newProfessor);
                tempWriter.write("\n" + professor);
                tempWriter.close();

                // copy all professors from temp file to professors file
                int anotherHelp = 0;
                BufferedWriter newProfessorWriter = new BufferedWriter(new FileWriter("src\\LOGIC\\Files\\Professors.json"));
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
                newProfessorReader.close();
                newProfessorWriter.close();
                nameField.setText("");
                professorNumberField.setText("");
                nationalCodeField.setText("");
                emailField.setText("");
                phoneNumberField.setText("");
                roomNumberField.setText("");
                collegeNameChoiceBox.setValue(null);
                professorDegreeChoiceBox.setValue(null);
                wrongInformation.setText("");
                log.info("New professor added");
            }
            else {
                nameField.setText("");
                professorNumberField.setText("");
                nationalCodeField.setText("");
                emailField.setText("");
                phoneNumberField.setText("");
                roomNumberField.setText("");
                collegeNameChoiceBox.setValue(null);
                professorDegreeChoiceBox.setValue(null);
                wrongInformation.setText("Invalid inputs !");
            }
        }
    }

    public void remove(ActionEvent event) throws IOException {

        // get user
        Gson tempGson = new Gson();
        BufferedReader userReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\LoggedInUser.json"));
        Professor user = tempGson.fromJson(userReader.readLine(), Professor.class);
        userReader.close();

        // remove professor
        if (!nameField.getText().isEmpty() || professorNumberField.getText().isEmpty() || !nationalCodeField.getText().isEmpty()
                || !emailField.getText().isEmpty() || !phoneNumberField.getText().isEmpty()
                || !roomNumberField.getText().isEmpty() || collegeNameChoiceBox.getValue() != null
                || professorDegreeChoiceBox.getValue() != null) {
            wrongInformation.setText("Invalid inputs !");
        }
        else {
            Gson gson = new Gson();
            int help = 0;
            boolean isInProfessors = false;
            BufferedWriter tempWriter = new BufferedWriter(new FileWriter("src\\LOGIC\\Files\\Temp.json"));
            BufferedReader professorReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Professors.json"));
            String line;
            while ((line = professorReader.readLine()) != null) {
                Professor tempProfessor = gson.fromJson(line, Professor.class);
                String json = gson.toJson(tempProfessor);
                if (user.getCollegeName() != tempProfessor.getCollegeName() && tempProfessor.getProfessorNumber().equals(professorNumberField.getText())) {
                    wrongInformation.setText("Invalid inputs !");
                    tempWriter.close();
                    professorReader.close();
                    professorNumberField.setText("");
                    return;
                }
                if (tempProfessor.getProfessorNumber().equals(professorNumberField.getText())) {
                    isInProfessors = true;
                }
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
            BufferedWriter newProfessorWriter = new BufferedWriter(new FileWriter("src\\LOGIC\\Files\\Professors.json"));
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
            newProfessorReader.close();
            newProfessorWriter.close();
            wrongInformation.setText("");
            log.info("Professor deleted");
            if (!isInProfessors) {
                wrongInformation.setText("Invalid inputs !");
            }
        }
        professorNumberField.setText("");
    }

    public void edit(ActionEvent event) throws IOException {

        // get user
        Gson tempGson = new Gson();
        BufferedReader userReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\LoggedInUser.json"));
        Professor user = tempGson.fromJson(userReader.readLine(), Professor.class);
        userReader.close();

        // remove professor
        if (!nameField.getText().isEmpty() || professorNumberField.getText().isEmpty() || !nationalCodeField.getText().isEmpty()
                || !emailField.getText().isEmpty() || !phoneNumberField.getText().isEmpty()
                || !roomNumberField.getText().isEmpty() || collegeNameChoiceBox.getValue() != null
                || professorDegreeChoiceBox.getValue() != null) {
            wrongInformation.setText("Invalid inputs !");
        }
        else {
            Gson gson = new Gson();
            BufferedReader professorReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Professors.json"));
            String line;
            while ((line = professorReader.readLine()) != null) {
                Professor tempProfessor = gson.fromJson(line, Professor.class);
                if (tempProfessor.getProfessorNumber().equals(professorNumberField.getText()) && tempProfessor.getCollegeName() == user.getCollegeName()) {
                    nameField.setText(tempProfessor.getName());
                    professorNumberField.setEditable(false);
                    nationalCodeField.setText(String.valueOf(tempProfessor.getNationalCode()));
                    emailField.setText(tempProfessor.getEmail());
                    phoneNumberField.setText(tempProfessor.getPhoneNumber());
                    roomNumberField.setText(String.valueOf(tempProfessor.getRoomNumber()));
                    collegeNameChoiceBox.setValue(tempProfessor.getCollegeName());
                    professorDegreeChoiceBox.setValue(tempProfessor.getProfessorDegree());
                    addButton.setDisable(true);
                    removeButton.setDisable(true);
                    editButton.setDisable(true);
                    applyEditButton.setDisable(false);
                    this.coursesMove = tempProfessor.getCourses();
                    wrongInformation.setText("");
                    professorReader.close();
                    return;
                }
            }
            professorReader.close();
            wrongInformation.setText("Invalid inputs !");
        }
    }

    public void applyEdit(ActionEvent event) throws IOException {
        professorNumberField.setEditable(true);
        addButton.setDisable(false);
        removeButton.setDisable(false);
        editButton.setDisable(false);
        applyEditButton.setDisable(true);

        // get user
        Gson tempGson = new Gson();
        BufferedReader userReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\LoggedInUser.json"));
        Professor user = tempGson.fromJson(userReader.readLine(), Professor.class);
        userReader.close();

        if (nameField.getText().isEmpty() || professorNumberField.getText().isEmpty() || nationalCodeField.getText().isEmpty()
                || emailField.getText().isEmpty() || phoneNumberField.getText().isEmpty()
                || roomNumberField.getText().isEmpty() || collegeNameChoiceBox.getValue() == null
                || professorDegreeChoiceBox.getValue() == null
                || user.getCollegeName() != collegeNameChoiceBox.getValue()) {
            wrongInformation.setText("Invalid inputs !");
        }
        else {
            if (checkValidity(professorNumberField.getText(), nationalCodeField.getText(),
                    phoneNumberField.getText(), Integer.parseInt(roomNumberField.getText()))) {
                Gson gson = new Gson();

                // remove ex professor
                int help = 0;
                BufferedWriter tempWriter = new BufferedWriter(new FileWriter("src\\LOGIC\\Files\\Temp.json"));
                BufferedReader professorReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Professors.json"));
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

                // make new professor
                Professor newProfessor = new Professor(nameField.getText(), nationalCodeField.getText(),
                        professorNumberField.getText(), phoneNumberField.getText(), emailField.getText(), "professor",
                        collegeNameChoiceBox.getValue(), Integer.parseInt(roomNumberField.getText()),
                        "GUI\\Images\\professorProfile.png", professorDegreeChoiceBox.getValue());
                newProfessor.setLastEntry(user.getLastEntry());
                newProfessor.setCourses(this.coursesMove);

                // copy all professors from temp file to professors file
                int anotherHelp = 0;
                BufferedWriter newProfessorWriter = new BufferedWriter(new FileWriter("src\\LOGIC\\Files\\Professors.json"));
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
                newProfessorWriter.write("\n" + professor);
                newProfessorReader.close();
                newProfessorWriter.close();
                wrongInformation.setText("");
                log.info("Professor information edited");
            }
            else {
                wrongInformation.setText("Invalid inputs !");
            }
        }
        nameField.setText("");
        professorNumberField.setText("");
        nationalCodeField.setText("");
        emailField.setText("");
        phoneNumberField.setText("");
        roomNumberField.setText("");
        collegeNameChoiceBox.setValue(null);
        professorDegreeChoiceBox.setValue(null);
    }

    private boolean checkValidity(String professorNumber, String nationalCode,
                                  String phnoeNumber, int roomNumber) throws IOException {
        return professorNumber.length() == 8 && nationalCode.length() == 10 && phnoeNumber.length() == 11
                && roomNumber <= 100 && roomNumber >= 1;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        applyEditButton.setDisable(true);

        // set choice boxes
        collegeNameChoiceBox.getItems().addAll(null, CollegeName.computer, CollegeName.electrical,
                CollegeName.mechanical, CollegeName.civil, CollegeName.chemistry);
        professorDegreeChoiceBox.getItems().addAll(null, ProfessorDegree.assistantProfessor,
                ProfessorDegree.associateProfessor, ProfessorDegree.fullProfessor);
    }
}
