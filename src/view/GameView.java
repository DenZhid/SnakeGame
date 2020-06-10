package view;

import core.Game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class GameView {

    public ImageView[][] arrayOfImages;
    private Map<Integer, Image> mapOfImages;
    private Game forGraphics;
    private int WIDTH = 600;
    private int HEIGHT = 400;

    public GameView(Game game) throws FileNotFoundException {
        this.forGraphics = game;
        arrayOfImages = new ImageView[game.gameBoard.x][game.gameBoard.y];

        importImages();
        redraw();
    }

    public void importImages() throws FileNotFoundException {
        mapOfImages = new HashMap<>();
        mapOfImages.put(0, new Image(new FileInputStream("images/Empty.png")));
        mapOfImages.put(1, new Image(new FileInputStream("images/SnakeHead.png")));
        mapOfImages.put(2, new Image(new FileInputStream("images/SnakeBody.png")));
        mapOfImages.put(3, new Image(new FileInputStream("images/Fruit.png")));
        mapOfImages.put(-1, new Image(new FileInputStream("images/EnemySnakeHead.png")));
        mapOfImages.put(-2, new Image(new FileInputStream("images/EnemySnakeBody.png")));
    }

    public void drawScene() {
        redraw();
        drawFruit();
        if (forGraphics.enemySnake.isAlive) {
            drawEnemySnake();
        }
        drawSnake();
    }

    public void redraw() {
        for (int y = 0; y < forGraphics.gameBoard.y; y++) {
            for (int x = 0; x < forGraphics.gameBoard.x; x++) {
                ImageView newImage = new ImageView();
                newImage.setImage(mapOfImages.get(0));
                newImage.setFitWidth(WIDTH/forGraphics.gameBoard.x);
                newImage.setFitHeight(HEIGHT/forGraphics.gameBoard.y);
                arrayOfImages[x][y] = newImage;
            }
        }
    }

    public void drawFruit() {
        ImageView newImage = new ImageView();
        newImage.setImage(mapOfImages.get(3));
        newImage.setFitWidth(WIDTH/forGraphics.gameBoard.x);
        newImage.setFitHeight(HEIGHT/forGraphics.gameBoard.y);
        arrayOfImages[forGraphics.fruit.x][forGraphics.fruit.y] = newImage;
    }

    public void drawSnake() {
        ImageView newImageHead = new ImageView();
        newImageHead.setImage(mapOfImages.get(1));
        newImageHead.setFitWidth(WIDTH/forGraphics.gameBoard.x);
        newImageHead.setFitHeight(HEIGHT/forGraphics.gameBoard.y);
        arrayOfImages[forGraphics.snake.snakeParts.get(0).x][forGraphics.snake.snakeParts.get(0).y] = newImageHead;
        for (int i = 1; i < forGraphics.snake.snakeParts.size(); i++) {
            ImageView newImageBody = new ImageView();
            newImageBody.setImage(mapOfImages.get(2));
            newImageBody.setFitWidth(WIDTH/forGraphics.gameBoard.x);
            newImageBody.setFitHeight(HEIGHT/forGraphics.gameBoard.y);
            arrayOfImages[forGraphics.snake.snakeParts.get(i).x][forGraphics.snake.snakeParts.get(i).y] = newImageBody;
        }
    }
    public void drawEnemySnake() {
        ImageView newImageHead = new ImageView();
        newImageHead.setImage(mapOfImages.get(-1));
        newImageHead.setFitWidth(WIDTH/forGraphics.gameBoard.x);
        newImageHead.setFitHeight(HEIGHT/forGraphics.gameBoard.y);
        arrayOfImages[forGraphics.enemySnake.snakeParts.get(0).x][forGraphics.enemySnake.snakeParts.get(0).y] = newImageHead;
        for (int i = 1; i < forGraphics.enemySnake.snakeParts.size(); i++) {
            ImageView newImageBody = new ImageView();
            newImageBody.setImage(mapOfImages.get(-2));
            newImageBody.setFitWidth(WIDTH/forGraphics.gameBoard.x);
            newImageBody.setFitHeight(HEIGHT/forGraphics.gameBoard.y);
            arrayOfImages[forGraphics.enemySnake.snakeParts.get(i).x][forGraphics.enemySnake.snakeParts.get(i).y] = newImageBody;
        }
    }

    public ImageView[][] getArrayOfImages(){
        return arrayOfImages;
    }
}
