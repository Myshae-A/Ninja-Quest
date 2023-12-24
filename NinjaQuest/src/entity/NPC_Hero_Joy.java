package entity;

import java.awt.Rectangle;
import java.util.Random;

import main.GamePanel;

public class NPC_Hero_Joy extends Entity{

	public static final String npcName = "Joy";
	
	int pressCount = 0;
	int dialogueCounter = 0;
	
	public NPC_Hero_Joy(GamePanel gp) {
		super(gp);
		
		direction = "down";
		type = type_npc;
		name = npcName; // Specialist
		defaultSpeed = 2;
		speed = defaultSpeed;
		if(gp.eHandler.danielPressed == true) {
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
		
		dialogueSet = 5;
		
		getImage();
		setDialogue();
	}
	public void getImage() {
		
		up1 = setup("/npc/joy_up_1", gp.tileSize, gp.tileSize);
		up2 = setup("/npc/joy_up_2", gp.tileSize, gp.tileSize);
		down1 = setup("/npc/joy_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/npc/joy_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/npc/joy_left_1", gp.tileSize, gp.tileSize);
		left2 = setup("/npc/joy_left_2", gp.tileSize, gp.tileSize);
		right1 = setup("/npc/joy_right_1", gp.tileSize, gp.tileSize);
		right2 = setup("/npc/joy_right_2", gp.tileSize, gp.tileSize);
		
		up3 = setup("/npc/joy_up_1", gp.tileSize, gp.tileSize);
		up4 = setup("/npc/joy_up_2", gp.tileSize, gp.tileSize);
		down3 = setup("/npc/joy_down_1", gp.tileSize, gp.tileSize);
		down4 = setup("/npc/joy_down_2", gp.tileSize, gp.tileSize);
		left3 = setup("/npc/joy_left_1", gp.tileSize, gp.tileSize);
		left4 = setup("/npc/joy_left_2", gp.tileSize, gp.tileSize);
		right3 = setup("/npc/joy_right_1", gp.tileSize, gp.tileSize);
		right4 = setup("/npc/joy_right_2", gp.tileSize, gp.tileSize);
	}
	public void setDialogue() {
		
		dialogues[0][0] = "Daniel's the strongest. Alden's the smartest. Esther's\nthe toughest. And I'm the fastest!";
		
		dialogues[1][0] = "Did you know I can play instruments like the piano,\nflute, and violin?! There's no instruements here\nthough.";
		
		dialogues[2][0] = "I enjoy playing or listening to music in peace and\nquiet. Gets my mind off... The Samurai...";
		dialogues[2][1] = "That's why I became a Specialist, to be invisible and\nsneak off to play music! The only problem is that\nmusic gets noisy.";
		
		dialogues[3][0] = "Specialists specialize in missions. Things like stealth\nand speed is best to get the job done."; // MUST NOT HAVE EMPTY QUOTES LIKE THIS!!! CAUSES ERRORS IN DIALOGUE!!!
		dialogues[3][1] = "We also enjoy using any weapons for battle.";
		
		dialogues[4][0] = "I don't really like fighting though, in fact, I also\nbecame a Specialist to be fast enough to run away\nfrom monsters!";
		dialogues[4][1] = "Ohh... Don't tell anyone else I said that...";
		
		dialogues[5][0] = "Yo! I can see you from here! You must be the new\nrecruit that Esther was telling me about.";
		dialogues[5][1] = "Oh btw, the bosses regenerate so you can fight\nthem multiple times.";
		dialogues[5][2] = "Watch out for the monsters on this map. They get\nprogressively harder too, not just the bosses.";
		dialogues[5][3] = "K, that's all I wanted to say, ttyl!";
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
