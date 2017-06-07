import javafx.scene.image.Image;

/**
 * Created by ASUS on 6/7/2017.
 */
public class Building {
    private Image image;
    private static boolean isActive=true;
    int[] pos;
    private int life;

    Building(int life, int x, int y) {
        this.life = life;
        pos = new int[2];
        pos[0] = x;
        pos[1] = y;
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
}
