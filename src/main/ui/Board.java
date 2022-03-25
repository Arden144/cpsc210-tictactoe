package ui;

import java.awt.GridLayout;

import javax.swing.JPanel;

import model.Game;

public class Board extends JPanel {
    private final Game game;

    public Board(Game game) {
        super();
        this.game = game;

        setLayout(new GridLayout(3, 3));

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                add(new Button(game, col, row));
            }
        }
    }

    public void updateText() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                ((Button) getComponent(row * 3 + col)).updateText();
            }
        }
    }

    @Override
    public void setEnabled(boolean b) {
        super.setEnabled(b);
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                ((Button) getComponent(row * 3 + col)).setEnabled(b);
            }
        }
    }
}
