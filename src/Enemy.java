/*
 * I will rape you.
 */

import java.util.Random;
import java.lang.Math;
import java.awt.Graphics;
import java.awt.Color;

public class Enemy extends Part {

    public static String[] PATTERNS = {
	"***\n*\n*\n***\n",
	"*\n*\n*\n*\n",
	"***\n*$*\n***\n",
	"*\n",
	"***\n",
	"*\n**\n***\n**\n*\n",
	"* *\n * \n* *\n",
    };

    EnemyPart[] parts;
    boolean shot;
    short ri, ui; // variables to use for loop iterations (r for render, u for update). This is probably one of the
    // more stupid idea's I've had lately.

    public Enemy(GameControler c) {
	this(c, PATTERNS[c.getRandom().nextInt(PATTERNS.length)]);
    }
    
    // The pattern is the pattern that the enemy will take, *'s are where blocks will be, $ are exploding blocks,
    // space where a space will be and \n (newline)'s move the remaining blocks down a level.
    public Enemy(GameControler co, String pattern) {
        super(co, Color.white);
        
        // Give it a random location on the screen and a random velocity.
        x = rand().nextInt(controler.getWidth()) - controler.getWidth() - 100;
        y = rand().nextInt(controler.getHeight() - 200) + 100;
        xv = rand().nextInt(5);
        yv = rand().nextInt(10) - 5;

        shot = false;

	// Work out the length and create the array with said length.
	short len = 0;
        for (int i = 0; i < pattern.length(); i++) if (pattern.charAt(i) != ' ' && pattern.charAt(i) != '\n') len++;
        parts = new EnemyPart[len];

	short px, py, pw, ph;

        px = (short) x;
        py = (short) y;
        pw = 40;
        ph = 40;

	// Go through the pattern and add blocks where they need to go.
        short i = 0;
        h = w = 0;
        for (short c = 0; c < pattern.length(); c++) {
            if (pattern.charAt(c) == '\n') {
		// Move the y location of remaining blocks down and add a block height to the total height.
                py += ph;
                h += ph;
		// If this lines width is greater than all others set the total width to this lines width.
                if (px - x > w) w = (short) (px - x);
                px = (short) x;

		// I don't want to move the next block over so skip the rest of this loop's iteration.
                continue;
            } else if (pattern.charAt(c) == '*') { // Add a part.
                parts[i++] = new EnemyPart(controler, pw, ph, px, py, xv, yv, color, GameSound.ENEMY_HIT); 
            } else if (pattern.charAt(c) == '$') {
		parts[i++] = new ExplosiveBulletEnemy(controler, pw, ph, px, py, xv, yv, color);
	    } else if (pattern.charAt(c) != ' ') System.out.println("WHAT THE FUCK BRO. I DON'T KNOW HOW TO HANDLE THIS SYMBOL '" + pattern.charAt(c) + "' TAKE IT OUT NOW OR I WILL CRASH!!!!!!");

            px += pw;
        }
    }

    public void paint(Graphics g) {
        for (ri = 0; ri < parts.length; ri++)
            parts[ri].paint(g);
    }

    public void update() {
	// Unless it's been shot and they parts have broken up move the total things place.
        if (!shot) {
            x += xv;
            y += yv;

	    if ((y + h > controler.getHeight() && yv > 0) || (y < 0 && yv < 0)) {
		yv = -yv;
		for (ui = 0; ui < parts.length; ui++) parts[ui].setYV(yv);
	    }
        }

	// Go though, update and check if it's still alive.
        alive = false;
        for (ui = 0; ui < parts.length; ui++) {
            EnemyPart p = parts[ui];
            if (p.isAlive()) alive = true;
            p.update();
        }

	// If it's not alive then remove it from the controler.
        if (!alive) {
            controler.addScore(controler.SCORE_SHOT * 2);
            controler.removeOther(this);
        }
    }

    /* Go though the parts and check if they collide.
     * If it's been broken up then remove just that one,
     * else remove the hole lot.
     */
    public boolean collides(Player p) {
	boolean hit = false;
        for (ui = 0; ui < parts.length; ui++) {
            if (parts[ui].collides(p)) {
		if (shot) parts[ui].hit();
		hit = true;
	    }
	}

	if (hit && !shot) controler.removeOther(this);
        return hit;
    }

    /*
     * Go through the parts and tell any that the bullet collides with
     * to go and fall apart.
     */
    public boolean collides(Bullet b) {
        boolean hit = false;
        boolean wasshot = shot;
        for (ui = 0; ui < parts.length; ui++) {
            EnemyPart e = parts[ui];
            if (e.collides(b)) {
                shot = true;
                e.hit(b);
                controler.addScore(controler.SCORE_SHOT);
                hit = true;
            }    
        }

	// If it wasn't shot but is now, go through and give them all random velocities.
        if (wasshot != shot) {
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
