package ui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import model.Game;

public class Menu extends JMenuBar {
    public Menu(TicTacToe window) {
        super();

        JMenu file = new JMenu("File");
        JMenuItem newGame = new JMenuItem("New Game");
        newGame.addActionListener(l -> window.restart());
        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(l -> window.save());
        JMenuItem load = new JMenuItem("Load");
        load.addActionListener(l -> window.load());
        file.add(newGame);
        file.add(save);
        file.add(load);
        add(file);
    }
}
