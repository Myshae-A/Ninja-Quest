package entity;

import java.awt.Rectangle;
import java.util.Random;

import main.GamePanel;

public class NPC_Hero_Esther extends Entity{

	public static final String npcName = "Esther";
	
	int pressCount = 0;
	int dialogueCounter = 0;
	
	public NPC_Hero_Esther(GamePanel gp) {
		super(gp);
		
		direction = "down";
		type = type_npc;
		name = npcName; // Guardian
		defaultSpeed = 1;
		speed = defaultSpeed;
		if(gp.eHandler.estherPressed == true) {
			sleep = false;
		}
		else {
			sleep = true;
		}
		
		solidArea = new Rectangle();
		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width = 40;
		solidArea.height = 48;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		dialogueSet = 4;
		
		getImage();
		setDialogue();
	}
	public void getImage() {
		
		up1 = setup("/npc/esther_up_1", gp.tileSize, gp.tileSize);
		up2 = setup("/npc/esther_up_2", gp.tileSize, gp.tileSize);
		down1 = setup("/npc/esther_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/npc/esther_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/npc/esther_left_1", gp.tileSize, gp.tileSize);
		left2 = setup("/npc/esther_left_2", gp.tileSize, gp.tileSize);
		right1 = setup("/npc/esther_right_1", gp.tileSize, gp.tileSize);
		right2 = setup("/npc/esther_right_2", gp.tileSize, gp.tileSize);
		
		up3 = setup("/npc/esther_up_1", gp.tileSize, gp.tileSize);
		up4 = setup("/npc/esther_up_2", gp.tileSize, gp.tileSize);
		down3 = setup("/npc/esther_down_1", gp.tileSize, gp.tileSize);
		down4 = setup("/npc/esther_down_2", gp.tileSize, gp.tileSize);
		left3 = setup("/npc/esther_left_1", gp.tileSize, gp.tileSize);
		left4 = setup("/npc/esther_left_2", gp.tileSize, gp.tileSize);
		right3 = setup("/npc/esther_right_1", gp.tileSize, gp.tileSize);
		right4 = setup("/npc/esther_right_2", gp.tileSize, gp.tileSize);
	}
	public void setDialogue() {
		
		dialogues[0][0] = "Press [SHIFT] to shield, you gotta have a shield\nequipped though. And why does nobody ever use\ntheir shield?";
		dialogues[0][1] = "In fact, do you even use your projectiles? Almost\neveryone I've seen never uses long-range attacks...";
		
		dialogues[1][0] = "I bet you've died a ton of times.";
		dialogues[1][1] = "This map won't make it any easier.";
		
		dialogues[2][0] = "I never knew my parents, I was only raised by my\ncousin. He protected me as his own daughter."; // MUST NOT HAVE EMPTY QUOTES LIKE THIS!!! CAUSES ERRORS IN DIALOGUE!!!
		dialogues[2][1] = "That's why I became a Guardian, to protect those\nwho can't protect themselves.";
		dialogues[2][2] = "Why did you become a hero?";
		
		dialogues[3][0] = "Don't expect me to come to your rescue when\nyou're about to die.";
		
		dialogues[4][0] = "Oh, another child who wants to play the hero?";
		dialogues[4][1] = "This is a serious business. How did you even make\nit this far?";
		dialogues[4][2] = "Only the strongest make it to this Island. If you\nreally are strong, then prove it...";
		dialogues[4][3] = "How about you challenge the Samurai, no one's ever\nbeat him, not even me.";
	}
	public void setAction() {
		
		if(onPath == true) {
			
//			int goalCol = 33; //coordinates to find axe
//			int goalRow = 7;
			int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize; //coordinates to find axe
			int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;
			
			searchPath(goalCol, goalRow);
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
				
				actionLockCounter = 0;
			}
		}
	}
	public void speak() {
		
		facePlayer();
		startDialogue(this,dialogueSet);
		
		dialogueSet++;
		
		if(dialogues[dialogueSet][0] == null) {
			
			dialogueSet = 0;
			// dialogueSet--; repeats the last set of dialogue
		}
		
	}
}
