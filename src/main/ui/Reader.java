package ui;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import model.Game;

public class Reader {
    private static final Pattern LETTER_NUMBER = Pattern.compile("([a-cA-C])([1-3])");
    private static final Pattern NUMBER_LETTER = Pattern.compile("([1-3])([a-cA-C])");
    private static final Pattern QUIT = Pattern.compile("(?i)quit");

    private final Screen screen;
    private final Game game;

    private String buffer;

    private static int locationToInt(String string) {
        switch (string) {
            case "1":
            case "a":
            case "A":
                return 0;
            case "2":
            case "b":
            case "B":
                return 1;
            case "3":
            case "c":
            case "C":
                return 2;
            default:
                throw new IllegalArgumentException("Unexpected location: " + string);
        }
    }

    private void character(Character c) {
        buffer += c;
    }

    private void backspace() {
        buffer = buffer.substring(0, buffer.length() - 1);
    }

    private void place(String x, String y) {
        game.place(locationToInt(x), locationToInt(y));
    }

    private void quit() {
        game.end();
    }

    private void enter() {
        Matcher m;

        m = LETTER_NUMBER.matcher(buffer);
        if (m.find()) {
            place(m.group(2), m.group(1));
        }

        m = NUMBER_LETTER.matcher(buffer);
        if (m.find()) {
            place(m.group(1), m.group(1));
        }

        m = QUIT.matcher(buffer);
        if (m.find()) {
            quit();
        }

        buffer = "";
    }

    public Reader(Screen screen, Game game) {
        this.screen = screen;
        this.game = game;
        buffer = "";
    }

    public void read() throws IOException {
        KeyStroke key = screen.readInput();

        switch (key.getKeyType()) {
            case Character:
                character(key.getCharacter());
                break;
            case Backspace:
                backspace();
                break;
            case Enter:
                enter();
                break;
            default:
                break;
        }
    }

    @Override
    public String toString() {
        return buffer;
    }
}
