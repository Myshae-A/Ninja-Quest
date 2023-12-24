package object;

import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sign extends Entity{

	public static final String objName = "Sign";
	GamePanel gp;
	
	public OBJ_Sign(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		name = objName;
		type = type_obstacle;
		//type = type_door;
		name = objName;
		down1 = setup("/objects/sign", gp.tileSize, gp.tileSize);
		collision = true;
		
		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width = 48;
		solidArea.height = 48;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		dialogueSet = -1;
		setDialogue();
	}
	public void setDialogue() {
		
		dialogues[0][0] = "The goal of this game is to get to the Final Island &\nbeat 'The Samurai'.";
		dialogues[0][1] = "You must finish the quests on each map to progress\nto the next. Press [Q] to check your quests. The\ndescription tells you what to do.";
		
		dialogues[1][0] = "If you need help, press [ESCAPE] & select 'Controls'\nby pressing [ENTER].";
		dialogues[1][1] = "To save, press [ESCAPE] & select 'Save Game' by\npressing [ENTER].";
		
		dialogues[2][0] = "Keys have a very low chance of dropping from\nenemies, but can also be bought at the Merchant's\nshop for 500 coins.";
		
		dialogues[3][0] = "To equip items, press [E] to open your inventory\n& move the cursor using [WASD] keys. You can use\nitems by pressing [ENTER].";
		
		dialogues[4][0] = "Find the shield & equip it in your inventory. You\ncan press [SHIFT] to guard from enemies. You\ncan also parry the Orc by guarding right before it\nattacks.";
		
		dialogues[5][0] = "There's an easy way to beat this boss, see if you\ncan figure it out...";
		
		dialogues[6][0] = "Axes can cut different shaded tress...";
		
		dialogues[7][0] = "You can use lanterns to see at night.";
		
		dialogues[8][0] = "Something about the trees...";
		
		dialogues[9][0] = "Everything saves when you die.";
		
		dialogues[10][0] = "You can 'critical hit' enemies if you attack them\nright after parrying! Kind of meant for more difficult\nenemies though, not slimes...";
		
		dialogues[11][0] = "The INFO option is describing what that class\n'Special Item' can do. These abilities will NOT unlock\nimmediately! You must first obtain the 'Special Item'!";
		dialogues[11][1] = "You get the 'Special Item' from deep in the Dungeon\nlocated in Beginner's Isle -- the next map!";
		
		dialogues[12][0] = "Leaving the dungeon resets the rocks.";
		
		dialogues[13][0] = "You can press [P] to pause. It does reset the music\nthough, so keep that in mind.";
		
		dialogues[14][0] = "You can press [T] to check your skill tree. But you\ncan't really do much on there, other than looking at\nwhat the other classes offer.";
		
		dialogues[15][0] = "You can only enter purple houses or your own\nhouse.";
		
		dialogues[16][0] = "This game might lag if you encounter hordes of\nenemies or characters.";
		dialogues[16][1] = "You may or may not have figured that out by now.";
		
		dialogues[17][0] = "You can't parry bosses.";
		
		dialogues[18][0] = "Try beating all the bosses again...";
		
		dialogues[19][0] = "Watch out for fireballs! They do about 8 hearts\nworth of damage.";
		
		dialogues[20][0] = "Have you gotten any rare drops yet? The Pink Ghost\nwas one. There's 2 more...";
		
		dialogues[21][0] = "If your weapon/item stops working and seems to be\nmalfunctioning, re-equip it.";
	}
	public void interact () {
		
		
		if(this.getCol() == 25 && this.getRow() == 8 && gp.currentMap == 6) {
			startDialogue(this,6);
		}
		else if(this.getCol() == 39 && this.getRow() == 12 && gp.currentMap == 6) {
			startDialogue(this,1);
		}
		else if(this.getCol() == 14 && this.getRow() == 34 && gp.currentMap == 6) {
			startDialogue(this,2);
		}
		else if(this.getCol() == 12 && this.getRow() == 7 && gp.currentMap == 6) {
			startDialogue(this,3);
		}
		else if(this.getCol() == 26 && this.getRow() == 21 && gp.currentMap == 6) {
			startDialogue(this,4);
		}
		else if(this.getCol() == 13 && this.getRow() == 6 && gp.currentMap == 7) {
			startDialogue(this,5);
		}
		else if(this.getCol() == 36 && this.getRow() == 43 && gp.currentMap == 7) {
			startDialogue(this,8);
		}
		else if(this.getCol() == 49 && this.getRow() == 18 && gp.currentMap == 6) {
			startDialogue(this,0);
		}
		else if(this.getCol() == 17 && this.getRow() == 16 && gp.currentMap == 6) {
			startDialogue(this,7);
		}
		else if(this.getCol() == 58 && this.getRow() == 81 && gp.currentMap == 6) {
			startDialogue(this,9);
		}
		else if(this.getCol() == 40 && this.getRow() == 36 && gp.currentMap == 6) {
			startDialogue(this,10);
		}
		else if(this.worldX == gp.tileSize*24+24 && this.worldY == gp.tileSize*22 && gp.currentMap == 7) {
			startDialogue(this,11);
		}
		else if(this.getCol() == 14 && this.getRow() == 24 && gp.currentMap == 3) {
			startDialogue(this,12);
		}
		else if(this.getCol() == 22 && this.getRow() == 18 && gp.currentMap == 0) {
			startDialogue(this,13);
		}
		else if(this.getCol() == 24 && this.getRow() == 18 && gp.currentMap == 0) {
			startDialogue(this,14);
		}
		else if(this.getCol() == 71 && this.getRow() == 27 && gp.currentMap == 6) {
			startDialogue(this,15);
		}
		else if(this.getCol() == 28 && this.getRow() == 39 && gp.currentMap == 0) {
			startDialogue(this,16);
		}
		else if(this.getCol() == 27 && this.getRow() == 39 && gp.currentMap == 4) {
			startDialogue(this,17);
		}
		else if(this.getCol() == 50 && this.getRow() == 27 && gp.currentMap == 5) {
			startDialogue(this,18);
		}
		else if(this.getCol() == 14 && this.getRow() == 10 && gp.currentMap == 2) {
			startDialogue(this,19);
		}
		else if(this.getCol() == 53 && this.getRow() == 46 && gp.currentMap == 1) {
			startDialogue(this,20);
		}
		else if(this.getCol() == 14 && this.getRow() == 25 && gp.currentMap == 6) {
			startDialogue(this,21);
		}
		
	}
}
