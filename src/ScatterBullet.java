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
 * A shotgun to the head.
 */

import java.awt.Color;

public class ScatterBullet extends Bullet {

    // The number of extra bullets to give the player.
    int more;

    public ScatterBullet(GameControler c, int m) {
        super(c);
        color = new Color(255, 0, 255);
        more = m;
	speed = speed * 0.7f;
    }

    public ScatterBullet(GameControler c) {
        this(c, 10);
    }

    public void initNoMore(float x, float y, float angle) {
	super.init(x, y, angle, -1);
    }
    
    public void init(float x, float y, float angle) {
	for (float a = -0.1f; a < 0.1f; a += 0.025f) {
	    ScatterBullet b = new ScatterBullet(controler, 0);
	    b.initNoMore(x, y, angle + a);
	    controler.addBullet(b);
	}

	controler.removeBullet(this);
	controler.playSound(GameSound.SCATTER_SHOT);
    }
    
    public short getDelay() {
	return (short) (SHOOTING_DELAY * 4);
    }

    public Bullet nextBullet() {
	if (more > 0) return new ScatterBullet(controler, more - 1);
	return null;
    }
}
