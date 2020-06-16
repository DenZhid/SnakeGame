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

public class ControllerOfStartScreen {

    public ComboBox<Difficulty> comboBoxDifficulty;
    public ComboBox<String> comboBoxSize;
    public static final double WIDTH = 600;
    public static final double HEIGHT = 400;

    private Stage stage;

    public void setStartScreen() {
        ObservableList<String> listOfSizes = FXCollections.observableArrayList("10x10", "15x15", "20x20");
        ObservableList<Difficulty> listOfDifficulties = FXCollections.observableArrayList(
                Difficulty.EASY, Difficulty.NORMAL, Difficulty.HARD
        );
        comboBoxDifficulty.setItems(listOfDifficulties);
        comboBoxSize.setItems(listOfSizes);
    }

    @FXML
    public void startGame() throws Exception {
        Difficulty difficulty = (comboBoxDifficulty.getValue() == null)? Difficulty.EASY: comboBoxDifficulty.getValue();
        String size = (comboBoxSize.getValue() == null)? "10x10": comboBoxSize.getValue();

        GameBoard gameBoard = new GameBoard(size);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gameScreen.fxml"));
        Parent root = loader.load();
        GameController gameController = loader.getController();
        Game game = new Game(gameBoard, difficulty);
        gameController.setGame(game);
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