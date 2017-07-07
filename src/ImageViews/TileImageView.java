package ImageViews;

import Game.Main;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;

import java.lang.reflect.Array;
import java.util.List;

public class TileImageView extends ImageView {
    private int i;
    private int j;
    PersonImageView cameFrom;

    TileImageView(String src, int i, int j) {
        super(src);
        this.i = i;
        this.j = j;
        setDragEntered();
        setDragExited();
        setDragReleased();
        mouseClicked();
        cameFrom = new PersonImageView();
    }

    public TileImageView(Image src, int i, int j) {
        super(src);
        this.i = i;
        this.j = j;
        setDragEntered();
        setDragExited();
        setDragReleased();
        // mouseClicked();
        cameFrom = new PersonImageView();

    }

    public Integer[] mouseClicked() {
        Integer[] loc = new Integer[2];
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                loc[0] = i;
                loc[1] = j;

            }
        });
        return loc;
    }

    private void setDragReleased() {
        setOnMouseDragReleased(new EventHandler<MouseDragEvent>() {
            @Override
            public void handle(MouseDragEvent event) {
                List<TileImageView> freeTiles = Main.getGame().getGraphic().getDragImage().getFreeTiles(i, j);
                if (freeTiles != null) {
                    Main.getGame().getGraphic().createBuilding(i - 1, j - 1);
                    for (TileImageView img : freeTiles)
                        img.setStyle("-fx-opacity: 1");
                }
            }
        });
    }


    private void setDragExited() {
        setOnMouseDragExited(new EventHandler<MouseDragEvent>() {
            @Override
            public void handle(MouseDragEvent event) {
                if (Main.getGame().getGraphic().isDragging()) {
                    List<TileImageView> freeTiles = Main.getGame().getGraphic().getDragImage().getFreeTiles(i, j);
                    if (freeTiles != null)
                        for (TileImageView img : freeTiles)
                            img.setStyle("-fx-opacity: 1");
                }
            }
        });
    }

    private void setDragEntered() {
        setOnMouseDragEntered(new EventHandler<MouseDragEvent>() {
            @Override
            public void handle(MouseDragEvent event) {
                List<TileImageView> freeTiles = Main.getGame().getGraphic().getDragImage().getFreeTiles(i, j);
                if (freeTiles != null)
                    for (TileImageView img : freeTiles)
                        img.setStyle("-fx-opacity: 0.5");
            }
        });
    }

    public int getJ() {
        return j;
    }

    public int getI() {
        return i;
    }
}
