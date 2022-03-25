package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.Game;

public class Button extends JButton implements ActionListener {
    private Game game;
    private final int posX;
    private final int posY;

    public Button(Game game, int posX, int posY) {
        super();
        this.game = game;
        this.posX = posX;
        this.posY = posY;

        setBackground(Color.WHITE);
        setFont(new Font("Arial", Font.BOLD, 64));
        updateText();
        addActionListener(this);
    }

    public void updateText() {
        setText(game.getBoard().getText(posX, posY));
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.place(posX, posY);
        updateText();
    }
}
