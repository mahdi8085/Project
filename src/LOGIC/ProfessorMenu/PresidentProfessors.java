package LOGIC.ProfessorMenu;

import GUI.Main;
import LOGIC.Person.*;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PresidentProfessors implements Initializable {

    static Logger log = LogManager.getLogger(PresidentProfessors.class);

    @FXML
    private Button editProfessorsButton;
    @FXML
    private Button backButton;
    @FXML
    private Button mainMenuButton;
    @FXML
    private ChoiceBox<CollegeName> collegeChoiceBox;
    @FXML
    private ChoiceBox<ProfessorDegree> degreeChoiceBox;
    @FXML
    private TextField nameTextField;
    @FXML
    private TableView<Professor> tableView;
    @FXML
    private TableColumn<Professor, String> name;
    @FXML
    private TableColumn<Professor, CollegeName> college;
    @FXML
    private TableColumn<Professor, ProfessorDegree> grade;
    @FXML
    private TableColumn<Professor, String> role;

    public void back(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("ProfessorMenu\\PresidentRegister.fxml");
    }

    public void mainMenu(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("PresidentMain.fxml");
    }

    public void editProfessors(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("ProfessorMenu\\PresidentEditProfessors.fxml");
    }

    public void applyFilters(ActionEvent event) {

        // clear table
        tableView.getItems().clear();

        // read and pass into table considering filters
        Gson gson = new Gson();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Professors.json"));
            String line;
            while ((line = reader.readLine()) != null) {
                Professor temp = gson.fromJson(line, Professor.class);
                if (collegeChoiceBox.getValue() != null && degreeChoiceBox.getValue() != null && !nameTextField.getText().equalsIgnoreCase("")) {
                    if (collegeChoiceBox.getValue() == temp.getCollegeName()
                            && degreeChoiceBox.getValue() == temp.getProfessorDegree()
                            && nameTextField.getText().equalsIgnoreCase(temp.getName())) {
                        tableView.getItems().add(temp);
                    }
                }
                else if (collegeChoiceBox.getValue() != null && degreeChoiceBox.getValue() != null) {
                    if (collegeChoiceBox.getValue() == temp.getCollegeName()
                            && degreeChoiceBox.getValue() == temp.getProfessorDegree()) {
                        tableView.getItems().add(temp);
                    }
                }
                else if (degreeChoiceBox.getValue() != null && !nameTextField.getText().equalsIgnoreCase("")) {
                    if (degreeChoiceBox.getValue() == temp.getProfessorDegree()
                            && nameTextField.getText().equalsIgnoreCase(temp.getName())) {
                        tableView.getItems().add(temp);
                    }
                }
                else if (collegeChoiceBox.getValue() != null && !nameTextField.getText().equalsIgnoreCase("")) {
                    if (collegeChoiceBox.getValue() == temp.getCollegeName()
                            && nameTextField.getText().equalsIgnoreCase(temp.getName())) {
                        tableView.getItems().add(temp);
                    }
                }
                else if (collegeChoiceBox.getValue() != null) {
                    if (collegeChoiceBox.getValue() == temp.getCollegeName()) {
                        tableView.getItems().add(temp);
                    }
                }
                else if (degreeChoiceBox.getValue() != null) {
                    if (degreeChoiceBox.getValue() == temp.getProfessorDegree()) {
                        tableView.getItems().add(temp);
                    }
                }
                else if (!nameTextField.getText().equalsIgnoreCase("")) {
                    if (nameTextField.getText().equalsIgnoreCase(temp.getName())) {
                        tableView.getItems().add(temp);
                    }
                }
                else {
                    tableView.getItems().add(temp);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // read and pass into table considering filters
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Deputies.json"));
            String line;
            while ((line = reader.readLine()) != null) {
                Professor temp = gson.fromJson(line, Professor.class);
                if (collegeChoiceBox.getValue() != null && degreeChoiceBox.getValue() != null && !nameTextField.getText().equalsIgnoreCase("")) {
                    if (collegeChoiceBox.getValue() == temp.getCollegeName()
                            && degreeChoiceBox.getValue() == temp.getProfessorDegree()
                            && nameTextField.getText().equalsIgnoreCase(temp.getName())) {
                        tableView.getItems().add(temp);
                    }
                }
                else if (collegeChoiceBox.getValue() != null && degreeChoiceBox.getValue() != null) {
                    if (collegeChoiceBox.getValue() == temp.getCollegeName()
                            && degreeChoiceBox.getValue() == temp.getProfessorDegree()) {
                        tableView.getItems().add(temp);
                    }
                }
                else if (degreeChoiceBox.getValue() != null && !nameTextField.getText().equalsIgnoreCase("")) {
                    if (degreeChoiceBox.getValue() == temp.getProfessorDegree()
                            && nameTextField.getText().equalsIgnoreCase(temp.getName())) {
                        tableView.getItems().add(temp);
                    }
                }
                else if (collegeChoiceBox.getValue() != null && !nameTextField.getText().equalsIgnoreCase("")) {
                    if (collegeChoiceBox.getValue() == temp.getCollegeName()
                            && nameTextField.getText().equalsIgnoreCase(temp.getName())) {
                        tableView.getItems().add(temp);
                    }
                }
                else if (collegeChoiceBox.getValue() != null) {
                    if (collegeChoiceBox.getValue() == temp.getCollegeName()) {
                        tableView.getItems().add(temp);
                    }
                }
                else if (degreeChoiceBox.getValue() != null) {
                    if (degreeChoiceBox.getValue() == temp.getProfessorDegree()) {
                        tableView.getItems().add(temp);
                    }
                }
                else if (!nameTextField.getText().equalsIgnoreCase("")) {
                    if (nameTextField.getText().equalsIgnoreCase(temp.getName())) {
                        tableView.getItems().add(temp);
                    }
                }
                else {
                    tableView.getItems().add(temp);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // read and pass into table considering filters
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Presidents.json"));
            String line;
            while ((line = reader.readLine()) != null) {
                Professor temp = gson.fromJson(line, Professor.class);
                if (collegeChoiceBox.getValue() != null && degreeChoiceBox.getValue() != null && !nameTextField.getText().equalsIgnoreCase("")) {
                    if (collegeChoiceBox.getValue() == temp.getCollegeName()
                            && degreeChoiceBox.getValue() == temp.getProfessorDegree()
                            && nameTextField.getText().equalsIgnoreCase(temp.getName())) {
                        tableView.getItems().add(temp);
                    }
                }
                else if (collegeChoiceBox.getValue() != null && degreeChoiceBox.getValue() != null) {
                    if (collegeChoiceBox.getValue() == temp.getCollegeName()
                            && degreeChoiceBox.getValue() == temp.getProfessorDegree()) {
                        tableView.getItems().add(temp);
                    }
                }
                else if (degreeChoiceBox.getValue() != null && !nameTextField.getText().equalsIgnoreCase("")) {
                    if (degreeChoiceBox.getValue() == temp.getProfessorDegree()
                            && nameTextField.getText().equalsIgnoreCase(temp.getName())) {
                        tableView.getItems().add(temp);
                    }
                }
                else if (collegeChoiceBox.getValue() != null && !nameTextField.getText().equalsIgnoreCase("")) {
                    if (collegeChoiceBox.getValue() == temp.getCollegeName()
                            && nameTextField.getText().equalsIgnoreCase(temp.getName())) {
                        tableView.getItems().add(temp);
                    }
                }
                else if (collegeChoiceBox.getValue() != null) {
                    if (collegeChoiceBox.getValue() == temp.getCollegeName()) {
                        tableView.getItems().add(temp);
                    }
                }
                else if (degreeChoiceBox.getValue() != null) {
                    if (degreeChoiceBox.getValue() == temp.getProfessorDegree()) {
                        tableView.getItems().add(temp);
                    }
                }
                else if (!nameTextField.getText().equalsIgnoreCase("")) {
                    if (nameTextField.getText().equalsIgnoreCase(temp.getName())) {
                        tableView.getItems().add(temp);
                    }
                }
                else {
                    tableView.getItems().add(temp);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info("Filters applied");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // set choice boxes
        collegeChoiceBox.getItems().addAll(null, CollegeName.mechanical, CollegeName.electrical,
                CollegeName.computer, CollegeName.chemistry, CollegeName.civil);
        collegeChoiceBox.setOnAction(this::applyFilters);
        degreeChoiceBox.getItems().addAll(null, ProfessorDegree.assistantProfessor,
                ProfessorDegree.associateProfessor, ProfessorDegree.fullProfessor);
        degreeChoiceBox.setOnAction(this::applyFilters);

        // show all courses
        name.setCellValueFactory(new PropertyValueFactory<Professor, String>("name"));
        college.setCellValueFactory(new PropertyValueFactory<Professor, CollegeName>("collegeName"));
        grade.setCellValueFactory(new PropertyValueFactory<Professor, ProfessorDegree>("professorDegree"));
        role.setCellValueFactory(new PropertyValueFactory<Professor, String>("role"));

        // read courses and pass into table
        Gson gson = new Gson();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Professors.json"));
            String line;
            while ((line = reader.readLine()) != null) {
                Professor temp = gson.fromJson(line, Professor.class);
                tableView.getItems().add(temp);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Deputies.json"));
            String line;
            while ((line = reader.readLine()) != null) {
                Deputy temp = gson.fromJson(line, Deputy.class);
                tableView.getItems().add(temp);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Presidents.json"));
            String line;
            while ((line = reader.readLine()) != null) {
                President temp = gson.fromJson(line, President.class);
                tableView.getItems().add(temp);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
