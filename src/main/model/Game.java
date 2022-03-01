package model;

public class Game {
    public enum State {
        Play, Draw, Win, End
    }

    private State state;
    private final Board board;
    private Tile tile;

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
            if (board.getDraw()) {
                state = State.Draw;
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
