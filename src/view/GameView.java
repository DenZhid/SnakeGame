package view;

import core.Game;

import core.Snake;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.util.EnumMap;

public class GameView {

    private ImageView[][] arrayOfImages;
    private EnumMap<ViewSegments, Image> mapOfImages;
    private final Game forGraphics;
    private final int WIDTH;
    private final int HEIGHT;

    private enum ViewSegments {
        EMPTY,
        SNAKEHEAD,
        SNAKEBODY,
        FRUIT,
        ENEMYSNAKEHEAD,
        ENEMYSNAKEBODY
    }

    public GameView(Game game, int WIDTH, int HEIGHT) throws FileNotFoundException {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.forGraphics = game;
        arrayOfImages = new ImageView[game.gameBoard.x][game.gameBoard.y];

        importImages();
        redraw();
    }

    public void importImages() throws FileNotFoundException {
        mapOfImages = new EnumMap<>(ViewSegments.class);
        mapOfImages.put(ViewSegments.EMPTY, new Image(new FileInputStream("images/Empty.png")));
        mapOfImages.put(ViewSegments.SNAKEHEAD, new Image(new FileInputStream("images/SnakeHead.png")));
        mapOfImages.put(ViewSegments.SNAKEBODY, new Image(new FileInputStream("images/SnakeBody.png")));
        mapOfImages.put(ViewSegments.FRUIT, new Image(new FileInputStream("images/Fruit.png")));
        mapOfImages.put(ViewSegments.ENEMYSNAKEHEAD, new Image(new FileInputStream("images/EnemySnakeHead.png")));
        mapOfImages.put(ViewSegments.ENEMYSNAKEBODY, new Image(new FileInputStream("images/EnemySnakeBody.png")));
    }

    public void drawScene() {
        redraw();
        drawFruit();
        if (forGraphics.enemySnake.isAlive) {
            drawSnake(forGraphics.enemySnake);
        }
        if (forGraphics.snake.isAlive) {
            drawSnake(forGraphics.snake);
        }
    }

    private void redraw() {
        for (int y = 0; y < forGraphics.gameBoard.y; y++) {
            for (int x = 0; x < forGraphics.gameBoard.x; x++) {
                ImageView newImage = new ImageView();
                newImage.setImage(mapOfImages.get(ViewSegments.EMPTY));
                newImage.setFitWidth(WIDTH/forGraphics.gameBoard.x);
                newImage.setFitHeight(HEIGHT/forGraphics.gameBoard.y);
                arrayOfImages[x][y] = newImage;
            }
        }
    }

    private void drawFruit() {
        arrayOfImages[forGraphics.fruit.x][forGraphics.fruit.y].setImage(mapOfImages.get(ViewSegments.FRUIT));
        arrayOfImages[forGraphics.fruit.x][forGraphics.fruit.y].setFitWidth(WIDTH/forGraphics.gameBoard.x);
        arrayOfImages[forGraphics.fruit.x][forGraphics.fruit.y].setFitHeight(HEIGHT/forGraphics.gameBoard.y);
    }

    private void drawSnake(Snake inputSnake) {
        if (inputSnake == forGraphics.snake) {
            arrayOfImages[inputSnake.snakeParts.get(0).x][inputSnake.snakeParts.get(0).y]
                    .setImage(mapOfImages.get(ViewSegments.SNAKEHEAD));
            arrayOfImages[inputSnake.snakeParts.get(0).x][inputSnake.snakeParts.get(0).y]
                    .setFitWidth(WIDTH/forGraphics.gameBoard.x);
            arrayOfImages[inputSnake.snakeParts.get(0).x][inputSnake.snakeParts.get(0).y]
                    .setFitHeight(HEIGHT/forGraphics.gameBoard.y);
            for (int i = 1; i < inputSnake.snakeParts.size(); i++) {
                arrayOfImages[inputSnake.snakeParts.get(i).x][inputSnake.snakeParts.get(i).y]
                        .setImage(mapOfImages.get(ViewSegments.SNAKEBODY));
                arrayOfImages[inputSnake.snakeParts.get(i).x][inputSnake.snakeParts.get(i).y]
                        .setFitWidth(WIDTH/forGraphics.gameBoard.x);
                arrayOfImages[inputSnake.snakeParts.get(i).x][inputSnake.snakeParts.get(i).y]
                        .setFitHeight(HEIGHT/forGraphics.gameBoard.y);
            }
        } else {
            arrayOfImages[inputSnake.snakeParts.get(0).x][inputSnake.snakeParts.get(0).y]
                    .setImage(mapOfImages.get(ViewSegments.ENEMYSNAKEHEAD));
            arrayOfImages[inputSnake.snakeParts.get(0).x][inputSnake.snakeParts.get(0).y]
                    .setFitWidth(WIDTH / forGraphics.gameBoard.x);
            arrayOfImages[inputSnake.snakeParts.get(0).x][inputSnake.snakeParts.get(0).y]
                    .setFitHeight(HEIGHT / forGraphics.gameBoard.y);
            for (int i = 1; i < inputSnake.snakeParts.size(); i++) {
                arrayOfImages[inputSnake.snakeParts.get(i).x][inputSnake.snakeParts.get(i).y]
                        .setImage(mapOfImages.get(ViewSegments.ENEMYSNAKEBODY));
                arrayOfImages[inputSnake.snakeParts.get(i).x][inputSnake.snakeParts.get(i).y]
                        .setFitWidth(WIDTH / forGraphics.gameBoard.x);
                arrayOfImages[inputSnake.snakeParts.get(i).x][inputSnake.snakeParts.get(i).y]
                        .setFitHeight(HEIGHT / forGraphics.gameBoard.y);
            }
        }
    }

    public ImageView[][] getArrayOfImages(){
        return arrayOfImages;
    }
}
