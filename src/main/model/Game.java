package model;

import java.util.Stack;

// Represents a Tic Tac Toe game.
public class Game {
    private final Stack<Move> moves;
    private boolean ended;

    // EFFECTS: Creates a new game with existing moves.
    public Game(Stack<Move> moves, boolean ended) {
        this.moves = moves;
        this.ended = ended;
    }

    // EFFECTS: Creates a new game with no moves.
    public Game() {
        moves = new Stack<>();
        ended = false;
    }

    // REQUIRES: 0 <= posX <= 2, 0 <= posY <= 2
    // MODIFIES: this
    // EFFECTS: Places a tile on the board.
    public String place(int posX, int posY) {
        if (isOverlapping(posX, posY)) {
            return null;
        }

        Tile tile = nextTile();
        moves.push(new Move(posX, posY, tile));
        logPlace(posX, posY, tile);

        String winner = getWinner();
        if (winner != null) {
            ended = true;
            return winner + " wins!";
        } else if (isDraw()) {
            ended = true;
            return "Draw!";
        } else {
            return null;
        }
    }

    // REQUIRES: 0 <= posX <= 2, 0 <= posY <= 2
    // EFFECTS: Returns true if a tiles exists at the given position.
    private boolean isOverlapping(int posX, int posY) {
        for (Move move : moves) {
            if (move.getPosX() == posX && move.getPosY() == posY) {
                return true;
            }
        }
        return false;
    }

    // REQUIRES: 0 <= posX <= 2, 0 <= posY <= 2
    // EFFECTS: Adds a placed tile to the event log.
    private void logPlace(int posX, int posY, Tile tile) {
        EventLog log = EventLog.getInstance();
        log.logEvent(new Event(String.format("Tile %s placed at (%d, %d)", tile, posX, posY)));
    }

    // EFFECTS: Returns the name of the winner, or null if there is no winner.
    private String getWinner() {
        Tile[][] board = getBoard();
        for (Tile[] row : board) {
            if (row[0] != null && row[0] == row[1] && row[1] == row[2]) {
                return row[0].toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (board[0][i] != null && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return board[0][i].toString();
            }
        }

        if (board[0][0] != null && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return board[0][0].toString();
        }

        if (board[0][2] != null && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return board[0][2].toString();
        }

        return null;
    }

    // EFFECTS: Returns true if the game is a draw.
    private boolean isDraw() {
        return moves.size() == 9;
    }

    // MODIFIES: this
    // EFFECTS: Undo the last move.
    public void undo() {
        if (moves.empty()) {
            return;
        }

        ended = false;
        Move move = moves.pop();
        logUndo(move.getPosX(), move.getPosY(), move.getTile());
    }

    // REQUIRES: 0 <= posX <= 2, 0 <= posY <= 2
    // EFFECTS: Adds an undo event to the event log.
    private void logUndo(int posX, int posY, Tile tile) {
        EventLog log = EventLog.getInstance();
        log.logEvent(new Event(String.format("Undid tile %s from (%d, %d)", tile, posX, posY)));
    }

    // EFFECTS: Returns the next tile to be placed.
    private Tile nextTile() {
        if (moves.size() % 2 == 0) {
            return Tile.X;
        } else {
            return Tile.O;
        }
    }

    // EFFECTS: Returns a representation of the board.
    public Tile[][] getBoard() {
        Tile[][] board = new Tile[3][3];
        for (Move move : moves) {
            board[move.getPosY()][move.getPosX()] = move.getTile();
        }
        return board;
    }

    public Stack<Move> getMoves() {
        return moves;
    }

    public boolean getEnded() {
        return ended;
    }
}
