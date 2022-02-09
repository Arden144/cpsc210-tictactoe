package ui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Game;

class Prompt {
    private static final Pattern LETTER_NUMBER = Pattern.compile("([a-cA-C])([1-3])");
    private static final Pattern NUMBER_LETTER = Pattern.compile("([1-3])([a-cA-C])");
    private static final Pattern QUIT = Pattern.compile("(?i)quit");

    private String prompt;
    private final Game game;

    public Prompt(Game game) {
        this.game = game;
        prompt = "";
    }

    private boolean checkNumberLetter() {
        Matcher m = Prompt.NUMBER_LETTER.matcher(prompt);
        if (m.find()) {
            int x = columnToNumber(m.group(1));
            int y = rowToNumber(m.group(2));
            game.addTile(x, y);
            return true;
        }
        return false;
    }

    private boolean checkLetterNumber() {
        Matcher m = Prompt.LETTER_NUMBER.matcher(prompt);
        if (m.find()) {
            int y = rowToNumber(m.group(1));
            int x = columnToNumber(m.group(2));
            game.addTile(x, y);
            return true;
        }
        return false;
    }

    private boolean checkQuit() {
        Matcher m = Prompt.QUIT.matcher(prompt);
        if (m.find()) {
            game.quit();
            return true;
        }
        return false;
    }

    public void run() {
        if (checkNumberLetter()) {
            //
        } else if (checkLetterNumber()) {
            //
        } else if (checkQuit()) {
            //
        }
        prompt = "";
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

    public void add(Character c) {
        prompt += c;
    }

    public void backspace() {
        if (prompt.length() > 0) {
            prompt = prompt.substring(0, prompt.length() - 1);
        }
    }

    public int getWidth() {
        return 2 + prompt.length();
    }

    @Override
    public String toString() {
        return prompt;
    }
}
