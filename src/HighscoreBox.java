/*
 * A box that lets the user input a name and saves that with the score to a file.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class HighscoreBox implements KeyListener {

    GameControler controler;
    long score;
    char[] name;
    int cursor;
    int x, y, w, h;
    FontMetrics metrics;
    boolean highscore;

	public HighscoreBox(GameControler c, long s) {
        controler = c;

        score = s;
        name = new char[64];
        for (int i = 0; i < 64; i++)
            name[i] = '\0';
        cursor = 0;
      
        metrics = controler.getGraphics().getFontMetrics(controler.getPanel().getFont());

        h = metrics.getHeight() * 4;
        w = metrics.stringWidth(" ") * 70;
        x = controler.getWidth() / 2 - w / 2;
        y = controler.getHeight() / 2 - h / 2;

        highscore = true;
 
        /*
         * Check the highscores file to see if this is a highscore.
         */
        try {
            // Create a file object from the filename.
            File file = new File(Game.HIGHSCORES_FILE());
            if (!file.exists()) file.createNewFile();

            // Create a buffered reader.
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            boolean ishighscore = false;
            int num = 0;
            // Loop through all the lines of the file.
            while ((line = br.readLine()) != null) {
                // For counting.
                num++;
               
                // Split the line to get the name and score.
                String[] parts = line.split(":");
                // If the players score is greater than this score then they have a highscore.
                if (Integer.parseInt(parts[1]) < score) {
                    ishighscore = true;
                }
            }
            // If there are less than ten scores then it is by default a highscore.
            if (num < 10) ishighscore = true;
            // If it is not a highscore then change to panel mode to view highscores.
            if (!ishighscore) {
                controler.getPanel().setMode(GamePanel.HIGH_SCORE_MENU);
                highscore = false;
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        // I am listening to EVERY KEY YOU PRESS!!!!!
        controler.getPanel().addKeyListener(this);
	}

	public void paint(Graphics g) {
        if (!highscore) return;

        g.setColor(Color.black);
        g.fillRect(x, y, w, h);
        g.setColor(Color.white);
        g.drawRect(x, y, w, h);

        String mess = "Highscore! Enter your name below:";
        g.drawString(mess, x + w / 2 - metrics.stringWidth(mess) / 2, y + h / 2 - metrics.getHeight() / 3 * 2);

        int len;
        for (len = 1; name[len] != '\0'; len++);

        int lx = x + w / 2 - metrics.stringWidth(new String(name, 0, len)) / 2;
        int ly = y + h / 2 + metrics.getHeight() / 3 * 2;
        g.drawString(new String(name), lx, ly);
        g.drawString("_", lx + metrics.stringWidth(new String(name, 0, cursor)), ly);
    }

    /*
     * Save the score to the file in order.
     */
    public void save() {
        if (score == -1)
            return;
        try {
            // Open the file and a temp of it.
            File tmp = new File(Game.HIGHSCORES_FILE() + "tmp");
            File file = new File(Game.HIGHSCORES_FILE());

            // If they don't exist then create them.
            if (!tmp.exists()) tmp.createNewFile();
            if (!file.exists()) file.createNewFile();

            BufferedReader br = new BufferedReader(new FileReader(file));
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(tmp)));

            int len;
            for (len = 0; name[len] != '\0'; len++);
            String aname = new String(name, 0, len);
            String line;
            boolean found = false;
            int num = 0;
            // Go through and check if the score goes in here.
            while ((line = br.readLine()) != null && num < 10) {
                String[] parts = line.split(":");

                // If I have not already found somewhere for it and this score is less than the new one put her in.
                if (!found && Integer.parseInt(parts[1]) < score) {
                    out.println(aname + ":" + score);
                    found = true;
                    num++;
                }
                // If there are now 10 scores exit.
                if (num >= 10) break;

                // Print the old one.
                out.println(line);
                num++;
            }
            // If I didn't find anywhere to put it but there are less than ten scores save it at the end.
            if (num < 10 && !found)
                out.println(aname + ":" + score);

            out.close();

            br = new BufferedReader(new FileReader(tmp));
            out = new PrintWriter(new BufferedWriter(new FileWriter(file)));

            // Write to the file from the tmp.
            while ((line = br.readLine()) != null) {
                out.println(line);
            }

            out.close();
            tmp.delete(); // Delete the tmp file.
         
            controler.getPanel().removeKeyListener(this);

            // Change the mode to view the highscores
            controler.getPanel().setMode(GamePanel.HIGH_SCORE_MENU);
            highscore = false; // Stops it from showing the box.
        } catch (Exception e) {
            System.out.println("An error occured when trying to save your score");
            e.printStackTrace();
        }
    }

    public void keyTyped(KeyEvent e) {
        int c = e.getKeyChar();

        if (c == '\n') save();
        else if (cursor < 63 && c >= ' ' && c <= '~') {
            for (int i = name.length - 2; i > cursor; i--) name[i] = name[i - 1];
            name[cursor++] = e.getKeyChar();
        }
    }
   
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();
        if (c == KeyEvent.VK_BACK_SPACE && cursor > 0) {
            for (int i = --cursor; name[i] != '\0'; i++) name[i] = name[i + 1];
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT && cursor > 0) {
            cursor--;
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT && name[cursor] != '\0') {
            cursor++;
        }
    }

    public void keyReleased(KeyEvent e) {}
}
