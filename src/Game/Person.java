package Game;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Timer;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;


public class Person implements Human {

    ArrayList<Tiles> closeList;
    ArrayList<Tiles> openList;
    int[] position = new int[2];
    int speed;
    int life;
    int foodAmount;
    int attackPower;
    boolean isClimbing;
    ImageView personImage;

    Person() {
        personImage = new ImageView("Images/romanSoldier.jpg");
    }

    @Override
    public void setFoodAmount() {

    }

    @Override
    public void attack() {

    }

    @Override
    public void move(GridPane pane) {
        Tiles[] tiles = new Tiles[4];
        for (int i = 0; i < 4; i++) {
            tiles[i] = new Tiles();
        }
        tiles[0].i = 10;
        tiles[0].j = 10;
        tiles[1].i = 20;
        tiles[1].j = 50;
        tiles[2].i = 30;
        tiles[2].j = 50;
        tiles[3].i = 0;
        tiles[3].j = 0;
        boolean reachedDestination = false;
        ArrayList<Tiles> list = new ArrayList<Tiles>(Arrays.asList(tiles));
        ImageView pImage = new ImageView();
        pImage.setImage(personImage.getImage());
        final long startNanoTime = System.nanoTime();
        final int[] x = {1};
        final int[] y = {1};
        Image image = new Image("Images/romanSoldier.jpg");

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;

                //gc.drawImage(image,x[0],y[0]);
               pane.getChildren().remove(pImage);
                pane.add(pImage, y[0], x[0], y[0] + 4, x[0] + 4);
                x[0] += 1;
               y[0] +=1;


            }
        }.start();

    }

    @Override
    public void setSpeed() {

    }

    @Override
    public void setRadius() {

    }


    public ArrayList<Tiles> roam(int i, int j, int igoal, int jgoal, Tiles[][] tiles) {
        int g = 1;
        closeList = new ArrayList<>();
        openList = new ArrayList<>();
        closeList.add(tiles[i][j]);

        while (!closeList.contains(tiles[igoal][jgoal])) {
            if ((!openList.contains(tiles[i - 1][j - 1])) && (!closeList.contains(tiles[i - 1][j - 1])) && (tiles[i - 1][j - 1].id == 1)
                    || (tiles[i - 1][j - 1].id == 3)) {
                tiles[i - 1][j - 1].g = g;
                tiles[i - 1][j - 1].h = calculateH(tiles[i - 1][j - 1], tiles[igoal][jgoal]);
                openList.add(tiles[i - 1][j - 1]);
            }

            if ((!openList.contains(tiles[i][j - 1])) && (!closeList.contains(tiles[i][j - 1])) && (tiles[i][j - 1].id == 1)
                    || (tiles[i][j - 1].id == 3)) {
                tiles[i][j - 1].g = g;
                tiles[i][j - 1].h = calculateH(tiles[i][j - 1], tiles[igoal][jgoal]);
                openList.add(tiles[i][j - 1]);
            }

            if ((!openList.contains(tiles[i - 1][j])) && (!closeList.contains(tiles[i - 1][j])) && (tiles[i - 1][j].id == 1)
                    || (tiles[i - 1][j].id == 3)) {
                tiles[i - 1][j].g = g;
                tiles[i - 1][j].h = calculateH(tiles[i - 1][j], tiles[igoal][jgoal]);
                openList.add(tiles[i - 1][j]);
            }

            if ((!openList.contains(tiles[i - 1][j + 1])) && (!closeList.contains(tiles[i - 1][j + 1])) && (tiles[i - 1][j + 1].id == 1)
                    || (tiles[i - 1][j + 1].id == 3)) {
                tiles[i - 1][j + 1].g = g;
                tiles[i - 1][j + 1].h = calculateH(tiles[i - 1][j + 1], tiles[igoal][jgoal]);
                openList.add(tiles[i - 1][j + 1]);
            }

            if ((!openList.contains(tiles[i][j + 1])) && (!closeList.contains(tiles[i][j + 1])) && (tiles[i][j + 1].id == 1)
                    || (tiles[i][j + 1].id == 3)) {
                tiles[i][j + 1].g = g;
                tiles[i][j + 1].h = calculateH(tiles[i][j + 1], tiles[igoal][jgoal]);
                openList.add(tiles[i][j + 1]);
            }

            if ((!openList.contains(tiles[i + 1][j - 1])) && (!closeList.contains(tiles[i + 1][j - 1])) && (tiles[i + 1][j - 1].id == 1)
                    || (tiles[i + 1][j - 1].id == 3)) {
                tiles[i + 1][j - 1].g = g;
                tiles[i + 1][j - 1].h = calculateH(tiles[i + 1][j - 1], tiles[igoal][jgoal]);
                openList.add(tiles[i + 1][j - 1]);
            }

            if ((!openList.contains(tiles[i + 1][j])) && (!closeList.contains(tiles[i + 1][j])) && (tiles[i + 1][j].id == 1)
                    || (tiles[i + 1][j].id == 3)) {
                tiles[i + 1][j].g = g;
                tiles[i + 1][j].h = calculateH(tiles[i + 1][j], tiles[igoal][jgoal]);
                openList.add(tiles[i + 1][j]);
            }

            if ((!openList.contains(tiles[i + 1][j + 1])) && (!closeList.contains(tiles[i + 1][j + 1])) && (tiles[i + 1][j + 1].id == 1)
                    || (tiles[i + 1][j + 1].id == 3)) {
                tiles[i + 1][j + 1].g = g;
                tiles[i + 1][j + 1].h = calculateH(tiles[i + 1][j + 1], tiles[igoal][jgoal]);
                openList.add(tiles[i + 1][j + 1]);
            }

            closeList.add(findMinH(openList));
            openList.remove(findMinH(openList));
            i = findMinH(openList).i;
            j = findMinH(openList).j;
            g++;

            if ((Math.abs(findMinH(openList).i - tiles[igoal][jgoal].i) == 1) && (Math.abs(findMinH(openList).j - tiles[igoal][jgoal].j) == 1) ||
                    (Math.abs(findMinH(openList).i - tiles[igoal][jgoal].i) == 0) && (Math.abs(findMinH(openList).j - tiles[igoal][jgoal].j) == 1)
                    || (Math.abs(findMinH(openList).i - tiles[igoal][jgoal].i) == 1) && (Math.abs(findMinH(openList).j - tiles[igoal][jgoal].j) == 0)) {
                closeList.add(tiles[igoal][jgoal]);
            }

        }

        for (int k = 0; k < closeList.size(); k++) {
            for (int l = k; l < closeList.size(); l++) {
                if ((closeList.get(k).g == closeList.get(l).g) && (closeList.get(k).h > closeList.get(l).h)) {
                    closeList.remove(l);

                }
            }
        }

        return closeList;

    }

    double calculateH(Tiles first, Tiles goal) {
        double h = ((first.x - goal.x) + (first.y - goal.y));
        return Math.abs(h);

    }

    Tiles findMinH(ArrayList<Tiles> tiles) {
        Tiles minH = new Tiles();
        minH.h = tiles.get(0).h;

        for (int i = 0; i < tiles.size(); i++) {
            if (tiles.get(i).h < minH.h) {
                minH.h = tiles.get(i).h;
            }
        }
        return minH;
    }


    @Override
    public void setPower() {

    }

    @Override
    public void setClimbing() {

    }


}
/* try {

            for (int k = 0; k < 4; k++) {
        System.out.println("in" + k);
        pane.getChildren().remove(pImage);
        pImage.setImage(personImage.getImage());
        pane.add(pImage, tiles[k].j, tiles[k].i,
        tiles[k].j + 4, tiles[k].i + 4);
        TimeUnit.SECONDS.sleep(1);

        }
        } catch (InterruptedException e) {
        System.out.println(e);
        System.out.println("sleep exc");
        }*/