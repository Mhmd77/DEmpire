package Game;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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
                setLimit(100000);
                setTime(10000);
                image = new ImageView("Images/gold_icon.png");
                break;
            }
            case WOOD: {
                setLimit(100000);
                setTime(2000);
                image = new ImageView("Images/lumber_icon.png");
                break;
            }
            case FOOD: {
                setLimit(100000);
                setTime(3000);
                image = new ImageView("Images/stone_icon.png");
                break;
            }
        }
        label.setText(value + "/" + limit);
    }

    public int getLimit() {
        return limit;
    }

    private void setLimit(int limit) {
        this.limit = limit;
    }

    public int getKind() {
        return kind;
    }

    public double getValue() {
        return value;
    }

    synchronized void addValue(int value) {
        this.value = this.value + value * ratio < limit ? this.value + value * ratio : limit;
        label.setText(this.value + "/" + limit);
    }

    synchronized boolean reduceValue(int value) {
        this.value = this.value - value * ratio;
        if (this.value < 0) {
            this.value = 0;
            return false;
        }
        return true;
    }

    public int getTime() {
        return time;
    }

    private void setTime(int time) {
        this.time = time;
    }

    public ImageView getImage() {
        return image;
    }

    public Label getLabel() {
        return label;
    }

    void gainRatio() {
        this.ratio *= 1.5;
    }
    void reduceRatio() {
        this.ratio /= 1.5;
    }
}

