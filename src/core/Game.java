package core;

public class Game {

    public GameBoard gameBoard;
    public Snake snake;
    public EnemySnake enemySnake;
    public Fruit fruit;
    public int goal;

    public enum Status {
        LOSE,
        WIN,
        CONTINUE
    }

    public Game(GameBoard gameBoard, Difficulty difficulty) {
        this.gameBoard = gameBoard;
        snake = new Snake( gameBoard.x/2,  gameBoard.y/2);
        enemySnake = new EnemySnake(0, 0);
        fruit = createNewFruit();
        switch (difficulty) {
            case EASY:
                goal = 10;
                break;
            case NORMAL:
                goal = 15;
                break;
            case HARD:
                goal = 20;
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
    }

    private Fruit createNewFruit() {
        Fruit newApple = new Fruit(getRandomPosX(), getRandomPosY());
        while (snake.checkCollision(newApple, snake.snakeParts) && enemySnake.checkCollision(newApple, enemySnake.snakeParts)) {
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

    public Status checkStatus() {
        if (!snake.isAlive) {
            return Status.LOSE;
        } else if (snake.snakeParts.size() > goal) {
           return Status.WIN;
        } else return Status.CONTINUE;
    }
}
