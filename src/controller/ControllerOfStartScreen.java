package controller;

import core.Difficulty;
import core.Game;
import core.GameBoard;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import view.GameView;

public class ControllerOfStartScreen {

    public ComboBox<Difficulty> comboBoxDifficulty;
    public ComboBox<String> comboBoxSize;
    public ComboBox<String> comboBoxWindow;

    private Stage stage;


    public void setStartScreen() {
        ObservableList<String> listOfSizes = FXCollections.observableArrayList("10x10", "15x15", "20x20");
        ObservableList<Difficulty> listOfDifficulties = FXCollections.observableArrayList(
                Difficulty.EASY, Difficulty.NORMAL, Difficulty.HARD
        );
        ObservableList<String> listOfWindowSizes = FXCollections.observableArrayList(
                "600x400", "800x600","1280x1024"
        );
        comboBoxDifficulty.setItems(listOfDifficulties);
        comboBoxSize.setItems(listOfSizes);
        comboBoxWindow.setItems(listOfWindowSizes);
    }

    @FXML
    public void startGame() throws Exception {
        Difficulty difficulty = (comboBoxDifficulty.getValue() == null)? Difficulty.EASY: comboBoxDifficulty.getValue();
        String size = (comboBoxSize.getValue() == null)? "10x10": comboBoxSize.getValue();
        String windowSize = (comboBoxWindow.getValue() == null)? "600x400": comboBoxWindow.getValue();

        int WIDTH = Integer.parseInt(windowSize.split("x")[0]);
        int HEIGHT = Integer.parseInt(windowSize.split("x")[1]);
        GameBoard gameBoard = new GameBoard(size);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gameScreen.fxml"));
        Parent root = loader.load();
        GameController gameController = loader.getController();
        Game game = new Game(gameBoard, difficulty);
        gameController.setGame(game);
        gameController.setWidthAndHeight(WIDTH, HEIGHT);
        gameController.setTurnDelay(difficulty);
        gameController.goFrame();

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage.setTitle("Snake");
        stage.setScene(scene);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}