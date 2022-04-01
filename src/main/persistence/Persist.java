package persistence;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;

// Saves data to the saved.json file
public class Persist {
    // EFFECTS: Saves the given game to the saved.json file
    public static void save(JSONObject json) throws IOException {
        Files.write(Paths.get("./data/saved.json"), json.toString().getBytes());
    }

    // EFFECTS: Returns a JSON object from the saved.json file
    public static JSONObject load() throws IOException {
        return new JSONObject(new String(Files.readAllBytes(Paths.get("./data/saved.json"))));
    }
}
