package model;

public class Board {
    private final Tile[] board;
    private final RowCount[] columns;
    private final RowCount[] rows;
    private final RowCount fallingDiagonal;
    private final RowCount risingDiagonal;
    private boolean win;
    private int turns;

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

    public boolean getWin() {
        return win;
    }

    public boolean getDraw() {
        return turns >= 9;
    }

    public Object[] getFormatArgs() {
        return board;
    }
}
