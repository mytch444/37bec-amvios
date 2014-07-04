/*
 * Just draw shit bro.
 */

import java.awt.Graphics;

public class RenderThread extends Thread {
    GamePanel panel;
    boolean end;
    long frameTime;
    
    public RenderThread(GamePanel p, long f) {
	panel = p;
	frameTime = f;
    }
	
    public void run() {
	long start, time;
        end = false;
	
        while (!end) {
	    start = System.currentTimeMillis();
	    
            panel.repaint();

	    // But wait until I've finished drawing before you tell me to draw again.
	    // Else I'll never finish anything.
            while (!panel.frameDone()) {
                try {
                    Thread.sleep(5);
                } catch (Exception e) {}
            }

	    time = frameTime - (System.currentTimeMillis() - start);
	    if (time > 0) {
                try {
                    Thread.sleep(time);
                } catch (Exception e) {}
	    } else System.out.println("Render out of time " + time);
	}
    }

    public void end() {
        end = true;
    }
}
