import java.awt.event.*;
import java.awt.*;
import java.lang.Math;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;

public class Player extends Part {
    private int SHOOTING_DELAY = 5;

    Bullet nextBullet;
    double angle;
    int moving;
    int hit;
    boolean showHit;

    boolean shooting;
    int shooting_delay;

    BufferedImage image, imageHit; 

	public Player(GameControler c) {
        super(c);
        w = 128;
        h = 128;
        x = c.getWidth();
        y = c.getHeight() / 2 - h / 2;
        angle = 0;
        moving = 0;
        shooting = false;
        shooting_delay = SHOOTING_DELAY;
        hit = 0;
        showHit = false;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/images/player.png"));
            imageHit = ImageIO.read(getClass().getResourceAsStream("/images/player_hit.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	public void paint(Graphics g1) {
        Graphics2D g = (Graphics2D) g1;

        g.rotate(angle, x, y);
        if (showHit) g.drawImage(imageHit, x - w / 2, y - h / 2, null);
        else g.drawImage(image, x - w / 2, y - h / 2, null);
        g.rotate(-angle, x, y);
	}

    public void update() {
        if (hit > 0) {
            if (hit % 10 == 0) showHit = !showHit;
            hit--;
            return;
        } else showHit = false;

        if (moving == 1) {
            y += 5;
            if (y > controler.getHeight()) y = controler.getHeight();
        }
        if (moving == -1) {
            y -= 5;
            if (y < 0) y = 0;
        }
        if (shooting && shooting_delay == 0) {
            if (nextBullet == null) 
                nextBullet = new Bullet(controler, x, y, angle);
            else 
                nextBullet.init(x, y, angle);

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
        if (e.getKeyCode() == KeyEvent.VK_S) moving = 1;
        if (e.getKeyCode() == KeyEvent.VK_W) moving = -1;
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S) moving = 0;
    }

    public void mousePressed(MouseEvent e) {
        shooting = true;
    }

    public void mouseReleased(MouseEvent e) {
        shooting = false;
    }
   
    public void mouseMoved(MouseEvent e) {
        if (hit > 0) return;
        double dy = y - e.getY();
        double dx = x - e.getX();

        if (dx <= 0) return;

        if (dy == 0) angle = 0;
        else angle = Math.atan(dy / dx);
    }
}
