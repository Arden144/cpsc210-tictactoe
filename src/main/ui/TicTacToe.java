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

    public TicTacToe() throws IOException {
        screen = new DefaultTerminalFactory().createScreen();
        screen.startScreen();

        game = new Game();
        prompt = new Prompt(game);

        start();
    }

    private void start() throws IOException {
        while (!game.isEnded()) {
            screen.setCursorPosition(new TerminalPosition(2 + prompt.getPrompt().length(), 9));
            screen.clear();
            render();
            screen.refresh();

            getUserInput();
        }
    }

    private void getUserInput() throws IOException {
        KeyStroke stroke = screen.readInput();

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
        }
    }

    private void render() {
        drawMessage();
        drawBoard();
        drawStatus();
    }

    private void drawMessage() {
        placeText(0, 0, game.getMessage());
    }

    private void drawBoard() {
        placeText(0, 1, game.getBoard().toString());
    }

    private void drawStatus() {
        placeText(0, 9, "> " + prompt.getPrompt());
    }

    private void placeText(int x, int y, String message) {
        TextGraphics text = screen.newTextGraphics();

        String[] lines = message.split("\n");
        int i = 0;

        for (String line : lines) {
            text.putString(x, y + i++, line);
        }
    }
}
