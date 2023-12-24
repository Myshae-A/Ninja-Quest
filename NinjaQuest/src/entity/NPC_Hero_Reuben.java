package entity;

import java.awt.Rectangle;
import java.util.Random;

import main.GamePanel;

public class NPC_Hero_Reuben extends Entity{

	public static final String npcName = "Alden";
	
	int pressCount = 0;
	int dialogueCounter = 0;
	
	public NPC_Hero_Reuben(GamePanel gp) {
		super(gp);
		
		direction = "down";
		type = type_npc;
		name = npcName; // Craftsman
		defaultSpeed = 1;
		speed = defaultSpeed;
		if(gp.eHandler.aldenPressed == true) {
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
		
		up1 = setup("/npc/reuben_up_1", gp.tileSize, gp.tileSize);
		up2 = setup("/npc/reuben_up_2", gp.tileSize, gp.tileSize);
		down1 = setup("/npc/reuben_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/npc/reuben_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/npc/reuben_left_1", gp.tileSize, gp.tileSize);
		left2 = setup("/npc/reuben_left_2", gp.tileSize, gp.tileSize);
		right1 = setup("/npc/reuben_right_1", gp.tileSize, gp.tileSize);
		right2 = setup("/npc/reuben_right_2", gp.tileSize, gp.tileSize);
		
		up3 = setup("/npc/reuben_up_1", gp.tileSize, gp.tileSize);
		up4 = setup("/npc/reuben_up_2", gp.tileSize, gp.tileSize);
		down3 = setup("/npc/reuben_down_1", gp.tileSize, gp.tileSize);
		down4 = setup("/npc/reuben_down_2", gp.tileSize, gp.tileSize);
		left3 = setup("/npc/reuben_left_1", gp.tileSize, gp.tileSize);
		left4 = setup("/npc/reuben_left_2", gp.tileSize, gp.tileSize);
		right3 = setup("/npc/reuben_right_1", gp.tileSize, gp.tileSize);
		right4 = setup("/npc/reuben_right_2", gp.tileSize, gp.tileSize);
	}
	public void setDialogue() {
		
		dialogues[0][0] = "I can't even sleep anymore after having fought\nthe Samurai...";
		dialogues[0][1] = "He's the strongest boss in this world, you don't\nwanna mess with him.";
		
		dialogues[1][0] = "I've been working on selling some of my crafting\nitems to the Merchants! So much to do, I wanna just\nrelax.";
		dialogues[1][1] = "You know, sometimes people need to take a break\nand enjoy life.";
		
		dialogues[2][0] = "I'm in the Craftsman class by the way. People like\nme are pretty chill."; // MUST NOT HAVE EMPTY QUOTES LIKE THIS!!! CAUSES ERRORS IN DIALOGUE!!!
		dialogues[2][1] = "Have you heard of John? He was a legendary\nCraftsman who lived long ago, I want to be great\nlike him.";
		
		dialogues[3][0] = "You see, my family was poor so I decided to become\na Craftsman to make money and sell items.";
		dialogues[3][1] = "You can't buy anything from me though... Only from\nthe merchants.";
		
		dialogues[4][0] = "Hey there! I see you've progressed to the next map.";
		dialogues[4][1] = "Oh, and it seems you have chosen a class as well!";
		dialogues[4][2] = "I'm sure beating the Slime Boss must've been quite\nthe pickle.";
		dialogues[4][3] = "Well, I hate to be the bearer of bad news, but the\nbosses only get harder from here...";
		dialogues[4][4] = "Don't worry, me and the others have slain plenty of\nthe foes. You'll get there!";
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
			// dialogueSet--; repeats the last set of dialogue
		}
		
	}
}
