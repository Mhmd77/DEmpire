import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;


public class Person extends Node implements Human {

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


    @Override
    public void roam(int x, int y, int desX, int desY, Person person) {

        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(2000), person);
        translateTransition.setFromX(x);
        translateTransition.setFromY(y);
        translateTransition.setToX(desX - x);
        translateTransition.setToY(desY - y);
        translateTransition.play();

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
