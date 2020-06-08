package controller;

import core.Game;
import core.Direction;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import view.GameView;

import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.image.ImageView;
import javafx.animation.Timeline;

import java.io.FileNotFoundException;

public class GameController {

    public GridPane gamePane;
    public static int GOAL = 10;//todo goal variable

    private int turnDelay;
    private Game game;
    private GameView graphics;
    private KeyCode keyCode;


    public void goFrame() throws FileNotFoundException {
        turnDelay = 300;//изменяется в зависимости от сложности
        graphics = new GameView(game);
        graphics.drawScene();
        setGraphicsOnPane();
        Timeline timeLine = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
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
            try {
                graphics.drawScene();
                setGraphicsOnPane();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }));
        //score
        timeLine.setCycleCount(Animation.INDEFINITE);
        timeLine.play();
    }

    public void KeyPressed(KeyEvent k) {
        keyCode = k.getCode();
    }
    public void KeyReleased(){
        switch (keyCode) {
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
    }

    private void win() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Snake");
        alert.setHeaderText("Вы выиграли!");
        alert.show();
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
