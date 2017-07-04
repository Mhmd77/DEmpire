package Game;

/**
 * Created by ASUS on 6/7/2017.
 */
public class ResourceBulding {
    private boolean buildable;
    private int kind;
    // Citizen citizen;

    /*ResourceBulding(int life, int x, int y, int kind) {
        super(life, x, y, imageView);
        this.kind = kind;
        if(kind!=2)
            buildable=true;
        else
            buildable=false;
    }*/


    public int getKind() {
        return kind;
    }

    public void build() {

    }

    public void addResource() {
        switch (kind) {
            //farm
            case 1:
                //sawmill
            case 2:
                //labour
            case 3:
                //mine
            case 4:

        }
    }


}
