package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class BoardTest {
    @Test
    public void placeOnExistingTile() {
        Board board = new Board();
        assertTrue(board.place(0, 0, Tile.newX()));
        assertFalse(board.place(0, 0, Tile.newO()));
    }

    @Test
    public void detectsDraw() {
        Board board = new Board();
        assertFalse(board.isDraw());
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                board.place(column, row, Tile.newX());
            }
        }
        assertTrue(board.isDraw());
    }

    @Test
    public void winRow() {
        Board board = new Board();
        assertFalse(board.isWin());
        board.place(0, 0, Tile.newX());
        board.place(1, 0, Tile.newX());
        board.place(2, 0, Tile.newX());
        assertTrue(board.isWin());
    }

    @Test
    public void winColumn() {
        Board board = new Board();
        assertFalse(board.isWin());
        board.place(0, 0, Tile.newX());
        board.place(0, 1, Tile.newX());
        board.place(0, 2, Tile.newX());
        assertTrue(board.isWin());
    }

    @Test
    public void winFallingDiagonal() {
        Board board = new Board();
        assertFalse(board.isWin());
        board.place(0, 0, Tile.newX());
        board.place(1, 1, Tile.newX());
        board.place(2, 2, Tile.newX());
        assertTrue(board.isWin());
    }

    @Test
    public void winRisingDiagonal() {
        Board board = new Board();
        assertFalse(board.isWin());
        board.place(0, 2, Tile.newX());
        board.place(1, 1, Tile.newX());
        board.place(2, 0, Tile.newX());
        assertTrue(board.isWin());
    }

    @Test
    public void placesTile() {
        Board board = new Board();
        Tile tile = Tile.newX();
        board.place(1, 1, tile);
        assertEquals(tile, board.getText(1, 1));
    }
}
