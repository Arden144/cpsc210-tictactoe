package model;

public class Game {
    public static final int TICKS_PER_SECOND = 10;
    private Board board;

    public Game() {
        board = new Board();
    }

    public boolean isEnded() {
        return false;
    }
}
