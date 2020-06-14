package controller;

import core.Difficulty;
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

    private int turnDelay;
    private Game game;
    private GameView graphics;
    private Timeline timeLine;
    private double WIDTH;
    private double HEIGHT;


    public void goFrame() throws FileNotFoundException {
        graphics = new GameView(game, WIDTH, HEIGHT);
        graphics.drawScene();
        gamePane.setPrefSize(WIDTH, HEIGHT);
        setGraphicsOnPane();
        gamePane.setFocusTraversable(true);
        timeLine.setCycleCount(Animation.INDEFINITE);
        timeLine.play();
    }

    public void keyPressed(KeyEvent k) {
        switch (k.getCode()) {
            case A: {
                game.getSnake().setNextDirection(Direction.LEFT);
                break;
            }
            case D: {
                game.getSnake().setNextDirection(Direction.RIGHT);
                break;
            }
            case S: {
                game.getSnake().setNextDirection(Direction.DOWN);
                break;
            }
            case W: {
                game.getSnake().setNextDirection(Direction.UP);
                break;
            }
        }
    }

    private void setGraphicsOnPane() {
        gamePane.getChildren().clear();
        ImageView[][] images = graphics.getArrayOfImages();
        for (int y = 0; y < game.getGameBoard().y; y++) {
            for (int x = 0; x < game.getGameBoard().x; x++) {
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

    public void setTurnDelay(Difficulty difficulty) {
        switch (difficulty) {
            case EASY:
                turnDelay = 300;
                break;
            case NORMAL:
                turnDelay = 200;
                break;
            case HARD:
                turnDelay = 100;
                break;
        }
       timeLine = new Timeline(new KeyFrame(Duration.millis(turnDelay), event -> {
            game.step();
            switch(game.checkStatus()) {
                case LOSE:
                    gameOver();
                    break;
                case WIN:
                    win();
                    break;
                case CONTINUE:
                    break;
            }
            graphics.drawScene();
            setGraphicsOnPane();
        }));
    }

    public void setWidthAndHeight(double WIDTH, double HEIGHT) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
    }
}
