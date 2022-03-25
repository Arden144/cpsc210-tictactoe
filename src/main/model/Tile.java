package model;

import persistence.Codable;

// Represents a Tic Tac Toe tile.
public class Tile extends Codable {
    // Type of the tile.
    public enum Type {
        X, O, Blank
    }

    private final Type type;

    // EFFECTS: Create a new tile
    public Tile(Type type) {
        this.type = type;
    }

    // EFFECTS: Returns a new X tile.
    public static Tile newX() {
        return new Tile(Type.X);
    }

    // EFFECTS: Returns a new O tile.
    public static Tile newO() {
        return new Tile(Type.O);
    }

    // EFFECTS: Returns a new blank tile.
    public static Tile newBlank() {
        return new Tile(Type.Blank);
    }

    // EFFECTS: Return true if the tile is blank.
    public boolean isBlank() {
        return type == Type.Blank;
    }

    // REQUIRES: type != Type.Blank
    // EFFECTS: Return a new tile after this one is placed.
    public Tile nextTile() {
        switch (type) {
            case O:
                return Tile.newX();
            case X:
                return Tile.newO();
            default:
                throw new IllegalStateException("Cannot get a next tile from a blank tile.");
        }
    }

    public Type getType() {
        return type;
    }

    // EFFECTS: Returns a string representation of the tile.
    @Override
    public String toString() {
        switch (type) {
            case Blank:
                return " ";
            case O:
                return "O";
            default:
                return "X";
        }
    }
}
