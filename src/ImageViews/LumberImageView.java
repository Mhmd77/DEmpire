package ImageViews;

import java.util.List;

public class LumberImageView extends BuildingImageView {

    public LumberImageView(String src) {
        super(src);
        nFreeTile = new int[]{
                776, 777, 778, 779, 780, 781, 786, 787, 788, 789, 808, 809, 810, 811, 812, 813, 818, 819, 820,
                821, 840, 841, 842, 843, 844, 845, 846, 847, 848, 849, 850, 851, 852, 853, 872, 873, 874, 875,
                876, 877, 878, 879, 880, 881, 882, 883, 884, 885, 904, 905, 906, 907, 908, 909, 910, 911, 912,
                913, 914, 915, 916, 917, 936, 937, 938, 939, 940, 941, 942, 943, 944, 945, 946, 947, 948, 949,
                968, 969, 970, 971, 972, 973, 974, 975, 976, 977, 978, 979, 980, 981, 1000, 1001, 1002, 1003,
                1004, 1005, 1006, 1009, 1010, 1011, 1012, 1013
        };
    }

    @Override
    public List<TileImageView> getFreeTiles(int i, int j) {
        return super.getFreeTiles(i, j);
    }
}
