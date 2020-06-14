package core;

public class Fruit extends GameObject {

    private boolean isAlive = true;

    public Fruit(int x, int y) {
        super(x, y);
    }

    public boolean getStatusOfFruit() {
        return isAlive;
    }

    public void setStatusOfFruit(boolean isAlive) {
        this.isAlive = isAlive;
    }
}