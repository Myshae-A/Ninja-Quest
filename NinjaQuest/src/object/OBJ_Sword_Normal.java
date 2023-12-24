package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword_Normal extends Entity{

	public static final String objName = "Normal Sword";
	
	public OBJ_Sword_Normal(GamePanel gp) {
		super(gp);
		
		type = type_sword;
		name = objName;
		down1 = setup("/objects/sword_normal", gp.tileSize, gp.tileSize);
		attackValue = 2;
		attackArea.width = 24;
		attackArea.height = 42;
		price = 100;
		knockBackPower = 2;
		motion1_duration = 5;
		motion2_duration = 25;
		durability = 100;
		
		description = "[" + name + "]\nPretty decent.\nAttack: "+attackValue+
				"\nKnockback: "+knockBackPower;// + "\nDurability: "+durability;
	}

}
