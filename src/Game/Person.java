package Game;

import ImageViews.PersonImageView;
import ImageViews.TileImageView;
import javafx.animation.PathTransition;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.util.ArrayList;

import java.util.List;


public class Person {
    List<TileImageView> tileImageView;
    public int i;
    public int j;
    private int life = 500;
    private int foodAmount = 1;
    private int attackPower = 40;
    private int team;
    private PersonImageView personImage;
    private PathTransition pathTransition;
    private boolean roamEnded = true;

    Person(int i, int j, int team, String loc) {
        this.team = team;
        tileImageView = MapLoader.tileImages;
        personImage = new PersonImageView(loc, i, j, this);
        this.i = i;
        this.j = j;
        Main.getGame().getGraphic().add(personImage, this.j, this.i);
    }

    public void attack(Person p) {
        System.out.println("attack initial implement");
    }

    public void move(int igoal, int jgoal) {
        move(igoal, jgoal, Main.getGame().getThisPlayer().getID());
    }

    public void move(int igoal, int jgoal, int playerID) {
        ArrayList<Tiles> list = (new PathFinder()).findPath(i, j, igoal, jgoal, playerID);
        Path path = new Path();
        path.getElements().add(new MoveTo(16, 16));
        list.remove(0);
        for (Tiles t :
                list)
            path.getElements().add(new Utils.LineToAbs(personImage, t.j * 16, t.i * 16));

        pathTransition = new PathTransition();
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setDuration(Duration.millis(Main.getGame().getPlayer(team).getSpeedPerson() * list.size()));
        pathTransition.setNode(personImage);
        pathTransition.setPath(path);
        pathTransition.setCycleCount(1);
        pathTransition.play();
        pathTransition.setOnFinished(event -> {
            roamEnded = true;
            i = list.get(list.size() - 1).i;
            j = list.get(list.size() - 1).j;
            double x = personImage.getLayoutX() + personImage.getTranslateX();
            double y = personImage.getLayoutY() + personImage.getTranslateY();
            personImage.relocate(x, y);
            personImage.setTranslateX(0);
            personImage.setTranslateY(0);
            Main.getGame().getGraphic().setSelectedPerson(null);
        });
        if (getTeam() == Main.getGame().getThisPlayer().getID()) {
            Main.getGame().getServerListener().sendCommand("person_move", team, igoal, jgoal, getPersonID());
        }
    }

    public void stopTransition() {
        pathTransition.stop();
        int jTmp = (int) (personImage.getTranslateX() / 16);
        int iTmp = (int) (personImage.getTranslateY() / 16);
        double x = personImage.getLayoutX() + personImage.getTranslateX();
        double y = personImage.getLayoutY() + personImage.getTranslateY();
        personImage.relocate(x, y);
        personImage.setTranslateX(0);
        personImage.setTranslateY(0);
        personImage.setInJ(i, j);
        i = iTmp;
        j = jTmp;
        Main.getGame().getGraphic().setSelectedPerson(null);
    }

    public boolean isRoamEnded() {
        return roamEnded;
    }

    public void setRoamEnded(boolean roamEnded) {
        this.roamEnded = roamEnded;
    }

    public void setFoodAmount() {

    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }


    public int getFoodAmount() {
        return foodAmount;
    }

    public int getAttackPower() {
        return attackPower;
    }


    public int getTeam() {
        return team;
    }

    public int getPersonID() {
        for (int i = 0; i < Main.getGame().getThisPlayer().getPersons().size(); i++)
            if (Main.getGame().getThisPlayer().getPersons().get(i).equals(this))
                return i;
        return -1;
    }
}
