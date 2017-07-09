package ImageViews;

import java.util.List;

/**
 * Created by asus on 6/29/2017.
 */
public class LumberImageView extends BuildingImageView {

    public LumberImageView(String src) {
        super(src);
        nFreeTile = new int[]{748, 749, 776, 777, 778, 779, 780, 781, 808, 809, 810, 811, 812, 813, 840, 841,
                842, 843, 844, 845, 872, 873, 874, 875, 876, 877, 904, 905, 906, 907, 908, 909, 936, 937, 938,
                939, 940, 941, 968, 969, 970, 971, 972, 973, 1000, 1001, 1002, 1003, 1004, 1005};
    }

    @Override
    public List<TileImageView> getFreeTiles(int i, int j) {
        return super.getFreeTiles(i, j);
    }
}
