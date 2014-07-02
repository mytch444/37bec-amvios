/*
 * An enemy that gives the player an scatterbullet.
 */

import java.awt.Color;

public class ScatterBulletEnemy extends EnemyPart {

    public ScatterBulletEnemy(GameControler co, short w, short h, float x, float y, float xv, float yv, Color c) {
	super(co, w, h, x, y, xv, yv, new Color(255, 0, 255), GameSound.EXPLOSIVE_ENEMY_HIT);
    }
    
    public ScatterBulletEnemy(GameControler c) {
        super(c, new Color(255, 0, 255), GameSound.EXPLOSIVE_ENEMY_HIT);
    }

    public boolean collides(Bullet b) {
	if (!alive || particles != null) return false;
        if (collidesSquare(b)) {
            controler.givePlayerBullet(new ScatterBullet(controler));
            hit(b);
            return true;
        }
        return false;
    }
}
