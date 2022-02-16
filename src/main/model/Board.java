package model;

public class Board {
    /**
     * Data
     */

    private Tile[] board;
    private RowCount[] columns;
    private RowCount[] rows;
    private RowCount fallingDiagonal;
    private RowCount risingDiagonal;
    private boolean win;

    /**
     * Internal
     */

    /**
     * Public
     */

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
    }

    public void place(int x, int y, Tile t) {
        board[y * 3 + x] = t;

        win = win
                || columns[x].checkWin(t)
                || rows[y].checkWin(t)
                || (x == y && fallingDiagonal.checkWin(t))
                || ((2 - x) == y && risingDiagonal.checkWin(t));
    }

    /**
     * Getters/Setters
     */

    public Object[] getFormatArgs() {
        return (Object[]) board;
    }
}
