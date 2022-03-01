package model;

// Represents a Tic Tac Toe board.
public class Board {
    private final Tile[] board;
    private final RowCount[] columns;
    private final RowCount[] rows;
    private final RowCount fallingDiagonal;
    private final RowCount risingDiagonal;
    private boolean win;
    private int turns;

    // EFFECTS: Creates a new board
    public Board() {
        board = new Tile[9];
        for (int i = 0; i < 9; i++) {
            board[i] = Tile.newBlank();
        }
        columns = new RowCount[] { new RowCount(), new RowCount(), new RowCount() };
        rows = new RowCount[] { new RowCount(), new RowCount(), new RowCount() };
        fallingDiagonal = new RowCount();
        risingDiagonal = new RowCount();
        win = false;
        turns = 0;
    }

    // REQUIRES: 0 <= x < 3 and 0 <= y < 3 and t.getType() != Tile.Type.Blank
    // MODIFIES: this
    // EFFECTS: Attempts to place a tile on the board by adding it to the board,
    // counting a turn, and updating the win state. Returns true if the tile was
    // placed and false if there is already a tile in that position.
    public boolean place(int x, int y, Tile t) {
        if (!board[y * 3 + x].isBlank()) {
            return false;
        }

        board[y * 3 + x] = t;
        turns++;
        win = win
                || columns[x].checkWin(t)
                || rows[y].checkWin(t)
                || (x == y && fallingDiagonal.checkWin(t))
                || ((2 - x) == y && risingDiagonal.checkWin(t));

        return true;
    }

    // EFFECTS: Returns true if 9 turns have been taken, resulting a in draw.
    public boolean isDraw() {
        return turns == 9;
    }

    public boolean getWin() {
        return win;
    }

    public Object[] getFormatArgs() {
        return board;
    }
}
