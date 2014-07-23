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
 * A building block for enemy and for extending to create stand alone enemies.
 */

import java.awt.Graphics2D;
import java.awt.Color;

public class EnemyPart extends Part {

    Particle[] particles;
    boolean bounce;
    short ui, ri;
    int sound;

    // For those who don't have a sociopathic side.
    public EnemyPart(GameControler co, Color c, int s) {
        super(co, c);
        color = c;

        w = 40;
        h = 40;
        x = rand().nextInt(controler.getWidth() - w) - controler.getWidth();
        y = rand().nextInt(controler.getHeight());
        xv = rand().nextInt(5) + 1;
        yv = rand().nextInt(10) - 5;
        
        bounce = true;

	sound = s;
    }

    // For those who are into having complete control over things.
    public EnemyPart(GameControler co, short w, short h, float x, float y, float xv, float yv, Color c, int s) {
        super(co, c);
        this.w = w;
        this.h = h;
        this.x = x;
        this.y = y;
        this.xv = xv;
        this.yv = yv;
        bounce = false;
	sound = s;
    }

    /*
     * If it's dead then paint the particles. Else paint a square.
     */
    public void paint(Graphics2D g) {
        if (!alive) return;

        if (particles != null) {
            for (ri = 0; ri < particles.length && particles[ri] != null; ri++)
                particles[ri].paint(g);
        } else {
            g.setColor(color);
            g.fillRect((int) x, (int) y, w, h);
            g.setColor(Color.black);
            g.drawRect((int) x, (int) y, w, h);
        }
    }

    /*
     * If dead do nothing, if dying drag out the death for cinematic effect, else do boring stuff.
     */
    public void update() {
        if (!alive) return;

        if (particles != null) {
            alive = false;
            for (ui = 0; ui < particles.length; ui++) {
                particles[ui].update();
                if (!particles[ui].shouldRemove()) alive = true;
            }
        } else {
            x += xv;
            y += yv;

	    // If I'm alowed to bounce (not part of a larger controling part) then bounce of the vertical edges.
            if (bounce && ((y < 0 && yv < 0) || (y + h > controler.getHeight() && yv > 0))) yv = -yv;
	    // If past the right edge then make the player feel sad.
            if (x > controler.getWidth()) {
                controler.lowerLives();
                controler.removeOther(this);
                alive = false;
            }
        }
    }

    // For both player and bullet collisions check if it collides.
    public boolean collides(Bullet b) {
        if (particles != null) return false;
        return collidesSquare(b);
    }
    
    public boolean collides(Player p) {
        if (particles != null) return false;
        return collidesSquare(p);
    }

    /*
     * Set up stuff for a dramatic exit from the show. It's fairly self explanatory.
     */
    public void hit(Bullet b) {
        if (!alive || particles != null) return;
        
        short w = (short) (this.w / 5);
        short h = (short) (this.h / 5);

        float speed = b.getSpeed() / -8;
	// If the bullet is going increadibly fast then it can pretty much just pass through.
	if (speed < -10) speed = 0.25f;

	// Some general direction stuff.
        float O;
        if (b.getXV() == 0) O = 0;
        else O = (float) Math.atan(b.getYV() / b.getXV());
	
	// Some color stuff.
        short alpha = (short) color.getAlpha();
        short red = (short) color.getRed();
        short green = (short) color.getGreen();
        short blue = (short) color.getBlue();

        particles = new Particle[w * h];
        for (short x = 0; x < w; x++) {
            for (short y = 0; y < h; y++) {
		float px = this.x + x * 5;
		float py = this.y + y * 5;

		float xd = px - b.getX();
		float yd = py - b.getY();
		if (yd > this.h || yd < 0) yd = 0;
		float d = (float) Math.sqrt(xd * xd + yd * yd);

                particles[y * w + x] = new Particle(controler, alpha, red, green, blue,
						    px, py, d, speed, O);
            }
        }

	controler.playSound(sound);
    }

    public void hit() {
	alive = false;
    }

    public void setBounce(boolean b) {
        bounce = b;
    }
}
