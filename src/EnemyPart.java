/*
 * A building block for enemy and for extending to create stand alone enemies.
 */

import java.awt.Graphics;
import java.awt.Color;

public class EnemyPart extends Part {

    Particle[] particles;
    boolean bounce;
    short ui, ri;

    // For those who don't have a sociopathic side.
    public EnemyPart(GameControler co, Color c) {
        super(co, c);
        color = c;

        w = 40;
        h = 40;
        x = rand().nextInt(controler.getWidth()) - controler.getWidth();
        y = rand().nextInt(controler.getHeight());
        xv = rand().nextInt(5);
        yv = rand().nextInt(10) - 5;
        
        bounce = true;
    }

    // For those who are into having complete control over things.
    public EnemyPart(GameControler co, short w, short h, float x, float y, float xv, float yv, Color c) {
        super(co, c);
        this.w = w;
        this.h = h;
        this.x = x;
        this.y = y;
        this.xv = xv;
        this.yv = yv;
        bounce = false;
    }

    public void paint(Graphics g) {
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
	    // If past the right edge then do shit to make the player feel sad.
            if (x > controler.getWidth()) {
                controler.lowerLives();
                controler.removeOther(this);
                alive = false;
            }
        }
    }

    // For both player and bullet collisions check if it collides with the square covered by the x,y,w,h shit.
    public boolean collides(Bullet b) {
        if (!alive || particles != null) return false;
        return collidesSquare(b);
    }
    
    public boolean collides(Player p) {
        if (!alive || particles != null) return false;
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
                particles[y * w + x] = new Particle(controler, alpha, red, green, blue,
                        this.x + x * 5, this.y + y * 5, b, speed, O);
            }
        }
    }

    public void hit() {
	alive = false;
    }

    public void setBounce(boolean b) {
        bounce = b;
    }
}
