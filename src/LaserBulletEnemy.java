/*
 * An enemy that gives the player a laser.
 */

import java.awt.Color;

public class LaserBulletEnemy extends EnemyPart {

    public LaserBulletEnemy(GameControler co, short w, short h, float x, float y, float xv, float yv, Color c) {
	super(co, w, h, x, y, xv, yv, new Color(0, 255, 255));
    }
    
    public LaserBulletEnemy(GameControler c) {
        super(c, new Color(0, 255, 255));
    }

    public boolean collides(Bullet b) {
	if (!alive || particles != null) return false;
        if (collidesSquare(b)) {
            controler.givePlayerBullet(new LaserBullet(controler));
            hit(b);
            return true;
        }
        return false;
    }
}
