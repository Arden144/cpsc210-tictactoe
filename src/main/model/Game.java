package model;

import java.io.Serializable;

import com.googlecode.lanterna.TerminalSize;

public class Game implements Serializable {
    public static final int TICKS_PER_SECOND = 10;
    private static int width;
    private static int height;
    private boolean turn;
    private String message;
    private Board board;
    private State state;

    public State getState() {
        return state;
    }

    public Game() {
        reset();
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public Board getBoard() {
        return board;
    }

    public static void resize(TerminalSize size) {
        Game.width = size.getColumns();
        Game.height = size.getRows();
    }

    public String getMessage() {
        return message == "" ? String.format("%s's turn", turn ? "O" : "X") : message;
    }

    public boolean getTurn() {
        return turn;
    }

    public void reset() {
        board = new Board();
        message = "";
        turn = false;
        state = State.Play;
    }

    public void quit() {
        state = State.End;
    }

    public void addTile(int x, int y) {
        Tile tile = board.getPosition(x, y);
        if (tile != Tile.BLANK) {
            message = "There's already a tile in that position";
            return;
        }

        message = "";
        board.setPosition(x, y, turn ? Tile.O : Tile.X);
        if (board.isWon()) {
            state = State.Win;
        } else if (board.isDraw()) {
            state = State.Draw;
        } else {
            turn = !turn;
        }
    }
}
