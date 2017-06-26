import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class BuildingImageView extends ImageView {
    public BuildingImageView(String src) {
        super(src);
        setMouseMethods();
    }

    private void setMouseMethods() {
        ImageView img = this;
        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setCursor(Cursor.OPEN_HAND);
            }
        });
        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setCursor(Cursor.CLOSED_HAND);
                event.setDragDetect(true);
                Main.getGraphic().setDragImage((BuildingImageView) img);
            }
        });
        setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                event.setDragDetect(false);
                setCursor(Cursor.MOVE);
            }
        });
        setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                startFullDrag();
            }
        });
    }

    void disableBuilding() {
        setMouseTransparent(true);
        setStyle("-fx-opacity: 30%");
    }

}
