package main;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import environment.Lighting;
import object.OBJ_Craftable_Anvil;
import object.OBJ_Craftable_Stone;
import object.OBJ_Craftable_Woodenplank;
import object.OBJ_Hero_Crafting_Table;


public class KeyHandler implements KeyListener{

	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, shotKeyPressed, shiftPressed, escapePressed;
	public int musicCounter = 0;
	
	public boolean tutorialOn = true;
	
	// DEBUG
	boolean showDebugText = false;
	
	
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {	
	}
	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		// TITLE STATE
		if(gp.gameState == gp.titleState) {
			titleState(code);
		}
		// CINEMA STATE
		else if(gp.gameState == gp.cinemaState) {
			cinemaState(code);
		}
		// CREDITS STATE
		else if(gp.gameState == gp.creditState) {
			creditState(code);
		}
		// GAMECOMPLETE STATE
		else if(gp.gameState == gp.gameCompleteState) {
			gameCompleteState(code);
		}
		// PLAY STATE
		else if(gp.gameState == gp.playState) {
			playState(code);
		}
		// PAUSE STATE
		else if(gp.gameState == gp.pauseState) {
			pauseState(code);
		}
		// DIALOGUE STATE
		else if(gp.gameState == gp.dialogueState || gp.gameState == gp.cutsceneState) {
			dialogueState(code);
		}
		// LEVEL UP STATE, I made this one!!!
		else if(gp.gameState == gp.levelUpState) {
			levelUpState(code);
		}
		// CHARACTER STATE (or inventory)
		else if (gp.gameState == gp.characterState) {
			characterState(code);
		}
		// ANVIL STATE
		else if (gp.gameState == gp.anvilState) {
			anvilState(code);
		}
		// CRAFTSMAN STATE
		else if (gp.gameState == gp.craftsmanState) {
			craftsmanState(code);
		}
		// CHOOSING STATE
		else if (gp.gameState == gp.choosingState) {
			choosingState(code);
		}
		// PICKING CLASS STATE
		else if (gp.gameState == gp.pickClassState) {
			pickClassState(code);
		}
		// OPTIONS STATE
		else if (gp.gameState == gp.optionsState) {
			optionsState(code);
		}
		// TRAVEL STATE
		else if (gp.gameState == gp.travelState) {
			travelState(code);
		}
		// GAME OVER STATE
		else if (gp.gameState == gp.gameOverState) {
			gameOverState(code);
		}
		// TRADE STATE
		else if (gp.gameState == gp.tradeState) {
			tradeState(code);
		}
		else if(gp.gameState == gp.mapState) {
			mapState(code);
		}
		else if(gp.gameState == gp.treeState) {
			treeState(code);
		}
		else if(gp.gameState == gp.questState) {
			questState(code);
		}
	}
	public void titleState(int code) {
		
		if(gp.ui.titleScreenState == 0) {
			
			if (code == KeyEvent.VK_W) {
				gp.ui.commandNum--;
				gp.playSE(9);
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = 3;
				}
			}
			if (code == KeyEvent.VK_S) {
				gp.ui.commandNum++;
				gp.playSE(9);
				if(gp.ui.commandNum > 3) {
					gp.ui.commandNum = 0;
				}
			}
			
			if(code == KeyEvent.VK_ENTER) {
				if(gp.ui.commandNum == 0) {
					gp.stopMusic(); /// WAS NOT COMMENTED BEFORE 9/6/23
					gp.player.setDefaultValues();
					//gp.eHandler.teleport(6, 22, 8, gp.outside);
					gp.saveload.save();
					gp.gameState = gp.cinemaState;
					//gp.playMusic(50); // PLAY NEW SONG!
				}
				if(gp.ui.commandNum == 1) {
					if(gp.haveLoadedGameBefore == true) {
						gp.keyH.tutorialOn = false;
						gp.stopMusic();
						gp.saveload.load();
						//gp.checkAllTheMusic();
						gp.gameState = gp.playState;
						gp.dayState = gp.day;
						gp.eHandler.teleport(6, 22, 8, gp.outside); // always goes back to house at beginning of a load... Fixes bugs, 
						// and feels more professional overall...
					}
					else {
						gp.gameState = gp.cinemaState;
					}
				}
				if(gp.ui.commandNum == 2) {
					gp.gameState = gp.creditState;
					
				}
				if(gp.ui.commandNum == 3) {
					//gp.gameState = gp.playState; // PLAY NEW SONG!
					System.exit(0);
				}
			}
			
		}
	}
	public void creditState(int code) {
		
		if(code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
	}
	public void playState(int code) {
		
		if(tutorialOn) {
			tutorialOn = false;
		}
		else { // NEED THIS CODE!
			if (code == KeyEvent.VK_W) {
				upPressed = true;
			}
			if (code == KeyEvent.VK_A) {
				leftPressed = true;
			}
			if (code == KeyEvent.VK_S) {
				downPressed = true;
			}
			if (code == KeyEvent.VK_D) {
				rightPressed = true;
			}
		}
		
		if (code == KeyEvent.VK_P) {
			gp.gameState = gp.pauseState;
		}
		if(gp.player.tableOn) {
			if (code == KeyEvent.VK_E) {
				gp.gameState = gp.craftsmanState;
			}
		}
		else if (code == KeyEvent.VK_E) {
			gp.gameState = gp.characterState;
		}
		if (code == KeyEvent.VK_Q) {
			gp.gameState = gp.questState;
		}
		
		// SKILL TREE
		if (code == KeyEvent.VK_T)  {
			gp.gameState = gp.treeState;
			if(gp.skillTreeOn == false) {
				gp.skillTreeOn = true;
			}
			else {
				gp.skillTreeOn = false;
			}
			
		}
		
		if (code == KeyEvent.VK_ENTER) {
			
			
			if(gp.player.tableOn) {
				
				// at maximum possible index for obj, place item
				gp.playSE(61);
				gp.player.attackCanceled = true;
				if(gp.obj[gp.currentMap][199] != null &&
						gp.obj[gp.currentMap][199].worldX != gp.tileSize*-10) {
					gp.player.inventory.add(gp.obj[gp.currentMap][199]);
				}
				gp.player.inventory.remove(gp.player.currentSpecial);
				gp.player.currentSpecial = null;
				
				
				switch(gp.player.direction) {
				case "right":gp.obj[gp.currentMap][199] = new OBJ_Hero_Crafting_Table(gp);
				gp.obj[gp.currentMap][199].worldX = gp.tileSize*gp.player.getCol()+gp.tileSize;
				gp.obj[gp.currentMap][199].worldY = gp.tileSize*gp.player.getRow(); break;
				case "left":gp.obj[gp.currentMap][199] = new OBJ_Hero_Crafting_Table(gp);
				gp.obj[gp.currentMap][199].worldX = gp.tileSize*gp.player.getCol()-gp.tileSize;
				gp.obj[gp.currentMap][199].worldY = gp.tileSize*gp.player.getRow(); break;
				case "up":gp.obj[gp.currentMap][199] = new OBJ_Hero_Crafting_Table(gp);
				gp.obj[gp.currentMap][199].worldX = gp.tileSize*gp.player.getCol();
				gp.obj[gp.currentMap][199].worldY = gp.tileSize*gp.player.getRow()-gp.tileSize; break;
				case "down":gp.obj[gp.currentMap][199] = new OBJ_Hero_Crafting_Table(gp);
				gp.obj[gp.currentMap][199].worldX = gp.tileSize*gp.player.getCol();
				gp.obj[gp.currentMap][199].worldY = gp.tileSize*gp.player.getRow()+gp.tileSize; break;
				}
				
				gp.player.tableOn = false;
				//System.out.println("high here");
			}
			if(gp.player.currentItem() != null) {
				if(gp.player.currentItem().sub_type == gp.player.sub_type_placeable &&
						gp.player.currentItem().isSelected) {
					
						if(gp.player.currentItem().name.equals("Anvil")) {
							
							gp.playSE(61);
							gp.player.attackCanceled = true;
							//gp.player.currentItem().isSelected = false;
							if(gp.obj[gp.currentMap][199] != null &&
									gp.obj[gp.currentMap][199].worldX != gp.tileSize*-10) {
								gp.player.inventory.add(gp.obj[gp.currentMap][199]);
							}
							gp.player.inventory.remove(gp.player.getCurrentItemSlot());
							
							switch(gp.player.direction) {
							case "right":gp.obj[gp.currentMap][199] = new OBJ_Craftable_Anvil(gp);
							gp.obj[gp.currentMap][199].worldX = gp.tileSize*gp.player.getCol()+gp.tileSize;
							gp.obj[gp.currentMap][199].worldY = gp.tileSize*gp.player.getRow(); break;
							case "left":gp.obj[gp.currentMap][199] = new OBJ_Craftable_Anvil(gp);
							gp.obj[gp.currentMap][199].worldX = gp.tileSize*gp.player.getCol()-gp.tileSize;
							gp.obj[gp.currentMap][199].worldY = gp.tileSize*gp.player.getRow(); break;
							case "up":gp.obj[gp.currentMap][199] = new OBJ_Craftable_Anvil(gp);
							gp.obj[gp.currentMap][199].worldX = gp.tileSize*gp.player.getCol();
							gp.obj[gp.currentMap][199].worldY = gp.tileSize*gp.player.getRow()-gp.tileSize; break;
							case "down":gp.obj[gp.currentMap][199] = new OBJ_Craftable_Anvil(gp);
							gp.obj[gp.currentMap][199].worldX = gp.tileSize*gp.player.getCol();
							gp.obj[gp.currentMap][199].worldY = gp.tileSize*gp.player.getRow()+gp.tileSize; break;
							}
							gp.player.tableOn = false;
							//System.out.println("here");
						}
						else if(gp.player.currentItem().name.equals("Wooden Plank")) {
							
							gp.playSE(61);
							gp.player.attackCanceled = true;
							//gp.player.currentItem().isSelected = false;
							if(gp.obj[gp.currentMap][199] != null &&
									gp.obj[gp.currentMap][199].worldX != gp.tileSize*-10) {
								gp.player.inventory.add(gp.obj[gp.currentMap][199]);
							}
							gp.player.inventory.remove(gp.player.getCurrentItemSlot());
							
							switch(gp.player.direction) {
							case "right":gp.obj[gp.currentMap][199] = new OBJ_Craftable_Woodenplank(gp);
							gp.obj[gp.currentMap][199].worldX = gp.tileSize*gp.player.getCol()+gp.tileSize;
							gp.obj[gp.currentMap][199].worldY = gp.tileSize*gp.player.getRow(); break;
							case "left":gp.obj[gp.currentMap][199] = new OBJ_Craftable_Woodenplank(gp);
							gp.obj[gp.currentMap][199].worldX = gp.tileSize*gp.player.getCol()-gp.tileSize;
							gp.obj[gp.currentMap][199].worldY = gp.tileSize*gp.player.getRow(); break;
							case "up":gp.obj[gp.currentMap][199] = new OBJ_Craftable_Woodenplank(gp);
							gp.obj[gp.currentMap][199].worldX = gp.tileSize*gp.player.getCol();
							gp.obj[gp.currentMap][199].worldY = gp.tileSize*gp.player.getRow()-gp.tileSize; break;
							case "down":gp.obj[gp.currentMap][199] = new OBJ_Craftable_Woodenplank(gp);
							gp.obj[gp.currentMap][199].worldX = gp.tileSize*gp.player.getCol();
							gp.obj[gp.currentMap][199].worldY = gp.tileSize*gp.player.getRow()+gp.tileSize; break;
							}
							gp.player.tableOn = false;
						}
				}
				else {
					enterPressed = true;
				}
			}

			else {
				enterPressed = true;
			}
		}
		if (code == KeyEvent.VK_F) {
			if(gp.player.shotAvailableCounter < 60) {
				gp.ui.addMessage("reloading!");
				gp.playSE(14);
			}
			shotKeyPressed = true;
		}
		if (code == KeyEvent.VK_ESCAPE) {
			gp.gameState =  gp.optionsState;
		}
		
		if (code == KeyEvent.VK_M) { // Disabled until unlocked (if Specialist)
			if(gp.player.currentSpecial != null) {
				if(gp.player.currentSpecial.name.equals("Map")) {
					gp.gameState = gp.mapState;
				}
			}
		}


//		if (code == KeyEvent.VK_X) {
//			if(gp.map.miniMapOn == false) {
//				gp.map.miniMapOn = true;
//			}
//			else {
//				gp.map.miniMapOn = false;
//			}
//		}
		if (code == KeyEvent.VK_SHIFT) {
			shiftPressed = true;
		}
		
		// DEBUG
		if (code == KeyEvent.VK_SLASH) {
			if(showDebugText == false) {
				showDebugText = true;
			}
			else if(showDebugText == true) {
				showDebugText = false;
			}
		}
//		if (code == KeyEvent.VK_PERIOD) {
//			switch(gp.currentMap) {
//			case 0: gp.tileM.loadMap("/maps/beginnersIsle.txt",0); break; // would need to change accordingly
//			//case 1: gp.tileM.loadMap("/maps/interior.txt",1); break;
//			}
//			
//		}
		
	}
	public void cinemaState(int code) {
		
		if(gp.haveLoadedGameBefore == false) {
			if(code == KeyEvent.VK_ESCAPE) {
				gp.gameState = gp.titleState;
			}
		}
		else {
			if(code == KeyEvent.VK_ESCAPE) {
				gp.eHandler.teleport(6, 22, 8, gp.outside); // will determine player's starting location!!! // 4, 25, 36 / 6,81,82
				//gp.ui.subState = 2;
				//gp.gameState = gp.optionsState;
				//gp.gameState = gp.playState;//testing...
				//gp.checkAllTheMusic();
				gp.ui.addMessage("Press ESCAPE for help!");
			}
		}
		
		if(code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
		
		int maxCommandNum = 0;
		
		switch(gp.ui.subState) {
		case 0: maxCommandNum = 1; break; // CONNECTED TO THE UI OPTIONS SCREEN!!
		case 1: maxCommandNum = 1; break;
		case 2: maxCommandNum = 1; break;
		case 3: maxCommandNum = 1; break;
		case 4: maxCommandNum = 1; break;
		case 5: maxCommandNum = 1; break;
		}
		
		if(code == KeyEvent.VK_W) {
			gp.ui.commandNum--;
			//gp.playSE(9);
			if(gp.ui.commandNum < 0) {
				gp.ui.commandNum = maxCommandNum;
			}
		}
		if(code == KeyEvent.VK_S) {
			gp.ui.commandNum++;
			//gp.playSE(9);
			if(gp.ui.commandNum > maxCommandNum) {
				gp.ui.commandNum = 0;
			}
		}
		
	}
	public void pauseState(int code) {
		
		if (code == KeyEvent.VK_P)  {
			gp.gameState = gp.playState;
			gp.checkAllTheMusic();
			musicCounter = 0;
		}
	}
	public void treeState(int code) {
		
		if (code == KeyEvent.VK_T)  {
			gp.gameState = gp.playState;
			gp.skillTreeOn = false;
		}
	}
	public void dialogueState(int code) {
//		if(gp.timer >= 1000000000 && code == KeyEvent.VK_ENTER) {
//				gp.gameState = gp.playState;
//		}
		if(code == KeyEvent.VK_ENTER) {
			enterPressed = true;
			//gp.gameState = gp.playState;
		}
	}
	public void levelUpState(int code) {
		if(gp.gameState == gp.levelUpState) { // So this way when u level up, You have to press shift to continue // edit: changed back to enter
			if(code == KeyEvent.VK_SHIFT) {
				gp.gameState = gp.playState;
			}
		}
	}
	public void characterState(int code) {
		
		//System.out.println("characterState");
		
		if(code == KeyEvent.VK_E) {
			gp.ui.subState = 0;
			gp.ui.commandNum = 0;
			gp.gameState = gp.playState;
		}
		if(code == KeyEvent.VK_ENTER) { // Trying to add boots inside of the game, but i'm too tired
			
			gp.player.selectItem();
			//System.out.println(gp.player.currentItem().name);
			//System.out.println(gp.player.currentItem().isSelected);
			
//			if(gp.player.currentItem().isSelected) {
//				System.out.println("place");
//			}
		}
		
		
		int maxCommandNum = 0;
		
		if(code == KeyEvent.VK_SHIFT) { // Tab to toggle through character screen pages
			shiftPressed = true;
			gp.ui.subState++;
			gp.playSE(9);
			if(gp.ui.subState > 3) { // must change this manually if u wanna add or remove character screens
				gp.ui.subState = 0;
			}
		}
		
		switch(gp.ui.subState) {
		case 0: maxCommandNum = 0; break; // CONNECTED TO THE UI OPTIONS SCREEN!!
		case 1: maxCommandNum = 0; break;
		case 2: maxCommandNum = 0; break;
		case 3: maxCommandNum = 0; break;
		}
		
		playerInventory(code);
	}
	
	public boolean[][] craftModeChecker = new boolean[3][3];
	int cModeX = 0;
	int cModeY = 0;
	
	public void craftsmanState(int code) {
		
		int maxCommandNum = 2; // # of craftable items!
		switch(gp.ui.subState) {
		case 0: maxCommandNum = 0; break; // CONNECTED TO THE UI OPTIONS SCREEN!!
		case 1: maxCommandNum = 2; break;
		case 2: maxCommandNum = 2; break;
		//default : //System.out.println("here");// when craftMode on, set gp.ui.subState to like 9 or something, so it can reach code down here...
		}
		
		if(code == KeyEvent.VK_E) {
			gp.gameState = gp.playState;
			// for resetting if craftOn was true... NICE, it works!
			if(gp.player.craftOn) {
				for(int i = 0; i < 3; i++) {
					for(int j = 0; j < 3; j++) {
						if(craftModeChecker[i][j]) {
							gp.player.currentAbility.amount++;
							craftModeChecker[i][j] = false;
						}
					}
				}
				gp.player.leftSide = false;
				gp.player.rightSide = true;
				gp.ui.slotXStart = gp.tileSize*12+20; // not final anymore
				gp.ui.slotYStart = gp.tileSize+20; // not final anymore
				gp.ui.playerSlotCol = 0;
				gp.ui.playerSlotRow = 0;
			}
			gp.player.craftOn = false;
			gp.player.rightSide = false;
			gp.player.leftSide = false;
			enterPressed = true;
			//gp.player.tableOn = false; // 12/21/2022 - trying to fix issue where player cant access Anvil
			//gp.ui.playerSlotCol = 0;
			//gp.ui.playerSlotRow = 0;
			gp.ui.subState = 0;
			gp.ui.commandNum = 0;
//			cModeX = 0;
//			cModeY = 0;
//			gp.player.currentAbility = null;
		}
		if(code == KeyEvent.VK_ENTER) { // Trying to add boots inside of the game, but i'm too tired //  && gp.player.leftSide == false
			enterPressed = true;
			//System.out.println("craftsmanState");
			if(gp.player.leftSide == false) {
				gp.player.selectItem(); // for the player Inventory...
			}
		}
		if(!gp.player.craftOn) { // we need WASD for crafting screen, so we can't have this on at the same time
			if(gp.player.leftSide == false) {
				if(code == KeyEvent.VK_W) {
					gp.ui.commandNum--;
					if(gp.ui.subState != 0) {
						gp.playSE(9);
					}
					if(gp.ui.commandNum < 0) {
						gp.ui.commandNum = maxCommandNum;
					}
				}
				if(code == KeyEvent.VK_S) {
					gp.ui.commandNum++;
					if(gp.ui.subState != 0) {
						gp.playSE(9);
					}
					if(gp.ui.commandNum > maxCommandNum) {
						gp.ui.commandNum = 0;
					}
				}
			}
		}
		if(code == KeyEvent.VK_SHIFT) { // Tab to toggle through character screen pages
			shiftPressed = true;
			if(gp.player.craftOn) { 
					if(gp.player.rightSide) {
						gp.player.leftSide = true;
						gp.player.rightSide = false;
						//System.out.println("left");
						//System.out.println(gp.ui.subState);
						gp.ui.slotXStart = 20; // not final anymore
						gp.ui.slotYStart = 20; // not final anymore
						gp.ui.playerSlotCol = 0;
						gp.ui.playerSlotRow = 0;
						cModeX = 0;
						cModeY = 0;
						gp.ui.subState = 4; // legit Crafting Screen (leftSide)
					}
					else {
						for(int i = 0; i < 3; i++) {
							for(int j = 0; j < 3; j++) {
								if(craftModeChecker[i][j]) {
									gp.player.currentAbility.amount++;
									craftModeChecker[i][j] = false;
								}
							}
						}
						gp.player.leftSide = false;
						gp.player.rightSide = true;
						gp.ui.slotXStart = gp.tileSize*12+20; // not final anymore
						gp.ui.slotYStart = gp.tileSize+20; // not final anymore
						gp.ui.playerSlotCol = 0;
						gp.ui.playerSlotRow = 0;
						//System.out.println("right");
						gp.ui.subState = 3; // back to right side of crafting screen
					}
			}
			else {
				gp.ui.subState++;
				gp.playSE(9);
				if(gp.ui.subState > 1) { // must change this manually if u wanna add or remove character screens
					gp.ui.subState = 0;
				}
			}
		}
		
//		if(gp.player.tableOn) { 
//			playerInventory(code);
//		}
//		
		if(gp.player.craftOn) {
			if(gp.player.rightSide) {
				playerInventory(code);
			}
			else if(gp.player.leftSide) {
				craftMode(code);
			}
		}
		
	}
	public void choosingState(int code) { // only for crafting HINTS...
		
		int maxCommandNum = 2; // Hold, Use, Nevermind options
		switch(gp.ui.subState) {
		case 0: maxCommandNum = 2; break;
		}
		
		if(code == KeyEvent.VK_S) {
			gp.ui.commandNum++;
			if(gp.ui.subState != 0) {
				gp.playSE(9);
			}
			if(gp.ui.commandNum > maxCommandNum) {
				gp.ui.commandNum = 0;
			}
		}
		if(code == KeyEvent.VK_SHIFT) { // Tab to toggle through character screen pages
			shiftPressed = true;
			gp.ui.subState++;
			gp.playSE(9);
			if(gp.ui.subState > 1) { // must change this manually if u wanna add or remove character screens
				gp.ui.subState = 0;
			}
		}
		
		
	}
	public void pickClassState(int code) {
		
		
		int maxCommandNum = 2;
		switch(gp.ui.subState) {
		case 0: maxCommandNum = 2; break;
		case 4: maxCommandNum = 0; break;
		case 5: maxCommandNum = 1; break;
		case 6: maxCommandNum = 0; break;
		}
		
		if(code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
		
		if(code == KeyEvent.VK_SHIFT) {
			shiftPressed = true;
		}
		
		if(code == KeyEvent.VK_W) {
			gp.ui.commandNum--;
			if(gp.ui.commandNum != 0) {
				gp.playSE(9);
			}
			if(gp.ui.commandNum < 0) {
				gp.ui.commandNum = maxCommandNum;
			}
		}
		if(code == KeyEvent.VK_S) {
			gp.ui.commandNum++;
			if(gp.ui.commandNum != 0) {
				gp.playSE(9);
			}
			if(gp.ui.commandNum > maxCommandNum) {
				gp.ui.commandNum = 0;
			}
		}
		
		
	}
	public void anvilState(int code) {
		
		int maxCommandNum = 2; // Hold, Use, Nevermind options
		switch(gp.ui.subState) {
		//case 0: maxCommandNum = 0; break;
		case 1: maxCommandNum = 2; break;
		case 2: maxCommandNum = 1; break;
		case 3: maxCommandNum = 2; break;
		}
		
		if(code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
		
		if(code == KeyEvent.VK_W) {
			gp.ui.commandNum--;
			if(gp.ui.subState != 0) {
				gp.playSE(9);
			}
			if(gp.ui.commandNum < 0) {
				gp.ui.commandNum = maxCommandNum;
			}
		}
		if(code == KeyEvent.VK_S) {
			gp.ui.commandNum++;
			if(gp.ui.subState != 0) {
				gp.playSE(9);
			}
			if(gp.ui.commandNum > maxCommandNum) {
				gp.ui.commandNum = 0;
			}
		}
	}
	public void statState(int code) {
		
		int maxCommandNum = 2; // Hold, Use, Nevermind options
		switch(gp.ui.subState) {
		//case 0: maxCommandNum = 0; break;
		case 0: maxCommandNum = 2; break;
		}
		
		if(code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
		
		if(code == KeyEvent.VK_W) {
			gp.ui.commandNum--;
			if(gp.ui.subState != 0) {
				gp.playSE(9);
			}
			if(gp.ui.commandNum < 0) {
				gp.ui.commandNum = maxCommandNum;
			}
		}
		if(code == KeyEvent.VK_S) {
			gp.ui.commandNum++;
			if(gp.ui.subState != 0) {
				gp.playSE(9);
			}
			if(gp.ui.commandNum > maxCommandNum) {
				gp.ui.commandNum = 0;
			}
		}
	}
	public void optionsState(int code) {
		
		if(code == KeyEvent.VK_ESCAPE) {
			escapePressed = true;
			gp.ui.commandNum = 0;
			gp.ui.subState = 0;
			
			gp.gameState = gp.playState;
		}
		if(code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
		
		int maxCommandNum = 0;
		switch(gp.ui.subState) {
		case 0: maxCommandNum = 6; break; // CONNECTED TO THE UI OPTIONS SCREEN!!
		case 3: maxCommandNum = 1; break;
		case 4: maxCommandNum = 1; break;
		}
		
		if(code == KeyEvent.VK_W) {
			gp.ui.commandNum--;
			gp.playSE(9);
			if(gp.ui.commandNum < 0) {
				gp.ui.commandNum = maxCommandNum;
			}
		}
		if(code == KeyEvent.VK_S) {
			gp.ui.commandNum++;
			gp.playSE(9);
			if(gp.ui.commandNum > maxCommandNum) {
				gp.ui.commandNum = 0;
			}
		}
		if(code == KeyEvent.VK_A) {
			if(gp.ui.subState == 0) {
				if(gp.ui.commandNum == 1 && gp.music.volumeScale > 0) {
					gp.music.volumeScale--;
					gp.music.checkVolume();
					gp.playSE(9);
				}
				if(gp.ui.commandNum == 2 && gp.se.volumeScale > 0) {
					gp.se.volumeScale--;
					gp.playSE(9);
				}
			}
		}
		if(code == KeyEvent.VK_D) {
			if(gp.ui.subState == 0) {
				if(gp.ui.commandNum == 1 && gp.music.volumeScale < 5) {
					gp.music.volumeScale++;
					gp.music.checkVolume();
					gp.playSE(9);
				}
				if(gp.ui.commandNum == 2 && gp.se.volumeScale < 5) {
					gp.se.volumeScale++;
					gp.playSE(9);
				}
			}
		}
	}
	public void travelState(int code) {
		
		if(code == KeyEvent.VK_ESCAPE) {
			gp.gameState = gp.playState;
		}
		if(code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
		
		int maxCommandNum = 0;
		
		switch(gp.ui.subState) {
		case 0: maxCommandNum = 4; break; // CONNECTED TO THE UI OPTIONS SCREEN!!
		case 1: maxCommandNum = 1; break;
		case 2: maxCommandNum = 1; break;
		case 3: maxCommandNum = 1; break;
		case 4: maxCommandNum = 1; break;
		case 5: maxCommandNum = 1; break;
		}
		
		if(code == KeyEvent.VK_W) {
			gp.ui.commandNum--;
			gp.playSE(9);
			if(gp.ui.commandNum < 0) {
				gp.ui.commandNum = maxCommandNum;
			}
		}
		if(code == KeyEvent.VK_S) {
			gp.ui.commandNum++;
			gp.playSE(9);
			if(gp.ui.commandNum > maxCommandNum) {
				gp.ui.commandNum = 0;
			}
		}
		
	}
	public void questState(int code) { // looking off travel state for now...
		
		if(code == KeyEvent.VK_Q) {
			gp.ui.commandNum = 0;
			gp.ui.subState = 0;
			gp.gameState = gp.playState;
		}
		if(code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
		
		int maxCommandNum = 0;
		
		switch(gp.ui.subState) {
		case 0: maxCommandNum = 4; break; // CONNECTED TO THE UI OPTIONS SCREEN!!, made 4 instead of 6, took out escort and delivery
		case 1: maxCommandNum = 0; break;
		case 2: maxCommandNum = 0; break;
		case 3: maxCommandNum = 0; break;
		case 4: maxCommandNum = 0; break;
		case 5: maxCommandNum = 0; break;
		}
		
		if(code == KeyEvent.VK_W) {
			gp.ui.commandNum--;
			gp.playSE(9);
			if(gp.ui.commandNum < 0) {
				gp.ui.commandNum = maxCommandNum;
			}
		}
		if(code == KeyEvent.VK_S) {
			gp.ui.commandNum++;
			gp.playSE(9);
			if(gp.ui.commandNum > maxCommandNum) {
				gp.ui.commandNum = 0;
			}
		}
		
	}
	public void gameOverState(int code) {
		
		if(code == KeyEvent.VK_W) {
			gp.ui.commandNum--;
			if(gp.ui.commandNum < 0) {
				gp.ui.commandNum = 1;
			}
			gp.playSE(9);
		}
		if(code == KeyEvent.VK_S) {
			gp.ui.commandNum++;
			if(gp.ui.commandNum > 1) {
				gp.ui.commandNum = 0;
			}
			gp.playSE(9);
		}
		if(code == KeyEvent.VK_ENTER) {
			if(gp.ui.commandNum == 0) {
				gp.saveload.save();
				gp.gameState = gp.playState; // put this line after gp.checkAllTheMusic to demonstrate lag...
				gp.setRespawn();
				gp.resetGame(false);
				//gp.checkAllTheMusic();
				
				
			}
			else if(gp.ui.commandNum == 1) {
				gp.gameState = gp.titleState;
				gp.resetGame(true);
				gp.playMusic(44);
			}
		}
	}
	public void tradeState(int code) {
		
		if(code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
		if(gp.ui.subState == 0) {
			if(code == KeyEvent.VK_W) {
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = 2;
				}
				gp.playSE(9);
			}
			if(code == KeyEvent.VK_S) {
				gp.ui.commandNum++;
				if(gp.ui.commandNum > 2) {
					gp.ui.commandNum = 0;
				}
				gp.playSE(9);
			}
		}
		if(gp.ui.subState == 1) {
			npcInventory(code);
			if(code == KeyEvent.VK_ESCAPE) {
				gp.ui.subState = 0;
			}
		}
		if(gp.ui.subState == 2) {
			playerInventory(code);
			if(code == KeyEvent.VK_ESCAPE) {
				gp.ui.subState = 0;
			}
		}
	}
	public void craftMode(int code) {
		
		//make a 3x3 grid to craft
		
		if(code == KeyEvent.VK_ENTER) {
			//System.out.println("craftMode"); // working!!
			enterPressed = true;
			if(gp.player.currentAbility != null) {
				
				String name = gp.player.currentAbility.name;
				if(gp.player.currentAbility.amount > 0) {
					if(craftModeChecker[cModeX][cModeY]) {
						craftModeChecker[cModeX][cModeY] = false;
						//System.out.println(gp.player.currentAbility.amount);
						gp.player.currentAbility.amount++;
						//System.out.println(gp.player.currentAbility.amount);
					}
					else {
						craftModeChecker[cModeX][cModeY] = true;
						gp.player.currentAbility.amount--;
					}
					
				}
				else {
					if(craftModeChecker[cModeX][cModeY]) {
						craftModeChecker[cModeX][cModeY] = false;
						gp.player.currentAbility.amount++;
//						if(name.equals("Stone")) {
//							//gp.player.inventory.add(new OBJ_Craftable_Stone(gp));
//							gp.player.currentAbility = new OBJ_Craftable_Stone(gp);
//						}
//						if(name.equals("Wood")) {
//							gp.player.currentAbility = new OBJ_Craftable_Stone(gp);
//						}
					}
				}
			}
		}
		if(code == KeyEvent.VK_C) { 
			//System.out.println("craftModeCPressed"); // working!!
			
			boolean anvilChecker = true;
			
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					if(craftModeChecker[i][j] && gp.player.currentAbility.name.equals("Stone")) {
						
					}
					else {
						anvilChecker = false;
					}
				}
			}
			if(anvilChecker) {
				for(int i = 0; i < 3; i++) {
					for(int j = 0; j < 3; j++) {
						craftModeChecker[i][j] = false;
					}
				}
				gp.player.inventory.add(new OBJ_Craftable_Anvil(gp));
				gp.playSE(62);
			}
			for(int i = 0; i < gp.player.inventory.size();i++) {
				if(gp.player.inventory.get(i).name.equals("Stone")) {
					if(gp.player.inventory.get(i).amount < 1) {
						gp.player.inventory.remove(i); // NICE!!! removes stone when all has been used
					}
				}
			}
			//------------------------------------------------------------------------------------------
			boolean woodenPlankChecker = true;
			
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					if(craftModeChecker[i][j] && gp.player.currentAbility.name.equals("Wood")) {
						
					}
					else {
						woodenPlankChecker = false;
					}
				}
			}
			if(woodenPlankChecker) {
				for(int i = 0; i < 3; i++) {
					for(int j = 0; j < 3; j++) {
						craftModeChecker[i][j] = false;
					}
				}
				gp.player.inventory.add(new OBJ_Craftable_Woodenplank(gp));
				gp.playSE(62);
			}
			for(int i = 0; i < gp.player.inventory.size();i++) {
				if(gp.player.inventory.get(i).name.equals("Wood")) {
					if(gp.player.inventory.get(i).amount < 1) {
						gp.player.inventory.remove(i); // NICE!!! removes stone when all has been used
					}
				}
			}
			
		}
		if(code == KeyEvent.VK_W) {
			if(gp.ui.playerSlotRow != 0) {
				
				if(cModeY != 0) {
					cModeY--;
//					System.out.println("cModeX: "+cModeX);
//					System.out.println("cModeY: "+cModeY);
				}
				
				gp.ui.playerSlotRow-=48;
				//System.out.println("W");
				gp.playSE(9);
			}
		}
		if(code == KeyEvent.VK_A) {
			if(gp.ui.playerSlotCol != 0) {
				
				if(cModeX != 0) {
					cModeX--;
//					System.out.println("cModeX: "+cModeX);
//					System.out.println("cModeY: "+cModeY);
				}
				
				gp.ui.playerSlotCol-=48;
				//System.out.println("A");
				gp.playSE(9);
			}
		}
		if(code == KeyEvent.VK_S) {
			if(gp.ui.playerSlotRow != gp.tileSize*2) {
				
				if(cModeY != 3) {
					cModeY++;
//					System.out.println("cModeX: "+cModeX);
//					System.out.println("cModeY: "+cModeY);
				}
				
				gp.ui.playerSlotRow+=48;
				//System.out.println("S");
				gp.playSE(9);
			}
		}
		if(code == KeyEvent.VK_D) {
			if(gp.ui.playerSlotCol != gp.tileSize*2) {
				
				if(cModeX != 3) {
					cModeX++;
//					System.out.println("cModeX: "+cModeX);
//					System.out.println("cModeY: "+cModeY);
				}
				
				gp.ui.playerSlotCol+=48;
				//System.out.println("D");
				gp.playSE(9);
			}
		}
	}
	public void mapState(int code) {
		
		if(code == KeyEvent.VK_M) {
			gp.gameState = gp.playState;
		}
	}
	public void playerInventory(int code) {
		
		
		
		if(code == KeyEvent.VK_W) {
			if(gp.ui.playerSlotRow != 0) {
				gp.ui.playerSlotRow--;
				gp.playSE(9);
			}
		}
		if(code == KeyEvent.VK_A) {
			if(gp.ui.playerSlotCol != 0) {
				gp.ui.playerSlotCol--;
				gp.playSE(9);
			}
		}
		if(code == KeyEvent.VK_S) {
			if(gp.ui.playerSlotRow != 3) {
				gp.ui.playerSlotRow++;
				gp.playSE(9);
			}
		}
		if(code == KeyEvent.VK_D) {
			if(gp.ui.playerSlotCol != 4) {
				gp.ui.playerSlotCol++;
				gp.playSE(9);
			}
		}
		
	}
	public void npcInventory(int code) {
		
		if(code == KeyEvent.VK_W) {
			if(gp.ui.npcSlotRow != 0) {
				gp.ui.npcSlotRow--;
				gp.playSE(9);
			}
		}
		if(code == KeyEvent.VK_A) {
			if(gp.ui.npcSlotCol != 0) {
				gp.ui.npcSlotCol--;
				gp.playSE(9);
			}
		}
		if(code == KeyEvent.VK_S) {
			if(gp.ui.npcSlotRow != 3) {
				gp.ui.npcSlotRow++;
				gp.playSE(9);
			}
		}
		if(code == KeyEvent.VK_D) {
			if(gp.ui.npcSlotCol != 4) {
				gp.ui.npcSlotCol++;
				gp.playSE(9);
			}
		}
	}
	public void gameCompleteState(int code) {
		
		if(code == KeyEvent.VK_SHIFT) {
			shiftPressed = true;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if (code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if (code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if (code == KeyEvent.VK_D) {
			rightPressed = false;
		}	
		if (code == KeyEvent.VK_F) {
			shotKeyPressed = false;
		}
		if (code == KeyEvent.VK_ENTER) {
			enterPressed = false;
		}
		if (code == KeyEvent.VK_SHIFT) {
			shiftPressed = false;
		}
	}
}
