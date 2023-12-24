package main;

import java.awt.Color;
import java.awt.Graphics2D;

import entity.NPC_Hero_Daniel;
import entity.NPC_Hero_Esther;
import entity.NPC_Hero_Joy;
import entity.NPC_Hero_Reuben;
import entity.PlayerDummy;
import monster.MON_Pirate;
import monster.MON_Samurai;
import monster.MON_Skeleton_Boss;
import monster.MON_SlimeBoss;
import object.OBJ_Door_Down;
import object.OBJ_Door_Iron;

public class CutsceneManager {

	GamePanel gp;
	Graphics2D g2;
	public int sceneNum;
	public int scenePhase;
	
	// Scene Number
	public final int NA = 0;
	public final int slimeBoss = 1;
	public final int skeletonBoss = 2;
	public final int pirateBoss = 3;
	public final int samuraiBoss = 4;
	public final int daniel = 5;
	public final int alden1 = 6;
	public final int alden2 = 7;
	public final int joy = 8;
	public final int esther = 9;
	
	public CutsceneManager(GamePanel gp) {
		this.gp = gp;
	}
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		
		switch(sceneNum) {
		case slimeBoss: scene_slimeBoss(); break;
		case skeletonBoss: scene_skeletonBoss(); break;
		case pirateBoss: scene_pirateBoss(); break;
		case samuraiBoss: scene_samuraiBoss(); break;
		case daniel: scene_daniel(); break;
		case alden1: scene_alden1(); break;
		case alden2: scene_alden2(); break;
		case joy: scene_joy(); break;
		case esther: scene_esther(); break;
		}
	}
	public void letterBox() {
		g2.setColor(new Color(0,0,0));// 138,43,226 (purple), 218,165,32 (golden rod)
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight/12);
		g2.fillRect(0, gp.tileSize*11, gp.screenWidth, gp.screenHeight/12);
	}
	public void scene_slimeBoss() {
		
		letterBox();
		
		if(scenePhase == 0) {
			
			gp.bossBattleOn = true;
			
			// shut the iron door
			for(int i = 0; i < gp.obj[1].length; i++) {
				
				if(gp.obj[gp.currentMap][i] == null) {
					gp.obj[gp.currentMap][i] = new OBJ_Door_Iron(gp);
					gp.obj[gp.currentMap][i].worldX = gp.tileSize*10;
					gp.obj[gp.currentMap][i].worldY = gp.tileSize*6;
					gp.obj[gp.currentMap][i].temp = true;
					gp.playSE(60);
					break;
				}
			}
			
			// Search a vacant slot for the dummy
			for(int i = 0; i < gp.npc[1].length; i++) {
				
				if(gp.npc[gp.currentMap][i] == null) {
					gp.npc[gp.currentMap][i] = new PlayerDummy(gp);
					gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
					gp.npc[gp.currentMap][i].worldY = gp.player.worldY;
					gp.npc[gp.currentMap][i].direction = gp.player.direction;
					break;
				}
			}
			
			gp.player.drawing = false;
			scenePhase++;
		}
		if(scenePhase == 1) {
			gp.player.worldY += 2;
			gp.player.worldX += 2;
			if(gp.player.worldY > gp.tileSize * 25+24) {
				gp.player.worldX += 2;
				if(gp.player.worldX > 30) {
					scenePhase++;
				}
			}
		}
		if(scenePhase == 2) {
			
			//search the boss
			for(int i = 0; i < gp.monster[1].length; i++) {
				
				if(gp.monster[gp.currentMap][i] != null &&
						gp.monster[gp.currentMap][i].name.equals(MON_SlimeBoss.monName)) {
					gp.monster[gp.currentMap][i].sleep = false;
					gp.ui.npc = gp.monster[gp.currentMap][i];
					scenePhase++;
					break;
				}
			}
		}
		if(scenePhase == 3) {
			
			gp.ui.drawDialogueScreen();
		}
		if(scenePhase == 4) {
			
			// Return camera to the player
			
			// Search the dummy
			for(int i = 0; i < gp.npc[1].length; i++) {
				
				if(gp.npc[gp.currentMap][i] != null && gp.npc[gp.currentMap][i].name.equals(PlayerDummy.npcName)) {
					// restore the player's position
					gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
					gp.player.worldY = gp.npc[gp.currentMap][i].worldY;
					//delete the dummy
					gp.npc[gp.currentMap][i] = null;
					break;
				}
			}
			
			// start drawing the player again
			gp.player.drawing = true;
			
			// reset
			sceneNum = NA;
			scenePhase = 0;
			gp.gameState = gp.playState;
			
			// change the music
			gp.stopMusic();
			gp.playMusic(63);
		}
	}
	public void scene_skeletonBoss() {
		
		letterBox();
		
		if(scenePhase == 0) {
			
			gp.bossBattleOn = true;
			
			// shut the iron door
			for(int i = 0; i < gp.obj[1].length; i++) {
				
				if(gp.obj[gp.currentMap][i] == null) {
					gp.obj[gp.currentMap][i] = new OBJ_Door_Iron(gp);
					gp.obj[gp.currentMap][i].worldX = gp.tileSize*25;
					gp.obj[gp.currentMap][i].worldY = gp.tileSize*28;
					gp.obj[gp.currentMap][i].temp = true;
					gp.playSE(60);
					break;
				}
			}
			// shut a second iron door
			for(int i = 0; i < gp.obj[1].length; i++) {
				
				if(gp.obj[gp.currentMap][i] == null) {
					gp.obj[gp.currentMap][i] = new OBJ_Door_Iron(gp);
					gp.obj[gp.currentMap][i].worldX = gp.tileSize*25;
					gp.obj[gp.currentMap][i].worldY = gp.tileSize*15;
					gp.obj[gp.currentMap][i].temp = true;
					gp.playSE(60);
					break;
				}
			}
			
			// Search a vacant slot for the dummy
			for(int i = 0; i < gp.npc[1].length; i++) {
				
				if(gp.npc[gp.currentMap][i] == null) {
					gp.npc[gp.currentMap][i] = new PlayerDummy(gp);
					gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
					gp.npc[gp.currentMap][i].worldY = gp.player.worldY;
					gp.npc[gp.currentMap][i].direction = gp.player.direction;
					break;
				}
			}
			
			gp.player.drawing = false;
			scenePhase++;
		}
		if(scenePhase == 1) {
			
			gp.player.worldY -= 2;
			if(gp.player.worldY < gp.tileSize * 16) {
				scenePhase++;
			}
		}
		if(scenePhase == 2) {
			
			//search the boss
			for(int i = 0; i < gp.monster[1].length; i++) {
				
				if(gp.monster[gp.currentMap][i] != null &&
						gp.monster[gp.currentMap][i].name.equals(MON_Skeleton_Boss.monName)) {
					gp.monster[gp.currentMap][i].sleep = false;
					gp.ui.npc = gp.monster[gp.currentMap][i];
					scenePhase++;
					break;
				}
			}
		}
		if(scenePhase == 3) {
			// the boss speaks
			gp.ui.drawDialogueScreen();
		}
		if(scenePhase == 4) {
			
			// Return camera to the player
			
			// Search the dummy
			for(int i = 0; i < gp.npc[1].length; i++) {
				
				if(gp.npc[gp.currentMap][i] != null && gp.npc[gp.currentMap][i].name.equals(PlayerDummy.npcName)) {
					// restore the player's position
					gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
					gp.player.worldY = gp.npc[gp.currentMap][i].worldY;
					//delete the dummy
					gp.npc[gp.currentMap][i] = null;
					break;
				}
			}
			
			// start drawing the player again
			gp.player.drawing = true;
			
			// reset
			sceneNum = NA;
			scenePhase = 0;
			gp.gameState = gp.playState;
			
			// change the music
			gp.checkAllTheMusic();
		}
	}
	public void scene_pirateBoss() {
		
		letterBox();
		
		if(scenePhase == 0) {

			gp.bossBattleOn = true;
			
			// shut the iron door
			for(int i = 0; i < gp.obj[1].length; i++) {
				
				if(gp.obj[gp.currentMap][i] == null) {
					gp.obj[gp.currentMap][i] = new OBJ_Door_Iron(gp);
					gp.obj[gp.currentMap][i].worldX = gp.tileSize*25;
					gp.obj[gp.currentMap][i].worldY = gp.tileSize*43;
					gp.obj[gp.currentMap][i].temp = true;
					gp.playSE(60);
					break;
				}
			}
			
			// Search a vacant slot for the dummy
			for(int i = 0; i < gp.npc[1].length; i++) {
				
				if(gp.npc[gp.currentMap][i] == null) {
					gp.npc[gp.currentMap][i] = new PlayerDummy(gp);
					gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
					gp.npc[gp.currentMap][i].worldY = gp.player.worldY;
					gp.npc[gp.currentMap][i].direction = gp.player.direction;
					break;
				}
			}
			
			gp.player.drawing = false;
			scenePhase++;
		}
		if(scenePhase == 1) {

			gp.player.worldY -= 2;
			if(gp.player.worldY < gp.tileSize * 24) {
				scenePhase++;
			}
		}
		if(scenePhase == 2) {
			
			//search the boss
			for(int i = 0; i < gp.monster[1].length; i++) {
				
				if(gp.monster[gp.currentMap][i] != null &&
						gp.monster[gp.currentMap][i].name.equals(MON_Pirate.monName)) {
					gp.monster[gp.currentMap][i].sleep = false;
					gp.ui.npc = gp.monster[gp.currentMap][i];
					scenePhase++;
					break;
				}
			}
		}
		if(scenePhase == 3) {
			
			gp.ui.drawDialogueScreen();
		}
		if(scenePhase == 4) {
			
			// Return camera to the player
			
			// Search the dummy
			for(int i = 0; i < gp.npc[1].length; i++) {
				
				if(gp.npc[gp.currentMap][i] != null && gp.npc[gp.currentMap][i].name.equals(PlayerDummy.npcName)) {
					// restore the player's position
					gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
					gp.player.worldY = gp.npc[gp.currentMap][i].worldY;
					//delete the dummy
					gp.npc[gp.currentMap][i] = null;
					break;
				}
			}
			
			// start drawing the player again
			gp.player.drawing = true;
			
			// reset
			sceneNum = NA;
			scenePhase = 0;
			gp.gameState = gp.playState;
			
			// change the music
			gp.stopMusic();
			gp.playMusic(22);
		}
	}
	public void scene_samuraiBoss() {
		
		letterBox();
		
		if(scenePhase == 0) {
			
			gp.stopMusic();
			
			gp.bossBattleOn = true;
			
			gp.obj[5][5] = null;
			
			// shut the iron door
			for(int i = 0; i < gp.obj[1].length; i++) {
				
				
				if(gp.obj[gp.currentMap][i] == null) {
					
					gp.obj[gp.currentMap][i] = new OBJ_Door_Iron(gp);
					gp.obj[gp.currentMap][i].worldX = gp.tileSize*50;
					gp.obj[gp.currentMap][i].worldY = gp.tileSize*71;
					gp.obj[gp.currentMap][i].temp = true;
					gp.playSE(60);
					break;
				}
			}
			
			// Search a vacant slot for the dummy
			for(int i = 0; i < gp.npc[1].length; i++) {
				
				if(gp.npc[gp.currentMap][i] == null) {
					gp.npc[gp.currentMap][i] = new PlayerDummy(gp);
					gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
					gp.npc[gp.currentMap][i].worldY = gp.player.worldY;
					gp.npc[gp.currentMap][i].direction = gp.player.direction;
					break;
				}
			}
			
			gp.player.drawing = false;
			scenePhase++;
		}
		if(scenePhase == 1) {
			
			gp.player.worldY -= 2;
			if(gp.player.worldY < gp.tileSize * 49) {
				scenePhase++;
			}
		}
		if(scenePhase == 2) {
			
			//search the boss
			for(int i = 0; i < gp.monster[1].length; i++) {
				
				if(gp.monster[gp.currentMap][i] != null &&
						gp.monster[gp.currentMap][i].name.equals(MON_Samurai.monName)) {
					gp.monster[gp.currentMap][i].sleep = false;
					gp.ui.npc = gp.monster[gp.currentMap][i];
					scenePhase++;
					break;
				}
			}
		}
		if(scenePhase == 3) {
			
			gp.ui.drawDialogueScreen();
		}
		if(scenePhase == 4) {
			
			// Return camera to the player
			
			// Search the dummy
			for(int i = 0; i < gp.npc[1].length; i++) {
				
				if(gp.npc[gp.currentMap][i] != null && gp.npc[gp.currentMap][i].name.equals(PlayerDummy.npcName)) {
					// restore the player's position
					gp.player.worldX = gp.tileSize*50;
					gp.player.worldY = gp.tileSize*70;
					//delete the dummy
					gp.npc[gp.currentMap][i] = null;
					break;
				}
			}
			
			// start drawing the player again
			gp.player.drawing = true;
			
			// reset
			sceneNum = NA;
			scenePhase = 0;
			gp.gameState = gp.playState;
			
			// change the music
			gp.playMusic(64);
		}
	}
	public void scene_daniel() {
		
		letterBox();
		
		if(scenePhase == 0) {
			
			gp.eHandler.danielPressed = true;
			// Search a vacant slot for the dummy
			for(int i = 0; i < gp.npc[1].length; i++) {
				
				if(gp.npc[gp.currentMap][i] == null) {
					gp.npc[gp.currentMap][i] = new PlayerDummy(gp);
					gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
					gp.npc[gp.currentMap][i].worldY = gp.player.worldY;
					gp.npc[gp.currentMap][i].direction = gp.player.direction;
					break;
				}
			}
			
			gp.player.drawing = false;
			scenePhase++;
		}
		if(scenePhase == 1) {
			
			gp.player.worldX += 2;
			if(gp.player.worldX > gp.tileSize * 55) {
				scenePhase++;
			}
		}
		if(scenePhase == 2) {
			
			//search the boss
			for(int i = 0; i < gp.npc[1].length; i++) {
				
				if(gp.npc[gp.currentMap][i] != null &&
						gp.npc[gp.currentMap][i].name.equals(NPC_Hero_Daniel.npcName)) {
					gp.npc[gp.currentMap][i].sleep = false;
					gp.ui.npc = gp.npc[gp.currentMap][i];
					scenePhase++;
					break;
				}
			}
		}
		if(scenePhase == 3) {
			
			gp.ui.drawDialogueScreen();
		}
		if(scenePhase == 4) {
			
			// Return camera to the player
			
			// Search the dummy
			for(int i = 0; i < gp.npc[1].length; i++) {
				
				if(gp.npc[gp.currentMap][i] != null && gp.npc[gp.currentMap][i].name.equals(PlayerDummy.npcName)) {
					// restore the player's position
					gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
					gp.player.worldY = gp.npc[gp.currentMap][i].worldY;
					//delete the dummy
					gp.npc[gp.currentMap][i] = null;
					break;
				}
			}
			
			// start drawing the player again
			gp.player.drawing = true;
			
			// reset
			sceneNum = NA;
			scenePhase = 0;
			gp.gameState = gp.playState;
		}
	}
	public void scene_alden1() {
		
		letterBox();
		
		if(scenePhase == 0) {
			
			gp.eHandler.aldenPressed = true;
			// Search a vacant slot for the dummy
			for(int i = 0; i < gp.npc[1].length; i++) {
				
				if(gp.npc[gp.currentMap][i] == null) {
					gp.npc[gp.currentMap][i] = new PlayerDummy(gp);
					gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
					gp.npc[gp.currentMap][i].worldY = gp.player.worldY;
					gp.npc[gp.currentMap][i].direction = gp.player.direction;
					break;
				}
			}
			
			gp.player.drawing = false;
			scenePhase++;
		}
		if(scenePhase == 1) {
			gp.player.worldY -= 2;
			if(gp.player.worldY < gp.tileSize * 12) {
				scenePhase++;
			}
		}
		if(scenePhase == 2) {
			
			//search the boss
			for(int i = 0; i < gp.npc[1].length; i++) {
				
				if(gp.npc[gp.currentMap][i] != null &&
						gp.npc[gp.currentMap][i].name.equals(NPC_Hero_Reuben.npcName)) {
					gp.npc[gp.currentMap][i].sleep = false;
					gp.ui.npc = gp.npc[gp.currentMap][i];
					scenePhase++;
					break;
				}
			}
		}
		if(scenePhase == 3) {
			
			gp.ui.drawDialogueScreen();
		}
		if(scenePhase == 4) {
			
			// Return camera to the player
			
			// Search the dummy
			for(int i = 0; i < gp.npc[1].length; i++) {
				
				if(gp.npc[gp.currentMap][i] != null && gp.npc[gp.currentMap][i].name.equals(PlayerDummy.npcName)) {
					// restore the player's position
					gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
					gp.player.worldY = gp.npc[gp.currentMap][i].worldY;
					//delete the dummy
					gp.npc[gp.currentMap][i] = null;
					break;
				}
			}
			
			// start drawing the player again
			gp.player.drawing = true;
			
			// reset
			sceneNum = NA;
			scenePhase = 0;
			gp.gameState = gp.playState;
		}
	}
	public void scene_alden2() {
		
		letterBox();
		
		if(scenePhase == 0) {
			
			gp.eHandler.aldenPressed = true;
			// Search a vacant slot for the dummy
			for(int i = 0; i < gp.npc[1].length; i++) {
				
				if(gp.npc[gp.currentMap][i] == null) {
					gp.npc[gp.currentMap][i] = new PlayerDummy(gp);
					gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
					gp.npc[gp.currentMap][i].worldY = gp.player.worldY;
					gp.npc[gp.currentMap][i].direction = gp.player.direction;
					break;
				}
			}
			
			gp.player.drawing = false;
			scenePhase++;
		}
		if(scenePhase == 1) {
			
			gp.player.worldX -= 2;
			if(gp.player.worldX < gp.tileSize * 23) {
				scenePhase++;
			}
		}
		if(scenePhase == 2) {
			
			//search the boss
			for(int i = 0; i < gp.npc[1].length; i++) {
				
				if(gp.npc[gp.currentMap][i] != null &&
						gp.npc[gp.currentMap][i].name.equals(NPC_Hero_Reuben.npcName)) {
					gp.npc[gp.currentMap][i].sleep = false;
					gp.ui.npc = gp.npc[gp.currentMap][i];
					scenePhase++;
					break;
				}
			}
		}
		if(scenePhase == 3) {
			
			gp.ui.drawDialogueScreen();
		}
		if(scenePhase == 4) {
			
			// Return camera to the player
			
			// Search the dummy
			for(int i = 0; i < gp.npc[1].length; i++) {
				
				if(gp.npc[gp.currentMap][i] != null && gp.npc[gp.currentMap][i].name.equals(PlayerDummy.npcName)) {
					// restore the player's position
					gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
					gp.player.worldY = gp.npc[gp.currentMap][i].worldY;
					//delete the dummy
					gp.npc[gp.currentMap][i] = null;
					break;
				}
			}
			
			// start drawing the player again
			gp.player.drawing = true;
			
			// reset
			sceneNum = NA;
			scenePhase = 0;
			gp.gameState = gp.playState;
		}
	}
	public void scene_joy() {
		
		letterBox();
		
		if(scenePhase == 0) {
			
			gp.eHandler.joyPressed = true;
			// Search a vacant slot for the dummy
			for(int i = 0; i < gp.npc[1].length; i++) {
				
				if(gp.npc[gp.currentMap][i] == null) {
					gp.npc[gp.currentMap][i] = new PlayerDummy(gp);
					gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
					gp.npc[gp.currentMap][i].worldY = gp.player.worldY;
					gp.npc[gp.currentMap][i].direction = gp.player.direction;
					break;
				}
			}
			
			gp.player.drawing = false;
			scenePhase++;
		}
		if(scenePhase == 1) {
			
			gp.player.worldX -= 3;
			if(gp.player.worldX < gp.tileSize * 13) {
				scenePhase++;
			}
		}
		if(scenePhase == 2) {
			
			//search the boss
			for(int i = 0; i < gp.npc[1].length; i++) {
				
				if(gp.npc[gp.currentMap][i] != null &&
						gp.npc[gp.currentMap][i].name.equals(NPC_Hero_Joy.npcName)) {
					gp.npc[gp.currentMap][i].sleep = false;
					gp.ui.npc = gp.npc[gp.currentMap][i];
					scenePhase++;
					break;
				}
			}
		}
		if(scenePhase == 3) {
			
			gp.ui.drawDialogueScreen();
		}
		if(scenePhase == 4) {
			
			// Return camera to the player
			
			// Search the dummy
			for(int i = 0; i < gp.npc[1].length; i++) {
				
				if(gp.npc[gp.currentMap][i] != null && gp.npc[gp.currentMap][i].name.equals(PlayerDummy.npcName)) {
					// restore the player's position
					gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
					gp.player.worldY = gp.npc[gp.currentMap][i].worldY;
					//delete the dummy
					gp.npc[gp.currentMap][i] = null;
					break;
				}
			}
			
			// start drawing the player again
			gp.player.drawing = true;
			
			// reset
			sceneNum = NA;
			scenePhase = 0;
			gp.gameState = gp.playState;
		}
	}
	public void scene_esther() {
		
		letterBox();
		
		if(scenePhase == 0) {
			
			gp.eHandler.estherPressed = true;
			// Search a vacant slot for the dummy
			for(int i = 0; i < gp.npc[1].length; i++) {
				
				if(gp.npc[gp.currentMap][i] == null) {
					gp.npc[gp.currentMap][i] = new PlayerDummy(gp);
					gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
					gp.npc[gp.currentMap][i].worldY = gp.player.worldY;
					gp.npc[gp.currentMap][i].direction = gp.player.direction;
					break;
				}
			}
			
			gp.player.drawing = false;
			scenePhase++;
		}
		if(scenePhase == 1) {
			
			gp.player.worldX -= 2;
			if(gp.player.worldX < gp.tileSize * 10) {
				scenePhase++;
			}
		}
		if(scenePhase == 2) {
			
			//search the boss
			for(int i = 0; i < gp.npc[1].length; i++) {
				
				if(gp.npc[gp.currentMap][i] != null &&
						gp.npc[gp.currentMap][i].name.equals(NPC_Hero_Esther.npcName)) {
					gp.npc[gp.currentMap][i].sleep = false;
					gp.ui.npc = gp.npc[gp.currentMap][i];
					scenePhase++;
					break;
				}
			}
		}
		if(scenePhase == 3) {
			
			gp.ui.drawDialogueScreen();
		}
		if(scenePhase == 4) {
			
			// Return camera to the player
			
			// Search the dummy
			for(int i = 0; i < gp.npc[1].length; i++) {
				
				if(gp.npc[gp.currentMap][i] != null && gp.npc[gp.currentMap][i].name.equals(PlayerDummy.npcName)) {
					// restore the player's position
					gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
					gp.player.worldY = gp.npc[gp.currentMap][i].worldY;
					//delete the dummy
					gp.npc[gp.currentMap][i] = null;
					break;
				}
			}
			
			// start drawing the player again
			gp.player.drawing = true;
			
			// reset
			sceneNum = NA;
			scenePhase = 0;
			gp.gameState = gp.playState;
		}
	}
}
