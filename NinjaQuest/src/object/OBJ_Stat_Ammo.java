package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Stat_Ammo extends Entity{
	
	public static final String objName = "Ammo Stat";
	GamePanel gp;
	
	public OBJ_Stat_Ammo(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_obstacle;
		name = objName;
		down1 = setup("/objects/statMana", gp.tileSize, gp.tileSize);
		collision = true;
		
		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width = 48;
		solidArea.height = 48;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		setDialogue();
		
		dialogueSet = -1;
		
		description = "[" + name + "]";
	}
	public void setDialogue() {
		
		dialogues[0][0] = "This increases the player's Ammo.\nYou can only choose 1.";
		
		dialogues[1][0] = "Are you sure you want to increase your Ammo?\nIf so, press again.";
		
		dialogues[2][0] = "Your Ammo has been increased.";
	}
	public void interact() {
		
		dialogueSet++;
		if(dialogueSet == 0) {
			startDialogue(this,dialogueSet);
			
		}
		if(dialogueSet == 1) {
			startDialogue(this,dialogueSet);
		}
		if(dialogueSet == 2) {
			gp.player.maxMana+=1;
			gp.player.mana+=1;
			startDialogue(this,dialogueSet);
			this.use();
		}
	}
	public boolean use() {
		for(int i = 0; i < gp.obj[gp.currentMap].length;i++) {
			if(gp.obj[gp.currentMap][i] != null) {
				if(gp.obj[gp.currentMap][i].name.equals("Ammo Stat") ||
						gp.obj[gp.currentMap][i].name.equals("Attack Stat") ||
						gp.obj[gp.currentMap][i].name.equals("Defense Stat") ||
						gp.obj[gp.currentMap][i].name.equals("HP Stat")) {
					gp.obj[gp.currentMap][i] = null;
				}
			}
		}
		return true;
	}
}
