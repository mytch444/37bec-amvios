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
	super.init(x, y, angle, -1);
    }
    
    public void init(float x, float y, float angle) {
	super.init(x, y, angle, GameSound.SCATTER_SHOT);

	for (float a = -0.1f; a < 0.1f; a += 0.025f) {
	    ScatterBullet b = new ScatterBullet(controler, 0);
	    b.initNoMore(x, y, angle + a);
	    controler.addBullet(b);
	}
    }
    
    public short getDelay() {
	return (short) (SHOOTING_DELAY * 4);
    }

    public Bullet nextBullet() {
	if (more > 0) return new ScatterBullet(controler, more - 1);
	return null;
    }
}
