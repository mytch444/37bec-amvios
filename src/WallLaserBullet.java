/*
 * A bullet that kills any enemies and friends that try go past the wall.
 */

import java.awt.event.*;
import java.awt.*;
import java.lang.Math;

public class WallLaserBullet extends Bullet {

    int time;
    
    public WallLaserBullet(GameControler c, int t) {
        super(c);
        color = new Color(50, 150, 255);
	time = t;
    }

    public WallLaserBullet(GameControler c) {
	this(c, 200);
    }

    public void init(float x, float y, float angle) {
	this.x = controler.getWidth() - 10;
	this.y = controler.getHeight();
	this.xv = 0;
	this.yv = controler.getHeight();
	//	controler.playSound(GameSound.EXPLOSIVE_SHOT);
    }

    public void update() {
	time--;
	if (time < 0) controler.removeBullet(this);
    }

    public void hitSomething() {}
}
