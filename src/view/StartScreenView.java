package view;

import controller.ControllerOfStartScreen;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartScreenView extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("startScreen.fxml"));
        Parent root = loader.load();
        ControllerOfStartScreen startController = loader.getController();
        startController.setStage(stage);
        startController.setStartScreen();

        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Snake");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
