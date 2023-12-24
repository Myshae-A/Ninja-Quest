package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.RenderingHints;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import entity.Entity;
import entity.PlayerDummy;
import object.OBJ_Coin_Bronze;
import object.OBJ_Coin_Gold;
import object.OBJ_Craftable_Anvil;
import object.OBJ_Craftable_Stone;
import object.OBJ_Craftable_Wood;
import object.OBJ_Craftable_Woodenplank;
import object.OBJ_Door_Down;
import object.OBJ_Door_Iron;
import object.OBJ_Emblem_Craftsman;
import object.OBJ_Emblem_Guardian;
import object.OBJ_Emblem_Ranger;
import object.OBJ_Emblem_Specialist;
import object.OBJ_Emblem_Warrior;
import object.OBJ_Heart;
import object.OBJ_Hero_Crafting_Table;
import object.OBJ_Hero_Guardian_Field;
import object.OBJ_Hero_Marksman_Bow;
import object.OBJ_Hero_Special_Cloak;
import object.OBJ_Hero_Warrior_Sword;
import object.OBJ_ManaCrystal;
import object.OBJ_Monster_Spawner;
import object.OBJ_Placeholder;
import object.OBJ_Placeholder_Selected;
import object.OBJ_Stat_Ammo;
import object.OBJ_Stat_Attack;
import object.OBJ_Stat_Defense;
import object.OBJ_Stat_HP;
import object.OBJ_Sword_Normal;

public class UI {

	GamePanel gp;
	QuestHandler qH;
	Graphics2D g2;
	public Font maruMonica, purisaB;
	
	// BUFFERED IMAGES ARE RIGHT HERE!!! I KNOW ITS A DROPDOWN, SO ITS HARD TO SEE!!!
	BufferedImage heart_full, heart_half, heart_blank, crystal_full, crystal_blank, coin, placeholder,
	ranger, guardian, specialist, warrior,
	rangerBadge, guardianBadge, specialistBadge, warriorBadge,
	placeholderSelected, swordN,
	marksmanBow, guardianField, specialCloak, warriorSword,
	stoneBlock, woodBlock, anvilBlock, woodenPlank,
	statAmmo, statAttack, statDefense, statHp;

	public boolean messageOn = false;
//	public String message = "";
//	int messageCounter = 0;
	ArrayList<String> message = new ArrayList<>();
	ArrayList<Integer> messageCounter = new ArrayList<>();
	public boolean gameFinished = false;
	public String currentDialogue = "";
	public int commandNum = 0;
	public int titleScreenState = 0; // 0: the first screen, 1: the second screen, 2: and so on
	public int playerSlotCol = 0;
	public int playerSlotRow = 0;
	public int npcSlotCol = 0;
	public int npcSlotRow = 0;
	public int subState = 0;
	int counter = 0;
	public Entity npc;
	int charIndex = 0;
	String combinedText = "";
	
	//public boolean inventoryBug = false;
	public boolean yesSelected = false;
	public String classPicked = "";
	
	// FOR QUESTS
	public boolean levelQuestComplete = false;
	public boolean bossQuestComplete = false;
	public boolean collectorQuestComplete = false;
	public boolean enemyQuestComplete = false;
	public boolean escortQuestComplete = false;
	public boolean deliveryQuestComplete = false;
	
	public Map<Integer,Boolean> questItemSaves; 
	
	public int slotXStart = 48*12 + 20; // not final anymore, also gp.tileSize replaced with 48
	public int slotYStart = 48 + 20; // not final anymore, also gp.tileSize replaced with 48

	
	public UI(GamePanel gp, QuestHandler qH) {
		
		this.gp = gp;
		this.qH = qH;
		
		try {
			InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
			maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
			is = getClass().getResourceAsStream("/font/Purisa Bold.ttf");
			purisaB = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		questItemSaves = new HashMap<Integer,Boolean>(){{
			put(0,false);
			put(1,false);
			put(2,false);
			put(3,false);
			put(4,false);
			put(5,false);
			put(6,false);
		}};
		
		// CREATE HUD OBJECT
		Entity heart = new OBJ_Heart(gp);
		heart_full = heart.image;
		heart_half = heart.image2;
		heart_blank = heart.image3;
		Entity crystal = new OBJ_ManaCrystal(gp);
		crystal_full = crystal.image;
		crystal_blank = crystal.image2;
		Entity bronzeCoin = new OBJ_Coin_Gold(gp); // changed from bronze to gold color...
		coin = bronzeCoin.down1;
		
		// SWORD FOR MAIN SCREEN IMAGE (CAN CHANGE THIS TO WHATEVER DESIRED)...
		Entity swordNormal = new PlayerDummy(gp);
		swordN = swordNormal.image;
		
		// CREATE CLASS IMAGES FOR SKILL TREE
		Entity ranger1 = new OBJ_Emblem_Ranger(gp);
		ranger = ranger1.down1;
		rangerBadge = ranger1.image;
		Entity guardian1 = new OBJ_Emblem_Guardian(gp);
		guardian = guardian1.down1;
		guardianBadge = guardian1.image;
		Entity specialist1 = new OBJ_Emblem_Specialist(gp);
		specialist = specialist1.down1;
		specialistBadge = specialist1.image;
		Entity warrrior1 = new OBJ_Emblem_Warrior(gp);
		warrior = warrrior1.down1;
		warriorBadge = warrrior1.image;
		
		Entity statAmmo1 = new OBJ_Stat_Ammo(gp);
		statAmmo = statAmmo1.down1;
		Entity statAttack1 = new OBJ_Stat_Attack(gp);
		statAttack = statAttack1.down1;
		Entity statDefense1 = new OBJ_Stat_Defense(gp);
		statDefense = statDefense1.down1;
		Entity statHp1 = new OBJ_Stat_HP(gp);
		statHp = statHp1.down1;
		
		Entity marksmanBow1 = new OBJ_Hero_Marksman_Bow(gp);
		marksmanBow = marksmanBow1.image;
		Entity guardianField1 = new OBJ_Hero_Guardian_Field(gp);
		guardianField = guardianField1.down1;
		Entity specialCloak1 = new OBJ_Hero_Special_Cloak(gp);
		specialCloak = specialCloak1.down1;
		Entity warriorSword1 = new OBJ_Hero_Warrior_Sword(gp);
		warriorSword = warriorSword1.down1;
		
		Entity skillTreePlaceholder = new OBJ_Placeholder(gp);
		placeholder = skillTreePlaceholder.down1;
		
		Entity skillTreePlaceholderSelected = new OBJ_Placeholder_Selected(gp);
		placeholderSelected = skillTreePlaceholderSelected.down1;

		Entity woodBlock1 = new OBJ_Craftable_Wood(gp);
		woodBlock = woodBlock1.down1;
		Entity stoneBlock1 = new OBJ_Craftable_Stone(gp);
		stoneBlock = stoneBlock1.down1;
		Entity anvilBlock1 = new OBJ_Craftable_Anvil(gp);
		anvilBlock = anvilBlock1.down1;
		Entity woodenPlank1 = new OBJ_Craftable_Woodenplank(gp);
		woodenPlank = woodenPlank1.down1;
		
	}
	public void addMessage(String text) {
		
		message.add(text);
		messageCounter.add(0);
		
	}
	public void draw(Graphics2D g2) {
				
		this.g2 = g2;
		
		g2.setFont(maruMonica);
//		g2.setFont(purisaB);
//		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setColor(Color.white);
		
		// TITLE STATE
		if(gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
		// CREDIT STATE
		if(gp.gameState == gp.creditState) {
			creditScreen();
		}
		// CINEMA STATE
		if(gp.gameState == gp.cinemaState) {
			drawCinemaScreen();		
		}
		if(gp.gameState == gp.gameCompleteState) {
			drawGameCompleteScreen();
		}
		// PLAY STATE
		if(gp.gameState == gp.playState) {
			drawPlayerLife();
			drawMonsterLife();
			drawMessage();
		}
		// PAUSE STATE
		if(gp.gameState == gp.pauseState) {
			drawPlayerLife();
			drawPauseScreen();
		}
		// TREE STATE
		if(gp.gameState == gp.treeState) {
			drawTreeScreen();
		}
		// DIALOGUE STATE
		if(gp.gameState == gp.dialogueState) {
			drawDialogueScreen();
		}
		// LEVEL UP STATE
		if(gp.gameState == gp.levelUpState) {
			drawDialogueScreen();
		}
		// CHARACTER STATE
		if(gp.gameState == gp.characterState) {
			drawCharacterScreen();
			drawInventory(gp.player,true);	
		}
		// CHARACTER STATE
		if(gp.gameState == gp.craftsmanState) {
			drawCraftsmanScreen();
			if(gp.ui.subState != 2) {
				drawInventory(gp.player,true);	
			}
		}
		// ANVIL STATE
		if(gp.gameState == gp.anvilState) {
			drawAnvilScreen();
		}
		// ANVIL STATE
		if(gp.gameState == gp.pickClassState) {
			drawPickClasscreen();
		}
		// OPTIONS STATE
		if(gp.gameState == gp.optionsState) {
			drawOptionsScreen();
		}
		// GAME OVER STATE
		if(gp.gameState == gp.gameOverState) {
			drawGameOverScreen();
		}
		// TRANSITION STATE
		if(gp.gameState == gp.transitionState) {
			if(gp.eHandler.spawnerPressed) {
				drawSpawnerTransition();
//				gp.eHandler.spawnerPressed = false;
			} else {
				drawTransition();
			}
		}
		// TRADE STATE
		if(gp.gameState == gp.tradeState) {
			npc.dialogueSet = 0;
			drawTradeScreen();
		}
		// TRADE STATE
		if(gp.gameState == gp.sleepState) {
			drawSleepScreen();
		}
		// TRAVEL STATE
		if(gp.gameState == gp.travelState) {
			drawTravelScreen();
		}
		// QUEST STATE
		if(gp.gameState == gp.questState) {
			drawQuestScreen();
		}
	}
	public void drawPlayerLife() {
		//return;
		int x = gp.tileSize/2;
		int y = gp.tileSize/2;
		int i = 0;
		int iconSize = 32;
		
		// DRAW MAX LIFE
		while(i < gp.player.maxLife/2) {
			g2.drawImage(heart_blank, x, y, iconSize, iconSize, null);
			
			i++;
			x += iconSize;
			if(i % 10 == 0) {
				x = gp.tileSize/2;
				y += iconSize;
			}
		}
		
		// RESET
		x = gp.tileSize/2;
		y = gp.tileSize/2;
		i = 0;
		// DRAW CURRENT Half-LIFE
		if(gp.player.life % 2 == 1) {
			while(i < (gp.player.life/2)+1) {
				g2.drawImage(heart_half, x, y, iconSize, iconSize, null);
				i++;
				x += iconSize;
				
				if(i % 10 == 0) {
					x = gp.tileSize/2;
					y += iconSize;
				}
			}
		}
		else {
			while(i < gp.player.life/2) {
				g2.drawImage(heart_half, x, y, iconSize, iconSize, null);
				i++;
				x += iconSize;
				
				if(i % 10 == 0) {
					x = gp.tileSize/2;
					y += iconSize;
				}
			}
		}
		
		// RESET
		x = gp.tileSize/2;
		y = gp.tileSize/2;
		i = 0;
		// DRAW CURRENT LIFE
		while(i < gp.player.life/2) {
			g2.drawImage(heart_full, x, y, iconSize, iconSize, null);
			i++;
			x += iconSize;
			if(i % 10 == 0) {
				x = gp.tileSize/2;
				y += iconSize;
			}
		}
		
		
		// DRAW MAX MANA
		x = (gp.tileSize/2)-5;
		if(gp.player.maxLife > 20) {
			y = iconSize*2+23;
		}
		else {
			y = iconSize+23;
		}
		i = 0;
		while(i < gp.player.maxMana) {
			g2.drawImage(crystal_blank,x,y, iconSize, iconSize, null);
			i++;
			x += iconSize-11;
		}
		
		// DRAW MANA
		x = (gp.tileSize/2)-5;
		if(gp.player.maxLife > 20) {
			y = iconSize*2+23;
		}
		else {
			y = iconSize+23;
		}
		i = 0;
		while(i < gp.player.mana) {
			g2.drawImage(crystal_full,x,y, iconSize, iconSize, null);
			i++;
			x += iconSize-11;
		}
		
		// DRAW CLASS BADGES
		if(gp.player.currentClass.equals("ranger")) {
			g2.drawImage(rangerBadge,gp.tileSize*17+24,24,null);
		}
		if(gp.player.currentClass.equals("guardian")) {
			g2.drawImage(guardianBadge,gp.tileSize*17+24,24,null);
		}
		if(gp.player.currentClass.equals("specialist")) {
			g2.drawImage(specialistBadge,gp.tileSize*17+24,24,null);
		}
		if(gp.player.currentClass.equals("warrior")) {
			g2.drawImage(warriorBadge,gp.tileSize*17+24,24,null);
		}
			
	}
	public void drawMonsterLife() {
		
		for(int i = 0; i < gp.monster[1].length; i++) {
			
			Entity monster = gp.monster[gp.currentMap][i];
			
			if(monster != null && monster.inCamera()) {
				
				// Monster HP bar
				if((monster.hpBarOn == true) && (monster.sub_type != monster.sub_type_boss)) {
					
					double oneScale = (double)gp.tileSize/monster.maxLife;
					double hpBarValue = oneScale*monster.life;
					
					g2.setColor(new Color(35,35,35));
					g2.fillRect(monster.getScreenX()-1, monster.getScreenY() - 6, gp.tileSize + 2, 12);
					
					g2.setColor(new Color(255, 0, 30));
					g2.fillRect(monster.getScreenX(), monster.getScreenY() - 5, (int)hpBarValue, 10);
					
					monster.hpBarCounter++;
					
					if(monster.hpBarCounter > 600) {
						monster.hpBarCounter = 0;
						monster.hpBarOn = false;
					}
				}
				else if(monster.sub_type == monster.sub_type_boss) {
					
//					double oneScale = (double)gp.tileSize*8/monster.maxLife;
//					double hpBarValue = oneScale*monster.life;
//					
//					int x = gp.screenWidth/2 - gp.tileSize*4;
//					int y = gp.tileSize*10;
//					
//					g2.setColor(new Color(35,35,35));
//					g2.fillRect(x-1, y-1, gp.tileSize*8 + 2, 22);
//					
//					g2.setColor(new Color(255, 0, 30));
//					g2.fillRect(x, y, (int)hpBarValue, 20);
//					
//					g2.setFont(g2.getFont().deriveFont(Font.BOLD,24f));
//					g2.setColor(Color.white);
//					g2.drawString(monster.name, x + 4, y - 10);
					
					
					double oneScale = (double)gp.tileSize/monster.maxLife;
					double hpBarValue = oneScale*monster.life;
					
					//System.out.println("normal if"); // Testing printed normal if, so it's running here
					g2.setColor(new Color(35,35,35));
					g2.fillRect(gp.tileSize-2, 11*gp.tileSize+10, 18*gp.tileSize + 4, 24);
					
					g2.setColor(new Color(255, 0, 30));
					g2.fillRect(gp.tileSize, 11*gp.tileSize+12, 18*(int)hpBarValue, 20);
					//System.out.println("Drawing Boss HP");
					
					// Okay, so the reason that the Boss HP Bar is disapearing has to do with,
					// player distance from the boss enemy
					if(monster.bossHalfHealth) {
						g2.setColor(new Color(35,35,35));
						g2.fillRect(gp.tileSize, 11*gp.tileSize+12, 18*gp.tileSize + 2, 22);
						
						g2.setColor(new Color(249, 166, 2)); // orange healthbar rgb
						g2.fillRect(gp.tileSize, 11*gp.tileSize+12, 18*(int)hpBarValue, 20);
					}
					if(monster.bossLastHealth) {
						g2.setColor(new Color(35,35,35));
						g2.fillRect(gp.tileSize, 11*gp.tileSize+12, 18*gp.tileSize + 2, 22);
						
						g2.setColor(new Color(160, 32, 240)); // purple healthbar rgb
						g2.fillRect(gp.tileSize, 11*gp.tileSize+12, 18*(int)hpBarValue, 20);
					}
					
					g2.setFont(g2.getFont().deriveFont(Font.BOLD,50f));
					g2.setColor(Color.white);
					g2.drawString(monster.name, gp.tileSize, 530);
				}
			}
		}
	}
	public void drawMessage() {
		
		int messageX = gp.tileSize;
		int messageY = gp.tileSize*4;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 24F));
		
		for(int i = 0; i < message.size(); i ++) {
			
			if(message.get(i) != null) {
				
				g2.setColor(Color.black);
				g2.drawString(message.get(i), messageX+2, messageY+2);
				g2.setColor(Color.white);
				g2.drawString(message.get(i), messageX, messageY);
				
				int counter = messageCounter.get(i) + 1; // messageCounter++
				messageCounter.set(i, counter); // set the counter to the array
				messageY += 50;
				
				if (messageCounter.get(i) > 300) {
					message.remove(i);
					messageCounter.remove(i);
				}
			}
		}
		
		
	}
	public void drawTitleScreen() {
		
		if(titleScreenState == 0) {
			
			g2.setColor(new Color(0,100,150));// 138,43,226 (purple), 218,165,32 (golden rod)
			g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
			
			//Graphics2D g2d = swordN.createGraphics();
			//g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
			g2.drawImage(swordN, 0, 0, gp.tileSize*20, gp.tileSize*12, null);
			
			// TITLE NAME
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
			String text = "Ninja Quest";
			int x = getXForCenteredText(text);
			int y = gp.tileSize*2+24;
			
			// SHADOW
			g2.setColor(Color.black);
			g2.drawString(text, x+5, y+gp.tileSize+5);
			
			// MAIN COLOR
			g2.setColor(Color.white);
			g2.drawString(text, x, y+gp.tileSize);
			
			// IMAGE
			//x = gp.screenWidth/2 - (gp.tileSize*2)/2;
			//y += gp.tileSize*2;
//			g2.drawImage(swordN, x-24, y-gp.tileSize, gp.tileSize*3, gp.tileSize*3, null);
			
					
			// MENU
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
			
			text = "NEW GAME";
			x = getXForCenteredText(text);
			y += gp.tileSize*3.5;
			g2.drawString(text, x, y);
			if(commandNum == 0) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
			text = "LOAD GAME";
			x = getXForCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if(commandNum == 1) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
			text = "CREDITS";
			x = getXForCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if(commandNum == 2) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
			text = "QUIT";
			x = getXForCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if(commandNum == 3) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
		    if(gp.randInt == 0) {
		    	text = "Press Enter!";
		    }
		    else if(gp.randInt == 1) {
		    	text = "Use WASD keys";
		    }
		    else if(gp.randInt == 2) {
		    	text = "No mouse needed";
		    }
		    else if(gp.randInt == 3) {
		    	text = "Start new game";
		    }
		    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
		    g2.drawString(text, 720, 550);
		    
		    // for tilting text...
//		    AffineTransform at = new AffineTransform();
//		    at.setToRotation(Math.PI / 4.0);
//		    g2.setTransform(at);
//		    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
//		    g2.drawString(text, 587, -550);
//		    at.setToRotation(0); // set angle rotation back to normal
//		    g2.setTransform(at);
		}
	}
	public void drawCinemaScreen() {
//		g2.setColor(new Color(0,100,150));// 138,43,226 (purple), 218,165,32 (golden rod)
//		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight/12);
//		g2.fillRect(gp.screenHeight-96, gp.screenHeight-96, gp.screenWidth, gp.screenHeight/12);
		
		//First, make a background to cover over the real worldif(commandNum == 0){
		if(gp.haveLoadedGameBefore == false) {
			g2.setColor(new Color(0,0,0));// 138,43,226 (purple), 218,165,32 (golden rod)
			g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
			
			// TITLE NAME
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 36F));
			String text = "You must start a new game first!";
			int x = getXForCenteredText(text);
			int y = gp.tileSize*5+24;
			
