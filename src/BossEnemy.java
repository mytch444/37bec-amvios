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
 * I will annoy you profoundly.
 */

import java.util.Random;

public class BossEnemy extends Enemy {

    // Possible layouts for the boss enemy.
    public static String[] PATTERNS = {
	"    *    \n   ***   \n  *****  \n ******* \n*********\n ******* \n  *****  \n   ***   \n    *    \n",
	"*******\n*******\n*******\n*******\n*******\n*******\n*******\n",
	"*     *\n ***** \n ***** \n ***** \n*     *\n"
    };

    int lives;

    public BossEnemy(GameControler c) {
	this(c, PATTERNS[c.getRandom().nextInt(PATTERNS.length)]);
    }
    
    public BossEnemy(GameControler co, String pattern) {
        super(co, pattern);
        
	lives = parts.length;
    }

    /*
     * Checks each part for a collision with the player, if they collide
     * then remove that one, rather than all like a normal enemy would.
     */
    public boolean collides(Player p) {
	boolean hit = false;
        for (ui = 0; ui < parts.length; ui++) {
            if (parts[ui].collides(p)) {
		parts[ui].hit();
		hit = true;
	    }
	}

        return hit;
    }

    /*
     * Check all of the parts, if the bullet collides with them then lower the lives.
     * If there are no lives left then remove that part.
     */
    public boolean collides(Bullet b) {
        boolean hit = false;
        for (ui = 0; ui < parts.length; ui++) {
            EnemyPart e = parts[ui];
            if (e.collides(b)) {
		lives--;
                controler.addScore(controler.SCORE_SHOT);
                hit = true;
		if (lives < 1)
		    e.hit(b);
            }    
        }

	// If I just lost my last life then break apart.
        if (!shot && lives < 1) {
	    shot = true;
            for (ui = 0; ui < parts.length; ui++) {
                EnemyPart e = parts[ui];
                e.setXV(rand().nextInt(5));
                e.setYV(rand().nextInt(10) - 5);
                e.setBounce(true);
            } 
        }

        return hit;
    }
}
