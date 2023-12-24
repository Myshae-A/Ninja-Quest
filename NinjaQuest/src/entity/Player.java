package entity;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.AlphaComposite;
import java.awt.Color;

import main.GamePanel;
import main.KeyHandler;
import main.QuestHandler;
import object.OBJ_Axe;
import object.OBJ_Coin_Bronze;
import object.OBJ_Craftable_Anvil;
import object.OBJ_Craftable_Stone;
import object.OBJ_Craftable_Wood;
import object.OBJ_Craftable_Woodenplank;
import object.OBJ_Fireball;
import object.OBJ_Hero_Crafting_Table;
import object.OBJ_Hero_Guardian_Field;
import object.OBJ_Hero_Marksman_Bow;
import object.OBJ_Hero_Special_Cloak;
import object.OBJ_Hero_Warrior_Sword;
import object.OBJ_Key;
import object.OBJ_Lantern;
import object.OBJ_Lantern_Blue;
import object.OBJ_Lantern_Emerald;
import object.OBJ_Lantern_Metal;
import object.OBJ_Lightning;
import object.OBJ_Map;
import object.OBJ_Ninjastar;
import object.OBJ_Pickaxe;
import object.OBJ_Potion_Big_Red;
import object.OBJ_Potion_Max_Blue;
import object.OBJ_Potion_Max_Red;
import object.OBJ_Potion_Red;
import object.OBJ_Rock;
import object.OBJ_Rock_forPlayer;
import object.OBJ_Shield_Blue;
import object.OBJ_Shield_Emerald;
import object.OBJ_Shield_Metal;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Blue;
import object.OBJ_Sword_Emerald;
import object.OBJ_Sword_Metal;
import object.OBJ_Sword_Normal;
import object.OBJ_Tent;
import object.OBJ_Travel_Token;

public class Player extends Entity{

	KeyHandler keyH;
	public QuestHandler qH;
	Graphics2D g2;
	
	public final int screenX;
	public final int screenY;
	int standCounter = 0;
	int projectileBrokeCounter = 0;
	public boolean attackCanceled = false;
	
	public String currentClass = ""; // THE PLAYER'S CHOSEN CLASS
	public boolean ranger = false;
	public boolean craftsman, tableOn = false, craftOn = false, leftSide = false, rightSide = false;
	public boolean guardian = false, fieldOn = false;
	public boolean specialist = false, invisible = false;
	public boolean warrior = false;
	
	//public boolean tpChecked = false;
	
	public int projectileCounter = 1;
	public int pickUpObjectCounter = 0;
	// Fireball damage increases as you level up now, finally!!!
	public boolean eternalRing = false;
	public int deathCount = 0;
	
	//<map #, whether quest is complete for that map or not>
	public Map<Integer,Boolean> enemyQuestCounter; // <map#, questComplete>
	public Map<Integer,Boolean> bossQuestCounter;
	public Map<Integer,Boolean> levelQuestCounter;
	public Map<Integer,Boolean> collectorQuestCounter;
	public Map<Integer,Boolean> allQuestsFinishedCounter;
	
	public long lastTime = 0;
	public double timeFactor = 1e-8; // must tune to fit
	
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		super(gp);
		
