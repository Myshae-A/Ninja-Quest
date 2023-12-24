package monster;

import java.util.Random;

import data.Progress;
import entity.Entity;
import main.GamePanel;
import object.OBJ_Chest;
import object.OBJ_Coin_Bronze;
import object.OBJ_Coin_Emerald;
import object.OBJ_Coin_Gold;
import object.OBJ_Coin_Silver;
import object.OBJ_Door_Iron;
import object.OBJ_Emblem_Craftsman;
import object.OBJ_Emblem_Guardian;
import object.OBJ_Emblem_Ranger;
import object.OBJ_Emblem_Specialist;
import object.OBJ_Emblem_Warrior;
import object.OBJ_Fireball;
import object.OBJ_Heart;
import object.OBJ_Key;
import object.OBJ_ManaCrystal;
import object.OBJ_Shield_Blue;
import object.OBJ_Shield_Metal;
import object.OBJ_Sign;
import object.OBJ_Travel_Stone;

public class MON_SlimeBoss extends Entity{
	
	GamePanel gp;
	public static final String monName = "Slime Boss";
	
	public MON_SlimeBoss(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		type = type_boss;
		sub_type = sub_type_boss;
		name = "Slime Boss";
		defaultSpeed = 1;
		speed = defaultSpeed;
		maxLife = 256;
		life = maxLife;
		attack = 4;
		defense = 1;
		exp = (int)(Math.random()*60)+240;
		knockBackPower = 6;
		
		
//		int size = gp.tileSize*5;
//		solidArea.x = 48;
//		solidArea.y = 48;
//		solidArea.width = size-48*2;
//		solidArea.height = size-48;
		
		int size = gp.tileSize*10;
		solidArea.x = 0;
		solidArea.y = gp.tileSize*2;
//		solidArea.width = gp.tileSize*5;
//		solidArea.height = gp.tileSize*4;
		solidArea.width = size;
		solidArea.height = size-48*2;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
//		attackArea.width = solidArea.width;
//		attackArea.height = solidArea.height;
		
		getImage();
		setDialogue();
	}
	public void getImage() {
		
		int i = 10;
		if(!bossHalfHealth) {
			up1 = setup("/monster/kingSlime_up_1", gp.tileSize*i, gp.tileSize*i);
			up2 = setup("/monster/kingSlime_up_2", gp.tileSize*i, gp.tileSize*i);
			down1 = setup("/monster/kingSlime_down_1", gp.tileSize*i, gp.tileSize*i);
			down2 = setup("/monster/kingSlime_down_2", gp.tileSize*i, gp.tileSize*i);
			left1 = setup("/monster/kingSlime_left_1", gp.tileSize*i, gp.tileSize*i);
			left2 = setup("/monster/kingSlime_left_2", gp.tileSize*i, gp.tileSize*i);
			right1 = setup("/monster/kingSlime_right_1", gp.tileSize*i, gp.tileSize*i);
			right2 = setup("/monster/kingSlime_right_2", gp.tileSize*i, gp.tileSize*i);
			
			up3 = setup("/monster/kingSlime_up_1", gp.tileSize*i, gp.tileSize*i);
			up4 = setup("/monster/kingSlime_up_2", gp.tileSize*i, gp.tileSize*i);
			down3 = setup("/monster/kingSlime_down_1", gp.tileSize*i, gp.tileSize*i);
			down4 = setup("/monster/kingSlime_down_2", gp.tileSize*i, gp.tileSize*i);
			left3 = setup("/monster/kingSlime_left_1", gp.tileSize*i, gp.tileSize*i);
			left4 = setup("/monster/kingSlime_left_2", gp.tileSize*i, gp.tileSize*i);
			right3 = setup("/monster/kingSlime_right_1", gp.tileSize*i, gp.tileSize*i);
			right4 = setup("/monster/kingSlime_right_2", gp.tileSize*i, gp.tileSize*i);
		}
		if(bossHalfHealth) {
			up1 = setup("/monster/kingSlime_left_1_stage2", gp.tileSize*i, gp.tileSize*i);
			up2 = setup("/monster/kingSlime_left_2_stage_2", gp.tileSize*i, gp.tileSize*i);
			down1 = setup("/monster/kingSlime_right_1_stage2", gp.tileSize*i, gp.tileSize*i);
			down2 = setup("/monster/kingSlime_right_2_stage2", gp.tileSize*i, gp.tileSize*i);
			left1 = setup("/monster/kingSlime_left_1_stage2", gp.tileSize*i, gp.tileSize*i);
			left2 = setup("/monster/kingSlime_left_2_stage_2", gp.tileSize*i, gp.tileSize*i);
			right1 = setup("/monster/kingSlime_right_1_stage2", gp.tileSize*i, gp.tileSize*i);
			right2 = setup("/monster/kingSlime_right_2_stage2", gp.tileSize*i, gp.tileSize*i);
			
			up3 = setup("/monster/kingSlime_left_1_stage2", gp.tileSize*i, gp.tileSize*i);
			up4 = setup("/monster/kingSlime_left_2_stage_2", gp.tileSize*i, gp.tileSize*i);
			down3 = setup("/monster/kingSlime_right_1_stage2", gp.tileSize*i, gp.tileSize*i);
			down4 = setup("/monster/kingSlime_right_2_stage2", gp.tileSize*i, gp.tileSize*i);
			left3 = setup("/monster/kingSlime_left_1_stage2", gp.tileSize*i, gp.tileSize*i);
			left4 = setup("/monster/kingSlime_left_2_stage_2", gp.tileSize*i, gp.tileSize*i);
			right3 = setup("/monster/kingSlime_right_1_stage2", gp.tileSize*i, gp.tileSize*i);
			right4 = setup("/monster/kingSlime_right_2_stage2", gp.tileSize*i, gp.tileSize*i);
		}
		
	}
	public void setDialogue() {
		
		dialogues[0][0] = "Yes. This is your first boss.";
		dialogues[0][1] = "You can look at the bottom of the screen to check\nthe boss health.";
		dialogues[0][2] = "Once their health bar is empty, then you beat them!";
		dialogues[0][3] = "You can't leave boss battles until either you or the\nboss dies.";
		dialogues[0][4] = "Quick tip: don't die.";
	}
	public void setAction() {
		
		
		if(bossLastHealth == false && life < maxLife/4) {
			bossLastHealth = true;
			gp.playSE(30);
			gp.ui.addMessage("Final Stage!");
			getImage();
			defaultSpeed++;
			speed = defaultSpeed;
			attack++;
			knockBackPower+= 5;
		}
		if(bossHalfHealth == false && life < maxLife/2) {
			bossHalfHealth = true;
			gp.playSE(30);
			gp.ui.addMessage("2nd Stage!");
			getImage();
			defaultSpeed++;
			speed = defaultSpeed;
			attack++;
		}
		
		if(getTileDistance(gp.player) < 45) { // He covers 90% of the map. I thought this would be more interesting...
			
			moveTowardPlayer(60); // means every 60 frames, it checks player's position
		}
		else {
			getRandomDirection(120);	
		}
	}
	public void damageReaction() {
		
		actionLockCounter = 0;
	}
	public void checkDrop() {
		
		gp.bossBattleOn = false;
		Progress.slimeBossDefeated = true;
		
		//restore the previous music;
		gp.stopMusic();
		
		for(int i = 0; i < gp.obj[1].length; i++) {
			if(gp.obj[gp.currentMap][i] != null && gp.obj[gp.currentMap][i].name.equals(OBJ_Door_Iron.objName)) {
				gp.playSE(60);
				gp.obj[gp.currentMap][i] = null;
			}
		}
		
		gp.player.worldX = gp.tileSize*24+24;
		gp.player.worldY = gp.tileSize*24+24;
		
		if(gp.player.craftsman || gp.player.guardian ||
				gp.player.specialist || gp.player.warrior) {
			// can't respawn class emblems
		}
		else {
			gp.obj[7][0] = new OBJ_Emblem_Ranger(gp);
			gp.obj[7][0].worldX = gp.tileSize*23;
			gp.obj[7][0].worldY = gp.tileSize*23;
			
			gp.obj[7][1] = new OBJ_Emblem_Guardian(gp);
			gp.obj[7][1].worldX = gp.tileSize*26;
			gp.obj[7][1].worldY = gp.tileSize*23;
			
			gp.obj[7][2] = new OBJ_Emblem_Specialist(gp);
			gp.obj[7][2].worldX = gp.tileSize*23;
			gp.obj[7][2].worldY = gp.tileSize*26;
			
			gp.obj[7][3] = new OBJ_Emblem_Warrior(gp);
			gp.obj[7][3].worldX = gp.tileSize*26;
			gp.obj[7][3].worldY = gp.tileSize*26;
			
			gp.obj[7][4] = new OBJ_Sign(gp);
			gp.obj[7][4].worldX = gp.tileSize*24+24;
			gp.obj[7][4].worldY = gp.tileSize*22;
		}
		
		for(int i = 1; i < 10;i++) {
			gp.monster[7][i] = null;
		}
		
		// CAST A DIE
		int i = new Random().nextInt(100);
		
		// SET THE MONSTER DROP
		if(i < 30) {
			dropItem(new OBJ_Shield_Blue(gp));
		}
		if(i >= 30 && i < 70) {
			dropItem(new OBJ_Key(gp));
		}
		if(i >= 70 && i < 100) {
			dropItem(new OBJ_Coin_Emerald(gp));
		}
		
		gp.stopMusic();
		gp.saveload.save();
		gp.ui.addMessage("Game saved!");
		//gp.playMusic(43);
		//System.out.println("Before:"+gp.player.qH.bossesDefeated);
		if(gp.player.qH.checkAllBosses() && gp.beatEntireGame == false) {
			gp.stopMusic();
			gp.playMusic(47);
			gp.gameState = gp.gameCompleteState;
		}
	}
}
