import core.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class SnakeTest {

    @Test
    public void setNextDirection() {
        Snake snake = new Snake(0,0);

        snake.setNextDirection(Direction.LEFT);
        assertEquals(Direction.LEFT, snake.getNextDirection());

        snake.setNextDirection(Direction.RIGHT);
        assertEquals(Direction.RIGHT, snake.getNextDirection());

        snake.setNextDirection(Direction.UP);
        assertEquals(Direction.UP, snake.getNextDirection());

        snake.setNextDirection(Direction.DOWN);
        assertEquals(Direction.DOWN, snake.getNextDirection());
    }

    @Test
    public void move() {
        Snake snake = new Snake(0,0);
        Snake enemySnake = new Snake(0, 10);
        Fruit fruit = new Fruit(1,1);
        GameBoard gameBoard = new GameBoard("10x10");
        snake.setNextDirection(Direction.RIGHT);


        snake.move(fruit, gameBoard, enemySnake);
        assertEquals(Direction.LEFT, snake.getCurrentDirection());
        assertEquals(0, snake.getSnakeParts().size());
        assertFalse(snake.getStatusOfSnake());

        snake = new Snake(5,5);
        snake.setNextDirection(Direction.UP);
        fruit = new Fruit(5, 4);
        snake.move(fruit, gameBoard, enemySnake);
        assertEquals(Direction.UP, snake.getCurrentDirection());
        assertEquals(4, snake.getSnakeParts().size());
        assertTrue(snake.getStatusOfSnake());
        assertFalse(fruit.getStatusOfFruit());

        snake = new Snake(5,5);
        snake.getSnakeParts().add(new GameObject(5,6));
        snake.setCurrentDirection(Direction.DOWN);
        snake.setNextDirection(Direction.UP);
        snake.move(fruit, gameBoard, enemySnake);
        assertEquals(Direction.DOWN, snake.getCurrentDirection());
        assertEquals(0, snake.getSnakeParts().size());
        assertEquals(5, snake.getDeathTimer());
        assertFalse(snake.getStatusOfSnake());

        snake = new Snake(5, 5);
        enemySnake = new Snake(2,5);
        snake.setCurrentDirection(Direction.LEFT);
        snake.setNextDirection(Direction.LEFT);
        enemySnake.setCurrentDirection(Direction.UP);
        enemySnake.setNextDirection(Direction.UP);
        snake.move(fruit, gameBoard, enemySnake);
        assertEquals(Direction.LEFT, snake.getCurrentDirection());
        assertEquals(0, snake.getSnakeParts().size());
        assertEquals(5, snake.getDeathTimer());
        assertFalse(snake.getStatusOfSnake());
        assertTrue(enemySnake.getStatusOfSnake());
    }

    @Test
    public void checkCollision() {
        Snake snake = new Snake(0,0);

        GameObject newHead = new GameObject(1,0);
        assertTrue(snake.checkCollision(newHead, snake.getSnakeParts()));

        newHead = new GameObject(3, 0);
        assertFalse(snake.checkCollision(newHead, snake.getSnakeParts()));
    }

    @Test
    public void createNewHead() {
        Snake snake = new Snake(5, 5);

        snake.setCurrentDirection(Direction.LEFT);
        assertEquals(new GameObject(4,5), snake.createNewHead());

        snake.setCurrentDirection(Direction.RIGHT);
        assertEquals(new GameObject(6, 5), snake.createNewHead());

        snake.setCurrentDirection(Direction.UP);
        assertEquals(new GameObject(5, 4), snake.createNewHead());

        snake.setCurrentDirection(Direction.DOWN);
        assertEquals(new GameObject(5, 6), snake.createNewHead());
    }

    @Test
    public void removeTail() {
        Snake snake = new Snake(0, 0);

        snake.removeTail();
        assertEquals(2, snake.getSnakeParts().size());
        assertEquals(new GameObject(0, 0), snake.getSnakeParts().get(0));
        assertEquals(new GameObject(1, 0), snake.getSnakeParts().get(1));
    }
}