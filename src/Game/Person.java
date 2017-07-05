package Game;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import javafx.scene.Node;

import java.util.ArrayList;


public class Person extends Node implements Human {

    ArrayList<Tile> closeList;
    ArrayList<Tile> openList;
    int[] position = new int[2];
    int speed;
    int life;
    int foodAmount;
    int attackPower;
    boolean isClimbing;


    @Override
    public void setFoodAmount() {

    }

    @Override
    public void attack() {

    }

    @Override
    public void setSpeed() {

    }

    @Override
    public void setRadius() {

    }


    public ArrayList<Tile> roam(int i, int j, int igoal, int jgoal, Tile[][] tiles) {
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

    private double calculateH(Tile first, Tile goal) {
        double h = ((first.x - goal.x) + (first.y - goal.y));
        return Math.abs(h);

    }

    private Tile findMinH(ArrayList<Tile> tiles) {
        Tile minH = new Tile();
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

    @Override
    protected NGNode impl_createPeer() {
        return null;
    }

    @Override
    public BaseBounds impl_computeGeomBounds(BaseBounds bounds, BaseTransform tx) {
        return null;
    }

    @Override
    protected boolean impl_computeContains(double localX, double localY) {
        return false;
    }

    @Override
    public Object impl_processMXNode(MXNodeAlgorithm alg, MXNodeAlgorithmContext ctx) {
        return null;
    }

}
