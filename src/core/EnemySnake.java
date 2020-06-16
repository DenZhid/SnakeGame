package core;

public class EnemySnake extends Snake {

    public EnemySnake(int x, int y) {
        super(x, y);
    }

    public void checkBorder(GameBoard gameBoard) {
        GameObject enemyNewHead = createNewHead();
        switch (getCurrentDirection()) {
            case LEFT:
                if (enemyNewHead.getX() < 0) {
                    if (enemyNewHead.getY() - 1 < 0) {
                        setNextDirection(Direction.DOWN);
                    }
                    else {
                        setNextDirection(Direction.UP);
                    }
                }
                break;
            case RIGHT:
                if (enemyNewHead.getX() > gameBoard.getX() - 1) {
                    if (enemyNewHead.getY() - 1 < 0) {
                        setNextDirection(Direction.DOWN);
                    }
                    else {
                        setNextDirection(Direction.UP);
                    }
                }
                break;
            case UP:
                if (enemyNewHead.getY() < 0) {
                    if (enemyNewHead.getX() - 1 < 0) {
                        setNextDirection(Direction.RIGHT);
                    }
                    else {
                        setNextDirection(Direction.LEFT);
                    }
                }
                break;
            case DOWN:
                if (enemyNewHead.getY() > gameBoard.getY() - 1) {
                    if (enemyNewHead.getX() - 1 < 0) {
                        setNextDirection(Direction.RIGHT);
                    }
                    else {
                        setNextDirection(Direction.LEFT);
                    }
                }
                break;
        }
    }

    public void findNextDirection(Fruit fruit, Snake snake) {
        GameObject enemySnakeHead = getSnakeParts().get(0);
        GameObject newHead = new GameObject(enemySnakeHead.getX() + 1, enemySnakeHead.getY());
        if (enemySnakeHead.getX() < fruit.getX() && getCurrentDirection() != Direction.LEFT) {
            if (checkNextDirection(newHead, snake)) {
                setNextDirection(Direction.RIGHT);
            } else {
                newHead = new GameObject(enemySnakeHead.getX(), enemySnakeHead.getY() + 1);
                if (checkNextDirection(newHead, snake)) {
                    setNextDirection(Direction.DOWN);
                } else {
                    setNextDirection(Direction.UP);
                }
            }
        } else if (enemySnakeHead.getX() > fruit.getX() && getCurrentDirection() != Direction.RIGHT) {
            newHead = new GameObject(enemySnakeHead.getX() - 1, enemySnakeHead.getY());
            if (checkNextDirection(newHead, snake)) {
                setNextDirection(Direction.LEFT);
            } else {
                newHead = new GameObject(enemySnakeHead.getX(), enemySnakeHead.getY() + 1);
                if (checkNextDirection(newHead, snake)) {
                    setNextDirection(Direction.DOWN);
                } else {
                    setNextDirection(Direction.UP);
                }
            }
        } else if (enemySnakeHead.getY() < fruit.getY() && getCurrentDirection() != Direction.UP) {
            newHead = new GameObject(enemySnakeHead.getX(), enemySnakeHead.getY() + 1);
            if (checkNextDirection(newHead, snake)) {
                setNextDirection(Direction.DOWN);
            } else {
                newHead = new GameObject(enemySnakeHead.getX() + 1, enemySnakeHead.getY());
                if (checkNextDirection(newHead, snake)) {
                    setNextDirection(Direction.RIGHT);
                } else {
                    setNextDirection(Direction.LEFT);
                }
            }
        } else if (enemySnakeHead.getY() > fruit.getY() && getCurrentDirection() != Direction.DOWN) {
            newHead = new GameObject(enemySnakeHead.getX(), enemySnakeHead.getY() - 1);
            if (checkNextDirection(newHead, snake)) {
                setNextDirection(Direction.UP);
            } else {
                newHead = new GameObject(enemySnakeHead.getX() + 1, enemySnakeHead.getY());
                if (checkNextDirection(newHead, snake)) {
                    setNextDirection(Direction.RIGHT);
                } else {
                    setNextDirection(Direction.LEFT);
                }
            }
        }
    }

    public boolean checkNextDirection(GameObject newHead, Snake snake) {
        return !checkCollision(newHead, getSnakeParts()) && !checkCollision(newHead, snake.getSnakeParts());
    }
}
