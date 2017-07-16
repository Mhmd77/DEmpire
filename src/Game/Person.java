package Game;

import ImageViews.PersonImageView;
import javafx.animation.PathTransition;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;


public class Person {
    private int personID;
    protected int i;
    protected int j;
    protected int life;
    protected int foodAmount;
    protected int attackPower;
    private int team;
    private Building building;
    private PersonImageView personImage;
    private PathTransition pathTransition;
    private boolean roamEnded = true;
    private AttackListener attackListener;

    Person(int personID, int j, int team, String loc, int i, int attackPower) {
        this(personID, j, team, loc, i);
        setAttackPower(attackPower);
    }

    Person(int personID, int j, int team, String loc, int i) {
        setInfo();
        this.personID = personID;
        personImage = new PersonImageView(loc, i, j, this);
        this.team = team;
        this.i = i;
        this.j = j;
        Main.getGame().getGraphic().add(personImage, this.j, this.i);
        if (team == Main.getGame().getThisPlayer().getID()) {
            attackListener = new AttackListener(this);
            Main.getGame().getPlayer(team).addAttackListener(attackListener);
            Main.getGame().getServerListener().sendCommand("create_person", getTeam(), personID, j, i, attackPower);
        }
    }

    protected void setInfo() {
        life = 500;
        foodAmount = 1;
        Random r = new Random();
        attackPower = r.nextInt(15) + 5;
    }

    public void attack(Person p) {
        p.reduceLife();
        System.out.println(team + "\t" + personID + "\t" + this.life + "\t" + p.team + "\t" + p.personID + "\t" + p.life);
    }

    private synchronized void reduceLife() {
        life -= attackPower;
        if (life < 0) {
            Main.getGame().killPerson(this);
            if (attackListener != null)
                attackListener.cancel();
        }
    }

    public void move(int iGoal, int jGoal) {
        move(iGoal, jGoal, Main.getGame().getThisPlayer().getID());
    }

    public void move(int iGoal, int jGoal, int playerID) {
        ArrayList<Tiles> list = (new PathFinder()).findPath(i, j, iGoal, jGoal, playerID);
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
            if (team == Main.getGame().getThisPlayer().getID())
                Main.getGame().getGraphic().setSelectedPerson(null);
            if (building != null) {
                building.collect();
            }
        });
        if (getTeam() == Main.getGame().getThisPlayer().getID()) {
            Main.getGame().getServerListener().sendCommand("person_move", team, iGoal, jGoal, getPersonID());
        }
    }

    public void stopTransition() {
        roamEnded = true;
        pathTransition.stop();
        double x = personImage.getLayoutX() + personImage.getTranslateX();
        double y = personImage.getLayoutY() + personImage.getTranslateY();
        personImage.relocate(x, y);
        personImage.setTranslateX(0);
        personImage.setTranslateY(0);
        i = (int) (y / 16);
        j = (int) (x / 16);
        personImage.setInJ(i, j);
        Main.getGame().getGraphic().setSelectedPerson(null);
        if (getTeam() == Main.getGame().getThisPlayer().getID()) {
            Main.getGame().getGraphic().setSelectedPerson(null);
            Main.getGame().getServerListener().sendCommand("person_stop", team, getPersonID());
        }
    }

    public boolean isRoamEnded() {
        return roamEnded;
    }

    public void setRoamEnded(boolean roamEnded) {
        this.roamEnded = roamEnded;
    }

    public int getTeam() {
        return team;
    }

    public int getPersonID() {
        return personID;
    }

    public PersonImageView getPersonImage() {
        return personImage;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public int getI() {
        if (isRoamEnded()) return i;
        return (int) (personImage.getLayoutX() + personImage.getTranslateX()) / 16;
    }

    public int getJ() {
        if (isRoamEnded()) return j;
        else return (int) (personImage.getLayoutY() + personImage.getTranslateY()) / 16;
    }

    public AttackListener getAttackListener() {
        return attackListener;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }
}
