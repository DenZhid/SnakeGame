package core;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    public List<GameObject> snakeParts = new ArrayList<>();
    public boolean isAlive = true;
    public Direction currentDirection = Direction.LEFT;
    public int deathTimer = 0;

    public Snake(int x, int y) {
        GameObject snake1 = new GameObject (x, y);
        GameObject snake2 = new GameObject (x + 1, y);
        GameObject snake3 = new GameObject (x + 2, y);

        snakeParts.add(snake1);
        snakeParts.add(snake2);
        snakeParts.add(snake3);
    }

    public void setNextDirection(Direction nextDirection) {
        if (
                currentDirection != Direction.LEFT & nextDirection == Direction.RIGHT ||
                        currentDirection != Direction.RIGHT & nextDirection == Direction.LEFT ||
                        currentDirection != Direction.UP & nextDirection == Direction.DOWN ||
                        currentDirection != Direction.DOWN & nextDirection == Direction.UP
        ) {
            switch (currentDirection) {
                case LEFT:
                case RIGHT:
                    if (snakeParts.get(0).x == snakeParts.get(1).x) return;
                    break;
                case UP:
                case DOWN:
                    if (snakeParts.get(0).y == snakeParts.get(1).y) return;
                    break;
            }
            currentDirection = nextDirection;
        }
    }

    public void move(Fruit fruit, GameBoard gameBoard, Snake enemySnake) {
        GameObject newHead = createNewHead();
        if (newHead.x >= gameBoard.x || newHead.x < 0 || newHead.y < 0 || newHead.y >= gameBoard.y) {
            isAlive = false;
            snakeParts.clear();
            deathTimer = 5;
        } else {
            if (checkCollision(newHead)) {
                isAlive = false;
                snakeParts.clear();
                deathTimer = 5;
            } else {
                for (GameObject enemySegment: enemySnake.snakeParts) {
                    if(newHead.x == enemySegment.x & newHead.y == enemySegment.y) {
                        isAlive = false;
                        snakeParts.clear();
                        deathTimer = 5;
                        return;
                    }
                }
                snakeParts.add(0, newHead);
                if (newHead.x == fruit.x & newHead.y == fruit.y) {
                    fruit.isAlive = false;
                } else {
                    removeTail();
                }
            }
        }
    }

    public boolean checkCollision(GameObject newHead) {
        for (GameObject segment: snakeParts) {
            if (newHead.x == segment.x & newHead.y == segment.y) {
                return true;
            }
        }
        return false;
    }

    public GameObject createNewHead() {
        GameObject head = new GameObject(0, 0);
        switch(currentDirection) {
            case LEFT:
                head = new GameObject(snakeParts.get(0).x - 1, snakeParts.get(0).y);
                break;
            case RIGHT:
                head = new GameObject(snakeParts.get(0).x + 1, snakeParts.get(0).y);
                break;
            case UP:
                head = new GameObject(snakeParts.get(0).x, snakeParts.get(0).y - 1);
                break;
            case DOWN:
                head = new GameObject(snakeParts.get(0).x, snakeParts.get(0).y + 1);
                break;
        }
        return head;
    }

    public void removeTail() {
        snakeParts.remove(snakeParts.size() - 1);
    }
}
