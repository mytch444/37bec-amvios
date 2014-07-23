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
 * An enemy that gives the player an a free run for a while.
 */

import java.awt.Color;

public class WallLaserBulletEnemy extends EnemyPart {

    public WallLaserBulletEnemy(GameControler co, short w, short h, float x, float y, float xv, float yv, Color c) {
	super(co, w, h, x, y, xv, yv, new Color(50, 150, 255), GameSound.EXPLOSIVE_ENEMY_HIT);
    }
    
    public WallLaserBulletEnemy(GameControler c) {
        super(c, new Color(50, 150, 255), GameSound.EXPLOSIVE_ENEMY_HIT);
    }

    public boolean collides(Bullet b) {
	if (particles != null) return false;
        if (collidesSquare(b)) {
            controler.givePlayerPowerup(new WallLaserBullet(controler));
            hit(b);
            return true;
        }
        return false;
    }

    public boolean collides(Player p) {
	if (particles != null) return false;
        if (collidesSquare(p)) {
            controler.givePlayerPowerup(new WallLaserBullet(controler));
            return true;
        }
        return false;
    }
}
