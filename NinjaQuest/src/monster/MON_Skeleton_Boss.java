package monster;

import java.util.Random;

import data.Progress;
import entity.Entity;
import main.GamePanel;
import object.OBJ_Chest;
import object.OBJ_Coin_Emerald;
import object.OBJ_Coin_Gold;
import object.OBJ_Door_Iron;
import object.OBJ_Heart;
import object.OBJ_Hero_Crafting_Table;
import object.OBJ_Hero_Guardian_Field;
import object.OBJ_Hero_Marksman_Bow;
import object.OBJ_Hero_Special_Cloak;
import object.OBJ_Hero_Warrior_Sword;
import object.OBJ_Key;
import object.OBJ_Lantern_Blue;
import object.OBJ_ManaCrystal;
import object.OBJ_Pickaxe;
import object.OBJ_Shield_Metal;
import object.OBJ_Sword_Metal;

public class MON_Skeleton_Boss extends Entity{

	GamePanel gp;
	public static final String monName = "Skeleton Lord";
	
	public MON_Skeleton_Boss(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		type = type_boss; // will have to change to type_boss soon
		sub_type = sub_type_boss;
		name = monName;
		defaultSpeed = 1;
		speed = defaultSpeed;
		maxLife = 512;
		life = maxLife;
		attack = 5;
		defense = 1;
		exp = (int)(Math.random()*120)+240;
		knockBackPower = 7;
		sleep = true;
		
		
		int size = gp.tileSize*5;
		solidArea.x = 48;
		solidArea.y = 48;
		solidArea.width = size-48*2;
		solidArea.height = size-48;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		attackArea.width = 170;
		attackArea.height = 170;
		motion1_duration = 20;
		motion2_duration = 50;
		
		getImage();
		getAttackImage();
		setDialogue();
	}
	public void getImage() {
		
		int i = 5;
		
		if(bossHalfHealth == false) {
			up1 = setup("/monster/skeletonlord_up_1", gp.tileSize*i, gp.tileSize*i); // 240x240
			up2 = setup("/monster/skeletonlord_up_2", gp.tileSize*i, gp.tileSize*i);
			down1 = setup("/monster/skeletonlord_down_1", gp.tileSize*i, gp.tileSize*i);
			down2 = setup("/monster/skeletonlord_down_2", gp.tileSize*i, gp.tileSize*i);
			left1 = setup("/monster/skeletonlord_left_1", gp.tileSize*i, gp.tileSize*i);
			left2 = setup("/monster/skeletonlord_left_2", gp.tileSize*i, gp.tileSize*i);
			right1 = setup("/monster/skeletonlord_right_1", gp.tileSize*i, gp.tileSize*i);
			right2 = setup("/monster/skeletonlord_right_2", gp.tileSize*i, gp.tileSize*i);
			
			up3 = setup("/monster/skeletonlord_up_1", gp.tileSize*i, gp.tileSize*i); // 240x240
			up4 = setup("/monster/skeletonlord_up_2", gp.tileSize*i, gp.tileSize*i);
			down3 = setup("/monster/skeletonlord_down_1", gp.tileSize*i, gp.tileSize*i);
			down4 = setup("/monster/skeletonlord_down_2", gp.tileSize*i, gp.tileSize*i);
			left3 = setup("/monster/skeletonlord_left_1", gp.tileSize*i, gp.tileSize*i);
			left4 = setup("/monster/skeletonlord_left_2", gp.tileSize*i, gp.tileSize*i);
			right3 = setup("/monster/skeletonlord_right_1", gp.tileSize*i, gp.tileSize*i);
			right4 = setup("/monster/skeletonlord_right_2", gp.tileSize*i, gp.tileSize*i);
		}
		if(bossHalfHealth == true) {
			up1 = setup("/monster/skeletonlord_phase2_up_1", gp.tileSize*i, gp.tileSize*i); // 240x240
			up2 = setup("/monster/skeletonlord_phase2_up_2", gp.tileSize*i, gp.tileSize*i);
			down1 = setup("/monster/skeletonlord_phase2_down_1", gp.tileSize*i, gp.tileSize*i);
			down2 = setup("/monster/skeletonlord_phase2_down_2", gp.tileSize*i, gp.tileSize*i);
			left1 = setup("/monster/skeletonlord_phase2_left_1", gp.tileSize*i, gp.tileSize*i);
			left2 = setup("/monster/skeletonlord_phase2_left_2", gp.tileSize*i, gp.tileSize*i);
			right1 = setup("/monster/skeletonlord_phase2_right_1", gp.tileSize*i, gp.tileSize*i);
			right2 = setup("/monster/skeletonlord_phase2_right_2", gp.tileSize*i, gp.tileSize*i);
			
			up3 = setup("/monster/skeletonlord_phase2_up_1", gp.tileSize*i, gp.tileSize*i); // 240x240
			up4 = setup("/monster/skeletonlord_phase2_up_2", gp.tileSize*i, gp.tileSize*i);
			down3 = setup("/monster/skeletonlord_phase2_down_1", gp.tileSize*i, gp.tileSize*i);
			down4 = setup("/monster/skeletonlord_phase2_down_2", gp.tileSize*i, gp.tileSize*i);
			left3 = setup("/monster/skeletonlord_phase2_left_1", gp.tileSize*i, gp.tileSize*i);
			left4 = setup("/monster/skeletonlord_phase2_left_2", gp.tileSize*i, gp.tileSize*i);
			right3 = setup("/monster/skeletonlord_phase2_right_1", gp.tileSize*i, gp.tileSize*i);
			right4 = setup("/monster/skeletonlord_phase2_right_2", gp.tileSize*i, gp.tileSize*i);
		}
		
		
	}
	public void getAttackImage() {
		
		int i = 5;
		
		if(bossHalfHealth == false) {
			attackUp1 = setup("/monster/skeletonlord_attack_up_1", gp.tileSize*i, gp.tileSize*i*2);
			attackUp2 = setup("/monster/skeletonlord_attack_up_2", gp.tileSize*i, gp.tileSize*i*2);
			attackDown1 = setup("/monster/skeletonlord_attack_down_1", gp.tileSize*i, gp.tileSize*i*2);
			attackDown2 = setup("/monster/skeletonlord_attack_down_2", gp.tileSize*i, gp.tileSize*i*2);
			attackLeft1 = setup("/monster/skeletonlord_attack_left_1", gp.tileSize*i*2, gp.tileSize*i);
			attackLeft2 = setup("/monster/skeletonlord_attack_left_2", gp.tileSize*i*2, gp.tileSize*i);
			attackRight1 = setup("/monster/skeletonlord_attack_right_1", gp.tileSize*i*2, gp.tileSize*i);
			attackRight2 = setup("/monster/skeletonlord_attack_right_2", gp.tileSize*i*2, gp.tileSize*i);
		}
		if(bossHalfHealth == true) {
			attackUp1 = setup("/monster/skeletonlord_phase2_attack_up_1", gp.tileSize*i, gp.tileSize*i*2);
			attackUp2 = setup("/monster/skeletonlord_phase2_attack_up_2", gp.tileSize*i, gp.tileSize*i*2);
			attackDown1 = setup("/monster/skeletonlord_phase2_attack_down_1", gp.tileSize*i, gp.tileSize*i*2);
			attackDown2 = setup("/monster/skeletonlord_phase2_attack_down_2", gp.tileSize*i, gp.tileSize*i*2);
			attackLeft1 = setup("/monster/skeletonlord_phase2_attack_left_1", gp.tileSize*i*2, gp.tileSize*i);
			attackLeft2 = setup("/monster/skeletonlord_phase2_attack_left_2", gp.tileSize*i*2, gp.tileSize*i);
			attackRight1 = setup("/monster/skeletonlord_phase2_attack_right_1", gp.tileSize*i*2, gp.tileSize*i);
			attackRight2 = setup("/monster/skeletonlord_phase2_attack_right_2", gp.tileSize*i*2, gp.tileSize*i);
		}
		
	}
	public void setDialogue() {
		
		dialogues[0][0] = "I wanted to bring peace to this world...";
		dialogues[0][1] = "But they neglected me because I was a monster.";
		dialogues[0][2] = "I never wanted to be your enemy, but you are\nhelping the real monsters--the humans!";
		dialogues[0][3] = "I have no choice, you cannot be a hero.\nWelcome to your DOOM!";
	}
	public void setAction() {
		
		if(bossHalfHealth == false && life < maxLife/2) {
			bossHalfHealth = true;
			gp.playSE(30);
			gp.ui.addMessage("2nd Stage!");
			getImage();
			getAttackImage();
			defaultSpeed++;
			speed = defaultSpeed;
			attack += 5;
		}
		
		
		if(getTileDistance(gp.player) < 10 ) { // CANNOT USE PATHFINDING (instead an alternate method) FOR THIS BC ITS TOO BIG...
			
			moveTowardPlayer(60); // means every 60 frames, it checks player's position
		}
		else {
			// get a random direction, if its not onPath
			getRandomDirection(120);	
		}
		
		// check if it attacks
		if(attacking == false) {
			checkAttackOrNot(60,gp.tileSize*7, gp.tileSize*5);
			
		}
	}
	public void damageReaction() {
		
		actionLockCounter = 0;
	}
	public void checkDrop() {
		
		gp.bossBattleOn = false;
		Progress.skeletonBossDefeated = true;
		
		//restore the previous music;
		gp.stopMusic();
		// gp.playMmusic(something);
		
		for(int i = 0; i < gp.obj[1].length; i++) {
			if(gp.obj[gp.currentMap][i] != null && gp.obj[gp.currentMap][i].name.equals(OBJ_Door_Iron.objName)) {
				gp.playSE(60);
				gp.obj[gp.currentMap][i] = null;
			}
		}
		
		gp.playSE(33);
		gp.player.canObtainItem(new OBJ_Key(gp));
		
		boolean alreadyHasSpecialItem = false;
		
		for(int i = 0; i < gp.player.inventory.size(); i++) {
			if(gp.player.inventory.get(i).type == type_special) {
				alreadyHasSpecialItem = true; // can only gain special stats once... Otherwise, u get a metal sword
			}
		}
		
		if(alreadyHasSpecialItem == false) {
			if(gp.player.ranger) {
				gp.obj[4][0] = new OBJ_Chest(gp);
				gp.obj[4][0].setLoot(new OBJ_Hero_Marksman_Bow(gp));
				gp.obj[4][0].worldX = gp.tileSize*25;
				gp.obj[4][0].worldY = gp.tileSize*8;
			}
			if(gp.player.guardian) {
				gp.obj[4][0] = new OBJ_Chest(gp);
				gp.obj[4][0].setLoot(new OBJ_Hero_Guardian_Field(gp));
				gp.obj[4][0].worldX = gp.tileSize*25;
				gp.obj[4][0].worldY = gp.tileSize*8;
			}
			if(gp.player.specialist) {
				gp.obj[4][0] = new OBJ_Chest(gp);
				gp.obj[4][0].setLoot(new OBJ_Hero_Special_Cloak(gp));
				gp.obj[4][0].worldX = gp.tileSize*25;
				gp.obj[4][0].worldY = gp.tileSize*8;
			}
			if(gp.player.warrior) {
				gp.obj[4][0] = new OBJ_Chest(gp);
				gp.obj[4][0].setLoot(new OBJ_Hero_Warrior_Sword(gp));
				gp.obj[4][0].worldX = gp.tileSize*25;
				gp.obj[4][0].worldY = gp.tileSize*8;
			}
		}
		else {
			gp.obj[4][0] = new OBJ_Chest(gp);
			gp.obj[4][0].setLoot(new OBJ_Sword_Metal(gp));
			gp.obj[4][0].worldX = gp.tileSize*25;
			gp.obj[4][0].worldY = gp.tileSize*8;
		}
		
		// CAST A DIE
		int i = new Random().nextInt(100);
		// SET THE MONSTER DROP
		if(i < 30) {
			dropItem(new OBJ_Shield_Metal(gp));
		}
		if(i >= 30 && i < 70) {
			dropItem(new OBJ_Coin_Emerald(gp));
		}
		if(i >= 70 && i < 100) {
			dropItem(new OBJ_Pickaxe(gp));
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
