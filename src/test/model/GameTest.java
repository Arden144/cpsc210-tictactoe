package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameTest {
    private Game game;

    @BeforeEach
    public void createGame() {
        game = new Game();
    }

    @Test
    public void testConstructor() {
        Stack<Move> moves = new Stack<>();
        Game game = new Game(moves, true);
        assertEquals(moves, game.getMoves());
        assertTrue(game.getEnded());
    }

    @Test
    public void testPlace() {
        assertNull(game.place(1, 2));
        assertNull(game.place(2, 2));
        assertEquals(Tile.X, game.getBoard()[2][1]);
        assertEquals(Tile.O, game.getBoard()[2][2]);
        assertFalse(game.getEnded());
    }

    @Test
    public void testPlaceOverlapping() {
        game.place(2, 1);
        assertEquals(Tile.X, game.getBoard()[1][2]);
        game.place(2, 1);
        assertEquals(Tile.X, game.getBoard()[1][2]);
    }

    @Test
    public void testWinHorizontal() {
        game.place(0, 0);
        game.place(0, 1);
        game.place(1, 0);
        game.place(1, 1);
        String message = game.place(2, 0);
        assertEquals("X wins!", message);
        assertTrue(game.getEnded());
    }

    @Test
    public void testWinFalling() {
        game.place(0, 0);
        game.place(0, 2);
        game.place(1, 1);
        game.place(2, 0);
        String message = game.place(2, 2);
        assertEquals("X wins!", message);
        assertTrue(game.getEnded());
    }

    @Test
    public void testWinRising() {
        game.place(0, 0);
        game.place(0, 2);
        game.place(1, 0);
        game.place(1, 1);
        game.place(2, 2);
        String message = game.place(2, 0);
        assertEquals("O wins!", message);
        assertTrue(game.getEnded());
    }

    @Test
    public void testWinVertical() {
        game.place(0, 0);
        game.place(1, 0);
        game.place(0, 1);
        game.place(1, 1);
        String message = game.place(0, 2);
        assertEquals("X wins!", message);
        assertTrue(game.getEnded());
    }

    @Test
    public void testDraw() {
        game.place(0, 0);
        game.place(2, 2);
        game.place(2, 0);
        game.place(1, 0);
        game.place(0, 2);
        game.place(0, 1);
        game.place(1, 2);
        game.place(1, 1);
        String message = game.place(2, 1);
        assertEquals("Draw!", message);
        assertTrue(game.getEnded());
    }

    @Test
    public void testUndo() {
        game.place(0, 0);
        assertEquals(Tile.X, game.getBoard()[0][0]);
        game.undo();
        assertNull(game.getBoard()[0][0]);
    }
}
