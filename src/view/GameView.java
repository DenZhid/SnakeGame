package view;

import core.Game;
import core.Snake;

import static controller.ControllerOfStartScreen.WIDTH;
import static controller.ControllerOfStartScreen.HEIGHT;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.util.EnumMap;

public class GameView {

    private ImageView[][] arrayOfImages;
    private EnumMap<ViewSegments, Image> mapOfImages;
    private final Game forGraphics;

    private enum ViewSegments {
        EMPTY,
        SNAKEHEAD,
        SNAKEBODY,
        FRUIT,
        ENEMYSNAKEHEAD,
        ENEMYSNAKEBODY
    }

    public GameView(Game game) throws FileNotFoundException {
        this.forGraphics = game;
        arrayOfImages = new ImageView[game.getGameBoard().getX()][game.getGameBoard().getY()];

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
        for (int y = 0; y < forGraphics.getGameBoard().getY(); y++) {
            for (int x = 0; x < forGraphics.getGameBoard().getX(); x++) {
                ImageView newImage = new ImageView();
                newImage.setImage(mapOfImages.get(ViewSegments.EMPTY));
                newImage.setFitWidth(WIDTH/forGraphics.getGameBoard().getX());
                newImage.setFitHeight(HEIGHT/forGraphics.getGameBoard().getY());
                arrayOfImages[x][y] = newImage;
            }
        }
    }

    private void drawFruit() {
        arrayOfImages[forGraphics.getFruit().getX()][forGraphics.getFruit().getY()]
                .setImage(mapOfImages.get(ViewSegments.FRUIT));
        arrayOfImages[forGraphics.getFruit().getX()][forGraphics.getFruit().getY()]
                .setFitWidth(WIDTH/forGraphics.getGameBoard().getX());
        arrayOfImages[forGraphics.getFruit().getX()][forGraphics.getFruit().getY()]
                .setFitHeight(HEIGHT/forGraphics.getGameBoard().getY());
    }

    private void drawSnake(Snake inputSnake) {
        if (inputSnake == forGraphics.getSnake()) {
            arrayOfImages[inputSnake.getSnakeParts().get(0).getX()][inputSnake.getSnakeParts().get(0).getY()]
                    .setImage(mapOfImages.get(ViewSegments.SNAKEHEAD));
            arrayOfImages[inputSnake.getSnakeParts().get(0).getX()][inputSnake.getSnakeParts().get(0).getY()]
                    .setFitWidth(WIDTH/forGraphics.getGameBoard().getX());
            arrayOfImages[inputSnake.getSnakeParts().get(0).getX()][inputSnake.getSnakeParts().get(0).getY()]
                    .setFitHeight(HEIGHT/forGraphics.getGameBoard().getY());
            for (int i = 1; i < inputSnake.getSnakeParts().size(); i++) {
                arrayOfImages[inputSnake.getSnakeParts().get(i).getX()][inputSnake.getSnakeParts().get(i).getY()]
                        .setImage(mapOfImages.get(ViewSegments.SNAKEBODY));
                arrayOfImages[inputSnake.getSnakeParts().get(i).getX()][inputSnake.getSnakeParts().get(i).getY()]
                        .setFitWidth(WIDTH/forGraphics.getGameBoard().getX());
                arrayOfImages[inputSnake.getSnakeParts().get(i).getX()][inputSnake.getSnakeParts().get(i).getY()]
                        .setFitHeight(HEIGHT/forGraphics.getGameBoard().getY());
            }
        } else {
            arrayOfImages[inputSnake.getSnakeParts().get(0).getX()][inputSnake.getSnakeParts().get(0).getY()]
                    .setImage(mapOfImages.get(ViewSegments.ENEMYSNAKEHEAD));
            arrayOfImages[inputSnake.getSnakeParts().get(0).getX()][inputSnake.getSnakeParts().get(0).getY()]
                    .setFitWidth(WIDTH / forGraphics.getGameBoard().getX());
            arrayOfImages[inputSnake.getSnakeParts().get(0).getX()][inputSnake.getSnakeParts().get(0).getY()]
                    .setFitHeight(HEIGHT / forGraphics.getGameBoard().getY());
            for (int i = 1; i < inputSnake.getSnakeParts().size(); i++) {
                arrayOfImages[inputSnake.getSnakeParts().get(i).getX()][inputSnake.getSnakeParts().get(i).getY()]
                        .setImage(mapOfImages.get(ViewSegments.ENEMYSNAKEBODY));
                arrayOfImages[inputSnake.getSnakeParts().get(i).getX()][inputSnake.getSnakeParts().get(i).getY()]
                        .setFitWidth(WIDTH / forGraphics.getGameBoard().getX());
                arrayOfImages[inputSnake.getSnakeParts().get(i).getX()][inputSnake.getSnakeParts().get(i).getY()]
                        .setFitHeight(HEIGHT / forGraphics.getGameBoard().getY());
            }
        }
    }

    public ImageView[][] getArrayOfImages(){
        return arrayOfImages;
    }
}
