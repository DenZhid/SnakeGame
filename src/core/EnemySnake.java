package core;

public class EnemySnake extends Snake {

    public EnemySnake(int x, int y) {
        super(x, y);
    }

    public void checkBorder(GameBoard gameBoard) {
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

    public void getNextDirection(Fruit fruit, Snake snake) {
        GameObject enemySnakeHead = snakeParts.get(0);
        GameObject newHead = new GameObject(enemySnakeHead.x++, enemySnakeHead.y);
        if (enemySnakeHead.x < fruit.x && currentDirection != Direction.LEFT) {
            if (checkNextDirection(newHead, snake)) {
                setNextDirection(Direction.RIGHT);
            } else {
                newHead = new GameObject(enemySnakeHead.x, enemySnakeHead.y++);
                if (checkNextDirection(newHead, snake)) {
                    setNextDirection(Direction.DOWN);
                } else {
                    setNextDirection(Direction.UP);
                }
            }
        } else if (enemySnakeHead.x > fruit.x && currentDirection != Direction.RIGHT) {
            newHead = new GameObject(enemySnakeHead.x--, enemySnakeHead.y);
            if (checkNextDirection(newHead, snake)) {
                setNextDirection(Direction.LEFT);
            } else {
                newHead = new GameObject(enemySnakeHead.x, enemySnakeHead.y++);
                if (checkNextDirection(newHead, snake)) {
                    setNextDirection(Direction.DOWN);
                } else {
                    setNextDirection(Direction.UP);
                }
            }
        } else if (enemySnakeHead.y < fruit.y && currentDirection != Direction.UP) {
            newHead = new GameObject(enemySnakeHead.x, enemySnakeHead.y++);
            if (checkNextDirection(newHead, snake)) {
                setNextDirection(Direction.DOWN);
            } else {
                newHead = new GameObject(enemySnakeHead.x++, enemySnakeHead.y);
                if (checkNextDirection(newHead, snake)) {
                    setNextDirection(Direction.RIGHT);
                } else {
                    setNextDirection(Direction.LEFT);
                }
            }
        } else if (enemySnakeHead.y > fruit.y && currentDirection != Direction.DOWN) {
               newHead = new GameObject(enemySnakeHead.x, enemySnakeHead.y--);
                if (checkNextDirection(newHead, snake)) {
                    setNextDirection(Direction.UP);
                } else {
                    newHead = new GameObject(enemySnakeHead.x++, enemySnakeHead.y);
                    if (checkNextDirection(newHead, snake)) {
                        setNextDirection(Direction.RIGHT);
                    } else {
                        setNextDirection(Direction.LEFT);
                    }
                }
        }
    }

    public boolean checkNextDirection(GameObject newHead, Snake snake) {
        return !checkCollision(newHead, snakeParts) && !checkCollision(newHead, snake.snakeParts);
    }
}
