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
	speed = 20;
        color = new Color(150, 150, 150);
        more = m;
    }

    public KTSBullet(GameControler c) {
        this(c, 50);
    }

    public void init(float x, float y, float angle) {
	super.init(x, y, angle, GameSound.KTS_SHOT);
    }
    
    public short getDelay() {
	return (short) (3);
    }

    public Bullet nextBullet() {
	if (more > 0) return new KTSBullet(controler, more - 1);
	return null;
    }
}
