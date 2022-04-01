package ui;

import java.awt.GridLayout;

import javax.swing.JPanel;

import model.Tile;

// Represents a game board shown on the screen.
public class Board extends JPanel {
    private final Button[] buttons;

    // EFFECTS: Creates a new board on the window.
    public Board(TicTacToe window) {
        super();

        setLayout(new GridLayout(3, 3));

        buttons = new Button[9];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row * 3 + col] = new Button(window, col, row);
                add(buttons[row * 3 + col]);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Updates the buttons on the board.
    public void updateComponent(Tile[][] board) {
        for (Button button : buttons) {
            button.updateComponent(board);
        }
    }

    // MODIFIES: this
    // EFFECTS: Sets the enabled/disabled state of the board and all buttons.
    @Override
    public void setEnabled(boolean b) {
        super.setEnabled(b);
        for (Button button : buttons) {
            button.setEnabled(b);
        }
    }
}
