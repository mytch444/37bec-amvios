/*
 *
 * Copyright: 2014 Mytchel Hammond <mytchel.hammond@gmail.com>
 *
 * 37bec-amvios is free software: you can redistribute it and/or modify
 * it under the term of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the Licence, or
 * (at your option) any later version.
 * 
 * 37bec-amvios is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License 
 * along with 37bec-amvios. If not, see <http://www.gnu.org/licenses/>
 *
 * --------------------------------------------------------------------
 *
 * The player. A triangle that they control. It's very evil, it allows them to kill things.
 * Clearly that is this things fault, not the fault of the person using it. And definatally
 * not the fault of the game that put them in a situation where they should kill things.
 * Silly logic, always getting in the way.
 */

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.lang.Math;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.LinkedList;

public class Player extends Part {
    LinkedList<Bullet> nextBullet;
    LinkedList<Bullet> nextPowerup;

    float angle;
    short hit;
    boolean showHit;

    boolean shooting;
    short shootingDelay;
    short powerupTime;

    BufferedImage image, imageHit; 

    public Player(GameControler c) {
        super(c, null);
        angle = 0;
        shooting = false;
	shootingDelay = 0;
	nextBullet = new LinkedList<Bullet>();
	nextPowerup = new LinkedList<Bullet>();
	powerupTime = 0;
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

    public void paint(Graphics2D g) {
	float a = angle;
	short x = (short) this.x;
	short y = (short) this.y;
	g.rotate(a, x, y);
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

        if (shooting && shootingDelay == 0) {
	    Bullet b;
	    if (nextBullet.peekFirst() == null)
		b = new Bullet(controler);
	    else
		b = nextBullet.remove();

	    b.init(x, y, angle);

	    shootingDelay = b.getDelay();
	    controler.addBullet(b);
	    
	    if (b.nextBullet() != null)
		nextBullet.push(b.nextBullet());
	}
        if (shootingDelay > 0) shootingDelay--;

	// If there is a powerup the initialise it when the last one is done.
	if (powerupTime > 0) powerupTime--;
	else if (powerupTime == 0 && nextPowerup.peekFirst() != null) {
	    Bullet b = nextPowerup.remove();
	    b.init(x, y, angle);
	    controler.addBullet(b);
	    powerupTime = b.getDelay();
	}
    }

    public void hit() { 
        hit = 50;
	controler.playSound(GameSound.PLAYER_HIT);
    }

    public void giveBullet(Bullet b) {
	nextBullet.addLast(b);
    }

    public void givePowerup(Bullet b) {
	nextPowerup.addLast(b);
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
   
    public void mouseMoved(int cx, int cy) {
        if (hit > 0) return;
	float dx = x - cx;
	float dy = y - cy;
	if (dx == 0) angle = 0;
	else angle = (float) Math.atan(dy / dx);
    }
}
