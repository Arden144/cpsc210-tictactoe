package model;

public class Tile {
    public enum Type {
        X, O, Blank
    }

    private final Type type;

    public Tile(Type type) {
        this.type = type;
    }

    public static Tile newX() {
        return new Tile(Type.X);
    }

    public static Tile newO() {
        return new Tile(Type.O);
    }

    public static Tile newBlank() {
        return new Tile(Type.Blank);
    }

    public boolean isBlank() {
        return type == Type.Blank;
    }

    public Tile nextTile() {
        switch (type) {
            case O:
                return Tile.newX();
            case X:
                return Tile.newO();
            default:
                throw new IllegalStateException("Cannot get a next tile from: " + type);
        }
    }

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
