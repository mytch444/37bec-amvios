/*
 * A stream of death.
 */

import java.awt.event.*;
import java.awt.*;
import java.lang.Math;

public class LaserBullet extends Bullet {

    int more;
    Bullet previous;

    public LaserBullet(GameControler c, int m, Bullet p) {
        super(c);
        color = new Color(0, 255, 255);
	more = m;
	previous = p;
    }

    public LaserBullet(GameControler c) {
        this(c, 200, null);
    }

    public void init(float x, float y, float a) {
	super.init(x, y, a, false);
	if (more % 5 == 0) // I don't want it to play too many.
	    controler.playSound(GameSound.LASER);
    }

    public void paint(Graphics g) {
	g.setColor(color);
	if (previous == null) g.drawLine((int) x, (int) y, (int) (x - xv), (int) (y - yv));
	else g.drawLine((int) previous.getX(), (int) previous.getY(), (int) x, (int) y);
    }

    public short getDelay() {
	return (short) 2;
    }

    public Bullet nextBullet() {
	if (more > 0) return new LaserBullet(controler, more - 1, this);
	return null;
    }
}
