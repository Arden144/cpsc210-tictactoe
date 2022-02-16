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

    /**
     * Internal
     */

    private void play() {

    }

    private void draw() {

    }

    private void win() {

    }

    /**
     * Public
     */

    public Game() {
        state = State.Play;
        board = new Board();
    }

    public void place(int x, int y) {
        //
    }

    public void end() {
        state = State.End;
    }

    /**
     * Getters & Setters
     */

    public State getState() {
        return state;
    }

    public Board getBoard() {
        return board;
    }
}
