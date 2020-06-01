package controller;

import core.GameBoard;
import core.Snake;
import core.Fruit;
import core.Direction;

import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.util.Timer;
import java.util.TimerTask;

public class GameController {

    public static Pane pane;
    public static GameBoard gameBoard;
    public static int GOAL = 10;//todo goal variable

    private int turnDelay;
    private Fruit fruit;
    private Snake snake;


    public void goFrame() {
        turnDelay = 300;
        snake = new Snake( gameBoard.getX()/2,  gameBoard.getY()/2);
        createNewFruit();
        Timer timer = new Timer();
        TimerTask onTurn = new Turn();
        drawScene();
        //score
        //pause
        timer.scheduleAtFixedRate(onTurn, 0,10*turnDelay);
    }

    private class Turn extends TimerTask {
        @Override
        public void run() {
            snake.move(fruit);
            if (!fruit.isAlive) {
                //score = score + 5;
                //setScore(score);
                turnDelay = turnDelay - 10;
                fruit = createNewFruit();
            }
            if (!snake.isAlive) {
                gameOver();
            }
            if (snake.getLength() > GOAL) {
                win();
            }
            drawScene();
        }
    }

    public void KeyPressed(KeyEvent k) {
        switch (k.getCode()) {
            case A: {
                snake.setDirection(Direction.LEFT);
                break;
            }
            case D: {
                snake.setDirection(Direction.RIGHT);
                break;
            }
            case S: {
                snake.setDirection(Direction.DOWN);
                break;
            }
            case W: {
                snake.setDirection(Direction.UP);
                break;
            }
                /*case KeyEvent.VK_SPACE: {
                    if (isGameStopped) {
                        createGame();
                    }
        }*/
        }
    }

    private int getRandomPosX() {
        return (int) (Math.random() * gameBoard.getX());
    }

    private int getRandomPosY() {
        return (int) (Math.random() * gameBoard.getX());
    }

    private Fruit createNewFruit() {
        Fruit newApple = new Fruit(getRandomPosX(), getRandomPosY());
        while (snake.checkCollision(newApple)) {
            newApple = new Fruit(getRandomPosX(), getRandomPosY());
        }
        return newApple;
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

    public void drawScene() {
        pane.setPrefSize(gameBoard.getX(), gameBoard.getY());
        snake.draw(gameBoard);
        snake.draw(gameBoard);
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }
}
