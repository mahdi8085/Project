package LOGIC.StudentMenu;

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

public class StudentCourses implements Initializable {

    static Logger log = LogManager.getLogger(StudentProfessors.class);

    @FXML
    private Button backButton;
    @FXML
    private Button mainMenuButton;
    @FXML
    private ChoiceBox<CollegeName> collegeChoiceBox;
    @FXML
    private ChoiceBox<StudentType> gradeChoiceBox;
    @FXML
    private ChoiceBox<Integer> creditChoiceBox;
    @FXML
    private TableView<Course> tableView;
    @FXML
    private TableColumn<Course, String> courseName;
    @FXML
    private TableColumn<Course, Integer> ID;
    @FXML
    private TableColumn<Course, CollegeName> college;
    @FXML
    private TableColumn<Course, Integer> credit;
    @FXML
    private TableColumn<Course, String> professor;
    @FXML
    private TableColumn<Course, StudentType> grade;

    public void back(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("StudentMenu\\StudentRegister.fxml");
    }

    public void mainMenu(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("StudentMain.fxml");
    }

    public void applyFilters(ActionEvent event) {

        // clear table
        tableView.getItems().clear();

        // read and pass into table considering filters
        Gson gson = new Gson();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Courses.json"));
            String line;
            while ((line = reader.readLine()) != null) {
                Course temp = gson.fromJson(line, Course.class);
                if (collegeChoiceBox.getValue() != null && gradeChoiceBox.getValue() != null && creditChoiceBox.getValue() != null) {
                    if (collegeChoiceBox.getValue() == temp.getCollegeName()
                            && gradeChoiceBox.getValue() == temp.getStudentType()
                            && creditChoiceBox.getValue() == temp.getCredit()) {
                        tableView.getItems().add(temp);
                    }
                }
                else if (collegeChoiceBox.getValue() != null && gradeChoiceBox.getValue() != null) {
                    if (collegeChoiceBox.getValue() == temp.getCollegeName()
                            && gradeChoiceBox.getValue() == temp.getStudentType()) {
                        tableView.getItems().add(temp);
                    }
                }
                else if (gradeChoiceBox.getValue() != null && creditChoiceBox.getValue() != null) {
                    if (gradeChoiceBox.getValue() == temp.getStudentType()
                            && creditChoiceBox.getValue() == temp.getCredit()) {
                        tableView.getItems().add(temp);
                    }
                }
                else if (collegeChoiceBox.getValue() != null && creditChoiceBox.getValue() != null) {
                    if (collegeChoiceBox.getValue() == temp.getCollegeName()
                            && creditChoiceBox.getValue() == temp.getCredit()) {
                        tableView.getItems().add(temp);
                    }
                }
                else if (collegeChoiceBox.getValue() != null) {
                    if (collegeChoiceBox.getValue() == temp.getCollegeName()) {
                        tableView.getItems().add(temp);
                    }
                }
                else if (gradeChoiceBox.getValue() != null) {
                    if (gradeChoiceBox.getValue() == temp.getStudentType()) {
                        tableView.getItems().add(temp);
                    }
                }
                else if (creditChoiceBox.getValue() != null) {
                    if (creditChoiceBox.getValue() == temp.getCredit()) {
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
        gradeChoiceBox.getItems().addAll(null, StudentType.bachelor, StudentType.master, StudentType.doctor);
        gradeChoiceBox.setOnAction(this::applyFilters);
        creditChoiceBox.getItems().addAll(null, 1, 2, 3, 4);
        creditChoiceBox.setOnAction(this::applyFilters);

        // set table
        courseName.setCellValueFactory(new PropertyValueFactory<Course, String>("name"));
        ID.setCellValueFactory(new PropertyValueFactory<Course, Integer>("id"));
        college.setCellValueFactory(new PropertyValueFactory<Course, CollegeName>("collegeName"));
        credit.setCellValueFactory(new PropertyValueFactory<Course, Integer>("credit"));
        professor.setCellValueFactory(new PropertyValueFactory<Course, String>("professorName"));
        grade.setCellValueFactory(new PropertyValueFactory<Course, StudentType>("studentType"));

        // read courses and pass into table
        Gson gson = new Gson();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Courses.json"));
            String line;
            while ((line = reader.readLine()) != null) {
                Course temp = gson.fromJson(line, Course.class);
                tableView.getItems().add(temp);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
