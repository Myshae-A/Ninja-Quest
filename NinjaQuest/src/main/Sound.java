package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {

	Clip clip;
	URL soundURL[] = new URL[100];
	FloatControl fc;
	int volumeScale = 3;
	float volume;
	
	public Sound() {
		soundURL[0] = getClass().getResource("/sound/marksmanSFX.wav");
		soundURL[1] = getClass().getResource("/sound/coin.wav");
		soundURL[2] = getClass().getResource("/sound/powerup01SFX.wav");
		soundURL[3] = getClass().getResource("/sound/unlock.wav");
		soundURL[4] = getClass().getResource("/sound/fanfare01SFX.wav");
		soundURL[5] = getClass().getResource("/sound/hitmonster.wav");
		soundURL[6] = getClass().getResource("/sound/receivedamage.wav");
		soundURL[7] = getClass().getResource("/sound/swingweapon.wav");
		soundURL[8] = getClass().getResource("/sound/levelup.wav");
		soundURL[9] = getClass().getResource("/sound/cursor.wav");
		soundURL[10] = getClass().getResource("/sound/burning.wav");
		soundURL[11] = getClass().getResource("/sound/cuttree.wav");
		soundURL[12] = getClass().getResource("/sound/gameover.wav");
		soundURL[13] = getClass().getResource("/sound/Stairs02SFX.wav");
		soundURL[14] = getClass().getResource("/sound/reloading.wav");
		soundURL[15] = getClass().getResource("/sound/Background_Positive.wav"); // DELETED!
		soundURL[16] = getClass().getResource("/sound/game_over_2.wav");
		soundURL[17] = getClass().getResource("/sound/Roblox-death-sound.wav");
		soundURL[18] = getClass().getResource("/sound/level_up_new.wav");
		soundURL[19] = getClass().getResource("/sound/level_up.wav"); // This is the newer lvl up sound
		soundURL[20] = getClass().getResource("/sound/notification.wav");
		soundURL[21] = getClass().getResource("/sound/special_drops.wav");
		soundURL[22] = getClass().getResource("/sound/BossSong_Dramatic.wav");
		soundURL[23] = getClass().getResource("/sound/wrong.wav");
		soundURL[24] = getClass().getResource("/sound/shooting_star.wav");
		soundURL[25] = getClass().getResource("/sound/bomb.wav");
		soundURL[26] = getClass().getResource("/sound/sleep.wav");
		soundURL[27] = getClass().getResource("/sound/blocked.wav");
		soundURL[28] = getClass().getResource("/sound/parry.wav");
		soundURL[29] = getClass().getResource("/sound/music_pick_avatar.wav");
		soundURL[30] = getClass().getResource("/sound/bomb.wav");
		soundURL[31] = getClass().getResource("/sound/Kevin MacLeod [Official] - Eternity - incompetech.com.wav"); // Replaced prev. song
		soundURL[32] = getClass().getResource("/sound/speak.wav"); // U can change the pitch for different characters, bc its easy, 1-note
		 
		//mon sounds
		soundURL[33] = getClass().getResource("/sound/orc_defeated.wav");
		soundURL[34] = getClass().getResource("/sound/orc_finds.wav");
		soundURL[35] = getClass().getResource("/sound/orc_hit.wav");
		soundURL[36] = getClass().getResource("/sound/skeleton_death.wav");
		soundURL[37] = getClass().getResource("/sound/skeleton_finds.wav");
		soundURL[38] = getClass().getResource("/sound/skeleton_hit.wav");
		
		soundURL[39] = getClass().getResource("/sound/health_recharged.wav");
		soundURL[40] = getClass().getResource("/sound/desesperebolo-9006.wav"); // DELETED!
		soundURL[41] = getClass().getResource("/sound/success-fanfare-trumpets.wav");
		soundURL[42] = getClass().getResource("/sound/wrong2.wav");
		
		// THEME SONGS
		soundURL[43] = getClass().getResource("/sound/Kevin MacLeod [Official] - Pixelland - incompetech.com.wav");
		soundURL[44] = getClass().getResource("/sound/Airship Serenity.wav"); //boss_music_metal_knight
		soundURL[45] = getClass().getResource("/sound/Bittersweet.wav"); // DELETED!
		soundURL[46] = getClass().getResource("/sound/Adventures in Adventureland.wav");
		soundURL[47] = getClass().getResource("/sound/Your Call.wav");
		soundURL[48] = getClass().getResource("/sound/Kevin MacLeod [Official] - Furious Freak - incompetech.com.wav"); // DELETED!
		soundURL[49] = getClass().getResource("/sound/Ethernight Club.wav");
		soundURL[50] = getClass().getResource("/sound/EMPTY");
		soundURL[51] = getClass().getResource("/sound/Kevin MacLeod [Official] - Exit the Premises - incompetech.com.wav"); // DELETED!
		
		soundURL[52] = getClass().getResource("/sound/door-slam-sound-effect-21878.wav");
		soundURL[53] = getClass().getResource("/sound/pickup.wav"); //replaced with 57swss
		soundURL[54] = getClass().getResource("/sound/travel_token.wav");
		soundURL[55] = getClass().getResource("/sound/quest_completed.wav");
		soundURL[56] = getClass().getResource("/sound/throw_rock.wav");
		soundURL[57] = getClass().getResource("/sound/blockPlaced.wav"); // THE ANNOYING MYSTERY SOUND!!!
		soundURL[58] = getClass().getResource("/sound/mixkit-soap-bubble-sound-2925.wav"); // not actually used!!!
		soundURL[59] = getClass().getResource("/sound/chipwall.wav");
		soundURL[60] = getClass().getResource("/sound/dooropen.wav");
		soundURL[61] = getClass().getResource("/sound/craftingtable.wav");
		soundURL[62] = getClass().getResource("/sound/craftedItem.wav");
		soundURL[63] = getClass().getResource("/sound/tuba archmage boss theme.wav"); // slime boss theme: , Tuba Angel Theme
		soundURL[64] = getClass().getResource("/sound/Volatile Reaction.wav");
		soundURL[65] = getClass().getResource("/sound/Tuba Angel Theme.wav");
		soundURL[66] = getClass().getResource("/sound/mixkit-distant-thunder-explosion-1278.wav");
		soundURL[67] = getClass().getResource("/sound/mixkit-explosion-hit-1704.wav");
		soundURL[68] = getClass().getResource("/sound/A Skeletune.wav");
		
		
	}
	
	public void setFile(int i) {
		
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
			checkVolume();
			
			
		}catch(Exception e) {
			
		}
	}
	public void play() {
		
		clip.start();
	}
	public void loop() {
		
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	public void stop() {
		
		clip.stop();
	}
	public void checkVolume() {
		
		switch(volumeScale) {
		case 0: volume = -80f; break;
		case 1: volume = -20f; break;
		case 2: volume = -12f; break;
		case 3: volume = -5f; break;
		case 4: volume = 1f; break;
		case 5: volume = 6f; break;
		}
		fc.setValue(volume);
	}
}
