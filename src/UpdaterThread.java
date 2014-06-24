/*
 * Thread to update the panel.
 */

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class UpdaterThread extends Thread {
    public static int TIME = 1000 / 50;

	private GamePanel panel;
    private boolean end;

	public UpdaterThread(GamePanel p) {
		panel = p;
	}
	
    public void run() {
        long start, time;

        end = false;
        /*
         * Repeat this until end becomes true. So until something calls the end method.
         */
		while (!end) {
            start = System.currentTimeMillis();

            // Repaint the panel.
            panel.repaint();
           
            // Figure out how long it took to paint, if this is positive sleep for however long it takes to
            // keep the frames the same length. Helps with consistancy.
            time = TIME - (System.currentTimeMillis() - start);
            if (time > 0) {
                try {
                    Thread.sleep(time);
                } catch (Exception e) {}
            }
		}
	}

    public void end() {
        end = true;
    }
}