			// MAIN COLOR
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
			
			text = "Press [ESCAPE] to go back.";
			x = getXForCenteredText(text);
			g2.drawString(text, x, y+gp.tileSize);
			
			g2.setColor(Color.white);
			g2.setFont(g2.getFont().deriveFont(32F));
			
			gp.keyH.enterPressed = false;
		}
		else {
			g2.setColor(new Color(0,0,0));// 138,43,226 (purple), 218,165,32 (golden rod)
			g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
			
			// TITLE NAME
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 36F));
			String text = "Press [ESCAPE] to begin";
			int x = getXForCenteredText(text);
			int y = gp.tileSize*6;
			
			// MAIN COLOR
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
			
			g2.setColor(Color.white);
			g2.setFont(g2.getFont().deriveFont(32F));
			
			
			text = "Dedicated to RyiSnow...";
			x = getXForCenteredText(text);
			g2.setColor(Color.white);
			g2.drawString(text, x, y-gp.tileSize);
			
			g2.setColor(Color.white);
			g2.setFont(g2.getFont().deriveFont(32F));
			
			gp.keyH.enterPressed = false;
		}
	}
	public void creditScreen() {
		
		g2.setColor(new Color(0,0,0));// 138,43,226 (purple), 218,165,32 (golden rod)
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		switch(subState) {
		case 0: creditSlide1(); break;
		case 1: creditSlide2(); break;
		case 2: creditSlide3(); break;
		case 3: creditSlide4(); break;
		case 4: creditSlide5(); break;
		}
		
		gp.keyH.enterPressed = false;
	}
	public void drawPauseScreen() {
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
		
		String text = "PAUSED";
		int x = getXForCenteredText(text);
		int y = gp.screenHeight/2;
		
		g2.drawString(text, x, y);
		
	}
	public void drawTreeScreen() {
		
		// I will make the skill tree not interactable, but you can change how it looks
		// every time that you level up, because when you level up, you will get to
		// choose what stat to upgrade
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(48F));
		
		int frameX = 0;//gp.tileSize;
		int frameY = 0;//gp.tileSize;
		int frameWidth = gp.tileSize*20;
		int frameHeight = gp.tileSize*12;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		int textX;
		int textY;
		
		// TITLE
		String text = "Skill Tree";
		textX = getXForCenteredText(text);
		textY = frameY + gp.tileSize+12;
		g2.drawString(text, textX, textY);
		
		g2.setFont(g2.getFont().deriveFont(32F));
		
		textX = frameX + gp.tileSize+33;
		textY += gp.tileSize;
		g2.drawString("Ranger", textX, textY); textX += gp.tileSize*5-10;
		g2.drawString("Guardian", textX, textY); textX += gp.tileSize*4+42;
		g2.drawString("Specialist", textX, textY); textX += gp.tileSize*5+12;
		g2.drawString("Warrior", textX, textY);// textX += gp.tileSize*6;
		
		// After +5 in that category/class, you gain stat boost
		
		// after +10: Gain title of "Novice (class)", and becomes predominant class,
		// & U cannot boost other classes past lvl 10 until you mastered this one
		
		// +15: Minor ability. W-dash, G-no damage on 1st hit(or ignite/thorns), C-building, S-night vision (w/out lamp)
		// maybe, press R for minor ability
		
		
		// +20: Special ability (2 choices) can only be unlocked for predominant class, so other
		// maybe, press CTRL for special ability
		
		
		// +30: Special item (3 choices)Mastery of that class, so u can boost others or continue this class
		// classes can only go up to lvl 29
		
		// Maybe add an acheivement troughout these processes, and a special one
		// when the player maxes everything out...
		
		
		textX = frameX + gp.tileSize*2; // unlock Class Badge/Symbol, will be an indicator in top right corner
		textY += gp.tileSize;
		
		
		
		g2.drawImage(ranger, textX, textY, 48, 48, null); // draw in classes
		g2.drawImage(guardian, textX+= gp.tileSize*5, textY, 48, 48, null);
		g2.drawImage(specialist, textX+= gp.tileSize*5, textY, 48, 48, null);
		g2.drawImage(warrior, textX+= gp.tileSize*5, textY, 48, 48, null);
		
		
		g2.drawImage(placeholder, textX, textY, 48, 48, null); textX -= gp.tileSize*5;
		g2.drawImage(placeholder, textX, textY, 48, 48, null); textX -= gp.tileSize*5;
		g2.drawImage(placeholder, textX, textY, 48, 48, null); textX -= gp.tileSize*5;
		g2.drawImage(placeholder, textX, textY, 48, 48, null);
