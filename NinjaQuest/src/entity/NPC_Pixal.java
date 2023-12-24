package entity;

import java.awt.Rectangle;
import java.util.Random;

import main.GamePanel;

public class NPC_Pixal extends Entity{
	
	public NPC_Pixal(GamePanel gp) {
		super(gp);
		
		type = type_npc;
		name = "Pixal";
		defaultSpeed = 1;
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
		
		up1 = setup("/npc/traveler_up_1", gp.tileSize, gp.tileSize);
		up2 = setup("/npc/traveler_up_2", gp.tileSize, gp.tileSize);
		down1 = setup("/npc/traveler_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/npc/traveler_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/npc/traveler_left_1", gp.tileSize, gp.tileSize);
		left2 = setup("/npc/traveler_left_2", gp.tileSize, gp.tileSize);
		right1 = setup("/npc/traveler_right_1", gp.tileSize, gp.tileSize);
		right2 = setup("/npc/traveler_right_2", gp.tileSize, gp.tileSize);
		
		up3 = setup("/npc/traveler_up_1", gp.tileSize, gp.tileSize);
		up4 = setup("/npc/traveler_up_2", gp.tileSize, gp.tileSize);
		down3 = setup("/npc/traveler_down_1", gp.tileSize, gp.tileSize);
		down4 = setup("/npc/traveler_down_2", gp.tileSize, gp.tileSize);
		left3 = setup("/npc/traveler_left_1", gp.tileSize, gp.tileSize);
		left4 = setup("/npc/traveler_left_2", gp.tileSize, gp.tileSize);
		right3 = setup("/npc/traveler_right_1", gp.tileSize, gp.tileSize);
		right4 = setup("/npc/traveler_right_2", gp.tileSize, gp.tileSize);
	}
	public void setDialogue() {
		
		dialogues[0][0] = "My father gave me this pink hairband when I was\nlittle... then he left us.";
		
		dialogues[1][0] = "Now i only have my old Grandpa... he's the guy\nwith the cane.";
		
		dialogues[2][0] = "I hope to live in a big city someday...";
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
		}
	}
}
