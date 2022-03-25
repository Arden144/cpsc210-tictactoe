package ui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import model.Game;

public class Menu extends JMenuBar {
    public Menu(Game game) {
        super();

        JMenu file = new JMenu("File");
        JMenuItem newGame = new JMenuItem("New Game");
        newGame.addActionListener(l -> game.restart());
        file.add(newGame);
        add(file);
    }
}
