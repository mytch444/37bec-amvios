/*
 * Class to handle all game sounds.
 */

import java.io.*;
import javax.sound.sampled.*;

public class GameSound {
    public static int STREAMS = 7;
    public static int DUPLICATES = 50;
    
    public static int BACKGROUND = 0;
    public static int BULLET = 1;
    public static int LASER = 2;
    public static int EXPLOSIVE_SHOT = 3;
    public static int EXPLOSIVE_EXPLODE = 4;
    public static int PLAYER_HIT = 5;
    public static int FRIEND_HIT = 6;

    Clip[][] clips;
        
    public GameSound() {
	clips = new Clip[STREAMS][DUPLICATES];

	for (int i = 0; i < DUPLICATES; i++) {
	    try {
		clips[BULLET][i] = AudioSystem.getClip();
		clips[BULLET][i].open(
		      AudioSystem.getAudioInputStream(
				  getClass().getResourceAsStream("/sounds/bullet.wav")));

		clips[EXPLOSIVE_SHOT][i] = AudioSystem.getClip();
		clips[EXPLOSIVE_SHOT][i].open(
		      AudioSystem.getAudioInputStream(
				  getClass().getResourceAsStream("/sounds/explosive_shot.wav")));
		
		clips[EXPLOSIVE_EXPLODE][i] = AudioSystem.getClip();
		clips[EXPLOSIVE_EXPLODE][i].open(
		      AudioSystem.getAudioInputStream(
				  getClass().getResourceAsStream("/sounds/explosive_explode.wav")));

		clips[LASER][i] = AudioSystem.getClip();
		clips[LASER][i].open(
		      AudioSystem.getAudioInputStream(
				  getClass().getResourceAsStream("/sounds/laser.wav")));

		clips[PLAYER_HIT][i] = AudioSystem.getClip();
		clips[PLAYER_HIT][i].open(
		      AudioSystem.getAudioInputStream(
				  getClass().getResourceAsStream("/sounds/player_hit.wav")));

		clips[FRIEND_HIT][i] = AudioSystem.getClip();
		clips[FRIEND_HIT][i].open(
		      AudioSystem.getAudioInputStream(
				  getClass().getResourceAsStream("/sounds/friend_hit.wav")));

	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}

    }

    public void play(int s) {
	for (int i = 0; i < DUPLICATES; i++) {
	    if (!clips[s][i].isRunning()) {
		clips[s][i].setFramePosition(0);
		clips[s][i].start();
		return;
	    }
	}
    }
}
