/*
 * An enemy that gives the player an explosive bullet when hit.
 */

import java.awt.Color;

public class ExplosiveBulletEnemy extends EnemyPart {

    public ExplosiveBulletEnemy(GameControler co, short w, short h, float x, float y, float xv, float yv, Color c) {
	super(co, w, h, x, y, xv, yv, new Color(255, 0, 0), GameSound.EXPLOSIVE_ENEMY_HIT);
    }
    
    public ExplosiveBulletEnemy(GameControler c) {
        super(c, new Color(255, 0, 0), GameSound.EXPLOSIVE_ENEMY_HIT);
    }

    public boolean collides(Bullet b) {
	if (!alive || particles != null) return false;
        if (collidesSquare(b)) {
            controler.givePlayerBullet(new ExplosiveBullet(controler));
            hit(b);
            return true;
        }
        return false;
    }
}
