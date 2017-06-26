import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;

import java.util.ArrayList;
import java.util.List;

public class TileImageView extends ImageView {
    private int i;
    private int j;

    TileImageView(String src, int i, int j) {
        super(src);
        this.i = i;
        this.j = j;
        setDragEntered();
        setDragExited();
        setDragReleased();
    }

    TileImageView(Image src, int i, int j) {
        super(src);
        this.i = i;
        this.j = j;
        setDragEntered();
        setDragExited();
        setDragReleased();
    }

    private void setDragReleased() {
        setOnMouseDragReleased(new EventHandler<MouseDragEvent>() {
            @Override
            public void handle(MouseDragEvent event) {
                boolean isFree = true;
                for (int m = i - 1; m >= 0 && m < 60 && m < i + 3 && isFree; m++) {
                    for (int k = j - 1; k > 0 && k < 60 && k < j + 3 && isFree; k++) {
                        TileImageView img = (TileImageView) Main.getGraphic().getNodeByRowColumnIndex(m, k);
                        if (!MapLoader.isFree(img.getI(), img.getJ()))
                            isFree = false;
                    }
                }
                Main.getGraphic().addElementToGrid(i - 1, j - 1, isFree);
            }
        });
    }

    private void setDragExited() {
        setOnMouseDragExited(new EventHandler<MouseDragEvent>() {
            @Override
            public void handle(MouseDragEvent event) {
                if (MapLoader.isFree(i, j)) {
                    setStyle("-fx-opacity: 1");
                    for (int m = i - 1; m >= 0 && m < 60 && m < i + 3; m++) {
                        for (int k = j - 1; k > 0 && k < 60 && k < j + 3; k++) {
                            ImageView img = (ImageView) Main.getGraphic().getNodeByRowColumnIndex(m, k);
                            if (img != null)
                                img.setStyle("-fx-opacity: 1");
                        }
                    }
                }
            }
        });
    }

    private void setDragEntered() {
        TileImageView thisImg = this;
        setOnMouseDragEntered(new EventHandler<MouseDragEvent>() {
            @Override
            public void handle(MouseDragEvent event) {
                if (MapLoader.isFree(i, j)) {
                    List<TileImageView> images = new ArrayList<>();
                    if (!(i < 2 || i > 58 || j < 2 || j > 58))
                        images.add(thisImg);
                    for (int m = i - 1; m >= 0 && m < 60 && m < i + 3; m++) {
                        for (int k = j - 1; k > 0 && k < 60 && k < j + 3; k++) {
                            TileImageView img = (TileImageView) Main.getGraphic().getNodeByRowColumnIndex(m, k);
                            if (!MapLoader.isFree(img.getI(), img.getJ())) {
                                System.out.println("OUT OF RANGE");
                                return;
                            }
                            images.add(img);
                        }
                    }
                    if (images.size() < 16)
                        System.out.println("HERE" + images.size());
                    for (TileImageView img : images)
                        img.setStyle("-fx-opacity: 0.5");
                }
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
