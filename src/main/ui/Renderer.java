package ui;

import java.io.IOException;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import model.Game;

// Render the parts of the game to the console.
public class Renderer {
    private static final String BOARD_FORMAT = ""
            + "      1     2     3   \n"
            + "   ┌─────┬─────┬─────┐\n"
            + "A  │%3s  │%3s  │%3s  │\n"
            + "   ├─────┼─────┼─────┤\n"
            + "B  │%3s  │%3s  │%3s  │\n"
            + "   ├─────┼─────┼─────┤\n"
            + "C  │%3s  │%3s  │%3s  │\n"
            + "   └─────┴─────┴─────┘";
    private static final String PROMPT_FORMAT = "> %s";
    private static final String WIN_FORMAT = "%s wins!";
    private static final String DRAW = "Draw!";
    private static final String CONTINUE = "Press any key to quit.";

    private final Screen screen;
    private final Game game;
    private final Reader reader;

    // REQUIRES: column >= 0 and row >= 0
    // EFFECTS: Write a string to the console at the given location.
    private static void putMultilineString(TextGraphics tg, int column, int row, String string) {
        int i = 0;
        for (String line : string.split("\n")) {
            tg.putString(column, row + i++, line);
        }
    }

    // EFFECTS: Draw a representation of the board on the console.
    private void renderBoard(TextGraphics tg) {
        String string = String.format(BOARD_FORMAT, game.getBoard().getFormatArgs());
        putMultilineString(tg, 0, 0, string);
    }

    // EFFECTS: Draw a representation of the prompt on the console.
    private void renderPrompt(TextGraphics tg) {
        String string = String.format(PROMPT_FORMAT, reader.getFormatArgs());
        putMultilineString(tg, 0, 10, string);
    }

    // EFFECTS: Craete a new renderer.
    public Renderer(Screen screen, Game game, Reader reader) {
        this.screen = screen;
        this.game = game;
        this.reader = reader;
    }

    // EFFECTS: Draw the game elements on the console.
    public void renderGame() throws IOException {
        TextGraphics tg = screen.newTextGraphics();
        screen.setCursorPosition(new TerminalPosition(2 + reader.getBufferLength(), 10));
        screen.clear();
        renderBoard(tg);
        renderPrompt(tg);
        screen.refresh();
    }

    // REQUIRES: game.getState() == Game.State.Win
    // EFFECTS: Draw the win screen on the console.
    public void renderWin() throws IOException {
        TextGraphics tg = screen.newTextGraphics();
        screen.setCursorPosition(new TerminalPosition(0, 2));
        screen.clear();
        tg.putString(0, 0, String.format(WIN_FORMAT, game.getWinner()));
        tg.putString(0, 1, CONTINUE);
        screen.refresh();
    }

    // EFFECTS: Draw the draw screen on the console.
    public void renderDraw() throws IOException {
        TextGraphics tg = screen.newTextGraphics();
        screen.setCursorPosition(new TerminalPosition(0, 2));
        screen.clear();
        tg.putString(0, 0, DRAW);
        tg.putString(0, 1, CONTINUE);
        screen.refresh();
    }
}
