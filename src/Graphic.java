import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.GridPane;


public class Graphic {
    private GridPane grid;
    private final double SCALE_DELTA = 1.05;
    private BuildingImageView dragImage;

    Graphic(GridPane grid) {
        this.grid = grid;
        setGridScrollListener();
        setGridMouseClickListener();
        dragImage = null;
    }

    private void setGridMouseClickListener() {
        grid.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    grid.setScaleX(1.0);
                    grid.setScaleY(1.0);
                }
            }
        });
    }

    private void setGridScrollListener() {
        grid.setOnScroll(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                if (event.getDeltaY() == 0) {
                    return;
                }

                double scaleFactor =
                        (event.getDeltaY() > 0)
                                ? SCALE_DELTA
                                : 1 / SCALE_DELTA;
                if (grid.getScaleX() < 0.65 && event.getDeltaY() < 0) ;
                else if (grid.getScaleX() > 1.1 && event.getDeltaY() > 0) ;
                else {
                    grid.setScaleX(grid.getScaleX() * scaleFactor);
                    grid.setScaleY(grid.getScaleY() * scaleFactor);
                }
                event.consume();
            }
        });
    }

    GridPane getGrid() {
        return grid;
    }

    void setDragImage(BuildingImageView dragImage) {
        this.dragImage = dragImage;
    }

    public Node getNodeByRowColumnIndex(final int row, final int column) {
        Node result = null;
        ObservableList<Node> children = grid.getChildren();
        for (Node node : children) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }

        return result;
    }

    void addElementToGrid(int i, int j, boolean isFree) {
        if (isFree) {
            dragImage.disableBuilding();
            grid.add(new ImageView(dragImage.getImage()), j, i, 4, 4);
        }
        dragImage = null;
    }
}
