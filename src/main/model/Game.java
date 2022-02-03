package model;

import java.io.Serializable;

public class Game implements Serializable {
    public static final int TICKS_PER_SECOND = 10;
    private int scoreX;
    private int scoreO;
    private Board board;

    public Game() {
        board = new Board();
        scoreX = 0;
        scoreO = 0;
    }

    public Board getBoard() {
        return board;
    }

    public boolean isEnded() {
        return false;
    }

    public int getScoreX() {
        return scoreX;
    }

    public int getScoreO() {
        return scoreO;
    }

    public void addScoreX() {
        scoreX += 1;
    }

    public void addScoreO() {
        scoreO += 1;
    }
}
