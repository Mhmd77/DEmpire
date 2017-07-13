package ImageViews;

import Game.Main;
import Game.Person;
import javafx.scene.image.ImageView;

public class PersonImageView extends ImageView {
    private Person ownPerson;
    private int i;
    private int j;

    public PersonImageView(String src, int i, int j, Person ownPerson) {
        super(src);
        this.i = i;
        this.j = j;
        setMouseClickListener();
        this.ownPerson = ownPerson;
    }

    public Person setMouseClickListener() {
        PersonImageView temp = this;
        temp.setOnMouseClicked(event -> {
            if (getOwnPerson().getTeam() == Main.getGame().getThisPlayer().getID()) {
                if (Main.getGame().getGraphic().getSelectedPerson() == null) {
                    Main.getGame().getGraphic().setSelectedPerson(getOwnPerson());
                    System.out.println("Person Selected.");
                } else {
                    System.out.println("Person Stopped");
                    getOwnPerson().stopTransition();
                    getOwnPerson().setRoamEnded(true);
                }
            } else if (Main.getGame().getGraphic().getSelectedPerson() != null) {
                System.out.println("Attack Incoming.");
                Main.getGame().getGraphic().getSelectedPerson().attack(getOwnPerson());
            }
            event.consume();
        });
        return temp.getOwnPerson();

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
