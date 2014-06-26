/*
 * The target you must click repeatidly to flip some bits that represent your worth.
 */

import java.awt.*;
import java.util.Random;
import java.lang.Math;
import java.util.ArrayList;

public class ExplosiveBulletEnemy extends EnemyPart {
    
	public ExplosiveBulletEnemy(GameControler c) {
        super(c, new Color(255, 0, 0));
	}

    public boolean collides(Bullet b) {
        if (particles == null && super.collides(b)) {
            controler.givePlayerBullet(new ExplosiveBullet(controler));
            hit();
            return true;
        }
        return false;
    }
}
