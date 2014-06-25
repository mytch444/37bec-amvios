/*
 * The target you must click repeatidly to flip some bits that represent your worth.
 */

import java.awt.*;
import java.util.Random;
import java.lang.Math;

public class EnemyPart {

    Enemy parent;
    int x, y, w, h;
    double xv, yv;
    Color fgcolor, bgcolor;

    public EnemyPart(Enemy p, int w, int h, int x, int y, double xv, double xy, Color fg, Color bg) {
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
        g.fillRect(x, y, w, h);
        g.setColor(fgcolor);
        g.drawRect(x, y, w, h);
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

    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return w; }
    public int getHeight() { return h; }
    public double getXV() { return xv; }
    public double getYV() { return yv; }

    public void setX(int x) { this.x = x; }
    public void setY(int x) { this.y = x; }
    public void setWidth(int x) { this.w = x; }
    public void setHeight(int x) { this.h = x; }
    public void setXV(double v) { xv = v; }
    public void setYV(double v) { yv = v; }
}
