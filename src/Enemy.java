import java.util.Random;
import java.lang.Math;
import java.awt.Graphics;
import java.awt.Color;

public class Enemy extends Part {
    
    public static String[] PATTERNS = {"***\n*\n*\n***\n", "*\n*\n*\n*\n", "***\n***\n***\n", "*\n", "***\n", "*\n**\n***\n**\n*\n", "* * *\n * * \n* * *\n"};

    EnemyPart[] parts;
    boolean shot;

	public Enemy(GameControler co, String pattern) {
        super(co, Color.white);
        
        // Give it a random location on the screen and a random velocity.
        x = rand.nextInt(controler.getWidth()) - controler.getWidth() - 100;
        y = rand.nextInt(controler.getHeight() - 200) + 100;
        xv = rand.nextInt(5);
        yv = rand.nextInt(10) - 5;
        shot = false;

        double px, py, pw, ph;
        int len = 0;
        for (int i = 0; i < pattern.length(); i++) if (pattern.charAt(i) == '*') len++;
        parts = new EnemyPart[len];

        px = x;
        py = y;
        pw = 40;
        ph = 40;

        int i = 0;
        h = w = 0;
        for (int c = 0; c < pattern.length(); c++) {
            if (pattern.charAt(c) == '\n') {
                py += ph; 
                h += ph;
                if (px - x > w) w = px - x;
                px = x;
                continue;
            } else if (pattern.charAt(c) == '*') {
                parts[i++] = new EnemyPart(controler, pw, ph, px, py, xv, yv, color); 
            }

            px += pw;
        }
	}

	public void paint(Graphics g) {
        for (int i = 0; i < parts.length; i++)
            parts[i].paint(g);
	}

    public void update() {
        if (!shot) {
            x += xv;
            y += yv;
        }

        alive = false;
        for (int i = 0; i < parts.length; i++) {
            EnemyPart p = parts[i];
            if (p.isAlive()) alive = true;
            p.update();
        }

        if (!alive) {
            controler.addScore(controler.SCORE_SHOT * 2);
            controler.removeOther(this);
        }
    }

    public boolean collides(Player p) {
        for (int i = 0; i < parts.length; i++) {
            EnemyPart e = parts[i];
            if (e.collides(p)) {
                controler.removeOther(this);
                return true;
            }
        }
        return false;
    }

    public boolean collides(Bullet b) {
        boolean hit = false;
        boolean wasshot = shot;
        for (int i = 0; i < parts.length; i++) {
            EnemyPart e = parts[i];
            if (e.collides(b)) {
                shot = true;
                e.hit(b);
                controler.addScore(controler.SCORE_SHOT);
                hit = true;
            }    
        }

        if (wasshot != shot) {
            for (int i = 0; i < parts.length; i++) {
                EnemyPart e = parts[i];
                e.setXV(rand.nextInt(5));
                e.setYV(rand.nextInt(10) - 5);
                e.setBounce(true);
            } 
        }

        return hit;
    }
}
