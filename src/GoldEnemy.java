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
 * An enemy that shrinks as it is hit then explodes.
 * VERY FUCKING HARD. Makes the game a lot better.
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

    public boolean collides(Player p) {
        if (collidesSquare(p)) {
            controler.removeOther(this);
            return true;
        }

        return false;
    }

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
