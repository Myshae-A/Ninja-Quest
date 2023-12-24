package monster;

import java.util.Random;

import data.DataStorage;
import data.Progress;
import entity.Entity;
import main.GamePanel;
import object.OBJ_Chest;
import object.OBJ_Coin_Emerald;
import object.OBJ_Door_Down;
import object.OBJ_Door_Iron;
import object.OBJ_Fireball;
import object.OBJ_Hero_Crafting_Table;
import object.OBJ_Hero_Guardian_Field;
import object.OBJ_Hero_Special_Cloak;
import object.OBJ_Hero_Warrior_Sword;
import object.OBJ_Key;
import object.OBJ_Lantern_Blue;
import object.OBJ_Lightning;
import object.OBJ_Pickaxe;
import object.OBJ_Shield_Emerald;
import object.OBJ_Shield_Metal;
import object.OBJ_Stat_Ammo;
import object.OBJ_Stat_Attack;
import object.OBJ_Stat_Defense;
import object.OBJ_Stat_HP;
import object.OBJ_Sword_Metal;
import object.OBJ_Travel_Stone;

public class MON_Samurai extends Entity{
	
	GamePanel gp;
	public static final String monName = "Samurai";

	public boolean halfHealthSE = false;
	public boolean lastHealthSE = false;
	
