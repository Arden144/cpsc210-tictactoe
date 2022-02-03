package ui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Game;
import model.Tile;

public class Prompt {
    private static final Pattern LETTER_NUMBER = Pattern.compile("([a-cA-C])([1-3])");
    private static final Pattern NUMBER_LETTER = Pattern.compile("([1-3])([a-cA-C])");

    private String prompt;
    private Game game;

    public Prompt(Game game) {
        this.game = game;
        prompt = "";
    }

    public void run() {
        Matcher m;

        /**
         * Match group for placing a tile
         */

        // Number and then a letter (ex. 1A)
        m = Prompt.NUMBER_LETTER.matcher(prompt);
        if (m.find()) {
            int x = columnToNumber(m.group(1));
            int y = rowToNumber(m.group(2));
            game.getBoard().setPosition(x, y, Tile.X);
            return;
        }

        // Letter and then a number (ex. A1)
        m = Prompt.LETTER_NUMBER.matcher(prompt);
        if (m.find()) {
            int y = rowToNumber(m.group(1));
            int x = columnToNumber(m.group(2));
            game.getBoard().setPosition(x, y, Tile.X);
            return;
        }
    }

    private int rowToNumber(String row) {
        switch (row) {
            case "a":
            case "A":
                return 0;
            case "b":
            case "B":
                return 1;
            case "c":
            case "C":
                return 2;
            default:
                return 0;
        }
    }

    private int columnToNumber(String col) {
        switch (col) {
            case "1":
                return 0;
            case "2":
                return 1;
            case "3":
                return 2;
            default:
                return 0;
        }
    }

    public String getPrompt() {
        return prompt;
    }

    public void clear() {
        prompt = "";
    }

    public void add(Character c) {
        prompt += c;
    }

    public void backspace() {
        if (prompt.length() > 0) {
            prompt = prompt.substring(0, prompt.length() - 1);
        }
    }
}
