package ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import model.Game;

public class TicTacToe {
    private final Game game;
    private final JFrame frame;
    private final JMenuBar menu;
    private final JPanel board;

    public TicTacToe() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        game = new Game();
        frame = new JFrame();
        frame.setTitle("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setSize(300, 300);
        Container contentPane = frame.getContentPane();

        menu = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem save = new JMenuItem("Save");
        file.add(save);
        menu.add(file);
        frame.setJMenuBar(menu);

        board = new JPanel(new GridLayout(3, 3));
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                final int x = col;
                final int y = row;
                JButton button = new JButton();
                button.setBackground(Color.WHITE);
                button.setFont(new Font("Arial", Font.BOLD, 64));
                button.setText(game.getBoard().getText(x, y));
                button.addActionListener(e -> {
                    game.place(x, y);
                    button.setText(game.getBoard().getText(x, y));
                    if (game.getState() == Game.State.Win) {
                        JOptionPane.showMessageDialog(null, String.format("%s wins!", game.getWinner()));
                    } else if (game.getState() == Game.State.Draw) {
                        JOptionPane.showMessageDialog(null, "Draw!");
                    }
                });
                board.add(button);
            }
        }
        contentPane.add(board);
    }

    public void start() {
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
