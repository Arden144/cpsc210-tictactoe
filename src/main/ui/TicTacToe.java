package ui;

import java.io.IOException;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import model.Game;

public class TicTacToe {
    private Game game;
    private Screen screen;
    private Prompt prompt;

    public TicTacToe() throws IOException, InterruptedException {
        screen = new DefaultTerminalFactory().createScreen();
        screen.startScreen();

        game = new Game();
        prompt = new Prompt(game);

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

        screen.setCursorPosition(new TerminalPosition(2 + prompt.getPrompt().length(), 8));
        screen.clear();
        render();
        screen.refresh();
    }

    private void handleUserInput() throws IOException {
        KeyStroke stroke = screen.pollInput();
        if (stroke == null) {
            return;
        }

        switch (stroke.getKeyType()) {
            case Backspace:
                prompt.backspace();
                break;
            case Enter:
                prompt.run();
                prompt.clear();
                break;
            case Character:
                prompt.add(stroke.getCharacter());
                break;
            default:
                break;
        }
    }

    private void render() {
        if (game.isEnded()) {
            return;
        }

        drawBoard();
        drawStatus();
    }

    private void drawBoard() {
        placeText(0, 0, game.getBoard().toString());
    }

    private void drawStatus() {
        placeText(0, 8, "> " + prompt.getPrompt());
    }

    private void placeText(int x, int y, String message) {
        TextGraphics text = screen.newTextGraphics();

        String[] lines = message.split("\n");
        int lineNumber = 0;

        for (String line : lines) {
            text.putString(x, y + lineNumber++, line);
        }
    }
}
