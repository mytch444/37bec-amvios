/*
 * The main menu, a class that holds a list of buttons and paints them.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class GameMenu {

    GamePanel panel;
    ArrayList<GameButton> buttons;
    int width, height, x, y;

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

        // Add some buttons to do stuff.
        buttons = new ArrayList<GameButton>();
        buttons.add(new GameButton(p, x + (w + 20) * 0, y, w, h, GamePanel.NEW_GAME, "New Game"));
        buttons.add(new GameButton(p, x + (w + 20) * 1, y, w, h, GamePanel.HIGH_SCORE_MENU, "Highscores"));
        buttons.add(new GameButton(p, x + (w + 20) * 2, y, w, h, GamePanel.QUIT, "Quit"));
        buttons.add(new GamePauseButton(p, width - w - 50, y, w, h, GamePanel.PAUSE, "Pause"));
	}

    // Go through the buttons and paint them.
	public void paint(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(x, y, width, height);

        for (int i = 0; i < buttons.size(); i++)
            buttons.get(i).paint(g);
    }

    // Stop the buttons from listening to the panel events.
    public void removeListeners() {
        for (int i = 0; i < buttons.size(); i++) {
            panel.removeMouseListener(buttons.get(i));
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
