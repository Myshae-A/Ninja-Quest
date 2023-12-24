package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Key extends Entity{

	public static final String objName = "Key";
	
	GamePanel gp;
	
	public OBJ_Key(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_consumable;
		//type = type_key;
		name = objName;
		//type = type_pickupOnly;
		value = 1;
		stackable = true;
		down1 = setup("/objects/key", gp.tileSize, gp.tileSize);
		image = setup("/objects/manacrystal_full",gp.tileSize,gp.tileSize);
		description = "[" + name + "]\nOpens doors.";
		price = 500;
		
		setDialogue();
	}
	public void setDialogue() {
		
		dialogues[0][0] = "You used the " + name + " and opened the door.";
		
		dialogues[1][0] = "Use this key on a door!";
	}
	public boolean use(Entity entity) { // I added all this so enemies can drop keys,
		// but that didn't work, but i'm too lazy to delete all this so yea...
		
//		gp.playSE(21);
//		gp.ui.addMessage("You obtained a key!");
//		gp.obj[gp.currentMap][value] = new OBJ_Potion_Red(gp);
//		gp.obj[gp.currentMap][value].worldX = gp.tileSize*worldX;
//		gp.obj[gp.currentMap][value].worldY = gp.tileSize*worldY;
//		value++;
		
		int objIndex = getDetected(entity,gp.obj,"Door");
		
		if(objIndex != 999) {
			startDialogue(this,0);
			gp.playSE(3);;
			gp.obj[gp.currentMap][objIndex] = null;
			return true;
		}
		else {
			// 13:07
			startDialogue(this,1);
			return false;
			// 14:08
		}
	}
}
