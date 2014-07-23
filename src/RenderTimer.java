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
 * Just draw bro.
 */

import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RenderTimer implements ActionListener {
    GamePanel panel;
    Timer timer;
    
    public RenderTimer(GamePanel p, int f) {
	panel = p;

	// Create a timer that will call actionPerformed every f milliseconds.
	// Supposidly better than a thread like I have for the updaterloop when
	// dealing with graphics, damn java.
	timer = new Timer(f, this);
    }

    public void start() {
	timer.start();
    }

    public void stop() {
	timer.stop();
    }

    // This is called by the timer every so often, it redraws the frame,
    // or doesnt if it shill hasn't finished.
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