//		if(gp.player.craftsman == false && gp.player.guardian == false
//				&& gp.player.specialist == false && gp.player.warrior == false) {
//			
//		}
		if(gp.player.currentClass.equals("ranger")) {
			g2.drawImage(placeholderSelected, textX, textY, 48, 48, null);
		}
		else if(gp.player.currentClass.equals("guardian")) {
			g2.drawImage(placeholderSelected, textX += gp.tileSize*5, textY, 48, 48, null);
		}
		else if(gp.player.currentClass.equals("specialist")) {
			g2.drawImage(placeholderSelected, textX += gp.tileSize*10, textY, 48, 48, null);
		}
		else if(gp.player.currentClass.equals("warrior")) {
			g2.drawImage(placeholderSelected, textX += gp.tileSize*15, textY, 48, 48, null);
		}
		
		textX = frameX + gp.tileSize*2; // Unlock minor ability, one choice
		textY += gp.tileSize*2;
		g2.drawImage(marksmanBow, textX, textY, 48, 48, null); // draw in classes
		g2.drawImage(guardianField, textX+= gp.tileSize*5, textY, 48, 48, null);
		g2.drawImage(specialCloak, textX+= gp.tileSize*5, textY, 48, 48, null);
		g2.drawImage(warriorSword, textX+= gp.tileSize*5, textY, 48, 48, null);
		
		g2.drawImage(placeholder, textX, textY, 48, 48, null); textX -= gp.tileSize*5;
		g2.drawImage(placeholder, textX, textY, 48, 48, null); textX -= gp.tileSize*5;
		g2.drawImage(placeholder, textX, textY, 48, 48, null); textX -= gp.tileSize*5;
		g2.drawImage(placeholder, textX, textY, 48, 48, null); textX -= gp.tileSize*5;
		
		
		
		// EDIT LATER
		
		textX = frameX + gp.tileSize+16; // unlock special item, two choices
		textY += gp.tileSize*2;
		
		g2.drawImage(statDefense, textX, textY, 48, 48, null); textX += gp.tileSize;
		g2.drawImage(statAmmo, textX+15, textY, 48, 48, null); textX += gp.tileSize*4;
		
		g2.drawImage(statDefense, textX, textY, 48, 48, null); textX += gp.tileSize;
		g2.drawImage(statHp, textX+15, textY, 48, 48, null); textX += gp.tileSize*4;
		
		g2.drawImage(statAttack, textX, textY, 48, 48, null); textX += gp.tileSize;
		g2.drawImage(statAmmo, textX+15, textY, 48, 48, null); textX += gp.tileSize*4;
		
		g2.drawImage(statAttack, textX, textY, 48, 48, null); textX += gp.tileSize;
		g2.drawImage(statHp, textX+15, textY, 48, 48, null); //textX += gp.tileSize*4;
		
		g2.drawImage(placeholder, textX+15, textY, 48, 48, null); textX -= gp.tileSize;
		g2.drawImage(placeholder, textX, textY, 48, 48, null); textX -= gp.tileSize*4;
		
		g2.drawImage(placeholder, textX+15, textY, 48, 48, null); textX -= gp.tileSize;
		g2.drawImage(placeholder, textX, textY, 48, 48, null); textX -= gp.tileSize*4;
		
		g2.drawImage(placeholder, textX+15, textY, 48, 48, null); textX -= gp.tileSize;
		g2.drawImage(placeholder, textX, textY, 48, 48, null); textX -= gp.tileSize*4;
		
		g2.drawImage(placeholder, textX+15, textY, 48, 48, null); textX -= gp.tileSize;
		g2.drawImage(placeholder, textX, textY, 48, 48, null); textX -= gp.tileSize;
		
		
		
		textX = frameX + gp.tileSize-10; // unlock Special Move, three choices
		textY += gp.tileSize*2;
		
		g2.drawImage(statDefense, textX, textY, 48, 48, null); textX += gp.tileSize;
		g2.drawImage(statHp, textX+10, textY, 48, 48, null); textX += gp.tileSize;
		g2.drawImage(statAmmo, textX+20, textY, 48, 48, null); textX += gp.tileSize*3;
		
		g2.drawImage(statDefense, textX, textY, 48, 48, null); textX += gp.tileSize;
		g2.drawImage(statAmmo, textX+10, textY, 48, 48, null); textX += gp.tileSize;
		g2.drawImage(statHp, textX+20, textY, 48, 48, null); textX += gp.tileSize*3;
		
		g2.drawImage(statAttack, textX, textY, 48, 48, null); textX += gp.tileSize;
		g2.drawImage(statHp, textX+10, textY, 48, 48, null); textX += gp.tileSize;
		g2.drawImage(statAmmo, textX+20, textY, 48, 48, null); textX += gp.tileSize*3;
		
		g2.drawImage(statAttack, textX, textY, 48, 48, null); textX += gp.tileSize;
		g2.drawImage(statAmmo, textX+10, textY, 48, 48, null); textX += gp.tileSize;
		g2.drawImage(statHp, textX+20, textY, 48, 48, null); textX+=20;
		
		
		
		g2.drawImage(placeholder, textX, textY, 48, 48, null); textX -= gp.tileSize;
		g2.drawImage(placeholder, textX-10, textY, 48, 48, null); textX -= gp.tileSize;
		g2.drawImage(placeholder, textX-20, textY, 48, 48, null); textX -= gp.tileSize*3;
		
		g2.drawImage(placeholder, textX, textY, 48, 48, null); textX -= gp.tileSize;
		g2.drawImage(placeholder, textX-10, textY, 48, 48, null); textX -= gp.tileSize;
		g2.drawImage(placeholder, textX-20, textY, 48, 48, null); textX -= gp.tileSize*3;
		
		g2.drawImage(placeholder, textX, textY, 48, 48, null); textX -= gp.tileSize;
		g2.drawImage(placeholder, textX-10, textY, 48, 48, null); textX -= gp.tileSize;
		g2.drawImage(placeholder, textX-20, textY, 48, 48, null); textX -= gp.tileSize*3;
		
		g2.drawImage(placeholder, textX, textY, 48, 48, null); textX -= gp.tileSize;
		g2.drawImage(placeholder, textX-10, textY, 48, 48, null); textX -= gp.tileSize;
		g2.drawImage(placeholder, textX-20, textY, 48, 48, null); textX -= gp.tileSize;
		
		g2.drawString("Press [T] to exit", getXForCenteredText("Press [T] to exit"), textY+gp.tileSize*2);
	}
	public void drawStatScreen() { // unused...
		
		//System.out.println("herhibefijen");
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		// CREATE A FRAME
		int frameX = gp.tileSize*2;
		int frameY = gp.tileSize;
		int frameWidth = gp.tileSize*5;
		int frameHeight = gp.tileSize*10;
		//drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		switch(subState) {
		//case 0: statClicked(gp.tileSize*8, frameY, classPicked, gp.player.level); break;
		//case 1: statRUSure(gp.tileSize*8, frameY); break;
		//case 2: statUse(gp.tileSize*8, frameY); break;
		
		}
		
		gp.keyH.shiftPressed = false;
		gp.keyH.enterPressed = false;
	}
	public void drawDialogueScreen() {
		
		if(gp.gameState == gp.cutsceneState) {
			
			// DIALOGUE WINDOW
			int x = gp.tileSize*3;
			int y = gp.tileSize+12;
			int width = gp.screenWidth - (gp.tileSize*6);
			int height = gp.tileSize*4;
			drawSubWindow(x, y, width, height);
			
			// DRAWING NAME WINDOWS FOR NPCs!!!
			if(gp.ui.npc.type == npc.type_npc) {
				g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 48F));
				drawSubWindow(x+width-gp.tileSize*4+24, y+height, width/4, height/3);
				g2.drawString(gp.ui.npc.name,x-6+width-gp.tileSize*3, y+height+gp.tileSize);
			}
			
			g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
			x += gp.tileSize;
			y += gp.tileSize;
			
			if(npc.dialogues[npc.dialogueSet][npc.dialogueIndex] != null) {
				
				currentDialogue = npc.dialogues[npc.dialogueSet][npc.dialogueIndex]; // makes previous dialogue play all the way, then changes...
				// can restart this...
				
				char characters[] = currentDialogue.toCharArray(); // ...by disabling this and the loop
				
				if(charIndex < characters.length) {
					
					gp.playSE(32); // SOUND EFFECT FOR TEXT/TALKING!!
					String s = String.valueOf(characters[charIndex]);
					combinedText = combinedText+s;
					currentDialogue = combinedText; // in everyloop we add one character of the combinedText
					charIndex++;
				}
				else if(gp.keyH.enterPressed == true) { // else if makes it so that you can't skip dialogue
					
					charIndex = 0;
					combinedText = "";
					
					if(gp.gameState == gp.cutsceneState) {
						
						npc.dialogueIndex++;
						gp.keyH.enterPressed = false;
					}
				}
			}
			else { // if no text is in array
				npc.dialogueIndex = 0;
				
				gp.csManager.scenePhase++;
			}
			
			
			
			for(String line : currentDialogue.split("\n")) {
				g2.drawString(line, x, y);
				y += 40;
			}
		}
		else {
			
			// DIALOGUE WINDOW
			int x = gp.tileSize*3;
			int y = gp.tileSize/2;
			int width = gp.screenWidth - (gp.tileSize*6);
			int height = gp.tileSize*4;
			drawSubWindow(x, y, width, height);
			
			// DRAWING NAME WINDOWS FOR NPCs!!!
			if(gp.ui.npc.type == npc.type_npc) {
				g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 48F));
				drawSubWindow(x+width-gp.tileSize*4+24, y+height, width/4, height/3);
				g2.drawString(gp.ui.npc.name,x-6+width-gp.tileSize*3, y+height+gp.tileSize);
			}
			
			g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
			x += gp.tileSize;
			y += gp.tileSize;
			
			if(npc.dialogues[npc.dialogueSet][npc.dialogueIndex] != null) {
				
				currentDialogue = npc.dialogues[npc.dialogueSet][npc.dialogueIndex]; // makes previous dialogue play all the way, then changes...
				// can restart this...
				
				char characters[] = currentDialogue.toCharArray(); // ...by disabling this and the loop
				
				if(charIndex < characters.length) {
					
					//gp.playSE(32); // SOUND EFFECT FOR TEXT/TALKING!!
					String s = String.valueOf(characters[charIndex]);
					combinedText = combinedText+s;
					currentDialogue = combinedText; // in everyloop we add one character of the combinedText
					charIndex++;
				}
				else if(gp.keyH.enterPressed == true) { // else if makes it so that you can't skip dialogue
					
					charIndex = 0;
					combinedText = "";
					
					if(gp.gameState == gp.dialogueState) {
						
						npc.dialogueIndex++;
						gp.keyH.enterPressed = false;
					}
				}
			}
			else { // if no text is in array
				npc.dialogueIndex = 0;
				
				if(gp.gameState == gp.dialogueState) {
					gp.gameState = gp.playState;
				}
			}
			
			
			
			for(String line : currentDialogue.split("\n")) {
				g2.drawString(line, x, y);
				y += 40;
			}
		}
		
	}
	public void drawCharacterScreen() {
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		// CREATE A FRAME
		int frameX = gp.tileSize*2;
		int frameY = gp.tileSize;
		int frameWidth = gp.tileSize*5;
		int frameHeight = gp.tileSize*10;
		//drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		switch(subState) {
		case 0: character_currentScreen(frameX, frameY); break; //currentItems screen, but i called it currentScreen for short
		case 1: character_statScreen(frameX, frameY); break;
		case 2: character_achievementScreen(frameX, frameY); break; // blank, say "No achievements" for now...
		case 3: character_extraScreen(frameX, frameY); break; // extra, in case I think of something new...
		
		}
		
		gp.keyH.shiftPressed = false;
		// took from here
	}
	public void drawCraftsmanScreen() {
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		// CREATE A FRAME
		int frameX = gp.tileSize*2;
		int frameY = gp.tileSize;
		int frameWidth = gp.tileSize*5;
		int frameHeight = gp.tileSize*10;
		//drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		switch(subState) {
		case 0: character_craftsmanCurrentScreen(frameX, frameY); break; //currentItems screen, but i called it currentScreen for short
		case 1: character_craftingTableScreen(frameX, frameY); break;
		case 2: character_craftingTableClicked(gp.tileSize*8, frameY); break;
		case 3: character_drawCraftScreen(frameX, frameX); break;
		case 4: character_legitCraftingScreen(frameX, frameY); break;
		
		}
		
		gp.keyH.shiftPressed = false;
		gp.keyH.enterPressed = false;
		// took from here
	}
	public void drawAnvilScreen() {
		
		npc.dialogueSet = 0;
		//drawDialogueScreen();
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		// CREATE A FRAME
		int frameX = gp.tileSize*2;
		int frameY = gp.tileSize;
		int frameWidth = gp.tileSize*5;
		int frameHeight = gp.tileSize*10;
		//drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		switch(subState) {
		//case 0: character_craftsmanCurrentScreen(frameX, frameY); break;
		case 1: character_anvilClicked(gp.tileSize*8, frameY); break;
		case 2: character_anvilUse(gp.tileSize*8, frameY); break;
		case 3: character_anvilUseLegit(gp.tileSize*8, frameY); break;
		
		}
		
		gp.keyH.shiftPressed = false;
		gp.keyH.enterPressed = false;
	}
	public void drawPickClasscreen() {
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		// CREATE A FRAME
		int frameX = gp.tileSize*6;
		int frameY = gp.tileSize;
		int frameWidth = gp.tileSize*8;
		int frameHeight = gp.tileSize*10;
		//drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		switch(subState) {
		//case 0: character_pickCraftsman(gp.tileSize*8, frameY); break;
		case 0: character_pickRanger(gp.tileSize*8, frameY); break;
		case 1: character_pickGuardian(gp.tileSize*8, frameY); break;
		case 2: character_pickSpecialist(gp.tileSize*8, frameY); break;
		case 3: character_pickWarrior(gp.tileSize*8, frameY); break;
		case 4: drawPickableClasses(frameX, frameY); break;
		case 5: rUSurePickClass(frameX, frameY); break;
		case 6: pickedClassComplete(frameX-gp.tileSize*2, frameY); break;
		}
		
		gp.keyH.shiftPressed = false;
		gp.keyH.enterPressed = false;
	}
	public void drawInventory(Entity entity, boolean cursor) {
		
		int frameX = 0;
		int frameY = 0;
		int frameWidth = 0;
		int frameHeight = 0;
		int slotCol = 0;
		int slotRow = 0;
		
		if(entity == gp.player) {
			frameX = gp.tileSize*12;
			frameY = gp.tileSize;
			frameWidth = gp.tileSize*6;
			frameHeight = gp.tileSize*5;
			slotCol = playerSlotCol;
			slotRow = playerSlotRow;
		}
		else {
			frameX = gp.tileSize*2;
			frameY = gp.tileSize;
			frameWidth = gp.tileSize*6;
			frameHeight = gp.tileSize*5;
			slotCol = npcSlotCol;
			slotRow = npcSlotRow;
		}
		
		// FRAME
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		// SLOT
		slotXStart = frameX + 20; // not final anymore
		slotYStart = frameY + 20; // not final anymore
		
		int slotX = slotXStart;
		int slotY = slotYStart;
		int slotSize = gp.tileSize + 3;
		
		// CURSOR
		
			if(cursor == true) {
				int cursorX = slotXStart + (slotSize * slotCol);
				int cursorY = slotYStart + (slotSize * slotRow);
				int cursorWidth = gp.tileSize;
				int cursorHeight = gp.tileSize;
				
				// DRAW CURSOR
				g2.setColor(Color.white);
				g2.setStroke(new BasicStroke(3));
				if(gp.player.leftSide == false) {
					g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
				}
				
				
				// DESCRIPTION FRAME
				int dFrameX = frameX;
				int dFrameY = frameY + frameHeight;
				int dFrameWidth = frameWidth;
				int dFrameHeight = gp.tileSize*5;
				
				// DRAW DESCRIPTION TEXT
				int textX = dFrameX + 20;
				int textY = dFrameY + gp.tileSize;
				g2.setFont(g2.getFont().deriveFont(28F));
				
				int itemIndex = getItemIndexOnSlot(slotCol, slotRow);
				
				if(entity.inventory.size()>0) {
					if(itemIndex < entity.inventory.size()) {
						
						drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
						if(entity.inventory.size()>0) {
							if(entity.inventory.get(itemIndex) != null) {
								
								for(String line: entity.inventory.get(itemIndex).description.split("\n")) {
									
									g2.drawString(line, textX, textY);
									textY += 32;
								}
								//draw range attack
//								if(entity.inventory.get(itemIndex).type == entity.type_projectile) {
//									if(entity.inventory.get(itemIndex).name.equals("Warrior Sword")) {
//										g2.drawString("Range Attack: "+entity.inventory.get(itemIndex).attack+"+",textX,textY);
//									}
//									else {
//										g2.drawString("Range Attack: "+entity.inventory.get(itemIndex).attack,textX,textY);
//									}
//									textY += 32;
//								}
								//draw durability
								if(entity.inventory.get(itemIndex).type == entity.type_sword ||
										entity.inventory.get(itemIndex).type == entity.type_axe ||
												entity.inventory.get(itemIndex).type == entity.type_pickaxe ||
										entity.inventory.get(itemIndex).type == entity.type_shield ||
										entity.inventory.get(itemIndex).type == entity.type_projectile) {
									if(entity.inventory.get(itemIndex).name.equals("Marksman Bow")) {
										g2.drawString("Durability: "+"Infinite",textX,textY);
									}
									else {
										g2.drawString("Durability: "+entity.inventory.get(itemIndex).durability,textX,textY);
									}
								}
								if(entity.inventory.get(itemIndex).name.equals("Warrior Sword")) {
									g2.drawString("Durability: "+"Infinite",textX,textY);
								}
							}
						}
					}
				}
			}
		
		// DRAW PLAYER'S ITEMS
		for(int i = 0; i < entity.inventory.size(); i++) {
			
			// EQUIP CURSOR
			if(entity.inventory.get(i) == entity.currentWeapon ||
					entity.inventory.get(i) == entity.currentShield ||
					entity.inventory.get(i) == entity.currentLight ||
					entity.inventory.get(i) == entity.currentProjectile ||
					entity.inventory.get(i) == entity.currentSpecial) {
				
				g2.setColor(new Color(240, 190, 90));
				g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
			}
			if(entity.inventory.get(i) == entity.currentAbility) {
				if(gp.player.craftOn) {
					g2.setColor(new Color(240, 190, 90)); // color before: 234, 221, 202
					g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
				}
			}
			if(entity.inventory.get(i) != null) {
				if(entity.inventory.get(i).sub_type == entity.sub_type_placeable &&
						entity.inventory.get(i).isSelected) {
					g2.setColor(new Color(144, 238, 144));
					g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
				}
				
				if(entity.inventory.get(i).type == gp.player.type_projectile) {
					g2.drawImage(entity.inventory.get(i).image, slotX, slotY, null);
				}
				else {
					g2.drawImage(entity.inventory.get(i).down1, slotX, slotY, null);
				}
				
				
				// DISPLAY AMOUNT
				if(entity == gp.player && entity.inventory.get(i).amount >= 0) {
					
					if(entity.inventory.get(i).stackable == true) {
						g2.setFont(g2.getFont().deriveFont(32));
						int amountX;
						int amountY;
						
						String s = "" + entity.inventory.get(i).amount;
						amountX = getXForAlignToRightText(s,slotX + 44);
						amountY = slotY + gp.tileSize;
						
						// SHADOW
						//System.out.println("here");
						g2.setColor(new Color(60,60,60));
						g2.drawString(s,amountX,amountY);
						//NUMBER
						g2.setColor(new Color(255,255,255));
						g2.drawString(s,amountX-1,amountY);
					}
				}
//				else if(entity == gp.player && entity.inventory.get(i).amount <= 0) {
//					entity.inventory.remove(i);
//				}
				
				slotX += slotSize;
				
				if(i == 4 || i == 9 || i == 14) {
					slotX = slotXStart;
					slotY += gp.tileSize+3;
				}
			}
		}
	}
	public void drawGameOverScreen() {
		
		if(gp.currentMap != 3) {
			for(int i = 0; i < gp.obj[gp.currentMap].length; i++) {
				if(gp.obj[gp.currentMap][i] != null && gp.obj[gp.currentMap][i].name.equals(OBJ_Door_Iron.objName)) {
					gp.obj[gp.currentMap][i] = null;
				}
			}
		}
		if(gp.currentMap == 5) {
			gp.obj[5][5] = new OBJ_Door_Down(gp,true);
			gp.obj[5][5].worldX = gp.tileSize*50;
			gp.obj[5][5].worldY = gp.tileSize*71;
		}
		
		g2.setColor(new Color(0,0,0,150));
		g2.fillRect(0,0,gp.screenWidth, gp.screenHeight);
		
		int x;
		int y;
		String text;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));
		
		text = "Game Over";
		// Shadow
		g2.setColor(Color.black);
		x = getXForCenteredText(text);
		y = gp.tileSize*4;
		g2.drawString(text,x,y);
		// Main
		g2.setColor(Color.white);
		g2.drawString(text, x-4, y-4);
		
		text = "Press 'W' or 'S'";
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 24f));
		// Shadow
		x = getXForCenteredText(text);
		y += 50 ;
		g2.setColor(Color.black);
		g2.drawString(text,x,y);
		// Main
		g2.setColor(Color.white);
		g2.drawString(text, x-2, y-2);
		
		// Retry
		g2.setFont(g2.getFont().deriveFont(50f));
		text = "Retry";
		x = getXForCenteredText(text);
		y += gp.tileSize*4;
		g2.drawString(text,x,y);
		if(commandNum == 0) {
			g2.drawString(">",x-40,y);
		}
		
		// Back to title screen
		text = "Quit";
		x = getXForCenteredText(text);
		y += 55;
		g2.drawString(text,x,y);
		if(commandNum == 1) {
			g2.drawString(">",x-40,y);
		}
		
	}
	public void drawOptionsScreen() {
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		// SUB WINDOW
		int frameX = gp.tileSize*6;
		int frameY = gp.tileSize;
		int frameWidth = gp.tileSize*8;
		int frameHeight = gp.tileSize*10;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		switch(subState) {
		case 0: options_top(frameX, frameY); break;
		case 1: options_fullScreenNotification(frameX, frameY); break;
		case 2: options_control(frameX, frameY); break;
		case 3: options_endGameConfirmation(frameX, frameY); break;
		case 4: options_quickSaveConfirmation(frameX, frameY); break; // Aahh.. So THESE are what the substates are for!!!
		case 5: options_saveScreenNotification(frameX, frameY); break;
		}
		
		gp.keyH.enterPressed = false;
		gp.keyH.escapePressed = false;
	}
	public void drawTravelScreen() {
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		// SUB WINDOW
		int frameX = gp.tileSize*6;
		int frameY = gp.tileSize;
		int frameWidth = gp.tileSize*8;
		int frameHeight = gp.tileSize*10;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		switch(subState) {
		case 0: options_top_travel(frameX, frameY); break;
		case 1: options_map1Confirmation(frameX, frameY); break;
		case 2: options_map2Confirmation(frameX, frameY); break;
		case 3: options_map3Confirmation(frameX, frameY); break;
		case 4: options_mapHOMEConfirmation(frameX, frameY); break;
		
		}
		
		gp.keyH.enterPressed = false;
	}
	public void drawQuestScreen() { // looking off travel screen
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		// checks if the quests are complete, then result shows in options_top_quest changes "commplete" to "incomplete"
		if(qH.levelQuestComplete(gp.currentMap)) {
			//levelQuestComplete = true;
			questItemSaves.replace(gp.currentMap, true);
		}
		else {
			levelQuestComplete = false;
		}
		
		// SUB WINDOW
		int frameX = gp.tileSize*6;
		int frameY = gp.tileSize;
		int frameWidth = gp.tileSize*8;
		int frameHeight = gp.tileSize*10;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		switch(subState) {
		case 0: options_top_quest(frameX, frameY); break;
		case 1: options_levelConfirmation(frameX, frameY); break;
		case 2: options_bossConfirmation(frameX, frameY); break;
		case 3: options_collectConfirmation(frameX, frameY); break;
		case 4: options_enemyConfirmation(frameX, frameY); break;
//		case 5: options_escortConfirmation(frameX, frameY); break;
//		case 6: options_deliverConfirmation(frameX, frameY); break;
		}
		
		gp.keyH.enterPressed = false;
	}
	public void drawYesOrNoScreen() { // UNUSED FOR NOW...
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		// SUB WINDOW
		int frameX = gp.tileSize*6;
		int frameY = gp.tileSize;
		int frameWidth = gp.tileSize*8;
		int frameHeight = gp.tileSize*10;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		switch(subState) {
		case 0: global_YesOrNo(frameX, frameY); break;
		}
		
		gp.keyH.enterPressed = false;
	}
	public void drawGameCompleteScreen() {
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		// CREATE A FRAME
		int frameX = gp.tileSize*6;
		int frameY = gp.tileSize;
		
		switch(subState) {
		case 0: gameComplete(frameX-gp.tileSize*3, frameY, classPicked); break;
		}
		
		gp.keyH.shiftPressed = false;
		gp.keyH.enterPressed = false;
	}
	public void gameComplete(int frameX, int frameY, String className) {
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		int frameWidth = gp.tileSize*14;
		int frameHeight = gp.tileSize*9;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		int textX;
		int textY;
		
		// TITLE
		String text = "You mastered";
		textX = getXForCenteredText(text);
		textY = gp.tileSize*2;
		g2.drawString(text, textX, textY);
		
		g2.setFont(g2.getFont().deriveFont(128F));
		text = "Ninja Quest!";
		textX = getXForCenteredText(text);
		textY = gp.tileSize*6;
		g2.drawString(text, textX, textY);
		
		
		g2.setFont(g2.getFont().deriveFont(32F));
		text = "You beat all the bosses at least twice!";
		textX = getXForCenteredText(text);
		textY = gp.tileSize*8+12;
		g2.drawString(text, textX, textY);
		
		g2.setFont(g2.getFont().deriveFont(32F));
		text = "[Shift] to continue";
		textX = getXForCenteredText(text);
		textY = gp.tileSize*9+12;
		g2.drawString(text, textX, textY);
		
		if(gp.keyH.shiftPressed) {
			
			gp.beatEntireGame = true;
			gp.gameState = gp.creditState;
			gp.ui.subState = 0;
			gp.ui.commandNum = 0;
			gp.currentMap = 6;
			gp.player.worldX = gp.tileSize*22;
			gp.player.worldY = gp.tileSize*8;
			gp.saveload.save();
		}
	}
	
	// CREDIT SCENES
	public void creditSlide1() {
		
		g2.setFont(g2.getFont().deriveFont(36F));
		int x = getXForCenteredText("Credits");
		int y = gp.tileSize+24;
		
		g2.setColor(Color.white);
		g2.drawString("Credits", x, y);
		y+=48;
		x = gp.tileSize*2;
		g2.drawString("All assets used in this game are free or Creative Commons (CC).", x, y);
		y+=48;
		g2.drawString("This version is fan-made, nonprofit, permission pending.", x, y);
		y+=48;
		g2.drawString("Much credit & thanks goes to RyiSnow.", x, y);
		y+=48;
		g2.drawString("Check out RyiSnow's channel on YouTube.", x, y);
		y+=48;
		
		x = getXForCenteredText("[Enter] next");
		g2.drawString("[Enter] next", x, gp.tileSize*11);
		
		if(gp.keyH.enterPressed) {
			subState++;
		}
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
	}
	public void creditSlide2() {
		
		g2.setFont(g2.getFont().deriveFont(36F));
		int x = getXForCenteredText("Credits");
		int y = gp.tileSize+24;
		
		g2.setColor(Color.white);
		g2.drawString("Credits", x, y);
		y+=48;
		x = gp.tileSize*2;
		g2.drawString("Majority of game code: RyiSnow", x, y);
		y+=48;
		g2.drawString("Editor of game code: Anonymous", x, y);
		y+=48;
		g2.drawString("Sound Effects: RyiSnow", x, y);
		y+=48;
		g2.drawString("Music: Kevin MacLeod", x, y);
		y+=48;
		g2.drawString("Music: Noah Giesler", x, y);
		y+=48;
		g2.drawString("Music: Nocturnal (user-82581097 on SoundCloud)", x, y);
		y+=48;
//		g2.drawString("SFX: mixkit.com", x, y);
//		y+=48;
//		g2.drawString("SFX: freesound.org", x, y);
//		y+=48;
//		g2.drawString("SFX: elements.envato.com", x, y);
//		y+=48;
//		g2.drawString("SFX: incompetech.com", x, y);
		x = getXForCenteredText("[Enter] next");
		g2.drawString("[Enter] next", x, gp.tileSize*11);
		
		if(gp.keyH.enterPressed) {
			subState++;
		}
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
	}
	public void creditSlide3() {

		g2.setFont(g2.getFont().deriveFont(36F));
		int x = getXForCenteredText("Credits");
		int y = gp.tileSize+24;
		
		g2.setColor(Color.white);
		g2.drawString("Credits", x, y);
		y+=48;
		x = gp.tileSize*2;
		g2.drawString("Title Art: Raoni Dorim", x, y);
		y+=48;
		g2.drawString("Sprites & Tiles: RyiSnow", x, y);
		y+=48;
//		g2.drawString("Sprites & Tiles: opengameart.org", x, y);
//		y+=48;
		g2.drawString("Sprites & Tiles: Anonymous", x, y);
		y+=48;
		g2.drawString("Sprites: alexdraws (itch.io)", x, y);
		y+=48;
		g2.drawString("Sprites: ElvGames", x, y);
		y+=48;
		//g2.drawString("Inspiration: itch.io", x, y);
		x = getXForCenteredText("[Enter] next");
		g2.drawString("[Enter] next", x, gp.tileSize*11);
		
		if(gp.keyH.enterPressed) {
			subState++;
		}
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
	}
	public void creditSlide4() {

		g2.setFont(g2.getFont().deriveFont(36F));
		int x = getXForCenteredText("Credits");
		int y = gp.tileSize+24;
		
		g2.setColor(Color.white);
		g2.drawString("Credits", x, y);
		y+=48;
		x = gp.tileSize*2;
		g2.drawString("Special Thanks:", x, y);
		y+=48;
		g2.drawString("- RyiSnow", x, y);
		y+=48;
		g2.drawString("- Miguel", x, y);
		y+=48;
		g2.drawString("- Daniel", x, y);
		y+=48;
		g2.drawString("- Alden", x, y);
		y+=48;
		x = getXForCenteredText("[Enter] next");
		g2.drawString("[Enter] next", x, gp.tileSize*11);
		
		if(gp.keyH.enterPressed) {
			subState++;
		}
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
	}
	public void creditSlide5() {

		g2.setFont(g2.getFont().deriveFont(36F));
		int x = getXForCenteredText("Credits");
		int y = gp.tileSize+24;
		
		g2.setColor(Color.white);
		g2.drawString("Credits", x, y);
		y+=48;
		x = gp.tileSize*2;
		g2.drawString("I hope you enjoyed this game!", x, y);
		y+=48;
		g2.drawString("Stay for the music!", x, y);
		y+=48;
		g2.drawString("God bless you!", x, y);
		x = getXForCenteredText("[Enter] title screen");
		g2.drawString("[Enter] title screen", x, gp.tileSize*11);
		
		if(gp.keyH.enterPressed) {
			subState = 0;
			commandNum = 0;
			gp.gameState = gp.titleState;
			
		}
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
	}
	
	// OPTIONS SCREENS
	public void options_top(int frameX, int frameY) {
		
		int textX;
		int textY;
		
		// TITLE
		String text = "Options";
		textX = getXForCenteredText(text);
		textY = frameY + gp.tileSize;
		g2.drawString(text, textX, textY);
		
		// FULL SCREEN ON/OFF
		textX = frameX + gp.tileSize;
		textY += gp.tileSize;
		textY += 24;
		g2.drawString("Full Screen", textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				if(gp.fullScreenOn == false) {
					gp.fullScreenOn = true;
				}
				else if(gp.fullScreenOn == true) {
					gp.fullScreenOn = false;
				}
				subState = 1;
			}
		}
		
		// MUSIC
		textY += gp.tileSize;
		g2.drawString("Music", textX, textY);
		if(commandNum == 1) {
			g2.drawString(">", textX-25, textY);
		}
		
		// SE
		textY += gp.tileSize;
		g2.drawString("SFX", textX, textY);
		if(commandNum == 2) {
			g2.drawString(">", textX-25, textY);
		}
		
		// CONTROL
		textY += gp.tileSize;
		g2.drawString("CONTROLS", textX, textY);
		if(commandNum == 3) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 2;
				commandNum = 0;
			}
		}
		
		// Save & quit GAME
		textY += gp.tileSize;
		g2.drawString("Quit Game", textX, textY);
		if(commandNum == 4) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 3;
				commandNum = 0;
			}
		}
		
		// Quick Save
		textY += gp.tileSize;
		g2.drawString("Save Game", textX, textY);
		if(commandNum == 5) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 4;
				commandNum = 0;
			}
		}
		
		// BACK
		textY += gp.tileSize+24;
		g2.drawString("Back", textX, textY);
		if(commandNum == 6) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				gp.gameState = gp.playState;
				commandNum = 0;
			}
		}
		
		// FULL SCREEN CHECK BOX
		textX = frameX + (int)(gp.tileSize*4.5);
		textY = frameY + gp.tileSize*2;
		g2.setStroke(new BasicStroke(3));
		g2.drawRect(textX, textY, 24, 24);
		if(gp.fullScreenOn == true) {
			g2.fillRect(textX, textY, 24, 24);
		}
		
		// MUSIC VOLUME
		textY += gp.tileSize;
		g2.drawRect(textX, textY, 120, 24); // 120/5 = 24
		int volumeWidth = 24* gp.music.volumeScale;
		g2.fillRect(textX, textY, volumeWidth, 24);
		
		// SE VOLUME
		textY += gp.tileSize;
		g2.drawRect(textX, textY, 120, 24);
		volumeWidth = 24* gp.se.volumeScale;
		g2.fillRect(textX, textY, volumeWidth, 24);
		
		gp.config.saveConfig();
	}
	public void options_fullScreenNotification(int frameX, int frameY) {
		
		int textX = frameX + gp.tileSize;
		int textY = frameY + gp.tileSize*3;
		
		currentDialogue = "Close and re-open the\nprogram for fullscreen.\nMay experience some\nperformance issues.";
		
		for(String line: currentDialogue.split("\n")) {
			g2.drawString(line, textX, textY);
			textY += 40;
		}
		
		// BACK 
		textY = frameY + gp.tileSize*9;
		g2.drawString("Back", textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 0;
			}
		}	
	}
	public void options_control(int frameX, int frameY) {
		
		int textX;
		int textY;
		
		// TITLE
		String text = "Controls";
		textX = getXForCenteredText(text);
		textY = frameY + gp.tileSize;
		g2.drawString(text, textX, textY);
		
		textX = frameX + gp.tileSize;
		textY += gp.tileSize-30;
		g2.drawString("Move", textX, textY); textY += 41;
		g2.drawString("Confirm/Attack", textX, textY); textY += 41;
		g2.drawString("Shield/Parry", textX, textY); textY += 41;
		g2.drawString("Shoot/Fire", textX, textY); textY += 41;
		//g2.drawString("Map", textX, textY); textY += gp.tileSize;
		g2.drawString("Inventory", textX, textY); textY += 41;
		g2.drawString("Pause", textX, textY); textY += 41;
		g2.drawString("Quests", textX, textY); textY += 41;
		g2.drawString("Skill Tree", textX, textY); textY += 41;
		g2.drawString("Options", textX, textY);
		
		textX = frameX + gp.tileSize*6-2;
		textY = frameY + gp.tileSize*2-24;
		g2.drawString("WASD", textX, textY); textY += 41;
		g2.drawString("Enter", textX, textY); textY += 41;
		g2.drawString("SHIFT", textX, textY); textY += 41;
		g2.drawString("F", textX, textY); textY += 41;
		//g2.drawString("M", textX, textY); textY += gp.tileSize;
		g2.drawString("E", textX, textY); textY += 41;
		g2.drawString("P", textX, textY); textY += 41;
		g2.drawString("Q", textX, textY); textY += 41;
		g2.drawString("T", textX, textY); textY += 41;
		g2.drawString("ESCAPE", textX, textY);
		
		// BACK
		textX = frameX + gp.tileSize;
		textY = frameY + gp.tileSize*9+18;
		g2.drawString("Back", textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 0;
				commandNum = 3;
			}
		}
	}
	public void options_endGameConfirmation(int frameX, int frameY) {
		
		int textX = frameX + gp.tileSize;//*2+12;
		int textY = frameY + gp.tileSize*3;
		
		currentDialogue = "Are you sure you want to\nquit the game?";
		
		for(String line: currentDialogue.split("\n")) {
			g2.drawString(line, textX, textY);
			textY += 40;
		}
		
		// YES
		String text = "Yes";
		textX = getXForCenteredText(text);
		textY += gp.tileSize*3;
		g2.drawString(text, textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 0;
				gp.stopMusic();
				gp.gameState = gp.titleState;
				gp.resetGame(true);
				gp.playMusic(44);
			}
		}
		
		// NO
		text = "No";
		textX = getXForCenteredText(text);
		textY += gp.tileSize;
		g2.drawString(text, textX, textY);
		if(commandNum == 1) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 0;
				commandNum = 4;
			}
		}
	}
	public void options_quickSaveConfirmation(int frameX, int frameY) {
		
		int textX = frameX + gp.tileSize*2+30;
		int textY = frameY + gp.tileSize*3;
		
		currentDialogue = "Quick Save?";
		
		for(String line: currentDialogue.split("\n")) {
			g2.drawString(line, textX, textY);
			textY += 40;
		}
		
		// YES
		String text = "Yes";
		textX = getXForCenteredText(text);
		textY += gp.tileSize*3;
		g2.drawString(text, textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 5;
				gp.saveload.save();
				commandNum = 5;
				//currentDialogue = "You saved!";
				//g2.drawString(currentDialogue, textX, textY);
			}
		}
		
		// NO
		text = "No";
		textX = getXForCenteredText(text);
		textY += gp.tileSize;
		g2.drawString(text, textX, textY);
		if(commandNum == 1) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 0;
				commandNum = 5;
			}
		}
	}
	public void options_saveScreenNotification(int frameX, int frameY) {
		
//		int textX = gp.tileSize*6+24;
//		int textY = gp.tileSize*2;
		int textX;
		int textY;
		
		// TITLE
		String text = "You saved!";
		textX = frameX + gp.tileSize;
		textY = frameY + gp.tileSize+24;
		g2.drawString(text, textX, textY);
		g2.drawString("Press [Enter] to exit.", textX, textY+gp.tileSize);
		
		
		if(gp.keyH.enterPressed) {
			subState = 0;
			commandNum = 0;
			gp.gameState = gp.playState;
		}
		if(gp.keyH.escapePressed) {
			subState = 0;
			commandNum = 0;
			gp.gameState = gp.playState;
		}
	}
	
	// PLAYER CHARACTER SCREENS
	public void character_currentScreen(int frameX, int frameY) {
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		int frameWidth = gp.tileSize*6;
		int frameHeight = gp.tileSize*8;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		// TEXT
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		int textX = frameX + 20;
		int textY = frameY + gp.tileSize;
		final int lineHeight = 35;
		
		// NAMES
		g2.drawString("FEATURES", textX, textY);textY += lineHeight + 15;
		g2.drawString("Weapon", textX, textY);textY += lineHeight + 15;
		g2.drawString("Shield", textX, textY);textY += lineHeight + 15;
		g2.drawString("Projectile", textX, textY);textY += lineHeight + 15;
		g2.drawString("Light", textX, textY);textY += lineHeight + 15;
		g2.drawString("Special", textX, textY);textY += lineHeight;//+ 15
//		g2.drawString("Ability", textX, textY);textY += lineHeight;
		
		
		// VALUES
		int tailX = (frameX + frameWidth) - 30;
		// Reset textY
		textY = frameY + gp.tileSize;
		String value;
		
		textY += lineHeight; // minor bug when weapon is selected 1st, and then warrior sword, then 1st frame bugs out, fix later...
//		System.out.println("here");
		if(gp.player.currentWeapon != null) { 
			if(gp.player.currentWeapon.type == gp.player.type_sword
					|| gp.player.currentWeapon.type == gp.player.type_axe
					|| gp.player.currentWeapon.type == gp.player.type_pickaxe) {
				gp.player.getAttackImage();
				if(gp.player.currentWeapon != null) { // need this double check bc computer is running too fast to register...
					g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY-24, null);
				}
				textY += gp.tileSize;
			}
		}
		else {
			textY += gp.tileSize;
		}
		if(gp.player.currentShield != null) {
			g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize, textY-24, null);
			textY += gp.tileSize;
		}
		else {
			textY += gp.tileSize;
		}
		if(gp.player.currentProjectile != null) {
			g2.drawImage(gp.player.currentProjectile.image, tailX - gp.tileSize, textY-24, null);
			textY += gp.tileSize;
		}
		else {
			textY += gp.tileSize;
		}
		if(gp.player.currentLight != null) {
			g2.drawImage(gp.player.currentLight.down1, tailX - gp.tileSize, textY-24, null);
			textY += gp.tileSize;
		}
		else {
			textY += gp.tileSize;
		}
		if(gp.player.currentSpecial != null) {
			if(gp.player.currentSpecial.name.equals("Warrior Sword")) {
				gp.player.getAttackImage();
			}
			if(gp.player.currentSpecial != null) { // had to check again, fixes problem, where player might be swapping too fast
				g2.drawImage(gp.player.currentSpecial.down1, tailX - gp.tileSize, textY-24, null);
			}
			textY += gp.tileSize;
		}
		else {
			textY += gp.tileSize;
		}
