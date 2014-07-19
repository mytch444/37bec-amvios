/*
 *          DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE
 *                    Version 2, December 2004
 *
 * Copyright (C) 2014 Mytchel Hammond
 *
 * Everyone is permitted to copy and distribute verbatim or modified
 * copies of this file, and changing it is allowed as long
 * as the name is changed.
 *
 *            DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE
 *   TERMS AND CONDITIONS FOR COPYING, DISTRIBUTION AND MODIFICATION
 *
 *  0. You just DO WHAT THE FUCK YOU WANT TO.
 *
 * -----------------------------------------------------------------
 *
 *
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
	if (particles != null) return false;
        if (collidesSquare(b)) {
            controler.givePlayerBullet(new ExplosiveBullet(controler));
            hit(b);
            return true;
        }
        return false;
    }
}
