/*
 * Killing Them Slowly.
 */

import java.awt.event.*;
import java.awt.*;
import java.lang.Math;

public class KTSBullet extends Bullet {

    // The number of extra bullets to give the player.
    int more;

    public KTSBullet(GameControler c, int m) {
        super(c);
	speed = 10;
        color = new Color(50, 50, 50);
        more = m;
    }

    public KTSBullet(GameControler c) {
        this(c, 50);
    }

    public void init(float x, float y, float angle) {
	super.init(x, y, angle, false);

	if (more % 10 == 0)
	    controler.playSound(GameSound.KTS_SHOT);
    }
    
    public short getDelay() {
	return (short) (1);
    }

    public Bullet nextBullet() {
	if (more > 0) return new KTSBullet(controler, more - 1);
	return null;
    }
}
