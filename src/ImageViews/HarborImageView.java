package ImageViews;

import Game.Main;

import java.util.ArrayList;
import java.util.List;

public class HarborImageView extends BuildingImageView {
    public HarborImageView(String src) {
        super(src);
        nFreeTile = new int[]{598, 599, 600, 601,603, 630,635, 662, 663, 666, 667, 694, 695, 698, 699, 630, 726, 758,760,761,762};
    }

    public List<TileImageView> getFreeTiles(int i, int j) {
        List<TileImageView> freeTiles = null;
        if (isFreeLand(i, j)) {
            freeTiles = new ArrayList<>();
            for (int m = i - 1; m >= 0 && m < 80 && m < i + 3; m++) {
                for (int k = j - 1; k >= 0 && k < 100 && k < j + 3; k++) {
                    TileImageView img = (TileImageView) Main.getGame().getGraphic().getNodeByRowColumnIndex(m, k);
                    freeTiles.add(img);
                }
            }
        }
        return freeTiles;
    }
}
