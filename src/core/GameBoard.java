package core;

public class GameBoard {

    public int x;
    public int y;

    public GameBoard(String size) {
        int x = Integer.parseInt(size.split("x")[0]);
        int y = Integer.parseInt(size.split("x")[1]);

        this.x = x;
        this.y = y;
    }
}