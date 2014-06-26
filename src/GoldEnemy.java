import java.awt.*;
import java.util.Random;
import java.lang.Math;
import java.util.ArrayList;

public class GoldEnemy extends EnemyPart {

    double maxW, maxH, minW, minH;

	public GoldEnemy(GameControler c) {
        super(c, new Color(255, 255, 0));

        maxW = w * 2;
        maxH = h * 2;
        minW = w;
        minH = h;

        w = maxW;
        h = maxW;
	}

    public boolean collides(Bullet b) {
        boolean hit = false;
        if (collidesSquare(b)) {
            w -= 2;
            h -= 2;
            hit = true;
        }

        if (w < minW || h < minH) hit();

        return hit;
    }
}
