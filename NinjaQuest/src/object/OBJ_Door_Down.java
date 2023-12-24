package object;

import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class OBJ_Door_Down extends Entity{

	public static final String objName = "Door Down";
	
	// NOTE: I DELETED Door Up, Left, and Right, because I won't use them anymore
	// in fact, once we have the roofed houses and the new 'teleport' inside of houses
	// pokemon-style, all doors will only be enterable from the front
	// (I might have to move the backyard of the house to the front as well)
	
	GamePanel gp;
	boolean open = false;
	
	public OBJ_Door_Down(GamePanel gp, boolean canEnter) {
		super(gp);
		this.gp = gp;
		
		name = objName;
		canEnterDoor = canEnter;
		type = type_obstacle;
		//type = type_door;
		name = objName;
		down1 = setup("/objects/door_down_close", gp.tileSize, gp.tileSize);
		collision = true;
		
		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width = 48;
		solidArea.height = 48;
//		interactableArea.width = 48;
//		interactableArea.height = 48;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		setDialogue();
	}
	public void setDialogue() {
		
		dialogues[0][0] = "You are not allowed to enter!";
		
		dialogues[1][0] = "Nobody's home, go away.";
		
		dialogues[2][0] = "Leave the deilvery by the door, thanks.";
		
		dialogues[3][0] = "This isn't your house bub.";
		
		dialogues[4][0] = "I ordered my pizza 6 hours ago, why are you so\nlate?";
		
		dialogues[5][0] = "Don't be sneaky now.";
		
		dialogues[6][0] = "Peeping Tom!";
		
		dialogues[7][0] = "Sorry, but you can't enter.";
		
		dialogues[8][0] = "Whatchu doin?";
		
		dialogues[9][0] = "Are you an imposter among us?";
		
		dialogues[10][0] = "You can only enter at night.";
		
		dialogues[11][0] = "You must defeat Samurai at least once.";
		
//		dialogues[12][0] = "The goal of the game is to get to the Final\nIsland and beat the final boss.";
//		dialogues[12][1] = "You must complete the quests, which ";
	}
	public void interact () {
		if(gp.currentMap == 5 && this.getCol() == 50 && this.getRow() == 71) {
			if(gp.dayState != gp.night) {
				startDialogue(this,10);
			}
			else {
				if(open == false) {
					down1 = setup("/objects/door_down_open", gp.tileSize, gp.tileSize);
					solidArea.width = 5;
					solidArea.height = 48;
					open = true;
				}
				else {
					down1 = setup("/objects/door_down_close", gp.tileSize, gp.tileSize);
					solidArea.width = 48;
					solidArea.height = 48;
					open = false;
				}
				gp.playSE(52);
			}
		}
		else if(gp.currentMap == 5 && this.getCol() == 50 && this.getRow() == 49) {
			if(gp.player.qH.bossesDefeated.get("Samurai") < 1) {
				startDialogue(this,11);
			}
			else {
				if(open == false) {
					down1 = setup("/objects/door_down_open", gp.tileSize, gp.tileSize);
					solidArea.width = 5;
					solidArea.height = 48;
					open = true;
				}
				else {
					down1 = setup("/objects/door_down_close", gp.tileSize, gp.tileSize);
					solidArea.width = 48;
					solidArea.height = 48;
					open = false;
				}
				gp.playSE(52);
			}
		}
		else {
			if(canEnterDoor == true) {
				if(open == false) {
					down1 = setup("/objects/door_down_open", gp.tileSize, gp.tileSize);
					solidArea.width = 5;
					solidArea.height = 48;
					open = true;
				}
				else {
					down1 = setup("/objects/door_down_close", gp.tileSize, gp.tileSize);
					solidArea.width = 48;
					solidArea.height = 48;
					open = false;
				}
				gp.playSE(52);
			}
			else {
				//System.out.println("here");
				Random random = new Random();
				int num = random.nextInt(10)+0; // should be 0 to 9
				
				if(num == 0) {
					startDialogue(this,0);
				}
				else if(num == 1) {
					startDialogue(this,1);
				}
				else if(num == 2) {
					startDialogue(this,2);
				}
				else if(num == 3) {
					startDialogue(this,3);
				}
				else if(num == 4) {
					startDialogue(this,4);
				}
				else if(num == 5) {
					startDialogue(this,5);
				}
				else if(num == 6) {
					startDialogue(this,6);
				}
				else if(num == 7) {
					startDialogue(this,7);
				}
				else if(num == 8) {
					startDialogue(this,8);
				}
				else if(num == 9) {
					startDialogue(this,9);
				}
			}
		}
	}
}
