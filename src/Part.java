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
    public boolean collidesSquare(Part p) {
        if (p.getX() > x && p.getX() < x + w) {
            if (p.getY() > y && p.getY() < y + h) {
                return true;
            }
        }
        return false;
    }
}
