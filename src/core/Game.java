package core;

public class Game {

    public GameBoard gameBoard;
    public Snake snake;
    public Fruit fruit;
    public int status = 0;
    public static int GOAL = 10;//todo goal variable

    public Game(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        snake = new Snake( gameBoard.x/2,  gameBoard.y/2);
        fruit = createNewFruit();
    }

    public void step() {
        snake.move(fruit, gameBoard);
        if (!fruit.isAlive) {
            //score = score + 5;
            //setScore(score);
            fruit = createNewFruit();
        }
        checkStatus();
    }

    private Fruit createNewFruit() {
        Fruit newApple = new Fruit(getRandomPosX(), getRandomPosY());
        while (snake.checkCollision(newApple)) {
            newApple = new Fruit(getRandomPosX(), getRandomPosY());
        }
        return newApple;
    }

    private int getRandomPosX() {
        return (int) (Math.random() * gameBoard.x);
    }

    private int getRandomPosY() {
        return (int) (Math.random() * gameBoard.y);
    }

    public void checkStatus() {
        if (!snake.isAlive) {
            status = -1;
        }
        if (snake.snakeParts.size() > GOAL) {
            status = 0;
        }
    }
}
