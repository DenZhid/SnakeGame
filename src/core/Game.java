package core;

public class Game {

    private GameBoard gameBoard;
    private Snake snake;
    private EnemySnake enemySnake;
    private Fruit fruit;
    private int goal;

    public Game(GameBoard gameBoard, Difficulty difficulty) {
        this.gameBoard = gameBoard;
        snake = new Snake( gameBoard.getX()/2,  gameBoard.getY()/2);
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
        int deathTimer = enemySnake.getDeathTimer();
        if (enemySnake.getStatusOfSnake()) {
            enemySnake.findNextDirection(fruit, snake);
            enemySnake.checkBorder(gameBoard);
            enemySnake.move(fruit, gameBoard, snake);
        } else {
            deathTimer--;
            enemySnake.setDeathTimer(deathTimer);
            if (enemySnake.getDeathTimer() == 0) {
                enemySnake = new EnemySnake(0, 0);
            }
        }
        if (!fruit.getStatusOfFruit()) {
            fruit = createNewFruit();
        }
    }

    public Fruit createNewFruit() {
        Fruit newApple = new Fruit(getRandomPosX(), getRandomPosY());
        while (
                snake.checkCollision(newApple, snake.getSnakeParts()) &&
                        enemySnake.checkCollision(newApple, enemySnake.getSnakeParts())
        ) {
            newApple = new Fruit(getRandomPosX(), getRandomPosY());
        }
        return newApple;
    }

    public int getRandomPosX() {
        return (int) (Math.random() * gameBoard.getX());
    }

    public int getRandomPosY() {
        return (int) (Math.random() * gameBoard.getY());
    }

    public Status checkStatus() {
        if (!snake.getStatusOfSnake()) {
            return Status.LOSE;
        } else if (snake.getSnakeParts().size() > goal) {
           return Status.WIN;
        } else return Status.CONTINUE;
    }

    public Snake getSnake() {
        return snake;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public Fruit getFruit() {
        return fruit;
    }

    public void setFruit(Fruit fruit) {
        this.fruit = fruit;
    }

    public EnemySnake getEnemySnake() {
        return enemySnake;
    }

    public void setEnemySnake(EnemySnake enemySnake) {
        this.enemySnake = enemySnake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }
}
