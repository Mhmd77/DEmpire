package Game;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public abstract class Building {
    private int buildingId;
    private int team;
    private BuildingKind kind;
    private Tiles pos;
    private int life;
    private ImageView imageView;
    private Person person;
    private boolean busy;

    public Building(int life, int team, int j, int i, BuildingKind kind, ImageView imageView) {
        this(Main.getGame().getPlayer(team).getBuildings().size(), life, team, j, i, kind, imageView);
    }

    public Building(int buildingId, int life, int team, int j, int i, BuildingKind kind, ImageView imageView) {
        this.buildingId = buildingId;
        this.team = team;
        this.life = life;
        this.kind = kind;
        setImageView(imageView);
        pos = new Tiles();
        pos.j = j;
        pos.i = i;
        busy = false;
    }

    int getLife() {
        return life;
    }

    Tiles getPos() {
        return pos;
    }

    public BuildingKind getKind() {
        return kind;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
        setBusy(true);
    }

    private boolean isBusy() {
        return busy;
    }

    private void setBusy(boolean busy) {
        this.busy = busy;
    }

    private void setImageView(ImageView imageView) {
        imageView.setOnMouseClicked(event -> {
            if (team == Main.getGame().getThisPlayer().getID()) {
                if (Main.getGame().getGraphic().getSelectedPerson() != null) {
                    if (!isBusy()) {
                        Main.getGame().getGraphic().getSelectedPerson().setBuilding(this);
                        Main.getGame().getGraphic().getSelectedPerson().move(getPos().i, getPos().j);
                    }
                }
            } else {
                if (Main.getGame().getGraphic().getSelectedPerson() != null) {
                    Main.getGame().getGraphic().getSelectedPerson().move(pos.i, pos.j);
                }
            }
        });
        this.imageView = imageView;
    }

    void reduceLif(int attackPower) {
        life -= attackPower;
        if (life < 0) {
            Main.getGame().destroyBuilding(this);
        }
    }

    public ImageView getImage() {
        return imageView;
    }

    int getBuildingId() {
        return buildingId;
    }

    int getTeam() {
        return team;
    }

    protected abstract void collect(Person person);

    public abstract void destroy();
}
