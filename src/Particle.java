import java.lang.Math;
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;

public class Particle extends Part  {
   
    int a, r, g, b;

	public Particle(GameControler co, Color c, double x, double y, Bullet b) {
        super(co, c);
        this.x = x;
        this.y = y;
        this.w = 5;
        this.h = 5;

        this.a = color.getAlpha();
        this.r = color.getRed();
        this.g = color.getGreen();
        this.b = color.getBlue();

        double xd = x - b.getX();
        double yd = y - b.getY();
        double d = Math.sqrt(xd * xd + yd * yd);

        double bxv = b.getXV();
        double byv = b.getYV();
        double bspeed = Math.sqrt(bxv * bxv + byv * byv);

        // Work out a good speed based on how far away the bullet impact was.
        double maxSpeed = -(bspeed / 8);
        double speed = -(maxSpeed / 900) * (d - 30) * (d + 30);

        // Work out the direction from the bullet.
        double O;
        if (b.getXV() == 0) O = 0;
        else O = Math.atan(b.getYV() / b.getXV());

        // Decide on a good error range for the direction.
        double Oerror = (2 * Math.PI / 1600) * d * d;
        
        // Randomize the direction;
        O = O + (rand.nextFloat() * Oerror) - (Oerror / 2);

        this.xv = Math.cos(O) * speed;
        this.yv = Math.sin(O) * speed;
    }

    public void paint(Graphics gr) {
        if (a < 0) return;
        color = new Color(r, g, b, a);
        gr.setColor(color);
        gr.fillRect((int)x, (int)y, (int)w, (int)h);
    }

    public void update() {
        x += xv;
        y += yv;
        a -= 5;
    }

    public boolean shouldRemove() {
        return (color.getAlpha() < 10);
    }
}
