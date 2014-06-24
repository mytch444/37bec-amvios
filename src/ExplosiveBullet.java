/*
 * The target you must click repeatidly to flip some bits that represent your worth.
 */

import java.awt.event.*;
import java.awt.*;
import java.lang.Math;

public class ExplosiveBullet extends Bullet {
    
    public ExplosiveBullet(GameControler c) {
        super(c);
        color = new Color(255, 0, 0);
    }

    public void hitSomething() {
        super.hitSomething();
        for (double a = -3.14; a < 3.14; a += 0.5) {
            controler.addBullet(new Bullet(controler, (int) x, (int) y, a));
        }
    }
}
