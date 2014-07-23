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
