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
 * The menu the sits on the bottom of the panel.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class GameMenu {

    GamePanel panel;

    GameButton newGame, highscores, quit, pause;

    int width, height, x, y;
    int titleX, titleY;
    String title;
    Font font;
    Color bgcolor;

    public GameMenu(GamePanel p, int w, int h, int x, int y) {
        panel = p;

        this.width = w;
        this.height = h;
        this.x = x;
        this.y = y;

        w = 150;
        h = 30;
        x = x + 50;
        y = y + height / 2 - h / 2;

        newGame = new GameButton(p, x + (w + 20) * 0, y, w, h, GamePanel.NEW_GAME, "New Game");
        highscores = new GameButton(p, x + (w + 20) * 1, y, w, h, GamePanel.HIGHSCORE_MENU, "Highscores");
	quit = new GameButton(p, x + (w + 20) * 2, y, w, h, GamePanel.QUIT, "Quit");
	pause = new GamePauseButton(p, width - w - 50, y, w, h, GamePanel.PAUSE, "Pause");

        title = "37Bec-Amvios";

        font = p.getFont().deriveFont(20.0f);
        FontMetrics metrics = p.getGraphics().getFontMetrics(font);
	
        int titleW = metrics.stringWidth(title);
        int left = x + (w + 20) * 3 + 20;
        int right = width - w - 50 - 20;
        titleX = left + (right - left) / 2 - titleW / 2;
        titleY = y + h / 2 + metrics.getHeight() / 2;

        bgcolor = new Color(40, 0, 110);
    }

    public void paint(Graphics2D g) {
        g.setColor(bgcolor);
        g.fillRect(x, y, width, height);

        g.setColor(Color.white);
        g.setFont(font);
        g.drawString(title, titleX, titleY);
        g.setFont(panel.getFont());

        g.drawLine(x, y, x + width, y);
        g.drawLine(x, y + 1, x + width, y + 1);

	newGame.paint(g);
	highscores.paint(g);
	quit.paint(g);
	pause.paint(g);
    }

    // Stop the buttons from listening to the panel events.
    public void removeListeners() {
	panel.removeMouseListener(newGame);
	panel.removeMouseListener(highscores);
	panel.removeMouseListener(quit);
	panel.removeMouseListener(pause);
    }
}
