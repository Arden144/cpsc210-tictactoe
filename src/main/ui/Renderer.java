package ui;

import java.io.IOException;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import model.Game;

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

    private final Screen screen;
    private final Game game;

    public Renderer(Screen screen, Game game) {
        this.screen = screen;
        this.game = game;
    }

    private static void putMultilineString(TextGraphics tg, int column, int row, String string) {
        int i = 0;
        for (String line : string.split("\n")) {
            tg.putString(column, row + i++, line);
        }
    }

    private void renderBoard(TextGraphics tg) {
        String string = String.format(BOARD_FORMAT, game.getBoard().getFormatArgs());
        putMultilineString(tg, 0, 0, string);
    }

    public void render() throws IOException {
        TextGraphics tg = screen.newTextGraphics();
        screen.clear();
        renderBoard(tg);
        screen.refresh();
    }
}
