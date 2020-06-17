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
        for (int y = 0; y < game.getGameBoard().getY(); y++) {
            for (int x = 0; x < game.getGameBoard().getX(); x++) {
                ImageView newImage = new ImageView();
                newImage.setImage(mapOfImages.get(ViewSegments.EMPTY));
                newImage.setFitWidth(WIDTH/game.getGameBoard().getX());
                newImage.setFitHeight(HEIGHT/game.getGameBoard().getY());
                arrayOfImages[x][y] = newImage;
            }
        }
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
            drawSnake(forGraphics.getEnemySnake(), ViewSegments.ENEMYSNAKEHEAD, ViewSegments.ENEMYSNAKEBODY);
        }
        if (forGraphics.getSnake().getStatusOfSnake()) {
            drawSnake(forGraphics.getSnake(),ViewSegments.SNAKEHEAD, ViewSegments.SNAKEBODY);
        }
    }

    private void redraw() {
        for (int y = 0; y < forGraphics.getGameBoard().getY(); y++) {
            for (int x = 0; x < forGraphics.getGameBoard().getX(); x++) {
                arrayOfImages[x][y].setImage((mapOfImages.get(ViewSegments.EMPTY)));
            }
        }
    }

    private void drawFruit() {
        arrayOfImages[forGraphics.getFruit().getX()][forGraphics.getFruit().getY()]
                .setImage(mapOfImages.get(ViewSegments.FRUIT));
    }

    private void drawSnake(Snake inputSnake, ViewSegments head, ViewSegments body) {
            arrayOfImages[inputSnake.getSnakeParts().get(0).getX()][inputSnake.getSnakeParts().get(0).getY()]
                    .setImage(mapOfImages.get(head));
            for (int i = 1; i < inputSnake.getSnakeParts().size(); i++) {
                arrayOfImages[inputSnake.getSnakeParts().get(i).getX()][inputSnake.getSnakeParts().get(i).getY()]
                        .setImage(mapOfImages.get(body));
            }
    }

    public ImageView[][] getArrayOfImages(){
        return arrayOfImages;
    }
}
