package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Pickaxe extends Entity{

	public static final String objName = "Pickaxe";
	
	public OBJ_Pickaxe(GamePanel gp) {
		super(gp);
		
		name = objName;
		type = type_pickaxe;
		down1 = setup("/objects/pickaxe", gp.tileSize, gp.tileSize);
		attackValue = 2;
		attackArea.width = 30;
		attackArea.height = 30;
		price = 750;
		knockBackPower = 10;
		motion1_duration = 10;
		motion2_duration = 20;
		durability = 1000;
		
		description = "[" + name + "]\nThe one and only.\nAttack: "+attackValue+
				"\nKnockback: "+knockBackPower;// + "\nDurability: "+durability;
	
	}
}
