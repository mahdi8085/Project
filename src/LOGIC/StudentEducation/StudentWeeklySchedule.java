package LOGIC.StudentEducation;

import GUI.Main;
import LOGIC.Person.Course;
import LOGIC.Person.DaysOfWeek;
import LOGIC.Person.Student;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentWeeklySchedule implements Initializable {

    @FXML
    private Button backButton;
    @FXML
    private Button mainMenuButton;
    @FXML
    private TableView<Course> tableView;
    @FXML
    private TableColumn<Course, String> courseName;
    @FXML
    private TableColumn<Course, Integer> ID;
    @FXML
    private TableColumn<Course, DaysOfWeek> dayOfWeek;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // set table
        courseName.setCellValueFactory(new PropertyValueFactory<Course, String>("name"));
        ID.setCellValueFactory(new PropertyValueFactory<Course, Integer>("id"));
        dayOfWeek.setCellValueFactory(new PropertyValueFactory<Course, DaysOfWeek>("classDay"));

        // read courses and pass into table
        Gson gson = new Gson();
        Student user = null;
        try {
            BufferedReader userReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\LoggedInUser.json"));
            user = gson.fromJson(userReader.readLine(), Student.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert user != null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Courses.json"));
            String line;
            while ((line = reader.readLine()) != null) {
                Course temp = gson.fromJson(line, Course.class);
                for (Course course : user.getCourses()) {
                    if (course.getName().equals(temp.getName()) && course.getId() == temp.getId()) {
                        tableView.getItems().add(temp);
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
