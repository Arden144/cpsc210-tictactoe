package persistence;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Persist {
    public static void write(String data) throws IOException {
        Files.write(Paths.get("./data/saved.json"), data.getBytes());
    }

    public static String read() throws IOException {
        return new String(Files.readAllBytes(Paths.get("./data/saved.json")));
    }
}
