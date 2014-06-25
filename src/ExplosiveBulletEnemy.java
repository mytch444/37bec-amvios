/*
 * The target you must click repeatidly to flip some bits that represent your worth.
 */

import java.awt.*;
import java.util.Random;
import java.lang.Math;
import java.util.ArrayList;

public class ExplosiveBulletEnemy extends Enemy {
    
	public ExplosiveBulletEnemy(GameControler c) {
        super(c, "*\n", new Color(255, 0, 0));
	}

	public void paint(Graphics g) {
        for (int i = 0; i < parts.size(); i++)
            parts.get(i).paint(g);
	}

    public boolean collides(Bullet b) {
        if (super.collides(b)) {
            controler.givePlayerBullet(new ExplosiveBullet(controler));
            return true;
        }
        return false;
    }
}
