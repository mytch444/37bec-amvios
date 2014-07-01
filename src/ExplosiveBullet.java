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

    public void update() {
        super.update();

	// Give the player some more of me.
        if (more > 0)
            controler.givePlayerBullet(new ExplosiveBullet(controler, more - 1));
	// But no more after this.
        more = 0;
    }

    // Give a whole heap of bullets going everywhere and just generally being annoying.
    public void hitSomething() {
        super.hitSomething();
        for (float a = -3.14f; a < 3.14f; a += 0.5f) {
            controler.addBullet(new Bullet(controler, (int) x, (int) y, a));
        }
    }
}
