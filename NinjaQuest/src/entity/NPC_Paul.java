package entity;

import java.awt.Rectangle;
import java.util.Random;

import main.GamePanel;

public class NPC_Paul extends Entity{

	public NPC_Paul(GamePanel gp) {
		super(gp);
		
		Random random = new Random(); // had to make all their directions random in first frame
		int i = random.nextInt(100)+1;
		if(i <= 25) {direction = "up";}
		if(i > 25 && i <= 50) {direction = "down";}
		if(i > 50 && i <= 75) {direction = "left";}
		if(i > 75 && i <= 100) {direction = "right";}
		
		// He will always go down towards player first
		type = type_npc;
		name = "Paul";
		defaultSpeed = 1;
		speed = defaultSpeed;
		
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 4;
		solidArea.width = 36;
		solidArea.height = 42;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		dialogueSet = -1;
		
		getImage();
		setDialogue();
	}
	public void getImage() {
		
		up1 = setup("/npc/Paul7", gp.tileSize, gp.tileSize);
		up2 = setup("/npc/Paul11", gp.tileSize, gp.tileSize);
		down1 = setup("/npc/Paul6", gp.tileSize, gp.tileSize);
		down2 = setup("/npc/Paul10", gp.tileSize, gp.tileSize);
		left1 = setup("/npc/Paul5", gp.tileSize, gp.tileSize);
		left2 = setup("/npc/Paul9", gp.tileSize, gp.tileSize);
		right1 = setup("/npc/Paul8", gp.tileSize, gp.tileSize);
		right2 = setup("/npc/Paul12", gp.tileSize, gp.tileSize);
		
		up3 = setup("/npc/Paul7", gp.tileSize, gp.tileSize);
		up4 = setup("/npc/Paul11", gp.tileSize, gp.tileSize);
		down3 = setup("/npc/Paul6", gp.tileSize, gp.tileSize);
		down4 = setup("/npc/Paul10", gp.tileSize, gp.tileSize);
		left3 = setup("/npc/Paul5", gp.tileSize, gp.tileSize);
		left4 = setup("/npc/Paul9", gp.tileSize, gp.tileSize);
		right3 = setup("/npc/Paul8", gp.tileSize, gp.tileSize);
		right4 = setup("/npc/Paul12", gp.tileSize, gp.tileSize);
	}
	public void setDialogue() {
		
		dialogues[0][0] = "My name is "+name+", I hope you are having a blessed\nday.";
		
		dialogues[1][0] = "What are your goals in life? Have you any wisdom?";
		
		dialogues[2][0] = "I used to be a rebel, but now I've become wiser\nwith age.";
	}
	public void setAction() {
		
		if(onPath == true) {
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
//		gp.ui.drawSubWindow(gp.tileSize*3, gp.tileSize/2+gp.tileSize*4, (gp.screenWidth - (gp.tileSize*6))/4, gp.tileSize*4/3); // for NPC's only... to put their names...
		dialogueSet++;
		if(dialogues[dialogueSet][0] == null) {
			dialogueSet = 0;
		}
	}
}
