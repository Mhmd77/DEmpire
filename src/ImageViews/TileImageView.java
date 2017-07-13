package ImageViews;

import Game.Main;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;

import java.lang.reflect.Array;
import java.util.List;

public class TileImageView extends ImageView {
    private int i;
    private int j;

    public TileImageView(Image src, int i, int j) {
        super(src);
        this.i = i;
        this.j = j;
        setDragEntered();
        setDragExited();
        setDragReleased();
        mouseClicked();

    }

    public void mouseClicked() {
        this.setOnMouseClicked(event -> {
            if (Main.getGame().getGraphic().getSelectedPerson() != null
                    && Main.getGame().getGraphic().getSelectedPerson().isRoamEnded()&& event.getButton() == MouseButton.PRIMARY) {
                Main.getGame().getGraphic().getSelectedPerson().setRoamEnded(false);
                Main.getGame().getGraphic().getSelectedPerson().move(this.i, this.j);
            } else if (Main.getGame().getGraphic().getSelectedPerson() != null && event.getButton() == MouseButton.SECONDARY) {
                Main.getGame().getGraphic().setSelectedPerson(null);
                System.out.println("Person Unselected");
            }
        });

    }

    private void setDragReleased() {
        setOnMouseDragReleased(event -> {
            List<TileImageView> freeTiles = Main.getGame().getGraphic().getDragImage().getFreeTiles(i, j);
            if (freeTiles != null) {
                Main.getGame().getGraphic().createBuilding(i - 1, j - 1);
                for (TileImageView img : freeTiles)
                    img.setStyle("-fx-opacity: 1");
            }
        });
    }


    private void setDragExited() {
        setOnMouseDragExited(event -> {
            if (Main.getGame().getGraphic().isDragging()) {
                List<TileImageView> freeTiles = Main.getGame().getGraphic().getDragImage().getFreeTiles(i, j);
                if (freeTiles != null)
                    for (TileImageView img : freeTiles)
                        img.setStyle("-fx-opacity: 1");
            }
        });
    }

    private void setDragEntered() {
        setOnMouseDragEntered(event -> {
            List<TileImageView> freeTiles = Main.getGame().getGraphic().getDragImage().getFreeTiles(i, j);
            if (freeTiles != null)
                for (TileImageView img : freeTiles)
                    img.setStyle("-fx-opacity: 0.5");
        });
    }

    public int getJ() {
        return j;
    }

    public int getI() {
        return i;
    }
}
