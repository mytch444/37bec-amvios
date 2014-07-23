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
 * Shows some instructions, rather bad ones but that's half the point.
 */

import java.awt.*;
import java.awt.event.*;

public class GameStartMenu implements KeyListener, MouseListener {

    static String[] message = new String[] {
	"37Bec-Amvios",
	"",
	"This is by general consensus of the population of me considered",
	"the best game ever created by a mortal.",
	"",
	"Don't let any SQUARES get past you or they will do horrible things",
	"behind you. For those of you who have problems with logic, that means",
	"leave the triangles alone.",
	"",
	"Move up and down with <w> and <s>. Aim and shoot with that annoyingly",
	"useless thing called a mouse. <q> to toggle pause.",
	"",
	"You can also stop them by placing yourself between them and the end,",
	"this will gain you 10 times as many points as if you shot them but will", 
	"immobilise you for a short time.",
	"",
	"DON'T DIE.",
	"",
	"And if you manage to, then good job, because I havn't added any way",
	"for you to.",
	"",
	"Now press a key to start."
    };

    GamePanel panel;
    int x, y, w, h;
    FontMetrics metrics;
    boolean show;

    public GameStartMenu(GamePanel p) {
        panel = p;
	show = false;
    }

    public void position() {
	metrics = panel.getGraphics().getFontMetrics(panel.getFont());
	
	h = 20;
        w = 20;

        for (int i = 0; i < message.length; i++) {
            h += metrics.getHeight();

            int iw = metrics.stringWidth(message[i]) + 20;
            if (iw > w) w = iw;
        }

        x = panel.getWidth() / 2 - w / 2;
        y = panel.getHeight() / 2 - h / 2;
    }

    public void paint(Graphics2D g) {
	if (!show) return;
	
        g.setColor(Color.black);
        g.fillRect(x, y, w, h);
        g.setColor(Color.white);
        g.drawRect(x, y, w, h);

        for (int i = 0; i < message.length; i++) {
            String line = message[i];
            int iy = y + 10 + (i + 1) * metrics.getHeight();
            g.drawString(line, x + w / 2 - metrics.stringWidth(line) / 2, iy);
        }
    }

    public void show() {
	show = true;
	position();
        panel.addKeyListener(this);
	panel.addMouseListener(this);
    }
    
    public void hide() {
	show = false;
	panel.removeKeyListener(this);
	panel.removeMouseListener(this);
    }

    // Start the game on key press or mouse press.
    public void keyReleased(KeyEvent e) {
	panel.startGame();
    }

    public void mouseReleased(MouseEvent e) {
	panel.startGame();
    }

    public void keyTyped(KeyEvent e) {}
    public void keyPressed(KeyEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
