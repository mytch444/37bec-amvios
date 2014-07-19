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
 * Killing Them Slowly.
 */

import java.awt.Color;

public class KTSBullet extends Bullet {

    // The number of extra bullets to give the player.
    int more;

    public KTSBullet(GameControler c, int m) {
        super(c);
	speed = 20;
        color = new Color(150, 150, 150);
        more = m;
    }

    public KTSBullet(GameControler c) {
        this(c, 80);
    }

    public void init(float x, float y, float angle) {
	super.init(x, y, angle, GameSound.KTS_SHOT);
    }
    
    public short getDelay() {
	return (short) (2);
    }

    public Bullet nextBullet() {
	if (more > 0) return new KTSBullet(controler, more - 1);
	return null;
    }
}
