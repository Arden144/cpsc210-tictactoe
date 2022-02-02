package model;

import java.io.Serializable;

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
}
