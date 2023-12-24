package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shield_Emerald extends Entity{

	public static final String objName = "Emerald Shield";
	
	public OBJ_Shield_Emerald(GamePanel gp) {
		super(gp);
		
		type = type_shield;
		name = objName;
		down1 = setup("/objects/shield_emerald", gp.tileSize, gp.tileSize);
		defenseValue = 4;
		price = 6000;
		durability = 5000;
		
		description = "[" + name + "]\nOne of the best.\nDefense: "+defenseValue;
				//+ "\nDurability: " +durability;
	}
}
