package Game;

import ImageViews.BuildingImageView;
import ImageViews.HarborImageView;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;


public class Graphic implements Runnable {
    private GridPane grid;
    private ScrollPane scrollPane;
    private final double SCALE_DELTA = 1.05;
    private BuildingImageView dragImage;
    private final Double ScreenMovingSpeed = 0.05;

    Graphic(GridPane grid) {
        this.grid = grid;
        setGridScrollListener();
        setGridMouseClickListener();
        dragImage = null;
        setMouseMoveOnNode(grid);
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
        grid.setOnScroll(event -> {
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
        });
    }

    public void setMouseMoveOnNode(Node node) {
        node.setOnMouseMoved(event -> {
            if (event.getSceneX() < 25) {
                scrollPane.setHvalue(scrollPane.getHvalue() - ScreenMovingSpeed);
            } else if (event.getSceneX() > Main.WIDTH - 50) {
                scrollPane.setHvalue(scrollPane.getHvalue() + ScreenMovingSpeed);
            }
            if (event.getSceneY() < 25) {
                scrollPane.setVvalue(scrollPane.getVvalue() - ScreenMovingSpeed);
            } else if (event.getSceneY() > Main.HEIGHT - 110) {
                scrollPane.setVvalue(scrollPane.getVvalue() + ScreenMovingSpeed);
            }
        });
    }

    GridPane getGrid() {
        return grid;
    }

    public void setDragImage(BuildingImageView dragImage) {
        this.dragImage = dragImage;
    }

    public BuildingImageView getDragImage() {
        return dragImage;
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

    public void createBuilding(int i, int j) {
        BuildingKind kind = null;
        dragImage.disableBuilding();
        ImageView newImage = new ImageView(dragImage.getImage());
        grid.add(newImage, j, i, 4, 4);
        if (dragImage instanceof BuildingImageView) {
            CastleBuilding castle = new CastleBuilding(100, j, i, BuildingKind.Castle, newImage);
            kind = BuildingKind.Castle;
        }
        dragImage = null;
        Main.getGame().getServerListener().sendCommand("building", Main.getGame().getThisPlayer().getID(), i, j, kind.getValue());
    }

    public void createBuilding(int i, int j, BuildingKind kind) {
        grid.add(kind.getImageView(), j, i, 4, 4);
    }

    public void createBuilding(int i, int j, int kind) {
        System.out.println("HERE");
        grid.add(BuildingKind.getInstanceByKind(kind).getImageView(), j, i, 4, 4);
    }

    public boolean isDragging() {
        return dragImage != null;
    }

    ScrollPane createScrollPane() {
        scrollPane = new ScrollPane(grid);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setFitToWidth(true);
        return scrollPane;
    }
   public void createPerson() throws InterruptedException {
       Person p=new Person();
p.move(grid);

   }

    public ScrollPane getScrollPane() {
        return scrollPane;
    }

    @Override
    public void run() {
        try{
        createPerson();

        }catch (InterruptedException e){
            System.out.println(e);
        }
    }
}
