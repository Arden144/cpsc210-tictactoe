package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RowCountTest {
    @Test
    public void testCheckWinX() {
        RowCount count = new RowCount();
        assertFalse(count.checkWin(Tile.newX()));
        assertFalse(count.checkWin(Tile.newX()));
        assertTrue(count.checkWin(Tile.newX()));
    }

    @Test
    public void testCheckWinO() {
        RowCount count = new RowCount();
        assertFalse(count.checkWin(Tile.newO()));
        assertFalse(count.checkWin(Tile.newO()));
        assertTrue(count.checkWin(Tile.newO()));
    }

    @Test
    public void testCheckWinBlank() {
        RowCount count = new RowCount();
        assertThrows(IllegalArgumentException.class, () -> count.checkWin(Tile.newBlank()));
    }
}
