import core.*;

import org.junit.Test;
import static org.junit.Assert.*;

import org.mockito.Mockito;
import org.mockito.internal.stubbing.answers.Returns;
import org.mockito.internal.stubbing.answers.ReturnsElementsOf;

import java.util.ArrayList;
import java.util.List;

public class GameTest {

    @Test
    public void step() {
        GameBoard gameBoard = new GameBoard("10x10");
        Difficulty difficulty = Difficulty.EASY;
        Game game = Mockito.spy(new Game(gameBoard, difficulty));
        List<Integer> listOfX = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            listOfX.add(5);
        }
        listOfX.add(4);
        for (int i = 0; i < 3; i++) {
            listOfX.add(3);
        }
        List<Integer> listOfY = new ArrayList<>();
        listOfY.add(4);
        listOfY.add(3);
        listOfY.add(2);
        for (int i = 0; i < 3; i++) {
            listOfY.add(1);
        }
        listOfY.add(2);
        listOfY.add(3);
        Mockito.when(game.getRandomPosX()).then(new ReturnsElementsOf(listOfX));
        Mockito.when(game.getRandomPosY()).then(new ReturnsElementsOf(listOfY));

        //Проверка положений после одного шага, продолжения игры, съедания фрукта
        game.setFruit(game.createNewFruit());
        game.setEnemySnake(new EnemySnake(0,9));
        game.getSnake().setNextDirection(Direction.UP);
        game.step();
        Snake testSnake = new Snake(5,4);
        testSnake.getSnakeParts().get(1).setXY(5, 5);
        testSnake.getSnakeParts().get(2).setXY(6,5);
        testSnake.getSnakeParts().add(new GameObject(7,5));
        testSnake.setCurrentDirection(Direction.UP);
        testSnake.setNextDirection(Direction.UP);
        EnemySnake testEnemySnake = new EnemySnake(0,8);
        testEnemySnake.getSnakeParts().get(1).setXY(0,9);
        testEnemySnake.getSnakeParts().get(2).setXY(1,9);
        testEnemySnake.setCurrentDirection(Direction.UP);
        testEnemySnake.setNextDirection(Direction.UP);

        assertEquals(testSnake, game.getSnake());
        assertEquals(testEnemySnake, game.getEnemySnake());
        assertTrue(game.getFruit().getStatusOfFruit());
        assertEquals(new Fruit(5,3), game.getFruit());

        //Игра оканчивается победой
        game.step();
        game.step();
        game.step();
        game.getSnake().setNextDirection(Direction.LEFT);
        game.step();
        game.step();
        game.getSnake().setNextDirection(Direction.DOWN);
        game.step();
        game.step();
        testSnake.getSnakeParts().get(0).setXY(3,3);
        testSnake.getSnakeParts().get(1).setXY(3,2);
        testSnake.getSnakeParts().get(2).setXY(3,1);
        testSnake.getSnakeParts().get(3).setXY(4,1);
        testSnake.getSnakeParts().add(new GameObject(5,1));
        testSnake.getSnakeParts().add(new GameObject(5,2));
        testSnake.getSnakeParts().add(new GameObject(5,3));
        testSnake.getSnakeParts().add(new GameObject(5,4));
        testSnake.getSnakeParts().add(new GameObject(5,5));
        testSnake.getSnakeParts().add(new GameObject(6,5));
        testSnake.getSnakeParts().add(new GameObject(7,5));
        testSnake.setCurrentDirection(Direction.DOWN);
        testSnake.setNextDirection(Direction.DOWN);
        testEnemySnake.getSnakeParts().get(0).setXY(3,6);
        testEnemySnake.getSnakeParts().get(1).setXY(3,7);
        testEnemySnake.getSnakeParts().get(2).setXY(4,7);
        testEnemySnake.setCurrentDirection(Direction.UP);
        testEnemySnake.setNextDirection(Direction.UP);

        assertEquals(testSnake, game.getSnake());
        assertEquals(testEnemySnake, game.getEnemySnake());
        assertEquals(Status.WIN, game.checkStatus());

        //Проверка проигрыша и столкновение с границей
        game = Mockito.spy(new Game(gameBoard, difficulty));
        game.step();
        game.step();
        game.step();
        game.step();
        game.step();
        game.step();
        assertEquals(Status.LOSE, game.checkStatus());

        //Проверка съедания фрукта вражеской змейкой и столкновения змейки игрока с вражеской
        game = Mockito.spy(new Game(gameBoard, difficulty));
        Mockito.when(game.getRandomPosX()).then(new Returns(0));
        Mockito.when(game.getRandomPosY()).then(new Returns(1));
        game.setFruit(game.createNewFruit());
        game.setEnemySnake(new EnemySnake(1,1));
        game.setSnake(new Snake(2,2));
        game.getSnake().setNextDirection(Direction.UP);
        game.step();
        testEnemySnake = new EnemySnake(0,1);
        testEnemySnake.getSnakeParts().add(new GameObject(3,1));
        testEnemySnake.setNextDirection(Direction.LEFT);
        testEnemySnake.setCurrentDirection(Direction.LEFT);

        assertEquals(testEnemySnake, game.getEnemySnake());
        assertFalse(game.getSnake().getStatusOfSnake());
        assertEquals(Status.LOSE, game.checkStatus());

        //Проверка столкновения вражеской змейки со змейкой игрока
        game.setEnemySnake(new EnemySnake(0,0));
        game.setSnake(new Snake(0,1));
        game.getSnake().setNextDirection(Direction.DOWN);
        game.step();

        assertFalse(game.getEnemySnake().getStatusOfSnake());
    }

    @Test
    public void checkStatus() {
        Game game = new Game(new GameBoard("10x10"), Difficulty.EASY);

        assertEquals(Status.CONTINUE, game.checkStatus());

        for (int i = 3; i < 11; i++) {
            game.getSnake().getSnakeParts().add(new GameObject(i, 0));
        }
        assertEquals(Status.WIN, game.checkStatus());

        game.getSnake().setStatusOfSnake(false);
        assertEquals(Status.LOSE, game.checkStatus());
    }
}