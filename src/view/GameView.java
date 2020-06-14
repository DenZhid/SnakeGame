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
    private  final double WIDTH;
    private final double HEIGHT;

    private enum ViewSegments {
        EMPTY,
        SNAKEHEAD,
        SNAKEBODY,
        FRUIT,
        ENEMYSNAKEHEAD,
        ENEMYSNAKEBODY
    }

    public GameView(Game game, double WIDTH, double HEIGHT) throws FileNotFoundException {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.forGraphics = game;
        arrayOfImages = new ImageView[game.getGameBoard().x][game.getGameBoard().y];

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
        if (forGraphics.getEnemySnake().getStatusOfSnake()) {
            drawSnake(forGraphics.getEnemySnake());
        }
        if (forGraphics.getSnake().getStatusOfSnake()) {
            drawSnake(forGraphics.getSnake());
        }
    }

    private void redraw() {
        for (int y = 0; y < forGraphics.getGameBoard().y; y++) {
            for (int x = 0; x < forGraphics.getGameBoard().x; x++) {
                ImageView newImage = new ImageView();
                newImage.setImage(mapOfImages.get(ViewSegments.EMPTY));
                newImage.setFitWidth(WIDTH/forGraphics.getGameBoard().x);
                newImage.setFitHeight(HEIGHT/forGraphics.getGameBoard().y);
                arrayOfImages[x][y] = newImage;
            }
        }
    }

    private void drawFruit() {
        arrayOfImages[forGraphics.getFruit().x][forGraphics.getFruit().y].setImage(mapOfImages.get(ViewSegments.FRUIT));
        arrayOfImages[forGraphics.getFruit().x][forGraphics.getFruit().y].setFitWidth(WIDTH/forGraphics.getGameBoard().x);
        arrayOfImages[forGraphics.getFruit().x][forGraphics.getFruit().y].setFitHeight(HEIGHT/forGraphics.getGameBoard().y);
    }

    private void drawSnake(Snake inputSnake) {
        if (inputSnake == forGraphics.getSnake()) {
            arrayOfImages[inputSnake.getSnakeParts().get(0).x][inputSnake.getSnakeParts().get(0).y]
                    .setImage(mapOfImages.get(ViewSegments.SNAKEHEAD));
            arrayOfImages[inputSnake.getSnakeParts().get(0).x][inputSnake.getSnakeParts().get(0).y]
                    .setFitWidth(WIDTH/forGraphics.getGameBoard().x);
            arrayOfImages[inputSnake.getSnakeParts().get(0).x][inputSnake.getSnakeParts().get(0).y]
                    .setFitHeight(HEIGHT/forGraphics.getGameBoard().y);
            for (int i = 1; i < inputSnake.getSnakeParts().size(); i++) {
                arrayOfImages[inputSnake.getSnakeParts().get(i).x][inputSnake.getSnakeParts().get(i).y]
                        .setImage(mapOfImages.get(ViewSegments.SNAKEBODY));
                arrayOfImages[inputSnake.getSnakeParts().get(i).x][inputSnake.getSnakeParts().get(i).y]
                        .setFitWidth(WIDTH/forGraphics.getGameBoard().x);
                arrayOfImages[inputSnake.getSnakeParts().get(i).x][inputSnake.getSnakeParts().get(i).y]
                        .setFitHeight(HEIGHT/forGraphics.getGameBoard().y);
            }
        } else {
            arrayOfImages[inputSnake.getSnakeParts().get(0).x][inputSnake.getSnakeParts().get(0).y]
                    .setImage(mapOfImages.get(ViewSegments.ENEMYSNAKEHEAD));
            arrayOfImages[inputSnake.getSnakeParts().get(0).x][inputSnake.getSnakeParts().get(0).y]
                    .setFitWidth(WIDTH / forGraphics.getGameBoard().x);
            arrayOfImages[inputSnake.getSnakeParts().get(0).x][inputSnake.getSnakeParts().get(0).y]
                    .setFitHeight(HEIGHT / forGraphics.getGameBoard().y);
            for (int i = 1; i < inputSnake.getSnakeParts().size(); i++) {
                arrayOfImages[inputSnake.getSnakeParts().get(i).x][inputSnake.getSnakeParts().get(i).y]
                        .setImage(mapOfImages.get(ViewSegments.ENEMYSNAKEBODY));
                arrayOfImages[inputSnake.getSnakeParts().get(i).x][inputSnake.getSnakeParts().get(i).y]
                        .setFitWidth(WIDTH / forGraphics.getGameBoard().x);
                arrayOfImages[inputSnake.getSnakeParts().get(i).x][inputSnake.getSnakeParts().get(i).y]
                        .setFitHeight(HEIGHT / forGraphics.getGameBoard().y);
            }
        }
    }

    public ImageView[][] getArrayOfImages(){
        return arrayOfImages;
    }
}
