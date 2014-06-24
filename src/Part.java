/*
 * The target you must click repeatidly to flip some bits that represent your worth.
 */

import java.awt.event.*;
import java.awt.*;
import java.lang.Math;
import java.util.Random;

public class Part {
	GameControler controler;
    int x, y, w, h;
    double xv, yv;
    Color color;
    Random rand;

	public Part(GameControler c) { controler = c; rand = new Random(); }
	public void paint(Graphics g1) {}
    public void update() {}
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return w; }
    public int getHeight() { return h; }
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
