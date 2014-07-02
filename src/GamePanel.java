/*
 * A custom panel that handles the game and menus.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.image.*;
import java.io.*;

public class GamePanel extends JPanel {
    public static int TIME = 1000 / 50;

    // Panel event codes would be a good name I suppose.
    public static int NEW_GAME = 0;
    public static int HIGH_SCORE_MENU = 1;
    public static int QUIT = 2;
    public static int PAUSE = 3;

    // Things that get shown depending on their state.
    GameControler controler;
    HighscoreMenu hsmenu;
    GameStartMenu gsmenu;
    GameMenu menu;

    // This will handle all sounds. Having one will hopefully help a lot rather than having lag with
    // just bullet sounds. And that's the normal bullets. I don't even want to know how the lasers would
    // work.
    GameSound sound;
    
    // A thread that will continue to update this panel every so often (60 times a second or so).
    UpdaterThread uloop;
    // A thread that will repaint the panel as soon as it has finished painting the panel.
    RenderThread rloop;
    boolean painting;
    
    int mode;

    // THE font.
    Font font;

    // Contructor for the class, this gets called when a new instance of this class is made.
    public GamePanel(int w, int h) {
	super(null, true);

        // Set the bounds and background.
	setBounds(0, 0, w, h);
        setBackground(Color.black);

	// Get THE font.
        try {
            InputStream is = getClass().getResourceAsStream("/font/UbuntuMono-R.ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(16f);
        } catch (Exception e) {
            System.out.println("Using default monospace font.");
            font = new Font("monospaced", Font.PLAIN, 16);
        }

	sound = new GameSound();
        
        mode = -1;
        painting = false;

        // Create and start the threads.
        uloop = new UpdaterThread(this, TIME);
        rloop = new RenderThread(this);
        uloop.start();
        rloop.start();
    }

    public void paintComponent(Graphics g) {
        painting = true;

	requestFocus();

        if (mode == -1) {
            g.setFont(font);
            menu = new GameMenu(this, getWidth(), 50, 0, getHeight() - 50);
            setMode(NEW_GAME);
        }
        
        controler.paint(g);
        menu.paint(g);
       
        if (hsmenu != null) 
            hsmenu.paint(g); 
        if (gsmenu != null)
            gsmenu.paint(g);

        painting = false;
    }  

    public void update() {
        if (controler == null) return;
        controler.update();
    }

    public void setMode(int m) {
        mode = m;

        if (m == NEW_GAME) newMenuGame();
        else if (m == HIGH_SCORE_MENU) showHighScores();
        else if (m == QUIT) quit();
        else if (m == PAUSE) pause();
    }

    private void newMenuGame() {
        hsmenu = null;
        gsmenu = new GameStartMenu(this);
        controler = new GameControler(this, getWidth(), getHeight() - menu.getHeight());
    }

    public void startGame() {
        gsmenu = null;
        controler.resume();
    }

    private void showHighScores() {
	controler.pause();
        hsmenu = new HighscoreMenu(this);
        gsmenu = null;
    }

    public void pause() {
        hsmenu = null;
        gsmenu = null;
        controler.togglepause();
    }

    public boolean isPaused() {
        return controler.isPaused();
    }

    // Call this to close the frame and exit the program.
    public void quit() {
	rloop.end();
        uloop.end();
        ((JFrame) SwingUtilities.getRoot(this)).dispose();
    }

    public Font getFont() {
        return font;
    }

    public boolean frameDone() {
        return !painting;
    }

    public void playSound(int s) {
	sound.play(s);
    }
}