	public MON_Samurai(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		direction = "down";
		dormant = true;
		type = type_boss;
		sub_type = sub_type_boss;
		name = "Samurai";
		defaultSpeed = 0;
		speed = defaultSpeed;
		maxLife = 1024;
		life = maxLife;
		attack = 7;
		defense = 2;
		exp = (int)(Math.random()*240)+240;
		projectile = new OBJ_Lightning(gp);
		knockBackPower = 10;
		sleep = true;
		
		int size = gp.tileSize*2;
		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width = size;
		solidArea.height = size;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage();
		setDialogue();
	}
	public void getImage() {
		
		int i = 2;
		
		if(!dormant) {
			up1 = setup("/monster/samurai_up_1", gp.tileSize*i, gp.tileSize*i);
			up2 = setup("/monster/samurai_up_2", gp.tileSize*i, gp.tileSize*i);
			down1 = setup("/monster/samurai_down_1", gp.tileSize*i, gp.tileSize*i);
			down2 = setup("/monster/samurai_down_2", gp.tileSize*i, gp.tileSize*i);
			left1 = setup("/monster/samurai_left_1", gp.tileSize*i, gp.tileSize*i);
			left2 = setup("/monster/samurai_left_2", gp.tileSize*i, gp.tileSize*i);
			right1 = setup("/monster/samurai_right_1", gp.tileSize*i, gp.tileSize*i);
			right2 = setup("/monster/samurai_right_2", gp.tileSize*i, gp.tileSize*i);
			
			up3 = setup("/monster/samurai_up_1", gp.tileSize*i, gp.tileSize*i);
			up4 = setup("/monster/samurai_up_2", gp.tileSize*i, gp.tileSize*i);
			down3 = setup("/monster/samurai_down_1", gp.tileSize*i, gp.tileSize*i);
			down4 = setup("/monster/samurai_down_2", gp.tileSize*i, gp.tileSize*i);
			left3 = setup("/monster/samurai_left_1", gp.tileSize*i, gp.tileSize*i);
			left4 = setup("/monster/samurai_left_2", gp.tileSize*i, gp.tileSize*i);
			right3 = setup("/monster/samurai_right_1", gp.tileSize*i, gp.tileSize*i);
			right4 = setup("/monster/samurai_right_2", gp.tileSize*i, gp.tileSize*i);
			
		}
		else {
			up1 = setup("/monster/samurai_dormant", gp.tileSize*i, gp.tileSize*i);
			up2 = setup("/monster/samurai_dormant", gp.tileSize*i, gp.tileSize*i);
			down1 = setup("/monster/samurai_dormant", gp.tileSize*i, gp.tileSize*i);
			down2 = setup("/monster/samurai_dormant", gp.tileSize*i, gp.tileSize*i);
			left1 = setup("/monster/samurai_dormant", gp.tileSize*i, gp.tileSize*i);
			left2 = setup("/monster/samurai_dormant", gp.tileSize*i, gp.tileSize*i);
			right1 = setup("/monster/samurai_dormant", gp.tileSize*i, gp.tileSize*i);
			right2 = setup("/monster/samurai_dormant", gp.tileSize*i, gp.tileSize*i);
			
			up3 = setup("/monster/samurai_dormant", gp.tileSize*i, gp.tileSize*i);
			up4 = setup("/monster/samurai_dormant", gp.tileSize*i, gp.tileSize*i);
			down3 = setup("/monster/samurai_dormant", gp.tileSize*i, gp.tileSize*i);
			down4 = setup("/monster/samurai_dormant", gp.tileSize*i, gp.tileSize*i);
			left3 = setup("/monster/samurai_dormant", gp.tileSize*i, gp.tileSize*i);
			left4 = setup("/monster/samurai_dormant", gp.tileSize*i, gp.tileSize*i);
			right3 = setup("/monster/samurai_dormant", gp.tileSize*i, gp.tileSize*i);
			right4 = setup("/monster/samurai_dormant", gp.tileSize*i, gp.tileSize*i);
		}
	}
	public void setDialogue() {
		
		dialogues[0][0] = "Zzzzz...";
		dialogues[0][1] = "You feel an ominous aura that lies dormant. Then\nyou hear a sinister voice in your head--";
		dialogues[0][2] = "'So you've made it this far? Not impressive, I've\nseen better.'";
		dialogues[0][3] = "'Don't worry, I'll put an end to your misery.'";
		dialogues[0][4] = "'Just come a little closer...'";
	}
	public void setAction() {
		
		if(bossHalfHealth == false && life < maxLife/2) {
			bossHalfHealth = true;
			gp.playSE(30);
			gp.ui.addMessage("2nd Stage!");
			getImage();
			defaultSpeed++;
			speed = defaultSpeed;
			defense++;
			attack++;
		}
		
		if(!dormant) {
			
			int i = new Random().nextInt(200)+1;
			if(bossHalfHealth) {
				if(i > 99 && projectile.alive == false && (shotAvailableCounter == 60)) {
					
					projectile.set(worldX+24, worldY+24,  direction,  true,  this);
					
					// CHECK VACANCY
					for(int j = 0; j < gp.projectile[1].length;j++) {
						if(gp.projectile[gp.currentMap][j] == null) {
							gp.projectile[gp.currentMap][j] = projectile;
							break;
						}
					}
					
					shotAvailableCounter = 0;
				}
			}
			else {
				if(i > 197 && projectile.alive == false && shotAvailableCounter == 60) {
					
					projectile.set(worldX+24, worldY+24,  direction,  true,  this);
					
					// CHECK VACANCY
					for(int j = 0; j < gp.projectile[1].length;j++) {
						if(gp.projectile[gp.currentMap][j] == null) {
							gp.projectile[gp.currentMap][j] = projectile;
							break;
						}
					}
					
					shotAvailableCounter = 0;
				}
			}
			
			
			if(getTileDistance(gp.player) < 24 ) { // CANNOT USE PATHFINDING (instead an alternate method) FOR THIS BC ITS TOO BIG...
				
				moveTowardPlayer(60); // means every 60 frames, it checks player's position
			}
			else {
				getRandomDirection(120);	
				
				onPath = false;
				if(bossMusicOn) {
//					gp.stopMusic();
//					gp.playMusic(65);
					bossMusicOn = false;
				}
				gp.bossFightOn = false;
				hpBarOn = false;
			}
		}
		else {
			if(getTileDistance(gp.player) < 18 ) {
				if(bossMusicOn == false) {
					gp.bossFightOn = true;
				}
				if((getTileDistance(gp.player) < 3) || (life != maxLife)) {
					gp.playSE(66);
					gp.ui.addMessage("Samurai awakes!");
					defaultSpeed += 3;
					speed = defaultSpeed;
					dormant = false;
					getImage();
				}
			}
			else {
//				if(bossMusicOn) {
//					gp.stopMusic();
//					if(gp.dayState == gp.night || gp.dayState == gp.dawn) {
//						gp.playMusic(49);
//					}
//					else {
//						gp.playMusic(65);
//					}
//					bossMusicOn = false;
//					gp.bossFightOn = false;
//				}
			}
			
		}
		// check if it attacks
//		if(attacking == false) {
//			checkAttackOrNot(60,gp.tileSize*7, gp.tileSize*5);
//			
//		}
	}
//	public void bossMusic() {
//		gp.stopMusic();
//		gp.playMusic(64);
//		bossMusicOn = true;
//		//System.out.println("musicBoss");
//	}
	public void damageReaction() {
		
		actionLockCounter = 0;
	}
	public void checkDrop() {
		
		gp.bossBattleOn = false;
		Progress.samuraiBossDefeated = true;
		
		//restore the previous music;
		gp.stopMusic();
		gp.checkAllTheMusic();
		
		for(int i = 0; i < gp.obj[1].length; i++) {
			if(gp.obj[gp.currentMap][i] != null && gp.obj[gp.currentMap][i].name.equals(OBJ_Door_Iron.objName)) {
				gp.playSE(60);
				gp.obj[gp.currentMap][i] = null;
			}
		}
		
		gp.obj[5][5] = new OBJ_Door_Down(gp,true);
		gp.obj[5][5].worldX = gp.tileSize*50;
		gp.obj[5][5].worldY = gp.tileSize*71;
		
		gp.playSE(66);
		
		if(alreadyHasStatItem2 == false) { // so player can't farm these statBoosts
			if(gp.player.ranger) { 
				gp.obj[5][0] = new OBJ_Stat_Defense(gp);
				gp.obj[5][0].worldX = gp.tileSize*49;
				gp.obj[5][0].worldY = gp.tileSize*50;
				
				gp.obj[5][1] = new OBJ_Stat_HP(gp);
				gp.obj[5][1].worldX = gp.tileSize*50;
				gp.obj[5][1].worldY = gp.tileSize*50;
				
				gp.obj[5][2] = new OBJ_Stat_Ammo(gp);
				gp.obj[5][2].worldX = gp.tileSize*51;
				gp.obj[5][2].worldY = gp.tileSize*50;
				alreadyHasStatItem2 = true;
			}
			if(gp.player.guardian) {
				gp.obj[5][0] = new OBJ_Stat_Defense(gp);
				gp.obj[5][0].worldX = gp.tileSize*49;
				gp.obj[5][0].worldY = gp.tileSize*50;
				
				gp.obj[5][1] = new OBJ_Stat_Ammo(gp);
				gp.obj[5][1].worldX = gp.tileSize*50;
				gp.obj[5][1].worldY = gp.tileSize*50;
				
				gp.obj[5][2] = new OBJ_Stat_HP(gp);
				gp.obj[5][2].worldX = gp.tileSize*51;
				gp.obj[5][2].worldY = gp.tileSize*50;
				alreadyHasStatItem2 = true;
			}
			if(gp.player.specialist) {
				gp.obj[5][0] = new OBJ_Stat_Attack(gp);
				gp.obj[5][0].worldX = gp.tileSize*49;
				gp.obj[5][0].worldY = gp.tileSize*50;
				
				gp.obj[5][1] = new OBJ_Stat_HP(gp);
				gp.obj[5][1].worldX = gp.tileSize*50;
				gp.obj[5][1].worldY = gp.tileSize*50;
				
				gp.obj[5][2] = new OBJ_Stat_Ammo(gp);
				gp.obj[5][2].worldX = gp.tileSize*51;
				gp.obj[5][2].worldY = gp.tileSize*50;
				alreadyHasStatItem2 = true;
			}
			if(gp.player.warrior) {
				gp.obj[5][0] = new OBJ_Stat_Attack(gp);
				gp.obj[5][0].worldX = gp.tileSize*49;
				gp.obj[5][0].worldY = gp.tileSize*50;
				
				gp.obj[5][1] = new OBJ_Stat_Ammo(gp);
				gp.obj[5][1].worldX = gp.tileSize*50;
				gp.obj[5][1].worldY = gp.tileSize*50;
				
				gp.obj[5][2] = new OBJ_Stat_HP(gp);
				gp.obj[5][2].worldX = gp.tileSize*51;
				gp.obj[5][2].worldY = gp.tileSize*50;
				alreadyHasStatItem2 = true;
			}
		}
		
		// SET THE MONSTER DROP
		int i = new Random().nextInt(100);
		// SET THE MONSTER DROP
		if(i < 1) {
			gp.player.canObtainItem(new OBJ_Lightning(gp));
		}
		if(i >= 1 && i < 70) {
			dropItem(new OBJ_Coin_Emerald(gp));
		}
		if(i >= 70 && i < 100) {
			dropItem(new OBJ_Shield_Emerald(gp)); // add a max potion
		}
		
		gp.saveload.save();
		gp.ui.addMessage("Game saved!");
		//System.out.println("Before:"+gp.player.qH.bossesDefeated);
		if(gp.player.qH.checkAllBosses() && gp.beatEntireGame == false) {
			gp.stopMusic();
			gp.playMusic(47);
			gp.gameState = gp.gameCompleteState;
		}
	}
}
