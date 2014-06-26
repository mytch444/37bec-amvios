import java.awt.Color;

public class ExplosiveBulletEnemy extends EnemyPart {
    
	public ExplosiveBulletEnemy(GameControler c) {
        super(c, new Color(255, 0, 0));
	}

    public boolean collidesPart(Part p) {
        if (collidesSquare(p)) {
            controler.givePlayerBullet(new ExplosiveBullet(controler));
            hit();
            return true;
        }
        return false;
    }

    public boolean collides(Player p) {
        return collidesPart(p);
    }

    public boolean collides(Bullet b) {
        return collidesPart(b);
    }
}
