package Game;

import javafx.scene.image.ImageView;

public enum BuildingKind {
    Castle(0), Harbor(1), Lumber(2), Mine(3), Army(4);
    private int value;

    BuildingKind(int value) {
        this.value = value;
    }

    public ImageView getImageView() {
        ImageView image = null;
        switch (this) {
            case Castle:
                image = new ImageView("Images/castle.png");
                break;
            case Harbor:
                image = new ImageView("Images/harbor.png");
                break;
            case Lumber:
                image = new ImageView("Images/mine.png");
                break;
            case Mine:
                image = new ImageView("Images/mine.png");
                break;
            case Army:
                image = new ImageView("Images/mine.png");
                break;
        }
        return image;
    }

    public int getValue() {
        return value;
    }

    public static BuildingKind getInstanceByKind(int kind) {
        switch (kind) {
            case 0:
                return Castle;
            case 1:
                return Harbor;
            case 2:
                return Lumber;
            case 3:
                return Mine;
            case 4:
                return Army;
        }
        return null;
    }
}
