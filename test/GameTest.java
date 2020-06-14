import core.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void step() {
    }

    @Test
    public void checkStatus() {
        Game game = new Game(new GameBoard("10x10"), Difficulty.EASY);

        assertEquals(Game.Status.CONTINUE, game.checkStatus());

        for (int i = 3; i < 11; i++) {
            game.snake.snakeParts.add(new GameObject(i, 0));
        }
        assertEquals(Game.Status.WIN, game.checkStatus());

        game.snake.isAlive = false;
        assertEquals(Game.Status.LOSE, game.checkStatus());
    }
}