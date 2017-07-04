package Game;

import javafx.scene.image.Image;

public class Resource {

    Image image;
    private int limit;
    private int kind;
    private int value;
    private int time;

    Resource(int kind) {
        this.kind = kind;
    }


    public void setInfo() {
        switch (kind) {
            case 0: {//GOLD
                setLimit(1000);
                setTime(10000);
            }
            case 1: {//WOOD
                setLimit(1000);
                setTime(2000);
            }
            case 2: {//FOOD
                setLimit(1000);
                setTime(3000);
            }

        }
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getKind() {
        return kind;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}

