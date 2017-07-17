package Game;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class Resource {
    static final int GOLD = 0, WOOD = 1, FOOD = 2;
    private ImageView image;
    private Label label;
    private int limit;
    private int kind;
    private double value;
    private int time;
    private double ratio;

    Resource(int kind) {
        this.kind = kind;
        this.ratio = 1;
        label = new Label();
        setInfo();
        VBox.setMargin(label, new Insets(5, 0, 0, 0));
        HBox.setHgrow(image, Priority.ALWAYS);
        HBox.setHgrow(label, Priority.ALWAYS);
    }


    private void setInfo() {
        switch (kind) {
            case GOLD: {
                setLimit();
                setTime(10000);
                value = 500;
                image = new ImageView("Images/gold_icon.png");
                break;
            }
            case WOOD: {
                setLimit();
                setTime(2000);
                image = new ImageView("Images/lumber_icon.png");
                break;
            }
            case FOOD: {
                setLimit();
                setTime(3000);
                value = 1000;
                image = new ImageView("Images/stone_icon.png");
                break;
            }
        }
        label.setText(value + "/" + limit);
    }

    public int getLimit() {
        return limit;
    }

    private void setLimit() {
        this.limit = 100000;
    }

    public int getKind() {
        return kind;
    }

    public double getValue() {
        return value;
    }

    synchronized void addValue() {
        this.value = this.value + 100 * ratio < limit ? this.value + 100 * ratio : limit;
        label.setText(this.value + "/" + limit);
    }

    synchronized void reduceValue(int value) {
        this.value = this.value - value * ratio;
        if (this.value < 0) {
            this.value = 0;
        }
    }

    int getTime() {
        return time;
    }

    private void setTime(int time) {
        this.time = time;
    }

    public ImageView getImage() {
        return image;
    }

    Label getLabel() {
        return label;
    }

    void gainRatio() {
        this.ratio *= 1.5;
    }
    void reduceRatio() {
        this.ratio /= 1.5;
    }
}

