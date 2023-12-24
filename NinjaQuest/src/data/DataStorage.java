package data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class DataStorage implements Serializable{

	private static final long serialVersionUID = 1L; // idk what this is for, but it fixed the yellow squiggly
	
	boolean haveLoadedGameBefore = false;
	
	// Current MAP
	int currentMap;
	int currentArea;
	int playerWorldX;
	int playerWorldY;
	
	// PLAYER STATS
	int level;
	int maxLife;
	int life;
	int maxMana;
	int mana;
	int strength; // The more strength he has, the more damage he gives.
	int rangeAttack;
	int dexterity; // The more dexterity he has, the less damage he receives.
	int exp;
	int nextLevelExp;
	int coin;
	
	int deathCount;
	
	ArrayList<String> itemNames = new ArrayList<String>();
	ArrayList<Integer> itemAmounts = new ArrayList<Integer>();
	ArrayList<Integer> itemDurabilities = new ArrayList<Integer>();
	int currentWeaponSlot;
	int currentShieldSlot;
	int currentProjectileSlot;
	int currentLightSlot;
	int currentSpecialSlot;
	int currentAbilitySlot;
	
	public Map<String,Integer> monsDefeated;
	public Map<String,Integer> bossesDefeated;
	
	public Map<Integer,Boolean> enemyQuestCounter; // <map#, questComplete>
	public Map<Integer,Boolean> bossQuestCounter;
	public Map<Integer,Boolean> levelQuestCounter;
	public Map<Integer,Boolean> collectorQuestCounter;
	public Map<Integer,Boolean> allQuestsFinishedCounter;
	
	// OBJECT ON MAP
	String mapObjectNames[][];
	int mapObjectWorldX[][];
	int mapObjectWorldY[][];
	String mapObjectLootNames[][];
	boolean mapObjectOpened[][];
	
	// for Hero cutscenes to happen only once...
	public boolean danielPressed;
	public boolean aldenPressed;
	public boolean joyPressed;
	public boolean estherPressed;
	
	public boolean beatEntireGame;
	
	public int dayState; // not used...
	
	public int dayNumber;
}
