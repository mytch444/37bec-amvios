/*
 * Class to handle all game sounds.
 */

import java.io.*;
import javax.sound.sampled.*;

public class GameSound {
    public static int STREAMS = 10;
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

		clips[PLAYER_HIT][i] = AudioSystem.getClip();
		clips[PLAYER_HIT][i].open(
		      AudioSystem.getAudioInputStream(
				  getClass().getResourceAsStream("/sounds/player_hit.wav")));

		clips[FRIEND_HIT][i] = AudioSystem.getClip();
		clips[FRIEND_HIT][i].open(
		      AudioSystem.getAudioInputStream(
				  getClass().getResourceAsStream("/sounds/friend_hit.wav")));

		clips[ENEMY_HIT][i] = AudioSystem.getClip();
		clips[ENEMY_HIT][i].open(
		      AudioSystem.getAudioInputStream(
				  getClass().getResourceAsStream("/sounds/enemy_hit.wav")));
		
		clips[GOLD_ENEMY_HIT][i] = AudioSystem.getClip();
		clips[GOLD_ENEMY_HIT][i].open(
		      AudioSystem.getAudioInputStream(
				  getClass().getResourceAsStream("/sounds/gold_enemy_hit.wav")));

		clips[EXPLOSIVE_ENEMY_HIT][i] = AudioSystem.getClip();
		clips[EXPLOSIVE_ENEMY_HIT][i].open(
		      AudioSystem.getAudioInputStream(
				  getClass().getResourceAsStream("/sounds/explosive_enemy_hit.wav")));

		clips[KTS_SHOT][i] = AudioSystem.getClip();
		clips[KTS_SHOT][i].open(
		      AudioSystem.getAudioInputStream(
				  getClass().getResourceAsStream("/sounds/kts_shot.wav")));

		
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
