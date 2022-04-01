package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.Tile;

// Represents a button on the screen.
public class Button extends JButton implements ActionListener {
    private final TicTacToe window;
    private final int posX;
    private final int posY;

    // REQUIRES: 0 <= posX <= 2, 0 <= posY <= 2
    // EFFECTS: Creates a new button at the given position.
    public Button(TicTacToe window, int posX, int posY) {
        super();
        this.window = window;
        this.posX = posX;
        this.posY = posY;

        setBackground(Color.WHITE);
        setFont(new Font("Arial", Font.BOLD, 64));
        addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: Updates the text on the button to match the board.
    public void updateComponent(Tile[][] board) {
        Tile tile = board[posY][posX];
        if (tile != null) {
            setText(tile.toString());
        } else {
            setText("");
        }
    }

    // EFFECTS: Places a tile on the board when the button is clicked.
    @Override
    public void actionPerformed(ActionEvent e) {
        window.place(posX, posY);
    }
}
