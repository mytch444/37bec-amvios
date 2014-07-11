/*
 * An enemy that gives the player an a free run for a while.
 */

import java.awt.Color;

public class WallLaserBulletEnemy extends EnemyPart {

    public WallLaserBulletEnemy(GameControler co, short w, short h, float x, float y, float xv, float yv, Color c) {
	super(co, w, h, x, y, xv, yv, new Color(50, 150, 255), GameSound.EXPLOSIVE_ENEMY_HIT);
    }
    
    public WallLaserBulletEnemy(GameControler c) {
        super(c, new Color(50, 150, 255), GameSound.EXPLOSIVE_ENEMY_HIT);
    }

    public boolean collides(Bullet b) {
	if (!alive || particles != null) return false;
        if (collidesSquare(b)) {
            controler.givePlayerPowerup(new WallLaserBullet(controler));
            hit(b);
            return true;
        }
        return false;
    }
}
