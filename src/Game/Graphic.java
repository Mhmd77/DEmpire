package Game;

import ImageViews.*;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;


public class Graphic {
    private Pane pane;
    private ScrollPane scrollPane;
    private final double SCALE_DELTA = 1.05;
    private BuildingImageView dragImage;
    private Person selectedPerson;
    private final Double ScreenMovingSpeed = 0.05;


    public Graphic(Pane pane) {
        this.pane = pane;
        setGridScrollListener();
        setPaneMouseClickListener();
        dragImage = null;
        setMouseMoveOnNode(pane);
    }

    private void setPaneMouseClickListener() {
        pane.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    pane.setScaleX(1.0);
                    pane.setScaleY(1.0);
                }
            }
        });
    }

    private void setGridScrollListener() {
        pane.setOnScroll(event -> {
            if (event.getDeltaY() == 0) {
                return;
            }

            double scaleFactor =
                    (event.getDeltaY() > 0)
                            ? SCALE_DELTA
                            : 1 / SCALE_DELTA;
            if (pane.getScaleX() < 0.55 && event.getDeltaY() < 0) ;
            else if (pane.getScaleX() > 1.1 && event.getDeltaY() > 0) ;
            else {
                pane.setScaleX(pane.getScaleX() * scaleFactor);
                pane.setScaleY(pane.getScaleY() * scaleFactor);
            }
            event.consume();
        });
    }

    public void setMouseMoveOnNode(Node node) {
        node.setOnMouseMoved(event -> {
            if (event.getSceneX() < 25) {
                scrollPane.setHvalue(scrollPane.getHvalue() - ScreenMovingSpeed);
            } else if (event.getSceneX() > Main.WIDTH + 9.2 - 50) {
                scrollPane.setHvalue(scrollPane.getHvalue() + ScreenMovingSpeed / 2);
            }
            if (event.getSceneY() < 70 + 25) {
                scrollPane.setVvalue(scrollPane.getVvalue() - ScreenMovingSpeed);
            } else if (event.getScreenY() > 678 - 50) {
                scrollPane.setVvalue(scrollPane.getVvalue() + ScreenMovingSpeed);
            }
        });
    }

    void add(Node node, int x, int y) {
        node.setLayoutX(x * 16);
        node.setLayoutY(y * 16);
        pane.getChildren().add(node);
    }

    public void setDragImage(BuildingImageView dragImage) {
        this.dragImage = dragImage;
    }

    public BuildingImageView getDragImage() {
        return dragImage;
    }

    public void createBuilding(int i, int j) {
        BuildingKind kind = null;
        dragImage.disableBuilding();
        ImageView newImage = new ImageView(dragImage.getImage());
        add(newImage, j, i);
        if (dragImage instanceof HarborImageView) {
            HarborBuilding harbor = new HarborBuilding(100, j, i, BuildingKind.Harbor, newImage);
            Main.getGame().getThisPlayer().addBuilding(harbor);
            kind = BuildingKind.Harbor;
        } else if (dragImage instanceof LumberImageView) {
            LumberBuilding lumber = new LumberBuilding(100, j, i, BuildingKind.Harbor, newImage);
            Main.getGame().getThisPlayer().addBuilding(lumber);
            kind = BuildingKind.Lumber;
        } else if (dragImage instanceof MineImageView) {
            MineBuilding mine = new MineBuilding(100, j, i, BuildingKind.Harbor, newImage);
            Main.getGame().getThisPlayer().addBuilding(mine);
            kind = BuildingKind.Mine;
        } else if (dragImage instanceof ArmyImageView) {
            ArmyBuilding army = new ArmyBuilding(100, j, i, BuildingKind.Army, newImage);
            Main.getGame().getThisPlayer().addBuilding(army);
            kind = BuildingKind.Army;
        } else {
            CastleBuilding castle = new CastleBuilding(100, j, i, BuildingKind.Castle, newImage);
            Main.getGame().getThisPlayer().addBuilding(castle);
            Main.getGame().getThisPlayer().createPersons(10);
            kind = BuildingKind.Castle;
        }
        dragImage = null;
        Main.getGame().getServerListener().sendCommand("building", Main.getGame().getThisPlayer().getID(), i, j, kind.getValue());
    }

    public void createBuilding(int id, int i, int j, int kind) {
        ImageView newImage = BuildingKind.getInstanceByKind(kind).getImageView();
        add(newImage, j, i);
        if (kind == BuildingKind.Castle.getValue()) {
            CastleBuilding castle = new CastleBuilding(100, j, i, BuildingKind.Castle, newImage);
            Main.getGame().getPlayer(id).addBuilding(castle);
        } else if (kind == BuildingKind.Harbor.getValue()) {
            HarborBuilding castle = new HarborBuilding(50, j, i, BuildingKind.Harbor, newImage);
            Main.getGame().getPlayer(id).addBuilding(castle);
        } else if (kind == BuildingKind.Lumber.getValue()) {
            LumberBuilding lumber = new LumberBuilding(70, j, i, BuildingKind.Lumber, newImage);
            Main.getGame().getPlayer(id).addBuilding(lumber);
        } else if (kind == BuildingKind.Mine.getValue()) {
            MineBuilding mine = new MineBuilding(70, j, i, BuildingKind.Lumber, newImage);
            Main.getGame().getPlayer(id).addBuilding(mine);
        } else if (kind == BuildingKind.Army.getValue()) {
            ArmyBuilding army = new ArmyBuilding(100, j, i, BuildingKind.Army, newImage);
            Main.getGame().getPlayer(id).addBuilding(army);
        }
    }

    public boolean isDragging() {
        return dragImage != null;
    }

    ScrollPane createScrollPane() {
        scrollPane = new ScrollPane(pane);
        pane.setStyle(Main.BG_COLOR);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        return scrollPane;
    }

    public Node getNodeByRowColumnIndex(int i, int j) {
        return pane.getChildren().get(i * 100 + j);
    }

    public void setSelectedPerson(Person selectedPerson) {
        this.selectedPerson = selectedPerson;
    }

    public Person getSelectedPerson() {
        return selectedPerson;
    }

    public void removeNode(PersonImageView personImage) {
        pane.getChildren().remove(personImage);
    }
}

