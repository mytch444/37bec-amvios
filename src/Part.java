import java.lang.Math;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Color;

public class Part {
	GameControler controler;
    float x, y, w, h;
    float xv, yv;
    Color color;
    Random rand;
    boolean alive;

	public Part(GameControler co, Color c) { 
        controler = co;
        rand = new Random();
        color = c;
        alive = true; 
    }

	public void paint(Graphics g) {}
    public void update() {}
    public float getX() { return x; }
    public void setX(float x) { this.x = x; }
    public float getY() { return y; }
    public void setY(float x) { this.y = x; }
    public float getWidth() { return w; }
    public void setWidth(float x) { this.w = x; }
    public float getHeight() { return h; }
    public void setHeight(float x) { this.h = x; }
    public float getXV() { return xv; }
    public void setXV(float x) { xv = x; }
    public float getYV() { return yv; }
    public void setYV(float y) { yv = y; }
    public boolean isAlive() { return alive; }
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