//		if(gp.player.currentAbility != null) {
//			g2.drawImage(gp.player.currentAbility.down1, tailX - gp.tileSize, textY-24, null);
//		}
		
		
		int x = gp.tileSize*2;
		int y = gp.tileSize*9;
		int width = gp.tileSize*6;
		int height = gp.tileSize*2;
		drawSubWindow(x,y,width,height);
		g2.drawString("[E] Back", x+14,y+40);
		y+=gp.tileSize;
		g2.drawString("[SHIFT] Toggle", x+14,y+32);
	}
	public void character_statScreen(int frameX, int frameY) {
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		int frameWidth = gp.tileSize*6;
		int frameHeight = gp.tileSize*8;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		// TEXT
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		int textX = frameX + 20;
		int textY = frameY + gp.tileSize;
		final int lineHeight = 35;
		
		// NAMES
		g2.drawString("STATS", textX, textY);textY += lineHeight;
		g2.drawString("Level", textX, textY);textY += lineHeight;
		g2.drawString("Coins", textX, textY);textY += lineHeight;
		g2.drawString("Life", textX, textY);textY += lineHeight;
		g2.drawString("Ammo", textX, textY);textY += lineHeight; // FYI: calling Ammo instead of energy or mana, but its using mana stats
		g2.drawString("Attack", textX, textY);textY += lineHeight;
		g2.drawString("Range Attack", textX, textY);textY += lineHeight;
		g2.drawString("Defense", textX, textY);textY += lineHeight;
		g2.drawString("Exp", textX, textY);textY += lineHeight;
		g2.drawString("Next Lvl Exp", textX, textY);textY += lineHeight + 10;
		
		
		// VALUES
		int tailX = (frameX + frameWidth) - 30;
		// Reset textY
		textY = frameY + gp.tileSize;
		String value;
		
		textY += lineHeight;
		value = String.valueOf(gp.player.level);
		textX = getXForAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.coin);
		textX = getXForAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
		textX = getXForAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.mana + "/" + gp.player.maxMana);
		textX = getXForAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.strength);
		textX = getXForAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.rangeAttack);
		textX = getXForAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.dexterity);
		textX = getXForAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.exp);
		textX = getXForAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.nextLevelExp);
		textX = getXForAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		textY += 5;
		
		
		
		int x = gp.tileSize*2;
		int y = gp.tileSize*9;
		int width = gp.tileSize*6;
		int height = gp.tileSize*2;
		drawSubWindow(x,y,width,height);
		g2.drawString("[E] Back", x+14,y+40);
		y+=gp.tileSize;
		g2.drawString("[SHIFT] Toggle", x+14,y+32);
	}
	public void character_achievementScreen(int frameX, int frameY) {
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		int frameWidth = gp.tileSize*6;
		int frameHeight = gp.tileSize*8;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		// TEXT
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		int textX = frameX + 20;
		int textY = frameY + gp.tileSize;
		final int lineHeight = 35;
		
		// NAMES
		g2.drawString("ACHIEVEMENTS", textX, textY);textY += lineHeight;
		if(gp.player.currentClass.equals("ranger")) {
			g2.drawString("- Ranger Emblem", textX, textY);textY += lineHeight;
		}
		if(gp.player.currentClass.equals("guardian")) {
			g2.drawString("- Guardian Emblem", textX, textY);textY += lineHeight;
		}
		if(gp.player.currentClass.equals("specialist")) {
			g2.drawString("- Specialist Emblem", textX, textY);textY += lineHeight;
		}
		if(gp.player.currentClass.equals("warrior")) {
			g2.drawString("- Warrior Emblem", textX, textY);textY += lineHeight;
		}
		
		int x = gp.tileSize*2;
		int y = gp.tileSize*9;
		int width = gp.tileSize*6;
		int height = gp.tileSize*2;
		drawSubWindow(x,y,width,height);
		g2.drawString("[E] Back", x+14,y+40);
		y+=gp.tileSize;
		g2.drawString("[SHIFT] Toggle", x+14,y+32);
	}
	public void character_extraScreen(int frameX, int frameY) {
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		int frameWidth = gp.tileSize*6;
		int frameHeight = gp.tileSize*8;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		// TEXT
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		int textX = frameX + 20;
		int textY = frameY + gp.tileSize;
		final int lineHeight = 35;
		
		// NAMES
		g2.drawString("EXTRA", textX, textY);textY += lineHeight;
		
		
		g2.drawString("Day Number: "+gp.dayNumber, textX, textY);textY += lineHeight;
		
		g2.drawString("Player Deaths: "+gp.player.deathCount, textX, textY);textY += lineHeight;
		
		int monsBeat = 0;
		for(Integer value : gp.player.qH.monsDefeated.values()) {
			monsBeat += value;
		}
		int bossesBeat = 0;
		for(Integer value : gp.player.qH.bossesDefeated.values()) {
			bossesBeat += value;
		}
		
		g2.drawString("Monster Count: "+monsBeat, textX, textY);textY += lineHeight;
		
		g2.drawString("Boss Count: "+bossesBeat, textX, textY);textY += lineHeight;
		
		int x = gp.tileSize*2;
		int y = gp.tileSize*9;
		int width = gp.tileSize*6;
		int height = gp.tileSize*2;
		drawSubWindow(x,y,width,height);
		g2.drawString("[E] Back", x+14,y+40);
		y+=gp.tileSize;
		g2.drawString("[SHIFT] Toggle", x+14,y+32);
	}
	
	// CRAFTSMAN SCREENS
	public void character_craftsmanCurrentScreen(int frameX, int frameY) {
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		int frameWidth = gp.tileSize*6;
		int frameHeight = gp.tileSize*8;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		// TEXT
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		int textX = frameX + 20;
		int textY = frameY + gp.tileSize;
		final int lineHeight = 35;
		
		// NAMES
		g2.drawString("FEATURES", textX, textY);textY += lineHeight + 15;
		g2.drawString("Weapon", textX, textY);textY += lineHeight + 15;
		g2.drawString("Shield", textX, textY);textY += lineHeight + 15;
		g2.drawString("Projectile", textX, textY);textY += lineHeight + 15;
		g2.drawString("Light", textX, textY);textY += lineHeight + 15;
		g2.drawString("Special", textX, textY);textY += lineHeight;
		
		
		// VALUES
		int tailX = (frameX + frameWidth) - 30;
		// Reset textY
		textY = frameY + gp.tileSize;
		String value;
		
		textY += lineHeight; // minor bug when weapon is selected 1st, and then warrior sword, then 1st frame bugs out, fix later...
//		System.out.println("here");
		if(gp.player.currentWeapon != null) { 
			if(gp.player.currentWeapon.type == gp.player.type_sword
					|| gp.player.currentWeapon.type == gp.player.type_axe
					|| gp.player.currentWeapon.type == gp.player.type_pickaxe) {
				gp.player.getAttackImage();
				if(gp.player.currentWeapon != null) { // need this double check bc computer is running too fast to register...
					g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY-24, null);
				}
				textY += gp.tileSize;
			}
		}
		else {
			textY += gp.tileSize;
		}
		if(gp.player.currentShield != null) {
			g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize, textY-24, null);
			textY += gp.tileSize;
		}
		else {
			textY += gp.tileSize;
		}
		if(gp.player.currentProjectile != null) {
			g2.drawImage(gp.player.currentProjectile.image, tailX - gp.tileSize, textY-24, null);
			textY += gp.tileSize;
		}
		else {
			textY += gp.tileSize;
		}
		if(gp.player.currentLight != null) {
			g2.drawImage(gp.player.currentLight.down1, tailX - gp.tileSize, textY-24, null);
			textY += gp.tileSize;
		}
		else {
			textY += gp.tileSize;
		}
		if(gp.player.currentSpecial != null) {
			if(gp.player.currentSpecial.name.equals("Warrior Sword")) {
				gp.player.getAttackImage();
			}
			if(gp.player.currentSpecial != null) { // had to check again, fixes problem, where player might be swapping too fast
				g2.drawImage(gp.player.currentSpecial.down1, tailX - gp.tileSize, textY-24, null);
			}
			textY += gp.tileSize;
		}
		else {
			textY += gp.tileSize;
		}
		
		
		int x = gp.tileSize*2;
		int y = gp.tileSize*9;
		int width = gp.tileSize*6;
		int height = gp.tileSize*2;
		drawSubWindow(x,y,width,height);
		g2.drawString("[E] Back", x+14,y+40);
		y+=gp.tileSize;
		g2.drawString("[SHIFT] Toggle", x+14,y+32);
	}
	public void character_craftingTableScreen(int frameX, int frameY) {
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		int frameWidth = gp.tileSize*6;
		int frameHeight = gp.tileSize*8;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		// TEXT
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		int textX = frameX + 20;
		int textY = frameY + gp.tileSize;
		final int lineHeight = 35;
		
		// NAMES
		g2.drawString("CRAFTING HINTS", textX, textY);
		textY += gp.tileSize;

		g2.drawString("Anvil", textX+25, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX, textY);
		}
		textY += gp.tileSize;
		g2.drawString("TNT", textX+25, textY);
		if(commandNum == 1) {
			g2.drawString(">", textX, textY);
		}
		textY += gp.tileSize;
		g2.drawString("Wood", textX+25, textY);
		if(commandNum == 2) {
			g2.drawString(">", textX, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 1;
				commandNum = 0;
			}
		}
		
