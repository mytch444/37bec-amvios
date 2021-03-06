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
