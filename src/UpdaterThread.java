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
            if (time > 5) {
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
