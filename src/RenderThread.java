public class RenderThread extends Thread {
	GamePanel panel;
    boolean end;
    long frameTime;

	public RenderThread(GamePanel p, long t) {
		panel = p;
        frameTime = t;
	}
	
    public void run() {
        long time;
        end = false;
		
        while (!end) {
            panel.repaint();
            
            try {
                Thread.sleep(frameTime);
            } catch (Exception e) {}
		}
	}

    public void end() {
        end = true;
    }
}
