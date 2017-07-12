package Game;

public class Tiles {
    int i;
    int j;
    int id;
    int g;
    double h;
    double x;
    double y;
    boolean setRock = false;

    @Override
    public boolean equals(Object obj) {
        return this.i == ((Tiles) obj).i && this.j == ((Tiles) obj).j;
    }
}
