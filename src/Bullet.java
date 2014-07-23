/*
 *
 * Copyright: 2014 Mytchel Hammond <mytchel.hammond@gmail.com>
 *
 * 37bec-amvios is free software: you can redistribute it and/or modify
 * it under the term of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the Licence, or
 * (at your option) any later version.
 * 
 * 37bec-amvios is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License 
 * along with 37bec-amvios. If not, see <http://www.gnu.org/licenses/>
 *
 * --------------------------------------------------------------------
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
     * Then maybe play the sound.
     */
    public void init(float x, float y, float angle, int sound) {
        this.x = x;
        this.y = y;
        xv = (float) (-speed * Math.cos(angle));
        yv = (float) (-speed * Math.sin(angle));

	if (sound > 0)
	    controler.playSound(sound);
    }

    // Call the other one.
    public void init(float x, float y, float angle) {
	init(x, y, angle, GameSound.BULLET);
    }

    // Draw me.
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

    // This will be called if I hit something. For this one I want to remove myself.
    public void hitSomething() {
        controler.removeBullet(this);
    }

    public float getSpeed() {
        return speed;
    }

    // Delay before the player gets to shoot another bullet.
    public short getDelay() {
	return SHOOTING_DELAY;
    }

    // The next bullet to give them, I don't want to give them anything special
    // so they can sort there own crap out.
    public Bullet nextBullet() {
	return null;
    }
}
