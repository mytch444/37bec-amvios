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
 * My own custom button to be used in menus. You could use an actuall one but this is simpler.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class GameButton implements MouseListener {

    GamePanel panel;
    int x, y, w, h;
    int mode;
    String name;
    FontMetrics metrics;

    // The panel, location and size in x, y, w, h, the state to be changed to when clicked and the message.
    public GameButton(GamePanel p, int x, int y, int w, int h, int m, String n) {
        panel = p;

        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.mode = m;
        this.name = n;

        panel.addMouseListener(this);
    }
	
    public void paint(Graphics2D g) {
        metrics = g.getFontMetrics();
        
        g.setColor(Color.white);
        g.fillRect(x, y, w, h);

        g.setColor(Color.black);
        g.drawString(name, x + w / 2 - metrics.stringWidth(name) / 2, y + metrics.getHeight() + 5);
    }

    // If clicked then set the panel mode.
    public void mouseClicked(MouseEvent e) {
        if (e.getX() > x && e.getX() < x + w) {
            if (e.getY() > y && e.getY() < y + h) {
                panel.setMode(mode);
            }
        }
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
