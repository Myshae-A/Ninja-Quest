package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword_Blue extends Entity{

	public static final String objName = "Blue Sword";
	
	public OBJ_Sword_Blue(GamePanel gp) {
		super(gp);
		
		type = type_sword;
		name = objName;
		down1 = setup("/objects/sword_blue", gp.tileSize, gp.tileSize);
		attackValue = 4;
		attackArea.width = 30;
		attackArea.height = 30;
		price = 500;
		knockBackPower = 3;
		motion1_duration = 4;
		motion2_duration = 24;
		durability = 500;
		
		description = "[" + name + "]\nNewer and bluer.\nAttack: "+attackValue+
				"\nKnockback: "+knockBackPower;// + "\nDurability: "+durability;
	}

}
