package main;

import java.util.HashMap;
import java.util.Map;

public class QuestHandler {
	GamePanel gp;

	public Map<String,Integer> monsDefeated; // each monster has a killcount of times player has beat
	public int totalMons; // Total normal monsters player has defeated, maybe use later...
	public Map<String,Integer> bossesDefeated; 
	public int totalBosses;
	public int totalDeaths; // total # of times player has died, maybe use later...
	
	// so that once true, always true:
	public boolean cQuest1; // home
	public boolean cQuest2;// beginner's Isle
	public boolean cQuest3;// Frosty Forest
	public boolean cQuest4;// FInal Island
	
	
	public QuestHandler(GamePanel gp) {
		this.gp = gp;
		
		monsDefeated = new HashMap<String,Integer>();
		monsDefeated.put("Bat",0);
		monsDefeated.put("Green Slime",0);
		monsDefeated.put("Blue Slime",0);
		monsDefeated.put("Red Slime",0);
		monsDefeated.put("Misty Slime",0);
		monsDefeated.put("Banana Slime",0);
		monsDefeated.put("Pink Slime",0);
		monsDefeated.put("Purple Slime",0);
		monsDefeated.put("Orc", 0);
		monsDefeated.put("Frost Orc", 0);
		monsDefeated.put("Skeleton", 0);
		monsDefeated.put("Elite Skeleton", 0);
		
		bossesDefeated = new HashMap<String,Integer>();
		bossesDefeated.put("Slime Boss",0);
		bossesDefeated.put("Skeleton Lord",0);
		bossesDefeated.put("Pirate King",0);
		bossesDefeated.put("Samurai",0);
		bossesDefeated.put("Armaggedon", 0);
	}
	
	public boolean levelQuestComplete(int map) {
		
		if((map == 0||map == 3||map == 4)) {if(gp.player.level >= 10) {return true;}}
		else if((map == 1||map == 2)) {if(gp.player.level >= 20) {return true;}}
		else if(map == 5) {if(gp.player.level >= 30) {return true;}}
		else if((map == 6 || map == 7)) {if(gp.player.level >= 5) {return true;}} // WORKING NOW!
		
		return false;
	}
	
	public boolean bossQuestComplete(int map) {
		if((map == 0 || map == 3 || map == 4) && bossesDefeated.get("Skeleton Lord") >= 1) {
			return true;
		}
		if((map == 1 || map == 2) && bossesDefeated.get("Pirate King") >= 1) {
			return true;
		}
		if(map == 5 && bossesDefeated.get("Samurai") >= 1) {
			return true;
		}
		if((map == 6 || map == 7) && bossesDefeated.get("Slime Boss") >= 1) {
			return true;
		}
		return false;
	}
	
	public boolean collectorQuestComplete(int map) {
		
		if(map == 0 || map == 3 || map == 4) {
			if(cQuest2) {
				return true;
			}
			for(int i = 0; i < gp.player.inventory.size(); i++) {
				if(gp.player.inventory.get(i).name.equals("Marksman Bow") ||
					gp.player.inventory.get(i).name.equals("Guardian Field") ||
					gp.player.inventory.get(i).name.equals("Special Cloak") ||
					gp.player.inventory.get(i).name.equals("Warrior Sword")) {
					cQuest2 = true;
					return true;
				}
			}
			
		}
		else if(map == 1 || map == 2) {
			if(cQuest3) {
				return true;
			}
			for(int i = 0; i < gp.player.inventory.size(); i++) {
				if(gp.player.inventory.get(i).name.equals("Max Red Potion")) {
					if(gp.player.inventory.get(i).amount >= 5) {
						cQuest3 = true;
						return true;
					}
				}
			}
		}
		else if(map == 5) {
			if(cQuest4) {
				return true;
			}
			if(gp.player.coin > 9000) { // used to be 9,000!
				cQuest4 = true;
				return true;
			}
		}
		else if((map == 6 || map == 7)) {
			if(cQuest1) {
				return true;
			}
			for(int i = 0; i < gp.player.inventory.size(); i++) {
				if(gp.player.inventory.get(i).name.equals("Blue Sword")) {
					cQuest1 = true;
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean enemyQuestComplete(int map) {
		if((map == 0 || map == 3 || map == 4) && monsDefeated.get("Blue Slime") >= 5) {
			return true;
		}
		if((map == 1 || map == 2) && monsDefeated.get("Frost Orc") >= 3) {
			return true;
		}
		if(map == 5 && monsDefeated.get("Elite Skeleton") >= 5) {
			return true;
		}
		if((map == 6 || map == 7) && monsDefeated.get("Orc") >= 3) {
			return true;
		}
		return false;
	}
	public boolean checkAllBosses() {
		if(bossesDefeated.get("Slime Boss") >=2 &&
			bossesDefeated.get("Skeleton Lord") >=2 &&
			bossesDefeated.get("Pirate King") >=2 &&
			bossesDefeated.get("Samurai") >=2) {
			return true;
		}
		return false;
	}
	
//	public boolean escortQuestComplete(int map) {
//		return false;
//	}
//	
//	public boolean deliveryQuestComplete(int map) {
//		return false;
//	}
}