//		g2.drawString("Anvil", textX, textY);textY += lineHeight; // NEW obj
//		g2.drawString("Heart", textX, textY);textY += lineHeight; // NEW obj
//		g2.drawString("Mana", textX, textY);textY += lineHeight; // NEW obj
//		g2.drawString("Tent", textX, textY);textY += lineHeight;
//		g2.drawString("TNT", textX, textY);textY += lineHeight; // NEW obj
//		g2.drawString("Wood", textX, textY);textY += lineHeight; // NEW obj
		
		int x = gp.tileSize*2;
		int y = gp.tileSize*9;
		int width = gp.tileSize*6;
		int height = gp.tileSize*2;
		drawSubWindow(x,y,width,height);
		g2.drawString("[E] Back", x+14,y+40);
		y+=gp.tileSize;
		g2.drawString("[SHIFT] Toggle", x+14,y+32);
	}
	public void character_craftingTableClicked(int frameX, int frameY) {
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		int frameWidth = gp.tileSize*3;
		int frameHeight = gp.tileSize*4;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		// TEXT
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		int textX = frameX + 24;
		int textY = frameY + gp.tileSize+12;
		final int lineHeight = 35;
		
		// NAMES
		g2.drawString("Hold", textX+25, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX, textY);
			if(gp.keyH.enterPressed == true) {
				gp.playSE(57);
				gp.player.inventory.add(new OBJ_Hero_Crafting_Table(gp));
				
				gp.obj[gp.currentMap][199] = new OBJ_Hero_Crafting_Table(gp);
				
				gp.obj[gp.currentMap][199].worldX = gp.tileSize*-10; //I  don't know how to null or rid item, so it gets transported off 
				gp.obj[gp.currentMap][199].worldY = gp.tileSize*-10; //the map (player can't see) giving illusion of picking and placing blocks
				gp.gameState = gp.characterState;
				gp.player.tableOn = false;
				gp.ui.subState = 0;
			}
		}
		textY += gp.tileSize;
		g2.drawString("Use", textX+25, textY);
		if(commandNum == 1) {
			g2.drawString(">", textX, textY);
			if(gp.keyH.enterPressed) {
				gp.ui.subState = 3;
				
				gp.player.currentWeapon = null;
				gp.player.currentShield = null;
				gp.player.currentLight = null;
				//gp.player.currentItem().isSelected = false;
				if(gp.player.inventory.contains(gp.player.currentItem())) {
					gp.player.currentItem().isSelected = false;
				}
				
				gp.player.craftOn = true;
				gp.player.rightSide = true; // right side is normal player inventory, left is the crafting table
			}
		}
		textY += gp.tileSize;
		g2.drawString("Back", textX+25, textY);
		if(commandNum == 2) {
			g2.drawString(">", textX, textY);
			if(gp.keyH.enterPressed == true) {
				gp.gameState = gp.playState;
				commandNum = 0;
			}
		}
	}
	public void character_drawCraftScreen(int frameX, int frameY) {
		//System.out.println("drawCraftScreen is on");
		
		int x = gp.tileSize*2;
		int y = gp.tileSize*9;
		int width = gp.tileSize*6;
		int height = gp.tileSize*2;
		drawSubWindow(x,y,width,height);
		g2.drawString("[E] Back [C] Craft", x+14,y+40);
		y+=gp.tileSize;
		g2.drawString("[SHIFT] Switch Sides", x+14,y+32);
		drawSubWindow(x, gp.tileSize, gp.tileSize*4, gp.tileSize*4);
		//drawSubWindow(x, gp.tileSize*5, width, gp.tileSize*4);
//		switch(subState) {
//		case 0: craft_select(); break;
//		case 1: craft_create(); break;
//		}
		gp.keyH.enterPressed = false;
	}
	public void character_legitCraftingScreen(int frameX, int frameY) {
		//System.out.println("legit craft started");
		
		int cursorX = gp.tileSize*2+24;
		int cursorY = gp.tileSize+24;
		int cursorWidth = gp.tileSize;
		int cursorHeight = gp.tileSize;
		//int slotCol = cursorX;
		//int slotRow = cursorY;
		int slotCol = playerSlotCol;
		int slotRow = playerSlotRow;
		
		// SLOT
		//slotXStart = 20; // not final anymore
		//slotYStart = 20; // not final anymore
		int slotX = slotXStart;
		int slotY = slotYStart;
		int slotSize = gp.tileSize + 3;
		
		// dont mess with this bottom part
		int x = gp.tileSize*2;
		int y = gp.tileSize*9;
		int width = gp.tileSize*6;
		int height = gp.tileSize*2;
		drawSubWindow(x,y,width,height);
		g2.drawString("[E] Back [C] Craft", x+14,y+40);
		y+=gp.tileSize;
		g2.drawString("[SHIFT] Switch Sides", x+14,y+32);
		drawSubWindow(x, gp.tileSize, gp.tileSize*4, gp.tileSize*4); // actual crafting screen
		drawSubWindow(x, gp.tileSize*5, gp.tileSize*4, gp.tileSize*2+24); // descriptions, etc, extra
		
		// DRAW CURSOR
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke(3));
//				if((playerSlotCol == 0)&& (playerSlotRow == 0)) {
//					//g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10); // so it doesn't draw on right side @ the beginning
//				}
//				else {
//					//g2.drawRoundRect(playerSlotCol+cursorX, playerSlotRow+cursorY, cursorWidth, cursorHeight, 10, 10);
//				}
		g2.drawRoundRect(playerSlotCol+cursorX, playerSlotRow+cursorY, cursorWidth, cursorHeight, 10, 10);
		
		// how to fill all tiles
//		for(int i = 0; i < 3; i++) {
//			for(int j = 0; j < 3; j++) {
//				g2.drawImage(gp.player.currentAbility.down1, j*48+cursorX, i*48+cursorY, null);
//			}
//		}
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				
				//System.out.println("drawedBlock");
				if(gp.player.currentAbility != null) {
					if(gp.keyH.craftModeChecker[i][j]) {
						g2.drawImage(gp.player.currentAbility.down1, 48*i+cursorX, 48*j+cursorY, null);
					}
					else {
						//g2.drawImage(anvilBlock, playerSlotCol+cursorX, playerSlotRow+cursorY, null);
					}
				}
			}
		}
		
		// check anvil for UI
		boolean anvilChecker = true;
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(gp.keyH.craftModeChecker[i][j] && gp.player.currentAbility.name.equals("Stone")) {
					//System.out.println("here");
				}
				else {
					anvilChecker = false;
				}
			}
		}
		
		if(anvilChecker) {
			g2.drawImage(anvilBlock, gp.tileSize*2+18, gp.tileSize*5+6, null);
			g2.drawString("Anvil", gp.tileSize*3+24,gp.tileSize*6);
		}
		//-----------------------------------------------------------------------------------------------
		// check Wooden Plank for UI
		boolean woodenPlankChecker = true;
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(gp.keyH.craftModeChecker[i][j] && gp.player.currentAbility.name.equals("Wood")) {
					
				}
				else {
					woodenPlankChecker = false;
				}
			}
		}
		
		if(woodenPlankChecker) {
			g2.drawImage(woodenPlank, gp.tileSize*2+18, gp.tileSize*5+12, null);
			g2.drawString("Wooden", gp.tileSize*3+24,gp.tileSize*6);
			g2.drawString("Plank", gp.tileSize*3+24,gp.tileSize*7-12);
		}
		
	}
	public void character_anvilClicked(int frameX, int frameY) {
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		int frameWidth = gp.tileSize*3;
		int frameHeight = gp.tileSize*4;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		// TEXT
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		int textX = frameX + 24;
		int textY = frameY + gp.tileSize+12;
		final int lineHeight = 35;
		
		// NAMES
		g2.drawString("Hold", textX+25, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX, textY);
			if(gp.keyH.enterPressed == true) {
				gp.playSE(57);
				gp.player.inventory.add(new OBJ_Craftable_Anvil(gp));
				
				gp.obj[gp.currentMap][199].worldX = gp.tileSize*-10; //I  don't know how to null or rid item, so it gets transported off 
				gp.obj[gp.currentMap][199].worldY = gp.tileSize*-10; //the map (player can't see) giving illusion of picking and placing blocks
				gp.gameState = gp.characterState;
				gp.player.tableOn = false;
				gp.ui.subState = 0;
			}
		}
		textY += gp.tileSize;
		g2.drawString("Use", textX+25, textY);
		if(commandNum == 1) {
			g2.drawString(">", textX, textY);
			if(gp.keyH.enterPressed) {
				subState = 2;
				//System.out.println("used");
			}
		}
		textY += gp.tileSize;
		g2.drawString("Back", textX+25, textY);
		if(commandNum == 2) {
			g2.drawString(">", textX, textY);
			if(gp.keyH.enterPressed == true) {
				gp.gameState = gp.playState;
				commandNum = 0;
			}
		}
	}
	public void character_anvilUse(int frameX, int frameY) {
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		int frameWidth = gp.tileSize*3;
		int frameHeight = gp.tileSize*4;
		drawSubWindow(frameX, frameY, frameWidth+gp.tileSize, frameHeight);
		
		// TEXT
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		int textX = frameX + 24;
		int textY = frameY + gp.tileSize+12;
		final int lineHeight = 35;
		
		// NAMES
		g2.drawString("Enhance", textX+25, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX, textY);
			if(gp.keyH.enterPressed) {
				//System.out.println(subState);
				commandNum = 0;
				subState = 3;
			}
		}
		textY += gp.tileSize;
		g2.drawString("Back", textX+25, textY);
		if(commandNum == 1) {
			g2.drawString(">", textX, textY);
			if(gp.keyH.enterPressed == true) {
				commandNum = 0;
				subState = 1;
			}
		}
