import java.io.File;
import java.util.ArrayList;

public class Game {
    private int[][] world = new int[16][16];
    private ArrayList<Player> players;
    private File mapFile;

    Game() {
        players = new ArrayList<>();
    }

    void addPlayer(Player player) {
        this.players.add(player);
    }

}
