package entity;

import java.awt.Rectangle;
import java.util.Random;

import main.GamePanel;

public class NPC_Traveler extends Entity{

	int pressCount = 0;
	int dialogueCounter = 0;
	
	public NPC_Traveler(GamePanel gp) {
		super(gp);
		
//		Random random = new Random(); // had to make all their directions random in first frame
//		int i = random.nextInt(100)+1;
//		if(i <= 25) {direction = "up";}
//		if(i > 25 && i <= 50) {direction = "down";}
//		if(i > 50 && i <= 75) {direction = "left";}
//		if(i > 75 && i <= 100) {direction = "right";}

		type = type_npc;
		name = "T-Bot";
		defaultSpeed = 0;
		speed = defaultSpeed;
		
		solidArea = new Rectangle();
		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width = 40;
		solidArea.height = 40;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		dialogueSet = -1;
		
		getImage();
		setDialogue();
	}
	public void getImage() {
		
		up1 = setup("/npc/travelbot_1", gp.tileSize, gp.tileSize);
		up2 = setup("/npc/travelbot_2", gp.tileSize, gp.tileSize);
		down1 = setup("/npc/travelbot_1", gp.tileSize, gp.tileSize);
		down2 = setup("/npc/travelbot_2", gp.tileSize, gp.tileSize);
		left1 = setup("/npc/travelbot_1", gp.tileSize, gp.tileSize);
		left2 = setup("/npc/travelbot_2", gp.tileSize, gp.tileSize);
		right1 = setup("/npc/travelbot_1", gp.tileSize, gp.tileSize);
		right2 = setup("/npc/travelbot_2", gp.tileSize, gp.tileSize);
		
		up3 = setup("/npc/travelbot_1", gp.tileSize, gp.tileSize);
		up4 = setup("/npc/travelbot_2", gp.tileSize, gp.tileSize);
		down3 = setup("/npc/travelbot_1", gp.tileSize, gp.tileSize);
		down4 = setup("/npc/travelbot_2", gp.tileSize, gp.tileSize);
		left3 = setup("/npc/travelbot_1", gp.tileSize, gp.tileSize);
		left4 = setup("/npc/travelbot_2", gp.tileSize, gp.tileSize);
		right3 = setup("/npc/travelbot_1", gp.tileSize, gp.tileSize);
		right4 = setup("/npc/travelbot_2", gp.tileSize, gp.tileSize);
	}
	public void setDialogue() {
		
		dialogues[0][0] = "Have at least 1 Travel Token to enter Beginner's\nIsle!";
		
		dialogues[1][0] = "You're already in this world!";
		
		dialogues[2][0] = "Have at least 3 Travel Tokens to enter Final Island!";
		
		dialogues[3][0] = "Have at least 2 Travel Tokens to enter Misty\nMeadows!";
		
		dialogues[4][0] = "Something is disrupting the teleportation. Wait and\talk to me later!";
		
		dialogues[5][0] = "That world doesn't exist yet!";
		
		dialogues[6][0] = "I am 'Travel Bot'! I can teleport you to wherever you\nwant to go.";
		
		dialogues[7][0] = "To get a Travel Token you must complete all your\nquests.";
		
		dialogues[8][0] = "I was built by a Specialist named John... exactly\n316 years ago!";
		
		dialogues[9][0] = "I do not need to recharge. John built me with his\nrare invention called the 'Eternal Ring'.";
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
		
		facePlayer(); // wont change much, but good practice for other npcs
		
		if(pressCount%2==0) {
			
			startDialogue(this,6+dialogueCounter);
			dialogueCounter++;
			if(dialogueCounter == 4) {
				dialogueCounter = 0;
			}
			pressCount++;
		}
		else {
			gp.gameState = gp.travelState;
			gp.ui.npc = this;
			pressCount++;
		}
		
	}
}
