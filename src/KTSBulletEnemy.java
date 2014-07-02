/*
 * An enemy that gives the player an kts bullet.
 */

import java.awt.Color;

public class KTSBulletEnemy extends EnemyPart {

    public KTSBulletEnemy(GameControler co, short w, short h, float x, float y, float xv, float yv, Color c) {
	super(co, w, h, x, y, xv, yv, new Color(50, 50, 50), GameSound.EXPLOSIVE_ENEMY_HIT);
    }
    
    public KTSBulletEnemy(GameControler c) {
        super(c, new Color(50, 50, 50), GameSound.EXPLOSIVE_ENEMY_HIT);
    }

    public boolean collides(Bullet b) {
	if (!alive || particles != null) return false;
        if (collidesSquare(b)) {
            controler.givePlayerBullet(new KTSBullet(controler));
            hit(b);
            return true;
        }
        return false;
    }
}
