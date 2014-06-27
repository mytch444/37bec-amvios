import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;
import javax.imageio.ImageIO;

public class Friend extends Part {
    
    BufferedImage image, imageHit;
    int lives;
    int hit;
    boolean showHit;

	public Friend(GameControler c) {
        super(c, null);
        
        // Give it a random location on the screen and a random velocity.
        x = rand.nextInt(controler.getWidth()) - controler.getWidth();
        y = rand.nextInt(controler.getHeight());
        xv = rand.nextInt(5);
        yv = rand.nextInt(10) - 5;
        lives = 3;

        hit = 0;
        showHit = false;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/images/friend.png"));
            imageHit = ImageIO.read(getClass().getResourceAsStream("/images/friend_hit.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        w = image.getWidth();
        h = image.getHeight();
	}

	public void paint(Graphics g) {
        if (showHit) g.drawImage(imageHit, (int)(x - w / 2), (int) (y - h / 2), null);
        else g.drawImage(image, (int) (x - w / 2), (int) (y - h / 2), null);
	}

    public void update() {
        if (hit > 0) {
            if (hit % 10 == 0) showHit = !showHit;
            hit--;
        } else showHit = false;

        x += xv;
        y += yv;

        if ((y + h > controler.getHeight() && yv > 0) || (y < 0 && yv < 0)) yv = -yv;

        if (x > controler.getWidth()) {
            controler.addScore(GameControler.SCORE_SHOT * 100);
            controler.removeOther(this);
        }
    }

    public boolean collides(Player p) {
        return false;
    }

    public boolean collides(Bullet b) {
        if (collidesSquare(b) && hit == 0) {
            if (lives < 1) {
                for (float a = -3.14f; a < 3.14f; a += 0.5f) controler.addBullet(new Bullet(controler, x, y, a));
                controler.removeOther(this);
                controler.addScore(GameControler.SCORE_SHOT * -100);
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
