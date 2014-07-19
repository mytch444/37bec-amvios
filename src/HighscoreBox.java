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
    int len;
    int cursor;
    int x, y, w, h;
    FontMetrics metrics;
    String error;

    public HighscoreBox(GameControler c, long s) {
        controler = c;

        score = s;
        name = new char[64];
        for (int i = 0; i < 64; i++)
            name[i] = '\0';
        cursor = 0;
        len = 0;
      
        metrics = controler.getPanel().getGraphics().getFontMetrics(controler.getPanel().getFont());

        h = (int) (metrics.getHeight() * 5.5);
        w = metrics.stringWidth(" ") * 70;
        x = controler.getWidth() / 2 - w / 2;
        y = controler.getHeight() / 2 - h / 2;

        controler.getPanel().addKeyListener(this);
    }

    public void paint(Graphics2D g) {
        g.setColor(Color.black);
        g.fillRect(x, y, w, h);
        g.setColor(Color.white);
        g.drawRect(x, y, w, h);

        String mess = "Highscore! Enter your name below:";
        g.drawString(mess, x + w / 2 - metrics.stringWidth(mess) / 2, y + h / 2 - metrics.getHeight() / 3 * 2);

        int lx = x + w / 2 - metrics.stringWidth(new String(name, 0, len)) / 2;
        int ly = y + h / 2 + metrics.getHeight() / 3 * 2;
        g.drawString(new String(name), lx, ly);
        g.drawString("_", lx + metrics.stringWidth(new String(name, 0, cursor)), ly);

        if (error != null) {
            g.drawString(error, x + w / 2 - metrics.stringWidth(error) / 2, y + h - metrics.getHeight());
        }
    }

    public void keyTyped(KeyEvent e) {
        int c = e.getKeyChar();

        if (c == '\n') save();
        else if (cursor < 63 && c >= ' ' && c <= '~') {
            for (int i = name.length - 2; i > cursor; i--) name[i] = name[i - 1];
            name[cursor++] = e.getKeyChar();
            if (len < 63) len++;
        }
    }
   
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();
        if (c == KeyEvent.VK_BACK_SPACE && cursor > 0) {
            for (int i = --cursor; name[i] != '\0'; i++) name[i] = name[i + 1];
            if (len > 0) len--;
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT && cursor > 0) {
            cursor--;
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT && name[cursor] != '\0') {
            cursor++;
        }
    }

    public void keyReleased(KeyEvent e) {}

    /*
     * Check if the score is a highscore, return true if it is, false otherwise.
     */
    public static boolean isHighscore(long score) {
	/*
         * Check the highscores file to see if this is a highscore.
         */
        try {
            // Create a file object from the filename.
            File file = Game.HIGHSCORES_FILE();

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

	    if (!ishighscore)
		return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
	
	return true;
    }

    /*
     * Save the score to the file in order.
     */
    public void save() {
        if (score == -1)
            return;
        if (len == 0) {
            error = "Enter a name for crists sake";
            return;
        }
        try {
            // Open the file and a temp of it.
            File file = Game.HIGHSCORES_FILE();
	    File tmp = File.createTempFile("37bec-amvios", ".highscore");

            BufferedReader br = new BufferedReader(new FileReader(file));
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(tmp)));

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
            controler.getPanel().setMode(GamePanel.HIGHSCORE_MENU);
        } catch (Exception e) {
            System.out.println("An error occured when trying to save your score");
            e.printStackTrace();
        }
    }
}
