package model;

import java.util.Objects;

import persistence.Codable;

// Represents a count of consecutive tiles in a row.
public class RowCount extends Codable {
    private int countX;
    private int countO;

    public RowCount(int countX, int countO) {
        this.countX = countX;
        this.countO = countO;
    }

    // EFFECTS: Creates a new row counter
    public RowCount() {
        countX = 0;
        countO = 0;
    }

    // REQUIRES: t.getType() != Tile.Type.Blank
    // MODIFIES: this
    // EFFECTS: Updates the count of the given tile and returns true if the move
    // results in 3 in a row (the win condition).
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RowCount rowCount = (RowCount) o;
        return countX == rowCount.countX && countO == rowCount.countO;
    }

    @Override
    public int hashCode() {
        return Objects.hash(countX, countO);
    }
}
