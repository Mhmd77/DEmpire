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
                t.x = j * 16;
                t.y = i * 16;
                tiles[k][l] = t;
            }
        }

        int g = 1;
        closeList = new ArrayList<>();
        openList = new ArrayList<>();
        closeList.add(tiles[i][j]);

        while (!closeList.contains(tiles[igoal][jgoal])) {
            if (openList.contains(tiles[igoal][jgoal])) {
                closeList.add(tiles[igoal][jgoal]);
            }
            if (i >= 1 && j >= 1 && (!openList.contains(tiles[i - 1][j - 1])) && (!closeList.contains(tiles[i - 1][j - 1])) && (isFreeLand(i - 1, j - 1))) {
                tiles[i - 1][j - 1].g = g;
                tiles[i - 1][j - 1].h = calculateH(tiles[i - 1][j - 1], tiles[igoal][jgoal]);
                openList.add(tiles[i - 1][j - 1]);
            }
            if (j >= 1 && (!openList.contains(tiles[i][j - 1])) && (!closeList.contains(tiles[i][j - 1])) && (isFreeLand(i, j - 1))) {
                tiles[i][j - 1].g = g;
                tiles[i][j - 1].h = calculateH(tiles[i][j - 1], tiles[igoal][jgoal]);
                openList.add(tiles[i][j - 1]);
            }
            if (i <= 58 && j >= 1 && (!openList.contains(tiles[i + 1][j - 1])) && (!closeList.contains(tiles[i + 1][j - 1])) && (isFreeLand(i + 1, j - 1))) {
                tiles[i + 1][j - 1].g = g;
                tiles[i + 1][j - 1].h = calculateH(tiles[i + 1][j - 1], tiles[igoal][jgoal]);
                openList.add(tiles[i + 1][j - 1]);
            }
            if (i <= 58 && j >= 1 && (!openList.contains(tiles[i + 1][j])) && (!closeList.contains(tiles[i + 1][j])) && (isFreeLand(i + 1, j))) {
                tiles[i + 1][j].g = g;
                tiles[i + 1][j].h = calculateH(tiles[i + 1][j], tiles[igoal][jgoal]);
                openList.add(tiles[i + 1][j]);
            }
            if (i <= 58 && j <= 58 && (!openList.contains(tiles[i + 1][j + 1])) && (!closeList.contains(tiles[i + 1][j + 1])) && (isFreeLand(i + 1, j + 1))) {
                tiles[i + 1][j + 1].g = g;
                tiles[i + 1][j + 1].h = calculateH(tiles[i + 1][j + 1], tiles[igoal][jgoal]);
                openList.add(tiles[i + 1][j + 1]);
            }
            if (j <= 78 && (!openList.contains(tiles[i][j + 1])) && (!closeList.contains(tiles[i][j + 1])) && (isFreeLand(i, j + 1))) {
                tiles[i][j + 1].g = g;
                tiles[i][j + 1].h = calculateH(tiles[i][j + 1], tiles[igoal][jgoal]);
                openList.add(tiles[i][j + 1]);
            }
            if (i >= 1 && j <= 78 && (!openList.contains(tiles[i - 1][j + 1])) && (!closeList.contains(tiles[i - 1][j + 1])) && (isFreeLand(i - 1, j + 1))) {
                tiles[i - 1][j + 1].g = g;
                tiles[i - 1][j + 1].h = calculateH(tiles[i - 1][j + 1], tiles[igoal][jgoal]);
                openList.add(tiles[i - 1][j + 1]);
            }
            if (i >= 1 && (!openList.contains(tiles[i - 1][j])) && (!closeList.contains(tiles[i - 1][j])) && (isFreeLand(i - 1, j))) {
                tiles[i - 1][j].g = g;
                tiles[i - 1][j].h = calculateH(tiles[i - 1][j], tiles[igoal][jgoal]);
                openList.add(tiles[i - 1][j]);
            }
            Tiles min = findMinH(openList);
            closeList.add(min);
            openList.remove(min);
            i = min.i;
            j = min.j;
            g = min.g + 1;
        }

        for (int k = 0; k < closeList.size(); k++) {
            for (int l = k; l < closeList.size(); l++) {
                if ((closeList.get(k).g == closeList.get(l).g) && (closeList.get(k).h > closeList.get(l).h)) {
                    closeList.remove(l);
                }
            }
        }
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
        double h = Math.abs(first.j - goal.j) + Math.abs(first.i - goal.i);
    //    System.out.println(h);
        return h;

    }

    private Tiles findMinH(ArrayList<Tiles> tiles) {
        Tiles minH = tiles.get(0);

        for (int i = 0; i < tiles.size(); i++) {
            if (tiles.get(i).h < minH.h) {
                minH = tiles.get(i);
            }
        }
//        System.out.println(minH.i +"\t" +minH.j + "\t" + minH.h);
        return minH;
    }
}
