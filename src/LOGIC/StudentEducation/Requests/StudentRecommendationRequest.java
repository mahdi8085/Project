package LOGIC.StudentEducation.Requests;

import GUI.Main;
import LOGIC.Person.Professor;
import LOGIC.Person.Student;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class StudentRecommendationRequest {

    @FXML
    private Label nameLabel;
    @FXML
    private Label studentNumberLabel;
    @FXML
    private Label wrongInformation;
    @FXML
    private TextField professorNumberField;

    public void back(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // get user
        Gson gson = new Gson();
        BufferedReader userReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\LoggedInUser.json"));
        Student user = gson.fromJson(userReader.readLine(), Student.class);
        userReader.close();

        // change scene
        switch (user.getStudentType()) {
            case bachelor:
                main.changeScene("StudentEducation\\Requests\\StudentBachelorRequests.fxml");
                break;
            case master:
                main.changeScene("StudentEducation\\Requests\\StudentMasterRequests.fxml");
                break;
        }
    }

    public void mainMenu(ActionEvent event) throws IOException {

        // create a new main
        Main main = new Main();

        // change scene
        main.changeScene("StudentMain.fxml");
    }

    public void request(ActionEvent event) throws IOException {

        // get user
        Gson tempGson = new Gson();
        BufferedReader userReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\LoggedInUser.json"));
        Student user = tempGson.fromJson(userReader.readLine(), Student.class);
        userReader.close();

        // find related professor
        Gson gson = new Gson();
        String line;
        Professor professor = null;
        BufferedReader professorReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Professors.json"));
        while ((line = professorReader.readLine()) != null) {
            Professor tempProfessor = gson.fromJson(line, Professor.class);
            if (tempProfessor.getProfessorNumber().equals(professorNumberField.getText())) {
                professor = tempProfessor;
            }
        }
        professorReader.close();
        BufferedReader deputyReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Deputies.json"));
        while ((line = deputyReader.readLine()) != null) {
            Professor tempDeputy = gson.fromJson(line, Professor.class);
            if (tempDeputy.getProfessorNumber().equals(professorNumberField.getText())) {
                professor = tempDeputy;
            }
        }
        deputyReader.close();
        BufferedReader presidentReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Presidents.json"));
        while ((line = presidentReader.readLine()) != null) {
            Professor tempPresident = gson.fromJson(line, Professor.class);
            if (tempPresident.getProfessorNumber().equals(professorNumberField.getText())) {
                professor = tempPresident;
            }
        }
        presidentReader.close();
        if (professor == null) {
            wrongInformation.setText("Wrong information !");
            professorNumberField.setText("");
            nameLabel.setText("");
            studentNumberLabel.setText("");
        }
        else {
            wrongInformation.setText("");
            professorNumberField.setText("");
            nameLabel.setText(user.getName());
            studentNumberLabel.setText(user.getStudentNumber());
        }
    }
}
