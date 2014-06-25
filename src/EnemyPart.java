/*
 * The target you must click repeatidly to flip some bits that represent your worth.
 */

import java.awt.*;
import java.util.Random;
import java.lang.Math;

public class EnemyPart {

    Enemy parent;
    double x, y, w, h;
    double xv, yv;
    Color fgcolor, bgcolor;

    public EnemyPart(Enemy p, double w, double h, double x, double y, double xv, double xy, Color fg, Color bg) {
        parent = p;
        this.w = w;
        this.h = h;
        this.x = x;
        this.y = y;
        this.xv = xv;
        this.yv = yv;
        fgcolor = fg;
        bgcolor = bg;
    }

	public void paint(Graphics g) {
        g.setColor(bgcolor);
        g.fillRect((int) x, (int) y, (int) w, (int) h);
        g.setColor(fgcolor);
        g.drawRect((int) x, (int) y, (int) w, (int) h);
	}

    public void update() {
        // Move the target.
        x += xv;
        y += yv;
    }

    public boolean collides(Part p) {
        if (p.getX() > x && p.getX() < x + w) {
            if (p.getY() > y && p.getY() < y + h) {
                return true;
            }
        }
        return false;
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public double getWidth() { return w; }
    public double getHeight() { return h; }
    public double getXV() { return xv; }
    public double getYV() { return yv; }

    public void setX(double x) { this.x = x; }
    public void setY(double x) { this.y = x; }
    public void setWidth(double x) { this.w = x; }
    public void setHeight(double x) { this.h = x; }
    public void setXV(double v) { xv = v; }
    public void setYV(double v) { yv = v; }
}
