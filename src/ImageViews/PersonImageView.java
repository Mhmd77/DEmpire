package ImageViews;

import javafx.scene.image.ImageView;

public class PersonImageView extends ImageView {
    private int i;
    private int j;
    TileImageView destination;

    PersonImageView() {
        super();
    }

    public PersonImageView(String src, int i, int j) {
        super(src);
        this.i = i;
        this.j = j;

    }


    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }
}
