package model;

public enum Tile {
    BLANK, X, O;

    public static String asString(Tile tile) {
        switch (tile) {
            case BLANK:
                return " ";
            case O:
                return "O";
            case X:
                return "X";
            default:
                return " ";
        }
    }
}
