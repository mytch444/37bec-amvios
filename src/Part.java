/*
 * The target you must click repeatidly to flip some bits that represent your worth.
 */

import java.awt.event.*;
import java.awt.*;
import java.lang.Math;
import java.util.Random;

public class Part {
	GameControler controler;
    double x, y, w, h;
    double xv, yv;
    Color color;
    Random rand;

	public Part(GameControler c) { controler = c; rand = new Random(); }
	public void paint(Graphics g1) {}
    public void update() {}
    public double getX() { return x; }
    public void setX(double x) { this.x = x; }
    public double getY() { return y; }
    public void setY(double x) { this.y = x; }
    public double getWidth() { return w; }
    public void setWidth(double x) { this.w = x; }
    public double getHeight() { return h; }
    public void setHeight(double x) { this.h = x; }
    public boolean collides(Player p) { return false; };
    public boolean collides(Bullet b) { return false; };
    public boolean collidesSquare(Part p) {
        if (p.getX() > x && p.getX() < x + w) {
            if (p.getY() > y && p.getY() < y + h) {
                return true;
            }
        }
        return false;
    }
}
