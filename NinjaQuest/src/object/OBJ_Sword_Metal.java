package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword_Metal extends Entity{

	public static final String objName = "Metal Sword";
	
	public OBJ_Sword_Metal(GamePanel gp) {
		super(gp);
		
		type = type_sword;
		name = objName;
		down1 = setup("/objects/sword_metal", gp.tileSize, gp.tileSize);
		attackValue = 10;
		attackArea.width = 30;
		attackArea.height = 30; // Slightly longer attack height
		price = 1500;
		knockBackPower = 4;
		motion1_duration = 4;
		motion2_duration = 20;
		durability = 1500;
		
		description = "[" + name + "]\nMade of strong metal.\nAttack: "+attackValue+
				"\nKnockback: "+knockBackPower;// + "\nDurability: "+durability;
	}

}
