package main;

import entity.Entity; //4:51 #56

public class EventHandler{

	GamePanel gp;
	EventRect eventRect[][][];
	Entity eventMaster;
	
	int previousEventX, previousEventY;
	boolean canTouchEvent = true;
	int tempMap, tempCol, tempRow;
	public boolean spawnerPressed = false;
	public boolean danielPressed;
	public boolean aldenPressed;
	public boolean joyPressed;
	public boolean estherPressed;
	
	public EventHandler(GamePanel gp) {
		this.gp = gp;
		
		eventMaster = new Entity(gp);
		
		eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
		
		int map = 0;
		int col = 0;
		int row = 0;
		while(map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow) {
			
			eventRect[map][col][row] = new EventRect();
			eventRect[map][col][row].x = 23;
			eventRect[map][col][row].y = 23;
			eventRect[map][col][row].width = 2;
			eventRect[map][col][row].height = 2;
			eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
			eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;
		
			col++;
			
			if(col == gp.maxWorldCol) {
				col = 0;
				row++;
				
				if(row == gp.maxWorldRow) {
					row = 0;
					map++;
				}
			}
		}
	}
	public void setDialogue() {
		
		eventMaster.dialogues[0][0] = "You got burned by the camp fire!";
		
		eventMaster.dialogues[1][0] = "You used the healing stone. Your life and mana has\nbeen recovered,"
				+ " but monsters might respawn."
				+ "\n(Your progress has been saved)";
	}
	public void checkEvent() {
		
		// Check if the player character is more than 1 tile away from the last event
		int xDistance = Math.abs(gp.player.worldX - previousEventX);
		int yDistance = Math.abs(gp.player.worldY - previousEventY);
		int distance = Math.max(xDistance, yDistance);
		if(distance > gp.tileSize) {
			canTouchEvent = true;
		}
		
		if(canTouchEvent == true) {
			
			//trader, thinking about adding a roof that disappears when you walk in, but maybe later
			//else if(hit(0,10,40, "up") == true) {teleportWithOutTrans(1,12,13);}
			//else if(hit(1,12,13, "any") == true) {teleportWithOutTrans(0,10,39);}
			if(gp.keyH.enterPressed == true && gp.player.getCol() == 11 &&
					gp.player.getRow() == 38 && gp.player.direction.equals("up")) {
				if(gp.currentMap == 0) {
					gp.player.attackCanceled = true;
					gp.npc[0][1].speak();
				}
			}
			if(gp.keyH.enterPressed == true && gp.player.getCol() == 86 &&
					gp.player.getRow() == 91 && gp.player.direction.equals("up")) {
				if(gp.currentMap == 1) {
					gp.player.attackCanceled = true;
					gp.npc[1][1].speak();
				}	
			}
			if(gp.keyH.enterPressed == true && gp.player.getCol() == 19 &&
					gp.player.getRow() == 63 && gp.player.direction.equals("up")) {
				if(gp.currentMap == 5) {
					gp.player.attackCanceled = true;
					gp.npc[5][0].speak();
				}	
			}
			if(gp.keyH.enterPressed == true && gp.player.getCol() == 81 &&
					gp.player.getRow() == 82 && gp.player.direction.equals("up")) {
				if(gp.currentMap == 6) {
					gp.player.attackCanceled = true;
					gp.npc[6][0].speak();
				}
			}
			
			// For dungeons
			else if(hit(0,12,9,"any") == true) {teleport(3,11,41,gp.dungeon);} // to dungeon
			else if(hit(3,11,41,"any") == true) {teleport(0,12,9,gp.outside);} // to outside
			else if(hit(3,10,7,"any") == true) {teleportWithOutTrans(4,26,41);} // to B2
			else if(hit(4,26,41,"any") == true) {teleportWithOutTrans(3,10,7);} // to B1
			
			// misty meadows
			else if(hit(1,34,93,"any") == true) {teleportWithOutTrans(1,11,89);}
			else if(hit(1,11,89,"any") == true) {teleportWithOutTrans(1,34,93);}
			else if(hit(1,41,7,"any") == true) {teleportWithOutTrans(1,76,49);}
			else if(hit(1,76,49,"any") == true) {teleportWithOutTrans(1,41,7);}
			
			else if(hit(5,50,47,"any") == true) {teleportWithOutTrans(5,50,42);} // to reward Island in final island
			else if(hit(5,50,42,"any") == true) {teleportWithOutTrans(5,50,47);} // to castle in final island
			
			else if(hit(6,13,37,"any") == true) {teleport(7,10,6,gp.outside);}
			else if(hit(7,10,6,"any") == true) {teleport(6,13,37,gp.outside);}
			
			else if(hit(7,10,7,"down") == true) {slimeBoss();}
			else if(hit(4,25,27,"up") == true) {skeletonLord();}
			else if(hit(2,25,42,"up") == true) {pirateBoss();}
			else if(hit(5,50,70,"up") == true) {samuraiBoss();}
				
			else if(danielPressed == false && hit(6,43,6,"right") == true) {daniel();}
			else if(aldenPressed == false && hit(0,23,18,"up") == true) {alden1();}
			else if(aldenPressed == false && hit(0,34,12,"left") == true) {alden2();}
			else if(joyPressed == false && hit(1,42,53,"left") == true) {joy();}
			else if(estherPressed == false && hit(5,19,85,"left") == true) {esther();}
			
			//else if(hit(1,12,9, "up") == true) {speak(gp.npc[1][0]);}
		}
		
	}
		
