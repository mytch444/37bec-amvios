/*
 *          DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE
 *                    Version 2, December 2004
 *
 * Copyright (C) 2014 Mytchel Hammond
 *
 * Everyone is permitted to copy and distribute verbatim or modified
 * copies of this file, and changing it is allowed as long
 * as the name is changed.
 *
 *            DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE
 *   TERMS AND CONDITIONS FOR COPYING, DISTRIBUTION AND MODIFICATION
 *
 *  0. You just DO WHAT THE FUCK YOU WANT TO.
 *
 * -----------------------------------------------------------------
 *
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

    // Main method, this is called by java when the class is run. Fuck java forcing everything into a class.
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
