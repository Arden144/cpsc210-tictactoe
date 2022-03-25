package ui;

import java.awt.GridLayout;

import javax.swing.JPanel;

import model.Game;

public class Board extends JPanel {
    private Button[] buttons;

    public Board(Game game) {
        super();

        setLayout(new GridLayout(3, 3));

        buttons = new Button[9];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row * 3 + col] = new Button(game, row, col);
                add(buttons[row * 3 + col]);
            }
        }
    }

    public void updateText() {
        for (Button button : buttons) {
            button.updateText();
        }
    }

    @Override
    public void setEnabled(boolean b) {
        super.setEnabled(b);
        for (Button button : buttons) {
            button.setEnabled(b);
        }
    }

    public void setGame(Game game) {
        for (Button button : buttons) {
            button.setGame(game);
        }
    }
}
