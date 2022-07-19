package LOGIC;

import GUI.Main;
import LOGIC.Person.President;
import LOGIC.Person.Professor;
import LOGIC.Person.Student;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class SignUp implements Initializable {

    static Logger log = LogManager.getLogger(SignUp.class);

    // captcha variable
    private int captchaNumber;

    // time limit variable
    private static final int TIME_LIMIT = 1;

    @FXML
    private Button logInButton;
    @FXML
    private Button changeCaptchaButton;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private TextField captcha;
    @FXML
    private Label wrongLogIn;
    @FXML
    private ImageView captchaImage;

    public void userLogIn(ActionEvent event) throws IOException {
        this.checkLogIn();
    }

    public void changeCaptcha(ActionEvent event) throws IOException {
        changeCaptchaFunction();
    }

    private void changeCaptchaFunction() {

        // generate a random number
        int random = (int) Math.floor(Math.random() * (5 - 1 + 1) + 1);

        // set a not repeated random image
        if (random == 1 && random != this.captchaNumber) {
            captchaImage.setImage(new Image("GUI\\Images\\captcha1.jpg"));
            this.captchaNumber = 1;
        }
        else if (random == 2 && random != this.captchaNumber) {
            captchaImage.setImage(new Image("GUI\\Images\\captcha2.jpg"));
            this.captchaNumber = 2;
        }
        else if (random == 3 && random != this.captchaNumber) {
            captchaImage.setImage(new Image("GUI\\Images\\captcha3.jpg"));
            this.captchaNumber = 3;
        }
        else if (random == 4 && random != this.captchaNumber) {
            captchaImage.setImage(new Image("GUI\\Images\\captcha4.jpg"));
            this.captchaNumber = 4;
        }
        else {
            if (random != this.captchaNumber) {
                captchaImage.setImage(new Image("GUI\\Images\\captcha5.jpg"));
                this.captchaNumber = 5;
            }
            else {
                captchaImage.setImage(new Image("GUI\\Images\\captcha1.jpg"));
                this.captchaNumber = 1;
            }
        }

        log.info("Captcha changed");
    }

    private boolean checkCaptcha() {

        // save user captcha
        String userCaptcha = this.captcha.getText();

        // check user captcha
        if (this.captchaNumber == 1) {
            return userCaptcha.equals("3478");
        }
        else if (this.captchaNumber == 2) {
            return userCaptcha.equals("7007");
        }
        else if (this.captchaNumber == 3) {
            return userCaptcha.equals("3657");
        }
        else if (this.captchaNumber == 4) {
            return userCaptcha.equals("7979");
        }
        else {
            return userCaptcha.equals("2764");
        }
    }

    private void checkLogIn() throws IOException {

        // create a new main
        Main main = new Main();

        // login as student
        if (checkUsernamePasswordCaptcha(this.username.getText(), this.password.getText(), "student")) {
            log.info("User logged in");
            main.changeScene("StudentMain.fxml");
        }

        // login as professor
        else if (checkUsernamePasswordCaptcha(this.username.getText(), this.password.getText(), "professor")) {
            log.info("User logged in");
            main.changeScene("ProfessorMain.fxml");
        }

        // login as deputy
        else if (checkUsernamePasswordCaptcha(this.username.getText(), this.password.getText(), "deputy")) {
            log.info("User logged in");
            main.changeScene("DeputyMain.fxml");
        }

        // login as president
        else if (checkUsernamePasswordCaptcha(this.username.getText(), this.password.getText(), "president")) {
            log.info("User logged in");
            main.changeScene("PresidentMain.fxml");
        }

        // check empty fields
        else if (this.username.getText().isEmpty() || this.password.getText().isEmpty() || this.captcha.getText().isEmpty()) {
            log.error("User empty information");
            this.wrongLogIn.setText("Please enter your information !");
            changeCaptchaFunction();
        }

        // check wrong information
        else {
            log.error("User entered wrong information");
            this.wrongLogIn.setText("Wrong information !");
            changeCaptchaFunction();
        }
    }

    private boolean checkUsernamePasswordCaptcha(String username, String password, String type) throws IOException {

        // create gson
        Gson gson = new Gson();

        // set current time
        SimpleDateFormat currentFormatter = new SimpleDateFormat("HH:mm:ss");
        Date currentDate = new Date();

        // check students
        switch (type) {
            case "student": {
                BufferedReader reader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Students.json"));
                String line;
                while ((line = reader.readLine()) != null) {
                    Student temp = gson.fromJson(line, Student.class);
                    if (temp.getName() != null && temp.getStudentNumber() != null) {
                        if (temp.getName().equalsIgnoreCase(username) && temp.getStudentNumber().equals(password) && checkCaptcha()) {
                            reader.close();
                            // save logged in user in a file
                            int firstTime = 0;
                            int secondTime = 0;
                            String currentTime = currentFormatter.format(currentDate);
                            if (temp.getLastEntry() != null) {
                                firstTime = ((temp.getLastEntry().charAt(0) - 48) * 10 + (temp.getLastEntry().charAt(1) - 48)) * 60
                                        + ((temp.getLastEntry().charAt(3) - 48) * 10 + (temp.getLastEntry().charAt(4)) - 48);
                                secondTime = ((currentTime.charAt(0) - 48) * 10 + (currentTime.charAt(1) - 48)) * 60
                                        + ((currentTime.charAt(3) - 48) * 10 + (currentTime.charAt(4)) - 48);
                            }
                            BufferedWriter writer = new BufferedWriter(new FileWriter("src\\LOGIC\\Files\\LoggedInUser.json"));
                            // check log in time
                            if (temp.getLastEntry() == null) {
                                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
                                Date date = new Date();
                                temp.setLastEntry(formatter.format(date));
                                String json = gson.toJson(temp);
                                writer.write(json);
                                writer.close();
                                changeTimeUser("student");
                                return true;
                            }
                            else if (Math.abs(secondTime - firstTime) > TIME_LIMIT) {
                                String json = gson.toJson(temp);
                                writer.write(json);
                                writer.close();
                                Main main = new Main();
                                log.error("User exceeds time limit of logging");
                                main.changeScene("StudentChangePassword.fxml");
                                return false;
                            }
                            else {
                                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
                                Date date = new Date();
                                temp.setLastEntry(formatter.format(date));
                                String json = gson.toJson(temp);
                                writer.write(json);
                                writer.close();
                                changeTimeUser("student");
                                return true;
                            }
                        }
                    }
                }
                break;
            }

            // check professors
            case "professor": {
                BufferedReader reader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Professors.json"));
                String line;
                while ((line = reader.readLine()) != null) {
                    Professor temp = gson.fromJson(line, Professor.class);
                    if (temp.getName() != null && temp.getProfessorNumber() != null) {
                        if (temp.getName().equalsIgnoreCase(username) && temp.getProfessorNumber().equals(password) && checkCaptcha()) {
                            reader.close();
                            // save logged in user in a file
                            int firstTime = 0;
                            int secondTime = 0;
                            String currentTime = currentFormatter.format(currentDate);
                            if (temp.getLastEntry() != null) {
                                firstTime = ((temp.getLastEntry().charAt(0) - 48) * 10 + (temp.getLastEntry().charAt(1) - 48)) * 60
                                        + ((temp.getLastEntry().charAt(3) - 48) * 10 + (temp.getLastEntry().charAt(4)) - 48);
                                secondTime = ((currentTime.charAt(0) - 48) * 10 + (currentTime.charAt(1) - 48)) * 60
                                        + ((currentTime.charAt(3) - 48) * 10 + (currentTime.charAt(4)) - 48);
                            }
                            BufferedWriter writer = new BufferedWriter(new FileWriter("src\\LOGIC\\Files\\LoggedInUser.json"));
                            // check log in time
                            if (temp.getLastEntry() == null) {
                                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
                                Date date = new Date();
                                temp.setLastEntry(formatter.format(date));
                                String json = gson.toJson(temp);
                                writer.write(json);
                                writer.close();
                                changeTimeUser("professor");
                                return true;
                            }
                            else if (Math.abs(secondTime - firstTime) > TIME_LIMIT) {
                                String json = gson.toJson(temp);
                                writer.write(json);
                                writer.close();
                                Main main = new Main();
                                log.error("User exceeds time limit of logging");
                                main.changeScene("ProfessorChangePassword.fxml");
                                return false;
                            }
                            else {
                                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
                                Date date = new Date();
                                temp.setLastEntry(formatter.format(date));
                                String json = gson.toJson(temp);
                                writer.write(json);
                                writer.close();
                                changeTimeUser("professor");
                                return true;
                            }
                        }
                    }
                }
                break;
            }

            // check deputies
            case "deputy": {
                BufferedReader reader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Deputies.json"));
                String line;
                while ((line = reader.readLine()) != null) {
                    Professor temp = gson.fromJson(line, Professor.class);
                    if (temp.getName() != null && temp.getProfessorNumber() != null) {
                        if (temp.getName().equalsIgnoreCase(username) && temp.getProfessorNumber().equals(password) && checkCaptcha()) {
                            reader.close();
                            // save logged in user in a file
                            int firstTime = 0;
                            int secondTime = 0;
                            String currentTime = currentFormatter.format(currentDate);
                            if (temp.getLastEntry() != null) {
                                firstTime = ((temp.getLastEntry().charAt(0) - 48) * 10 + (temp.getLastEntry().charAt(1) - 48)) * 60
                                        + ((temp.getLastEntry().charAt(3) - 48) * 10 + (temp.getLastEntry().charAt(4)) - 48);
                                secondTime = ((currentTime.charAt(0) - 48) * 10 + (currentTime.charAt(1) - 48)) * 60
                                        + ((currentTime.charAt(3) - 48) * 10 + (currentTime.charAt(4)) - 48);
                            }
                            BufferedWriter writer = new BufferedWriter(new FileWriter("src\\LOGIC\\Files\\LoggedInUser.json"));
                            // check log in time
                            if (temp.getLastEntry() == null) {
                                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
                                Date date = new Date();
                                temp.setLastEntry(formatter.format(date));
                                String json = gson.toJson(temp);
                                writer.write(json);
                                writer.close();
                                changeTimeUser("professor");
                                return true;
                            }
                            else if (Math.abs(secondTime - firstTime) > TIME_LIMIT) {
                                String json = gson.toJson(temp);
                                writer.write(json);
                                writer.close();
                                Main main = new Main();
                                log.error("User exceeds time limit of logging");
                                main.changeScene("ProfessorChangePassword.fxml");
                                return false;
                            }
                            else {
                                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
                                Date date = new Date();
                                temp.setLastEntry(formatter.format(date));
                                String json = gson.toJson(temp);
                                writer.write(json);
                                writer.close();
                                changeTimeUser("professor");
                                return true;
                            }
                        }
                    }
                }
                break;
            }

            // check presidents
            case "president" : {
                BufferedReader reader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Presidents.json"));
                String line;
                while ((line = reader.readLine()) != null) {
                    President temp = gson.fromJson(line, President.class);
                    if (temp.getName() != null && temp.getProfessorNumber() != null) {
                        if (temp.getName().equalsIgnoreCase(username) && temp.getProfessorNumber().equals(password) && checkCaptcha()) {
                            reader.close();
                            // save logged in user in a file
                            int firstTime = 0;
                            int secondTime = 0;
                            String currentTime = currentFormatter.format(currentDate);
                            if (temp.getLastEntry() != null) {
                                firstTime = ((temp.getLastEntry().charAt(0) - 48) * 10 + (temp.getLastEntry().charAt(1) - 48)) * 60
                                        + ((temp.getLastEntry().charAt(3) - 48) * 10 + (temp.getLastEntry().charAt(4)) - 48);
                                secondTime = ((currentTime.charAt(0) - 48) * 10 + (currentTime.charAt(1) - 48)) * 60
                                        + ((currentTime.charAt(3) - 48) * 10 + (currentTime.charAt(4)) - 48);
                            }
                            BufferedWriter writer = new BufferedWriter(new FileWriter("src\\LOGIC\\Files\\LoggedInUser.json"));
                            // check log in time
                            if (temp.getLastEntry() == null) {
                                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
                                Date date = new Date();
                                temp.setLastEntry(formatter.format(date));
                                String json = gson.toJson(temp);
                                writer.write(json);
                                writer.close();
                                changeTimeUser("professor");
                                return true;
                            }
                            else if (Math.abs(secondTime - firstTime) > TIME_LIMIT) {
                                String json = gson.toJson(temp);
                                writer.write(json);
                                writer.close();
                                Main main = new Main();
                                log.error("User exceeds time limit of logging");
                                main.changeScene("ProfessorChangePassword.fxml");
                                return false;
                            }
                            else {
                                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
                                Date date = new Date();
                                temp.setLastEntry(formatter.format(date));
                                String json = gson.toJson(temp);
                                writer.write(json);
                                writer.close();
                                changeTimeUser("professor");
                                return true;
                            }
                        }
                    }
                }
                break;
            }
        }
        return false;
    }

    private void changeTimeUser(String userType) throws IOException {

        switch (userType) {

            case "student" :

                // get user
                Gson tempGson = new Gson();
                BufferedReader userReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\LoggedInUser.json"));
                Student user = tempGson.fromJson(userReader.readLine(), Student.class);
                userReader.close();

                // new student
                Student newStudent = new Student(user.getName(), user.getNationalCode(), user.getStudentNumber(),
                        user.getPhoneNumber(), user.getEmail(), user.getGPA(), user.getCollegeName(), user.getSupervisor(),
                        user.getEnteringYear(), user.getImage(), user.getStudentType(), user.getEducationalStatus());
                newStudent.setLastEntry(user.getLastEntry());
                newStudent.setCourses(user.getCourses());

                Gson gson = new Gson();

                // remove ex student
                int help = 0;
                BufferedWriter tempWriter = new BufferedWriter(new FileWriter("src\\LOGIC\\Files\\Temp.json"));
                BufferedReader studentReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Students.json"));
                String line;
                while ((line = studentReader.readLine()) != null) {
                    Student tempStudent = gson.fromJson(line, Student.class);
                    String json = gson.toJson(tempStudent);
                    if (!tempStudent.getStudentNumber().equals(user.getStudentNumber())) {
                        if (help == 0) {
                            tempWriter.write(json);
                            help = 1;
                        } else {
                            tempWriter.write("\n" + json);
                        }
                    }
                }
                tempWriter.close();
                studentReader.close();

                // copy all students from temp file to students file
                int anotherHelp = 0;
                BufferedWriter newStudentWriter = new BufferedWriter(new FileWriter("src\\LOGIC\\Files\\Students.json"));
                BufferedReader newStudentReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Temp.json"));
                while ((line = newStudentReader.readLine()) != null) {
                    Student tempStudent = gson.fromJson(line, Student.class);
                    String json = gson.toJson(tempStudent);
                    if (anotherHelp == 0) {
                        newStudentWriter.write(json);
                        anotherHelp = 1;
                    } else {
                        newStudentWriter.write("\n" + json);
                    }
                }
                String student = gson.toJson(newStudent);
                if (anotherHelp == 0) {
                    newStudentWriter.write(student);
                }
                else {
                    newStudentWriter.write("\n" + student);
                }
                newStudentReader.close();
                newStudentWriter.close();
                break;

            case "professor" :

                // get user
                Gson newTempGson = new Gson();
                BufferedReader newUserReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\LoggedInUser.json"));
                Professor newUser = newTempGson.fromJson(newUserReader.readLine(), Professor.class);
                newUserReader.close();

                // set strings due to type of user
                String jsonFile = null;
                switch (newUser.getRole()) {
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
                Professor newProfessor = new Professor(newUser.getName(), newUser.getNationalCode(), newUser.getProfessorNumber(),
                        newUser.getPhoneNumber(), newUser.getEmail(), newUser.getRole(), newUser.getCollegeName(), newUser.getRoomNumber(),
                        newUser.getImage(), newUser.getProfessorDegree());
                newProfessor.setLastEntry(newUser.getLastEntry());
                newProfessor.setCourses(newUser.getCourses());

                Gson newGson = new Gson();

                // remove ex professor
                int newHelp = 0;
                BufferedWriter newTempWriter = new BufferedWriter(new FileWriter("src\\LOGIC\\Files\\Temp.json"));
                assert jsonFile != null;
                BufferedReader professorReader = new BufferedReader(new FileReader(jsonFile));
                String newLine;
                while ((newLine = professorReader.readLine()) != null) {
                    Professor tempProfessor = newGson.fromJson(newLine, Professor.class);
                    String json = newGson.toJson(tempProfessor);
                    if (!tempProfessor.getProfessorNumber().equals(newUser.getProfessorNumber())) {
                        if (newHelp == 0) {
                            newTempWriter.write(json);
                            newHelp = 1;
                        } else {
                            newTempWriter.write("\n" + json);
                        }
                    }
                }
                newTempWriter.close();
                professorReader.close();

                // copy all professors from temp file to professors file
                int newAnotherHelp = 0;
                BufferedWriter newProfessorWriter = new BufferedWriter(new FileWriter(jsonFile));
                BufferedReader newProfessorReader = new BufferedReader(new FileReader("src\\LOGIC\\Files\\Temp.json"));
                while ((newLine = newProfessorReader.readLine()) != null) {
                    Professor tempProfessor = newGson.fromJson(newLine, Professor.class);
                    String json = newGson.toJson(tempProfessor);
                    if (newAnotherHelp == 0) {
                        newProfessorWriter.write(json);
                        newAnotherHelp = 1;
                    } else {
                        newProfessorWriter.write("\n" + json);
                    }
                }
                String professor = newGson.toJson(newProfessor);
                if (newAnotherHelp == 0) {
                    newProfessorWriter.write(professor);
                }
                else {
                    newProfessorWriter.write("\n" + professor);
                }
                newProfessorReader.close();
                newProfessorWriter.close();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // generate a random number
        int random = (int) Math.floor(Math.random() * (5 - 1 + 1) + 1);

        // set a random captcha image
        if (random == 1) {
            captchaImage.setImage(new Image("GUI\\Images\\captcha1.jpg"));
            this.captchaNumber = 1;
        }
        else if (random == 2) {
            captchaImage.setImage(new Image("GUI\\Images\\captcha2.jpg"));
            this.captchaNumber = 2;
        }
        else if (random == 3) {
            captchaImage.setImage(new Image("GUI\\Images\\captcha3.jpg"));
            this.captchaNumber = 3;
        }
        else if (random == 4) {
            captchaImage.setImage(new Image("GUI\\Images\\captcha4.jpg"));
            this.captchaNumber = 4;
        }
        else {
            captchaImage.setImage(new Image("GUI\\Images\\captcha5.jpg"));
            this.captchaNumber = 5;
        }
    }
}