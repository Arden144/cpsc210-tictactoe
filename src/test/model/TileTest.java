package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TileTest {
    @Test
    public void testGetType() {
        Tile tile = Tile.newX();
        assertEquals(Tile.Type.X, tile.getType());
        tile = Tile.newO();
        assertEquals(Tile.Type.O, tile.getType());
        tile = Tile.newBlank();
        assertEquals(Tile.Type.Blank, tile.getType());
    }

    @Test
    public void testisBlank() {
        Tile tile = Tile.newBlank();
        assertTrue(tile.isBlank());
        tile = Tile.newX();
        assertFalse(tile.isBlank());
    }

    @Test
    public void testToString() {
        Tile tile = Tile.newX();
        assertEquals("X", tile.toString());
        tile = Tile.newO();
        assertEquals("O", tile.toString());
        tile = Tile.newBlank();
        assertEquals(" ", tile.toString());
    }

    @Test
    public void testNextTile() {
        Tile tile = Tile.newX();
        assertEquals(Tile.Type.O, tile.nextTile().getType());
        tile = Tile.newO();
        assertEquals(Tile.Type.X, tile.nextTile().getType());
        tile = Tile.newBlank();
        assertThrows(IllegalStateException.class, tile::nextTile);
    }
}
