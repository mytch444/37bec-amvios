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
 * A friendly triangle, gives you heaps of stuff if you're nice to it. Takes all your hard
 * earned points away if you arn't.
 */

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.Random;
import javax.imageio.ImageIO;

public class Friend extends Part {
    
    BufferedImage image, imageHit;
    short lives;
    short hit;
    boolean showHit; // If this is true then I'll draw a different image.

    public Friend(GameControler c) {
        super(c, null);
        
        // Give it a random location on the screen and a random velocity.
        x = rand().nextInt(controler.getWidth()) - controler.getWidth();
        y = rand().nextInt(controler.getHeight());
        xv = rand().nextInt(5);
        yv = rand().nextInt(10) - 5;
        lives = 3;

        hit = 0;
        showHit = false;

	// Load the images.
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/images/friend.png"));
            imageHit = ImageIO.read(getClass().getResourceAsStream("/images/friend_hit.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        w = (short) image.getWidth();
        h = (short) image.getHeight();
    }

    public void paint(Graphics2D g) {
	if (showHit) g.drawImage(imageHit, (int)(x - w / 2), (int) (y - h / 2), null);
        else g.drawImage(image, (int) (x - w / 2), (int) (y - h / 2), null);
    }

    public void update() {
	// If hit then decrease the time I'm hit for.
        if (hit > 0) {
	    // Toggle the showHit evey so often.
            if (hit % 10 == 0) showHit = !showHit;
            hit--;
        } else showHit = false;

        x += xv;
        y += yv;

	// Bounce of verticle edges.
        if ((y + h > controler.getHeight() && yv > 0) || (y < 0 && yv < 0)) yv = -yv;

	// If past the end then reward the player with a hand job, don't worry it's beyond the screen,
	// nobody will see it.
        if (x > controler.getWidth()) {
            controler.addScore(GameControler.SCORE_SHOT * 100); // <-- handjob.
            controler.removeOther(this);
        }
    }

    public boolean collides(Player p) {
        return false;
    }

    // Check if the players a c***, if he is and has been a couple times explode,
    // and then take all their points. Else just yell at them. If they're rather nice then
    // don't do anything.
    public boolean collides(Bullet b) {
	if (b.getClass() == WallLaserBullet.class) return false;
        if (collidesSquare(b) && hit == 0) {
            if (lives < 1) {
                for (float a = -3.14f; a < 3.14f; a += 0.5f) controler.addBullet(new Bullet(controler, x, y, a));
                controler.removeOther(this);
                controler.addScore(GameControler.SCORE_SHOT * -100);
		controler.playSound(GameSound.FRIEND_HIT);
            } else {
                showHit = true;
                hit = 50;
                lives--;
            }
            return true;
        }
        return false;
    }
}
