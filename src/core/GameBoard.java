package core;

public class GameBoard {

    private int x;
    private int y;

    public GameBoard(String size) {
        int x = Integer.parseInt(size.split("x")[0]);
        int y = Integer.parseInt(size.split("x")[1]);

        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}