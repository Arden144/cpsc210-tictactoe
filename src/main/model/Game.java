package model;

import java.util.function.BiConsumer;

import persistence.Codable;
import persistence.Ignored;

// Represents a Tic Tac Toe game.
public class Game extends Codable {
    // Represents the current game state.
    public enum State {
        Play, Draw, Win, Restart
    }

    private State state;
    private Board board;
    private Tile tile;
    @Ignored
    private BiConsumer<State, State> listener;

    public Game(State state, Board board, Tile tile) {
        this.state = state;
        this.board = board;
        this.tile = tile;
    }

    // EFFECTS: Creates a new game.
    public Game() {
        restart();
    }

    // REQUIRES: 0 <= x < 3 and 0 <= y < 3
    // MODIFIES: this
    // EFFECTS: Try placing a tile on the board, change the next tile if successful,
    // and check if the move resulted in a win or draw.
    public void place(int x, int y) {
        boolean placed = board.place(x, y, tile);
        if (placed) {
            tile = tile.nextTile();
            if (board.isWin()) {
                setState(State.Win);
            } else if (board.isDraw()) {
                setState(State.Draw);
            }
        }
    }

    public void restart() {
        board = new Board();
        tile = Tile.newX();
        setState(State.Restart);
        setState(State.Play);
    }

    public void addStateChangeListener(BiConsumer<State, State> listener) {
        this.listener = listener;
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
        State oldState = this.state;
        this.state = state;
        if (listener != null) {
            listener.accept(oldState, state);
        }
    }

    public Board getBoard() {
        return board;
    }
}
