import java.util.Random;
import java.lang.Math;
import java.awt.Graphics;
import java.awt.Color;

public class Enemy extends Part {
    
    public static String[] PATTERNS = {"***\n*\n*\n***\n", "*\n*\n*\n*\n", "***\n***\n***\n", "*\n", "***\n", "*\n**\n***\n**\n*\n", "* * *\n * * \n* * *\n"};

    EnemyPart[] parts;
    boolean shot;
    short ui, ri;

	public Enemy(GameControler co, String pattern) {
        super(co, Color.white);
        
        // Give it a rand()om location on the screen and a rand()om velocity.
        x = rand().nextInt(controler.getWidth()) - controler.getWidth() - 100;
        y = rand().nextInt(controler.getHeight() - 200) + 100;
        xv = rand().nextInt(5);
        yv = rand().nextInt(10) - 5;
        shot = false;

        short px, py, pw, ph;
        short len = 0;
        for (int i = 0; i < pattern.length(); i++) if (pattern.charAt(i) == '*') len++;
        parts = new EnemyPart[len];

        px = (short) x;
        py = (short) y;
        pw = 40;
        ph = 40;

        short i = 0;
        h = w = 0;
        for (short c = 0; c < pattern.length(); c++) {
            if (pattern.charAt(c) == '\n') {
                py += ph; 
                h += ph;
                if (px - x > w) w = (short) (px - x);
                px = (short) x;
                continue;
            } else if (pattern.charAt(c) == '*') {
                parts[i++] = new EnemyPart(controler, pw, ph, px, py, xv, yv, color); 
            }

            px += pw;
        }
	}

	public void paint(Graphics g) {
        for (ri = 0; ri < parts.length; ri++)
            parts[ri].paint(g);
	}

    public void update() {
        if (!shot) {
            x += xv;
            y += yv;
        }

        alive = false;
        for (ui = 0; ui < parts.length; ui++) {
            EnemyPart p = parts[ui];
            if (p.isAlive()) alive = true;
            p.update();
        }

        if (!alive) {
            controler.addScore(controler.SCORE_SHOT * 2);
            controler.removeOther(this);
        }
    }

    public boolean collides(Player p) {
        for (ui = 0; ui < parts.length; ui++) {
            if (parts[ui].collides(p)) {
                controler.removeOther(this);
                return true;
            }
        }
        return false;
    }

    public boolean collides(Bullet b) {
        boolean hit = false;
        boolean wasshot = shot;
        for (ui = 0; ui < parts.length; ui++) {
            EnemyPart e = parts[ui];
            if (e.collides(b)) {
                shot = true;
                e.hit(b);
                controler.addScore(controler.SCORE_SHOT);
                hit = true;
            }    
        }

        if (wasshot != shot) {
            for (ui = 0; ui < parts.length; ui++) {
                EnemyPart e = parts[ui];
                e.setXV(rand().nextInt(5));
                e.setYV(rand().nextInt(10) - 5);
                e.setBounce(true);
            } 
        }

        return hit;
    }
}
