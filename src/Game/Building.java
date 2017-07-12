package Game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
        this.imageView = imageView;
        pos = new Tiles();
        pos.j = j;
        pos.i = i;
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

    public Tiles getPos() {
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


    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }
}
