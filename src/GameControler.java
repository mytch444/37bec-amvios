/*
 *          DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE
 *                    Version 2, December 2004
 *
 * Copyright (C) 2014 Mytchel Hammond
 *
 * Everyone is permitted to copy and distribute verbatim or modified
 * copies of this file, and changing it is allowed as long
 * as the name is changed.
 *
 *            DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE
 *   TERMS AND CONDITIONS FOR COPYING, DISTRIBUTION AND MODIFICATION
 *
 *  0. You just DO WHAT THE FUCK YOU WANT TO.
 *
 * -----------------------------------------------------------------
 *
 *
 * Class to control a game.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.image.VolatileImage;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.image.*;
import java.util.Random;
import java.io.*;

public class GameControler implements MouseListener, MouseMotionListener, KeyListener {

    public static int SCORE_SHOT = 500;
    
    GamePanel panel;

    // Stuff that gets updated and draw.
    Player player;
    ArrayList<Part> others;
    ArrayList<Bullet> bullets;
    Star[] stars; // It's full of stars!!!

    // Stuff for the current highscore, must feed the competition.
    String highscoreHolder;
    long highscore;
    String winningMessage, competitionMessage, highscoreMessage;
    boolean isHighscore;

    long score;
    int lives;
    
    // Chance of another part being added.
    int addChance;

    // Width and height of the game area.
    int width, height;

    boolean paused;
    boolean end;
    HighscoreBox hsbox;
    
    // Object for generating random numbers, this is used by all parts also. Probably
    // has some efficiancy benifits with that but I don't actually know. I wouldn't recomend
    // doing that sort of thing.
    Random rand;

    // Cursor location, so it gets reset when the player moves so it always points towards the cursor.
    int cx, cy;

    // Stuff for the font, to work out string widths and so on.
    FontMetrics metrics;

    public GameControler(GamePanel p, int w, int h) {
        panel = p;

        rand = new Random();
        width = w;
        height = h;

        metrics = p.getGraphics().getFontMetrics(p.getFont());

	// Full it with stars.
	int sl = w / 5 * h / 5 / 500;
	stars = new Star[sl];
	for (short i = 0; i < sl; i++) stars[i] = new Star(this);
	
        player = new Player(this);
        others = new ArrayList<Part>();
        for (short i = 0; i < 10; i++)
            others.add(newEnemy());
        bullets = new ArrayList<Bullet>();

        readHighscore(); // Set highscore stuff for initialisng players macostic side.
	
        score = 0;
        lives = 3;
        addChance = 1000;

        paused = true;
        end = false;

        // Listen to mouse and key events on the panel.
        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
        panel.addKeyListener(this);
    }

    public void paint(Graphics2D g) {
        String message;
	short i;

	for (i = 0; i < stars.length; i++)
	    stars[i].paint(g);

        for (i = 0; i < bullets.size(); i++)
            bullets.get(i).paint(g);

        player.paint(g);

        for (i = 0; i < others.size(); i++)
            others.get(i).paint(g);
	
        g.setColor(Color.white);
        g.drawString("Score: " + score + "       Lives: " + lives, 20, 20);
	g.drawString(highscoreMessage, getWidth() - metrics.stringWidth(highscoreMessage) - 20, 20);
		
	// If paused or the game has ended show such.
        if (paused) {
            g.setColor(Color.white);
            if (end) {
		message = "Game Over";
		g.drawString(message,
			     getWidth() / 2 - metrics.stringWidth(message) / 2,
			     getHeight() / 2 - metrics.getHeight());
	    } else {
		message = "Paused";
		g.drawString(message,
			     getWidth() / 2 - metrics.stringWidth(message) / 2,
			     getHeight() / 2 - metrics.getHeight());
		message = "Press 'q' to resume.";
		g.drawString(message,
			     getWidth() / 2 - metrics.stringWidth(message) / 2,
			     getHeight() / 2);
	    }
	    // If it's ended paint the highscores box.
            if (end && hsbox != null) hsbox.paint(g);
        }
    }
    
    public void update() {
        if (paused) return;
	short i, j;
	long start, time;

        player.update();

        for (i = 0; i < bullets.size(); i++)
            bullets.get(i).update();

        if (rand.nextInt(1000) == 0) others.add(new Friend(this));
        if (rand.nextInt(addChance--) == 0) {
            others.add(newEnemy());
            if (addChance < 30) addChance = 30;
        }

        for (i = 0; i < others.size(); i++) {
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

	    start = System.currentTimeMillis();
            for (j = 0; j < bullets.size(); j++) {
		Bullet b = bullets.get(j);
                if (p.collides(b)) {
		    b.hitSomething();
		    if (!p.isAlive()) break;
		}
	    }
	    time = System.currentTimeMillis() - start;
	    if (time > 5) System.out.println("bullet collision: " + time);	
	}
	
	if (!isHighscore && score > highscore) {
	    isHighscore = true;
	    highscoreMessage = winningMessage;
	} else if (isHighscore && score < highscore) {
	    isHighscore = false;
	    highscoreMessage = competitionMessage;
	}

	if (lives < 1) {
            if (hsbox == null) {
		if (HighscoreBox.isHighscore(score))
		    hsbox = new HighscoreBox(this, score);
		else
		    panel.setMode(GamePanel.HIGHSCORE_MENU);
	    }
            end = true;
	    paused = true;
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
	if (end) return;
        paused = false;
    }
    
    public void togglepause() {
	if (paused) resume();
	else pause();
    }

    public boolean isPaused() {
        return paused;
    }

    public void givePlayerBullet(Bullet b) {
        player.giveBullet(b);
    }

    public void givePlayerPowerup(Bullet b) {
	player.givePowerup(b);
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

    // Returns a new enemy of random sort.
    public Part newEnemy() {
        Part e;

	if (score != 0 && score % 20000 == 0) {
	    e = new BossEnemy(this);
	} else {
	    switch (rand.nextInt(20)) {
	    case 1: e = new ExplosiveBulletEnemy(this);
		break;
	    case 2: e = new ExplosiveBulletEnemy(this);
		break;
	    case 3: e = new ExplosiveBulletEnemy(this);
		break;
	    case 4: e = new ExplosiveBulletEnemy(this);
		break;
	    case 5: e = new GoldEnemy(this);
		break;
	    case 6: e = new GoldEnemy(this);
		break;
	    case 7: e = new ScatterBulletEnemy(this);
		break;
	    case 8: e = new KTSBulletEnemy(this);
		break;
	    case 9: e = new WallLaserBulletEnemy(this);
		break;
	    default: e = new Enemy(this);
		break;
	    }
	}
	
        return e;
    }

    public GamePanel getPanel() {
        return panel;
    }

    // Reads the highscores file to get the name and score of the best.
    public void readHighscore() {
        highscore = 0;
        highscoreHolder = "nobody";
	competitionMessage = "Try beat '" + highscoreHolder + "' with " + highscore;
	winningMessage = "Who da man? You da man!";
	isHighscore = false;
	highscoreMessage = competitionMessage;
	
        try {

            File file = Game.HIGHSCORES_FILE();

            BufferedReader br = new BufferedReader(new FileReader(file));

            String line = br.readLine();
            if (line == null) return;

            String[] parts = line.split(":");
            if (parts.length != 2) return;
	    highscoreHolder = parts[0];
	    highscore = Long.parseLong(parts[1]);
	    competitionMessage = "Try beat '" + highscoreHolder + "' with " + highscore;
	    highscoreMessage = competitionMessage;
        } catch (Exception e) {
            e.printStackTrace();
	}
    }

    public Random getRandom() {
        return rand;
    }

    public int playSound(int s) {
	return panel.playSound(s);
    }

    public void stopSound(int s, int i) {
	panel.stopSound(s, i);
    }

    public void mousePressed(MouseEvent e) {
        if (paused || end) return;
        player.mousePressed();
    }

    public void mouseReleased(MouseEvent e) {
        if (paused || end) return;
        player.mouseReleased();
    }
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}

    public void mouseDragged(MouseEvent e) {
        mouseMoved(e);
    }

    public void mouseMoved(MouseEvent e) {
        if (paused) return;
	cx = e.getX();
	cy = e.getY();
        player.mouseMoved(cx, cy);
    }

    public void keyTyped(KeyEvent e) {
        if (end) return;
        if (e.getKeyChar() == 'q') {
	    System.out.println("pause");
	    panel.setMode(GamePanel.PAUSE);
	}
    }

    public void keyPressed(KeyEvent e) {
        if (paused) return;
        player.keyPressed(e);
	player.mouseMoved(cx, cy);
    }
    
    public void keyReleased(KeyEvent e) {
        if (paused) return;
        player.keyReleased(e);
	player.mouseMoved(cx, cy);
    }
}
