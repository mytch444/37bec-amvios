/*
 *
 * Copyright: 2014 Mytchel Hammond <mytchel.hammond@gmail.com>
 *
 * 37bec-amvios is free software: you can redistribute it and/or modify
 * it under the term of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the Licence, or
 * (at your option) any later version.
 * 
 * 37bec-amvios is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License 
 * along with 37bec-amvios. If not, see <http://www.gnu.org/licenses/>
 *
 * --------------------------------------------------------------------
 *
 * A class to show a list of highscores.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class HighscoreMenu {

    GamePanel panel;
    ArrayList<String[]> board;
    int x, y, w, h;
    String head, foot;
    FontMetrics metrics;

    public HighscoreMenu(GamePanel p) {
        panel = p;

        board = new ArrayList<String[]>(); // The highscores.
        
        head = "Highscores";
        foot = "Click <New Game> for the obvious result.";
    
        metrics = p.getGraphics().getFontMetrics(p.getFont());

	readScores();
    }

    public void readScores() {
	h = 20;
        w = metrics.stringWidth(foot) + 40;

        // Read through the file and save the scores to an arraylist.
        try {
            File file = Game.HIGHSCORES_FILE();
  
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            while ((line = br.readLine()) != null) {
                board.add(line.split(":"));
 
                int iw = metrics.stringWidth(line + "  ");
                if (iw > w - 40) w = iw + 40;
           
                h += metrics.getHeight();
            }
        } catch (Exception e) {
            System.out.println("Error reading highscores file.");
            e.printStackTrace();
        }

        x = panel.getWidth() / 2 - w / 2;
        y = panel.getHeight() / 2 - h / 2;
    }
    
    public void paint(Graphics2D g) {
        g.setColor(Color.black);
        g.fillRect(x, y - metrics.getHeight() * 3, w, h + metrics.getHeight() * 3);
        g.setColor(Color.white);
        g.drawRect(x, y - metrics.getHeight() * 3, w, h + metrics.getHeight() * 3);

        g.drawString(head, x + w / 2 - metrics.stringWidth(head) / 2, y - metrics.getHeight() / 2 * 3);
        g.drawString(foot, x + w / 2 - metrics.stringWidth(foot) / 2, y + h - metrics.getHeight() / 2);
            
        for (int i = 0; i < board.size(); i++) {
            String name = board.get(i)[0];
            String score = board.get(i)[1];
            int iy = i * metrics.getHeight() + y;
            g.drawString(name, x + 20, iy);
            g.drawString(score, x + w - metrics.stringWidth(score) - 20, iy);
        }
    }
}