	public boolean hit(int map, int col, int row, String reqDirection) {
		
		boolean hit = false;
		
		if(map == gp.currentMap) {
			gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
			gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
			eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
			eventRect[map][col][row].y = row * gp.tileSize + eventRect[map][col][row].y;
			
			if(gp.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false) {
				if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
					hit = true;
					
					previousEventX = gp.player.worldX;
					previousEventY = gp.player.worldY;
				}
			}
			
			gp.player.solidArea.x = gp.player.solidAreaDefaultX;
			gp.player.solidArea.y = gp.player.solidAreaDefaultY;
			eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
			eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
		}
		
		return hit;
	}
	public void damagePit(int gameState) { // Called damagePit for now,
		//but this is supposed to be for the camp fire
		gp.gameState = gameState;
		gp.playSE(6);
		eventMaster.startDialogue(eventMaster, 0);
		gp.player.life -= 1;
//		eventRect[col][row].eventDone = true;
		canTouchEvent = false;
	}
	public void healingPool(int gameState) {
	
	if(gp.keyH.enterPressed == true) {
			gp.gameState = gameState;
			gp.player.attackCanceled = true;
			gp.playSE(2);
			eventMaster.startDialogue(eventMaster, 1);
			gp.player.life = gp.player.maxLife;
			gp.player.mana = gp.player.maxMana;
			gp.aSetter.setMonster();
			gp.saveload.save();
		}
	}
	public void teleport(int map, int col, int row, int area) {
		
		gp.gameState = gp.transitionState;
		gp.nextArea = area;
		
		//I dont really like this transition
		
		gp.nextMap = map;
		// gp.currentMap = map;
		tempMap = map;
		tempCol = col;
		tempRow = row;
		
		gp.player.worldX = gp.tileSize * col;
		gp.player.worldY = gp.tileSize * row;
		previousEventX = gp.player.worldX;
		previousEventY = gp.player.worldY;
		
		canTouchEvent = false;
		//gp.player.tpChecked = true;
		gp.playSE(39);  //WAY better Sound effect now!
	}
	public void teleportWithOutTrans(int map, int col, int row) {
		
		gp.currentMap = map;
		tempMap = map;
		tempCol = col;
		tempRow = row;
		
		gp.player.worldX = gp.tileSize * col;
		gp.player.worldY = gp.tileSize * row;
		previousEventX = gp.player.worldX;
		previousEventY = gp.player.worldY;
		
		canTouchEvent = false;
		gp.playSE(13); 
	}
	public void speak(Entity entity) {
		
		if(gp.keyH.enterPressed == true) {
			gp.gameState = gp.dialogueState;
			gp.player.attackCanceled = true;
			entity.speak();
		}
	}
	public void slimeBoss() {
		
		// can add && Progress.skeletonLordDefeated == false as another condition if u want to face bosses only once...
		if(gp.bossBattleOn == false) {
			gp.gameState = gp.cutsceneState;
			gp.csManager.sceneNum = gp.csManager.slimeBoss;
		}
	}
	public void skeletonLord() {
		
		// can add && Progress.skeletonLordDefeated == false as another condition if u want to face bosses only once...
		if(gp.bossBattleOn == false) {
			gp.gameState = gp.cutsceneState;
			gp.csManager.sceneNum = gp.csManager.skeletonBoss;
		}
	}
	public void pirateBoss() {
		
		// can add && Progress.skeletonLordDefeated == false as another condition if u want to face bosses only once...
		if(gp.bossBattleOn == false) {
			gp.gameState = gp.cutsceneState;
			gp.csManager.sceneNum = gp.csManager.pirateBoss;
		}
	}
	public void samuraiBoss() {
		
		// can add && Progress.skeletonLordDefeated == false as another condition if u want to face bosses only once...
		if(gp.bossBattleOn == false) {
			gp.gameState = gp.cutsceneState;
			gp.csManager.sceneNum = gp.csManager.samuraiBoss;
		}
	}
	public void daniel() {
		
		// can add && Progress.skeletonLordDefeated == false as another condition if u want to face bosses only once...
		gp.gameState = gp.cutsceneState;
		gp.csManager.sceneNum = gp.csManager.daniel;
	}
	public void alden1() {
		
		// can add && Progress.skeletonLordDefeated == false as another condition if u want to face bosses only once...
		gp.gameState = gp.cutsceneState;
		gp.csManager.sceneNum = gp.csManager.alden1;
	}
	public void alden2() {
		
		// can add && Progress.skeletonLordDefeated == false as another condition if u want to face bosses only once...
		gp.gameState = gp.cutsceneState;
		gp.csManager.sceneNum = gp.csManager.alden2;
	}
	public void joy() {
		
		// can add && Progress.skeletonLordDefeated == false as another condition if u want to face bosses only once...
		gp.gameState = gp.cutsceneState;
		gp.csManager.sceneNum = gp.csManager.joy;
	}
	public void esther() {
		
		// can add && Progress.skeletonLordDefeated == false as another condition if u want to face bosses only once...
		gp.gameState = gp.cutsceneState;
		gp.csManager.sceneNum = gp.csManager.esther;
	}
}
