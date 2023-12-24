package monster;

import java.util.Random;

import data.Progress;
import entity.Entity;
import main.GamePanel;
import object.OBJ_Chest;
import object.OBJ_Coin_Emerald;
import object.OBJ_Door_Iron;
import object.OBJ_Fireball;
import object.OBJ_Key;
import object.OBJ_Stat_Ammo;
import object.OBJ_Stat_Attack;
import object.OBJ_Stat_Defense;
import object.OBJ_Stat_HP;
import object.OBJ_Travel_Stone;

public class MON_Pirate extends Entity{

	GamePanel gp;
	public static final String monName = "Pirate King";

	public boolean halfHealthSE = false;
	public boolean lastHealthSE = false;
	
	public MON_Pirate(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		type = type_boss;
		sub_type = sub_type_boss;
		name = "Pirate King";
		defaultSpeed = 1;
		speed = defaultSpeed;
		maxLife = 512;
		life = maxLife;
		attack = 6;
		defense = 2;
		exp = (int)(Math.random()*180)+240;
		projectile = new OBJ_Fireball(gp);
		knockBackPower = 8;
		sleep = true;
		
		solidArea.x = 2;
		solidArea.y = 2;
		solidArea.width = 46;
		solidArea.height = 46;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage();
		setDialogue();
	}
	public void getImage() {
		
		up1 = setup("/monster/pirate_up_1.1", gp.tileSize, gp.tileSize);
		up2 = setup("/monster/pirate_up_2.1", gp.tileSize, gp.tileSize);
		down1 = setup("/monster/pirate_down_1.3", gp.tileSize, gp.tileSize);
		down2 = setup("/monster/pirate_down_2.0", gp.tileSize, gp.tileSize);
		left1 = setup("/monster/pirate_left_1.1", gp.tileSize, gp.tileSize);
		left2 = setup("/monster/pirate_left_2.1", gp.tileSize, gp.tileSize);
		right1 = setup("/monster/pirate_right_1.0", gp.tileSize, gp.tileSize);
		right2 = setup("/monster/pirate_right_2.0", gp.tileSize, gp.tileSize);
		
		up3 = setup("/monster/pirate_up_1.1", gp.tileSize, gp.tileSize);
		up4 = setup("/monster/pirate_up_2.1", gp.tileSize, gp.tileSize);
		down3 = setup("/monster/pirate_down_1.3", gp.tileSize, gp.tileSize);
		down4 = setup("/monster/pirate_down_2.0", gp.tileSize, gp.tileSize);
		left3 = setup("/monster/pirate_left_1.1", gp.tileSize, gp.tileSize);
		left4 = setup("/monster/pirate_left_2.1", gp.tileSize, gp.tileSize);
		right3 = setup("/monster/pirate_right_1.0", gp.tileSize, gp.tileSize);
		right4 = setup("/monster/pirate_right_2.0", gp.tileSize, gp.tileSize);
	}
	public void setDialogue() {
		
		dialogues[0][0] = "Well... well... who do we have here?";
		dialogues[0][1] = "No one dares to disrupt me! You ever wonder why\nnobody knows who I am?";
		dialogues[0][2] = "Because no one has ever lived to tell the tale!";
		
	}
	public void update() {
		
		super.update();
		
		int xDistance = Math.abs(worldX - gp.player.worldX);
		int yDistance = Math.abs(worldY - gp.player.worldY);
		int tileDistance = (xDistance + yDistance)/gp.tileSize;
		if(onPath == false && tileDistance < 13) {
			
			
			onPath = true;
			
			//System.out.println("Hit here");
			hpBarOn = true;
		}
		if(onPath == true && tileDistance >= 30) { // 2nd condition,
			// if player runs at least 10 blocks away, monsters stop chasing
			
			onPath = false; // works, might look a little weird when
			// looking at the path, but it works.
		}
		if(life <= maxLife/2) {
			speed  = 2;
			attack  = 7;
			bossHalfHealth = true;
			if(halfHealthSE == false) {
				halfhealthSE();
			}
		}
		if(life <= maxLife/10) {
			speed = 3;
			defense = 3;
			attack = 8;
			bossLastHealth = true;
			if(lastHealthSE == false) {
				lasthealthSE();
			}
		}
		
	}
	public void setAction() {
		
		if(onPath == true) {
			
			//this.hpBarOn = true;
//			int goalCol = 33; //coordinates to find axe
//			int goalRow = 7;
			int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize; //coordinates to find axe
			int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;
			
			searchPath(goalCol, goalRow);
			
			int i = new Random().nextInt(200)+1;
			if(i > 197 && projectile.alive == false && shotAvailableCounter == 60) {
				
				projectile.set(worldX, worldY,  direction,  true,  this);
				//gp.projectileList.add(projectile);
				
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
			
			actionLockCounter ++;
			
			if(actionLockCounter == 120) {
				
				Random random = new Random();
				int i = random.nextInt(100)+1; // picks up number from 1 to 100
				
				if(i <= 25) {
					direction = "up";
				}
				if(i > 25 && i <= 50) {
					direction = "down";
				}
				if(i > 50 && i <= 75) {
					direction = "left";
				}
				if(i > 75 && i <= 100) {
					direction = "right";
				}
				
				actionLockCounter = 0; //Now Boss follows you too!!!
			}
		}
	}
	public void damageReaction() {
		
		//actionLockCounter = 0;
		
		// original AI
		//direction = gp.player.direction;
		
		// AI to make slimes come at you if you hit them
//		if(gp.player.direction == "down") {
//		direction = "up";
//		}
//		if(gp.player.direction == "up") {
//			direction = "down";
//		}
//		if(gp.player.direction == "left") {
//			direction = "right";
//		}
//		if(gp.player.direction == "right") {
//			direction = "left";
//		}
		onPath = true;
		speak();
	}
	public void checkDrop() {
		
		gp.bossBattleOn = false;
		Progress.pirateBossDefeated = true;
		
		//restore the previous music;
		gp.stopMusic();
		
		for(int i = 0; i < gp.obj[1].length; i++) {
			if(gp.obj[gp.currentMap][i] != null && gp.obj[gp.currentMap][i].name.equals(OBJ_Door_Iron.objName)) {
				gp.obj[gp.currentMap][i] = null;
			}
		}
		
		gp.playSE(4);
		
		gp.player.worldX = gp.tileSize*24+24;
		gp.player.worldY = gp.tileSize*26;
		
		gp.obj[2][0] = new OBJ_Travel_Stone(gp);
		gp.obj[2][0].worldX = gp.tileSize*25;
		gp.obj[2][0].worldY = gp.tileSize*24;
		
		gp.obj[2][1] = new OBJ_Chest(gp);
		gp.obj[2][1].setLoot(new OBJ_Fireball(gp));
		gp.obj[2][1].worldX = gp.tileSize*24;
		gp.obj[2][1].worldY = gp.tileSize*24;
		
		if(alreadyHasStatItem1 == false) { // so player can't farm these statBoosts
			if(gp.player.ranger) { 
				gp.obj[2][2] = new OBJ_Stat_Defense(gp);
				gp.obj[2][2].worldX = gp.tileSize*24;
				gp.obj[2][2].worldY = gp.tileSize*25;
				
				gp.obj[2][3] = new OBJ_Stat_Ammo(gp);
				gp.obj[2][3].worldX = gp.tileSize*25;
				gp.obj[2][3].worldY = gp.tileSize*25;
				alreadyHasStatItem1 = true;
			}
			if(gp.player.guardian) {
				gp.obj[2][2] = new OBJ_Stat_Defense(gp);
				gp.obj[2][2].worldX = gp.tileSize*24;
				gp.obj[2][2].worldY = gp.tileSize*25;
				
				gp.obj[2][3] = new OBJ_Stat_HP(gp);
				gp.obj[2][3].worldX = gp.tileSize*25;
				gp.obj[2][3].worldY = gp.tileSize*25;
				alreadyHasStatItem1 = true;
			}
			if(gp.player.specialist) {
				gp.obj[2][2] = new OBJ_Stat_Attack(gp);
				gp.obj[2][2].worldX = gp.tileSize*24;
				gp.obj[2][2].worldY = gp.tileSize*25;
				
				gp.obj[2][3] = new OBJ_Stat_Ammo(gp);
				gp.obj[2][3].worldX = gp.tileSize*25;
				gp.obj[2][3].worldY = gp.tileSize*25;
				alreadyHasStatItem1 = true;
			}
			if(gp.player.warrior) {
				gp.obj[2][2] = new OBJ_Stat_Attack(gp);
				gp.obj[2][2].worldX = gp.tileSize*24;
				gp.obj[2][2].worldY = gp.tileSize*25;
				
				gp.obj[2][3] = new OBJ_Stat_HP(gp);
				gp.obj[2][3].worldX = gp.tileSize*25;
				gp.obj[2][3].worldY = gp.tileSize*25;
				alreadyHasStatItem1 = true;
			}
		}
		
		for(int i = 1; i < 11;i++) {
			gp.monster[4][i] = null;
		}
		
		// CAST A DIE
		int i = new Random().nextInt(100)+1;
		// SET THE MONSTER DROP
		
		if(i < 75) {
			dropItem(new OBJ_Coin_Emerald(gp));
		}
		
		// Boots don't really work, so this will be later, if i even get to it...
		
//		else if(i > 50 && i < 75) {
//			if(gp.player.canObtainItem(new OBJ_Boots(gp)) == true) {
//				gp.ui.addMessage("You obtained boots!");
//			}
//			else {
//				gp.ui.addMessage("How unlucky!!!");
//			}
//		}
		else {
			if(gp.player.canObtainItem(new OBJ_Key(gp)) == true) {
				gp.ui.addMessage("Key obtained!");
			}
			else {
				gp.ui.addMessage("How unlucky!!!");
			}
		}
		gp.stopMusic();
		gp.beatBoss = true;
		
		gp.saveload.save();
		gp.ui.addMessage("Game saved!");
		//System.out.println("Before:"+gp.player.qH.bossesDefeated);
		if(gp.player.qH.checkAllBosses() && gp.beatEntireGame == false) {
			gp.stopMusic();
			gp.playMusic(47);
			gp.gameState = gp.gameCompleteState;
		}
	}

	public void halfhealthSE() {
		gp.playSE(25);
		halfHealthSE = true;
		gp.ui.addMessage("Boss 2nd Stage!");
	}
	public void lasthealthSE() {
		gp.playSE(25);
		lastHealthSE = true;
		gp.ui.addMessage("Boss Final Stage!");
	}
	public void speak() {
		
		// Do this character specific stuff
		
		super.speak();
		
		//onPath = true; // Can now use pathfinding for NPCs when desired
		
	}
}
