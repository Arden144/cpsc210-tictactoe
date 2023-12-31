package ui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

// Represents the menu bar on the screen.
public class Menu extends JMenuBar {
    // EFFECTS: Creates a new menu bar.
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

        JMenu edit = new JMenu("Edit");
        JMenuItem undo = new JMenuItem("Undo");
        undo.addActionListener(l -> window.undo());
        edit.add(undo);
        add(edit);
    }
}
