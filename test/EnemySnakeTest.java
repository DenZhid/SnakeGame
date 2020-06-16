import core.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class EnemySnakeTest {

    @Test
    public void checkBorder() {

        GameBoard gameBoard = new GameBoard("10x10");
        EnemySnake enemySnake = new EnemySnake(0,0);
        enemySnake.checkBorder(gameBoard);
        assertEquals(Direction.DOWN, enemySnake.getNextDirection());

        enemySnake = new EnemySnake(0,9);
        enemySnake.checkBorder(gameBoard);
        assertEquals(Direction.UP, enemySnake.getNextDirection());

        enemySnake = new EnemySnake(5,0);
        enemySnake.setCurrentDirection(Direction.UP);
        enemySnake.checkBorder(gameBoard);
        assertEquals(Direction.LEFT, enemySnake.getNextDirection());
    }

    @Test
    public void getNextDirection() {

        GameBoard gameBoard = new GameBoard("10x10");
        EnemySnake enemySnake = new EnemySnake(5, 5);
        Fruit fruit = new Fruit(3,3);
        Snake snake = new Snake(0,0);

        enemySnake.findNextDirection(fruit, snake);
        assertEquals(Direction.LEFT, enemySnake.getNextDirection());

        enemySnake.move(fruit, gameBoard, snake);
        enemySnake.move(fruit, gameBoard, snake);
        enemySnake.findNextDirection(fruit, snake);
        assertEquals(Direction.UP, enemySnake.getNextDirection());
    }

    @Test
    public void checkNextDirection() {

        EnemySnake enemySnake = new EnemySnake(5,5);
        assertFalse(enemySnake.checkNextDirection(new GameObject(4,5), new Snake(2,5)));

        enemySnake.getSnakeParts().add(new GameObject(5,4));
        assertFalse(enemySnake.checkNextDirection(new GameObject(5,4), new Snake(0,0)));

        assertTrue(enemySnake.checkNextDirection(new GameObject(4,5), new Snake(0,0)));
    }
}