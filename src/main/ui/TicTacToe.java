package ui;

import java.io.IOException;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import model.Game;
import model.State;

class TicTacToe {
    private final Game game;
    private final Screen screen;
    private final Prompt prompt;

    public TicTacToe() throws IOException {
        screen = new DefaultTerminalFactory().createScreen();
        screen.startScreen();

        Game.resize(screen.getTerminalSize());
        game = new Game();
        prompt = new Prompt(game);

        start();
    }

    private void start() throws IOException {
        while (game.getState() != State.End) {
            TerminalSize size = screen.doResizeIfNecessary();
            if (size != null) {
                Game.resize(size);
            }

            screen.setCursorPosition(new TerminalPosition(prompt.getWidth(), 9));

            screen.clear();
            draw(screen.newTextGraphics());
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
                break;
            case Character:
                prompt.add(stroke.getCharacter());
                break;
            default:
        }
    }

    public void draw(TextGraphics tg) {
        switch (game.getState()) {
            case Play:
                Drawing.placeText(tg, 0, 0, game.getMessage());
                Drawing.placeText(tg, 0, 1, game.getBoard().toString());
                break;
            case Draw:
                Drawing.placeText(tg, 0, 0, "Draw!\nPress enter to continue");
                break;
            case Win:
                Drawing.placeText(tg, 0, 0,
                        String.format("%s Wins!\nPress enter to continue", game.getTurn() ? "O" : "X"));
                break;
            default:
                break;
        }

        Drawing.placeText(tg, 0, 9, "> " + prompt.toString());
    }
}
