package model;

// Represents a player's move in the game.
public class Move {
    private final int posX;
    private final int posY;
    private final Tile tile;

    // REQUIRES: 0 <= posX <= 2, 0 <= posY <= 2
    // EFFECTS: Creates a new move.
    public Move(int posX, int posY, Tile tile) {
        this.posX = posX;
        this.posY = posY;
        this.tile = tile;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public Tile getTile() {
        return tile;
    }
}
