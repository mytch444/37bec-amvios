/*
 * The menu the sits on the bottom of the panel.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class GameMenu {

    GamePanel panel;
    ArrayList<GameButton> buttons;
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

        // Add some buttons to do stuff.
        buttons = new ArrayList<GameButton>();
        buttons.add(new GameButton(p, x + (w + 20) * 0, y, w, h, GamePanel.NEW_GAME, "New Game"));
        buttons.add(new GameButton(p, x + (w + 20) * 1, y, w, h, GamePanel.HIGH_SCORE_MENU, "Highscores"));
        buttons.add(new GameButton(p, x + (w + 20) * 2, y, w, h, GamePanel.QUIT, "Quit"));
        buttons.add(new GamePauseButton(p, width - w - 50, y, w, h, GamePanel.PAUSE, "Pause"));

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

    public void paint(Graphics g) {
        g.setColor(bgcolor);
        g.fillRect(x, y, width, height);

        g.setColor(Color.white);
        g.setFont(font);
        g.drawString(title, titleX, titleY);
        g.setFont(panel.getFont());

        g.drawLine(x, y, x + width, y);
        g.drawLine(x, y + 1, x + width, y + 1);

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
