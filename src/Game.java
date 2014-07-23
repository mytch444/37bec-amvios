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
 * Main class, this is where it all starts.
 */

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Game {
    // Function to return the name and path of the highscores file.
    public static File HIGHSCORES_FILE() throws IOException {
        File file = new File(System.getProperty("user.home") + "/.37bec-amvios");
	if (!file.exists()) file.createNewFile();

	return file;
    }

    // Main method, this is called by java when the class is run.
    public static void main(String[] args) {
	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	//        GraphicsDevice gs = ge.getScreenDevices();
	GraphicsDevice gd = ge.getDefaultScreenDevice(); 

	int width = gd.getDisplayMode().getWidth();
	int height = gd.getDisplayMode().getHeight();

        // Create a JFrame (window), set the name, size, visibility and resizable.
	JFrame frame = new JFrame("37Bec-Amvios");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cause the program to stop when the window is closed.
	frame.setUndecorated(true);

	gd.setFullScreenWindow(frame);
	frame.setVisible(false);
	
	// Create a custom panel.
	GamePanel panel = new GamePanel(width, height);
        panel.setPreferredSize(new Dimension(width, height));
        // Add it to the window and reorganise it.
	frame.add(panel);
	frame.pack();
	
	frame.setVisible(true);
	panel.requestFocus();
    }
}
