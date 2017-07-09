package ImageViews;

import Game.Main;

import java.util.ArrayList;
import java.util.List;

public class HarborImageView extends BuildingImageView {
    public HarborImageView(String src) {
        super(src);
        nFreeTile = new int[]{598, 599, 600, 601, 602, 603, 758, 759, 760, 761, 762, 763, 630, 635, 662, 667, 694, 699, 726, 731};
    }

    public List<TileImageView> getFreeTiles(int i, int j) {
        List<TileImageView> freeTiles = null;
        if (isFreeLand(i, j)) {
            freeTiles = new ArrayList<>();
            for (int m = i - 1; m >= 0 && m < 60 && m < i + 3; m++) {
                for (int k = j - 1; k >= 0 && k < 60 && k < j + 3; k++) {
                    TileImageView img = (TileImageView) Main.getGame().getGraphic().getNodeByRowColumnIndex(m, k);
                    freeTiles.add(img);
                }
            }
        }
        return freeTiles;
    }
}
