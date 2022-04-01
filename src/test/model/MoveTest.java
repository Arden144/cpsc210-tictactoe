package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MoveTest {
    @Test
    public void testConstructor() {
        Move move = new Move(2, 1, Tile.O);
        assertEquals(2, move.getPosX());
        assertEquals(1, move.getPosY());
        assertEquals(Tile.O, move.getTile());
    }
}
