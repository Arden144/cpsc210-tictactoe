package ui;

import java.io.IOException;

import model.Game;
import persistence.Codable;
import persistence.Persist;

public class Main {
    public static void main(String[] args) throws IOException {
        TicTacToe game;
        try {
            Game oldGame = Codable.decode(Persist.load(), Game.class);
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
