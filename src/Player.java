import java.awt.event.*;
import java.awt.*;
import java.lang.Math;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;

public class Player extends Part {
    private short SHOOTING_DELAY = 5;

    Bullet nextBullet;
    double angle;
    short hit;
    boolean showHit;

    boolean shooting;
    short shooting_delay;

    BufferedImage image, imageHit; 

    public Player(GameControler c) {
        super(c, null);
        angle = 0;
        shooting = false;
        shooting_delay = SHOOTING_DELAY;
        hit = 0;
        showHit = false;
        yv = 0;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/images/player.png"));
            imageHit = ImageIO.read(getClass().getResourceAsStream("/images/player_hit.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        w = (short) image.getWidth();
        h = (short) image.getHeight();
        x = c.getWidth();
        y = c.getHeight() / 2 - h / 2;
	
    }

    public void paint(Graphics g1) {
        Graphics2D g = (Graphics2D) g1;
	double a = angle;
	short x = (short) this.x;
	short y = (short) this.y;
	g.rotate(a, x, y);
	g.setColor(Color.blue);
	g.drawLine(x, y, x - controler.getWidth() * 2, y);
        if (showHit) g.drawImage(imageHit, x - w / 2, y - h / 2, null);
        else g.drawImage(image, x - w / 2, y - h / 2, null);
        g.rotate(-a, x, y);
    }

    public void update() {
        if (hit > 0) {
            if (hit % 10 == 0) showHit = !showHit;
            hit--;
            return;
        } else showHit = false;

        y += yv;
        if (y < 0 && yv < 0) y = 0;
        if (y > controler.getHeight() && yv > 0) y = controler.getHeight(); 
        
        if (shooting && shooting_delay == 0) {
            if (nextBullet == null) 
                nextBullet = new Bullet(controler, x, y, (float)angle);
            else 
                nextBullet.init(x, y, (float)angle);

            controler.addBullet(nextBullet);
            nextBullet = null;
            shooting_delay = SHOOTING_DELAY;
        }
        if (shooting_delay > 0) shooting_delay--;
    }

    public void hit() { 
        hit = 50;
    }

    public void giveBullet(Bullet b) {
        nextBullet = b;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_S) yv = 5;
        if (e.getKeyCode() == KeyEvent.VK_W) yv = -5;
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S) yv = 0;
    }

    public void mousePressed() {
        shooting = true;
    }

    public void mouseReleased() {
        shooting = false;
    }
   
    public void mouseMoved(float dx, float dy) {
        if (hit > 0) return;
	angle -= Math.atan(dy / dx);
    }
}
