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
