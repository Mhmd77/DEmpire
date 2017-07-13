package Game;

import javafx.scene.Node;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;

public class Utils {
    public static class MoveToAbs extends MoveTo {

        MoveToAbs(Node node) {
            super(node.getLayoutBounds().getWidth() / 2 , node.getLayoutBounds().getHeight() / 2 );
        }
    }

    public static class LineToAbs extends LineTo {

        LineToAbs(Node node, double x, double y) {
            super(x - node.getLayoutX() + node.getLayoutBounds().getWidth() / 2, y - node.getLayoutY() + node.getLayoutBounds().getHeight() / 2);
        }

    }
}
