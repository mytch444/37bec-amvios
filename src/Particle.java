import java.lang.Math;
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;

public class Particle extends Part  {
   
    int a, r, g, b;

	public Particle(GameControler co, int alpha, int red, int green, int blue, float x, float y, Bullet b, float maxSpeed, float O) {
        super(co, null);
        this.x = x;
        this.y = y;
        this.w = 5;
        this.h = 5;
        this.a = alpha;
        this.r = red;
        this.g = green;
        this.b = blue;
        color = new Color(this.r, this.g, this.b, this.a);

        float xd = x - b.getX();
        float yd = y - b.getY();
        float d = (float) Math.sqrt(xd * xd + yd * yd);

        // Work out a good speed based on how far away the bullet impact was.
        float speed = -(maxSpeed / 900) * (d - 30) * (d + 30);

        // Decide on a good error range for the direction.
        float Oerror = (2 * (float) Math.PI / 1600) * d * d;
        
        // Randomize the direction;
        O = O + (rand().nextFloat() * Oerror) - (Oerror / 2);

        this.xv = (float) Math.cos(O) * speed;
        this.yv = (float) Math.sin(O) * speed; 
    }

    public void paint(Graphics gr) {
        if (a < 0) return;
        gr.setColor(color);
        gr.fillRect((int)x, (int)y, (int)w, (int)h);
    }

    public void update() {
        x += xv;
        y += yv;
        a -= 5;
        color = new Color(r, g, b, a);
    }

    public boolean shouldRemove() {
        return (a < 10);
    }
}
