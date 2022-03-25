package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameTest {
    private Game game;

    @BeforeEach
    public void createGame() {
        game = new Game();
    }

    @Test
    public void testGetBoard() {
        assertNotNull(game.getBoard());
    }

    @Test
    public void testGetState() {
        assertEquals(Game.State.Play, game.getState());
    }

    @Test
    public void testPlace() {
        game.place(0, 0);
        assertEquals(Game.State.Play, game.getState());
    }

    @Test
    public void testPlaceWin() {
        game.place(0, 0);
        game.place(1, 0);
        game.place(0, 1);
        game.place(1, 1);
        game.place(0, 2);
        assertEquals(Game.State.Win, game.getState());
        assertEquals("X", game.getWinner());
    }

    @Test
    public void testPlaceDraw() {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                game.place(column, row);
            }
        }
        assertEquals(Game.State.Draw, game.getState());
    }
}
