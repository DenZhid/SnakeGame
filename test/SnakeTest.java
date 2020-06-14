import core.*;
import org.ietf.jgss.GSSManager;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SnakeTest {

    @Test
    public void setNextDirection() {
        Snake snake = new Snake(0,0);

        snake.setNextDirection(Direction.LEFT);
        assertEquals(Direction.LEFT, snake.nextDirection);

        snake.setNextDirection(Direction.RIGHT);
        assertEquals(Direction.RIGHT, snake.nextDirection);

        snake.setNextDirection(Direction.UP);
        assertEquals(Direction.UP, snake.nextDirection);

        snake.setNextDirection(Direction.DOWN);
        assertEquals(Direction.DOWN, snake.nextDirection);
    }

    @Test
    public void move() {
        Snake snake = new Snake(0,0);
        Snake enemySnake = new Snake(0, 10);
        Fruit fruit = new Fruit(1,1);
        GameBoard gameBoard = new GameBoard("10x10");
        snake.nextDirection = Direction.RIGHT;


        snake.move(fruit, gameBoard, enemySnake);
        assertEquals(Direction.LEFT, snake.currentDirection);
        assertEquals(0, snake.snakeParts.size());
        assertFalse(snake.isAlive);

        snake = new Snake(5,5);
        snake.nextDirection = Direction.UP;
        fruit = new Fruit(5, 4);
        snake.move(fruit, gameBoard, enemySnake);
        assertEquals(Direction.UP, snake.currentDirection);
        assertEquals(4, snake.snakeParts.size());
        assertTrue(snake.isAlive);
        assertFalse(fruit.isAlive);

        snake = new Snake(5,5);
        snake.snakeParts.add(new GameObject(5,6));
        snake.currentDirection = Direction.DOWN;
        snake.nextDirection = Direction.UP;
        snake.move(fruit, gameBoard, enemySnake);
        assertEquals(Direction.DOWN, snake.currentDirection);
        assertEquals(0, snake.snakeParts.size());
        assertEquals(5, snake.deathTimer);
        assertFalse(snake.isAlive);

        snake = new Snake(5, 5);
        enemySnake = new Snake(2,5);
        snake.currentDirection = Direction.LEFT;
        snake.nextDirection = Direction.LEFT;
        enemySnake.currentDirection = Direction.UP;
        enemySnake.nextDirection = Direction.UP;
        snake.move(fruit, gameBoard, enemySnake);
        assertEquals(Direction.LEFT, snake.currentDirection);
        assertEquals(0, snake.snakeParts.size());
        assertEquals(5, snake.deathTimer);
        assertFalse(snake.isAlive);
        assertTrue(enemySnake.isAlive);
    }

    @Test
    public void checkCollision() {
        Snake snake = new Snake(0,0);

        GameObject newHead = new GameObject(1,0);
        assertTrue(snake.checkCollision(newHead, snake.snakeParts));

        newHead = new GameObject(3, 0);
        assertFalse(snake.checkCollision(newHead, snake.snakeParts));
    }

    @Test
    public void createNewHead() {
        Snake snake = new Snake(5, 5);

        snake.currentDirection = Direction.LEFT;
        assertEquals(new GameObject(4,5), snake.createNewHead());

        snake.currentDirection = Direction.RIGHT;
        assertEquals(new GameObject(6, 5), snake.createNewHead());

        snake.currentDirection = Direction.UP;
        assertEquals(new GameObject(5, 4), snake.createNewHead());

        snake.currentDirection = Direction.DOWN;
        assertEquals(new GameObject(5, 6), snake.createNewHead());
    }

    @Test
    public void removeTail() {
        Snake snake = new Snake(0, 0);

        snake.removeTail();
        assertEquals(2, snake.snakeParts.size());
        assertEquals(new GameObject(0, 0), snake.snakeParts.get(0));
        assertEquals(new GameObject(1, 0), snake.snakeParts.get(1));
    }
}