//		if(commandNum == 2) {
//			System.out.println("here");
//			commandNum = 0;
//		}
	}
	public void character_anvilUseLegit(int frameX, int frameY) {
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		int frameWidth = gp.tileSize*3;
		int frameHeight = gp.tileSize*4;
		drawSubWindow(frameX, frameY, frameWidth+gp.tileSize, frameHeight);
		
		// TEXT
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		int textX = frameX + 24;
		int textY = frameY + gp.tileSize+12;
		final int lineHeight = 35;
		
		// NAMES
		g2.drawString("x3 Stone", textX+25, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX, textY);
			if(gp.keyH.enterPressed) {
				//System.out.println("used");
				boolean containsChosen = false;
				boolean played = false;
				for(int i = 0; i < gp.player.inventory.size(); i++) {
					if(gp.player.inventory.get(i).name.equals("Stone")) {
						if(gp.player.inventory.get(i).amount >= 3) {
							//System.out.println("noice");
							gp.player.inventory.get(i).amount-=3;
							if(gp.player.inventory.get(i).amount == 0) {
								gp.player.inventory.remove(i);
							}
							
							// HERE IT BEGINS!!!
							if(gp.player.currentWeapon != null) {
								gp.player.currentWeapon.durability += 30;
							}
							if(gp.player.currentShield != null) {
								gp.player.currentShield.durability += 30;
							}
							if(gp.player.currentProjectile != null) {
								gp.player.currentProjectile.durability += 30;
							}
							
							containsChosen = true;
							gp.gameState = gp.playState;
							npc.startDialogue(npc,3);
							drawDialogueScreen();
							break;
						}
						else {
							gp.gameState = gp.playState;
							npc.startDialogue(npc,1);
							drawDialogueScreen();
							played = true;
						}
					}
					
				}
				if(containsChosen == false && played == false) {
					gp.gameState = gp.playState;
					npc.startDialogue(npc,1);
					drawDialogueScreen();
				}
				
			}
		}
		textY += gp.tileSize;
		g2.drawString("x3 Wood", textX+25, textY);
		if(commandNum == 1) {
			g2.drawString(">", textX, textY);
			if(gp.keyH.enterPressed) {
				//System.out.println("used");
				boolean containsChosen = false;
				boolean played = false;
				for(int i = 0; i < gp.player.inventory.size(); i++) {
					if(gp.player.inventory.get(i).name.equals("Wood")) {
						if(gp.player.inventory.get(i).amount >= 3) {
							//System.out.println("noice");
							gp.player.inventory.get(i).amount-=3;
							if(gp.player.inventory.get(i).amount == 0) {
								gp.player.inventory.remove(i);
							}
							
							// HERE IT BEGINS!!!
							if(gp.player.currentWeapon != null) {
								gp.player.currentWeapon.durability += 10;
							}
							if(gp.player.currentShield != null) {
								gp.player.currentShield.durability += 10;
							}
							if(gp.player.currentProjectile != null) {
								gp.player.currentProjectile.durability += 10;
							}
							
							containsChosen = true;
							gp.gameState = gp.playState;
							//System.out.println(npc.name); // npc here is Anvil!
							npc.startDialogue(npc,2);
							drawDialogueScreen();
							break;
						}
						else {
							gp.gameState = gp.playState;
							npc.startDialogue(npc,0);
							drawDialogueScreen();
							played = true;
						}
					}
					
				}
				if(containsChosen == false && played == false) {
					gp.gameState = gp.playState;
					npc.startDialogue(npc,0);
					drawDialogueScreen();
				}
				
			}
		}
		textY += gp.tileSize;
		g2.drawString("Back", textX+25, textY);
		if(commandNum == 2) {
			g2.drawString(">", textX, textY);
			if(gp.keyH.enterPressed == true) {
				commandNum = 0;
				subState = 2;
			}
		}
	}

	
	// PICK CLASS SCREEN
	public void character_pickCraftsman(int frameX, int frameY) {
		
		classPicked = "Craftsman";
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		int frameWidth = gp.tileSize*3;
		int frameHeight = gp.tileSize*4;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		// TEXT
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		int textX = frameX + 24;
		int textY = frameY + gp.tileSize+12;
		final int lineHeight = 35;
		
		//System.out.println("commandNum: "+commandNum);
		
		// NAMES
		g2.drawString("INFO", textX+25, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX, textY);
			if(gp.keyH.enterPressed == true) {
				classPicked = "Craftsman";
				gp.ui.subState = 4;
			}
		}
		textY += gp.tileSize;
		g2.drawString("Select", textX+25, textY);
		if(commandNum == 1) {
			g2.drawString(">", textX, textY);
			if(gp.keyH.enterPressed) {
				classPicked = "Craftsman";
				gp.ui.subState = 5;
			}
		}
		textY += gp.tileSize;
		g2.drawString("Back", textX+25, textY);
		if(commandNum == 2) {
			g2.drawString(">", textX, textY);
			if(gp.keyH.enterPressed == true) {
				classPicked = null;
				gp.gameState = gp.playState;
				commandNum = 0;
			}
		}
	}
	public void character_pickRanger(int frameX, int frameY) {
		
		classPicked = "Ranger";
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		int frameWidth = gp.tileSize*3;
		int frameHeight = gp.tileSize*4;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		// TEXT
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		int textX = frameX + 24;
		int textY = frameY + gp.tileSize+12;
		final int lineHeight = 35;
		
		//System.out.println("commandNum: "+commandNum);
		
		// NAMES
		g2.drawString("INFO", textX+25, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX, textY);
			if(gp.keyH.enterPressed == true) {
				classPicked = "Ranger";
				gp.ui.subState = 4;
			}
		}
		textY += gp.tileSize;
		g2.drawString("Select", textX+25, textY);
		if(commandNum == 1) {
			g2.drawString(">", textX, textY);
			if(gp.keyH.enterPressed) {
				classPicked = "Ranger";
				gp.ui.subState = 5;
			}
		}
		textY += gp.tileSize;
		g2.drawString("Back", textX+25, textY);
		if(commandNum == 2) {
			g2.drawString(">", textX, textY);
			if(gp.keyH.enterPressed == true) {
				classPicked = null;
				gp.gameState = gp.playState;
				commandNum = 0;
			}
		}
	}
	public void character_pickGuardian(int frameX, int frameY) {
		
		classPicked = "Guardian";
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		int frameWidth = gp.tileSize*3;
		int frameHeight = gp.tileSize*4;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		// TEXT
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		int textX = frameX + 24;
		int textY = frameY + gp.tileSize+12;
		final int lineHeight = 35;
		
		//System.out.println("commandNum: "+commandNum);
		
		// NAMES
		g2.drawString("INFO", textX+25, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX, textY);
			if(gp.keyH.enterPressed == true) {
				classPicked = "Guardian";
				gp.ui.subState = 4;
			}
		}
		textY += gp.tileSize;
		g2.drawString("Select", textX+25, textY);
		if(commandNum == 1) {
			g2.drawString(">", textX, textY);
			if(gp.keyH.enterPressed) {
				classPicked = "Guardian";
				gp.ui.subState = 5;
			}
		}
		textY += gp.tileSize;
		g2.drawString("Back", textX+25, textY);
		if(commandNum == 2) {
			g2.drawString(">", textX, textY);
			if(gp.keyH.enterPressed == true) {
				classPicked = null;
				gp.gameState = gp.playState;
				commandNum = 0;
			}
		}
	}
	public void character_pickSpecialist(int frameX, int frameY) {
		
		classPicked = "Specialist";
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		int frameWidth = gp.tileSize*3;
		int frameHeight = gp.tileSize*4;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		// TEXT
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		int textX = frameX + 24;
		int textY = frameY + gp.tileSize+12;
		final int lineHeight = 35;
		
		//System.out.println("commandNum: "+commandNum);
		
		// NAMES
		g2.drawString("INFO", textX+25, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX, textY);
			if(gp.keyH.enterPressed == true) {
				classPicked = "Specialist";
				gp.ui.subState = 4;
			}
		}
		textY += gp.tileSize;
		g2.drawString("Select", textX+25, textY);
		if(commandNum == 1) {
			g2.drawString(">", textX, textY);
			if(gp.keyH.enterPressed) {
				classPicked = "Specialist";
				gp.ui.subState = 5;
			}
		}
		textY += gp.tileSize;
		g2.drawString("Back", textX+25, textY);
		if(commandNum == 2) {
			g2.drawString(">", textX, textY);
			if(gp.keyH.enterPressed == true) {
				classPicked = null;
				gp.gameState = gp.playState;
				commandNum = 0;
			}
		}
	}
	public void character_pickWarrior(int frameX, int frameY) {
		
		classPicked = "Warrior";
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		int frameWidth = gp.tileSize*3;
		int frameHeight = gp.tileSize*4;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		// TEXT
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		int textX = frameX + 24;
		int textY = frameY + gp.tileSize+12;
		final int lineHeight = 35;
		
		//System.out.println("commandNum: "+commandNum);
		
		// NAMES
		g2.drawString("INFO", textX+25, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX, textY);
			if(gp.keyH.enterPressed == true) {
				classPicked = "Warrior";
				gp.ui.subState = 4;
			}
		}
		textY += gp.tileSize;
		g2.drawString("Select", textX+25, textY);
		if(commandNum == 1) {
			g2.drawString(">", textX, textY);
			if(gp.keyH.enterPressed) {
				classPicked = "Warrior";
				gp.ui.subState = 5;
			}
		}
		textY += gp.tileSize;
		g2.drawString("Back", textX+25, textY);
		if(commandNum == 2) {
			g2.drawString(">", textX, textY);
			if(gp.keyH.enterPressed == true) {
				classPicked = null;
				gp.gameState = gp.playState;
				commandNum = 0;
			}
		}
	}
	public void drawPickableClasses(int frameX, int frameY) {
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		int frameWidth = gp.tileSize*8;
		int frameHeight = gp.tileSize*10;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		int textX;
		int textY;
		
		// TITLE
		String text = classPicked+" Emblem";
		textX = getXForCenteredText(text);
		textY = frameY + gp.tileSize;
		g2.drawString(text, textX, textY);
		
		textX = frameX + gp.tileSize;
		textY = frameY + gp.tileSize*2;
		
		String[] options = {
				"Priority: Range Attak & Ammo\nSpecial Item: Marksman Bow\n\nIt has infinite durability.\nShoots far, fast, strong,\nand deals knockback.\nYou can only pick 1 class!",
				"Priority: Defense & Life\nSpecial Item: Guardian Field\n\nNegates knockback,\nprojectiles, & halves\ndamage from all enemies\nincluding bosses.\nYou can only pick 1 class!",
				"Priority: Attack & Ammo\nSpecial Item: Special Cloak\n\nEnables invisibility from\nenemies and NPCs. Also\nincreases player speed.\nYou can only pick 1 class!",
				"Priority: Attack & Life\nSpecial Item: Warrior Sword\n\nIt has infinite durability.\nDoes strong knockback &\ndeals damage + equipped\nweapon's damage.\nYou can only pick 1 class!"};
		
		if(classPicked.equals("Ranger")) {
			currentDialogue = options[0]; // BeginnersIsle
		}
		if(classPicked.equals("Guardian")) {
			currentDialogue = options[1]; // Misty Meadows
		}
		if(classPicked.equals("Specialist")) {
			currentDialogue = options[2]; // Boss map
		}
		if(classPicked.equals("Warrior")) {
			currentDialogue = options[3]; // Dungeon01
		}
		
		for(String line: currentDialogue.split("\n")) {
			g2.drawString(line, textX, textY);
			textY += 40;
		}
		
		// NO
		text = "Back";
		textX = getXForCenteredText(text)-gp.tileSize*2;
		textY = frameY + gp.tileSize*9;
		g2.drawString(text, textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				
				// VERY IMPORTANT CODE HERE!!! FIXES THE BUG!
				if(classPicked.equals("Ranger")) {
					subState = 0;
				}
				if(classPicked.equals("Guardian")) {
					subState = 1;
				}
				if(classPicked.equals("Specialist")) {
					subState = 2;
				}
				if(classPicked.equals("Warrior")) {
					subState = 3;
				}
				commandNum = 0;
			}
		}
	}
	public void rUSurePickClass(int frameX, int frameY) {
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		int frameWidth = gp.tileSize*8;
		int frameHeight = gp.tileSize*10;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		int textX;
		int textY;
		
		// TITLE
		String text = classPicked+" Emblem";
		textX = getXForCenteredText(text);
		textY = frameY + gp.tileSize;
		g2.drawString(text, textX, textY);
		
		textX = frameX + gp.tileSize;
		textY = frameY + gp.tileSize*2;
		
		String[] options = {
				"Are you sure you want to\njoin the Ranger class?",
				"Are you sure you want to\njoin the Guardian class?",
				"Are you sure you want to\njoin the Specialist class?",
				"Are you sure you want to\njoin the Warrior class?"};
		
		if(classPicked.equals("Ranger")) {
			currentDialogue = options[0]; // BeginnersIsle
		}
		if(classPicked.equals("Guardian")) {
			currentDialogue = options[1]; // Misty Meadows
		}
		if(classPicked.equals("Specialist")) {
			currentDialogue = options[2]; // Boss map
		}
		if(classPicked.equals("Warrior")) {
			currentDialogue = options[3]; // Dungeon01
		}
		
		for(String line: currentDialogue.split("\n")) {
			g2.drawString(line, textX, textY);
			textY += 40;
		}
		
		if(classPicked.equals("Ranger")) {
			// YES
			text = "Yes";
			textX = getXForCenteredText(text)-gp.tileSize*2;
			textY = frameY + gp.tileSize*8;
			g2.drawString(text, textX, textY);
			if(commandNum == 0) {
				g2.drawString(">", textX-25, textY);
				if(gp.keyH.enterPressed == true) {
					
					gp.player.ranger = true;
					gp.playSE(41);
					gp.player.canObtainItem(new OBJ_Emblem_Ranger(gp));
					classPicked = "Ranger";
					gp.player.currentClass = "ranger";
					gp.ui.subState = 6;
				}
			}
			// NO
			text = "No";
			textY += gp.tileSize;
			g2.drawString(text, textX, textY);
			if(commandNum == 1) {
				g2.drawString(">", textX-25, textY);
				if(gp.keyH.enterPressed == true) {
					commandNum = 1;
					subState = 0;
				}
			}
		}
		if(classPicked.equals("Guardian")) {
			// YES
			text = "Yes";
			textX = getXForCenteredText(text)-gp.tileSize*2;
			textY = frameY + gp.tileSize*8;
			g2.drawString(text, textX, textY);
			if(commandNum == 0) {
				g2.drawString(">", textX-25, textY);
				if(gp.keyH.enterPressed == true) {
					
					gp.player.guardian = true;
					gp.playSE(41);
					gp.player.canObtainItem(new OBJ_Emblem_Guardian(gp));
					classPicked = "Guardian";
					gp.player.currentClass = "guardian";
					gp.ui.subState = 6;
				}
			}
			// NO
			text = "No";
			textY += gp.tileSize;
			g2.drawString(text, textX, textY);
			if(commandNum == 1) {
				g2.drawString(">", textX-25, textY);
				if(gp.keyH.enterPressed == true) {
					commandNum = 1;
					subState = 1;
				}
			}
		}
		if(classPicked.equals("Specialist")) {
			// YES
			text = "Yes";
			textX = getXForCenteredText(text)-gp.tileSize*2;
			textY = frameY + gp.tileSize*8;
			g2.drawString(text, textX, textY);
			if(commandNum == 0) {
				g2.drawString(">", textX-25, textY);
				if(gp.keyH.enterPressed == true) {

					gp.player.specialist = true;
					gp.playSE(41);
					gp.player.canObtainItem(new OBJ_Emblem_Specialist(gp));
					classPicked = "Specialist";
					gp.player.currentClass = "specialist";
					gp.ui.subState = 6;
				}
			}
			// NO
			text = "No";
			textY += gp.tileSize;
			g2.drawString(text, textX, textY);
			if(commandNum == 1) {
				g2.drawString(">", textX-25, textY);
				if(gp.keyH.enterPressed == true) {
					commandNum = 1;
					subState = 2;
				}
			}
		}
		if(classPicked.equals("Warrior")) {
			// YES
			text = "Yes";
			textX = getXForCenteredText(text)-gp.tileSize*2;
			textY = frameY + gp.tileSize*8;
			g2.drawString(text, textX, textY);
			if(commandNum == 0) {
				g2.drawString(">", textX-25, textY);
				if(gp.keyH.enterPressed == true) {
					
					gp.player.warrior = true;
					gp.playSE(41);
					gp.player.canObtainItem(new OBJ_Emblem_Warrior(gp));
					classPicked = "Warrior";
					gp.player.currentClass = "warrior";
					gp.ui.subState = 6;
				}
			}
			// NO
			text = "No";
			textY += gp.tileSize;
			g2.drawString(text, textX, textY);
			if(commandNum == 1) {
				g2.drawString(">", textX-25, textY);
				if(gp.keyH.enterPressed == true) {
					commandNum = 1;
					subState = 3;
				}
			}
		}
		
	}
	public void pickedClassComplete(int frameX, int frameY) {
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		int frameWidth = gp.tileSize*12;
		int frameHeight = gp.tileSize*9;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		int textX;
		int textY;
		
		// TITLE
		String text = "You are now a";
		textX = getXForCenteredText(text);
		textY = gp.tileSize*2 + gp.tileSize;
		g2.drawString(text, textX, textY);
		
		g2.setFont(g2.getFont().deriveFont(128F));
		text = classPicked+"!";
		textX = getXForCenteredText(text);
		textY = gp.tileSize*5 + gp.tileSize;
		g2.drawString(text, textX, textY);
		
		g2.setFont(g2.getFont().deriveFont(32F));
		text = "[Shift] to continue";
		textX = getXForCenteredText(text);
		textY = gp.tileSize*7 + gp.tileSize;
		g2.drawString(text, textX, textY);
		
		if(gp.keyH.shiftPressed) {
			
			gp.obj[7][0] = null;
			gp.obj[7][1] = null;
			gp.obj[7][2] = null;
			gp.obj[7][3] = null;
			
			gp.ui.subState = 0;
			gp.ui.commandNum = 0;
			gp.gameState = gp.playState;
		}
	}

	
	// Traveler BOT's TRAVEL STATE
	public void options_top_travel(int frameX, int frameY) {
		
		int textX;
		int textY;
		
		// TITLE
		String text = "Map Options";
		textX = getXForCenteredText(text);
		textY = frameY + gp.tileSize;
		g2.drawString(text, textX, textY);
		
		// World 1, Map 0
		textX -= gp.tileSize;
		textY += gp.tileSize+24;
		g2.drawString("Beginner's Isle", textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 1;
				commandNum = 0;
			}
		}
		
		// World 2, Map 1
		textY += gp.tileSize;
		g2.drawString("Frosty Forest", textX, textY);
		if(commandNum == 1) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 2;
				commandNum = 0;
			}
		}
		
		// World 3, Map 5
		textY += gp.tileSize;
		g2.drawString("Final Island", textX, textY);
		if(commandNum == 2) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 3;
				commandNum = 0;
			}
		}
		
		// HOME MAP
		textY += gp.tileSize;
		g2.drawString("Home", textX, textY);
		if(commandNum == 3) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 4;
				commandNum = 0;
			}
		}
		
		// BACK
		textY += gp.tileSize+24;
		g2.drawString("Back", textX, textY);
		if(commandNum == 4) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				gp.gameState = gp.playState;
				commandNum = 0;
			}
		}
		
		//gp.config.saveConfig();
	}
	public void options_map1Confirmation(int frameX, int frameY) { // Beginner's Isle so really it's map 0
		
		boolean canTravel = false;
		for(int i = 0; i<gp.player.inventory.size(); i++) {
			if(gp.player.inventory.get(i).name.equals("Travel Token")) {
				if(gp.player.inventory.get(i).amount >= 1) {
					canTravel = true;
				}
			}
		}
		
		int textX = frameX + gp.tileSize;
		int textY = frameY + gp.tileSize*3;
		
		currentDialogue = "Travel to Beginner's Isle?";
		
		for(String line: currentDialogue.split("\n")) {
			g2.drawString(line, textX, textY);
			textY += 40;
		}
		
		// YES
		String text = "Yes";
		textX = getXForCenteredText(text);
		textY += gp.tileSize*3;
		g2.drawString(text, textX, textY);
		if(commandNum == 0) {
			
			
			
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				
				if(gp.currentMap == 0) {
					npc.startDialogue(npc, 1);
				}
				else if(canTravel){
					gp.currentMap = 0;
					gp.npc[0][1].clearItems();
					gp.npc[0][1].setItems();
					
					gp.eHandler.teleport(0, 23, 21,gp.outside);
					gp.stopMusic();
					if(gp.dayState == gp.night || gp.dayState == gp.dawn) {
						gp.playMusic(49);
					}
					else {
						gp.playMusic(46);
					}
				}
				else {
					npc.startDialogue(npc, 0);
				}
				
				subState = 0;
				commandNum = 0;
				
			}
		}
		
		// NO
		text = "No";
		textX = getXForCenteredText(text);
		textY += gp.tileSize;
		g2.drawString(text, textX, textY);
		if(commandNum == 1) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				
				subState = 0;
				
				
				commandNum = 0;
			}
		}
	}
	public void options_map2Confirmation(int frameX, int frameY) { // Misty Meadows so really it's map 1
		
		boolean canTravel = false;
		for(int i = 0; i<gp.player.inventory.size(); i++) {
			if(gp.player.inventory.get(i).name.equals("Travel Token")) {
				if(gp.player.inventory.get(i).amount >= 2) {
					canTravel = true;
				}
			}
		}
		
		int textX = frameX + gp.tileSize;
		int textY = frameY + gp.tileSize*3;
		
		currentDialogue = "Travel to Frosty Forest?";
		
		for(String line: currentDialogue.split("\n")) {
			g2.drawString(line, textX, textY);
			textY += 40;
		}
		
		// YES
		String text = "Yes";
		textX = getXForCenteredText(text);
		textY += gp.tileSize*3;
		g2.drawString(text, textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				
				if(gp.currentMap == 1) {
					npc.startDialogue(npc, 1);
				}
				else if(canTravel){
					gp.currentMap = 0;
					gp.npc[1][0].clearItems();
					gp.npc[1][0].setItems();
					
					gp.eHandler.teleport(1, 49, 49,gp.outside);
					gp.stopMusic();
					if(gp.dayState == gp.night || gp.dayState == gp.dawn) {
						gp.playMusic(49);
					}
					else {
						gp.playMusic(29);
					}
				}
				else {
					npc.startDialogue(npc, 3);
				}
				
				subState = 0;
				commandNum = 0;
			}
		}
		
		// NO
		text = "No";
		textX = getXForCenteredText(text);
		textY += gp.tileSize;
		g2.drawString(text, textX, textY);
		if(commandNum == 1) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				
				
				
				subState = 0;
				commandNum = 1;
			}
		}
	}
	public void options_map3Confirmation(int frameX, int frameY) { // Final Island so really it's map 5
		
		boolean canTravel = false;
		for(int i = 0; i<gp.player.inventory.size(); i++) {
			if(gp.player.inventory.get(i).name.equals("Travel Token")) {
				if(gp.player.inventory.get(i).amount >= 3) {
					canTravel = true;
				}
			}
		}
		
		int textX = frameX + gp.tileSize;
		int textY = frameY + gp.tileSize*3;
		
		currentDialogue = "Travel to Final Island?";
		
		for(String line: currentDialogue.split("\n")) {
			g2.drawString(line, textX, textY);
			textY += 40;
		}
		
		// YES
		String text = "Yes";
		textX = getXForCenteredText(text);
		textY += gp.tileSize*3;
		g2.drawString(text, textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				
				if(gp.currentMap == 5) {
					npc.startDialogue(npc, 1);
				}
				else if(canTravel){
					gp.currentMap = 5;
					gp.npc[5][0].clearItems();
					gp.npc[5][0].setItems();
					
					gp.eHandler.teleport(5, 21, 85,gp.outside);
					gp.stopMusic();
					if(gp.dayState == gp.night || gp.dayState == gp.dawn) {
						gp.playMusic(49);
					}
					else {
						gp.playMusic(65);
					}
				}
				else {
					npc.startDialogue(npc, 2);
				}
				
				subState = 0;
				commandNum = 0;
			}
		}
		
		// NO
		text = "No";
		textX = getXForCenteredText(text);
		textY += gp.tileSize;
		g2.drawString(text, textX, textY);
		if(commandNum == 1) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				
				
				
				subState = 0;
				commandNum = 2;
			}
		}
	}
	public void options_mapHOMEConfirmation(int frameX, int frameY) { // Home World Map, so map 6
		
		npc.dialogueSet = 0;
		
		int textX = frameX + gp.tileSize*2+24;
		int textY = frameY + gp.tileSize*3;
		
		currentDialogue = "Travel Home?";
		
		for(String line: currentDialogue.split("\n")) {
			g2.drawString(line, textX, textY);
			textY += 40;
		}
		
		// YES
		String text = "Yes";
		textX = getXForCenteredText(text);
		textY += gp.tileSize*3;
		g2.drawString(text, textX, textY);
		if(commandNum == 0) {
			
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				if(gp.currentMap == 6) {
					npc.startDialogue(npc, 1);
				}
				else {
					gp.currentMap = 0;
					gp.npc[0][1].clearItems();
					gp.npc[0][1].setItems();
					
					gp.eHandler.teleport(6, 49, 49,gp.outside);
					gp.stopMusic();
					if(gp.dayState == gp.night || gp.dayState == gp.dawn) {
						gp.playMusic(49);
					}
					else {
						gp.playMusic(43);
					}
				}
				
				subState = 0;
				commandNum = 0;
				
			}
		}
		
		// NO
		text = "No";
		textX = getXForCenteredText(text);
		textY += gp.tileSize;
		g2.drawString(text, textX, textY);
		if(commandNum == 1) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				
				subState = 0;
				commandNum = 0;
			}
		}
	}
	
	// QUEST SCREENS

	// ALL HERO QUEST STATES
	public void options_top_quest(int frameX, int frameY) {
		
		int textX;
		int textY;
		
		// TITLE
		String text = gp.mapNames[gp.currentMap]+" Quests";
		textX = getXForCenteredText(text);
		textY = frameY + gp.tileSize;
		g2.drawString(text, textX, textY);
		//rewrite to fit
		text = "Quests";
		textX = getXForCenteredText(text);
		textY = frameY + gp.tileSize;
		
		textX -= gp.tileSize*2;
		textY += gp.tileSize;
		g2.drawString("Level", textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 1;
				commandNum = 0;
			}
		}
		
		textY += gp.tileSize;
		g2.drawString("Boss", textX, textY);
		if(commandNum == 1) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 2;
				commandNum = 0;
			}
		}
		
		textY += gp.tileSize;
		g2.drawString("Collect", textX, textY);
		if(commandNum == 2) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 3;
				commandNum = 0;
			}
		}
		
		textY += gp.tileSize;
		g2.drawString("Enemy", textX, textY);
		if(commandNum == 3) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 4;
				commandNum = 0;
			}
		}
		
