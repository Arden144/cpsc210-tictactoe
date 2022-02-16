package model;

public class Tile {
    public enum Type {
        X, O, Blank
    }

    /**
     * Data
     */

    private Type type;

    /**
     * Public
     */

    public Tile(Type type) {
        this.type = type;
    }

    public static Tile newX() {
        return new Tile(Type.X);
    }

    public static Tile newY() {
        return new Tile(Type.O);
    }

    public static Tile newBlank() {
        return new Tile(Type.Blank);
    }

    /**
     * Getters/Setters
     */

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        switch (type) {
            case Blank:
                return " ";
            case O:
                return "O";
            case X:
                return "X";
            default:
                throw new IllegalStateException("Unexpected tile state: " + type);
        }
    }
}
