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
        //mapOfImages.put(-1, new Image(new FileInputStream("images/EnemySnakeHead.png")));
        //mapOfImages.put(-2, new Image(new FileInputStream("images/EnemySnakeBody.png")));
    }

    public void drawScene() throws FileNotFoundException {
        redraw();
        drawFruit();
        drawSnake();
    }

    public void redraw() {
        for (int y = 0; y < forGraphics.gameBoard.y; y++) {
            for (int x = 0; x < forGraphics.gameBoard.x; x++) {
                ImageView newImage = new ImageView();
                newImage.setImage(mapOfImages.get(0));
                newImage.setFitWidth(WIDTH/10);//поменять на изменяемые
                newImage.setFitHeight(HEIGHT/10);
                arrayOfImages[x][y] = newImage;
            }
        }
    }

    public void drawFruit() {
        ImageView newImage = new ImageView();
        newImage.setImage(mapOfImages.get(3));
        newImage.setFitWidth(WIDTH/10);//поменять на изменяемые
        newImage.setFitHeight(HEIGHT/10);
        arrayOfImages[forGraphics.fruit.x][forGraphics.fruit.y] = newImage;
    }

    public void drawSnake() {
        ImageView newImageHead = new ImageView();
        newImageHead.setImage(mapOfImages.get(1));
        newImageHead.setFitWidth(WIDTH/10);//поменять на изменяемые
        newImageHead.setFitHeight(HEIGHT/10);
        arrayOfImages[forGraphics.snake.snakeParts.get(0).x][forGraphics.snake.snakeParts.get(0).y] = newImageHead;
        for (int i = 1; i < forGraphics.snake.snakeParts.size(); i++) {
            ImageView newImageBody = new ImageView();
            newImageBody.setImage(mapOfImages.get(2));
            newImageBody.setFitWidth(WIDTH/10);//поменять на изменяемые
            newImageBody.setFitHeight(HEIGHT/10);
            arrayOfImages[forGraphics.snake.snakeParts.get(i).x][forGraphics.snake.snakeParts.get(i).y] = newImageBody;
        }
    }

    public ImageView[][] getArrayOfImages(){
        return arrayOfImages;
    }
}
