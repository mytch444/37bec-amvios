/*
 * A shotgun to the head.
 */

import java.awt.event.*;
import java.awt.*;
import java.lang.Math;

public class ScatterBullet extends Bullet {

    // The number of extra bullets to give the player.
    int more;

    public ScatterBullet(GameControler c, int m) {
        super(c);
        color = new Color(255, 0, 255);
        more = m;
    }

    public ScatterBullet(GameControler c) {
        this(c, 10);
    }

    public void initNoMore(float x, float y, float angle) {
	super.init(x, y, angle, false);
    }
    
    public void init(float x, float y, float angle) {
	super.init(x, y, angle, false);

	for (float a = angle - 0.15f; a < angle + 0.15f; a += 0.05f) {
	    ScatterBullet b = new ScatterBullet(controler, 0);
	    b.initNoMore(x, y, a);
	    controler.addBullet(b);
	}

	controler.playSound(GameSound.EXPLOSIVE_SHOT);
    }
    
    public short getDelay() {
	return (short) (SHOOTING_DELAY * 4);
    }

    public Bullet nextBullet() {
	if (more > 0) return new ScatterBullet(controler, more - 1);
	return null;
    }
}
