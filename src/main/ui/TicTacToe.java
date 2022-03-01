package ui;

import java.io.IOException;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import model.Game;

public class TicTacToe {
    /**
     * Data
     */

    private final Screen screen;
    private final Game game;
    private final Renderer renderer;
    private final Reader reader;

    /**
     * Internal
     */

    private void play() throws IOException {
        renderer.renderGame();
        reader.read();
    }

    private void draw() throws IOException {
        renderer.renderDraw();
        reader.pause();
        game.end();
    }

    private void win() throws IOException {
        renderer.renderWin();
        reader.pause();
        game.end();
    }

    /**
     * Public
     */

    public TicTacToe() throws IOException {
        screen = new DefaultTerminalFactory().createScreen();
        game = new Game();
        reader = new Reader(screen, game);
        renderer = new Renderer(screen, game, reader);
    }

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
    }
}
