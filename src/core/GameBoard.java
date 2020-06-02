package core;

public class GameBoard {

    private double x;
    private double y;

    public GameBoard(String size) {
        double x = Double.parseDouble(size.split("x")[0]);
        double y = Double.parseDouble(size.split("x")[1]);

        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

}