package model;

import java.io.Serializable;

public class Count implements Serializable {
    private int x;
    private int o;

    public Count() {
        this.x = 0;
        this.o = 0;
    }

    public boolean checkMoveForWin(Tile v) {
        if (v == Tile.X) {
            return ++x == 3;
        } else if (v == Tile.O) {
            return ++o == 3;
        }
        return false;
    }
}
