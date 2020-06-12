package core;

public class Game {

    public GameBoard gameBoard;
    public Snake snake;
    public EnemySnake enemySnake;
    public Fruit fruit;
    public int status = 0;
    public static int GOAL;

    public Game(GameBoard gameBoard, String difficulty) {
        this.gameBoard = gameBoard;
        snake = new Snake( gameBoard.x/2,  gameBoard.y/2);
        enemySnake = new EnemySnake(0, 0);
        fruit = createNewFruit();
        switch (difficulty) {
            case "Easy":
                GOAL = 10;
                break;
            case "Normal":
                GOAL = 15;
                break;
            case "Hard":
                GOAL = 20;
                break;
        }
    }

    public void step() {
        snake.move(fruit, gameBoard, enemySnake);
        if (enemySnake.isAlive) {
            enemySnake.getNextDirection(fruit, snake);
            enemySnake.checkBorder(gameBoard);
            enemySnake.move(fruit, gameBoard, snake);
        } else {
            enemySnake.deathTimer--;
            if (enemySnake.deathTimer == 0) {
                enemySnake = new EnemySnake(0, 0);
            }
        }
        if (!fruit.isAlive) {
            fruit = createNewFruit();
        }
        checkStatus();
    }

    private Fruit createNewFruit() {
        Fruit newApple = new Fruit(getRandomPosX(), getRandomPosY());
        while (snake.checkCollision(newApple) && enemySnake.checkCollision(newApple)) {
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
        } else if (snake.snakeParts.size() > GOAL) {
            status = 1;
        }
    }
}
