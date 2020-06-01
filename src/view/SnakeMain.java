package view;

import controller.ControllerOfStartScreen;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static com.sun.javafx.scene.control.skin.Utils.getResource;

public class SnakeMain extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        ControllerOfStartScreen startController = new FXMLLoader(getResource("/screens/startScreen.fxml")).getController();
        startController.setStage(stage);
        startController.setStartScreen();

        Parent root = FXMLLoader.load(getResource("/screens/startScreen.fxml"));
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Snake");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
