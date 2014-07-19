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
 * I will rape you profoundly.
 */

import java.util.Random;

public class BossEnemy extends Enemy {

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
        
	lives = parts.length / 2;
    }

    // They will have a hard time stopping me like this.
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
     * Go through the parts and tell any that the bullet collides with
     * to go and fall apart.
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
