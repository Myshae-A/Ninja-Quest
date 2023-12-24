package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shield_Metal extends Entity{

	public static final String objName = "Metal Shield";
	
	public OBJ_Shield_Metal(GamePanel gp) {
		super(gp);
		
		type = type_shield;
		name = objName;
		down1 = setup("/objects/shield_metal", gp.tileSize, gp.tileSize);
		defenseValue = 3;
		price = 1200;
		durability = 1000;
		
		description = "[" + name + "]\nSerious gamers only.\nDefense: "+defenseValue;
				//+ "\nDurability: " +durability;
	}
}
