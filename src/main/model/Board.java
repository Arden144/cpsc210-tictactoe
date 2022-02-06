package model;

import java.io.Serializable;
import java.util.Arrays;

public class Board implements Serializable {
    private Count[] rows, cols, diags;
    private Tile[][] board;

    public Board() {
        board = new Tile[][] {
                { Tile.BLANK, Tile.BLANK, Tile.BLANK },
                { Tile.BLANK, Tile.BLANK, Tile.BLANK },
                { Tile.BLANK, Tile.BLANK, Tile.BLANK }
        };
        rows = new Count[] { new Count(), new Count(), new Count() };
        cols = new Count[] { new Count(), new Count(), new Count() };
        diags = new Count[] { new Count(), new Count() };
    }

    protected Tile getPosition(int x, int y) throws IllegalArgumentException {
        if (x < 0 || x >= 3 || y < 0 || y >= 3) {
            throw new IllegalArgumentException();
        }

        return board[y][x];
    }

    protected void setPosition(int x, int y, Tile v) throws IllegalArgumentException {
        if (x < 0 || x >= 3 || y < 0 || y >= 3 || v == Tile.BLANK) {
            throw new IllegalArgumentException();
        }

        board[y][x] = v;

        if (rows[y].checkMoveForWin(v) ||
                cols[x].checkMoveForWin(v) ||
                (x == y && diags[0].checkMoveForWin(v)) ||
                ((2 - x) == y && diags[1].checkMoveForWin(v))) {
            System.exit(1);
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
        Board secondBoard = (Board) o;
        return Arrays.deepEquals(board, secondBoard.board);
    }

    @Override
    public String toString() {
        String format = ""
                + "      1     2     3   \n"
                + "   ┌─────┬─────┬─────┐\n"
                + "A  │%3s  │%3s  │%3s  │\n"
                + "   ├─────┼─────┼─────┤\n"
                + "B  │%3s  │%3s  │%3s  │\n"
                + "   ├─────┼─────┼─────┤\n"
                + "C  │%3s  │%3s  │%3s  │\n"
                + "   └─────┴─────┴─────┘";

        String[] tiles = new String[9];
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                tiles[y * 3 + x] = Tile.asString(board[y][x]);
            }
        }

        return String.format(format, (Object[]) tiles);
    }
}
