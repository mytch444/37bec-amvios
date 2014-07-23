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
 * An enemy that shrinks as it is hit then dies like the rest.
 */

import java.awt.Graphics2D;
import java.awt.Color;

public class GoldEnemy extends EnemyPart {

    short minW, minH;

    public GoldEnemy(GameControler c) {
        super(c, new Color(255, 240, 0), GameSound.GOLD_ENEMY_HIT);

	minW = w;
        minH = h;

        w = (short) (w * 2);
        h = (short) (h * 2);
    }

    // If I hit the player then just remve me.
    public boolean collides(Player p) {
        if (collidesSquare(p)) {
            controler.removeOther(this);
            return true;
        }

        return false;
    }

    // If hit by a bullet then shrink or die.
    public boolean collides(Bullet b) {
        boolean hit = false;
        if (collidesSquare(b)) {
            w -= 2;
            h -= 2;
            hit = true;
        }

        if (w < minW || h < minH) hit(b);

        return hit;
    }
}
