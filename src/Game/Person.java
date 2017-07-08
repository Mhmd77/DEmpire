package Game;

import ImageViews.BuildingImageView;
import ImageViews.PersonImageView;
import ImageViews.TileImageView;
import javafx.animation.AnimationTimer;
import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Person {
    List<TileImageView> tileImageView;
    int[] position = new int[2];//Position 0 : i Position 1 : j
    //    Integer[] destination = new Integer[2];
    int speed;
    int life;
    int foodAmount;
    int attackPower;
    boolean isClimbing;
    private PersonImageView personImage;
    private PathTransition pathTransition;

    Person(int i, int j) {
        tileImageView = MapLoader.tileImages;
        personImage = new PersonImageView("Images/romanSoldier.png", i, j, this);
        position[0] = i;
        position[1] = j;
        Main.getGame().getGraphic().add(personImage, position[1], position[0]);
    }


    public void setFoodAmount() {

    }


    public void attack() {

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
            position[0] = list.get(list.size() - 1).i;
            position[1] = list.get(list.size() - 1).j;
            System.out.println("FINISHED");
            Main.getGame().getGraphic().setSelectedPerson(null);
        });
    }


    public void setSpeed() {

    }


    public void setRadius() {

    }


    public void setPower() {

    }


    public void setClimbing() {

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
}
