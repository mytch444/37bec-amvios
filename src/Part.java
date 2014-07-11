/*
 * The basic building block of all the parts that are displayed on the game. Player, bullets, friends and enemy
 * all extend this. It however does nothing.
 */

import java.lang.Math;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Color;

public class Part {
    GameControler controler;
    float x, y, xv, yv;
    short w, h;
    Color color;
    boolean alive;

    public Part(GameControler co, Color c) { 
        controler = co;
        color = c;
        alive = true; 
    }

    public Random rand() { return controler.getRandom(); }
    public void paint(Graphics g) {}
    public void update() {}

    public Color getColor() { return color; }
    public void setColor(Color c) { color = c; }

    public float getX() { return x; }
    public void setX(float x) { this.x = x; }
    
    public float getY() { return y; }
    public void setY(float x) { this.y = x; }
   
    public short getWidth() { return w; }
    public void setWidth(short x) { this.w = x; }
   
    public short getHeight() { return h; }
    public void setHeight(short x) { this.h = x; }
   
    public float getXV() { return xv; }
    public void setXV(float x) { xv = x; }
   
    public float getYV() { return yv; }
    public void setYV(float y) { yv = y; }
   
    public boolean isAlive() { return alive; }
    public boolean collides(Player p) { return false; };
    public boolean collides(Bullet b) { return false; };
    public boolean collidesSquare(Player p) {
	if (p.getX() > x && p.getX() < x + w) {
            if (p.getY() > y && p.getY() < y + h) {
                return true;
            }
        }
        return false;
    }
    
    public boolean collidesSquare(Bullet b) {
	return collidesLine(b);
    }
    
    public boolean collidesLine(Part p) {
	if (x < 0) return false;

	float px1 = p.getX();
	float py1 = p.getY();
	float px2 = p.getX() - p.getXV();
	float py2 = p.getY() - p.getYV();
	if (px1 > px2) {
	    float tmp = px2;
	    px2 = px1;
	    px1 = tmp;
	}
	if (py1 > py2) {
	    float tmp;
	    tmp = py2;
	    py2 = py1;
	    py1 = tmp;
	}
	
	float a;
	if (p.getXV() == 0) a = 10000;
	else a = p.getYV() / p.getXV();
	float c = p.getY() - a * p.getX();

	float top = y;
	float bottom = y + h;
	float left = x;
	float right = x + w;
	
	// Intersect with top side.
	if (py1 < top && top < py2) {
	    float xi = (top - c) / a;
	    if (left < xi && xi < right) return true;
	}

	// Intersect with bottom side.
	if (py1 < bottom && bottom < py2) {
	    float xi = (bottom - c) / a;
	    if (left < xi && xi < right) return true;
	}
	
	// Intersect with left side.
	if (px1 < left && left < px2) {
	    float yi = a * left + c;
	    if (top < yi && yi < bottom) return true;
	}

	// Intersect with right side.
	if (px1 < right && right < px2) {
	    float yi = a * right + c;
	    if (top < yi && yi < bottom) return true;
	}

	return false;
    }
}
