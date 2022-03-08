package persistence;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;

public class Persist {
    public static void save(JSONObject json) throws IOException {
        Files.write(Paths.get("./data/saved.json"), json.toString().getBytes());
    }

    public static JSONObject load() throws IOException {
        return new JSONObject(new String(Files.readAllBytes(Paths.get("./data/saved.json"))));
    }
}
