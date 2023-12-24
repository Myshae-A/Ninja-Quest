package entity;

import java.awt.Rectangle;
import java.util.Random;

import main.GamePanel;

public class NPC_OldMan extends Entity{

	public NPC_OldMan(GamePanel gp) {
		super(gp);
		
		// He will always go down towards player first
		type = type_npc;
		name = "Gandalf";
		direction = "left";
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
		
		up1 = setup("/npc/oldman_up_1", gp.tileSize, gp.tileSize);
		up2 = setup("/npc/oldman_up_2", gp.tileSize, gp.tileSize);
		down1 = setup("/npc/oldman_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/npc/oldman_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/npc/oldman_left_1", gp.tileSize, gp.tileSize);
		left2 = setup("/npc/oldman_left_2", gp.tileSize, gp.tileSize);
		right1 = setup("/npc/oldman_right_1", gp.tileSize, gp.tileSize);
		right2 = setup("/npc/oldman_right_2", gp.tileSize, gp.tileSize);
		
		up3 = setup("/npc/oldman_up_1", gp.tileSize, gp.tileSize);
		up4 = setup("/npc/oldman_up_2", gp.tileSize, gp.tileSize);
		down3 = setup("/npc/oldman_down_1", gp.tileSize, gp.tileSize);
		down4 = setup("/npc/oldman_down_2", gp.tileSize, gp.tileSize);
		left3 = setup("/npc/oldman_left_1", gp.tileSize, gp.tileSize);
		left4 = setup("/npc/oldman_left_2", gp.tileSize, gp.tileSize);
		right3 = setup("/npc/oldman_right_1", gp.tileSize, gp.tileSize);
		right4 = setup("/npc/oldman_right_2", gp.tileSize, gp.tileSize);
	}
	public void setDialogue() {
		
		// 1st number indicates the dialogueset, so they will be displayed together
		
		dialogues[0][0] = "You may call me Grandpa, good day to you.";
		
		dialogues[1][0] = "You should collect the lantern if you haven't\nalready. It gets dark at night... you know.";
		dialogues[1][1] = "Tip: Parrying should be used against challenging\nenemies, not slimes.";
		
		dialogues[2][0] = "*Have you met my granddaughter Pixal? She is\nquite the character.";
		dialogues[2][1] = "Ah yes, we fled to this island to avoid the war.";
		
		dialogues[3][0] = "Eh? You still want to talk to me?";
		dialogues[3][1] = "Umm... I saw a mysterious chest to the South-\nWest. I bet it might contain something free.";
		
		dialogues[4][0] = "Sorry, but I am quite tired now. Especially being\na bit old *cough*.";
		dialogues[4][1] = "You can't defeat me, i'm invincible... and also an\nnpc *sigh*.";
		
		dialogues[5][0] = "That's enough questions for now. Good luck on\nyou... young lad.";
		
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
		
		// Do this character specific stuff
		
		//oldman turns himself toward player and then begins dialogue
		facePlayer();
		startDialogue(this,dialogueSet);
		
		dialogueSet++;
		
		if(dialogues[dialogueSet][0] == null) {
			
			dialogueSet = 0;
			// dialogueSet--; repeats the last set of dialogue
		}
		
		// YOU CAN SET CONDITIONAL DIALOGUES NOW!
		
		
		//onPath = true; // Can now use pathfinding for NPCs when desired
		
	}
}
