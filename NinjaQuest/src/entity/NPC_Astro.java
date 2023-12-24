package entity;

import java.awt.Rectangle;
import java.util.Random;

import main.GamePanel;

public class NPC_Astro extends Entity{

	public NPC_Astro(GamePanel gp) {
		super(gp);
		
		Random random = new Random(); // had to make all their directions random in first frame
		int i = random.nextInt(100)+1;
		if(i <= 25) {direction = "up";}
		if(i > 25 && i <= 50) {direction = "down";}
		if(i > 50 && i <= 75) {direction = "left";}
		if(i > 75 && i <= 100) {direction = "right";}
		
		// He will always go down towards player first
		type = type_npc;
		name = "Astro";
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
		
		up1 = setup("/npc/astro_up_1", gp.tileSize, gp.tileSize);
		up2 = setup("/npc/astro_up_2", gp.tileSize, gp.tileSize);
		down1 = setup("/npc/astro_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/npc/astro_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/npc/astro_left_1", gp.tileSize, gp.tileSize);
		left2 = setup("/npc/astro_left_2", gp.tileSize, gp.tileSize);
		right1 = setup("/npc/astro_right_1", gp.tileSize, gp.tileSize);
		right2 = setup("/npc/astro_right_2", gp.tileSize, gp.tileSize);
		
		up3 = setup("/npc/astro_up_1", gp.tileSize, gp.tileSize);
		up4 = setup("/npc/astro_up_2", gp.tileSize, gp.tileSize);
		down3 = setup("/npc/astro_down_1", gp.tileSize, gp.tileSize);
		down4 = setup("/npc/astro_down_2", gp.tileSize, gp.tileSize);
		left3 = setup("/npc/astro_left_1", gp.tileSize, gp.tileSize);
		left4 = setup("/npc/astro_left_2", gp.tileSize, gp.tileSize);
		right3 = setup("/npc/astro_right_1", gp.tileSize, gp.tileSize);
		right4 = setup("/npc/astro_right_2", gp.tileSize, gp.tileSize);
	}
	public void setDialogue() {
		
		dialogues[0][0] = "Hi, are you the new person in town?";
		
		dialogues[1][0] = "Oh yea, this is the sister-village of a bigger town\nnot too far from here.";
		dialogues[1][1] = "However, we villagers must travel by foot. We don't\nlike using the teleporters.";
		
		dialogues[2][0] = "There is a merchant around here, but he's lurking\naway in a corner somewhere...";
		
		dialogues[3][0] = "I used to be responsible for helping everybody get\nfood and stuff.";
		dialogues[3][1] = "But one day the heros visited us, and they appointed\nJoy to be here.";
		dialogues[3][2] = "So now Joy helps us get items and protects us.\nShe's also fast which helps a lot!";
		
		dialogues[4][0] = "Joy protects us... Sounds a bit funny doesn't it?";
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
		dialogueSet++;
		if(dialogues[dialogueSet][0] == null) {
			dialogueSet = 0;
		}
	}
}
