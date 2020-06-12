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
        boolean checked = true;
        if (enemySnakeHead.x < fruit.x & currentDirection != Direction.LEFT) {
            for (GameObject enemySnakeSegment: snakeParts) {
                if (enemySnakeHead.x + 1 == enemySnakeSegment.x & enemySnakeHead.y == enemySnakeSegment.y) {
                    checked = false;
                    break;
                }
            }
            if (checked) {
                for (GameObject snakeSegment: snake.snakeParts) {
                    if (enemySnakeHead.x + 1 == snakeSegment.x & enemySnakeHead.y == snakeSegment.y) {
                        checked = false;
                        break;
                    }
                }
            }
            if (checked) {
                setNextDirection(Direction.RIGHT);
            } else {
                checked = true;
                for (GameObject enemySnakeSegment: snakeParts) {
                    if (enemySnakeHead.x == enemySnakeSegment.x & enemySnakeHead.y + 1 == enemySnakeSegment.y) {
                        checked = false;
                        break;
                    }
                }
                if (checked) {
                    for (GameObject snakeSegment: snake.snakeParts) {
                        if (enemySnakeHead.x == snakeSegment.x & enemySnakeHead.y + 1 == snakeSegment.y) {
                            checked = false;
                            break;
                        }
                    }
                }
                if (checked) {
                    setNextDirection(Direction.DOWN);
                } else
                {
                    setNextDirection(Direction.UP);
                }
            }
        } else
            if (enemySnakeHead.x > fruit.x & currentDirection != Direction.RIGHT) {
            for (GameObject enemySnakeSegment: snakeParts) {
                if (enemySnakeHead.x - 1 == enemySnakeSegment.x & enemySnakeHead.y == enemySnakeSegment.y) {
                    checked = false;
                    break;
                }
            }
            if (checked) {
                for (GameObject snakeSegment: snake.snakeParts) {
                    if (enemySnakeHead.x - 1 == snakeSegment.x & enemySnakeHead.y == snakeSegment.y) {
                        checked = false;
                        break;
                    }
                }
            }
            if (checked) {
                setNextDirection(Direction.LEFT);
            } else  {
                checked = true;
                for (GameObject enemySnakeSegment: snakeParts) {
                    if (enemySnakeHead.x == enemySnakeSegment.x & enemySnakeHead.y + 1 == enemySnakeSegment.y) {
                        checked = false;
                        break;
                    }
                }
                if (checked) {
                    for (GameObject snakeSegment: snake.snakeParts) {
                        if (enemySnakeHead.x == snakeSegment.x & enemySnakeHead.y + 1 == snakeSegment.y) {
                            checked = false;
                            break;
                        }
                    }
                }
                if (checked) {
                    setNextDirection(Direction.DOWN);
                } else
                {
                    setNextDirection(Direction.UP);
                }
            }
        } else
            if (enemySnakeHead.y < fruit.y & currentDirection != Direction.UP) {
            for (GameObject enemySnakeSegment: snakeParts) {
                if (enemySnakeHead.x == enemySnakeSegment.x & enemySnakeHead.y + 1 == enemySnakeSegment.y) {
                    checked = false;
                    break;
                }
            }
            if (checked) {
                for (GameObject snakeSegment: snake.snakeParts) {
                    if (enemySnakeHead.x == snakeSegment.x & enemySnakeHead.y + 1 == snakeSegment.y) {
                        checked = false;
                        break;
                    }
                }
            }
            if (checked) {
                setNextDirection(Direction.DOWN);
            } else {
                checked = true;
                for (GameObject enemySnakeSegment: snakeParts) {
                    if (enemySnakeHead.x + 1 == enemySnakeSegment.x & enemySnakeHead.y == enemySnakeSegment.y) {
                        checked = false;
                        break;
                    }
                }
                if (checked) {
                    for (GameObject snakeSegment: snake.snakeParts) {
                        if (enemySnakeHead.x + 1 == snakeSegment.x & enemySnakeHead.y == snakeSegment.y) {
                            checked = false;
                            break;
                        }
                    }
                }
                if (checked) {
                    setNextDirection(Direction.RIGHT);
                } else
                {
                    setNextDirection(Direction.LEFT);
                }
            }
        } else
            if (enemySnakeHead.y > fruit.y & currentDirection != Direction.DOWN) {
                for (GameObject enemySnakeSegment: snakeParts) {
                    if (enemySnakeHead.x == enemySnakeSegment.x & enemySnakeHead.y - 1 == enemySnakeSegment.y) {
                        checked = false;
                        break;
                    }
                }
                if (checked) {
                    for (GameObject snakeSegment: snake.snakeParts) {
                        if (enemySnakeHead.x == snakeSegment.x & enemySnakeHead.y - 1 == snakeSegment.y) {
                            checked = false;
                            break;
                        }
                    }
                }
                if (checked) {
                    setNextDirection(Direction.UP);
                } else {
                    checked = true;
                    for (GameObject enemySnakeSegment: snakeParts) {
                        if (enemySnakeHead.x + 1 == enemySnakeSegment.x & enemySnakeHead.y == enemySnakeSegment.y) {
                            checked = false;
                            break;
                        }
                    }
                    if (checked) {
                        for (GameObject snakeSegment: snake.snakeParts) {
                            if (enemySnakeHead.x + 1 == snakeSegment.x & enemySnakeHead.y == snakeSegment.y) {
                                checked = false;
                                break;
                            }
                        }
                    }
                    if (checked) {
                        setNextDirection(Direction.RIGHT);
                    } else
                    {
                        setNextDirection(Direction.LEFT);
                    }
                }
        }
    }
}
