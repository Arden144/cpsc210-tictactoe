package ui;

import java.io.IOException;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import model.Game;
import persistence.Persist;

// Represents a Tic Tac Toe game.
public class TicTacToe {
    private final Screen screen;
    private final Game game;
    private final Renderer renderer;
    private final Reader reader;

    // EFFECTS: Run the methods to play a round of the game.
    private void play() throws IOException {
        renderer.renderGame();
        reader.read();
    }

    // EFFETCTS: Draw the draw screen and bring the game to the end state after a
    // keypress.
    private void draw() throws IOException {
        renderer.renderDraw();
        reader.pause();
        game.end();
    }

    // EFFECTS: Draw the win screen and bring the game to the end
    // state after a keypress.
    private void win() throws IOException {
        renderer.renderWin();
        reader.pause();
        game.end();
    }

    public TicTacToe(Game game) throws IOException {
        screen = new DefaultTerminalFactory().createScreen();
        this.game = game;
        reader = new Reader(screen, game);
        renderer = new Renderer(screen, game, reader);
    }

    // EFFECTS: Create a new Tic Tac Toe game.
    public TicTacToe() throws IOException {
        screen = new DefaultTerminalFactory().createScreen();
        game = new Game();
        reader = new Reader(screen, game);
        renderer = new Renderer(screen, game, reader);
    }

    // EFFECTS: Start the game loop and run the corresponding method based on the
    // game state.
    public void start() throws IOException {
        screen.startScreen();

        while (game.getState() != Game.State.End) {
            switch (game.getState()) {
                case Play:
                    play();
                    break;
                case Draw:
                    draw();
                    break;
                case Win:
                    win();
                    break;
                default:
                    throw new IllegalStateException("Unexpected Game State: " + game.getState());
            }
        }

        screen.stopScreen();
        Persist.save(game.encode());
    }
}
