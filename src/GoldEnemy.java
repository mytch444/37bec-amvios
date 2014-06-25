import java.awt.*;
import java.util.Random;
import java.lang.Math;
import java.util.ArrayList;

public class GoldEnemy extends Enemy {

    int maxW, maxH, minW, minH;

	public GoldEnemy(GameControler c) {
        super(c, "*\n", new Color(255, 255, 0));

        maxW = w * 2;
        maxH = h * 2;
        minW = w;
        minH = h;

        w = maxW;
        h = maxW;
        for (int i = 0; i < parts.size(); i++) {
            parts.get(i).setWidth(w);
            parts.get(i).setHeight(w);
        }
	}

    public boolean collides(Bullet b) {
        boolean hit = false;
        for (int i = 0; i < parts.size(); i++) {
            EnemyPart e = parts.get(i);
            if (e.collides(b)) {
                w -= 2;
                h -= 2;
                if (w < minW || h < minH) parts.remove(e);
                e.setX(e.getX() + e.getWidth() / 2 - w / 2);
                e.setY(e.getY() + e.getHeight() / 2 - h / 2);
                e.setWidth(w);
                e.setHeight(h);
                hit = true;
            }
        }

        if (parts.size() == 0) controler.removeOther(this);

        return hit;
    }

    public void remove(EnemyPart p) {
        parts.remove(p);
    }
}
