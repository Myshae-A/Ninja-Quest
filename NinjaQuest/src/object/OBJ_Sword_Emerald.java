package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword_Emerald extends Entity{

	public static final String objName = "Emerald Sword";
	
	public OBJ_Sword_Emerald(GamePanel gp) {
		super(gp);
		
		type = type_sword;
		name = objName;
		down1 = setup("/objects/sword_emerald", gp.tileSize, gp.tileSize);
		attackValue = 16;
		attackArea.width = 30;
		attackArea.height = 30; // Slightly longer attack height
		price = 3000;
		knockBackPower = 5;
		motion1_duration = 4;
		motion2_duration = 16;
		durability = 3000;
		
		description = "[" + name + "]\nActually strong.\nAttack: "+attackValue+
				"\nKnockback: "+knockBackPower;// + "\nDurability: "+durability;
	}
}
