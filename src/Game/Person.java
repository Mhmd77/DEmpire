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
    public int[] position = new int[2];//Position 0 : i Position 1 : j
    private int speed = 1;
    private int life = 500;
    private int foodAmount = 1;
    private int attackPower = 40;
    private boolean isClimbing = false;
    private int team;
    private PersonImageView personImage;
    private PathTransition pathTransition;
    private boolean roamEnded = true;

    Person(int i, int j, int team, String loc) {
        this.team = team;
        tileImageView = MapLoader.tileImages;
        personImage = new PersonImageView(loc, i, j, this);
        position[0] = i;
        position[1] = j;
        Main.getGame().getGraphic().add(personImage, position[1], position[0]);
        System.out.println("PERSON CREATED");
    }


    public void setFoodAmount() {

    }


    public void attack(Person p) {
        System.out.println("attack initial implement");
    }


    public void move(int igoal, int jgoal) {
        ArrayList<Tiles> list = (new PathFinder()).roam(position[0], position[1], igoal, jgoal);
        pathTransition = new PathTransition();
        Path path = new Path();
        path.getElements().add(new MoveTo(16 + position[1] * 16, 16 + position[0] * 16));
        for (Tiles t :
                list) {
            path.getElements().add(new LineTo(t.j * 16 + 16, t.i * 16 + 16));
        }
        pathTransition.setDuration(Duration.millis(300 * list.size()));
        pathTransition.setNode(personImage);
        pathTransition.setPath(path);
        pathTransition.setCycleCount(1);
        pathTransition.play();
        pathTransition.setOnFinished(event -> {
            roamEnded = true;
            position[0] = list.get(list.size() - 1).i;
            position[1] = list.get(list.size() - 1).j;
            System.out.println("FINISHED");
            Main.getGame().getGraphic().setSelectedPerson(null);
        });
    }


    public void setPersonImage(PersonImageView personImage) {
        this.personImage = personImage;
    }

    public PersonImageView getPersonImage() {
        return personImage;
    }

    public void stopTransition() {
        pathTransition.stop();
        int j = (int) (personImage.getTranslateX() / 16);
        int i = (int) (personImage.getTranslateY() / 16);
        personImage.setInJ(i, j);
        position[0] = i;
        position[1] = j;
        System.out.println(i + "\t" + j);
    }

    public boolean isRoamEnded() {
        return roamEnded;
    }

    public void setRoamEnded(boolean roamEnded) {
        this.roamEnded = roamEnded;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getSpeed() {
        return speed;
    }

    public int getFoodAmount() {
        return foodAmount;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public boolean isClimbing() {
        return isClimbing;
    }

    public void setClimbing() {
        speed /= 2;
        isClimbing = true;
    }

    public int getTeam() {
        return team;
    }
}
