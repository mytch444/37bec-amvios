import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;

public class EnemyPart extends Part {

    Particle[] particles;
    boolean bounce;
    short ui, ri;

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

    public EnemyPart(GameControler co, short w, short h, float x, float y, float xv, float xy, Color c) {
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

            if (bounce && ((y < 0 && yv < 0) || (y + h > controler.getHeight() && yv > 0))) yv = -yv;
            if (x > controler.getWidth()) {
                controler.lowerLives();
                controler.removeOther(this);
                alive = false;
            }
        }
    }

    public boolean collides(Bullet b) {
        if (!alive || particles != null) return false;
        return collidesSquare(b);
    }
    
    public boolean collides(Player p) {
        if (!alive || particles != null) return false;
        return collidesSquare(p);
    }

    public void hit(Bullet b) {
        if (!alive || particles != null) return;
        
        short w = (short) (this.w / 5);
        short h = (short) (this.h / 5);

        float speed = b.getSpeed() / -8;

        float O;
        if (b.getXV() == 0) O = 0;
        else O = (float) Math.atan(b.getYV() / b.getXV());

        int alpha = color.getAlpha();
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();

        particles = new Particle[w * h];
        for (short x = 0; x < w; x++) {
            for (short y = 0; y < h; y++) {
                particles[y * w + x] = new Particle(controler, alpha, red, green, blue,
                        this.x + x * 5, this.y + y * 5, b, speed, O);
            }
        }
    }

    public void setBounce(boolean b) {
        bounce = b;
    }
}
