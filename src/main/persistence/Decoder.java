package persistence;

import java.util.Stack;

import org.json.JSONObject;

import model.Game;
import model.Move;
import model.Tile;

// Decodes a JSON object into a game object.
public class Decoder {
    // EFFECTS: Returns a move from the given JSON object.
    private static Move decodeMove(JSONObject json) {
        return new Move(json.getInt("posX"), json.getInt("posY"), Tile.valueOf(json.getString("tile")));
    }

    // EFFECTS: Returns a game from the given JSON object.
    public static Game decodeGame(JSONObject json) {
        Stack<Move> moves = new Stack<>();
        for (Object o : json.getJSONArray("moves")) {
            moves.push(decodeMove((JSONObject) o));
        }
        return new Game(moves, json.getBoolean("ended"));
    }
}
