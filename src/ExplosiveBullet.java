/*
 * A bullet that explodes upon collision and gives the player another, or not.
 */

import java.awt.event.*;
import java.awt.*;
import java.lang.Math;

public class ExplosiveBullet extends Bullet {

    // The number of extra bullets to give the player.
    int more;

    public ExplosiveBullet(GameControler c, int m) {
        super(c);
        color = new Color(255, 0, 0);
        more = m;
    }

    public ExplosiveBullet(GameControler c) {
        this(c, 5);
    }

    public void init(float x, float y, float angle) {
	super.init(x, y, angle, false);
	controler.playSound(GameSound.EXPLOSIVE_SHOT);
    }
    
    // Give a whole heap of bullets going everywhere and just generally being annoying.
    public void hitSomething() {
        super.hitSomething();
        for (float a = -3.14f; a < 3.14f; a += 0.5f) {
	    Bullet b = new Bullet(controler);
	    b.init(x, y, a, false);
            controler.addBullet(b);
        }
	controler.playSound(GameSound.EXPLOSIVE_EXPLODE);
    }

    public short getDelay() {
	return (short) (SHOOTING_DELAY * 2);
    }

    public Bullet nextBullet() {
	if (more > 0) return new ExplosiveBullet(controler, more - 1);
	return null;
    }
}
