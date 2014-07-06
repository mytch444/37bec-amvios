/*
 * Just draw shit bro.
 */

import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RenderTimer implements ActionListener {
    GamePanel panel;
    Timer timer;
    
    public RenderTimer(GamePanel p, int f) {
	panel = p;

	timer = new Timer(f, this);
    }

    public void start() {
	timer.start();
    }

    public void stop() {
	timer.stop();
    }
    
    public void actionPerformed(ActionEvent ev) {
	if (!panel.frameDone()) {
	    System.out.println("Not finished in time so skipping");
	    return;
	}
	
	panel.repaint();
    }

    public void end() {
	stop();
    }
}
