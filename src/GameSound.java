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
 * Class to handle all game sounds.
 */

import java.io.*;
import javax.sound.sampled.*;

public class GameSound {

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

    public static int[] duplicates = {
	1,
	7,
	5,
	10,
	1,
	2,
	10,
	5,
	5,
	8,
	5,
	1,
    };

    Clip[][] clips;

    public GameSound() {
	int max = 0;
	for (int j = 0; j < duplicates.length; j++) if (duplicates[j] > max) max = duplicates[j];
	clips = new Clip[soundfiles.length][max];

	for (int i = 0; i < max; i++) {
	    for (int s = 0; s < soundfiles.length; s++) {
		if (i > duplicates[s]) continue;
		
		try {
		    clips[s][i] = AudioSystem.getClip();
		    clips[s][i].open(AudioSystem.getAudioInputStream(
							       getClass().getResource("/sounds/" + soundfiles[s])));
		} catch (Exception e) {
		    System.err.println("Error opening clip for " + soundfiles[s] + " sound");
		    e.printStackTrace();
		    clips[s][i] = null;
		}
	    }
	}
    }

    public int play(int s) {
	for (int i = 0; i < clips[s].length; i++) {
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
	for (int i = 0; i < clips[s].length; i++) {
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
