package core;

public class EnemySnake extends Snake {

    public EnemySnake(int x, int y) {
        super(x, y);
    }

    public void checkNextStep(GameBoard gameBoard) {
        GameObject enemyNewHead = createNewHead();
        switch (currentDirection) {
            case LEFT:
                if (enemyNewHead.x < 0) {
                    if (enemyNewHead.y - 1 < 0) {
                        setNextDirection(Direction.DOWN);
                    }
                    else {
                        setNextDirection(Direction.UP);
                    }
                }
                break;
            case RIGHT:
                if (enemyNewHead.x > gameBoard.x - 1) {
                    if (enemyNewHead.y - 1 < 0) {
                        setNextDirection(Direction.DOWN);
                    }
                    else {
                        setNextDirection(Direction.UP);
                    }
                }
                break;
            case UP:
                if (enemyNewHead.y < 0) {
                    if (enemyNewHead.x - 1 < 0) {
                        setNextDirection(Direction.RIGHT);
                    }
                    else {
                        setNextDirection(Direction.LEFT);
                    }
                }
                break;
            case DOWN:
                if (enemyNewHead.y > gameBoard.y - 1) {
                    if (enemyNewHead.x - 1 < 0) {
                        setNextDirection(Direction.RIGHT);
                    }
                    else {
                        setNextDirection(Direction.LEFT);
                    }
                }
                break;
        }
    }

    public void getRandomDirection(GameBoard gameBoard) {
        GameObject enemySnakeHead = snakeParts.get(0);
        if(randomNum(1) == 1) {
            switch (randomNum(3)) {
                case 0:
                    if (enemySnakeHead.x - 1 > 0) {
                        setNextDirection(Direction.LEFT);
                    }
                    else {
                        getRandomDirection(gameBoard);
                    }
                    break;
                case 1:
                    if (enemySnakeHead.x + 1 < gameBoard.x - 1) {
                        setNextDirection(Direction.RIGHT);
                    }
                    else {
                        getRandomDirection(gameBoard);
                    }
                    break;
                case 2:
                    if (enemySnakeHead.y - 1 > 0) {
                        setNextDirection(Direction.UP);
                    }
                    else {
                        getRandomDirection(gameBoard);
                    }
                    break;
                case 3:
                    if (enemySnakeHead.y + 1 < gameBoard.y - 1) {
                        setNextDirection(Direction.DOWN);
                    }
                    else {
                        getRandomDirection(gameBoard);
                    }
                    break;
            }
        }
    }

    private int randomNum(int max) {
        return (int) (Math.random() * ++max);
    }

}
