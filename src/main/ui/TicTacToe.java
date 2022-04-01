package ui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import model.Event;
import model.EventLog;
import model.Game;
import persistence.Decoder;
import persistence.Encoder;
import persistence.Persist;

// Represents the game window.
public class TicTacToe extends JFrame {
    private static final WindowListener windowListener = new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
            EventLog log = EventLog.getInstance();
            for (Event event : log) {
                System.out.println(event);
            }
        }
    };

    private Game game;
    private final Board board;

    // EFFECTS: Creates a new game window.
    public TicTacToe() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Failed to use the cross-platform theme.");
        }
        game = new Game();
        board = new Board(this);

        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(windowListener);
        setJMenuBar(new Menu(this));
        getContentPane().add(board);
    }

    // MODIFIES: this
    // EFFECTS: Place a tile on the board, and show a message if the game is ending.
    public void place(int posX, int posY) {
        String message = game.place(posX, posY);
        updateComponent();
        if (message != null) {
            JOptionPane.showMessageDialog(null, message);
        }
    }

    // MODIFIES: this
    // EFFECTS: Undo the last move.
    public void undo() {
        game.undo();
        updateComponent();
    }

    // MODIFIES: this
    // EFFECTS: Restarts the game.
    public void restart() {
        game = new Game();
        updateComponent();
    }

    // EFFECTS: Saves the game to a file.
    public void save() {
        try {
            Persist.save(Encoder.encodeGame(game));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Failed to save the game.");
        }
    }

    // MODIFIES: this
    // EFFECTS: Loads the game from a file.
    public void load() {
        try {
            game = Decoder.decodeGame(Persist.load());
            updateComponent();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Failed to load the game.");
        }
    }

    // MODIFIES: this
    // EFFECTS: Updates the board
    private void updateComponent() {
        if (board != null) {
            board.updateComponent(game.getBoard());
            board.setEnabled(!game.getEnded());
        }
    }

    // MODIFIES: this
    // EFFECTS: Makes the game window visible.
    public void start() {
        setSize(300, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
