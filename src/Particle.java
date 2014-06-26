import java.lang.Math;
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;

public class Particle extends Part  {
   
    int a, r, g, b;

	public Particle(GameControler co, Color c, double x, double y, double xd, double yd) {
        super(co, c);
        this.x = x;
        this.y = y;
        this.w = 5;
        this.h = 5;

        a = color.getAlpha();
        r = color.getRed();
        g = color.getGreen();
        b = color.getBlue();

        double anglerange = (int) ((0x005 * xd * xd + 0.051) * (0.005 * yd * yd + 0.051) * 2 * Math.PI);
        
        double speed = 40 / anglerange + 1;
        double rspeed = speed + ((rand.nextDouble() * 0.2) - 0.1);

        double Oerror = (int) (-60 * rspeed + 180 + anglerange);

        double O;
        if (xd == 0) O = 0;
        else O = Math.atan(yd / xd);

        O = O + (rand.nextFloat() * Oerror) - (Oerror / 2);

        xv = Math.cos(O) * rspeed;
        yv = Math.sin(O) * rspeed;
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
