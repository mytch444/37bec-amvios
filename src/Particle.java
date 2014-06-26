import java.lang.Math;
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;

public class Particle extends Part  {
   
    int a;

	public Particle(GameControler co, double x, double y, double w, double h, Color c) {
        super(co);
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        a = 255;
        color = c;

        xv = rand.nextDouble() * 6 - 3;
        yv = rand.nextDouble() * 6 - 3;
    }

    public void paint(Graphics g) {
        if (a < 50) return;
        color = new Color(color.getRed(), color.getGreen(), color.getBlue(), a);
        g.setColor(color);
        g.fillRect((int)x, (int)y, (int)w, (int)h);
    }

    public void update() {
        x += xv;
        y += yv;
        a -= 5;
    }

    public boolean shouldRemove() {
        return (color.getAlpha() < 100);
    }
}