//		textY += gp.tileSize;
//		g2.drawString("Escort", textX, textY);
//		if(commandNum == 4) {
//			g2.drawString(">", textX-25, textY);
//			if(gp.keyH.enterPressed == true) {
//				subState = 5;
//				commandNum = 0;
//			}
//		}
//		
//		textY += gp.tileSize;
//		g2.drawString("Delivery", textX, textY);
//		if(commandNum == 5) {
//			g2.drawString(">", textX-25, textY);
//			if(gp.keyH.enterPressed == true) {
//				subState = 6;
//				commandNum = 0;
//			}
//		}
		
		// BACK
		textY += gp.tileSize*2;
		g2.drawString("Back", textX, textY);
		if(commandNum == 4) { // from 6 down to 4..
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				gp.gameState = gp.playState;
				commandNum = 0;
			}
		}
		
		
		textX = getXForCenteredText(text)+gp.tileSize+24;
		textY = frameY + gp.tileSize*2;
		
		if(gp.player.qH.levelQuestComplete(gp.currentMap)) {
			g2.drawString("Complete", textX, textY);
		}
		else {
			g2.drawString("Incomplete", textX, textY);
		}
		
		textY += gp.tileSize;
		if(gp.player.qH.bossQuestComplete(gp.currentMap)) {
			g2.drawString("Complete", textX, textY);
		}
		else {
			g2.drawString("Incomplete", textX, textY);
		}
		
		textY += gp.tileSize;
		if(gp.player.qH.collectorQuestComplete(gp.currentMap)) {
			g2.drawString("Complete", textX, textY);
		}
		else {
			g2.drawString("Incomplete", textX, textY);
		}
		
		textY += gp.tileSize;
		if(gp.player.qH.enemyQuestComplete(gp.currentMap)) {
			g2.drawString("Complete", textX, textY);
			
		}
		else {
			g2.drawString("Incomplete", textX, textY);
		}
		