		this.keyH = keyH;
		qH = new QuestHandler(gp);
		
		
		enemyQuestCounter = new HashMap<Integer,Boolean>();
		for(int i = 0; i < gp.maxMap; i++) {
			enemyQuestCounter.put(i, false);
		}
		bossQuestCounter = new HashMap<Integer,Boolean>();
		for(int i = 0; i < gp.maxMap; i++) {
			bossQuestCounter.put(i, false);
		}
		levelQuestCounter = new HashMap<Integer,Boolean>();
		for(int i = 0; i < gp.maxMap; i++) {
			levelQuestCounter.put(i, false);
		}
		collectorQuestCounter = new HashMap<Integer,Boolean>();
		for(int i = 0; i < gp.maxMap; i++) {
			collectorQuestCounter.put(i, false);
		}
		allQuestsFinishedCounter = new HashMap<Integer,Boolean>();
		for(int i = 0; i < gp.maxMap; i++) {
			allQuestsFinishedCounter.put(i, false);
		}
		
//		if(gp.fullScreenOn) {
//			screenX = gp.screenWidth2/2 - (gp.tileSize/2);
//			screenY = gp.screenHeight2/2 - (gp.tileSize/2);
//		}
//		else {
//			screenX = gp.screenWidth/2 - (gp.tileSize/2);
//			screenY = gp.screenHeight/2 - (gp.tileSize/2);
//		}
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);//to center the character
		screenY = gp.screenHeight/2 - (gp.tileSize/2);//to center the character
		
		if(screenX == gp.screenWidth) {
			//System.out.println("ScreenX is big");
		}
		
		// SOLID AREA
		solidArea = new Rectangle();
		solidArea.x = 10;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 28; // offset for better compatibility with doors
		solidArea.height = 30;
		
		setDefaultValues();
	}
	public void setDefaultValues() {
		
		gp.currentMap = 6; // start at home...
		
		worldX = gp.tileSize * 22;//22 for home // 13, 36 is for entrance to slime boss 
		worldY = gp.tileSize *8;//8
		//worldX = gp.tileSize * 69;// for town
		//worldY = gp.tileSize * 28;
		//worldX = gp.tileSize * 81;// for merchant 87
		//worldY = gp.tileSize * 84;//90
		
		// = gp.tileSize * 23;// for beginner's Island
		//worldY = gp.tileSize * 21;
		//worldX = gp.tileSize * 10;// for merchant
		//worldY = gp.tileSize * 41;
		//worldX = gp.tileSize * 12;// for castle
		//worldY = gp.tileSize * 10;
		//worldX = gp.tileSize * 36;// for new world
		//worldY = gp.tileSize * 21;
		
		//worldX = gp.tileSize * 21;// for final island
		//worldY = gp.tileSize * 85;
		//worldX = gp.tileSize * 50;// for final island EMblems
		//worldY = gp.tileSize * 34;
//		worldX = gp.tileSize * 50;// for final island Fight
//		worldY = gp.tileSize * 70;
		
		
		defaultSpeed = 4;
		speed = defaultSpeed;
		direction = "down";
		craftsman = false;
		guardian = false;
		specialist = false;
		warrior = false;
		
		// RESET ALL QUESTS
		enemyQuestCounter = new HashMap<Integer,Boolean>();
		for(int i = 0; i < gp.maxMap; i++) {
			enemyQuestCounter.put(i, false);
		}
		bossQuestCounter = new HashMap<Integer,Boolean>();
		for(int i = 0; i < gp.maxMap; i++) {
			bossQuestCounter.put(i, false);
		}
		levelQuestCounter = new HashMap<Integer,Boolean>();
		for(int i = 0; i < gp.maxMap; i++) {
			levelQuestCounter.put(i, false);
		}
		collectorQuestCounter = new HashMap<Integer,Boolean>();
		for(int i = 0; i < gp.maxMap; i++) {
			collectorQuestCounter.put(i, false);
		}
		allQuestsFinishedCounter = new HashMap<Integer,Boolean>();
		for(int i = 0; i < gp.maxMap; i++) {
			allQuestsFinishedCounter.put(i, false);
		}
		
		this.deathCount = 0;
		this.eternalRing = false;
		
		this.qH.monsDefeated.put("Bat",0);
		this.qH.monsDefeated.put("Green Slime",0);
		this.qH.monsDefeated.put("Blue Slime",0);
		this.qH.monsDefeated.put("Red Slime",0);
		this.qH.monsDefeated.put("Misty Slime",0);
		this.qH.monsDefeated.put("Banana Slime",0);
		this.qH.monsDefeated.put("Pink Slime",0);
		this.qH.monsDefeated.put("Purple Slime",0);
		this.qH.monsDefeated.put("Orc", 0);
		this.qH.monsDefeated.put("Frost Orc", 0);
		this.qH.monsDefeated.put("Skeleton", 0);
		this.qH.monsDefeated.put("Elite Skeleton", 0);
		
		this.qH.bossesDefeated.put("Slime Boss",0);
		this.qH.bossesDefeated.put("Skeleton Lord",0);
		this.qH.bossesDefeated.put("Pirate King",0);
		this.qH.bossesDefeated.put("Samurai",0);
		this.qH.bossesDefeated.put("Armaggedon", 0);
		
		gp.dayNumber = 0;
		
		// PLAYER STATUS
		name = "player";
		level = 1;
		maxLife = 6;//40=max//6=start
		life = maxLife;
		maxMana = 3;//15=max//3=start
		mana = maxMana;
		ammo = 10; // for testing and showing other examples
		strength = 1; // The more strength he has, the more damage he gives.///default=1
		rangeAttack = 0; // The more range attack he has, the more damage his projectiles do///default=0
		dexterity = 0; // The more dexterity he has, the less damage he receives.
		exp = 0;
		nextLevelExp = 10;
		coin = 0;
		currentWeapon = null;//OBJ_Axe(gp); // Punching works now, when weapons break!!!
		currentShield = null;//new OBJ_Shield_Wood(gp);
		currentProjectile = null;//new OBJ_Ninjastar(gp);
		currentLight = null;//new OBJ_Lantern(gp);
		currentSpecial = null;//new OBJ_Hero_Crafting_Table(gp);
		attack = getAttack(); // The total attack value is decided by strength and weapon.
		defense = getDefense(); // The total defense value is decided by dexterity and shield.
		
		getImage();
		getAttackImage();
		getGuardImage();
		setItems();
		setDialogue();
		
	}


	public void setDefaultPositions() {
		
		gp.currentArea = gp.outside;
		
		if(gp.monster[2][0].bossMusicOn == true) { // respawn in Misty Meadows if you die from pirate boss
			gp.currentMap = 1;
			gp.monster[2][0].bossMusicOn = false;
			worldX = gp.tileSize * 24;
			worldY = gp.tileSize * 24;
			direction = "down";
		}
		else if(gp.currentMap == 1) {
			worldX = gp.tileSize * 24;
			worldY = gp.tileSize * 24;
			direction = "down";
		}
		else if(gp.currentMap == 5) {
			worldX = gp.tileSize * 21;
			worldY = gp.tileSize * 85;
			direction = "down";
		}
		else if(gp.currentMap == 6) {
			worldX = gp.tileSize * 22;
			worldY = gp.tileSize * 8;
			direction = "down";
		}
		else if(gp.currentMap == 7) {
			gp.currentMap = 6;
			worldX = gp.tileSize * 22;
			worldY = gp.tileSize * 8;
			direction = "down";
		}
		else {
			gp.currentMap = 0;
			// makes you respawn at the start
			worldX = gp.tileSize * 23;
			worldY = gp.tileSize * 21;
			direction = "down";
		}
	}
	public void setDialogue() {
		
		dialogues[0][0] = "You are level " + level + " now!"
				+ " Progress has been saved.\nPress [enter] to continue.";
		
		dialogues[1][0] = "Press [WASD] Keys to move. Press [ENTER] to read\nthings, open doors, or talk to other characters.";
		dialogues[1][1] = "Read the wooden signs scattered around the map.\nThey will give you helpful tips. [Enter to exit]";
	}
	public void restoreStatus() {
		
		life = maxLife;
		mana = maxMana;
		speed = defaultSpeed;
		invincible = false;
		transparent = false;
		attacking = false;
		guarding = false;
		knockBack = false;
		lightUpdated = true;
	}
	public void setItems() {
		//inventory.add(currentSpecial);
		
		inventory.clear();
		//inventory.add(currentWeapon);
		//inventory.add(new OBJ_Tent(gp));
		//inventory.add(new OBJ_Hero_Special_Cloak(gp));
		//inventory.add(new OBJ_Rock_forPlayer(gp));
		//inventory.add(new OBJ_Key(gp));
		
		//inventory.add(new OBJ_Hero_Warrior_Sword(gp));
		
//		inventory.add(new OBJ_Shield_Wood(gp));
//		inventory.add(new OBJ_Shield_Blue(gp));
//		inventory.add(new OBJ_Shield_Metal(gp));
//		inventory.add(new OBJ_Shield_Emerald(gp));
		//inventory.add(new OBJ_Potion_Max_Red(gp));
		//inventory.add(new OBJ_Potion_Max_Blue(gp));
//		
//		inventory.add(new OBJ_Sword_Normal(gp));
//		inventory.add(new OBJ_Sword_Blue(gp));
//		inventory.add(new OBJ_Sword_Metal(gp));
//		inventory.add(new OBJ_Sword_Emerald(gp));
		
		//inventory.add(new OBJ_Hero_Crafting_Table(gp));
		//inventory.add(new OBJ_Lantern(gp));
		//inventory.add(new OBJ_Lightning(gp));
		//inventory.add(new OBJ_Pickaxe(gp));
		
		//inventory.add(new OBJ_Craftable_Anvil(gp));
		//inventory.add(new OBJ_Craftable_Woodenplank(gp));
//		inventory.add(new OBJ_Travel_Token(gp));
//		inventory.get(0).amount = 5;
		//inventory.add(new OBJ_Sword_Normal(gp));
		//inventory.add(currentProjectile);
		//inventory.add(new OBJ_Sword_Metal(gp));
		//inventory.add(new OBJ_Ninjastar(gp));
//		inventory.add(new OBJ_Potion_Red(gp));
//		inventory.add(new OBJ_Key(gp));
//		inventory.get(1).amount = 30;
////		inventory.add(new OBJ_Potion_Big_Red(gp));
////		inventory.get(2).amount = 5;
		//inventory.add(new OBJ_Craftable_Stone(gp));
		//inventory.get(1).amount = 10;
		//inventory.add(new OBJ_Craftable_Wood(gp));
		//inventory.get(2).amount = 10;
//		inventory.add(new OBJ_Axe(gp));
//		inventory.add(new OBJ_Sword_Emerald(gp));
//		inventory.add(new OBJ_Shield_Emerald(gp));
//		inventory.add(new OBJ_Lantern(gp));
//		inventory.add(new OBJ_Lantern_Blue(gp));
//		inventory.add(new OBJ_Lantern_Metal(gp));
//		inventory.add(new OBJ_Lantern_Emerald(gp));
//		//inventory.add(new OBJ_Fireball(gp));
//		inventory.add(new OBJ_Hero_Special_Cloak(gp));
//		inventory.add(new OBJ_Hero_Marksman_Bow(gp));
//		inventory.add(new OBJ_Hero_Warrior_Sword(gp));
//		inventory.add(new OBJ_Hero_Guardian_Field(gp));
//		inventory.add(new OBJ_Map(gp));
		
//		inventory.add(new OBJ_Key(gp));
		// COME BACK HERE LATER, I THINK THIS IS HOW WE WILL GET KEY DROPS FROM MONSTERS!!!
		//inventory.add(currentKey);
	}
	public int getAttack() {
		
		if(currentSpecial != null) {
			if(currentSpecial.name.equals("Warrior Sword")) {
				attackArea = currentSpecial.attackArea;
				motion1_duration = currentSpecial.motion1_duration;
				motion2_duration = currentSpecial.motion2_duration;
				if(currentWeapon != null) {
					return attack = strength + currentSpecial.attackValue+currentWeapon.attackValue;
				}
				return attack = strength + currentSpecial.attackValue; // When doing *, power is OP, too strong
			}
			else {
				knockBackPower = 1;
				attackArea.width = 30;
				attackArea.height = 30;
				motion1_duration = 5;
				motion2_duration = 20;
				return attack = strength; // damage of punch
			}
		}
		else if(currentWeapon != null) {
			attackArea = currentWeapon.attackArea;
			motion1_duration = currentWeapon.motion1_duration;
			motion2_duration = currentWeapon.motion2_duration;
			return attack = strength + currentWeapon.attackValue; // When doing *, power is OP, too strong
		}
		else {
			knockBackPower = 1;
			attackArea.width = 30;
			attackArea.height = 30;
			motion1_duration = 5;
			motion2_duration = 20;
			return attack = strength; // damage of punch
		}
	}
	public int getDefense() {
		if(currentShield != null) {
			return defense = dexterity + currentShield.defenseValue; // When doing *, defense is OP, too strong
		}
		else {
			return  defense = dexterity;
		}
		
	}
	public int getCurrentWeaponSlot() {
		int currentWeaponSlot = 0;
		for(int i = 0; i < inventory.size(); i++) {
			if(inventory.get(i) == currentWeapon) {
				currentWeaponSlot = i;
			}
		}
		return currentWeaponSlot;
	}
	public int getCurrentShieldSlot() {
		int CurrentShieldSlot = 0;
		for(int i = 0; i < inventory.size(); i++) {
			if(inventory.get(i) == currentShield) {
				CurrentShieldSlot = i;
			}
		}
		return CurrentShieldSlot;
	}
	public int getCurrentProjectileSlot() {
		int CurrentProjectileSlot = -1;
		for(int i = 0; i < inventory.size(); i++) {
			if(inventory.get(i) == currentProjectile) {
				CurrentProjectileSlot = i;
			}
		}
		return CurrentProjectileSlot;
	}
	public int getCurrentLightSlot() {
		int CurrentLightSlot = 0;
		for(int i = 0; i < inventory.size(); i++) {
			if(inventory.get(i) == currentLight) {
				CurrentLightSlot = i;
			}
		}
		return CurrentLightSlot;
	}
	public int getCurrentSpecialSlot() {
		int CurrentLightSlot = 0;
		for(int i = 0; i < inventory.size(); i++) {
			if(inventory.get(i) == currentSpecial) {
				CurrentLightSlot = i;
			}
		}
		return CurrentLightSlot;
	}
	public int getCurrentAbilitySlot() {
		int CurrentLightSlot = 0;
		for(int i = 0; i < inventory.size(); i++) {
			if(inventory.get(i) == currentAbility) {
				CurrentLightSlot = i;
			}
		}
		return CurrentLightSlot;
	}
	public int getCurrentItemSlot() {
		int CurrentItemSlot = 0;
		for(int i = 0; i < inventory.size(); i++) {
			if(inventory.get(i).isSelected) {
				CurrentItemSlot = i;
			}
		}
		return CurrentItemSlot;
	}
	public int getSpeed() {
		return speed = speed+ currentFootwear.speed;
	}
	public void getImage() {
		
		
		up1 = setup("/player/ninja_up_1", gp.tileSize, gp.tileSize);
		up2 = setup("/player/ninja_up_2", gp.tileSize, gp.tileSize);
		down1 = setup("/player/ninja_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/player/ninja_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/player/ninja_left_1", gp.tileSize, gp.tileSize);
		left2 = setup("/player/ninja_left_2", gp.tileSize, gp.tileSize);
		right1 = setup("/player/ninja_right_1", gp.tileSize, gp.tileSize);
		right2 = setup("/player/ninja_right_2", gp.tileSize, gp.tileSize);
		
		up3 = setup("/player/ninja_up_1", gp.tileSize, gp.tileSize);
		up4 = setup("/player/ninja_up_2", gp.tileSize, gp.tileSize);
		down3 = setup("/player/ninja_down_1", gp.tileSize, gp.tileSize);
		down4 = setup("/player/ninja_down_2", gp.tileSize, gp.tileSize);
		left3 = setup("/player/ninja_left_1", gp.tileSize, gp.tileSize);
		left4 = setup("/player/ninja_left_2", gp.tileSize, gp.tileSize);
		right3 = setup("/player/ninja_right_1", gp.tileSize, gp.tileSize);
		right4 = setup("/player/ninja_right_2", gp.tileSize, gp.tileSize);
	}
	public void getSleepingImage(BufferedImage image) {
		
		up1 = image;
		up2 = image;
		down1 = image;
		down2 = image;
		left1 = image;
		left2 = image;
		right1 = image;
		right2 = image;
		
	}
	public void getAttackImage() {
		
		if(currentSpecial != null) {
			if(currentSpecial.name.equals("Warrior Sword")) { // add warrior sword sprites
				attackUp1 = setup("/player/ninja_attack_warrior_up_1", gp.tileSize, gp.tileSize*2);
				attackUp2 = setup("/player/ninja_attack_warrior_up_2", gp.tileSize, gp.tileSize*2);
				attackDown1 = setup("/player/ninja_attack_warrior_down_1", gp.tileSize, gp.tileSize*2);
				attackDown2 = setup("/player/ninja_attack_warrior_down_2", gp.tileSize, gp.tileSize*2);
				attackLeft1 = setup("/player/ninja_attack_warrior_left_1", gp.tileSize*2, gp.tileSize);
				attackLeft2 = setup("/player/ninja_attack_warrior_left_2", gp.tileSize*2, gp.tileSize);
				attackRight1 = setup("/player/ninja_attack_warrior_right_1", gp.tileSize*2, gp.tileSize);
				attackRight2 = setup("/player/ninja_attack_warrior_right_2", gp.tileSize*2, gp.tileSize);
			}
		}
		else if(currentWeapon != null) {
			if(currentWeapon.type == type_sword) {
				if(currentWeapon.name.equals("Normal Sword")) {
					attackUp1 = setup("/player/ninja_attack_up_1.1", gp.tileSize, gp.tileSize*2);
					attackUp2 = setup("/player/ninja_attack_up_2.1", gp.tileSize, gp.tileSize*2);
					attackDown1 = setup("/player/ninja_attack_down_1.1", gp.tileSize, gp.tileSize*2);
					attackDown2 = setup("/player/ninja_attack_down_2.1", gp.tileSize, gp.tileSize*2);
					attackLeft1 = setup("/player/ninja_attack_left_1.1", gp.tileSize*2, gp.tileSize);
					attackLeft2 = setup("/player/ninja_attack_left_2.1", gp.tileSize*2, gp.tileSize);
					attackRight1 = setup("/player/ninja_attack_right_1", gp.tileSize*2, gp.tileSize);
					attackRight2 = setup("/player/ninja_attack_right_2.1", gp.tileSize*2, gp.tileSize);
				}
				else if(currentWeapon.name.equals("Blue Sword")) {
					attackUp1 = setup("/player/ninja_attack_blue_up_1", gp.tileSize, gp.tileSize*2);
					attackUp2 = setup("/player/ninja_attack_blue_up_2", gp.tileSize, gp.tileSize*2);
					attackDown1 = setup("/player/ninja_attack_blue_down_1", gp.tileSize, gp.tileSize*2);
					attackDown2 = setup("/player/ninja_attack_blue_down_2", gp.tileSize, gp.tileSize*2);
					attackLeft1 = setup("/player/ninja_attack_blue_left_1", gp.tileSize*2, gp.tileSize);
					attackLeft2 = setup("/player/ninja_attack_blue_left_2", gp.tileSize*2, gp.tileSize);
					attackRight1 = setup("/player/ninja_attack_blue_right_1", gp.tileSize*2, gp.tileSize);
					attackRight2 = setup("/player/ninja_attack_blue_right_2", gp.tileSize*2, gp.tileSize);
				}
				else if(currentWeapon.name.equals("Metal Sword")) {
					attackUp1 = setup("/player/ninja_attack_metal_up_1", gp.tileSize, gp.tileSize*2);
					attackUp2 = setup("/player/ninja_attack_metal_up_2", gp.tileSize, gp.tileSize*2);
					attackDown1 = setup("/player/ninja_attack_metal_down_1", gp.tileSize, gp.tileSize*2);
					attackDown2 = setup("/player/ninja_attack_metal_down_2", gp.tileSize, gp.tileSize*2);
					attackLeft1 = setup("/player/ninja_attack_metal_left_1", gp.tileSize*2, gp.tileSize);
					attackLeft2 = setup("/player/ninja_attack_metal_left_2", gp.tileSize*2, gp.tileSize);
					attackRight1 = setup("/player/ninja_attack_metal_right_1", gp.tileSize*2, gp.tileSize);
					attackRight2 = setup("/player/ninja_attack_metal_right_2", gp.tileSize*2, gp.tileSize);
				}
				else if(currentWeapon.name.equals("Emerald Sword")) {
					attackUp1 = setup("/player/ninja_attack_emerald_up_1", gp.tileSize, gp.tileSize*2);
					attackUp2 = setup("/player/ninja_attack_emerald_up_2", gp.tileSize, gp.tileSize*2);
					attackDown1 = setup("/player/ninja_attack_emerald_down_1", gp.tileSize, gp.tileSize*2);
					attackDown2 = setup("/player/ninja_attack_emerald_down_2", gp.tileSize, gp.tileSize*2);
					attackLeft1 = setup("/player/ninja_attack_emerald_left_1", gp.tileSize*2, gp.tileSize);
					attackLeft2 = setup("/player/ninja_attack_emerald_left_2", gp.tileSize*2, gp.tileSize);
					attackRight1 = setup("/player/ninja_attack_emerald_right_1", gp.tileSize*2, gp.tileSize);
					attackRight2 = setup("/player/ninja_attack_emerald_right_2", gp.tileSize*2, gp.tileSize);
				}
				
			}
			else if(currentWeapon.type == type_axe) {
				attackUp1 = setup("/player/ninja_axe_up_1.3", gp.tileSize, gp.tileSize*2);
				attackUp2 = setup("/player/ninja_axe_up_2.1", gp.tileSize, gp.tileSize*2);
				attackDown1 = setup("/player/ninja_axe_down_1.1", gp.tileSize, gp.tileSize*2);
				attackDown2 = setup("/player/ninja_axe_down_2.1", gp.tileSize, gp.tileSize*2);
				attackLeft1 = setup("/player/ninja_axe_left_1.2", gp.tileSize*2, gp.tileSize);
				attackLeft2 = setup("/player/ninja_axe_left_2.2", gp.tileSize*2, gp.tileSize);
				attackRight1 = setup("/player/ninja_axe_right_1", gp.tileSize*2, gp.tileSize);
				attackRight2 = setup("/player/ninja_axe_right_2.1", gp.tileSize*2, gp.tileSize);
			}
			else if(currentWeapon.type == type_pickaxe) {
				attackUp1 = setup("/player/ninja_pick_up_1", gp.tileSize, gp.tileSize*2);
				attackUp2 = setup("/player/ninja_pick_up_2", gp.tileSize, gp.tileSize*2);
				attackDown1 = setup("/player/ninja_pick_down_1", gp.tileSize, gp.tileSize*2);
				attackDown2 = setup("/player/ninja_pick_down_2", gp.tileSize, gp.tileSize*2);
				attackLeft1 = setup("/player/ninja_pick_left_1", gp.tileSize*2, gp.tileSize);
				attackLeft2 = setup("/player/ninja_pick_left_2", gp.tileSize*2, gp.tileSize);
				attackRight1 = setup("/player/ninja_pick_right_1", gp.tileSize*2, gp.tileSize);
				attackRight2 = setup("/player/ninja_pick_right_2", gp.tileSize*2, gp.tileSize);
			}
		}
		else {
			attackUp1 = setup("/player/ninja_punch_up_1", gp.tileSize, gp.tileSize);
			attackUp2 = setup("/player/ninja_punch_up_2", gp.tileSize, gp.tileSize);
			attackDown1 = setup("/player/ninja_punch_down_1", gp.tileSize, gp.tileSize);
			attackDown2 = setup("/player/ninja_punch_down_2", gp.tileSize, gp.tileSize);
			attackLeft1 = setup("/player/ninja_punch_left_1", gp.tileSize, gp.tileSize);
			attackLeft2 = setup("/player/ninja_punch_left_2", gp.tileSize, gp.tileSize);
			attackRight1 = setup("/player/ninja_punch_right_1", gp.tileSize, gp.tileSize);
			attackRight2 = setup("/player/ninja_punch_right_2", gp.tileSize, gp.tileSize);
		}
		
	}
	public void getGuardImage() {
		
		if(currentShield != null) {
			if(currentShield.name.equals("Wood Shield")) {
				guardUp = setup("/player/ninja_guard_up", gp.tileSize, gp.tileSize);
				guardDown = setup("/player/ninja_guard_down", gp.tileSize, gp.tileSize);
				guardLeft = setup("/player/ninja_guard_left", gp.tileSize, gp.tileSize);
				guardRight = setup("/player/ninja_guard_right", gp.tileSize, gp.tileSize);
			}
			else if(currentShield.name.equals("Blue Shield")) {
				guardUp = setup("/player/ninja_guard_blue_up", gp.tileSize, gp.tileSize);
				guardDown = setup("/player/ninja_guard_blue_down", gp.tileSize, gp.tileSize);
				guardLeft = setup("/player/ninja_guard_blue_left", gp.tileSize, gp.tileSize);
				guardRight = setup("/player/ninja_guard_blue_right", gp.tileSize, gp.tileSize);
			}
			else if(currentShield.name.equals("Metal Shield")) {
				guardUp = setup("/player/ninja_guard_metal_up", gp.tileSize, gp.tileSize);
				guardDown = setup("/player/ninja_guard_metal_down", gp.tileSize, gp.tileSize);
				guardLeft = setup("/player/ninja_guard_metal_left", gp.tileSize, gp.tileSize);
				guardRight = setup("/player/ninja_guard_metal_right", gp.tileSize, gp.tileSize);
			}
			else if(currentShield.name.equals("Emerald Shield")) {
				guardUp = setup("/player/ninja_guard_emerald_up", gp.tileSize, gp.tileSize);
				guardDown = setup("/player/ninja_guard_emerald_down", gp.tileSize, gp.tileSize);
				guardLeft = setup("/player/ninja_guard_emerald_left", gp.tileSize, gp.tileSize);
				guardRight = setup("/player/ninja_guard_emerald_right", gp.tileSize, gp.tileSize);
			}
			
		}
		
	}
	public int getShieldValue() {
		return currentShield.defenseValue;
	}
	public void update() {
		
//		long deltaTime = 0;
//		long currentTime = System.nanoTime();
//		
//		if(lastTime != 0) {
//			deltaTime = currentTime - lastTime;
//		}
//		lastTime = currentTime;
		
		if(knockBack == true) { // Stoped at 14:00 in video #49
			
			collisionOn = false;
			gp.cChecker.checkTile(this);
			gp.cChecker.checkObject(this, true);
			gp.cChecker.checkEntity(this, gp.npc);
			gp.cChecker.checkEntity(this, gp.monster);
			gp.cChecker.checkEntity(this, gp.iTile);
			
			if(collisionOn == true) {
				knockBackCounter = 0;
				knockBack = false;
				speed = defaultSpeed;
			}
			else if(collisionOn == false) {
				switch(knockBackDirection) { 
				case "up":worldY -= speed;break;
				case "down":worldY += speed;break;
				case "left":worldX -= speed;break;
				case "right":worldX += speed;break;
				}
			}
			
			knockBackCounter++;
			if(knockBackCounter == 10) { // bigger #, bigger knockback
				knockBackCounter = 0;
				knockBack = false;
				speed = defaultSpeed;
			}
		}
		else if(attacking == true) {
			attacking();
		}
		else if(keyH.shiftPressed == true) {
			
			if(currentShield == null && myshaeBool == false) {} // was trying to add a "You need a shield!" UI, but couldn't
			// figure out how to make it not spam the text, so i kept this empty and gave up
			else {
				getGuardImage();
				guarding = true;
				guardCounter++;
			}
		}
		else if(keyH.upPressed == true || keyH.downPressed == true ||
				keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true) {
			
			if (keyH.upPressed == true) {
				direction = "up";
			}
			else if(keyH.downPressed == true) {				
				direction = "down";
			}
			else if (keyH.leftPressed == true) {				
				direction = "left";
			}
			else if (keyH.rightPressed == true) {				
				direction = "right";
			}
			
			// CHECK TILE COLLISION
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			// CHECK OBJECT COLLISION
			int objIndex = gp.cChecker.checkObject(this, true);
			if(pickUpObjectCounter<=0) {
				pickUpObject(objIndex);
			}
			pickUpObjectCounter--;
			
			// CHECKS NPC COLLISION
			int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
			interactNPC(npcIndex);
			
			// CHECK MONSTER COLLISI0N
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			contactMonster(monsterIndex);
			
			// CHECK INTERACTIVE TILE COLLISION
			int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
			// This has a yellow squiggly, but it makes the collision on
			// interactive tiles work, so yeah idk... but KEEP THIS
			
			
			// CHECK EVENT
			gp.eHandler.checkEvent();
			
			// 	IF COLLISION IS FALSE, PLAYER CAN MOVE
			if(collisionOn == false && keyH.enterPressed == false) { //
				switch(direction) {
				case "up":worldY -= speed;break;
				case "down":worldY += speed;break;
				case "left":worldX -= speed;break;
				case "right":worldX += speed;break;
				}
			}
			
			if(keyH.enterPressed == true && attackCanceled == false) {
				if(tableOn) {
					gp.playSE(61);
				}
				else {
					gp.playSE(7);
					attacking = true;
					spriteCounter = 0;
				}
			}
			
			attackCanceled = false;
			gp.keyH.enterPressed = false;
			guarding = false;
			guardCounter = 0;
			
			spriteCounter++;
			if(spriteCounter > 12) {
				if(spriteNum == 1) {
					spriteNum = 2;
				}
				else if(spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}
		else {
			standCounter++;
			
			if(standCounter == 20) {
				spriteNum = 1;
				standCounter = 0;
			}
			guarding = false;
			guardCounter = 0;
		}
		
		if(currentProjectile != null) {
			if(gp.keyH.shotKeyPressed == true && currentProjectile.alive == false
					&& shotAvailableCounter == 60 && currentProjectile.haveResource(this) == true) {
				
				projectileIsShot();
			}
		}
		
		
		// This needs to be outside of key if statement!
		if(invincible == true) {
			invincibleCounter++;
			if(invincibleCounter > 60) {
				invincible = false;
				transparent = false;
				invincibleCounter = 0;
			}
		}
		if(shotAvailableCounter < 60) {
			shotAvailableCounter++;
		}
		if(life > maxLife) {
			life = maxLife;
		}
		if(mana > maxMana) {
			mana = maxMana;
		}
		if(life <= 0) {
			boolean eternalRing = false;
			if(inventory.size() > 0) {
				for(int i =0; i < gp.player.inventory.size(); i++) {
					if(gp.player.inventory.get(i).name.equals("Eternal Ring")) {
						eternalRing = true;
					}
				}
			}
			if(!eternalRing) {
				deathCount++;
				gp.gameState = gp.gameOverState;
				gp.ui.commandNum = -1;
				gp.stopMusic();
				gp.stopSE();
				// maybe make
				gp.playSE(12);
			}
			
		}
		
	}
	public void pickUpObject(int i) {
		
		//boolean unlockedDoor = false;
		
		if(i != 999) {
			
			if(gp.obj[gp.currentMap][i] != null) {
				
				// EMBLEMS
				
				if(gp.obj[gp.currentMap][i].type == type_emblem) {
					if(gp.keyH.enterPressed) {
						gp.obj[gp.currentMap][i].interact();
					}
				}
			}
			
			// PICKUP ONLY ITEMS
			if(gp.obj[gp.currentMap][i].type == type_pickupOnly) {
				gp.obj[gp.currentMap][i].use(this);
				if(gp.obj[gp.currentMap][i].sub_type == sub_type_coin) {
					gp.ui.addMessage("+ "+gp.obj[gp.currentMap][i].value+" coins");
					gp.obj[gp.currentMap][i] = null;
				}
				gp.obj[gp.currentMap][i] = null;
			}
			
			// OBSTACLE
			else if(gp.obj[gp.currentMap][i].type == type_obstacle) {
				if(keyH.enterPressed == true) {
					attackCanceled = true;
					if(gp.obj[gp.currentMap][i].name.equals("Door Down")) { // later
						
						gp.obj[gp.currentMap][i].interact();
					}
					else if(gp.obj[gp.currentMap][i].name.equals("blue bed")) { // later
						
						gp.obj[gp.currentMap][i].use(gp.obj[gp.currentMap][i]);
					}
					else {
						gp.obj[gp.currentMap][i].interact();
					}
					
				}
			}
			
			else if(gp.obj[gp.currentMap][i].type == type_special) {
				if((keyH.enterPressed == true) && (gp.obj[gp.currentMap][i].name.equals("Crafting Table"))) {
					attackCanceled = true;
					gp.obj[gp.currentMap][i].interact();
				}
			}
			else if(gp.obj[gp.currentMap][i].sub_type == sub_type_placeable) {
				if((keyH.enterPressed == true)) {
					attackCanceled = true;
					gp.obj[gp.currentMap][i].interact();
				}
				
			}
			else if(gp.obj[gp.currentMap][i].sub_type == sub_type_insideStuff) {
				if((keyH.enterPressed == true)) {
					attackCanceled = true;
					gp.obj[gp.currentMap][i].interact();
				}
				
			}
			
			// INVENTORY ITEMS
			else {
				String text = "";
				
				if(gp.obj[gp.currentMap][i].type == type_emblem) {
					// SO THE EMBLEM ISN'T PICKED UP TWICE!
					checkCollision();
				}
				else if(canObtainItem(gp.obj[gp.currentMap][i]) == true) {
					
					
					if(gp.obj[gp.currentMap][i].name.equals("Travel Token")) {
						gp.playSE(54); // had to given travel token a seperate sound
					}
					else {
						gp.playSE(57);
					}
					
					if(gp.obj[gp.currentMap][i].name.equals("Eternal Ring")) {
						gp.obj[gp.currentMap][i] = null;
						eternalRing = true;
					}
					else if(gp.obj[gp.currentMap][i].name.equals("Wood")||gp.obj[gp.currentMap][i].name.equals("Stone")) {
						gp.obj[gp.currentMap][i] = null;
					}
					else {
						text = "Got a " + gp.obj[gp.currentMap][i].name + "!";
						gp.ui.addMessage(text);
						gp.obj[gp.currentMap][i] = null;
					}
				}
				else {
					text = "You cannot carry any more!";
					gp.ui.addMessage(text);
				}
			}
			
			if(qH.collectorQuestComplete(gp.currentMap) && collectorQuestCounter.get(gp.currentMap) == false) {
				gp.playSE(55);
				gp.ui.addMessage("Collector Quest Complete!");
				if((gp.currentMap == 6)||(gp.currentMap == 7)) { // only possible on map 6
					collectorQuestCounter.replace(6, true); // makes it so this notification only happens once
					collectorQuestCounter.replace(7, true);
				}
				else if((gp.currentMap == 4)||(gp.currentMap == 3)||(gp.currentMap == 0)) {
					collectorQuestCounter.replace(0, true);
					collectorQuestCounter.replace(3, true);
					collectorQuestCounter.replace(4, true);
				}
				else if((gp.currentMap == 1)||(gp.currentMap == 2)) {
					collectorQuestCounter.replace(1, true);
					collectorQuestCounter.replace(2, true);
				}
				else if(gp.currentMap == 5) {
					collectorQuestCounter.replace(5, true);
				}
				
				checkAllQuests(gp.currentMap);
			}
		}
	}
	public void interactNPC(int i) {
		
		if(i != 999) {
			
			if(gp.keyH.enterPressed == true) {
				attackCanceled = true;
				
//				gp.npc[gp.currentMap][i].facePlayer();
//				gp.npc[gp.currentMap][i].startDialogue(gp.ui.npc, dialogueSet); // doesnt work (my own edit part)
				
				gp.npc[gp.currentMap][i].speak();
			}
			
			gp.npc[gp.currentMap][i].move(direction);
		}
	}
	public void contactMonster(int i) {
		
		if(i != 999) {
			
			if(invincible == false && gp.monster[gp.currentMap][i].dying == false) {
				gp.playSE(6);
				
				
				
				int damage = gp.monster[gp.currentMap][i].attack - defense;
//				if(damage < 1) {
//					damage = 1;
//				}
				
				//life -= damage;
				if(fieldOn) { // ig code never reaches here, so this might be uneeded and put life-=damage back maybe...
					damageMonster(i,gp.player,gp.player.strength*2,8);
//					life -= damage/2;
					damagePlayer(attack/2);
//					int index = getMonIndex(this);
//					gp.player.damageMonster(index,gp.player,gp.player.strength,0);
					//setKnockBack(gp.monster[gp.currentMap][index], gp.player, 8);
					//System.out.println("player.contactMonster");
				}
				else {
					life -= damage;
				}
				if(!gp.player.invisible) { 
					invincible = true;
					transparent = true;
				}
				
				// Decrease durability
				if(currentShield != null) {
					currentShield.durability--;
						if(currentShield.durability <= 0) {
							gp.ui.addMessage("Your "+currentShield.name+" broke!");
							inventory.remove(getCurrentShieldSlot());
							
//							for(int j = 0; j < inventory.size(); j++) {
//								if(inventory.get(j).type == type_shield) {
//									currentShield = inventory.get(j); break;
//								}
//							}
							//System.out.println("Got to null 3");
							currentShield = null;
					}
				}
			}
		}
	}
	public void damageMonster(int i, Entity attacker, int attack, int knockBackPower) {
		
		if((i != 999)) {
			
			if(gp.monster[gp.currentMap][i].invincible == false) {
				
				
				boolean criticalHit = false;
				
				if(fieldOn) {
					//setKnockBack(gp.monster[gp.currentMap][i],gp.player,gp.player.strength*2); // for Guardian Field
					
					damagePlayer(attack/2);
					if(this.direction.equals(getOppositeDirection(this.direction))) {
						setKnockBack(gp.monster[gp.currentMap][i],gp.player, strength*2);
					}
					else {
						setSpecialKnockBack(gp.monster[gp.currentMap][i],strength*2);
					}
					
//					int index = getMonIndex(this);
//					gp.player.damageMonster(index,gp.player,gp.player.strength*2,8);
					
					//System.out.println("player.damageMonster");
				}
				else if(knockBackPower > 0) {
					setKnockBack(gp.monster[gp.currentMap][i], attacker, knockBackPower);
				}
				
				if(gp.monster[gp.currentMap][i].offBalance == true) {
					attack *= 3;
					criticalHit = true;
				}
				int damage = attack - gp.monster[gp.currentMap][i].defense;
				
				if(damage < 0) {
					damage = 0;
					gp.playSE(5);
					gp.monster[gp.currentMap][i].invincible = true;
					gp.monster[gp.currentMap][i].damageReaction(); // mon didnt take damage, but needs to still do damage reaction
				}
				else {
					//System.out.println(gp.monster[gp.currentMap][i].life);
					gp.monster[gp.currentMap][i].life -= damage;
					gp.playSE(5);
					//System.out.println(gp.monster[gp.currentMap][i].life);
					//System.out.println("player.damageMonsterNEXTELSE");
					if(criticalHit == true) {
						gp.ui.addMessage("critical hit!");
					}
					else {
						//gp.ui.addMessage(damage + " damage!");
					}
					
					// Decrease durability
					if(currentWeapon != null) {
						currentWeapon.durability--;
						if(currentWeapon.durability <= 0) {
							gp.ui.addMessage("Your "+currentWeapon.name+" broke!");
							inventory.remove(currentWeapon);
							
//							for(int j = 0; j < inventory.size(); j++) {
//								if(inventory.get(j).type == type_sword) {
//									currentWeapon = inventory.get(j); break;
//								}
//								else if(inventory.get(j).type == type_axe) {
//									currentWeapon = inventory.get(j); break;
//								}
//							}
							currentWeapon = null; // create & activate BareFist images for player here
							getAttackImage();
						}
						
					}
					
					gp.monster[gp.currentMap][i].invincible = true;
					gp.monster[gp.currentMap][i].damageReaction();
				}
				
				
				
				if(gp.monster[gp.currentMap][i].life <= 0) {
					gp.monster[gp.currentMap][i].dying = true;
					//gp.ui.addMessage("Beat the " + gp.monster[gp.currentMap][i].name + "!");
					exp += gp.monster[gp.currentMap][i].exp;
					gp.ui.addMessage("+ " + gp.monster[gp.currentMap][i].exp + " exp");
					
					// QUEST Update for ENEMY and BOSS quests
					if(gp.monster[gp.currentMap][i].sub_type == sub_type_enemy) {
						gp.player.qH.monsDefeated.replace(gp.monster[gp.currentMap][i].name, gp.player.qH.monsDefeated.get(gp.monster[gp.currentMap][i].name)+1);
						if(gp.player.qH.enemyQuestComplete(gp.currentMap) && enemyQuestCounter.get(gp.currentMap) == false) {
							gp.playSE(55);
							gp.ui.addMessage("Enemy Quest Complete!");
							
							 // makes it so this notification only happens once
							if(gp.currentMap == 0 || gp.currentMap == 3) { // no map 4, bc only skellyBoss will be there...
								enemyQuestCounter.replace(gp.currentMap, true);
								enemyQuestCounter.replace(3, true);
								enemyQuestCounter.replace(4, true);
							}
							else if(gp.currentMap == 1) { // no map 2, bc no blue slime will be there...
								enemyQuestCounter.replace(gp.currentMap, true);
								enemyQuestCounter.replace(2, true);
							}
							else if(gp.currentMap == 6) { // no  map 7 bc no green slime will be there...
								enemyQuestCounter.replace(gp.currentMap, true);
								enemyQuestCounter.replace(7, true);
							}
							checkAllQuests(gp.currentMap);
							
						}
					}
					else if(gp.monster[gp.currentMap][i].sub_type == sub_type_boss) {
						//System.out.println(qH.bossesDefeated);
						qH.bossesDefeated.replace(gp.monster[gp.currentMap][i].name, qH.bossesDefeated.get(gp.monster[gp.currentMap][i].name)+1);
						if(qH.bossQuestComplete(gp.currentMap) && bossQuestCounter.get(gp.currentMap) == false) {
							gp.playSE(55);
							gp.ui.addMessage("Boss Quest Complete!");
							bossQuestCounter.replace(gp.currentMap, true); // makes it so this notification only happens once
							//System.out.println("After:"+gp.player.qH.bossesDefeated);
							checkAllQuests(gp.currentMap);
						}
						//System.out.println(qH.bossesDefeated);
					}
					//System.out.println(qH.bossesDefeated.get("Samurai")+"befor lvlUP");
					checkLevelUp();
				}
			}
		}
	}
	public void damageInteractiveTile(int i) {
		
		if(i != 999 && gp.iTile[gp.currentMap][i].destructible == true
				&& gp.iTile[gp.currentMap][i].isCorrectItem(this) == true && gp.iTile[gp.currentMap][i].invincible == false) {
			
			gp.iTile[gp.currentMap][i].playSE();
			gp.iTile[gp.currentMap][i].life--;
			gp.iTile[gp.currentMap][i].invincible = true;
			
			// Generate particle
			generateParticle(gp.iTile[gp.currentMap][i], gp.iTile[gp.currentMap][i]);
			
			// Decrease durability
			if(currentWeapon != null) {
				
				currentWeapon.durability--;
				
				if(currentWeapon.durability <= 0) {
					gp.ui.addMessage("Your "+currentWeapon.name+" broke!");
					inventory.remove(currentWeapon);
				currentWeapon = null; // create & activate BareFist images for player here
				getAttackImage();
				}
			}
			
			if(gp.iTile[gp.currentMap][i].life == 0) {
				
				gp.iTile[gp.currentMap][i].checkDrop();
				gp.iTile[gp.currentMap][i] = gp.iTile[gp.currentMap][i].getDestroyedForm();
			}
		}
	}
	public void damageProjectile(int i) {
		
		if(i != 999) {
			Entity projectile = gp.projectile[gp.currentMap][i];
			projectile.alive = false;
			generateParticle(projectile,projectile);
			
			// Decrease durability
			
			if(currentWeapon != null) {
				currentWeapon.durability--;
				if(currentWeapon != null) {
					if(currentWeapon.durability <= 0) {
						gp.ui.addMessage("Your "+currentWeapon.name+" broke!");
						inventory.remove(currentWeapon);
						
						for(int j = 0; j < inventory.size(); j++) {
							if(inventory.get(j).type == type_sword) {
								currentWeapon = inventory.get(j); break;
							}
							else if(inventory.get(j).type == type_axe) {
								currentWeapon = inventory.get(j); break;
							}
						}
						currentWeapon = null; // create & activate BareFist images for player here
						getAttackImage();
					}
				}
				
			}
			
		}
	}
	public void projectileIsShot() {
		// SET FIREBALL COORDINATES, DIRECTION AND USER
		if(currentProjectile.durability <= 0 && projectileBrokeCounter == 0) {
			
			projectileBrokeCounter = 1;
			
			//currentProjectile = null;
			if(inventory.size() > 0 && getCurrentProjectileSlot() != -1) {
				inventory.remove(getCurrentProjectileSlot());
			}
			
			//System.out.println("here");
			gp.ui.addMessage("Your projectile broke!");
			
		}
		if(currentProjectile.durability > 0) {
			
			projectileBrokeCounter = 0;
			
			currentProjectile.set(worldX, worldY, direction, true, this);
			currentProjectile.durability--;
			
			// SUBTRACT THE COST (MANA, AMMO ETC.)
			currentProjectile.subtractResource(this);
			
			
			//CHECK VACANCY
			for(int i = 0; i < gp.projectile[1].length;i++) {
				if(gp.projectile[gp.currentMap][i] == null) {
					gp.projectile[gp.currentMap][i] = currentProjectile;
					break;
				}
			}
			
			shotAvailableCounter = 0;
			if(currentProjectile.name == "Fireball") {
				gp.playSE(10);
			}
			if(currentProjectile.name == "Ninja star") {
				gp.playSE(24);
			}
			if(currentProjectile.name == "Rock") {
				gp.playSE(56);
			}
			if(currentProjectile.name == "Lightning") {
				gp.playSE(67);
			}
		}
	}
	public void checkLevelUp() {
		if (exp >= nextLevelExp) {
			
			level++;
			
			if(level < 5) {
				nextLevelExp += 100;
				if(gp.player.level == 4) {
					maxLife += 2;
					dexterity++;
					maxMana++;
					rangeAttack++;
					strength++;
				}
			}
			else if(level >= 5) {
				nextLevelExp += 300;
				if(gp.player.level % 4 == 0) {
					if(level <= 56) {
						maxLife += 2; // so life cap is 18 hearts, or 36 life without class benefits
						if(maxLife == 40) { // highest possible max
							gp.ui.addMessage("Max life achieved!");
							// add an achievement
						}
					}
					if(level <= 36) {
						maxMana ++; // so ammo cap is 13 ammo, bc the 4 + 11, without class benefits
						if(maxMana == 15) { // highest possible max
							gp.ui.addMessage("Max ammo achieved!");
							// add an achievement
						}
					}
					strength++;
					dexterity++;
					rangeAttack++;
				}
			}

			attack = getAttack();
			defense = getDefense();
			gp.player.life = gp.player.maxLife;
			gp.player.mana = gp.player.maxMana;
			
			gp.saveload.save();
			
			gp.playSE(18);
			gp.gameState = gp.levelUpState; // level up state doesnt do anything now, bc of the new 'setdialogue' stuff
			setDialogue();
			startDialogue(this,0);
			if(qH.levelQuestComplete(gp.currentMap) && levelQuestCounter.get(gp.currentMap) == false) {
				gp.playSE(55);
				gp.ui.addMessage("Level Quest Complete!");
				levelQuestCounter.replace(gp.currentMap, true); // makes it so this notification only happens once
				checkAllQuests(gp.currentMap);
			}
			gp.ui.addMessage("Leveled up to " + gp.player.level + "!");
			
//			if(level == 20) {
//				gp.gameState = gp.statState;
//				gp.ui.subState = 0;
//			}
		}
	}
	public void selectItem() {
		
		int itemIndex = gp.ui.getItemIndexOnSlot(gp.ui.playerSlotCol, gp.ui.playerSlotRow);
		
		if(itemIndex < inventory.size()) {
			
			Entity selectedItem = inventory.get(itemIndex);
			
			// ADDED EQUIP AND UNEQUIP!!! 11/12/22
			if(gp.player.craftOn == false) {
				
				
				if(gp.player.currentWeapon == selectedItem) {
					selectedItem = gp.player.currentWeapon;
					gp.player.inventory.remove(currentWeapon);
					gp.player.currentWeapon = null;
					gp.player.inventory.add(itemIndex, selectedItem);
					attack = getAttack();
					getAttackImage();
				}
				else if(selectedItem.type == type_sword || selectedItem.type == type_axe || selectedItem.type == type_pickaxe) {
					
					currentWeapon = selectedItem;
					attack = getAttack();
					getAttackImage();
				}
				
				if(gp.player.currentShield == selectedItem) {
					selectedItem = gp.player.currentShield;
					gp.player.inventory.remove(currentShield);
					gp.player.currentShield = null;
					gp.player.inventory.add(itemIndex, selectedItem);
					attack = getDefense();
				}
				else if(selectedItem.type == type_shield) {
					
					currentShield = selectedItem;
					defense = getDefense();
				}
				
				if(gp.player.currentLight == selectedItem) {
					selectedItem = gp.player.currentLight;
					gp.player.inventory.remove(currentLight);
					gp.player.currentLight = null;
					gp.player.inventory.add(itemIndex, selectedItem);
				}
				else if(selectedItem.type == type_light) {
					
					if(currentLight == selectedItem) {
						currentLight = null;
					}
					else {
						currentLight = selectedItem;
					}
					lightUpdated = true;
				}
				
				if(gp.player.currentProjectile == selectedItem) {
					selectedItem = gp.player.currentProjectile;
					gp.player.inventory.remove(currentProjectile);
					gp.player.currentProjectile = null;
					gp.player.inventory.add(itemIndex, selectedItem);
				}
				else if(selectedItem.type == type_projectile) {
					currentProjectile = selectedItem;
				}
				
				if(gp.player.currentSpecial == selectedItem) {
					selectedItem = gp.player.currentSpecial;
					gp.player.inventory.remove(currentSpecial);
					gp.player.currentSpecial = null;
					gp.player.inventory.add(itemIndex, selectedItem);
					
					if(selectedItem.name.equals("Special Cloak")) {
						transparent = false;
						invisible = false;
					}
					if(selectedItem.name.equals("Guardian Field")) {
						fieldOn = false;
					}
					if(selectedItem.name.equals("Crafting Table")) {
						tableOn = false;
						gp.gameState = gp.characterState;
						//System.out.println("craftsmanStateEnd");
					}
					
					getAttackImage();
				}
				else if(selectedItem.type == type_special) {
					currentSpecial = selectedItem;
					
					if(selectedItem.name.equals("Special Cloak")) {
						transparent = true;
						invisible = true;
					}
					if(selectedItem.name.equals("Guardian Field")) {
						fieldOn = true;
					}
					if(selectedItem.name.equals("Crafting Table")) {
						tableOn = true;
						gp.ui.subState = 0;
						gp.gameState = gp.craftsmanState;
						//System.out.println("craftsmanStateStart");
					}
				}
				
				if(gp.player.currentAbility == selectedItem) {
					selectedItem = gp.player.currentAbility;
					gp.player.inventory.remove(currentAbility);
					gp.player.currentAbility = null;
					gp.player.inventory.add(itemIndex, selectedItem);
				}
				else if(selectedItem.type == type_ability) {
					currentAbility = selectedItem;
				}
				
				if(selectedItem.type == type_consumable) {
					
					if(selectedItem.use(this) == true) {
						if(selectedItem.amount > 1) {
							selectedItem.amount--;
						}
						else {
							inventory.remove(itemIndex);
						}
					}
				}
				
				if(selectedItem.sub_type == sub_type_placeable) {
//					System.out.println(selectedItem.name);
//					System.out.println(selectedItem.isSelected);
					boolean somethingIsAlreadySlected = false;
					int alreadySelectedIndex = -1;
					for(int i = 0; i < inventory.size(); i++) {
						if(inventory.get(i).isSelected) {
							if(selectedItem != inventory.get(i)) {
								somethingIsAlreadySlected = true;
								alreadySelectedIndex = i;
							}
						}
					}
					if(somethingIsAlreadySlected == false) {
						if(selectedItem.isSelected) {
							selectedItem.isSelected = false;
						}
						else {
							selectedItem.isSelected = true;
						}
					}
					else {
						inventory.get(alreadySelectedIndex).isSelected = false;
						selectedItem.isSelected = true;
					}
//					System.out.println(selectedItem.name);
//					System.out.println(selectedItem.isSelected);
//					System.out.println(somethingIsAlreadySlected);
//					System.out.println("--------------");
				}
				
				gp.playSE(53);
				
			}
			else if(selectedItem.name.equals("Wood")||selectedItem.name.equals("Stone")) {
				
				if(currentAbility == selectedItem) {
					currentAbility = null;
				}
				else {
					currentAbility = selectedItem;
				}
				
//				int slotX = gp.ui.slotXStart;
//				int slotY = gp.ui.slotYStart;
//				
//				for(int i = 0; i < inventory.size(); i++) {
//					g2.setColor(new Color(255, 165, 0));
//					g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
//					
//					g2.drawImage(inventory.get(i).down1, slotX, slotY, null);
//				}
			}
			
		}
	}
	public Entity currentItem() {
		int itemIndex = gp.ui.getItemIndexOnSlot(gp.ui.playerSlotCol, gp.ui.playerSlotRow);
		Entity temp = null;
		Entity selectedItem = null;
		
		for(int i = 0; i < inventory.size(); i++) {
			if(inventory.get(i) != null) {
				if(inventory.get(i).isSelected) {
					selectedItem = inventory.get(i);
					temp = selectedItem;
				}
			}
		}
		
		if(selectedItem == null) {
			if(itemIndex < inventory.size()) {
				//if(inventory.get(itemIndex).)
				selectedItem = inventory.get(itemIndex);
				temp = selectedItem;
			}
		}
		
		return temp;
	}
	public int searchItemInInventory(String itemName) {
		
		int itemIndex = 999;
		
		for(int i = 0; i < inventory.size(); i++) {
			if(inventory.get(i).name.equals(itemName)) {
				itemIndex = i;
				break;
			}
		}
		return itemIndex;
	}
	public boolean canObtainItem(Entity item) {
		//check if there is space in inventory for that item
		boolean canObtain = false;
		
		Entity newItem = gp.eGenerator.getObject(item.name);
		
		// CHECK IF ITEM IS STACKABLE
		if(newItem.stackable == true) {
			
			int index = searchItemInInventory(newItem.name);
			
			if(index != 999) {
				inventory.get(index).amount++;
				canObtain = true;
			}
			else { // Is a new item, so need to check vacancy
				if(inventory.size() != maxInventorySize) {
					
					
					inventory.add(newItem);
					
					canObtain = true;
				}
			}
		}
		else { // not stackable, so check vacancy
			if(inventory.size() != maxInventorySize) {
				if(newItem.type == type_projectile) {
					if(newItem.name.equals("Fireball")) {
						projectile = new OBJ_Fireball(gp);
					}
					else if(newItem.name.equals("Ninja star")){
						projectile = new OBJ_Ninjastar(gp);
					}
					else if(newItem.name.equals("Rock")){
						projectile = new OBJ_Rock_forPlayer(gp);
					}
					else {
						// (add later) THIS IS WHERE YOU ADD THE PROJECTILES!!! VERY IMPORTANT!!!
					}
				}
				inventory.add(newItem);
				
				//System.out.println("eree");
				if(newItem.type == type_emblem) {
					
					if(newItem.name.equals("Ranger Emblem")) {
						
					}
					else if(newItem.name.equals("Guardian Emblem")) {
						
					}
					else if(newItem.name.equals("Specialist Emblem")) {
						
					}
					else if(newItem.name.equals("Warrior Emblem")) {
						
					}
				}
				
				canObtain = true;
			}
		}
		return canObtain;
	}
	public void draw(Graphics2D g2) {
		
//		g2.setColor(Color.white);
//		g2.fillRect(x, y, gp.tileSize, gp.tileSize);
		
		BufferedImage image = null;
		int tempScreenX = screenX;
		int tempScreenY = screenY;
		
		int x = screenX;
		int y = screenY;
		
		int rightOffset = gp.screenWidth - screenX;
		int bottomOffset = gp.screenHeight - screenY;
		
		if(fieldOn) {
			guardianField =  setup("/objects/hero_guardian_field_activate", gp.tileSize*2, gp.tileSize*2);
			g2.drawImage(guardianField, rightOffset-gp.tileSize*2+24, bottomOffset-gp.tileSize*2+24, 96, 96, null);
			
		}
		
		if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
				worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
				worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
				worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
		switch(direction) {
		case "up":
			if(attacking == false) {
				if (spriteNum == 1) {image = up1;}
				if (spriteNum == 2) {image = up2;}
			}
			if(attacking == true) {
				
				if(currentSpecial != null) { // for warrior sword
					if(currentSpecial.name.equals("Warrior Sword")) {
						tempScreenY = screenY - gp.tileSize;
					}
				}
				else if(currentWeapon != null) {
					tempScreenY = screenY - gp.tileSize;
				}
				
				
				if (spriteNum == 1) {image = attackUp1;}
				if (spriteNum == 2) {image = attackUp2;}
			}
			if(guarding == true) {
				image = guardUp;
			}
			break;
		case "down":
			if (attacking == false) {
				if (spriteNum == 1) {image = down1;}
				if (spriteNum == 2) {image = down2;}
			}
			if (attacking == true) {
				if (spriteNum == 1) {image = attackDown1;}
				if (spriteNum == 2) {image = attackDown2;}
			}
			if(guarding == true) {
				image = guardDown;
			}
			break;
		case "left":
			if(attacking == false) {
				if (spriteNum == 1) {image = left1;}
				if (spriteNum == 2) {image = left2;}
			}
			if(attacking == true) {
				
				if(currentSpecial != null) { // for warrior sword
					if(currentSpecial.name.equals("Warrior Sword")) {
						tempScreenX = screenX - gp.tileSize;
					}
				}
				else if(currentWeapon != null) {
					tempScreenX = screenX - gp.tileSize;
				}
				
				
				if (spriteNum == 1) {image = attackLeft1;}
				if (spriteNum == 2) {image = attackLeft2;}
			}
			if(guarding == true) {
				image = guardLeft;
			}
			break;
		case "right":
			if(attacking == false) {
				if (spriteNum == 1) {image = right1;}
				if (spriteNum == 2) {image = right2;}
			}
			if(attacking == true) {
				if (spriteNum == 1) {image = attackRight1;}
				if (spriteNum == 2) {image = attackRight2;}
			}
			if(guarding == true) {
				image = guardRight;
			}
			break;
		}
		
		if(screenX > worldX) {
			x = worldX;
		}
		if(screenY > worldY) {
			y = worldY;
		}
		
		if(rightOffset > gp.worldWidth - worldX) {
			x = gp.screenWidth - (gp.worldWidth - worldX);
		}
		if(bottomOffset > gp.worldHeight - worldY) {
			y = gp.screenHeight - (gp.worldHeight - worldY);
		}
		if(worldX < gp.tileSize*10) {
			tempScreenX = x;
		}
		
		if(transparent == true) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
		}
		
		if(rightOffset > gp.worldWidth - worldX || bottomOffset > gp.worldHeight - worldY
				|| screenX > worldX || screenY > worldY) {
			if(drawing) {
				g2.drawImage(image, x, y, null);
			}
		}
		else {
			if(drawing) {
				g2.drawImage(image, tempScreenX, tempScreenY, null);
			}
			
		}
		//g2.drawImage(image, x, y, null);
		
		// This is to draw player hitbox
		//g2.setColor(Color.red);
		//g2.drawRect(screenX+solidArea.x, screenY+solidArea.y, solidArea.width, solidArea.height);
		
		// to draw weapon hitboxes, insert in the attacking try and catch...
		//g2.setColor(Color.blue);
		//g2.drawRect(screenX+solidArea.x, screenY+solidArea.height+solidArea.y-4, attackArea.width, attackArea.height);
		
		// Reset alpha (opacity of the player)
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		
		// DEBUG
//		g2.setFont(new Font("Arial", Font.PLAIN, 26));
//		g2.setColor(Color.white);
//		g2.drawString("Invincible:"+invincibleCounter, 10, 400);
		}
		else if(gp.player.screenX > gp.player.worldX ||
				gp.player.screenY > gp.player.worldY ||
				rightOffset > gp.worldWidth - gp.player.worldX ||
				bottomOffset > gp.worldHeight - gp.player.worldY) {
			switch(direction) {
				case "up":
					if(attacking == false) {
						if (spriteNum == 1) {image = up1;}
						if (spriteNum == 2) {image = up2;}
					}
					if(attacking == true) {
						tempScreenY = screenY - gp.tileSize;
						if (spriteNum == 1) {image = attackUp1;}
						if (spriteNum == 2) {image = attackUp2;}
					}
					break;
				case "down":
					if (attacking == false) {
						if (spriteNum == 1) {image = down1;}
						if (spriteNum == 2) {image = down2;}
					}
					if (attacking == true) {
						if (spriteNum == 1) {image = attackDown1;}
						if (spriteNum == 2) {image = attackDown2;}
					}
					break;
				case "left":
					if(attacking == false) {
						if (spriteNum == 1) {image = left1;}
						if (spriteNum == 2) {image = left2;}
					}
					if(attacking == true) {
						tempScreenX = screenX - gp.tileSize;
						if (spriteNum == 1) {image = attackLeft1;}
						if (spriteNum == 2) {image = attackLeft2;}
					}
					break;
				case "right":
					if(attacking == false) {
						if (spriteNum == 1) {image = right1;}
						if (spriteNum == 2) {image = right2;}
					}
					if(attacking == true) {
						if (spriteNum == 1) {image = attackRight1;}
						if (spriteNum == 2) {image = attackRight2;}
					}
					break;
				}
				
			if(rightOffset > gp.worldWidth - worldX || bottomOffset > gp.worldHeight - worldY
					|| screenX > worldX || screenY > worldY) {
				g2.drawImage(image, x, y, null);
			}
			else {
				g2.drawImage(image, tempScreenX, tempScreenY, null);
			}
				//g2.drawImage(image, x, y, null);
				
				// This is to draw player hitbox
				//g2.setColor(Color.red);
				//g2.drawRect(screenX+solidArea.x, screenY+solidArea.y, solidArea.width, solidArea.height);
				
				// Reset alpha (opacity of the player)
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
			
		}
		
	}
	public boolean checkAllQuests(int cMap) { // cMap is currentMap
		if(qH.levelQuestComplete(cMap) &&
		qH.bossQuestComplete(cMap) &&
		qH.collectorQuestComplete(cMap) &&
		qH.enemyQuestComplete(cMap)) {
			
			if(allQuestsFinishedCounter.get(gp.currentMap) == false) {
				
				gp.player.canObtainItem(new OBJ_Travel_Token(gp));
				allQuestsFinishedCounter.replace(gp.currentMap, true);
				gp.ui.addMessage("All quests complete!");
				return true;
			}
		}
		return false;
	}
}
