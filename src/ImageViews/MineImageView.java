package ImageViews;

import Game.Main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MineImageView extends BuildingImageView {
    public MineImageView(String src) {
        super(src);
        nFreeTile = new int[]{6, 7, 8, 19, 34, 38, 39, 40, 41, 45, 46, 47, 48, 49, 50, 65, 66, 69, 70, 71, 72, 73, 74, 76,
                77, 78, 79, 80, 81, 82, 97, 98, 99, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114,
                129, 130, 131, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 161, 162, 163, 164, 165, 169,
                170, 171, 175, 176, 177, 193, 194, 195, 196, 197, 201, 202, 203, 224, 225, 226, 227, 232, 233, 234, 235,
                241, 242, 243, 256, 257, 258, 259, 260, 261, 264, 265, 266, 267, 270, 271, 272, 273, 274, 275, 288, 289,
                290, 291, 296, 297, 298, 299, 305, 306, 307, 320, 321, 322, 323, 328, 329, 330, 337, 338, 339, 352, 353,
                354, 355, 360, 361, 362, 369, 370, 371, 384, 385, 386, 387, 392, 393, 394, 403, 417, 418, 419, 424, 425,
                426, 832, 833, 834, 835, 836, 837, 838, 864, 865, 866, 867, 868, 869, 870, 896, 897, 898, 899, 900, 901,
                902, 928, 929, 930, 931, 932, 933, 934, 960, 961, 962, 963, 964, 965, 966, 993, 994, 995, 996, 997, 998};
    }
//    public List<TileImageView> getFreeTiles(int i, int j) {
//        List<TileImageView> freeTiles = null;
//        if (isFreeLand(i, j)) {
//            freeTiles = new ArrayList<>();
//            for (int m = i - 1; m >= 0 && m < 60 && m < i + 3; m++) {
//                for (int k = j - 1; k >= 0 && k < 60 && k < j + 3; k++) {
//                    TileImageView img = (TileImageView) Main.getGame().getNodeByRowColumnIndex(m, k);
//                    if (!isFreeLand(img.getI(), img.getJ()))
//                        return null;
//                    freeTiles.add(img);
//                }
//            }
//        }
//        return freeTiles;
//    }


}
