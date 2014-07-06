/*
 * Just keep on evolving.
 */

public class UpdaterThread extends Thread {
    GamePanel panel;
    boolean end;
    long frameTime; 

    public UpdaterThread(GamePanel p, long t) {
	panel = p;
        frameTime = t;
    }
	
    public void run() {
	long start, time;

        end = false;
	while (!end) {
            start = System.currentTimeMillis();

            // Repaint the panel.
            panel.update();
           
            // Figure out how long it took to paint, if this is positive sleep for however long it takes to
            // keep the frames the same length. Helps with consistancy.
            time = frameTime - (System.currentTimeMillis() - start);
            if (time > 0) {
                try {
                    Thread.sleep(time);
                } catch (Exception e) {}
            } else System.out.println("Update out of time " + time);
	}
    }

    public void end() {
        end = true;
    }
}
