/*
 * My own custom button to be used in menus. You could use an actuall one but this is simpler.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class GamePauseButton extends GameButton {

    String pause, resume;

    // The panel, location and size in x, y, w, h, the state to be changed to when clicked and the message.
	public GamePauseButton(GamePanel p, int x, int y, int w, int h, int m, String n) {
        super(p, x, y, w, h, m, n);
        pause = "Pause";
        resume = "Resume";
	}
	
	public void paint(Graphics g) {
        if (panel.isPaused()) name = resume;
        else name = pause;

        super.paint(g);
    }
}
