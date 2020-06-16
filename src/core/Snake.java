package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Snake {

    private List<GameObject> snakeParts = new ArrayList<>();
    private Direction currentDirection = Direction.LEFT;
    private Direction nextDirection;
    private boolean isAlive = true;
    private int deathTimer = 0;

    public Snake(int x, int y) {
        GameObject snake1 = new GameObject (x, y);
        GameObject snake2 = new GameObject (x + 1, y);
        GameObject snake3 = new GameObject (x + 2, y);

        snakeParts.add(snake1);
        snakeParts.add(snake2);
        snakeParts.add(snake3);
    }

    public void move(Fruit fruit, GameBoard gameBoard, Snake enemySnake) {
        if (
                currentDirection != Direction.LEFT & nextDirection == Direction.RIGHT ||
                        currentDirection != Direction.RIGHT & nextDirection == Direction.LEFT ||
                        currentDirection != Direction.UP & nextDirection == Direction.DOWN ||
                        currentDirection != Direction.DOWN & nextDirection == Direction.UP
        ) currentDirection = nextDirection;
        GameObject newHead = createNewHead();
        if (newHead.getX() >= gameBoard.getX() || newHead.getX() < 0 || newHead.getY() < 0 || newHead.getY() >= gameBoard.getY()) {
            isAlive = false;
            snakeParts.clear();
            deathTimer = 5;
        } else {
            if (checkCollision(newHead, snakeParts)) {
                isAlive = false;
                snakeParts.clear();
                deathTimer = 5;
            } else {
                    if(checkCollision(newHead, enemySnake.snakeParts)) {
                        isAlive = false;
                        snakeParts.clear();
                        deathTimer = 5;
                        return;
                    }
                snakeParts.add(0, newHead);
                if (newHead.getX() == fruit.getX() & newHead.getY() == fruit.getY()) {
                    fruit.setStatusOfFruit(false);
                } else {
                    removeTail();
                }
            }
        }
    }

    public boolean checkCollision(GameObject newHead, List<GameObject> partsOfSnake) {
        for (GameObject segment: partsOfSnake) {
            if (newHead.getX() == segment.getX() && newHead.getY() == segment.getY()) {
                return true;
            }
        }
        return false;
    }

    public GameObject createNewHead() {
        GameObject head = new GameObject(0, 0);
        switch(currentDirection) {
            case LEFT:
                head = new GameObject(snakeParts.get(0).getX() - 1, snakeParts.get(0).getY());
                break;
            case RIGHT:
                head = new GameObject(snakeParts.get(0).getX() + 1, snakeParts.get(0).getY());
                break;
            case UP:
                head = new GameObject(snakeParts.get(0).getX(), snakeParts.get(0).getY() - 1);
                break;
            case DOWN:
                head = new GameObject(snakeParts.get(0).getX(), snakeParts.get(0).getY() + 1);
                break;
        }
        return head;
    }

    public void removeTail() {
        snakeParts.remove(snakeParts.size() - 1);
    }

    public boolean getStatusOfSnake() {
        return isAlive;
    }

    public void setStatusOfSnake(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public List<GameObject> getSnakeParts() {
        return snakeParts;
    }

    public int getDeathTimer() {
        return deathTimer;
    }

    public void setDeathTimer(int deathTimer) {
        this.deathTimer = deathTimer;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }

    public Direction getNextDirection() {
        return nextDirection;
    }

    public void setNextDirection(Direction nextDirection) {
        this.nextDirection = nextDirection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Snake snake = (Snake) o;
        return isAlive == snake.isAlive &&
                deathTimer == snake.deathTimer &&
                Objects.equals(snakeParts, snake.snakeParts) &&
                currentDirection == snake.currentDirection &&
                nextDirection == snake.nextDirection;
    }

    @Override
    public int hashCode() {
        return Objects.hash(snakeParts, currentDirection, nextDirection, isAlive, deathTimer);
    }

    @Override
    public String toString() {
        return "Snake{" +
                "snakeParts=" + snakeParts +
                ", currentDirection=" + currentDirection +
                ", nextDirection=" + nextDirection +
                ", isAlive=" + isAlive +
                ", deathTimer=" + deathTimer +
                '}';
    }
}
