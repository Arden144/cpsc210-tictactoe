package model;

public class Game {
    public enum State {
        Play, Draw, Win, End
    }

    /**
     * Data
     */

    private State state;
    private Board board;
    private Tile tile;

    /**
     * Internal
     */

    /**
     * Public
     */

    public Game() {
        state = State.Play;
        board = new Board();
        tile = Tile.newX();
    }

    public void place(int x, int y) {
        boolean placed = board.place(x, y, tile);
        if (placed) {
            tile = tile.nextTile();
            if (board.getWin()) {
                state = State.Win;
            }
        }
    }

    public void end() {
        state = State.End;
    }

    public String getWinner() {
        return tile.nextTile().toString();
    }

    public State getState() {
        return state;
    }

    public Board getBoard() {
        return board;
    }
}
