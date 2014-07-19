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
