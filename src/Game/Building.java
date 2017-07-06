package Game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Building {

    private BuildingKind kind;
    private Image image;
    private static boolean isActive = true;
    private Tiles pos;
    private int life;
    private ImageView imageView;

    public Building(int life, int x, int y, BuildingKind kind, ImageView imageView) {
        this.life = life;
        this.kind = kind;
        this.imageView = imageView;
        pos = new Tiles();
        pos.x = x;
        pos.y = y;
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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
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
}
