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
 * A bullet that explodes upon collision and gives the player another, or not.
 */

import java.awt.Graphics2D;
import java.awt.Color;
import java.lang.Math;

public class ExplosiveBullet extends Bullet {

    // The number of extra bullets to give the player.
    int more;

    public ExplosiveBullet(GameControler c, int m) {
        super(c);
        color = new Color(255, 0, 0);
        more = m;
    }

    public ExplosiveBullet(GameControler c) {
        this(c, 5);
    }

    public void init(float x, float y, float angle) {
	super.init(x, y, angle, GameSound.EXPLOSIVE_SHOT);
    }
    
    // Give a whole heap of bullets going everywhere and just generally being annoying.
    public void hitSomething() {
        super.hitSomething();
        for (float a = -3.14f; a < 3.14f; a += 0.5f) {
	    Bullet b = new Bullet(controler);
	    b.init(x, y, a);
	    b.update();
            controler.addBullet(b);
        }
	controler.playSound(GameSound.EXPLOSIVE_EXPLODE);
    }

    public short getDelay() {
	return (short) (SHOOTING_DELAY * 2);
    }

    public Bullet nextBullet() {
	if (more > 0) return new ExplosiveBullet(controler, more - 1);
	return null;
    }
}
