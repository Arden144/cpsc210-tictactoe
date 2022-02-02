package model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardTest {
    private Board board;

    @BeforeEach
    public void createBoard() {
        board = new Board();
    }

    @Test
    public void getChecksOutOfBounds() {
        // x
        assertThrows(IllegalArgumentException.class, () -> board.getPosition(-1, 0));
        assertDoesNotThrow(() -> board.getPosition(0, 0));
        assertDoesNotThrow(() -> board.getPosition(2, 0));
        assertThrows(IllegalArgumentException.class, () -> board.getPosition(3, 0));

        // y
        assertThrows(IllegalArgumentException.class, () -> board.getPosition(0, -1));
        assertDoesNotThrow(() -> board.getPosition(0, 0));
        assertDoesNotThrow(() -> board.getPosition(0, 2));
        assertThrows(IllegalArgumentException.class, () -> board.getPosition(0, 3));
    }

    @Test
    public void setChecksOutOfBounds() {
        // x
        assertThrows(IllegalArgumentException.class, () -> board.setPosition(-1, 0, Tile.BLANK));
        assertDoesNotThrow(() -> board.setPosition(0, 0, Tile.BLANK));
        assertDoesNotThrow(() -> board.setPosition(2, 0, Tile.BLANK));
        assertThrows(IllegalArgumentException.class, () -> board.setPosition(3, 0, Tile.BLANK));

        // y
        assertThrows(IllegalArgumentException.class, () -> board.setPosition(0, -1, Tile.BLANK));
        assertDoesNotThrow(() -> board.setPosition(0, 0, Tile.BLANK));
        assertDoesNotThrow(() -> board.setPosition(0, 2, Tile.BLANK));
        assertThrows(IllegalArgumentException.class, () -> board.setPosition(0, 3, Tile.BLANK));
    }
}
