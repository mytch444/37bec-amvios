/*
 * Class to handle all game sounds.
 */

import java.io.*;
import javax.sound.sampled.*;

public class GameSound {
    public static int DUPLICATES = 30;
    
    public static int BACKGROUND = 0;
    public static int BULLET = 1;
    public static int EXPLOSIVE_SHOT = 2;
    public static int EXPLOSIVE_EXPLODE = 3;
    public static int PLAYER_HIT = 4;
    public static int FRIEND_HIT = 5;
    public static int ENEMY_HIT = 6;
    public static int GOLD_ENEMY_HIT = 7;
    public static int EXPLOSIVE_ENEMY_HIT = 8;
    public static int KTS_SHOT = 9;
    public static int SCATTER_SHOT = 10;
    public static int WALL_LASER_SETUP = 11;

    public static String[] soundfiles = {
	"background.wav",
	"bullet.wav",
	"explosive_shot.wav",
	"explosive_explode.wav",
	"player_hit.wav",
	"friend_hit.wav",
	"enemy_hit.wav",
	"gold_enemy_hit.wav",
	"explosive_enemy_hit.wav",
	"kts_shot.wav",
	"scatter_shot.wav",
	"wall_laser_setup.wav",
    };

    Clip[][] clips;

    public GameSound() {
	clips = new Clip[soundfiles.length][DUPLICATES];

	for (int s = 0; s < soundfiles.length; s++) {
	    for (int i = 0; i < DUPLICATES; i++) {
		try {
		    clips[s][i] = AudioSystem.getClip();
		    clips[s][i].open(
				     AudioSystem.getAudioInputStream(
						 getClass().getResource("/sounds/" + soundfiles[s])));
		} catch (Exception e) {
		    System.out.println("Error loading " + soundfiles[s] + " sound");
		    e.printStackTrace();
		    clips[s][i] = null;
		}
	    }
	}
    }

    public int play(int s) {
	for (int i = 0; i < DUPLICATES; i++) {
	    if (clips[s][i] == null) continue;
	    if (!clips[s][i].isRunning()) {
		clips[s][i].setFramePosition(0);
		clips[s][i].start();

		return i;
	    }
	}
	
	return -1;
    }

    public int loop(int s) {
	for (int i = 0; i < DUPLICATES; i++) {
	    if (clips[s][i] == null) continue;
	    if (!clips[s][i].isRunning()) {
		clips[s][i].setFramePosition(0);
		clips[s][i].loop(Clip.LOOP_CONTINUOUSLY);

		return i;
	    }
	}
	
	return -1;
    }


    public void stop(int s, int i) {
	if (i == -1) return;
	clips[s][i].stop();
    }
}
