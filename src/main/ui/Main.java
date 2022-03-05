package ui;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.json.JSONException;
import org.json.JSONObject;

import model.Game;
import persistence.Codable;
import persistence.Persist;

public class Main {
    public static void main(String[] args)
            throws IOException, NoSuchMethodException, SecurityException, InstantiationException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException, JSONException {
        TicTacToe game;
        try {
            Game oldGame = Codable.decode(new JSONObject(Persist.read()), Game.class);
            if (oldGame.getState() == Game.State.End) {
                oldGame.setState(Game.State.Play);
            }
            game = new TicTacToe(oldGame);
        } catch (IOException e) {
            game = new TicTacToe();
        }
        game.start();
    }
}
