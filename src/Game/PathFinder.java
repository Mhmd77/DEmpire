package Game;

import java.util.ArrayList;

class PathFinder {
    private ArrayList<Tiles> closeList;
    private ArrayList<Tiles> openList;

    PathFinder() {
        openList = new ArrayList<Tiles>();
        closeList = new ArrayList<Tiles>();
    }

    ArrayList<Tiles> findPath(int i, int j, int iGoal, int jGoal, int id) {
        if (isFreeLand(iGoal, jGoal) == 1)//It's Not Free
            return null;
        else if (isFreeLand(iGoal, jGoal) == 2 && !Main.getGame().getPlayer(id).isClimbing())
            return null;
        Tiles[][] tiles = new Tiles[80][100];
        int world[][] = MapLoader.getWorld();
        for (int k = 0; k < 80; k++) {
            for (int l = 0; l < 100; l++) {
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
        int[] nFreeTile = new int[]{0, 1, 32, 33, 21, 30, 31, 53, 62, 63, 85, 94, 95, 117, 126, 127, 149, 158, 159, 190, 191, 214, 215, 216, 217,
                218, 219, 222, 223, 246, 247, 248, 249, 250, 251, 254, 255, 278, 279, 280, 281, 282, 283, 310, 311, 312, 313, 314, 315, 342,
                343, 344, 345, 346, 347, 374, 375, 376, 377, 378, 379, 590, 591, 622, 623};

        int[] nMineTile = new int[]{2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50
                , 51, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108
                , 109, 110, 111, 112, 113, 114, 115, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 160, 161
                , 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202
                , 203, 204, 205, 206, 207, 208, 209, 210, 211, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243
                , 256, 257, 258, 259, 260, 261, 262, 263, 264, 265, 266, 267, 268, 269, 270, 271, 272, 273, 274, 275, 288, 289, 290, 291, 292, 293, 294, 295, 296
                , 297, 298, 299, 300, 301, 302, 303, 304, 305, 306, 307, 320, 321, 322, 323, 324, 325, 326, 327, 328, 329, 330, 331, 332, 333, 334, 335, 336, 337
                , 338, 339, 352, 353, 354, 355, 356, 357, 358, 359, 360, 361, 362, 363, 364, 365, 366, 367, 368, 369, 370, 371, 384, 385, 386, 387, 388, 389, 390
                , 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 416, 417, 418, 419, 420, 421, 422, 423, 424, 425, 426, 427, 428, 429, 430, 431
                , 432, 433, 434, 435, 448, 449, 480, 481, 512, 513, 544, 545, 576, 577, 608, 609, 640, 790, 791, 792, 793, 822, 823, 824, 825, 832, 833, 834, 835
                , 836, 837, 838, 854, 855, 856, 857, 864, 865, 866, 867, 868, 869, 870, 886, 887, 888, 889, 896, 897, 898, 899, 900, 901, 902, 920, 921, 928, 929
                , 930, 931, 932, 933, 934, 952, 953, 960, 961, 962, 963, 964, 965, 993, 994, 995, 996, 997};
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

        if (i <= 78 && j >= 1) {
            if (isFreeLand(i + 1, j - 1) == 0) {
                result.add(tiles[i + 1][j - 1]);
            } else if (isFreeLand(i + 1, j - 1) == 2 && Main.getGame().getPlayer(id).isClimbing()) {
                result.add(tiles[i + 1][j - 1]);
            }
        }

        if (i <= 78 && j >= 1) {
            if (isFreeLand(i + 1, j) == 0) {
                result.add(tiles[i + 1][j]);
            } else if (isFreeLand(i + 1, j) == 2 && Main.getGame().getPlayer(id).isClimbing()) {
                result.add(tiles[i + 1][j]);
            }
        }
        if (i <= 58 && j <= 98) {
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

        if (i >= 1 && j <= 98) {
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
