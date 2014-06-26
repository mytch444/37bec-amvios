import java.util.Random;
import java.lang.Math;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;

public class Enemy extends Part {
    
    public static String[] PATTERNS = {"***\n*\n*\n***\n", "*\n*\n*\n*\n", "***\n***\n***\n", "*\n", "*\n", "*\n", "***\n", "*\n**\n***\n**\n*\n"};

    ArrayList<EnemyPart> parts;
    boolean shot;

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
                parts.add(new EnemyPart(controler, pw, ph, px, py, xv, yv, color)); 
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
                e.hit();
            //    parts.remove(e);
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
