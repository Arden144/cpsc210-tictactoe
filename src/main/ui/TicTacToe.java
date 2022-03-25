package ui;

import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import model.Game;
import model.Game.State;
import persistence.Codable;
import persistence.Persist;

public class TicTacToe extends JFrame {
    private WindowListener windowListener = new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
            if (game.getState() == Game.State.Win || game.getState() == Game.State.Draw) {
                game.restart();
            }
            save();
        }
    };

    private Game game;
    private Board board;

    public TicTacToe() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        load();
        if (game == null) {
            game = new Game();
        }
        game.addStateChangeListener(this::onStateChange);

        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(windowListener);

        setJMenuBar(new Menu(this));
        Container contentPane = getContentPane();
        board = new Board(game);
        contentPane.add(board);
    }

    private void onStateChange(State oldState, State newState) {
        switch (newState) {
            case Win:
                JOptionPane.showMessageDialog(null, String.format("%s wins!", game.getWinner()));
                board.setEnabled(false);
                break;
            case Draw:
                JOptionPane.showMessageDialog(null, "Draw!");
                board.setEnabled(false);
                break;
            case Restart:
                board.updateText();
                board.setEnabled(true);
                break;
            default:
                break;
        }
    }

    public void save() {
        try {
            Persist.save(game.encode());
        } catch (IOException ex) {
            System.err.println("Failed to save the game to file.");
        }
    }

    public void load() {
        try {
            game = Codable.decode(Persist.load(), Game.class);
            game.addStateChangeListener(this::onStateChange);
        } catch (IOException e) {
            System.err.println("Failed to load game from save file.");
        }
        if (board != null) {
            board.setGame(game);
            board.updateText();
            board.setEnabled(true);
        }
    }

    public void restart() {
        game.restart();
    }

    public void start() {
        setSize(300, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
