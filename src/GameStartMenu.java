/*
 * Shows some instructions, rather bad ones but that's half the point.
 */

import java.awt.*;
import java.awt.event.*;

public class GameStartMenu implements KeyListener {

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

    public void paint(Graphics g) {
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
    }
    
    public void hide() {
	show = false;
	panel.removeKeyListener(this);
    }
    
    public void keyReleased(KeyEvent e) {
	panel.startGame();
    }

    public void keyTyped(KeyEvent e) {}
    public void keyPressed(KeyEvent e) {}
}
