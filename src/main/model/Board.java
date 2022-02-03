package model;

import java.io.Serializable;
import java.util.Arrays;

public class Board implements Serializable {
    private Tile[][] board;

    public Board() {
        board = new Tile[][] {
                { Tile.BLANK, Tile.BLANK, Tile.BLANK },
                { Tile.BLANK, Tile.BLANK, Tile.BLANK },
                { Tile.BLANK, Tile.BLANK, Tile.BLANK }
        };
    }

    public Tile getPosition(int x, int y) throws IllegalArgumentException {
        if (x < 0 || x >= 3 || y < 0 || y >= 3) {
            throw new IllegalArgumentException();
        }

        return board[y][x];
    }

    public void setPosition(int x, int y, Tile v) throws IllegalArgumentException {
        if (x < 0 || x >= 3 || y < 0 || y >= 3) {
            throw new IllegalArgumentException();
        }

        board[y][x] = v;
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
                + "┌───┬───┬───┐\n"
                + "│ %s │ %s │ %s │\n"
                + "├───┼───┼───┤\n"
                + "│ %s │ %s │ %s │\n"
                + "├───┼───┼───┤\n"
                + "│ %s │ %s │ %s │\n"
                + "└───┴───┴───┘";

        String[] tiles = new String[9];
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                tiles[y * 3 + x] = Tile.asString(board[y][x]);
            }
        }

        return String.format(format, tiles);
    }
}
