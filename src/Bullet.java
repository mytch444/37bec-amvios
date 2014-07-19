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
 * This is violence now, don't get me wrong.
 */

import java.awt.Graphics2D;
import java.awt.Color;
import java.lang.Math;

public class Bullet extends Part {
    public static float SPEED = 40;
    public static short SHOOTING_DELAY = 5;

    float speed;

    public Bullet(GameControler c) {
        super(c, new Color(255, 0, 255));
        speed = SPEED;
    }

    public Bullet(GameControler c, float x, float y, float angle) {
        this(c);
        init(x, y, angle);
    }

    /*
     * Set the coordanates and compute the velocity.
     */
    public void init(float x, float y, float angle, int sound) {
        this.x = x;
        this.y = y;
        xv = (float) (-speed * Math.cos(angle));
        yv = (float) (-speed * Math.sin(angle));

	if (sound > 0)
	    controler.playSound(sound);
    }

    public void init(float x, float y, float angle) {
	init(x, y, angle, GameSound.BULLET);
    }

    public void paint(Graphics2D g) {
        g.setColor(color);
        g.drawLine((int) x, (int) y, (int) (x - xv), (int) (y - yv));
    }

    /*
     * Move it along and possibly remove it if it is outside the contoler.
     */
    public void update() {
        x += xv;
        y += yv;

        if (x < -SPEED ||
	    x > controler.getWidth() + SPEED ||
	    y < -SPEED ||
	    y > controler.getHeight() + SPEED) controler.removeBullet(this);
    }

    public void hitSomething() {
        controler.removeBullet(this);
    }

    public float getSpeed() {
        return speed;
    }

    /*
     * The following is stuff for dealing with the way that the player shoots.
     * This is where stuff that gives the player a cannon or a laser would go.
     */
    
    public short getDelay() {
	return SHOOTING_DELAY;
    }

    public Bullet nextBullet() {
	return null;
    }
}
