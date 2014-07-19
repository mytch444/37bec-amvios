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
 * A bullet that kills any enemies and friends that try go past the wall.
 */

import java.awt.Color;
import java.awt.Graphics2D;

public class WallLaserBullet extends Bullet {

    short time;
    int sound;
    
    public WallLaserBullet(GameControler c, int t) {
        super(c);
        color = new Color(50, 150, 255);
	time = (short)t;
    }

    public WallLaserBullet(GameControler c) {
	this(c, 200);
    }

    public void init(float x, float y, float angle) {
	this.x = controler.getWidth() - 10;
	this.y = controler.getHeight();
	this.xv = 0;
	this.yv = controler.getHeight();
	sound = controler.playSound(GameSound.WALL_LASER_SETUP);
    }

    public void update() {
	time--;
	if (time < 0) {
	    controler.stopSound(GameSound.WALL_LASER_SETUP, sound);
	    controler.removeBullet(this);
	}
    }

    public short getDelay() {
	return time;
    }

    public void paint(Graphics2D g) {
	g.setColor(color);
	g.drawLine((int) x, (int) y, (int) x, (int) (y - yv));
	g.drawLine((int) x - 1, (int) y, (int) x - 1, (int) (y - yv));
	g.drawLine((int) x - 2, (int) y, (int) x - 2, (int) (y - yv));
    }

    public void hitSomething() {}
}
