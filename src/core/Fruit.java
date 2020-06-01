package core;

import javafx.scene.layout.Pane;

import static controller.GameController.pane;

public class Fruit extends GameObject {

    public boolean isAlive = true;


    public Fruit(double x, double y) {
        super(x, y);
    }

    public static void draw(Pane pane) {
        //pane.getChildren().add(image);
    }
}