package ImageViews;

import Game.MapLoader;
import Game.Main;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class BuildingImageView extends ImageView {
    int[] nFreeTile;

    public BuildingImageView(String src) {
        super(src);
        nFreeTile = new int[]{0, 1, 32, 33};
        setMouseMethods();
        HBox.setMargin(this, new Insets(0, 10, 0, 0));
    }

    private void setMouseMethods() {
        ImageView img = this;
        setOnMouseEntered(event -> setCursor(Cursor.OPEN_HAND));
        setOnMousePressed(event -> {
            setCursor(Cursor.CLOSED_HAND);
            event.setDragDetect(true);
            Main.getGame().getGraphic().setDragImage((BuildingImageView) img);
        });
        setOnMouseDragged(event -> {
            event.setDragDetect(false);
            setCursor(Cursor.MOVE);
        });
        setOnDragDetected(event -> startFullDrag());
    }

    public void disableBuilding() {
        setMouseTransparent(true);
        setStyle("-fx-opacity: 30%");
    }

    boolean isFreeLand(int i, int j) {
        int[][] world = MapLoader.getWorld();
        for (int x :
                nFreeTile)
            if (world[i][j] == x) return true;
        return false;
    }

    public List<TileImageView> getFreeTiles(int i, int j) {
        List<TileImageView> freeTiles = null;
        if (i > 57 || j > 77)
            return null;
        if (isFreeLand(i, j)) {
            freeTiles = new ArrayList<>();
            for (int m = i - 1; m >= 0 && m < 60 && m < i + 3; m++) {
                for (int k = j - 1; k >= 0 && k < 80 && k < j + 3; k++) {
                    TileImageView img = (TileImageView) Main.getGame().getGraphic().getNodeByRowColumnIndex(m, k);
                    if (!isFreeLand(img.getI(), img.getJ()))
                        return null;
                    freeTiles.add(img);
                }
            }
        }
        return freeTiles;
    }

}
