/*
 * Class to control a game.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.image.VolatileImage;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.image.*;
import java.util.Random;

public class GameControler implements MouseListener, MouseMotionListener, KeyListener {

    public static int SCORE_SHOT = 500;

    // Some variables such as how long there is left in the game, the target and the score.
    GamePanel panel; 
    Player player;
    ArrayList<Part> others;
    ArrayList<Bullet> bullets;
    long score;
    int addChance;
    int lives;
    int width, height;
    boolean paused;
    boolean end;
    HighscoreBox hsbox;
    Random rand;

    FontMetrics metrics;

    VolatileImage frame;

	public GameControler(GamePanel p, int w, int h) {
        panel = p;

        rand = new Random();
        metrics = p.getGraphics().getFontMetrics(p.getFont());
        width = w;
        height = h;

        player = new Player(this);
        others = new ArrayList<Part>();
        for (int i = 0; i < 10; i++)
            others.add(newEnemy());
        bullets = new ArrayList<Bullet>();

        score = 0;
        lives = 3;
        addChance = 1000;

        paused = true;
        end = false;

        // Listen to mouse and key events on the panel, this is so I can figure out if
        // the target was clicked by some faggot playing this game.
        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
        panel.addKeyListener(this);
	}

	public void paint(Graphics g) {
        renderFrame();

        if (frame == null) {
            frame = panel.createVolatileImage(width, height);
        }

        g.drawImage(frame, 0, 0, null);
    }

    public void renderFrame() {
        if (frame == null) return;
        Graphics g = frame.createGraphics();
        g.setFont(panel.getFont());

        g.setColor(Color.black);
        g.fillRect(0, 0, width, height);

        for (int i = 0; i < bullets.size(); i++)
            bullets.get(i).paint(g);
        player.paint(g);
        for (int i = 0; i < others.size(); i++)
            others.get(i).paint(g);

        g.setColor(Color.white);
        g.drawString("Score: " + score + "       Lives: " + lives, 5, 20);


        if (paused || end) {
            g.setColor(Color.white);
            String message = "Paused";
            if (end) message = "Game Over";
            g.drawString(message, getWidth() / 2 - metrics.stringWidth(message) / 2, getHeight() / 2 - metrics.getHeight());
            if (end) hsbox.paint(g);
        }

        g.dispose();
    }

    public void update() {
        if (paused || end) return;
        player.update();
        
        if (rand.nextInt(1000) == 0) others.add(new Friend(this));
        if (rand.nextInt(addChance--) == 0) {
            others.add(newEnemy());
            if (addChance < 50) addChance = 50;
        }

        for (int i = 0; i < bullets.size(); i++)
            bullets.get(i).update();
        
        for (int i = 0; i < others.size(); i++) {
            Part p = others.get(i);
            if (!p.isAlive()) {
                removeOther(p);
                continue;
            }
            p.update();

            if (p.collides(player)) {
                player.hit();
                score += SCORE_SHOT * 10;
            }

            for (int j = 0; j < bullets.size(); j++) {
                Bullet b = bullets.get(j);
                if (p.collides(b)) b.hitSomething();
            }
        }

        if (lives < 1) {
            if (hsbox == null) hsbox = new HighscoreBox(this, score);
            end = true;
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public long getScore() {
        return score;
    }

    public void addScore(long s) {
        score += s;
    }

    public void lowerLives() {
        lives--;
    }

    public void increaseLives() {
        lives++;
    }

    public void pause() {
        paused = true;
    }

    public void resume() {
        paused = false;
    }
    
    public void togglepause() {
        paused = !paused;
    }

    public boolean isPaused() {
        return paused;
    }

    public void givePlayerBullet(Bullet b) {
        player.giveBullet(b);
    }

    public void addBullet(Bullet b) {
        bullets.add(b);
    }

    public void removeBullet(Bullet b) {
        bullets.remove(b);
    }

    public void removeOther(Part p) {
        others.remove(p);
    }

    public void addOther(Part p) {
        others.add(p);
    }

    public Part newEnemy() {
        Part e;
        switch (rand.nextInt(10)) {
            case 1: e = new ExplosiveBulletEnemy(this);
                    break;
            case 2: e = new ExplosiveBulletEnemy(this);
                    break;
            case 3: e = new GoldEnemy(this);
                    break;
            default: e = new Enemy(this, Enemy.PATTERNS[rand.nextInt(Enemy.PATTERNS.length)]);
                     break;
        }
        
        return e;
    }

    public Graphics getGraphics() {
        return frame.createGraphics();
    }

    public GamePanel getPanel() {
        return panel;
    }

    // Listens to mouse pressed events on the panel.
    public void mousePressed(MouseEvent e) {
        if (paused || end) return;
        player.mousePressed(e);
    }

    public void mouseReleased(MouseEvent e) {
        if (paused || end) return;
        player.mouseReleased(e);
    }
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}

    public void mouseDragged(MouseEvent e) {
        if (paused || end) return;
        player.mouseMoved(e);
    }

    public void mouseMoved(MouseEvent e) {
        if (paused || end) return;
        player.mouseMoved(e);
    }

    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == ' ') panel.setMode(GamePanel.PAUSE);
    }

    public void keyPressed(KeyEvent e) {
        if (paused || end) return;
        player.keyPressed(e);
    }
    public void keyReleased(KeyEvent e) {
        if (paused || end) return;
        player.keyReleased(e);
    }
}
