/*
 * The target you must click repeatidly to flip some bits that represent your worth.
 */

import java.awt.*;
import java.util.Random;
import java.lang.Math;
import java.util.ArrayList;

public class Enemy extends Part {
    
    public static String[] PATTERNS = {"***\n*\n*\n***\n", "*\n*\n*\n*\n", "***\n***\n***\n", "*\n", "*\n", "*\n", "***\n", "*\n**\n***\n**\n*\n"};

    ArrayList<EnemyPart> parts;
    boolean shot;
    Color color;

    public Enemy(GameControler c, String pattern) {
        this(c, pattern, Color.white);
    }

	public Enemy(GameControler c, String pattern, Color bg) {
        super(c);
        
        // Give it a random location on the screen and a random velocity.
        x = rand.nextInt(controler.getWidth()) - controler.getWidth() - 100;
        y = rand.nextInt(controler.getHeight() - 200) + 100;
        xv = rand.nextInt(5);
        yv = rand.nextInt(10) - 5;
        shot = false;

        color = bg;

        double px = x;
        double py = y;
        double pw = 40;
        double ph = 40;
        double lw = 0;

        h = w = 0;
        parts = new ArrayList<EnemyPart>();
        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) == '\n') {
                py += pw; 
                px = x;
                h += ph;
                if (lw > w) w = lw;
                lw = 0;
            } else {
                parts.add(new EnemyPart(this, pw, ph, px, py, xv, yv, Color.black, color)); 
                px += pw;
                lw += pw;
            }
        }
	}

	public void paint(Graphics g) {
        for (int i = 0; i < parts.size(); i++)
            parts.get(i).paint(g);
	}

    public void update() {
        if (!shot) {
            x += xv;
            y += yv;
/*            if (y < 0 || y + h > controler.getHeight()) {
                yv = -yv;
                for (int i = 0; i < parts.size(); i++) parts.get(i).setYV(yv);
            } */
        }

        for (int i = 0; i < parts.size(); i++) {
            EnemyPart p = parts.get(i);
            p.update();

            if (p.getX() > controler.getWidth()) {
                remove(p);
                controler.lowerLives();
            }
            
            if (shot && ((p.getYV() > 0 && p.getY() + p.getHeight() > controler.getHeight()) || (p.getYV() < 0 && p.getY() < 0))) {
                p.setYV(-p.getYV());
            }
        }
    }

    public boolean collides(Player p) {
        boolean hit = false;
        for (int i = 0; i < parts.size(); i++) {
            EnemyPart e = parts.get(i);
            if (e.collides(p)) {
                parts.remove(e);
                hit = true;
            }
        }
        return hit;
    }

    public boolean collides(Bullet b) {
        boolean hit = false;
        boolean wasshot = shot;
        for (int i = 0; i < parts.size(); i++) {
            EnemyPart e = parts.get(i);
            if (e.collides(b)) {
                shot = true;
                parts.remove(e);
                controler.addScore(controler.SCORE_SHOT);
                hit = true;
            }
        }

        if (wasshot != shot) {
            for (int i = 0; i < parts.size(); i++) {
                EnemyPart e = parts.get(i);
                e.setXV(rand.nextInt(5));
                e.setYV(rand.nextInt(10) - 5);
            } 
        }
   
        if (parts.size() == 0) {
            controler.addScore(controler.SCORE_SHOT * 2);
            controler.removeOther(this);
        }

        return hit;
    }

    public void remove(EnemyPart p) {
        parts.remove(p);
    }
}
