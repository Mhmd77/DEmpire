package Game;

import java.util.ArrayList;

public class PathFinder {
    private ArrayList<Tiles> closeList;
    private ArrayList<Tiles> openList;

    PathFinder() {
        openList = new ArrayList<Tiles>();
        closeList = new ArrayList<Tiles>();
    }

    public ArrayList<Tiles> findPath(int i, int j, int iGoal, int jGoal) {
        return findPath(i, j, iGoal, jGoal, Main.getGame().getThisPlayer().getID());
    }

    public ArrayList<Tiles> findPath(int i, int j, int iGoal, int jGoal, int id) {
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
        int g;
        closeList = new ArrayList<>();
        openList = new ArrayList<>();
        Tiles thisNode = tiles[i][j];
        openList.add(thisNode);
        while (!openList.isEmpty()) {
            thisNode = findMin(openList);
            if (closeList.contains(tiles[iGoal][jGoal]))
                break;
            closeList.add(thisNode);
            openList.remove(thisNode);
            g = thisNode.g + 1;
            for (Tiles t : getAdj(tiles, thisNode.i, thisNode.j, id)) {
                t.h = calculateH(t, tiles[iGoal][jGoal]);
                t.g = g;
                if (closeList.contains(t)) {
                    continue;
                }
                if (!openList.contains(t)) {
                    openList.add(t);
                }
            }
        }
        closeList.add(tiles[iGoal][jGoal]);
        closeList.remove(closeList.size() - 1);
        return closeList;
    }

    private int isFreeLand(int i, int j) {
        int[] nFreeTile = new int[]{0, 1, 32, 33};
        int[] nMineTile = new int[]{6, 7, 8, 19, 34, 38, 39, 40, 41, 45, 46, 47, 48, 49, 50, 65, 66, 69, 70, 71, 72, 73, 74, 76,
                77, 78, 79, 80, 81, 82, 97, 98, 99, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114,
                129, 130, 131, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 161, 162, 163, 164, 165, 169,
                170, 171, 175, 176, 177, 193, 194, 195, 196, 197, 201, 202, 203, 224, 225, 226, 227, 232, 233, 234, 235,
                241, 242, 243, 256, 257, 258, 259, 260, 261, 264, 265, 266, 267, 270, 271, 272, 273, 274, 275, 288, 289,
                290, 291, 296, 297, 298, 299, 305, 306, 307, 320, 321, 322, 323, 328, 329, 330, 337, 338, 339, 352, 353,
                354, 355, 360, 361, 362, 369, 370, 371, 384, 385, 386, 387, 392, 393, 394, 403, 417, 418, 419, 424, 425,
                426, 832, 833, 834, 835, 836, 837, 838, 864, 865, 866, 867, 868, 869, 870, 896, 897, 898, 899, 900, 901,
                902, 928, 929, 930, 931, 932, 933, 934, 960, 961, 962, 963, 964, 965, 966, 993, 994, 995, 996, 997, 998};
        int world[][] = MapLoader.getWorld();
        for (int x :
                nFreeTile)
            if (world[i][j] == x) return 0;

        for (int y :
                nMineTile) {
            if (world[i][j] == y) return 2;

        }
        return 1;

    }

    private double calculateH(Tiles first, Tiles goal) {
        double x1 = (first.j - goal.j) * (first.j - goal.j);
        double x2 = (first.i - goal.i) * (first.i - goal.i);
        return Math.sqrt(x1 + x2);

    }

    private Tiles findMin(ArrayList<Tiles> tiles) {
        Tiles min = tiles.get(tiles.size() - 1);
        for (Tiles tile : tiles)
            if (tile.h < min.h)
                min = tile;
        return min;
    }

    private ArrayList<Tiles> getAdj(Tiles[][] tiles, int i, int j, int id) {
        ArrayList<Tiles> result = new ArrayList<Tiles>();
        if (i >= 1 && j >= 1) {
            if (isFreeLand(i - 1, j - 1) == 2 && Main.getGame().getPlayer(id).isClimbing()) {
                result.add(tiles[i - 1][j - 1]);
            } else if (isFreeLand(i - 1, j - 1) == 0) {
                result.add(tiles[i - 1][j - 1]);
            }
        }
        if (j >= 1) {
            if (isFreeLand(i, j - 1) == 0) {
                result.add(tiles[i][j - 1]);
            } else if (isFreeLand(i, j - 1) == 2 && Main.getGame().getPlayer(id).isClimbing()) {
                result.add(tiles[i][j - 1]);
            }
        }

        if (i <= 58 && j >= 1) {
            if (isFreeLand(i + 1, j - 1) == 0) {
                result.add(tiles[i + 1][j - 1]);
            } else if (isFreeLand(i + 1, j - 1) == 2 && Main.getGame().getPlayer(id).isClimbing()) {
                result.add(tiles[i + 1][j - 1]);
            }
        }

        if (i <= 58 && j >= 1) {
            if (isFreeLand(i + 1, j) == 0) {
                result.add(tiles[i + 1][j]);
            } else if (isFreeLand(i + 1, j) == 2 && Main.getGame().getPlayer(id).isClimbing()) {
                result.add(tiles[i + 1][j]);
            }
        }
        if (i <= 58 && j <= 78) {
            if (isFreeLand(i + 1, j + 1) == 0) {
                result.add(tiles[i + 1][j + 1]);
            } else if (isFreeLand(i + 1, j + 1) == 2 && Main.getGame().getPlayer(id).isClimbing()) {
                result.add(tiles[i + 1][j + 1]);

            }
        }

        if (j <= 78) {
            if (isFreeLand(i, j + 1) == 0) {
                result.add(tiles[i][j + 1]);
            } else if (isFreeLand(i, j + 1) == 2 && Main.getGame().getPlayer(id).isClimbing()) {
                result.add(tiles[i][j + 1]);
            }
        }

        if (i >= 1 && j <= 78) {
            if (isFreeLand(i - 1, j + 1) == 0) {
                result.add(tiles[i - 1][j + 1]);
            } else if (isFreeLand(i - 1, j + 1) == 2 && Main.getGame().getPlayer(id).isClimbing()) {
                result.add(tiles[i - 1][j + 1]);
            }
        }
        if (i >= 1) {
            if (isFreeLand(i - 1, j) == 0) {
                result.add(tiles[i - 1][j]);
            } else if (isFreeLand(i - 1, j) == 2 && Main.getGame().getPlayer(id).isClimbing()) {
                result.add(tiles[i - 1][j]);
            }
        }
        return result;
    }
}
