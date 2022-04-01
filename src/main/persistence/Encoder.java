package persistence;

import org.json.JSONObject;

import model.Game;
import model.Move;

// Encodes a game object into a JSON object.
public class Encoder {
    // EFFECTS: Returns a JSON object from the given move.
    private static JSONObject encodeMove(Move move) {
        JSONObject json = new JSONObject();
        json.put("posX", move.getPosX());
        json.put("posY", move.getPosY());
        json.put("tile", move.getTile().toString());
        return json;
    }

    // EFFECTS: Returns a JSON object from the given game.
    public static JSONObject encodeGame(Game game) {
        JSONObject json = new JSONObject();
        json.put("moves", game.getMoves().stream().map(Encoder::encodeMove).toArray());
        json.put("ended", game.getEnded());
        return json;
    }
}
