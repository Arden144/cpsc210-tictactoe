package ui;

import com.googlecode.lanterna.graphics.TextGraphics;

class Drawing {
    public static void placeText(TextGraphics tg, int x, int y, String message) {
        int i = 0;
        for (String line : message.split("\n")) {
            tg.putString(x, y + i++, line);
        }
    }
}
