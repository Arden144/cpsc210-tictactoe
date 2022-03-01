package ui;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import model.Game;

// Read console input and run game methods based on the command entered.
public class Reader {
    private static final Pattern LETTER_NUMBER = Pattern.compile("([a-cA-C])([1-3])");
    private static final Pattern NUMBER_LETTER = Pattern.compile("([1-3])([a-cA-C])");
    private static final Pattern QUIT = Pattern.compile("(?i)quit");

    private final Screen screen;
    private final Game game;

    private String buffer;

    // REQUIRES: string matches the pattern [a-cA-C1-3]
    // EFFECTS: Return the 0-indexed position of the given board axis.
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

    // MODIFIES: this
    // EFFECTS: Add a character to the buffer.
    private void character(Character c) {
        buffer += c;
    }

    // MODIFIES: this
    // EFFECTS: Removes a character from the buffer if the buffer is longer than 0
    // characters.
    private void backspace() {
        if (buffer.length() == 0) {
            return;
        }
        buffer = buffer.substring(0, buffer.length() - 1);
    }

    // EFFECTS: Tell the game to place a tile at the given axis.
    private void place(String x, String y) {
        game.place(locationToInt(x), locationToInt(y));
    }

    // EFFECTS: Ends the game
    private void quit() {
        game.end();
    }

    // EFFECTS: Match the buffer against the possible commands and run the
    // corresponding method, then clear the buffer.
    private void enter() {
        Matcher m;

        m = LETTER_NUMBER.matcher(buffer);
        if (m.find()) {
            place(m.group(2), m.group(1));
        }

        m = NUMBER_LETTER.matcher(buffer);
        if (m.find()) {
            place(m.group(1), m.group(2));
        }

        m = QUIT.matcher(buffer);
        if (m.find()) {
            quit();
        }

        buffer = "";
    }

    // EFFECTS: Creates a new reader.
    public Reader(Screen screen, Game game) {
        this.screen = screen;
        this.game = game;
        buffer = "";
    }

    // MODIFIES: this
    // EFFECTS: Block until a keyboard key is pressed, then call the corresponding
    // method based on the type of the key pressed.
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

    // EFFECTS: Wait for any key to be pressed.
    public void pause() throws IOException {
        screen.readInput();
    }

    // EFFECTS: Returns the length of the buffer.
    public int getBufferLength() {
        return buffer.length();
    }

    // EFFECTS: Returns the arguments to be used in String.format to display the
    // buffer in a prompt.
    public Object[] getFormatArgs() {
        return new Object[] { buffer };
    }
}
