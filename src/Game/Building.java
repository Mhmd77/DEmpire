package Game;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Building {
    private BuildingKind kind;
    private static boolean isActive = true;
    private Tiles pos;
    private int life;
    private ImageView imageView;
    private Person person;
    private boolean busy;

    public Building(int life, int j, int i, BuildingKind kind, ImageView imageView) {
        this.life = life;
        this.kind = kind;
        setImageView(imageView);
        pos = new Tiles();
        pos.j = j;
        pos.i = i;
        busy = false;
    }

    public static void setIsActive(boolean isActive) {
        Building.isActive = isActive;
    }

    public static boolean isActive() {
        return isActive;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    Tiles getPos() {
        return pos;
    }

    public ImageView getImageView() {
        return imageView;
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

    protected void setBusy(boolean busy) {
        this.busy = busy;
    }

    private void setImageView(ImageView imageView) {
        imageView.setOnMouseClicked(event -> {
            if (Main.getGame().getGraphic().getSelectedPerson() != null) {
                if (isBusy()) {
                    System.out.println("In Use");
                } else {
                    Main.getGame().getGraphic().getSelectedPerson().setBuilding(this);
                    Main.getGame().getGraphic().getSelectedPerson().move(getPos().i, getPos().j);
                }

            }
        });
        this.imageView = imageView;
    }

    protected void collect() {
    }
}
