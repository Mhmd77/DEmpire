package Game;

import ImageViews.PersonImageView;
import ImageViews.TileImageView;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Person implements Human {
    List<TileImageView> tileImageView;


    private ArrayList<Tiles> closeList;
    private ArrayList<Tiles> openList;
    Integer[] position = new Integer[2];
    Integer[] destination = new Integer[2];
    int speed;
    int life;
    int foodAmount;
    int attackPower;
    boolean isClimbing;
    private PersonImageView personImage;

    Person() {

        tileImageView = MapLoader.tileImages;

        // destination[0]= MapLoader.tileImages.get(0).getI();
        //  dest = TileImageView.
        personImage = new PersonImageView("Images/romanSoldier.jpg",0,0);
        position[0]=personImage.getI();
        position[1]=personImage.getJ();

    }

    public void setDest() {
        for (int i = 0; i < 4800; i++) {
            TileImageView img = tileImageView.get(i);
            destination = img.mouseClicked();
            if ((destination[0] == null || destination[1] == null) && i == 4799)
                i = 0;

        }

    }


    @Override
    public void setFoodAmount() {

    }

    @Override
    public void attack() {

    }

    @Override
    public void move(Pane pane) {


        ArrayList<Tiles> list = new ArrayList<Tiles>();
        // list = roam(position[0],position[1],int igoal,int jgoal); ba A*
        //test
             Tiles t1=new Tiles();
        Tiles t2=new Tiles();
        Tiles t3=new Tiles();
        Tiles t4=new Tiles();
        t1.i=0;t1.j=0;
        t2.i=10;t2.j=0;
        t3.i=20;t3.j=0;
        t4.i=30;t4.j=0;
        list.add(t1);
        list.add(t2);
        list.add(t3);
        list.add(t4);



        ImageView pImage = new ImageView();
        pImage.setImage(personImage.getImage());
        final long startNanoTime = System.nanoTime();
        final int[] x = {position[0]};
        final int[] y = {position[1]};
        Image image = new Image("Images/romanSoldier.png");
        Main.getGame().getGraphic().add(pImage, y[0], x[0]);
        int i = 0;

        new AnimationTimer() {
            int i = 0;

            public void handle(long currentNanoTime) {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;
                pImage.setX(list.get(i).i);
                pImage.setY(list.get(i).j );
                i++;
                System.out.println(pImage.getX()
                );
                if (i==list.size()-1)
                    stop();

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

    private double calculateH(Tiles first, Tiles goal) {
        double h = ((first.x - goal.x) + (first.y - goal.y));
        return Math.abs(h);

    }

    private Tiles findMinH(ArrayList<Tiles> tiles) {
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
