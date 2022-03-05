package model;

import persistence.Codable;

// Represents a Tic Tac Toe game.
public class Game extends Codable {
    // Represents the current game state.
    public enum State {
        Play, Draw, Win, End
    }

    private State state;
    private final Board board;
    private Tile tile;

    public Game(State state, Board board, Tile tile) {
        this.state = state;
        this.board = board;
        this.tile = tile;
    }

    // EFFECTS: Creates a new game.
    public Game() {
        state = State.Play;
        board = new Board();
        tile = Tile.newX();
    }

    // REQUIRES: 0 <= x < 3 and 0 <= y < 3
    // MODIFIES: this
    // EFFECTS: Try placing a tile on the board, change the next tile if successful,
    // and check if the move resulted in a win or draw.
    public void place(int x, int y) {
        boolean placed = board.place(x, y, tile);
        if (placed) {
            tile = tile.nextTile();
            if (board.getWin()) {
                state = State.Win;
            }
            if (board.isDraw()) {
                state = State.Draw;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Set game state to end.
    public void end() {
        state = State.End;
    }

    // REQUIRES: state = State.Win
    // EFFECTS: Return the name of the winner.
    public String getWinner() {
        return tile.nextTile().toString();
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Board getBoard() {
        return board;
    }
}
