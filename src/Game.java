/*
 * Main class, this is where it all starts.
 */

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Game {
    // Function to return the name and path of the highscores file.
    public static String HIGHSCORES_FILE() {
        return System.getProperty("user.home") + "/.37bec-amvios";
    }

    // Main method, this is called by java when the class is run. Fuck java forcing everything into a class.
    public static void main(String[] args) {
       
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();

        // Create a JFrame (window), set the name, size, visibility and resizable.
	JFrame frame = new JFrame("37Bec-Amvios");
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cause the program to stop when the window is closed.
        
        gs[0].setFullScreenWindow(frame);

	// Set the visibility to false, dam macs.
	frame.setVisible(false);

	// Create a custom panel.
	GamePanel panel = new GamePanel(frame.getWidth(), frame.getHeight());
        panel.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));
        // Add it to the window and reorganise it.
	frame.add(panel);
        frame.pack();

	// Set the visibility to true, this needed to be toggles because macs don't like fullscreen.
	// And there I was thinking they were simple minded and don't like multitasking.
	frame.setVisible(true);

        panel.requestFocus();
    }
}
