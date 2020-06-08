package view;

import core.GameBoard;
import core.Snake;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GameView {

    int[][] codeArray;

    public static void drawScene() throws FileNotFoundException {
        //gamePane.getChildren().clear();
        //gamePane.setPrefSize(gameBoard.getX(), gameBoard.getY());
        //drawFruit();
        //drawSnake();
    }

    public void drawFruit(Pane pane) throws FileNotFoundException {
        Image fruit = new Image(new FileInputStream("images/Apple.png"));
        ImageView image = new ImageView();
        image.setImage(fruit);
        pane.getChildren().add(image);
    }

    public void drawSnake(Pane gamePane) throws FileNotFoundException {
        /*Image snakeBody = new Image(new FileInputStream("images/Тело.png"));
        Image snakeHead = new Image(new FileInputStream("images/Голова.png"));
        ImageView image = new ImageView();
        image.setImage(snakeHead);
        gamePane.getChildren().add(image);
        image.setImage(snakeBody);
        //for (int i = 1; i < snakeParts.size(); i++) {
           //gamePane.getChildren().add(image);
        }*/
    }
}
