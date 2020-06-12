package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.image.ImageView;

import java.io.FileNotFoundException;

import view.GameView;

import core.Game;
import core.Direction;

public class GameController {

    public GridPane gamePane;
    public int turnDelay;
    private Game game;
    private GameView graphics;
    private Timeline timeLine;


    public void goFrame() throws FileNotFoundException {
        graphics = new GameView(game);
        graphics.drawScene();
        setGraphicsOnPane();
        gamePane.setFocusTraversable(true);
        timeLine.setCycleCount(Animation.INDEFINITE);
        timeLine.play();
    }

    public void keyPressed(KeyEvent k) {
        switch (k.getCode()) {
            case A: {
                game.snake.setNextDirection(Direction.LEFT);
                break;
            }
            case D: {
                game.snake.setNextDirection(Direction.RIGHT);
                break;
            }
            case S: {
                game.snake.setNextDirection(Direction.DOWN);
                break;
            }
            case W: {
                game.snake.setNextDirection(Direction.UP);
                break;
            }
        }
    }

    private void setGraphicsOnPane() {
        gamePane.getChildren().clear();
        ImageView[][] images = graphics.getArrayOfImages();
        for (int y = 0; y < game.gameBoard.y; y++) {
            for (int x = 0; x < game.gameBoard.x; x++) {
                    gamePane.add(images[x][y], x, y);
            }
        }
    }

    private void gameOver() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Snake");
        alert.setHeaderText("Вы проиграли!");
        alert.show();
        timeLine.stop();
    }

    private void win() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Snake");
        alert.setHeaderText("Вы выиграли!");
        alert.show();
        timeLine.stop();
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setTurnDelay(String difficulty) {
        switch (difficulty) {
            case "Easy":
                turnDelay = 300;
                break;
            case "Normal":
                turnDelay = 200;
                break;
            case "Hard":
                turnDelay = 100;
                break;
        }
       timeLine = new Timeline(new KeyFrame(Duration.millis(turnDelay), event -> {
            game.step();
            switch(game.status) {
                case -1:
                    gameOver();
                    break;
                case 1:
                    win();
                    break;
                case 0:
                    break;
            }
            graphics.drawScene();
            setGraphicsOnPane();
        }));
    }
}
