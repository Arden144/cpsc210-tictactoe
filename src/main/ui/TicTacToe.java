package ui;

import java.io.IOException;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import model.Game;

public class TicTacToe {
    private Game game;
    private Screen screen;

    public TicTacToe() throws IOException, InterruptedException {
        screen = new DefaultTerminalFactory().createScreen();
        screen.startScreen();

        game = new Game();

        beginTicks();
    }

    private void beginTicks() throws InterruptedException, IOException {
        while (!game.isEnded()) {
            tick();
            Thread.sleep(1000L / Game.TICKS_PER_SECOND);
        }

        System.exit(0);
    }

    private void tick() throws IOException {
        handleUserInput();

        screen.setCursorPosition(new TerminalPosition(0, 0));
        screen.clear();
        render();
        screen.refresh();

        screen.setCursorPosition(new TerminalPosition(screen.getTerminalSize().getColumns() - 1, 0));
    }

    private void handleUserInput() throws IOException {
        KeyStroke stroke = screen.pollInput();
        if (stroke == null) {
            return;
        }
    }

    private void render() {
        if (game.isEnded()) {
            return;
        }
    }
}
