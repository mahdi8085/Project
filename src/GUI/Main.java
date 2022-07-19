package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    Resources resources;

    static Logger log = LogManager.getLogger(Main.class);

    private static Stage stage;

    public Main() throws IOException {
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //resources = new Resources();
        stage = primaryStage;
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SignUp.fxml")));
        primaryStage.setTitle("Sharif University");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(parent));
        primaryStage.show();
        log.info("Application started");
    }

    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml)));
        stage.getScene().setRoot(pane);
        log.info("Change scene to " + fxml);
    }
}
