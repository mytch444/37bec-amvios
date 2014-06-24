/*
 * Main class, this is where it all starts.
 */

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Game {
    // Function to return the name and path of the highscores file.
    public static String HIGHSCORES_FILE() {
        return System.getProperty("user.home") + "/.java_game";
    }

    // Main method, this is called by java when the class is run. Fuck java forcing everything into a class.
	public static void main(String[] args) {
       
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();

        // Create a JFrame (window), set the name, size, visibility and resizable.
		JFrame frame = new JFrame("Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cause the program to stop when the window is closed.
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            
        gs[0].setFullScreenWindow(frame);
       
//        frame.setResizable(false);
 //       frame.setUndecorated(true);
//        frame.setAlwaysOnTop(true);
		frame.setVisible(false);

//        frame.setBounds(0, 0, screenSize.width, screenSize.height);

        // Create a custom panel.
		GamePanel panel = new GamePanel(frame.getWidth(), frame.getHeight());
        panel.setPreferredSize(screenSize);
        // Add it to the window and reorganise it.
		frame.add(panel);
        frame.pack();

		frame.setVisible(true);
        panel.requestFocus();
	}
}
