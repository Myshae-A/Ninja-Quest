package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import main.GamePanel;

public class SaveLoad {

	GamePanel gp;
	
	public SaveLoad(GamePanel gp) {
		this.gp = gp;
	}
	public void save() {
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));
			
			DataStorage ds = new DataStorage();
			gp.haveLoadedGameBefore = true;
			ds.haveLoadedGameBefore = true;
			ds.deathCount = gp.player.deathCount;
			
			//ds.dayState = gp.eManager.lighting.dayState;
			
			// saving all enemy death counts
			ds.monsDefeated = new HashMap<String,Integer>();
			ds.monsDefeated.put("Bat",0);
			ds.monsDefeated.put("Green Slime",0);
			ds.monsDefeated.put("Blue Slime",0);
			ds.monsDefeated.put("Red Slime",0);
			ds.monsDefeated.put("Misty Slime",0);// start here (for new mons reference)
			ds.monsDefeated.put("Banana Slime",0);
			ds.monsDefeated.put("Pink Slime",0);
			ds.monsDefeated.put("Purple Slime",0);
			ds.monsDefeated.put("Orc", 0);
			ds.monsDefeated.put("Frost Orc", 0);
			ds.monsDefeated.put("Skeleton", 0);
			ds.monsDefeated.put("Elite Skeleton", 0);
			
			ds.bossesDefeated = new HashMap<String,Integer>();
			ds.bossesDefeated.put("Slime Boss",0);
			ds.bossesDefeated.put("Skeleton Lord",0);
			ds.bossesDefeated.put("Pirate King",0);
			ds.bossesDefeated.put("Samurai",0);
			ds.bossesDefeated.put("Armaggedon", 0);
			
			ds.monsDefeated.put("Bat",gp.qH.monsDefeated.get("Bat"));
			ds.monsDefeated.put("Green Slime",gp.player.qH.monsDefeated.get("Green Slime"));
			ds.monsDefeated.put("Blue Slime",gp.player.qH.monsDefeated.get("Blue Slime"));
			ds.monsDefeated.put("Red Slime",gp.player.qH.monsDefeated.get("Red Slime"));
			ds.monsDefeated.put("Misty Slime",gp.qH.monsDefeated.get("Misty Slime"));
			ds.monsDefeated.put("Banana Slime",gp.player.qH.monsDefeated.get("Banana Slime"));
			ds.monsDefeated.put("Pink Slime",gp.player.qH.monsDefeated.get("Pink Slime"));
			ds.monsDefeated.put("Purple Slime",gp.player.qH.monsDefeated.get("Purple Slime"));
			ds.monsDefeated.put("Orc",gp.player.qH.monsDefeated.get("Orc"));
			ds.monsDefeated.put("Frost Orc",gp.player.qH.monsDefeated.get("Frost Orc"));
			ds.monsDefeated.put("Skeleton",gp.player.qH.monsDefeated.get("Skeleton"));
			ds.monsDefeated.put("Elite Skeleton",gp.player.qH.monsDefeated.get("Elite Skeleton"));
			
			ds.monsDefeated.put("Slime Boss",gp.player.qH.bossesDefeated.get("Slime Boss"));
			ds.monsDefeated.put("Skeleton Lord",gp.player.qH.bossesDefeated.get("Skeleton Lord"));
			ds.monsDefeated.put("Pirate King",gp.player.qH.bossesDefeated.get("Pirate King"));
			ds.monsDefeated.put("Samurai",gp.player.qH.bossesDefeated.get("Samurai"));
			ds.monsDefeated.put("Armaggedon",gp.player.qH.bossesDefeated.get("Armaggedon"));
			
			ds.enemyQuestCounter = new HashMap<Integer,Boolean>();
			for(int i = 0; i < gp.maxMap; i++) {
				ds.enemyQuestCounter.put(i, false);
			}
			ds.bossQuestCounter = new HashMap<Integer,Boolean>();
			for(int i = 0; i < gp.maxMap; i++) {
				ds.bossQuestCounter.put(i, false);
			}
			ds.levelQuestCounter = new HashMap<Integer,Boolean>();
			for(int i = 0; i < gp.maxMap; i++) {
				ds.levelQuestCounter.put(i, false);
			}
			ds.collectorQuestCounter = new HashMap<Integer,Boolean>();
			for(int i = 0; i < gp.maxMap; i++) {
				ds.collectorQuestCounter.put(i, false);
			}
			ds.allQuestsFinishedCounter = new HashMap<Integer,Boolean>();
			for(int i = 0; i < gp.maxMap; i++) {
				ds.allQuestsFinishedCounter.put(i, false);
			}
			
			for(int i = 0; i < 8; i++) {
				ds.enemyQuestCounter.put(i, gp.player.enemyQuestCounter.get(i));
				ds.bossQuestCounter.put(i, gp.player.bossQuestCounter.get(i));
				ds.levelQuestCounter.put(i, gp.player.levelQuestCounter.get(i));
				ds.collectorQuestCounter.put(i, gp.player.collectorQuestCounter.get(i));
				ds.allQuestsFinishedCounter.put(i, gp.player.allQuestsFinishedCounter.get(i));
			}
			
			
			if(gp.currentMap == 2) { // if boss map, reset to misty meadows, meaning u can respawn in dungeons too
				ds.currentMap = 1;
			}
			else {
				ds.currentMap = gp.currentMap;
			}
			ds.currentArea = gp.currentArea;
			ds.playerWorldX = gp.player.worldX;
			ds.playerWorldY = gp.player.worldY;
			
			ds.level = gp.player.level;
			ds.maxLife = gp.player.maxLife;
			ds.life = gp.player.life;
			ds.maxMana = gp.player.maxMana;
			ds.mana = gp.player.mana;
			ds.strength = gp.player.strength;
			ds.rangeAttack = gp.player.rangeAttack;
			ds.dexterity = gp.player.dexterity;
			ds.exp = gp.player.exp;
			ds.nextLevelExp = gp.player.nextLevelExp;
			ds.coin = gp.player.coin;
			
			// PLAYER INVENTORY
			for(int i = 0; i < gp.player.inventory.size(); i++) {
				ds.itemNames.add(gp.player.inventory.get(i).name);
				ds.itemAmounts.add(gp.player.inventory.get(i).amount);
				ds.itemDurabilities.add(gp.player.inventory.get(i).durability);
			}
			
			
			
			// PLAYER EQUIPMENT
			// I ADDED some extra code, so if the currentItem is null, then we can make it work more safely
			if(gp.player.currentWeapon != null) {
				ds.currentWeaponSlot = gp.player.getCurrentWeaponSlot();
			}
			else {
				ds.currentWeaponSlot = -1;
			}
			if(gp.player.currentShield != null) {
				ds.currentShieldSlot = gp.player.getCurrentShieldSlot();
			}
			else {
				ds.currentShieldSlot = -1;
			}
			if(gp.player.currentProjectile != null) {
				ds.currentProjectileSlot = gp.player.getCurrentProjectileSlot();
			}
			else {
				ds.currentProjectileSlot = -1;
			}
			if(gp.player.currentLight != null) {
				ds.currentLightSlot = gp.player.getCurrentLightSlot();
			}
			else {
				ds.currentLightSlot = -1;
			}
			if(gp.player.currentSpecial != null) {
				ds.currentSpecialSlot = gp.player.getCurrentSpecialSlot();
			}
			else {
				ds.currentSpecialSlot = -1;
			}
			if(gp.player.currentAbility != null) {
				ds.currentAbilitySlot = gp.player.getCurrentAbilitySlot();
			}
			else {
				ds.currentAbilitySlot = -1;
			}
			
			
			// OBJECTS ON MAP
			ds.mapObjectNames = new String[gp.maxMap][gp.obj[1].length];
			ds.mapObjectWorldX = new int[gp.maxMap][gp.obj[1].length];
			ds.mapObjectWorldY = new int[gp.maxMap][gp.obj[1].length];
			ds.mapObjectLootNames = new String[gp.maxMap][gp.obj[1].length];
			ds.mapObjectOpened = new boolean[gp.maxMap][gp.obj[1].length];
			
			for(int mapNum = 0; mapNum < gp.maxMap; mapNum++) {
				
				for(int i = 0; i < gp.obj[1].length; i++) {
					
					if(gp.obj[mapNum][i] == null) {
						ds.mapObjectNames[mapNum][i] = "NA";
					}
					else {
						ds.mapObjectNames[mapNum][i] = gp.obj[mapNum][i].name;
						ds.mapObjectWorldX[mapNum][i] = gp.obj[mapNum][i].worldX;
						ds.mapObjectWorldY[mapNum][i] = gp.obj[mapNum][i].worldY;
						if(gp.obj[mapNum][i].loot != null) {
							ds.mapObjectLootNames[mapNum][i] = gp.obj[mapNum][i].loot.name;
						}
						ds.mapObjectOpened[mapNum][i] = gp.obj[mapNum][i].opened;
					}
				}
			}
			
			ds.danielPressed = gp.eHandler.danielPressed;
			ds.aldenPressed = gp.eHandler.aldenPressed;
			ds.joyPressed = gp.eHandler.joyPressed;
			ds.estherPressed = gp.eHandler.estherPressed;
			
			ds.beatEntireGame = gp.beatEntireGame;
			
			ds.dayNumber = gp.dayNumber;
			
			// write the datastorage object
			oos.writeObject(ds);
		}
		catch(Exception e ) {
			System.out.println("Save Exception!");
		}
	}
	public void load() {
		
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));
			
			// read the DataStorage object
			DataStorage ds = (DataStorage)ois.readObject();
			//System.out.println(ds.haveLoadedGameBefore);
			gp.haveLoadedGameBefore = ds.haveLoadedGameBefore;
			gp.player.deathCount = ds.deathCount;
			
			//gp.eManager.lighting.dayState = ds.dayState;
			
			gp.player.qH.monsDefeated.put("Bat",ds.monsDefeated.get("Bat"));
			gp.player.qH.monsDefeated.put("Green Slime",ds.monsDefeated.get("Green Slime"));
			gp.player.qH.monsDefeated.put("Blue Slime",ds.monsDefeated.get("Blue Slime"));
			gp.player.qH.monsDefeated.put("Red Slime",ds.monsDefeated.get("Red Slime"));
			gp.player.qH.monsDefeated.put("Misty Slime",ds.monsDefeated.get("Misty Slime"));
			gp.player.qH.monsDefeated.put("Banana Slime",ds.monsDefeated.get("Banana Slime"));
			gp.player.qH.monsDefeated.put("Pink Slime",ds.monsDefeated.get("Pink Slime"));
			gp.player.qH.monsDefeated.put("Purple Slime",ds.monsDefeated.get("Purple Slime"));
			gp.player.qH.monsDefeated.put("Orc",ds.monsDefeated.get("Orc"));
			gp.player.qH.monsDefeated.put("Frost Orc",ds.monsDefeated.get("Frost Orc"));
			gp.player.qH.monsDefeated.put("Skeleton",ds.monsDefeated.get("Skeleton"));
			gp.player.qH.monsDefeated.put("Elite Skeleton",ds.monsDefeated.get("Elite Skeleton"));
			
			gp.player.qH.bossesDefeated.put("Slime Boss",ds.monsDefeated.get("Slime Boss"));
			gp.player.qH.bossesDefeated.put("Skeleton Lord",ds.monsDefeated.get("Skeleton Lord"));
			gp.player.qH.bossesDefeated.put("Pirate King",ds.monsDefeated.get("Pirate King"));
			gp.player.qH.bossesDefeated.put("Samurai",ds.monsDefeated.get("Samurai"));
			gp.player.qH.bossesDefeated.put("Armaggedon",ds.monsDefeated.get("Armaggedon"));
			
			for(int i = 0; i < 8; i++) {
				gp.player.enemyQuestCounter.put(i, ds.enemyQuestCounter.get(i));
				gp.player.bossQuestCounter.put(i, ds.bossQuestCounter.get(i));
				gp.player.levelQuestCounter.put(i, ds.levelQuestCounter.get(i));
				gp.player.collectorQuestCounter.put(i, ds.collectorQuestCounter.get(i));
				gp.player.allQuestsFinishedCounter.put(i, ds.allQuestsFinishedCounter.get(i));
			}
			
			gp.currentMap = ds.currentMap;
			gp.currentArea = ds.currentArea;
			gp.player.worldX = ds.playerWorldX;
			gp.player.worldY = ds.playerWorldY;
			
			gp.player.level = ds.level;
			gp.player.maxLife = ds.maxLife;
			gp.player.life = ds.life;
			gp.player.maxMana = ds.maxMana;
			gp.player.mana = ds.mana;
			gp.player.strength = ds.strength;
			gp.player.rangeAttack = ds.rangeAttack;
			gp.player.dexterity = ds.dexterity;
			gp.player.exp = ds.exp;
			gp.player.nextLevelExp = ds.nextLevelExp;
			gp.player.coin = ds.coin;
			
			// PLAYER INVENTORY
			gp.player.inventory.clear();
			for(int i = 0; i < ds.itemNames.size();i++) {
				gp.player.inventory.add(gp.eGenerator.getObject(ds.itemNames.get(i)));
				gp.player.inventory.get(i).amount = ds.itemAmounts.get(i);
				gp.player.inventory.get(i).durability = ds.itemDurabilities.get(i);
			}
			
			
			//System.out.println("Pst2");
			// PLAYER EQUIPMENT
			
			// Dont need else statements, just leave the currentItem slots blank...
			if(ds.currentWeaponSlot != -1) {
				gp.player.currentWeapon = gp.player.inventory.get(ds.currentWeaponSlot);
			}
			if(ds.currentShieldSlot != -1) {
				gp.player.currentShield = gp.player.inventory.get(ds.currentShieldSlot);
			}
			if(ds.currentProjectileSlot != -1) {
				gp.player.currentProjectile = gp.player.inventory.get(ds.currentProjectileSlot);
			}
			if(ds.currentLightSlot != -1) {
				gp.player.currentLight = gp.player.inventory.get(ds.currentLightSlot);
			}
			if(ds.currentSpecialSlot != -1) {
				gp.player.currentSpecial = gp.player.inventory.get(ds.currentSpecialSlot);
			}
			if(ds.currentAbilitySlot != -1) {
				gp.player.currentAbility = gp.player.inventory.get(ds.currentAbilitySlot);
			}
			
			
			// OKAY, EVERYTHING WORKS FROM THIS POINT ON NOW!!! i hope...
			
			
			//System.out.println("Pst");
			gp.player.getAttack();
			gp.player.getDefense();
			gp.player.getAttackImage();
			
			// OBJECTS ON MAP
			for(int mapNum = 0; mapNum < gp.maxMap; mapNum++) {
				for(int i = 0; i < gp.obj[1].length; i++) {
					if(ds.mapObjectNames[mapNum][i].equals("NA")) {
						gp.obj[mapNum][i] = null;
					}
					else {
						if(ds.mapObjectNames[mapNum][i].equals("Door Down") || ds.mapObjectNames[mapNum][i].equals("Door Up") ||
								ds.mapObjectNames[mapNum][i].equals("Door Left") ||ds.mapObjectNames[mapNum][i].equals("Door Right")) {
							// fixes the lock-all problem when loading...
						}
						else {
							gp.obj[mapNum][i] = gp.eGenerator.getObject(ds.mapObjectNames[mapNum][i]);
							gp.obj[mapNum][i].worldX = ds.mapObjectWorldX[mapNum][i];
							gp.obj[mapNum][i].worldY = ds.mapObjectWorldY[mapNum][i];
						}
						
						if(ds.mapObjectLootNames[mapNum][i] != null) {
							gp.obj[mapNum][i].setLoot(gp.eGenerator.getObject(ds.mapObjectLootNames[mapNum][i]));
						}
						gp.obj[mapNum][i].opened = ds.mapObjectOpened[mapNum][i];
						if(gp.obj[mapNum][i].opened == true) {
							gp.obj[mapNum][i].down1 = gp.obj[mapNum][i].image2;
						}
					}
				}
			}
			
			gp.eHandler.danielPressed = ds.danielPressed;
			gp.eHandler.aldenPressed = ds.aldenPressed;
			gp.eHandler.joyPressed = ds.joyPressed;
			gp.eHandler.estherPressed = ds.estherPressed;
			
			gp.beatEntireGame = ds.beatEntireGame;
			
			gp.dayNumber = ds.dayNumber-1; // because an extra day will be added as soon as you load a new game
		}
		catch(Exception e ) {
			// MUST ALWAYS CLEAR SAVE.dat file before exporting this game!!!
			//System.out.println("Load Exception!"); // THIS MESSAGE WILL ALWAYS DISPLAY, the VERY FIRST time u open the game...
		}
	}
}
