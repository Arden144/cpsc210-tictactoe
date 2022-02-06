package model;

import java.io.Serializable;

public class Game implements Serializable {
    public static final int TICKS_PER_SECOND = 10;
    private boolean ended;
    private boolean turn;
    private int scoreX;
    private int scoreO;
    private String message;
    private Board board;

    public Game() {
        board = new Board();
        message = "";
        turn = false;
        scoreX = 0;
        scoreO = 0;
    }

    public Board getBoard() {
        return board;
    }

    public String getMessage() {
        return message == "" ? String.format("%s's turn", turn ? "O" : "X") : message;
    }

    public boolean isEnded() {
        return ended;
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

    private void nextTurn() {
        turn = !turn;
    }

    public boolean addTile(int x, int y) {
        Tile tile = board.getPosition(x, y);
        if (tile != Tile.BLANK) {
            message = "There's already a tile in that position";
            return false;
        }

        Tile newTile = turn ? Tile.O : Tile.X;
        message = "";
        board.setPosition(x, y, newTile);
        nextTurn();
        return true;
    }

    public void end() {
        ended = true;
    }
}
