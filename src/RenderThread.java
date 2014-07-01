/*
 * Just draw shit bro.
 */

import java.awt.Graphics;

public class RenderThread extends Thread {
	GamePanel panel;
    boolean end;

	public RenderThread(GamePanel p) {
		panel = p;
	}
	
    public void run() {
        end = false;
	
        while (!end) {
            panel.repaint();

	    // But wait until I've finished drawing before you tell me to draw again.
	    // Else I'll never finish anything.
            while (!panel.frameDone()) {
                try {
                    Thread.sleep(1);
                } catch (Exception e) {}
            }
	}
    }

    public void end() {
        end = true;
    }
}
