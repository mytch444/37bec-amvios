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

	int width = gs[0].getDisplayMode().getWidth();
	int height = gs[0].getDisplayMode().getHeight();

        // Create a JFrame (window), set the name, size, visibility and resizable.
	JFrame frame = new JFrame("37Bec-Amvios");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cause the program to stop when the window is closed.
	//	frame.setUndecorated(true);

	gs[0].setFullScreenWindow(frame);
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
