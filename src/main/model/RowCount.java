package model;

public class RowCount {
    /**
     * Data
     */

    private int countX;
    private int countO;

    /**
     * Public
     */

    public RowCount() {
        countX = 0;
        countO = 0;
    }

    public boolean checkWin(Tile t) {
        switch (t.getType()) {
            case O:
                return ++countO == 3;
            case X:
                return ++countX == 3;
            default:
                throw new IllegalArgumentException("Unexpected tile placed: " + t.getType());
        }
    }
}
