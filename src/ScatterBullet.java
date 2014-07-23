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
