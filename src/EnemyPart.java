import java.util.Random;
import java.lang.Math;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;

public class EnemyPart extends Part {

    Particle[] particles;
    boolean bounce;

    public EnemyPart(GameControler co, Color c) {
        super(co);
        color = c;

        w = 40;
        h = 40;
        x = rand.nextInt(controler.getWidth()) - controler.getWidth();
        y = rand.nextInt(controler.getHeight());

        xv = rand.nextInt(5);
        yv = rand.nextInt(10) - 5;
        bounce = true;
    }

    public EnemyPart(GameControler co, double w, double h, double x, double y, double xv, double xy, Color c) {
        super(co);
        this.w = w;
        this.h = h;
        this.x = x;
        this.y = y;
        this.xv = xv;
        this.yv = yv;
        color = c;
        bounce = false;
    }

	public void paint(Graphics g) {
        if (particles != null) {
            for (int i = 0; i < particles.length; i++) particles[i].paint(g);
        } else {
            g.setColor(color);
            g.fillRect((int) x, (int) y, (int) w, (int) h);
            g.setColor(Color.black);
            g.drawRect((int) x, (int) y, (int) w, (int) h);
        }
	}

    public void update() {
        if (particles != null) {
            boolean remove = true;
            for (int i = 0; i < particles.length; i++) {
                particles[i].update();
                if (!particles[i].shouldRemove()) remove = false;
            }
            if (remove) controler.removeOther(this);
        } else {
            x += xv;
            y += yv;

            if (bounce && (y < 0 || y + h > controler.getHeight())) yv = -yv;
        }
    }

    public boolean collides(Bullet b) {
        if (particles != null) return false;
        return collidesSquare(b);
    }
    
    public boolean collides(Player p) {
        if (particles != null) return false;
        return collidesSquare(p);
    }

    public void hit() {
        if (particles != null) return;
        int w = (int) (this.w / 5);
        int h = (int) (this.h / 5);
        particles = new Particle[w * h];
        for (int x = 0; x < w; x++)
            for (int y = 0; y < h; y++)
                particles[y * w + x] = new Particle(controler, x * 5 + this.x, y * 5 + this.y, 5, 5, color);
    }

    public boolean shouldRemove() {
        if (particles != null)
            for (int i = 0; i < particles.length; i++) if (particles[i].shouldRemove()) return true;
        return false;
    }
}
