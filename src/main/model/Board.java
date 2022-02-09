package model;

import java.io.Serializable;

public class Board implements Serializable {
    private final Count[] rows;
    private final Count[] cols;
    private final Count[] diags;
    protected final Tile[][] board;

    private boolean win;
    private boolean draw;

    public Board() {
        board = new Tile[][] {
                { Tile.BLANK, Tile.BLANK, Tile.BLANK },
                { Tile.BLANK, Tile.BLANK, Tile.BLANK },
                { Tile.BLANK, Tile.BLANK, Tile.BLANK }
        };
        rows = new Count[] { new Count(), new Count(), new Count() };
        cols = new Count[] { new Count(), new Count(), new Count() };
        diags = new Count[] { new Count(), new Count() };
        win = false;
        draw = false;
    }

    protected Tile getPosition(int x, int y) {
        return board[y][x];
    }

    protected void setPosition(int x, int y, Tile v) {
        board[y][x] = v;

        win = rows[y].checkMoveForWin(v)
                || cols[x].checkMoveForWin(v)
                || (x == y && diags[0].checkMoveForWin(v))
                || ((2 - x) == y && diags[1].checkMoveForWin(v));
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

    public boolean isWon() {
        return win;
    }

    public boolean isDraw() {
        return draw;
    }
}
