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
	    b.init(x, y, a, -1);
 	    controler.addBullet(b);
        }
	controler.playSound(GameSound.EXPLOSIVE_EXPLODE);
    }

    public short getDelay() {
	return (short) (SHOOTING_DELAY * 2);
    }

    // If more is greater than 0 give the player another.
    public Bullet nextBullet() {
	if (more > 0) return new ExplosiveBullet(controler, more - 1);
	return null;
    }
}
