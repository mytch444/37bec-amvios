import java.awt.Color;

public class ExplosiveBulletEnemy extends EnemyPart {
    
	public ExplosiveBulletEnemy(GameControler c) {
        super(c, new Color(255, 0, 0));
	}

    public boolean collides(Player p) {
        if (collidesSquare(p)) {
            controler.givePlayerBullet(new ExplosiveBullet(controler));
            return true;
        }
        return false;
    }

    public boolean collides(Bullet b) {
        if (collidesSquare(b)) {
            controler.givePlayerBullet(new ExplosiveBullet(controler));
            hit(b);
            return true;
        }
        return false;
    }
}
