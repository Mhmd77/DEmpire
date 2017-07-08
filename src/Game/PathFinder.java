package Game;

import java.util.ArrayList;

public class PathFinder {
    private ArrayList<Tiles> closeList;
    private ArrayList<Tiles> openList;

    PathFinder() {
        openList = new ArrayList<Tiles>();
        closeList = new ArrayList<Tiles>();
    }

    public ArrayList<Tiles> roam(int i, int j, int igoal, int jgoal) {
        Tiles[][] tiles = new Tiles[60][80];
        int world[][] = MapLoader.getWorld();
        for (int k = 0; k < 60; k++) {
            for (int l = 0; l < 80; l++) {
                Tiles t = new Tiles();
                t.id = world[k][l];
                t.i = k;
                t.j = l;
                tiles[k][l] = t;
            }
        }
        int g = 0;
        closeList = new ArrayList<>();
        openList = new ArrayList<>();
        Tiles thisNode = tiles[i][j];
        openList.add(thisNode);
        while (!openList.isEmpty()) {
            thisNode = findMin(openList);
            if (closeList.contains(tiles[igoal][jgoal]))
                break;
            closeList.add(thisNode);
            openList.remove(thisNode);
            i = thisNode.i;
            j = thisNode.j;
            g = thisNode.g + 1;
            for (Tiles t : getAdj(tiles, thisNode.i, thisNode.j)) {
                t.h = calculateH(t, tiles[igoal][jgoal]);
                t.g = g;
                if (closeList.contains(t)) {
                    continue;
                }
                if (!openList.contains(t)) {
                    openList.add(t);
                } /*else {

                }*/
            }
        }
        closeList.add(tiles[igoal][jgoal]);
        closeList.remove(closeList.size() - 1);
        return closeList;
    }

    private boolean isFreeLand(int i, int j) {
        int[] nFreeTile = new int[]{0, 1, 32, 33};
        int world[][] = MapLoader.getWorld();
        for (int x :
                nFreeTile)
            if (world[i][j] == x) return true;

        return false;
    }

    private double calculateH(Tiles first, Tiles goal) {
        double x1 = (first.j - goal.j) * (first.j - goal.j);
        double x2 = (first.i - goal.i) * (first.i - goal.i);
        return Math.sqrt(x1 + x2);

    }

    private Tiles findMin(ArrayList<Tiles> tiles) {
        Tiles min = tiles.get(tiles.size() - 1);
        for (Tiles tile : tiles)
            if (tile.h < min.h )
                min = tile;
        return min;
    }

    private ArrayList<Tiles> getAdj(Tiles[][] tiles, int i, int j) {
        ArrayList<Tiles> result = new ArrayList<Tiles>();
        if (i >= 1 && j >= 1 && isFreeLand(i - 1, j - 1)) {
            result.add(tiles[i - 1][j - 1]);
        }
        if (j >= 1 && isFreeLand(i, j - 1)) {
            result.add(tiles[i][j - 1]);
        }
        if (i <= 58 && j >= 1 && isFreeLand(i + 1, j - 1)) {
            result.add(tiles[i + 1][j - 1]);
        }
        if (i <= 58 && j >= 1 && isFreeLand(i + 1, j)) {
            result.add(tiles[i + 1][j]);
        }
        if (i <= 58 && j <= 78 && isFreeLand(i + 1, j + 1)) {
            result.add(tiles[i + 1][j + 1]);
        }
        if (j <= 78 && isFreeLand(i, j + 1)) {
            result.add(tiles[i][j + 1]);
        }
        if (i >= 1 && j <= 78 && isFreeLand(i - 1, j + 1)) {
            result.add(tiles[i - 1][j + 1]);
        }
        if (i >= 1 && isFreeLand(i - 1, j)) {
            result.add(tiles[i - 1][j]);
        }
        return result;
    }
}
