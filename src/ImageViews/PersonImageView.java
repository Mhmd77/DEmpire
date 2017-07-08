package ImageViews;

import Game.Main;
import Game.Person;
import javafx.scene.image.ImageView;

public class PersonImageView extends ImageView {
    private Person ownPerson;
    private int i;
    private int j;

    public PersonImageView() {
        super();
        mouseClicked();
    }

    public PersonImageView(String src, int i, int j, Person ownPerson) {
        super(src);
        this.i = i;
        this.j = j;
        mouseClicked();
        this.ownPerson = ownPerson;
    }

    public void mouseClicked() {
        PersonImageView temp = this;
        temp.setOnMouseClicked(event -> {
            if (Main.getGame().getGraphic().getSelectedPerson() == null) {
                Main.getGame().getGraphic().setSelectedPerson(getOwnPerson());
                System.out.println("person selected");
            } else {
                getOwnPerson().stopTransition();
                System.out.println("person selected2");
            }

        });
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public Person getOwnPerson() {
        return ownPerson;
    }

    public void setInJ(int i, int j) {
        this.i = i;
        this.j = j;
    }

}
