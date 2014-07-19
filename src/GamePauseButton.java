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
 * A button that sets it's content depending on the state of the panel, when gamecontroler really.
 */

import java.awt.Graphics2D;

public class GamePauseButton extends GameButton {

    String pause, resume;

    // The panel, location and size in x, y, w, h, the state to be changed to when clicked and the message.
    public GamePauseButton(GamePanel p, int x, int y, int w, int h, int m, String n) {
        super(p, x, y, w, h, m, n);
        pause = "Pause";
        resume = "Resume";
    }
    
    public void paint(Graphics2D g) {
        if (panel.isPaused()) name = resume;
        else name = pause;

        super.paint(g);
    }
}
