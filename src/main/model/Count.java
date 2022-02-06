package model;

public class Count {
    private int x;
    private int o;

    public Count() {
        this.x = 0;
        this.o = 0;
    }

    public boolean checkMoveForWin(Tile v) {
        if (v == Tile.X) {
            x++;
            return x == 3;
        } else if (v == Tile.O) {
            o++;
            return o == 3;
        }
        return false;
    }
}
