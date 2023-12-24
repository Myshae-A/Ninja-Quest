package entity;

import java.awt.Rectangle;
import java.util.Random;

import main.GamePanel;

public class NPC_Hero_Chad extends Entity{

	public static final String npcName = "Chad";
	
	int pressCount = 0;
	int dialogueCounter = 0;
	
	public NPC_Hero_Chad(GamePanel gp) {
		super(gp);
		
		Random random = new Random(); // had to make all their directions random in first frame
		int i = random.nextInt(100)+1;
		if(i <= 25) {direction = "up";}
		if(i > 25 && i <= 50) {direction = "down";}
		if(i > 50 && i <= 75) {direction = "left";}
		if(i > 75 && i <= 100) {direction = "right";}

		type = type_npc;
		name = npcName; // Craftsman
		defaultSpeed = 1;
		speed = defaultSpeed;
		
		solidArea = new Rectangle();
		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width = 40;
		solidArea.height = 48;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		dialogueSet = -1;
		
		getImage();
		setDialogue();
	}
	public void getImage() {
		
		up1 = setup("/npc/Chad_up_1", gp.tileSize, gp.tileSize);
		up2 = setup("/npc/Chad_up_2", gp.tileSize, gp.tileSize);
		down1 = setup("/npc/Chad_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/npc/Chad_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/npc/Chad_left_1", gp.tileSize, gp.tileSize);
		left2 = setup("/npc/Chad_left_2", gp.tileSize, gp.tileSize);
		right1 = setup("/npc/Chad_right_1", gp.tileSize, gp.tileSize);
		right2 = setup("/npc/Chad_right_2", gp.tileSize, gp.tileSize);
		
		up3 = setup("/npc/Chad_up_3", gp.tileSize, gp.tileSize);
		up4 = setup("/npc/Chad_up_4", gp.tileSize, gp.tileSize);
		down3 = setup("/npc/Chad_down_3", gp.tileSize, gp.tileSize);
		down4 = setup("/npc/Chad_down_4", gp.tileSize, gp.tileSize);
		left3 = setup("/npc/Chad_left_3", gp.tileSize, gp.tileSize);
		left4 = setup("/npc/Chad_left_4", gp.tileSize, gp.tileSize);
		right3 = setup("/npc/Chad_right_3", gp.tileSize, gp.tileSize);
		right4 = setup("/npc/Chad_right_4", gp.tileSize, gp.tileSize);
	}
	public void setDialogue() {
		
		dialogues[0][0] = "You can press on the Monster Spawners to respawn\nenemies. It's useful for farming.";
		
		dialogues[1][0] = "Have you seen Daniel? There's 3 other heros just\nlike him."; // MUST NOT HAVE EMPTY QUOTES LIKE THIS!!! CAUSES ERRORS IN DIALOGUE!!!
		dialogues[1][1] = "They're all located in different maps.";
	
		dialogues[2][0] = "Well, I'll let you go about your way.";
//		
//		dialogues[3][0] = "";
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
