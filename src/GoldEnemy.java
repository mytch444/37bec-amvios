/*
 * An enemy that shrinks as it is hit then explodes.
 * VERY FUCKING HARD. Makes the game a lot better.
 */

import java.awt.Graphics;
import java.awt.Color;

public class GoldEnemy extends EnemyPart {

    short maxW, maxH, minW, minH;

    public GoldEnemy(GameControler c) {
        super(c, new Color(255, 255, 0), GameSound.GOLD_ENEMY_HIT);

        maxW = (short) (w * 2);
        maxH = (short) (h * 2);
        minW = w;
        minH = h;

        w = maxW;
        h = maxW;
    }

    public boolean collides(Player p) {
        if (collidesSquare(p)) {
            controler.removeOther(this);
            return true;
        }

        return false;
    }

    public boolean collides(Bullet b) {
        boolean hit = false;
        if (collidesSquare(b)) {
            w -= 2;
            h -= 2;
            hit = true;
        }

        if (w < minW || h < minH) hit(b);

        return hit;
    }
}
