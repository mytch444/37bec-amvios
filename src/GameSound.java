/*
 * Class to handle all game sounds.
 */

import java.io.*;
import javax.sound.sampled.*;

public class GameSound {
    public static int STREAMS = 12;
    public static int DUPLICATES = 10;
    
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

    Clip[][] clips;

    public GameSound() {
	clips = new Clip[STREAMS][DUPLICATES];

	// Fucking awfull.
	for (int i = 0; i < DUPLICATES; i++) {
	    try {
		clips[BULLET][i] = AudioSystem.getClip();
		clips[BULLET][i].open(
		      AudioSystem.getAudioInputStream(
				  getClass().getResource("/sounds/bullet.wav")));
	    } catch (Exception e) {
		System.out.println("Error loading bullet sound");
		e.printStackTrace();
		clips[BULLET][i] = null;
	    }

	    try {
		clips[EXPLOSIVE_SHOT][i] = AudioSystem.getClip();
		clips[EXPLOSIVE_SHOT][i].open(
		      AudioSystem.getAudioInputStream(
				  getClass().getResource("/sounds/explosive_shot.wav")));
	    } catch (Exception e) {
		System.out.println("Error loading explosive shot sound");
		e.printStackTrace();
		clips[EXPLOSIVE_SHOT][i] = null;
	    }

	    try {
		clips[EXPLOSIVE_EXPLODE][i] = AudioSystem.getClip();
		clips[EXPLOSIVE_EXPLODE][i].open(
		      AudioSystem.getAudioInputStream(
				  getClass().getResource("/sounds/explosive_explode.wav")));
	    } catch (Exception e) {
		System.out.println("Error loading explosive explode sound");
		e.printStackTrace();
		clips[EXPLOSIVE_EXPLODE][i] = null;
	    }

	    try {
		clips[PLAYER_HIT][i] = AudioSystem.getClip();
		clips[PLAYER_HIT][i].open(
		      AudioSystem.getAudioInputStream(
				  getClass().getResource("/sounds/player_hit.wav")));
	    } catch (Exception e) {
		System.out.println("Error loading player hit sound");
		e.printStackTrace();
		clips[PLAYER_HIT][i] = null;
	    }

	    try {
		clips[FRIEND_HIT][i] = AudioSystem.getClip();
		clips[FRIEND_HIT][i].open(
		      AudioSystem.getAudioInputStream(
				  getClass().getResource("/sounds/friend_hit.wav")));
	    } catch (Exception e) {
		System.out.println("Error loading friend hit sound");
		e.printStackTrace();
		clips[FRIEND_HIT][i] = null;
	    }

	    try {
		clips[ENEMY_HIT][i] = AudioSystem.getClip();
		clips[ENEMY_HIT][i].open(
		      AudioSystem.getAudioInputStream(
				  getClass().getResource("/sounds/enemy_hit.wav")));
	    } catch (Exception e) {
		System.out.println("Error loading enemy hit sound");
		e.printStackTrace();
		clips[ENEMY_HIT][i] = null;
	    }

	    try {
		clips[GOLD_ENEMY_HIT][i] = AudioSystem.getClip();
		clips[GOLD_ENEMY_HIT][i].open(
		      AudioSystem.getAudioInputStream(
				  getClass().getResource("/sounds/gold_enemy_hit.wav")));
	    } catch (Exception e) {
		System.out.println("Error loading gold enemy hit sound");
		e.printStackTrace();
		clips[GOLD_ENEMY_HIT][i] = null;
	    }

	    try {
		clips[EXPLOSIVE_ENEMY_HIT][i] = AudioSystem.getClip();
		clips[EXPLOSIVE_ENEMY_HIT][i].open(
		      AudioSystem.getAudioInputStream(
				  getClass().getResource("/sounds/explosive_enemy_hit.wav")));

	    } catch (Exception e) {
		System.out.println("Error loading explosive enemy sound");
		e.printStackTrace();
		clips[EXPLOSIVE_ENEMY_HIT][i] = null;
	    }

	    try {
		clips[KTS_SHOT][i] = AudioSystem.getClip();
		clips[KTS_SHOT][i].open(
		      AudioSystem.getAudioInputStream(
				  getClass().getResource("/sounds/kts_shot.wav")));
	    } catch (Exception e) {
		System.out.println("Error loading kts shot sound");
		e.printStackTrace();
		clips[KTS_SHOT][i] = null;
	    }

	    try {
		clips[SCATTER_SHOT][i] = AudioSystem.getClip();
		clips[SCATTER_SHOT][i].open(
		      AudioSystem.getAudioInputStream(
				  getClass().getResource("/sounds/scatter_shot.wav")));
	    } catch (Exception e) {
		System.out.println("Error loading scatter shot sound");
		e.printStackTrace();
		clips[SCATTER_SHOT][i] = null;
	    }

	    try {
		clips[WALL_LASER_SETUP][i] = AudioSystem.getClip();
		clips[WALL_LASER_SETUP][i].open(
		      AudioSystem.getAudioInputStream(
				  getClass().getResource("/sounds/wall_laser_setup.wav")));
	    } catch (Exception e) {
		System.out.println("Error loading wall laser setup sound");
		e.printStackTrace();
		clips[WALL_LASER_SETUP][i] = null;
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

    public void stop(int s, int i) {
	clips[s][i].stop();
    }
}
