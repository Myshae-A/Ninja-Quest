package entity;

import java.awt.Rectangle;
import java.util.Random;

import main.GamePanel;

public class NPC_Hero_Daniel extends Entity{

	public static final String npcName = "Daniel";
	
	int pressCount = 0;
	int dialogueCounter = 0;
	
	public NPC_Hero_Daniel(GamePanel gp) {
		super(gp);
		
		direction = "down";
		type = type_npc;
		name = npcName; // Warrior
		defaultSpeed = 1;
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
		
		dialogueSet = 6;
		
		getImage();
		setDialogue();
	}
	public void getImage() {
		
		up1 = setup("/npc/daniel_up_1", gp.tileSize, gp.tileSize);
		up2 = setup("/npc/daniel_up_2", gp.tileSize, gp.tileSize);
		down1 = setup("/npc/daniel_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/npc/daniel_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/npc/daniel_left_1", gp.tileSize, gp.tileSize);
		left2 = setup("/npc/daniel_left_2", gp.tileSize, gp.tileSize);
		right1 = setup("/npc/daniel_right_1", gp.tileSize, gp.tileSize);
		right2 = setup("/npc/daniel_right_2", gp.tileSize, gp.tileSize);
		
		up3 = setup("/npc/daniel_up_1", gp.tileSize, gp.tileSize);
		up4 = setup("/npc/daniel_up_2", gp.tileSize, gp.tileSize);
		down3 = setup("/npc/daniel_down_1", gp.tileSize, gp.tileSize);
		down4 = setup("/npc/daniel_down_2", gp.tileSize, gp.tileSize);
		left3 = setup("/npc/daniel_left_1", gp.tileSize, gp.tileSize);
		left4 = setup("/npc/daniel_left_2", gp.tileSize, gp.tileSize);
		right3 = setup("/npc/daniel_right_1", gp.tileSize, gp.tileSize);
		right4 = setup("/npc/daniel_right_2", gp.tileSize, gp.tileSize);
	}
	public void setDialogue() {
		
		dialogues[0][0] = "You should collect the lantern if you haven't\nalready. It gets dark at night... you know.";
		dialogues[0][1] = "Tip: Parrying should be used against challenging\nenemies, not slimes.";
		
		dialogues[1][0] = "Team up with me and the others. Let's destroy\nThe Creature... 'The Samurai'!";
		dialogues[1][1] = "I'm looking for any books that might be helpful\nDid you know the bookshelves are readable?";
		
		dialogues[2][0] = "Completeing all your quests gives you a Travel\nToken, which you can use by talking to T-Bot.";
		
		dialogues[3][0] = "I'm in the Warrior class! If you want tips on what\nwe do, you can talk to me!";
		dialogues[3][1] = "I became a warrior to fight the monsters in this\nworld. They are not your friend.";
		
		dialogues[4][0] = "Warriors specialize in meelee combat. We like to\nfight up close and personal."; // MUST NOT HAVE EMPTY QUOTES LIKE THIS!!! CAUSES ERRORS IN DIALOGUE!!!
		dialogues[4][1] = "Although projectiles are still valid, I prefer the\nsword.";
		
		dialogues[5][0] = "DO YOU HAVE WHAT IT TAKES--"; // MUST NOT HAVE EMPTY QUOTES LIKE THIS!!! CAUSES ERRORS IN DIALOGUE!!!
		dialogues[5][1] = "--TO BECOME A HERO?!";
		
		dialogues[6][0] = "Welcome to Ninja Quest!"; // MUST NOT HAVE EMPTY QUOTES LIKE THIS!!! CAUSES ERRORS IN DIALOGUE!!!
		dialogues[6][1] = "In this game, your goal is to help our world fight\nagainst the monsters!";
		dialogues[6][2] = "You will have to complete all your quests on a map\nto progress in the game!";
		dialogues[6][3] = "Press [Q] to check your quests!";
		dialogues[6][4] = "I wish you the best of luck!";
		
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