//		textY += gp.tileSize;
//		if(escortQuestComplete == true) {g2.drawString("Complete", textX, textY);}
//		else {g2.drawString("Incomplete", textX, textY);}
//		
//		textY += gp.tileSize;
//		if(deliveryQuestComplete == true) {g2.drawString("Complete", textX, textY);}
//		else {g2.drawString("Incomplete", textX, textY);}
	}
	public void options_levelConfirmation(int frameX, int frameY) {
		
		int textX;
		int textY;
		
		// TITLE
		String text = "Level Quest";
		textX = getXForCenteredText(text);
		textY = frameY + gp.tileSize;
		g2.drawString(text, textX, textY);
		
		textX = frameX + gp.tileSize;
		textY = frameY + gp.tileSize*2;
		
		String[] options = {"Level up to 5.","Level up to 10.","Level up to 20.","Level up to 30."};
		
		if(gp.currentMap == 0) {
			currentDialogue = options[1]; // BeginnersIsle
		}
		else if(gp.currentMap == 1) {
			currentDialogue = options[2]; // Misty Meadows
		}
		else if(gp.currentMap == 2) {
			currentDialogue = options[2]; // Boss map
		}
		else if(gp.currentMap == 3) {
			currentDialogue = options[1]; // Dungeon01
		}
		else if(gp.currentMap == 4) {
			currentDialogue = options[1]; // Dungeon02
		}
		else if(gp.currentMap == 5) {
			currentDialogue = options[3]; // Final Island
		}
		else if(gp.currentMap == 6) {
			currentDialogue = options[0]; // Home world
		}
		else if(gp.currentMap == 7) {
			currentDialogue = options[0]; // Home world
		}
		
		for(String line: currentDialogue.split("\n")) {
			g2.drawString(line, textX, textY);
			textY += 40;
		}
		
		// NO
		text = "Back";
		textX = getXForCenteredText(text)-gp.tileSize*2;
		textY = frameY + gp.tileSize*9;
		g2.drawString(text, textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				
				subState = 0;
				
				
				commandNum = 0;
			}
		}
	}
	public void options_bossConfirmation(int frameX, int frameY) {
		
		int textX;
		int textY;
		
		// TITLE
		String text = "Boss Quest";
		textX = getXForCenteredText(text);
		textY = frameY + gp.tileSize;
		g2.drawString(text, textX, textY);
		
		textX = frameX + gp.tileSize;
		textY = frameY + gp.tileSize*2;
		
		String[] options = {"Defeat Giant Slime.","Defeat Skeleton Lord.","Defeat Pirate King.","Defeat Samurai."};
		
		if(gp.currentMap == 0) {
			currentDialogue = options[1]; // BeginnersIsle
		}
		else if(gp.currentMap == 1) {
			currentDialogue = options[2]; // Misty Meadows
		}
		else if(gp.currentMap == 2) {
			currentDialogue = options[2]; // Boss map
		}
		else if(gp.currentMap == 3) {
			currentDialogue = options[1]; // Dungeon01
		}
		else if(gp.currentMap == 4) {
			currentDialogue = options[1]; // Dungeon02
		}
		else if(gp.currentMap == 5) {
			currentDialogue = options[3]; // Final Island
		}
		else if(gp.currentMap == 6) {
			currentDialogue = options[0]; // Home world
		}
		else if(gp.currentMap == 7) {
			currentDialogue = options[0]; // Home world
		}
		
		for(String line: currentDialogue.split("\n")) {
			g2.drawString(line, textX, textY);
			textY += 40;
		}
		
		// NO
		text = "Back";
		textX = getXForCenteredText(text)-gp.tileSize*2;
		textY = frameY + gp.tileSize*9;
		g2.drawString(text, textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				
				subState = 0;
				
				
				commandNum = 1;
			}
		}
	}
	public void options_collectConfirmation(int frameX, int frameY) {
		
		int textX;
		int textY;
		
		// TITLE
		String text = "Collector's Quest";
		textX = getXForCenteredText(text);
		textY = frameY + gp.tileSize;
		g2.drawString(text, textX, textY);
		
		textX = frameX + gp.tileSize;
		textY = frameY + gp.tileSize*2;
		
		String[] options = {"Find a blue sword.","Collect your class Special\nItem.","Get 5 max red potions","Collect OVER 9,000 coins."};
		
		if(gp.currentMap == 0) {
			currentDialogue = options[1]; // BeginnersIsle
		}
		else if(gp.currentMap == 1) {
			currentDialogue = options[2]; // Misty Meadows
		}
		else if(gp.currentMap == 2) {
			currentDialogue = options[2]; // Boss map
		}
		else if(gp.currentMap == 3) {
			currentDialogue = options[1]; // Dungeon01
		}
		else if(gp.currentMap == 4) {
			currentDialogue = options[1]; // Dungeon02
		}
		else if(gp.currentMap == 5) {
			currentDialogue = options[3]; // Final Island
		}
		else if(gp.currentMap == 6) {
			currentDialogue = options[0]; // Home world
		}
		else if(gp.currentMap == 7) {
			currentDialogue = options[0]; // Home world
		}
		
		for(String line: currentDialogue.split("\n")) {
			g2.drawString(line, textX, textY);
			textY += 40;
		}
		
		// NO
		text = "Back";
		textX = getXForCenteredText(text)-gp.tileSize*2;
		textY = frameY + gp.tileSize*9;
		g2.drawString(text, textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				
				subState = 0;
				
				
				commandNum = 2;
			}
		}
	}
	public void options_enemyConfirmation(int frameX, int frameY) {
		
		int textX;
		int textY;
		
		// TITLE
		String text = "Enemy Quest";
		textX = getXForCenteredText(text);
		textY = frameY + gp.tileSize;
		g2.drawString(text, textX, textY);
		
		textX = frameX + gp.tileSize;
		textY = frameY + gp.tileSize*2;
		
		String[] options = {"Beat 3 Orcs.","Beat 5 Blue Slimes.","Beat 3 Frost Orcs.","Beat 5 Elite Skeletons."};
		
		if(gp.currentMap == 0) {
			currentDialogue = options[1]; // BeginnersIsle
		}
		else if(gp.currentMap == 1) {
			currentDialogue = options[2]; // Misty Meadows
		}
		else if(gp.currentMap == 2) {
			currentDialogue = options[2]; // Boss map
		}
		else if(gp.currentMap == 3) {
			currentDialogue = options[1]; // Dungeon01
		}
		else if(gp.currentMap == 4) {
			currentDialogue = options[1]; // Dungeon02
		}
		else if(gp.currentMap == 5) {
			currentDialogue = options[3]; // Final Island
		}
		else if(gp.currentMap == 6 || gp.currentMap == 7) {
			currentDialogue = options[0]; // Home world
		}
		
		for(String line: currentDialogue.split("\n")) {
			g2.drawString(line, textX, textY);
			textY += 40;
		}
		
		// NO
		text = "Back";
		textX = getXForCenteredText(text)-gp.tileSize*2;
		textY = frameY + gp.tileSize*9;
		g2.drawString(text, textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				
				subState = 0;
				
				
				commandNum = 3;
			}
		}
	}
	public void options_escortConfirmation(int frameX, int frameY) { // UNUSED
		
		int textX;
		int textY;
		
		// TITLE
		String text = "Escorter's Quest";
		textX = getXForCenteredText(text);
		textY = frameY + gp.tileSize;
		g2.drawString(text, textX, textY);
		
		textX = frameX + gp.tileSize;
		textY = frameY + gp.tileSize*2;
		
		String[] options = {"Escort T-Bot to center","Escort Merchant to his\nshop","Escort Pixal from captivity","Escort Old Man to spawn", "You must complete all\nother quests first"};
		
		if(gp.currentMap == 0) {
			currentDialogue = options[1]; // BeginnersIsle
		}
		else if(gp.currentMap == 1) {
			currentDialogue = options[2]; // Misty Meadows
		}
		else if(gp.currentMap == 2) {
			currentDialogue = options[2]; // Boss map
		}
		else if(gp.currentMap == 3) {
			currentDialogue = options[1]; // Dungeon01
		}
		else if(gp.currentMap == 4) {
			currentDialogue = options[1]; // Dungeon02
		}
		else if(gp.currentMap == 5) {
			currentDialogue = options[3]; // Final Island
		}
		else if(gp.currentMap == 6 && escortQuestComplete == true) {
			currentDialogue = options[0]; // Home world
		}
		else if(gp.currentMap == 6 && escortQuestComplete == false) {
			currentDialogue = options[4]; // Home world
		}
		
		
		for(String line: currentDialogue.split("\n")) {
			g2.drawString(line, textX, textY);
			textY += 40;
		}
		
		// NO
		text = "Back";
		textX = getXForCenteredText(text)-gp.tileSize*2;
		textY = frameY + gp.tileSize*9;
		g2.drawString(text, textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				
				subState = 0;
				
				
				commandNum = 4;
			}
		}
	}
	public void options_deliverConfirmation(int frameX, int frameY) { // UNUSED
		
		int textX;
		int textY;
		
		// TITLE
		String text = "Courier's Quest";
		textX = getXForCenteredText(text);
		textY = frameY + gp.tileSize;
		g2.drawString(text, textX, textY);
		
		textX = frameX + gp.tileSize;
		textY = frameY + gp.tileSize*2;
		
		String[] options = {"Deliver mana potion to\nChad","Deliver tent to Matthias","Deliver skull to Daniel","Deliver key to Reuben"};
		
		if(gp.currentMap == 0) {
			currentDialogue = options[1]; // BeginnersIsle
		}
		else if(gp.currentMap == 1) {
			currentDialogue = options[2]; // Misty Meadows
		}
		else if(gp.currentMap == 2) {
			currentDialogue = options[2]; // Boss map
		}
		else if(gp.currentMap == 3) {
			currentDialogue = options[1]; // Dungeon01
		}
		else if(gp.currentMap == 4) {
			currentDialogue = options[1]; // Dungeon02
		}
		else if(gp.currentMap == 5) {
			currentDialogue = options[3]; // Final Island
		}
		else if(gp.currentMap == 6) {
			currentDialogue = options[0]; // Home world
		}
		
		for(String line: currentDialogue.split("\n")) {
			g2.drawString(line, textX, textY);
			textY += 40;
		}
		
		// NO
		text = "Back";
		textX = getXForCenteredText(text)-gp.tileSize*2;
		textY = frameY + gp.tileSize*9;
		g2.drawString(text, textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				
				subState = 0;
				
				
				commandNum = 5;
			}
		}
	}
	
	
	public void options_youSavedScreen(int frameX, int frameY) {
		
		int textX = frameX + gp.tileSize+24;
		int textY = frameY + gp.tileSize*3;
		
		currentDialogue = "Quick Save?";
		
		for(String line: currentDialogue.split("\n")) {
			g2.drawString(line, textX, textY);
			textY += 40;
		}
		
		// YES
		String text = "Yes";
		textX = getXForCenteredText(text);
		textY += gp.tileSize*3;
		g2.drawString(text, textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 0;
				gp.saveload.save();
				commandNum = 5;
				//currentDialogue = "You saved!";
				//g2.drawString(currentDialogue, textX, textY);
			}
		}
		
		// NO
		text = "No";
		textX = getXForCenteredText(text);
		textY += gp.tileSize;
		g2.drawString(text, textX, textY);
		if(commandNum == 1) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 0;
				commandNum = 5;
			}
		}
	}
	public boolean global_YesOrNo(int frameX, int frameY) { // UNUSED FOR NOW...
		
		int textX = frameX + gp.tileSize+24;
		int textY = frameY + gp.tileSize*3;
		
		currentDialogue = "Are you sure?";
		
		for(String line: currentDialogue.split("\n")) {
			g2.drawString(line, textX, textY);
			textY += 40;
		}
		
		// YES
		String text = "Yes";
		textX = getXForCenteredText(text);
		textY += gp.tileSize*3;
		g2.drawString(text, textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 0;
				commandNum = 0;
				//currentDialogue = "You saved!";
				//g2.drawString(currentDialogue, textX, textY);
				return true;
			}
		}
		
		// NO
		text = "No";
		textX = getXForCenteredText(text);
		textY += gp.tileSize;
		g2.drawString(text, textX, textY);
		if(commandNum == 1) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 0;
				commandNum = 0;
				return false;
			}
		}
		
		return false;
	}
	public void drawTransition() {
		
		counter++;
		g2.setColor(new Color(counter,counter,counter));
		g2.fillRect(0,0,gp.screenWidth, gp.screenHeight);
		
		g2.setColor(Color.black);
		g2.setFont(g2.getFont().deriveFont(64F));
		int x = getXForCenteredText("Loading...");
		g2.drawString("Loading...",x, gp.tileSize*6);
		
		if(counter == 60) {
			gp.currentMap = gp.eHandler.tempMap;
			gp.player.worldX = gp.tileSize * gp.eHandler.tempCol;
			gp.player.worldY = gp.tileSize * gp.eHandler.tempRow;
			gp.eHandler.previousEventX = gp.player.worldX;
			gp.eHandler.previousEventY = gp.player.worldY;
			gp.changeArea();
		}
		
		if(counter == 240) { // Where the transition is done, 4 seconds loading screen is needed!!!
			counter = 0;
			if(gp.keyH.tutorialOn) {
				gp.player.startDialogue(gp.player,1);
			}
			else {
				gp.saveload.save();
				gp.gameState = gp.playState;
			}
		}
	}
	public void drawSpawnerTransition() {
		
		counter++;
		g2.setColor(new Color(counter,counter,counter));
		g2.fillRect(0,0,gp.screenWidth, gp.screenHeight);
		
		g2.setColor(Color.black);
		g2.setFont(g2.getFont().deriveFont(64F));
		int x = getXForCenteredText("Spawning...");
		g2.drawString("Spawning...",x, gp.tileSize*6);
		
		if(counter == 60) {
			gp.aSetter.setMonster();
		}
		
		if(counter == 240) { // Where the transition is done, 4 seconds loading screen is needed!!!
			counter = 0;
			gp.eHandler.spawnerPressed = false;
			Entity m1 = new OBJ_Monster_Spawner(gp);
			gp.saveload.save();
			m1.startDialogue(m1,0);
			gp.playSE(30);
		}
	}
	public void drawTradeScreen() {
		
		switch(subState) {
		case 0: trade_select(); break;
		case 1: trade_buy(); break;
		case 2: trade_sell(); break;
		}
		gp.keyH.enterPressed = false;
	}
	public void trade_select() {
		
		
		drawDialogueScreen();
		
		
		
		// DRAW MINI SUB WINDOW
		int x = gp.tileSize * 14;
		int y = gp.tileSize * 5;
		int width = gp.tileSize * 3;
		int height = (int)(gp.tileSize * 3.5);
		drawSubWindow(x,y,width,height);
		
		// DRAW TEXTS
		x += gp.tileSize;
		y += gp.tileSize;
		g2.drawString("Buy", x, y);
		if(commandNum == 0) {
			g2.drawString(">", x-24,y);
			if(gp.keyH.enterPressed) {
				subState = 1;
			}
		}
		y+= gp.tileSize;
		
		g2.drawString("Sell", x, y);
		if(commandNum == 1) {
			g2.drawString(">", x-24,y);
			if(gp.keyH.enterPressed) {
				subState = 2;
			}
		}
		y+= gp.tileSize;
		
		g2.drawString("Leave", x, y);
		if(commandNum == 2) {
			g2.drawString(">", x-24,y);
			if(gp.keyH.enterPressed) {
				commandNum = 0;
				npc.startDialogue(npc,1);
			}
		}
		
	}
	public void trade_buy() {
		
		// DRAW NPC INVENTORY
		drawInventory(npc, true);
		
		// DRAW PLAYER INVENTORY // inventory's in this order so none overlaps
		drawInventory(gp.player, false);
		
		// DRAW HINT WINDOW
		int x = gp.tileSize*2;
		int y = gp.tileSize*10;
		int width = gp.tileSize*3;
		int height = gp.tileSize*1;
		//drawSubWindow(x,y,width,height);
		
		
		// DRAW PLAYER COIN WINDOW
//		x = gp.tileSize*12;
//		y = gp.tileSize*9;
//		width = gp.tileSize*6;
//		height = gp.tileSize*2;
//		drawSubWindow(x,y,width,height);
//		g2.drawString("Your Coins: " + gp.player.coin, x+24,y+60);
		x = gp.tileSize*12;
		y = gp.tileSize*6;
		width = gp.tileSize*6;
		height = gp.tileSize*4;
		drawSubWindow(x,y,width,height);
		g2.drawImage(coin, x+16, y+8, 32, 32, null);
		g2.drawString("Player coins: "+gp.player.coin, x+48,y+33);
		y+=gp.tileSize*3;
		g2.drawString("[ESC] Back", x+24,y+33);
		y-=gp.tileSize+24;
		
		// DRAW PRICE WINDOW
		int itemIndex = getItemIndexOnSlot(npcSlotCol, npcSlotRow);
		if(itemIndex < npc.inventory.size()) {
			
//			x = (int)(gp.tileSize*5.5);
//			y = (int)(gp.tileSize*10);
//			width = (int)(gp.tileSize*2.5);
//			height = gp.tileSize;
			//drawSubWindow(x,y,width,height);
			g2.drawImage(coin, x+16, y-26, 32, 32, null);
			
			int price = npc.inventory.get(itemIndex).price;
			String text = "Item price: "+price;
			//x = getXForAlignToRightText(text, gp.tileSize*8-20);
			g2.drawString(text,x+48,y);//+34);
			
			// BUY AN ITEM
			if(gp.keyH.enterPressed == true) {
				if(npc.inventory.get(itemIndex).price > gp.player.coin) {
					subState = 0;
					//System.out.println("here");
					npc.startDialogue(npc,2);
				}
				else {
					if(gp.player.canObtainItem(npc.inventory.get(itemIndex)) == true) {
						gp.player.coin -= npc.inventory.get(itemIndex).price;
						gp.playSE(1);
					}
					else {
						subState = 0;
						npc.startDialogue(npc,3);
					}
				}
				
				if(qH.collectorQuestComplete(gp.currentMap) && gp.player.collectorQuestCounter.get(gp.currentMap) == false) {
					gp.playSE(55);
					gp.ui.addMessage("Collector Quest Complete!");
					gp.player.collectorQuestCounter.replace(gp.currentMap, true); // makes it so this notification only happens once
					gp.player.checkAllQuests(gp.currentMap);
				}
//				else if(gp.player.inventory.size() == gp.player.maxInventorySize) {
//					gp.gameState = gp.dialogueState;
//					currentDialogue = "You c-cannot c-carry any more!";
//					//drawDialogueScreen();
//				}
//				else {
//					gp.player.coin -= npc.inventory.get(itemIndex).price;
//					gp.player.inventory.add(npc.inventory.get(itemIndex));
//					gp.playSE(1);
//				}
			}
		}
	}
	public void trade_sell() {
		
		
		drawInventory(npc, false);
		drawInventory(gp.player, true);
		// DRAW PLAYER INVENTORY
		//inventoryBug = true;
		
		
		
		int x;
		int y;
		int width;
		int height;
		
//		// DRAW HINT WINDOW
//				x = gp.tileSize*2;
//				y = gp.tileSize*9;
//				width = gp.tileSize*6;
//				height = gp.tileSize*2;
//				drawSubWindow(x,y,width,height);
//				g2.drawString("[ESC] Back", x+24,y+60);
		// DRAW HINT WINDOW
				x = gp.tileSize*2;
				y = gp.tileSize*10;
				width = gp.tileSize*3;
				height = gp.tileSize*1;
				drawSubWindow(x,y,width,height);
				g2.drawString("[ESC] Back", x+14,y+33);
				
//				// DRAW PLAYER COIN WINDOW
//				x = gp.tileSize*12;
//				y = gp.tileSize*9;
//				width = gp.tileSize*6;
//				height = gp.tileSize*2;
//				drawSubWindow(x,y,width,height);
//				g2.drawString("Your Coins: " + gp.player.coin, x+24,y+60);
				x = gp.tileSize*12;
				y = gp.tileSize*10;
				width = gp.tileSize*6;
				height = gp.tileSize*1;
				drawSubWindow(x,y,width,height);
				g2.drawString("Your coins: "+gp.player.coin, x+14,y+33);
				
				// DRAW PRICE WINDOW
				int itemIndex = getItemIndexOnSlot(playerSlotCol, playerSlotRow);
				if(itemIndex < gp.player.inventory.size()) {
					
					x = (int)(gp.tileSize*15.5);
					y = (int)(gp.tileSize*5.5);
					width = (int)(gp.tileSize*2.5);
					height = gp.tileSize;
					drawSubWindow(x,y,width,height);
					g2.drawImage(coin, x+10, y+8, 32, 32, null);
					
					int price = gp.player.inventory.get(itemIndex).price/2;
					String text = ""+price;
					x = getXForAlignToRightText(text, gp.tileSize*18-20);
					g2.drawString(text,x,y+34);
					
					// SELL AN ITEM
					if(gp.keyH.enterPressed == true) {
						
						if(gp.player.inventory.get(itemIndex) == gp.player.currentWeapon||
								gp.player.inventory.get(itemIndex) == gp.player.currentShield||
								gp.player.inventory.get(itemIndex) == gp.player.currentProjectile||
								gp.player.inventory.get(itemIndex) == gp.player.currentLight) {
							commandNum = 0;
							subState = 0;
							npc.startDialogue(npc,4);
						}
						else if(gp.player.inventory.get(itemIndex).name.equals("Travel Token") ||
								gp.player.inventory.get(itemIndex).name.equals("Eternal Ring") ||
								gp.player.inventory.get(itemIndex).type == gp.player.type_emblem) {
							commandNum = 0;
							subState = 0;
							npc.startDialogue(npc,5);
						}
						else {
							if(gp.player.inventory.get(itemIndex).amount > 1) {
								gp.player.inventory.get(itemIndex).amount--;
							}
							else {
								gp.player.inventory.remove(itemIndex);
							}
							gp.player.coin += price;
						}
					}
				}
				
	}
	public void drawSleepScreen() {
		
		counter++;
		
		if(counter < 120) {
			gp.eManager.lighting.filterAlpha += 0.01f;
			if(gp.eManager.lighting.filterAlpha > 1f) {
				gp.eManager.lighting.filterAlpha = 1f;
			}
		}
		if(counter >= 120) {
			gp.eManager.lighting.filterAlpha -= 0.01f;
			if(gp.eManager.lighting.filterAlpha <= 0f) {
				gp.eManager.lighting.filterAlpha = 0f;
				counter = 0;
				gp.eManager.lighting.dayState = gp.eManager.lighting.day;
				gp.eManager.lighting.dayCounter = 0;
				gp.dayState = gp.day;
				gp.checkAllTheMusic();
				gp.saveload.save();
				gp.ui.addMessage("Game saved!");
				gp.gameState = gp.playState;
				gp.player.getImage();
			}
		}
	}
	public void drawYesOrNoScreen(int frameX, int frameY) {
		
		int textX = frameX + gp.tileSize;
		int textY = frameY + gp.tileSize*3;
		
		currentDialogue = "Are you ready to face\nyour first Boss?";
		
		for(String line: currentDialogue.split("\n")) {
			g2.drawString(line, textX, textY);
			textY += 40;
		}
		
		// YES
		String text = "Yes";
		textX = getXForCenteredText(text);
		textY += gp.tileSize*3;
		g2.drawString(text, textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				gp.stopMusic();
				yesSelected = true;
			}
		}
		
		// NO
		text = "No";
		textX = getXForCenteredText(text);
		textY += gp.tileSize;
		g2.drawString(text, textX, textY);
		if(commandNum == 1) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				yesSelected = false;
			}
		}
	}
	public int getItemIndexOnSlot(int slotCol, int slotRow) {
		int itemIndex = slotCol + (slotRow*5);
		return itemIndex;
	}
	public void drawSubWindow( int x, int y, int width, int height) {
		
		Color c = new Color(0, 0, 0, 210);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		c = new Color(255, 255, 255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
	}
	public int getXForCenteredText(String text) {
		
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		return x;
	}
	public int getXForAlignToRightText(String text, int tailX) {
		
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = tailX - length;
		return x;
	}
	// WHY IS ALL THE CODE HIDDEN HERE?!?!
}
